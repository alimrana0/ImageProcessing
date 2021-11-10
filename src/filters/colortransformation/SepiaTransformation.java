package filters.colortransformation;

import filters.Kernel;

/**
 * Represents a sepia transformation that can be performed on an image that is stored as pixels.
 */
public class SepiaTransformation extends AbstractColorTransformationProcessor {

  /**
   * Constructs a new SepiaTransformation, with the transformation matrix being set to the correct
   * values such that the pixel can be transformed into a sepia color.
   */
  public SepiaTransformation() {

    super(new Kernel(3, 3, new double[][]{{0.393, 0.769, 0.189},
        {0.349, 0.686, 0.168},
        {0.272, 0.534, 0.131}}));
  }


}
