package model.Imaging.pixel;

import model.Imaging.Color;
import model.Imaging.Posn;

public interface IPixel{

  Color getColor();

  /**
   * Gets the position of the pixel.
   * @return
   */
  Posn getPosn();
}
