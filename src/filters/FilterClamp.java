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
   * @param pixel which has the RGB values
   * @return a new Pixel with corrected values if they were out of bounds before
   */
  public static IPixel clamp(IPixel pixel) {
    int red = pixel.getColor().getRed();
    int green = pixel.getColor().getGreen();
    int blue = pixel.getColor().getBlue();

    final int min = 0;
    final int max = 255;

    if (red < 0) {
      red = 0;
    } else if (red > max) {
      red = max;
    }
    if (green < min) {
      green = min;
    } else if (green > max) {
      green = max;
    }
    if (blue < min) {
      blue = min;
    } else if (blue > max) {
      blue = max;
    }
    Color c = new Color(red, green, blue);

    return new Pixel(pixel.getPosn(), c);
  }
}
