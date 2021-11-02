package filters.ColorTransformation;

import filters.FilterClamp;
import model.Imaging.Color;
import model.Imaging.Posn;
import model.Imaging.pixel.IPixel;
import model.Imaging.pixel.Pixel;

public class ValueChange extends AbstractColorTransformation{

  /**
   * Empty constructor for a ValueChange filter.
   */
  public ValueChange() {}

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

  /**
   * Determines the max rgb value in the pixel.
   * @return The max value of the pixel's RGB.
   */
  private int getMaxColor(int r, int g, int b) {
    return Math.max(r, Math.max(g, b));
  }
}
