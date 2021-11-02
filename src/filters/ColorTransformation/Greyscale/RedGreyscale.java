package filters.ColorTransformation.Greyscale;

import filters.ColorTransformation.AbstractColorTransformation;
import filters.FilterClamp;
import model.Imaging.Color;
import model.Imaging.Posn;
import model.Imaging.pixel.IPixel;
import model.Imaging.pixel.Pixel;

public class RedGreyscale extends AbstractColorTransformation {
  /**
   * Applies the given transformation to the given pixel by updating its rgb values. Any out of
   * range rgb value is clamped to the minimum value of 0 or the maximum value of 255.
   *
   * @param pixel Pixel to transform.
   * @return The transformed pixel.
   */
  protected IPixel colorTransform(IPixel pixel) {


    int red = pixel.getColor().getRed();

    int changedGreen = red;
    int changedBlue = red;

    return new Pixel(new Posn(pixel.getPosn().getX(), pixel.getPosn().getY()), new Color(red,
            changedGreen, changedBlue));

  }
}
