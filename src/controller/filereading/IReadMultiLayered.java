package controller.filereading;

import java.util.List;
import java.util.Map;

import model.imaging.ImageOfPixel;

/**
 * Interface containing methods to read files consisting of multilayered images
 * by loading and retrieving its contents.
 */
public interface IReadMultiLayered {

  /**
   * Takes in a formatted text file that contains information on a multilayered image (id, type,
   * path, visibility).
   *
   * @param name text file's path.
   * @return A map of IDs of images and their corresponding pixel images.
   * @throws IllegalArgumentException If the file is null, nonexistent, or not retrievable.
   */
  Map<String, ImageOfPixel> readImages(String name) throws IllegalArgumentException;

  /**
   * Finds the invisible images and returns their corresponding IDs.
   *
   * @return List of IDs.
   */
  List<String> findVisibility();

}
