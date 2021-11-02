package filters.ColorTransformation.Greyscale;

import filters.ColorTransformation.AbstractColorTransformation;
import filters.FilterClamp;
import model.Imaging.Color;
import model.Imaging.Posn;
import model.Imaging.pixel.IPixel;
import model.Imaging.pixel.Pixel;

public class GreenGreyscale extends AbstractColorTransformation {

  /**
   * Applies the given transformation to the given pixel by updating its rgb values. Any out of
   * range rgb value is clamped to the minimum value of 0 or the maximum value of 255.
   *
   * @param pixel Pixel to transform.
   * @return The transformed pixel.
   */
  protected IPixel colorTransform(IPixel pixel) {


    int green = pixel.getColor().getGreen();

    int changedRed = green;
    int changedBlue = green;

    return new Pixel(new Posn(pixel.getPosn().getX(), pixel.getPosn().getY()), new Color(changedRed,
            green, changedBlue));

  }
}
