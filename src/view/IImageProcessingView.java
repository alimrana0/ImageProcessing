package view;

import java.io.IOException;

public interface IImageProcessingView {


  String toString();

  /**
   * Render a specific message to the provided data destination.
   *
   * @param message the message to be transmitted
   * @throws IOException if transmission of the board to the provided data destination fails
   */
  void renderMessage(String message) throws IOException;

  void showOptions() throws IOException;
}
