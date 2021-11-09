package filters.colortransformation;

import filters.IKernel;
import filters.Kernel;


/**
 * Represents a Luma Transformation, which sets each pixel to the weighted sum 0.2126r + 0.7152b +
 * 0.0722g.
 */
public class GreyscaleTransformation extends AbstractColorTransformationProcessor {
  /**
   * Constructs a new GreyscaleTransformation, with the transformation matrix being set to the correct
   * values such that the pixel can be transformed.
   */
  public GreyscaleTransformation() {
    super(new Kernel(3,3, new double[][]{{0.2126, 0.7152, 0.0722},
            {0.2126, 0.7152, 0.0722},
            {0.2126, 0.7152, 0.0722}}));
  }
}