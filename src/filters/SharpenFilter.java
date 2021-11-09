package filters;

public class SharpenFilter extends AbstractImageFilter {

  /**
   * Creates an instance of a sharpen filter using the associated matrix for sharpening.
   */
  public SharpenFilter() {


    super(new Kernel(5,5, new double[][]{{-0.125, -0.125, -0.125, -0.125, -0.125},
            {-0.125, 0.25, 0.25, 0.25, -0.125},
            {-0.125, 0.25, 1, 0.25, -0.12},
            {-0.125, 0.25, 0.25, 0.25, -0.125},
            {-0.125, -0.125, -0.125, -0.125, -0.125}}));
  }
}
