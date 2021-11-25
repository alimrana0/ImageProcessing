package controller.filewriting;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import model.imaging.ImageOfPixel;

/**
 * Interface containing methods to write files consisting of multilayered images
 * by using the image data to save using a given file type and image.
 */
public interface IWriteMultiLayered {

  /**
   * Writes a formatted text file that contains information on a multilayered image (id, type,
   * path, visibility).
   *
   * @param name   text file name.
   * @param type       file write type.
   * @param layers     Map of ids corresponding to the images in the multilayer.
   * @param invisible list of invisible image IDs.
   * @throws IllegalArgumentException If the file is null or nonexistent.
   * @throws IOException              If the writing malfunctions.
   */
  void writeFile(String name, String type, Map<String, ImageOfPixel> layers,
      List<String> invisible)
      throws IllegalArgumentException, IOException;

}
