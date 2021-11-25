package controller.filewriting;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;

import model.imaging.ImageOfPixel;

/**
 * Abstract class to represent a file writer that maintains the file's file type to write it to
 * using those given in the IO Image library.
 */
public abstract class AbstractImageWriteFile implements IImageWriteFile {

  protected final String type;

  /**
   * Constructor for a file writer given the file type.
   *
   * @param type file type
   * @throws IllegalArgumentException If file type is outside the IOImage library or null.
   */
  protected AbstractImageWriteFile(String type) throws IllegalArgumentException {
    if (type == null) {
      throw new IllegalArgumentException("Null file type.");
    }
    if (!Arrays.asList(ImageIO.getWriterFileSuffixes()).contains(type)) {
      throw new IllegalArgumentException("File extension isn't a valid image file type.");
    }
    this.type = type;
  }

  /**
   * Writes the file for the image under the given file type and the image file.
   *
   * @param name  image file path.
   * @param image image to be written as a file.
   * @throws IOException              If the file write fails.
   * @throws IllegalArgumentException If given null arguments.
   */
  public void writeFile(String name, ImageOfPixel image)
          throws IOException, IllegalArgumentException {
    if (name == null || image == null) {
      throw new IllegalArgumentException("Argument can't be null.");
    }
    File file = new File(name);
    File parent = file.getParentFile();
    if (parent != null) {
      parent.mkdirs();
    }
    FileOutputStream output = new FileOutputStream(file);

    int height = image.getPixels().size();
    int width = image.getPixels().get(0).size();

    BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int red = image.getPixels().get(i).get(j).getColor().getRed();
        int green = image.getPixels().get(i).get(j).getColor().getGreen();
        int blue = image.getPixels().get(i).get(j).getColor().getBlue();

        Color c = new Color(red, green, blue);
        newImage.setRGB(j, i, c.getRGB());
      }
    }

    ImageIO.write(newImage, this.type, output);
    output.close();

  }
}
