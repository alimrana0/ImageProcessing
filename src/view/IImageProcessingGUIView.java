package view;

import java.awt.image.BufferedImage;
import java.util.List;

import model.graph.Line;

/**
 * Interface representing an image processor view that has a graphical user interface that the
 * use may use to perform certain manipulations on images.
 */
public interface IImageProcessingGUIView extends IImageProcessingView {

  /**
   * Runs a GUI for the user to see on their client.
   */
  void run();

  /**
   * Sets the currently visible image to the given buffered image.
   *
   * @param image the buffered image that will be viewed in the GUI.
   */
  void setImage(BufferedImage image);

  /**
   * Adds the given name of an image to the list of images that the GUI is holding on to. This list
   * consists of all previously-loaded images by the user.
   *
   * @param name Name of the image to add.
   */
  void addImage(String name);

  /**
   * Given a list of lines this method updates the histogram with the given lines.
   *
   * @param lines A list of lines that are drawn to make a histogram.
   */
  void updateGraph(List<Line> lines);


  /**
   * Sets the image in the preview pane.
   *
   * @param image The image to be displayed in the preview plane.
   */
  public void setPreviewImage (BufferedImage image);
}
