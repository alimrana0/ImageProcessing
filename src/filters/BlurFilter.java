package filters;

/**
 * Class to represent a blur filter on an image.
 */
public class BlurFilter extends AbstractImageProcessing {

  /**
   * Constructor for a blur transformation with a matrix of values that will allow for a pixel to be
   * blurred.
   */
  public BlurFilter() {

    super(new Kernel(3, 3, new double[][]{{0.0625, 0.125, 0.0625}, {0.125, 0.25, 0.125},
        {0.0625, 0.125, 0.0625}}));
  }

}
