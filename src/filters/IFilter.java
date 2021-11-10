package filters;

import model.imaging.ImageOfPixel;

/**
 * Represents some type of transformation that can be done on a PixelImage. Includes things such as
 * grayscale, brightening, darkening, etc.
 */
public interface IFilter {

  /**
   * Performs some transformation on the given image.
   *
   * @param image The image to be manipulated.
   * @return The transformed image.
   * @throws IllegalArgumentException If the provided image is null.
   */
  ImageOfPixel transform(ImageOfPixel image) throws IllegalArgumentException;
}
