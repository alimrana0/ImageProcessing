package model.Imaging.pixel;

import model.Imaging.Color;
import model.Imaging.IColor;
import model.Imaging.Posn;

public interface IPixel{

  IColor getColor();

  /**
   * Gets the position of the pixel.
   * @return
   */
  Posn getPosn();
}
