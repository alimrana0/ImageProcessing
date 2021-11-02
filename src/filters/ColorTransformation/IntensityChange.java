package filters.ColorTransformation;

import filters.FilterClamp;
import model.Imaging.Color;
import model.Imaging.Posn;
import model.Imaging.pixel.IPixel;
import model.Imaging.pixel.Pixel;

public class IntensityChange extends AbstractColorTransformation{

  /**
   * Empty constructor for IntensityChange.
   */
  public IntensityChange() {}

  /**
   * Applies the color transformation to the given pixel by updating its rgb values. Any out of
   * range rgb value is clamped to the minimum value of 0 or the maximum value of 255.
   *
   * @param pixel Pixel being transformed.
   * @return The transformed pixel.
   */
  @Override
  protected IPixel colorTransform(IPixel pixel) {

    int avg = FilterClamp.clamp((pixel.getColor().getRed()
            + pixel.getColor().getGreen()
            + pixel.getColor().getGreen()) / 3);

    return new Pixel(new Posn(pixel.getPosn().getX(), pixel.getPosn().getY()), new Color(avg,
            avg, avg));
  }
}
