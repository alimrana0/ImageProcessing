package view;

import controller.IFeatures;
import java.awt.event.ActionEvent;

public interface IGUI {

  //only java swing
  /*
  add feautrues

  all the buttons

   */

  /**
   * Displays the image currently being worked on for the user.
   */
  public void displayImage();

  /**
   * Displays the histogram of the image that is currently being worked on for the user.
   */
  public void displayHistogram();

  /**
   * Displays a message to the user running the image processing program.
   *
   * @param message The string to show the user.
   */
  public void displayPopUp(String message);


  void addFeatures(IFeatures features);

  /**
   * Returns the filepath of the file that the user selected. If the user selects the wrong file
   * type, in this case something that is not a basic image type, this method returns null.
   *
   * @return The string of the filepath for the file selected.
   */
  public String getFile();

}
