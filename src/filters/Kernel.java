package filters;

import java.util.List;

/**
 * Class representing a Filters.Kernel which is used to calculate the new color of a pixel for a
 * given image.
 */
public class Kernel implements IKernel {

  private int width;
  private int height;
  private double[][] values;

  /**
   * Constructor for the Kerenel class that implements the Filters.IKernel. Initializes the width,
   * height, and values of the Filters.Kernel.
   *
   * @param width  int representing how wide the kernel 2D array is
   * @param height int representing how tall the kerenl 2D array is
   * @param values List of List of Doubles representing the values in the kernel
   */
  public Kernel(int width, int height, double[][] values) {
    if (width == 0 || height == 0 || values == null || values.length == 0) {
      throw new IllegalArgumentException("Invalid Filters.Kernel");
    }

    this.width = width;
    this.height = height;
    this.values = values;
  }

  /**
   * Alternative constructor that is empty.
   */
  public Kernel() {
    // default empty constructor
  }

  @Override
  public final int getHeight() {
    return height;
  }

  @Override
  public final int getWidth() {
    return width;
  }

  @Override
  public final int getXCenter() {
    int xCenter = (this.width - 1) / 2;
    return xCenter;
  }

  @Override
  public final int getYCenter() {
    int yCenter = (this.height - 1) / 2;
    return yCenter;
  }

  @Override
  public final double getValueAt(int x, int y) {
    try {
      return this.values[y][x];
    } catch (IndexOutOfBoundsException e) {
      throw new IllegalArgumentException("Index is out of bounds.");
    }
  }


}
