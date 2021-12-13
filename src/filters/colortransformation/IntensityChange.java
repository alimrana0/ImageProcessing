package filters.colortransformation;

import java.util.List;

import filters.FilterClamp;
import model.imaging.Color;
import model.imaging.Posn;
import model.imaging.pixel.IPixel;
import model.imaging.pixel.Pixel;

/**
 * Class to represent an intensity change on a pixel.
 */
public class IntensityChange extends AbstractColorTransformation {

  /**
   * Empty constructor for IntensityChange.
   */
  public IntensityChange() {
    //Doesn't need any intializations.
  }

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

  @Override
  protected IPixel colorTransform(IPixel pixel, List<Posn> maskedPixelPosns) {
    if (maskedPixelPosns.contains(pixel.getPosn())) {
      return colorTransform(pixel);
    }
    return pixel;
  }
}
