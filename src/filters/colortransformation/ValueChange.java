package filters.colortransformation;

import java.util.List;

import model.imaging.Color;
import model.imaging.Posn;
import model.imaging.pixel.IPixel;
import model.imaging.pixel.Pixel;

/**
 * Class to represent a value change on a pixel.
 */
public class ValueChange extends AbstractColorTransformation {

  /**
   * Empty constructor for a ValueChange filter.
   */
  public ValueChange() {
    //Doesn't need any initialization.
  }

  /**
   * Applies the color transformation to the given pixel by updating its rgb values. Any out of
   * range rgb value is clamped to the minimum value of 0 or the maximum value of 255.
   *
   * @param pixel Pixel being transformed.
   * @return The transformed pixel.
   */
  protected IPixel colorTransform(IPixel pixel) {

    int largest = getMaxColor(pixel.getColor().getRed(),
            pixel.getColor().getGreen(),
            pixel.getColor().getBlue());

    return new Pixel(new Posn(pixel.getPosn().getX(), pixel.getPosn().getY()), new Color(largest,
            largest, largest));

  }

  @Override
  protected IPixel colorTransform(IPixel pixel, List<Posn> maskedPixelPosns) {
    if (maskedPixelPosns.contains(pixel.getPosn())) {
      return colorTransform(pixel);
    }
    return pixel;
  }

  /**
   * Determines the max rgb value in the pixel.
   * @return The max value of the pixel's RGB.
   */
  private int getMaxColor(int r, int g, int b) {
    return Math.max(r, Math.max(g, b));
  }
}
