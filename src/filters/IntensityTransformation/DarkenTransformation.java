package filters.IntensityTransformation;

import model.Imaging.Color;
import filters.FilterClamp;
import model.Imaging.Posn;
import model.Imaging.pixel.IPixel;
import model.Imaging.pixel.Pixel;

/**
 * Class to represent a darkening filter on a pixel.
 */
public class DarkenTransformation extends AbstractIntensityTransformation {

  /**
   * Empty contrsuctor for a DarkenTransformation filter.
   */
  public DarkenTransformation(){}

  /**
   * Applies a darkening on the given pixel.
   * If the RGB value is out of range 0-255, it will be clamped to the value.
   * @param pixel pixel being transformed
   * @param val the value that the pixel will be darkened by
   * @return the transformed pixel.
   */
  @Override
  protected IPixel intensityTransform(IPixel pixel, int val) {

    int changedRed = FilterClamp.clamp((int) (pixel.getColor().getRed() - val));
    int changedGreen = FilterClamp.clamp((int) (pixel.getColor().getGreen() - val));
    int changedBlue = FilterClamp.clamp((int) (pixel.getColor().getBlue() - val));

    return new Pixel(new Posn(pixel.getPosn().getX(), pixel.getPosn().getY()), new Color(changedRed,
            changedGreen, changedBlue));
  }

}
