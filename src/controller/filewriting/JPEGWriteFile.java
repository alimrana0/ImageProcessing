package controller.filewriting;

/**
 * Class to represent a JPEG file writer that allows for a jpeg image to be written.
 */
public class JPEGWriteFile extends AbstractImageWriteFile {

  /**
   * Constructs an abstracted image file writer with the file type of "jpeg".
   */
  public JPEGWriteFile() {
    super("jpeg");
  }
}
