package model.graph;

import java.awt.Color;
import java.util.Objects;

/**
 * This class represents a line that is used by the GUI to draw a histogram.
 * This code was based off of the turtle solutions code from class.
 */
public final class Line {
  public final Position2D start;
  public final Position2D end;
  public final Color color;

  /**
   * Constructs a line with a beginning, end, as well as a color.
   *
   * @param start The location the line starts at.
   * @param end The location the line ends at.
   * @param color The color of the line.
   */
  public Line(Position2D start, Position2D end, Color color) {
    this.start = start;
    this.end = end;
    this.color = color;
  }

  /**
   * Returns the start of the line.
   *
   * @return The point the line starts at.
   */
  public Position2D getStart() {
    return this.start;
  }

  /**
   * Returns the end of the line.
   *
   * @return The point the line ends at.
   */
  public Position2D getEnd() {
    return this.end;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Line)) {
      return false;
    }

    Line line = (Line) o;

    return (this.start.equals(line.start) && this.end.equals(line.end))
            || (this.end.equals(line.start) && this.start.equals(line.end));
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.start, this.end);
  }
}
