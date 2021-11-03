package model.imaging;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.imaging.pixel.IPixel;
import model.imaging.pixel.Pixel;

/**
 * Class to represent an image made of pixels.
 */
public class Image implements ImageOfPixel {
  private final List<ArrayList<IPixel>> pixels;

  /**
   * Constructs an image object from the given 2D list of pixels.
   *
   * @param pixels
   */
  public Image(List<ArrayList<IPixel>> pixels) {
    if (pixels == null) {
      throw new IllegalArgumentException("Pixels cannot be null!");
    }
    this.pixels = this.replicate(pixels);
  }


  /**
   * Replicates a 2D list of pixels.
   *
   * @param pixels the image's pixels
   * @return a 2D replicated list of the image's pixels.
   */
  private List<ArrayList<IPixel>> replicate(List<ArrayList<IPixel>> pixels) {
    List<ArrayList<IPixel>> tempPixels = new ArrayList<>();
    for (ArrayList<IPixel> row : pixels) {
      ArrayList<IPixel> rowCopy = new ArrayList<>();
      for (IPixel pixel : row) {
        rowCopy.add(new Pixel(pixel.getPosn(), pixel.getColor()));
      }
      tempPixels.add(rowCopy);
    }
    return tempPixels;
  }

  /**
   * Gets this images pixels.
   *
   * @return a 2D list of an image's pixels.
   */
  @Override
  public List<List<IPixel>> getPixels() {
    return new ArrayList<>(pixels);
  }

  /**
   * Saves an image as a PPM file given the name of the file to save it as.
   *
   * @param filename The name of the ppm file to create.
   * @throws IOException Thrown if the file output stream does not function correctly.
   */
  public void saveImageAsPPM(String filename) throws IOException {
    FileOutputStream outputStream = new FileOutputStream(filename);
    StringBuilder output = new StringBuilder();
    output.append("P3");
    output.append("\n" + this.pixels.get(1).size() + " " + this.pixels.size());
    output.append("\n255");
    for (List<IPixel> pixelList : this.pixels) {
      for (IPixel pixel : pixelList) {
        output.append("\n");
        output.append(pixel.getColor().getRed());
        output.append("\n");
        output.append(pixel.getColor().getGreen());
        output.append("\n");
        output.append(pixel.getColor().getBlue());
      }
    }
    outputStream.write(output.toString().getBytes());
    outputStream.close();
  }
}

