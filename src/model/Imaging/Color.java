package model.Imaging;

import java.util.Objects;

public class Color implements IColor {

  private final int red;
  private final int green;
  private final int blue;


  public Color(int r, int g, int b) throws IllegalArgumentException{
    if (r < 0 || r > 255 || g < 0 || g > 255 || b < 0 || b > 255) {
      throw new IllegalArgumentException("Values must be between 0-255");
    }
    red = r;
    green = g;
    blue = b;
  }

  @Override
  public int getRed() {
    return red;
  }

  @Override
  public int getGreen() {
    return green;
  }

  @Override
  public int getBlue() {
    return blue;
  }

  public boolean equals(Object o) {
    try {
      Color color = (Color) o;
      return (color.getRed() == this.red) && (color.getGreen() == this.green)
              && (color.getBlue() == this.blue);
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("This object is not an RGB Pixel");
    }
  }

  @Override
  public int hashCode() {
    return Objects.hash(red, green, blue);
  }

}
