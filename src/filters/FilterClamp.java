package filters;

import model.Imaging.Color;
import model.Imaging.pixel.IPixel;
import model.Imaging.pixel.Pixel;
import java.util.ArrayList;
import java.util.List;

/**
 * Class representing the filter for clamping which sets the color values of each pixel to the min
 * and max as specified by the class.
 */
public class FilterClamp {


  /**
   * Takes in a given pixel and makes sure all the color values are within the given bounds
   * otherwise they are set to the respective values.
   *
   * @param val which has the RGB value
   * @return a new Pixel with corrected values if they were out of bounds before
   */
  public static int clamp(int val) {
    if (val > 255) {
      return 255;
    } else if (val < 0) {
      return 0;
    }
    return val;
  }
}
