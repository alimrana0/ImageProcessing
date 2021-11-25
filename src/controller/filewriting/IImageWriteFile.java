package controller.filewriting;

import java.io.IOException;

import model.imaging.ImageOfPixel;

/**
 * Interface containing methods to write files by using its data to save using a given file type
 * and image.
 */
public interface IImageWriteFile {

  /**
   * Writes the file for the image under the given file type and the image file.
   *
   * @param name  image file path.
   * @param image image to be written as a file.
   * @throws IOException              If the file write fails.
   * @throws IllegalArgumentException If given null arguments.
   */
  void writeFile(String name, ImageOfPixel image) throws IOException, IllegalArgumentException;

}
