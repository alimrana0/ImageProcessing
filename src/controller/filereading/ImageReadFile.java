package controller.filereading;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import model.imaging.Color;
import model.imaging.Image;
import model.imaging.ImageOfPixel;
import model.imaging.Posn;
import model.imaging.pixel.IPixel;
import model.imaging.pixel.Pixel;

/**
 * Class that represents an object that can read and name files of different types in the IO
 * library for Image.
 */
public class ImageReadFile implements IReadFile {

  @Override
  public ImageOfPixel readImage(String name) throws IllegalArgumentException {
    if (name == null) {
      throw new IllegalArgumentException("Name can't be null.");
    }
    try {
      File file = new File(name);
      BufferedImage image = ImageIO.read(file);
      if (image == null) {
        throw new IllegalArgumentException("File was not able to be read.");
      }
      int height = image.getHeight();
      int width = image.getWidth();

      List<ArrayList<IPixel>> pixels = new ArrayList<>();

      for (int i = 0; i < height; i++) {
        ArrayList<IPixel> r = new ArrayList<>();
        for (int j = 0; j < width; j++) {
          java.awt.Color rgb = new java.awt.Color(image.getRGB(j, i));
          r.add(new Pixel(new Posn(j, i),
                  new Color(rgb.getRed(), rgb.getGreen(), rgb.getBlue())));
        }
        pixels.add(r);
      }

      return new Image(pixels);


    } catch (IOException e) {
      throw new IllegalArgumentException("File doesn't exist.");
    }

  }

}
