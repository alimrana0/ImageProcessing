package filters.ColorTransformation.Greyscale;

import filters.ColorTransformation.AbstractColorTransformation;
import filters.FilterClamp;
import filters.IntensityTransformation.AbstractIntensityTransformation;
import model.Imaging.Color;
import model.Imaging.Posn;
import model.Imaging.pixel.IPixel;
import model.Imaging.pixel.Pixel;

public class BlueGreyscale extends AbstractColorTransformation {
  public BlueGreyscale(){}

  /**
   * Applies the given transformation to the given pixel by updating its rgb values. Any out of
   * range rgb value is clamped to the minimum value of 0 or the maximum value of 255.
   *
   * @param pixel Pixel to transform.
   * @return The transformed pixel.
   */
  protected IPixel colorTransform(IPixel pixel) {

    int blue = pixel.getColor().getBlue();

    int changedRed = blue;
    int changedGreen = blue;

    return FilterClamp.clamp(new Pixel(new Posn(pixel.getPosn().getX(), pixel.getPosn().getY()), new Color(changedRed,
            changedGreen, blue)));

  }

}
