package model.Imaging.pixel;

import java.util.Objects;

import model.Imaging.Color;
import model.Imaging.IColor;
import model.Imaging.Posn;

/**
 * Class representing a standard pixel that uses a combination or red, blue, and green
 * color values to represent it.
 */
public class Pixel implements IPixel {

  private final Posn posn;
  private final IColor color;

  public Pixel(Posn position, IColor color) {

    if (position == null || color == null) {
      throw new IllegalArgumentException("Argument cannot be null.");
    }

    this.posn = position;

    this.color = color;
  }

  @Override
  public IColor getColor() {
    return color;
  }

  @Override
  public Posn getPosn() {
    return new Posn(posn.getX(), posn.getY());
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Pixel)) {
      return false;
    }

    Pixel other = (Pixel) o;
    return this.color.equals(other.color) && this.posn.equals(other.posn);
  }

  @Override
  public int hashCode() {
    return Objects.hash(color, posn);
  }

}
