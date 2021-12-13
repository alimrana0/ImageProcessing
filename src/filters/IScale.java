package filters;

import model.imaging.ImageOfPixel;

/**
 * Interface to represent a scaling transformation on an image.
 */
public interface IScale {

  /**
   * Changes the dimensions of a given image using the given width and height dimensions.
   *
   * @param image    the image being scaled.
   * @param adjustedWidth  the adjusted width of the desired image.
   * @param adjustedHeight the adjusted height of the desired image.
   * @return the scaled image.
   */
  ImageOfPixel scale(ImageOfPixel image, int adjustedWidth, int adjustedHeight);
}
