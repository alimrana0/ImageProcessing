package model.Imaging;

import java.util.ArrayList;
import java.util.List;

import model.Imaging.pixel.IPixel;
import model.Imaging.pixel.Pixel;

public class Image implements ImageOfPixel {
  private final List<ArrayList<IPixel>> pixels;

  public Image(List<ArrayList<IPixel>> pixels) {
    if (pixels == null) {
      throw new IllegalArgumentException("Pixels cannot be null!");
    }
    this.pixels = this.replicate(pixels);
  }


  private List<ArrayList<IPixel>> replicate(List<ArrayList<IPixel>> pixels) {
    List<ArrayList<IPixel>> tempPixels = new ArrayList<>();
    for (ArrayList<IPixel> row : pixels) {
      ArrayList<IPixel> rowCopy = new ArrayList<>();
      for (IPixel pixel : row) {
        rowCopy.add(new Pixel(pixel.getPosn(), pixel.getColor()));
      }
      tempPixels.add(rowCopy);
    }
    return tempPixels;
  }

  @Override
  public List<List<IPixel>> getPixels() {
    return new ArrayList<>(pixels);
  }
}


