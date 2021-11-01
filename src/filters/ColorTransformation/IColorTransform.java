package filters.ColorTransformation;

import model.Imaging.ImageOfPixel;

public interface IColorTransform {

  /**
   * Applies some transformation on the color of the given image.
   * @param image Image to apply the transformation to.
   * @return The transformed image.
   * @throws IllegalArgumentException If the supplied image is null.
   */
  ImageOfPixel applyColorTransformation(ImageOfPixel image);

}

