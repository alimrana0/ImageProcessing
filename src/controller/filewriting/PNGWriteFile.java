package controller.filewriting;

/**
 * * Class to represent a PNG file writer that allows for a png image to be written.
 */
public class PNGWriteFile extends AbstractImageWriteFile {

  /**
   * Constructs an abstracted image file writer with the file type of "png".
   */
  public PNGWriteFile() {
    super("png");
  }
}
