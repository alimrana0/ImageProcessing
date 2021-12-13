package filters.intensitytransformation;

import java.util.List;

import model.imaging.ImageOfPixel;
import model.imaging.Posn;

/**
 * An interface to represent an intensity transformation on an image.
 */
public interface IIntensityTransform {

  /**
   * Applies some transformation on the intensity of a given image.
   * @param image image being transformed.
   * @return The transformed image.
   * @throws IllegalArgumentException If the image is null.
   */
  ImageOfPixel applyTransformation(ImageOfPixel image, int val) throws IllegalArgumentException;
}

