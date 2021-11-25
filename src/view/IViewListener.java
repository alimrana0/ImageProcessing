package view;

/**
 * Interface representing a listener for a GUI image processing view. It reacts by performing
 * functions on the view based on instructions.
 */
public interface IViewListener {

  /**
   * Returns the ID of the selected image in the image processor.
   *
   * @return ID of the selected layer in string format.
   */
  String getSelectedLayerID();

  /**
   * Handles the loading of the top layer via file formats of PPM, PNG, or JPEG under the given
   * file path.
   *
   * @param name      file's path.
   * @param type      file type to be saved.
   * @param layerName name of the layer.
   */
  void loadLayerHandler(String name, String type, String layerName);

  /**
   * Handles the loading of the layers using the given file path.
   *
   * @param name file's path.
   */
  void loadAllHandler(String name);

  /**
   * Handles the saving of the top layer via file formats of PPM, PNG, or JPEG under the given
   * file path.
   *
   * @param name file's path.
   * @param type file type to be saved.
   */
  void saveLayerHandler(String name, String type);

  /**
   * Handles the saving of all the layers to a folder via the given file format, alongside a text
   * file containing the image's information.
   * file path.
   *
   * @param name file's path.
   * @param type file type to be saved.
   */
  void saveAllHandler(String name, String type);

  /**
   * Handles the remove layer event on the selected image.
   */
  void removeLayer();

  /**
   * Handles the select layer event on the selected image by using the given ID as the selection.
   *
   * @param ID current layer's ID.
   */
  void selectLayer(String ID);

  /**
   * Handles the blurring of the selected image.
   */
  void blurHandler();

  /**
   * Handles the sharpening of the selected image.
   */
  void sharpenHandler();

  /**
   * Handles the grayscale of the selected image.
   */
  void grayscaleHandler();

  /**
   * Handles the sepia transformation of the selected image.
   */
  void sepiaHandler();

  /**
   * Handles the red component grayscale of the selected image.
   */
  void redComponentHandler();

  /**
   * Handles the green component grayscale of the selected image.
   */
  void greenComponentHandler();

  /**
   * Handles the blue component grayscale of the selected image.
   */
  void blueComponentHandler();

  /**
   * Handles the value change of the selected image.
   */
  void valueHandler();

  /**
   * Handles the intensity change of the selected image.
   */
  void intensityHandler();

  /**
   * Handles the brightening of the selected image.
   */
  void brightenHandler(int val);

  /**
   * Handles the darkening of the selected image.
   */
  void darkenHandler(int val);

  /**
   * Handles the vertical flip transformation of the selected image.
   */
  void handleFlipVertical();

  /**
   * Handles the horizontal flip transformation of the selected image.
   */
  void handleFlipHorizontal();

  /**
   * Handles the show event on the selected image.
   */
  void show();

  /**
   * Handles the hide event on the selected image.
   */
  void hide();

  /**
   * Handles the script-based run of the program that contains a list of commands that would be
   * used to run image processor's text-based version.
   *
   * @param name script file's file path.
   */
  void useScript(String name);
}
