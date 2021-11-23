package controller.filereading;

import java.util.List;
import java.util.Map;

import model.imaging.ImageOfPixel;

/**
 * Interface for function objects that can read in a text file representing a multi layer image.
 */
public interface IMultiLayerReader {

  /**
   * Reads in the formated txt file representing a multi layer image. The txt file contains a string
   * for the file type of the images, then each line for each layer contains the path for image
   * file, its id, and whether its invisible or not.
   *
   * @param filename File path of the txt file.
   * @return The mapping of ids to the images they represent for the layers.
   * @throws IllegalArgumentException If the file cannot be found, the name is null, or the file is
   *                                  malformed.
   */
  Map<String, ImageOfPixel> readImages(String filename)
      throws IllegalArgumentException;

  /**
   * Returns the list of invisible ids for the multi layer image.
   *
   * @return List of ids of invisible images.
   */
  List<String> readVisibility();

}
