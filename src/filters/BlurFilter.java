package filters;

/**
 * Class that represents the blurring of an image.
 */
public class BlurFilter extends AbstractImageFilter {

  /**
   * Creates an instance of the blur filter using a matrix that performs a linear transformation on the
   * RGB values of the pixels in order to blur the image.
   */
  public BlurFilter() {

    super(new Kernel(3,3, new double[][]{{0.0625, 0.125, 0.0625}, {0.125, 0.25, 0.125},
            {0.0625, 0.125, 0.0625}}));
  }

}
