package view;

import java.io.IOException;

public interface IImageProcessingView {


  String toString();

  /**
   * Render a specific message to the provided data destination.
   *
   * @param message the message being transmitted
   * @throws IOException if transmission of the message to the data destination fails
   */
  void renderMessage(String message) throws IOException;

  /**
   * Shows the options that a client has for use.
   * @throws IOException if the option menu message fails to transmit to the data destination.
   */
  void showOptions() throws IOException;
}