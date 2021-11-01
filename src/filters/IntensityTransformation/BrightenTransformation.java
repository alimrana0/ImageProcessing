package filters.IntensityTransformation;

import filters.FilterClamp;
import model.Imaging.Color;
import model.Imaging.pixel.IPixel;
import model.Imaging.pixel.Pixel;
import model.Imaging.Posn;
import model.Imaging.pixel.IPixel;

public class BrightenTransformation extends AbstractIntensityTransformation {

  public BrightenTransformation (){}

  /**
   * Applies the correct transformation to the given pixel by changing its red, green, and blue
   * values. If the RGB value is out of range 0-255, it will be clamped to the value.
   * @param pixel
   * @return
   */

  @Override
  protected IPixel intensityTransform(IPixel pixel, int val) {

    int changedRed = (int) (pixel.getColor().getRed() + val);
    int changedGreen = (int) (pixel.getColor().getGreen() + val);
    int changedBlue = (int) (pixel.getColor().getBlue() + val);

    return FilterClamp.clamp(new Pixel(new Posn(pixel.getPosn().getX(), pixel.getPosn().getY()), new Color(changedRed,
            changedGreen, changedBlue)));
  }


}
