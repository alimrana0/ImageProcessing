package filters.ColorTransformation;

import model.Imaging.ImageOfPixel;

public interface IColorTransform {

  /**
   * Applies a transformation on the color of a given image.
   * @param image image being transformed.
   * @return The transformed image.
   * @throws IllegalArgumentException If the image is null.
   */
  ImageOfPixel applyColorTransformation(ImageOfPixel image);

}

