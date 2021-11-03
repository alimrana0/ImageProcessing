import java.io.IOException;

import model.ImageProcessingModel;
import view.ImageProcessingView;

/**
 * Class representing a mock image processor's view.
 */
public class MockImageProcessingView extends ImageProcessingView {

  public MockImageProcessingView(ImageProcessingModel model) {
    super(model);
  }

  /**
   * Renders a message that simulates the throwing of an IOException.
   *
   * @param message the message being transmitted
   * @throws IOException if the message fails (always)
   */
  public void renderMessage(String message) throws IOException {

    throw new IOException("Mock render Message failure");

  }

  /**
   * Shows the options that a client has for use.
   *
   * @throws IOException if the option menu message fails to transmit to the data destination.
   */
  @Override
  public void showOptions() throws IOException {
    throw new IOException("Mock options failure");
  }
}
