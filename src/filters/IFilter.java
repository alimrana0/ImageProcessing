package filters;

import model.Imaging.Image;
import model.Imaging.pixel.IPixel;
import java.util.List;

/**
 * Interface representing a filter which a image processor may use.
 */
public interface IFilter {

  /**
   * Applies the filter to the given image.
   *
   * @param image a 2D array of all the pixels in the image
   * @return a 2D array of the pixels in the filtered image
   */
  List<List<IPixel>> filter(List<List<IPixel>> image);
}
