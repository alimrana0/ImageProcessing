package view;

import java.io.IOException;

import model.ImageProcessingModel;

/**
 * Class implementing IImageProcessingView which allows the user to see messages and
 * information about Image Processor's view at the given Appendable.
 */

public class ImageProcessingView implements IImageProcessingView {

  private ImageProcessingModel model;
  private Appendable out;

  /**
   * Constructor that only takes in a model.
   *
   * @param model represents an IImageProcessingView that is used by the Controller
   */
  public ImageProcessingView(ImageProcessingModel model) {
    this(model, System.out);
    this.model = model; //THIS IS NOT NEEDED BUT REQUIRED TO AVOID JAVA  STYLE PENALTY

  }

  /**
   * Constructor that takes in both a model and an output source.
   *
   * @param model an IImageProcessingView model
   * @param out   Output source
   */
  public ImageProcessingView(ImageProcessingModel model, Appendable out) {
    if (model == null || out == null) {
      throw new IllegalArgumentException("Arguments cannot be null");
    }

    this.model = model;
    this.out = out;
  }

  /**
   * Render a specific message to the provided data destination.
   *
   * @param message the message being transmitted
   * @throws IOException if transmission of the message to the data destination fails
   */
  public void renderMessage(String message) throws IOException {
    try {
      out.append(message);
    } catch (IOException io) {
      throw new IOException("Could not render Message");
    }
  }

  /**
   * Shows the options that a client has for use.
   * @throws IOException if the option menu message fails to transmit to the data destination.
   */
  public void showOptions() throws IOException {
    String options = "Commands:"
            + "\nload filepath name"
            + "\nsave saveLocation name"
            + "\nget-component component "
            + "\nhorizontal-flip"
            + "\nvertical-flip"
            + "\nbrighten"
            + "\ndarken"
            + "\nQ or q to quit";
    this.renderMessage(options);
  }
}