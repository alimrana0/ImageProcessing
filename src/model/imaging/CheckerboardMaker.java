package model.imaging;

import java.util.ArrayList;
import java.util.List;

import model.IImageMaker;
import model.imaging.pixel.IPixel;
import model.imaging.pixel.Pixel;

public class CheckerboardMaker implements IImageMaker {

  private final int r;
  private final int c;
  private final List<IColor> colors;

  /**
   * Constructs a checkerboard maker.
   *
   * @param r    the amount of r in the checkerboard
   * @param c the amount of columns in the checkerboard
   * @param colors  the checkerboard's colors.
   * @throws IllegalArgumentException If invalid colors are provided or if the checkerboard
   *                                  dimensions are invalid
   */
  public CheckerboardMaker(int r, int c, List<IColor> colors) throws IllegalArgumentException {
    if (r < 0 || c < 0) {
      throw new IllegalArgumentException("Can't have a negative dimensioned checkerboard");
    }
    if (colors.size() != 2) {
      throw new IllegalArgumentException("The checkerboard requires two colors only");
    }
    if (colors == null) {
      throw new IllegalArgumentException("Colors can't be null");
    }

    this.r = r;
    this.c = c;
    this.colors = colors;
  }

  @Override
  public ImageOfPixel generateImage() {
    List<ArrayList<IPixel>> pixels = new ArrayList<>();

    for (int i = 0; i < this.r; i++) {
      ArrayList<IPixel> row = new ArrayList<>();
      for (int j = 0; j < this.c; j++) {
        row.add(new Pixel(new Posn(j, i), alternateColors(i, j)));
      }
      pixels.add(row);
    }

    return new Image(pixels);
  }

  /**
   * Alternates the color according to the given row and column of the checkerboard
   *
   * @param r  the row of the current position
   * @param c the column of the current position
   * @return the desired color of the current square.
   */
  private IColor alternateColors(int r, int c) {
    int x = c % colors.size() + r % colors.size();
    if (x > 1) {
      x = 0;
    }

    return colors.get(x);
  }
}

