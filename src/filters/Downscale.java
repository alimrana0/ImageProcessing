package filters;

import model.imaging.ImageOfPixel;

/**
 * Interface to represent a downscale transformation on an image.
 */
public interface Downscale {

  /**
   * The method that takes an image and change its size to the expected.
   *
   * @param image    the data set that contains the pixels of a image.
   * @param newWidth  the new width of the image.
   * @param newHeight the new height of the image.
   * @return a new image with the given size.
   */
  ImageOfPixel apply(ImageOfPixel image, int newWidth, int newHeight);
}
