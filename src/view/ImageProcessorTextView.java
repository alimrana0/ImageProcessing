package view;

import java.io.IOException;

/**
 * Class to represent a textual view for the image program to display messages to the user.
 */
public class ImageProcessorTextView implements IImageProcessingView {

  private Appendable ap;

  /**
   * Creates a new view.
   *
   * @param ap Appendable for the view.
   * @throws IllegalArgumentException IF appendable is null.
   */
  public ImageProcessorTextView(Appendable ap)
      throws IllegalArgumentException {
    if (ap == null) {
      throw new IllegalArgumentException("Null parameter.");
    }
    this.ap = ap;

  }

  @Override
  public void renderMessage(String message) throws IOException {
    this.ap.append(message);
  }
}
