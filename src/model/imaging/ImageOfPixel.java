package model.imaging;

import java.io.IOException;
import java.util.List;

import model.imaging.pixel.IPixel;

/**
 * Interface representing an image made up of pixels.
 */
public interface ImageOfPixel {

  /**
   * Gets the 2D list of pixels of this pixel image.
   * @return
   */
  List<List<IPixel>> getPixels();

  /**
   * Saves an image as a PPM file given the name of the file to save it as.
   *
   * @param filename The name of the ppm file to create.
   * @throws IOException Thrown if the file output stream does not function correctly.
   */
  void saveImageAsPPM(String filename) throws IOException;
}