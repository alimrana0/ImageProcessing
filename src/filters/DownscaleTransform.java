package filters;

import java.util.ArrayList;
import java.util.List;

import model.imaging.Color;
import model.imaging.Image;
import model.imaging.ImageOfPixel;
import model.imaging.Posn;
import model.imaging.pixel.IPixel;
import model.imaging.pixel.Pixel;

/**
 * Class to represent a downscale transformation.
 */
public class DownscaleTransform implements Downscale {

  @Override
  public ImageOfPixel apply(ImageOfPixel image, int newWidth, int newHeight) {
    if (image == null || image.getPixels().size() == 0 || image.getPixels().get(0).size() == 0) {
      throw new IllegalArgumentException("The input image is empty.");
    }

    if (newHeight < 0 || newWidth < 0) {
      throw new IllegalArgumentException("Cannot resize the image to negative size!");
    }

    if (newHeight >= image.getPixels().size() || newWidth >= image.getPixels().get(0).size()) {
      throw new IllegalArgumentException("The input size cannot be bigger than the original.");
    }

    int width = image.getPixels().get(0).size();
    int height = image.getPixels().size();
    List<ArrayList<IPixel>> newImage = new ArrayList<>();
    for (int i = 0; i < newHeight; i++) {
      ArrayList<IPixel> temp = new ArrayList<>();
      for (int j = 0; j < newWidth; j++) {
        temp.add(null);
      }
      newImage.add(temp);
    }

    for (int i = 0; i < newHeight; i++) {
      for (int j = 0; j < newWidth; j++) {
        getNewPixel(image.getPixels(), i, j, width, height, newWidth, newHeight, newImage);
      }
    }
    return new Image(newImage);
  }

  /**
   * Produces a new pixel for the cooresponding
   *
   * @param pixels    2D list of pixels representing the image.
   * @param y         The y coordinate of the old pixel.
   * @param x         The x coordinate of the new pixel.
   * @param width     The width of the original image.
   * @param height    The height of the original image.
   * @param newWidth  The width of the new image.
   * @param newHeight The height of the new image.
   * @param newImage  A 2d list of pixels representing the new image.
   */
  private void getNewPixel(List<List<IPixel>> pixels,
                           int y, int x, int width, int height, int newWidth,
                           int newHeight, List<ArrayList<IPixel>> newImage) {

    double newX = (x / (double) newWidth) * (double) width;
    double newY = (y / (double) newHeight) * (double) height;

    if (newX == 0) {
      newX = 0.5;
    }

    if (newY == 0) {
      newY = 0.5;
    }

    List<IPixel> pixelsAround = getPixelAround(pixels, newX, newY);

    int newRed = getCP(newY,
            getMorN(newX, pixelsAround.get(0).getColor().getRed(),
                    pixelsAround.get(1).getColor().getRed()),
            getMorN(newX, pixelsAround.get(2).getColor().getRed(),
                    pixelsAround.get(3).getColor().getRed()));
    int newGreen = getCP(newY,
            getMorN(newX, pixelsAround.get(0).getColor().getGreen(),
                    pixelsAround.get(1).getColor().getGreen()),
            getMorN(newX, pixelsAround.get(2).getColor().getGreen(),
                    pixelsAround.get(3).getColor().getGreen()));
    int newBlue = getCP(newY,
            getMorN(newX, pixelsAround.get(0).getColor().getBlue(),
                    pixelsAround.get(1).getColor().getBlue()),
            getMorN(newX, pixelsAround.get(2).getColor().getBlue(),
                    pixelsAround.get(3).getColor().getBlue()));

    IPixel newPixel = new Pixel(new Posn(x, y), new Color(newRed, newGreen, newBlue));
    newImage.get(y).set(x, newPixel);
  }

  // Formula to handle when a pixel gets mapped to a floating point value.
  private int getCP(double y, double m, double n) {
    if (y == Math.floor(y)) {
      return (int) (n + m) / 2;
    }

    return (int) (n * (y - Math.floor(y)) + m * (Math.ceil(y) - y));
  }

  // Returns a value to pass into the getCP method, which is a
  // formula to handle when a pixel gets mapped to a floating point value.
  private double getMorN(double x, double ca, double cb) {
    if (x == Math.floor(x)) {
      return (cb + ca) / 2.0;
    }

    return cb * (x - Math.floor(x)) + ca * (Math.ceil(x) - x);
  }

  // Gets the pixel at the given x and y location.
  private IPixel getPixel(List<List<IPixel>> pixels, int x, int y) {
    if (x >= pixels.get(0).size() || y >= pixels.size()) {
      return new Pixel(new Posn(x, y), new Color(1, 1, 1));
    }

    return pixels.get(y).get(x);
  }

  // Used when the new pixel location is a floating point number, so we have to round it off.
  private List<IPixel> getPixelAround(List<List<IPixel>> pixels, double newX, double newY) {
    int ceilX = (int) Math.ceil(newX);
    int floorX = (int) Math.floor(newX);
    int ceilY = (int) Math.ceil(newY);
    int floorY = (int) Math.floor(newY);

    IPixel a = getPixel(pixels, floorX, floorY);
    IPixel b = getPixel(pixels, ceilX, floorY);
    IPixel c = getPixel(pixels, floorX, ceilY);
    IPixel d = getPixel(pixels, ceilX, ceilY);

    List<IPixel> result = new ArrayList<>();
    result.add(a);
    result.add(b);
    result.add(c);
    result.add(d);

    return result;
  }
}
