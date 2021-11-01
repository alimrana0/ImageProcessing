package model.Imaging;

import java.util.Objects;

public class Posn {

  private final int x;
  private final int y;

  /**
   * Create a 2D point given its x and y coordinates.
   *
   * @param x x-coordinate of the 2D point
   * @param y y-coordinate of the 2D point
   */
  public Posn(int x, int y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Return the x-coordinate of this point.
   *
   * @return the x-coordinate as an integer
   */
  public int getX() {
    return this.x;
  }

  /**
   * Return the y-coordinate of this point.
   *
   * @return the y-coordinate as an integer.
   */
  public int getY() {
    return this.y;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Posn)) {
      return false;
    }

    Posn other = (Posn) o;
    return this.x == other.x && this.y == other.y;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.x, this.y);

  }
}
