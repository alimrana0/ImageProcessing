package filters.ColorTransformation;

import filters.FilterClamp;
import model.Imaging.Color;
import model.Imaging.Posn;
import model.Imaging.pixel.IPixel;
import model.Imaging.pixel.Pixel;

public class IntensityChange extends AbstractColorTransformation{

  public IntensityChange() {}

  @Override
  protected IPixel colorTransform(IPixel pixel) {

    int avg = (pixel.getColor().getRed()
            + pixel.getColor().getGreen()
            + pixel.getColor().getGreen()) / 3;

    return FilterClamp.clamp(new Pixel(new Posn(pixel.getPosn().getX(), pixel.getPosn().getY()), new Color(avg,
            avg, avg)));
  }
}
