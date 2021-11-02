package filters.FlippingTransformation;

import model.Imaging.ImageOfPixel;

/**
 * Interface representing an image transformation of flipping.
 */
public interface IFlipTransform {

  /**
   * Applies some flip transformation on a given image.
   * @param image image being flipped.
   * @return the flipped image.
   * @throws IllegalArgumentException if the image is null/
   */
  ImageOfPixel flipTransform(ImageOfPixel image) throws IllegalArgumentException;

}