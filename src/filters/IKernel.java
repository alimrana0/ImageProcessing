package filters;

/**
 * Interface representing a kernel which is used to calculate Image Processing filters.
 */
public interface IKernel {

  /**
   * Method that returns the height (number of List in the List) of the 2D Array.
   *
   * @return int representing the height of the array
   */
  int getHeight();

  /**
   * Method that returns the width (number of Integers in the List) of the 2D Array.
   *
   * @return int representing the width of the array
   */
  int getWidth();

  /**
   * Gets the distance from the edge to the center of the kernel horizontally.
   *
   * @return int representing the center of the Matrix.
   */
  int getXCenter();

  /**
   * Gets the distance from the edge to the center of the kernel vertically.
   *
   * @return int representing the center of the Matrix.
   */
  int getYCenter();

  /**
   * Returns the value at a specific x or y of a given 2D Array.
   *
   * @param x int representing the width of the array starting at 0
   * @param y int representing the height of the array starting at 0
   * @return int representing the value at the specific coordinate
   */
  double getValueAt(int x, int y);
}
