package controller.filereading;

import java.io.IOException;

import model.imaging.ImageOfPixel;

/**
 * Interface containing methods to read files by loading and retrieving its contents.
 */
public interface IReadFile {

  /**
   * Reads a given file image and returns an image of pixels that represent the makeup of the given
   * file.
   *
   * @param name the file path.
   * @throws IllegalArgumentException If the file is null, nonexistent, or not retrievable.
   */
  ImageOfPixel readImage(String name) throws IOException;

}
