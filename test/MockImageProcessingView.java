import java.io.IOException;
import model.ImageProcessingModel;
import view.ImageProcessingView;

public class MockImageProcessingView extends ImageProcessingView {

  public MockImageProcessingView(ImageProcessingModel model) {
    super(model);
  }

  public void renderMessage(String message) throws IOException {

      throw new IOException("Mock render Message failure");

  }

  /**
   * Shows the options that a client has for use.
   * @throws IOException if the option menu message fails to transmit to the data destination.
   */
  @Override
  public void showOptions() throws IOException {
    throw new IOException("Mock options failure");
  }
}
