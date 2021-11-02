package model.Imaging;

import java.io.IOException;
import java.util.List;

import model.Imaging.pixel.IPixel;
import model.Imaging.pixel.Pixel;

public interface ImageOfPixel {

  List<List<IPixel>> getPixels();

  void saveImageAsPPM(String filename) throws IOException;
}
