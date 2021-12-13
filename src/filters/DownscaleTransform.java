package filters;

import model.imaging.Color;
import model.imaging.Image;
import model.imaging.ImageOfPixel;
import model.imaging.Posn;
import model.imaging.pixel.IPixel;
import model.imaging.pixel.Pixel;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing the process of downscaling an image using given dimensions.
 */
public class DownscaleTransform implements IScale {

  @Override
  public ImageOfPixel scale(ImageOfPixel image, int adjustedWidth, int adjustedHeight) {
    if (adjustedWidth < 0 || adjustedHeight < 0) {
      throw new IllegalArgumentException("The image can't have negative dimensions.");
    }
    if (image == null || image.getPixels().size() == 0 || image.getPixels().get(0).size() == 0) {
      throw new IllegalArgumentException("Invalid image.");
    }
    if (adjustedWidth > image.getPixels().size()
            || adjustedHeight > image.getPixels().get(0).size()) {
      throw new IllegalArgumentException("Cannot downscale to a dimensions larger than original.");
    }


    List<ArrayList<IPixel>> downscaledImage = new ArrayList<>();

    for (int i = 0; i < adjustedHeight; i++) {
      ArrayList<IPixel> row = new ArrayList<>();
      for (int j = 0; j < adjustedWidth; j++) {
        row.add(null);
      }
      downscaledImage.add(row);
    }

    int currWidth = image.getPixels().get(0).size();
    int currHeight = image.getPixels().size();

    for (int i = 0; i < adjustedHeight; i++) {
      for (int j = 0; j < adjustedWidth; j++) {
        downscalePixel(image.getPixels(), i, j,
                currWidth, currHeight, adjustedWidth, adjustedHeight, downscaledImage);
      }
    }
    return new Image(downscaledImage);
  }

  /**
   * Returns a pixel to be placed in the downscaled image according to the proportions on its
   * location in the original image vs. that of the new image.
   *
   * @param imagePixels     the list of list of pixels of the image being downscaled.
   * @param row             the row of the pixel in the list of list of pixels of the image
   * @param col             the column of the pixel in the list of list of pixels of the image
   * @param currWidth       The original image's width
   * @param currHeight      The original image's height
   * @param adjustedWidth   the desired width of the downscaled image
   * @param adjustedHeight  the desired height of the downscaled image
   * @param downscaledImage the list of list of pixels representing the downscaled image.
   */
  private void downscalePixel(List<List<IPixel>> imagePixels,
                              int row, int col, int currWidth, int currHeight, int adjustedWidth,
                              int adjustedHeight, List<ArrayList<IPixel>> downscaledImage) {

    double adjustedCol = (col / (double) adjustedWidth) * (double) currWidth;
    double adjustedRow = (row / (double) adjustedHeight) * (double) currHeight;

    if (adjustedCol == 0) {
      adjustedCol = 0.5;
    }
    if (adjustedRow == 0) {
      adjustedRow = 0.5;
    }

    List<IPixel> peripherals = getPeripherals(imagePixels, adjustedCol, adjustedRow);

    int changedRed = findCP(adjustedRow,
            findMN(adjustedCol, peripherals.get(1).getColor().getRed(), peripherals.get(0).getColor().getRed()),
            findMN(adjustedCol, peripherals.get(3).getColor().getRed(), peripherals.get(2).getColor().getRed()));
    int changedGreen = findCP(adjustedRow,
            findMN(adjustedCol, peripherals.get(1).getColor().getGreen(), peripherals.get(0).getColor().getGreen()),
            findMN(adjustedCol, peripherals.get(3).getColor().getGreen(), peripherals.get(2).getColor().getGreen()));
    int changedBlue = findCP(adjustedRow,
            findMN(adjustedCol, peripherals.get(1).getColor().getBlue(), peripherals.get(0).getColor().getBlue()),
            findMN(adjustedCol, peripherals.get(3).getColor().getBlue(), peripherals.get(2).getColor().getBlue()));

    IPixel downscaledPixel = new Pixel(new Posn(col, row), new Color(changedRed, changedGreen, changedBlue));
    downscaledImage.get(row).set(col, downscaledPixel);
  }

  /**
   * Returns the Cp value of the new pixel.
   *
   * @param row the desired
   * @param m the m value of the pixel position (derived from pixel peripherals)
   * @param n the n value of the pixel position (derived from pixel peripherals)
   * @return the Cp value of the pixel location.
   */
  private int findCP(double row, double m, double n) {
    if (row != Math.floor(row)) {
      return (int) (n * (row - Math.floor(row)) + m * (Math.ceil(row) - row));
    }
    return (int) (m + n) / 2;
  }

  /**
   *
   * @param col the desired column of the pixel.
   * @param ca
   * @param cb
   * @return
   */
  private double findMN(double col, double cb, double ca) {
    if (col != Math.floor(col)) {
      return ca * (col - Math.floor(col)) + cb * (Math.ceil(col) - col);
    }
    return (ca + cb) / 2.0;
  }

  /**
   * Retrieves the pixel at the given location in the give list of list of pixels.
   * @param imagePixels the list of list of pixels representing the given image.
   * @param col the column of the position of the desired pixel
   * @param row the row of the position of the desired pixel
   * @return the pixel at the given location in the image.
   */
  private IPixel findPixel(List<List<IPixel>> imagePixels, int col, int row) {
    if (col >= imagePixels.get(0).size() || row >= imagePixels.size()) {
      return new Pixel(new Posn(col, row), new Color(1, 1, 1));
    }
    return imagePixels.get(row).get(col);
  }

  /**
   * Returns the peripheral pixels of the given desired pixel location
   * @param imagePixels the list of list of pixels representing the given image
   * @param adjustedCol the pixel column of the desired location in the given image.
   * @param adjustedRow the pixel column of the desired location in the given image.
   * @return the list of peripheral pixels at the designation location.
   */
  private List<IPixel> getPeripherals(List<List<IPixel>> imagePixels, double adjustedCol, double adjustedRow) {

    IPixel northwest = findPixel(imagePixels,
            (int) Math.floor(adjustedCol), (int) Math.floor(adjustedRow));
    IPixel northeast = findPixel(imagePixels,
            (int) Math.ceil(adjustedCol), (int) Math.floor(adjustedRow));
    IPixel southwest = findPixel(imagePixels,
            (int) Math.floor(adjustedCol), (int) Math.ceil(adjustedRow));
    IPixel southeast = findPixel(imagePixels,
            (int) Math.ceil(adjustedCol), (int) Math.ceil(adjustedRow));

    List<IPixel> peripherals = new ArrayList<>();
    peripherals.add(northwest);
    peripherals.add(northeast);
    peripherals.add(southwest);
    peripherals.add(southeast);

    return peripherals;
  }
}
