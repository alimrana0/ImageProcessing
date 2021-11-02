package model.Imaging;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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

  public void saveImageAsPPM(String filename) throws IOException {
    FileOutputStream outputStream = new FileOutputStream(filename);
    StringBuilder output = new StringBuilder();
    output.append("P3");
    output.append("\n" + this.pixels.get(1).size() + " " + this.pixels.size());
    for (List<IPixel> pixelList: this.pixels) {
      for (IPixel pixel: pixelList) {
        output.append("\n");
        output.append(pixel.getColor().getRed());
        output.append("\n");
        output.append(pixel.getColor().getGreen());
        output.append("\n");
        output.append(pixel.getColor().getBlue());
      }
    }
    outputStream.write(output.toString().getBytes());
  }
}


