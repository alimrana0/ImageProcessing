package controller.filewriting;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import model.imaging.ImageOfPixel;
import model.imaging.pixel.IPixel;

/**
 * Class representing a PPM file writer that writes PPM ASCII images using a filetype and an iamge.
 */
public class PPMWriteFile implements IImageWriteFile {

  @Override
  public void writeFile(String name, ImageOfPixel image)
          throws IOException, IllegalArgumentException {
    if (name == null || image == null) {
      throw new IllegalArgumentException("Argument can't be null.");
    }
    String ppm = this.createPPM(image);
    File file = new File(name);
    File parent = file.getParentFile();
    if (parent != null) {
      parent.mkdirs();
    }
    FileOutputStream fos = new FileOutputStream(file);
    fos.write(ppm.getBytes());
    fos.close();
  }

  /**
   * Creates the PPM string using the image data formatted to the ASCII PPM image guidelines.
   *
   * @param image image being converted.
   * @return the PPM string.
   */
  private String createPPM(ImageOfPixel image) {
    List<List<IPixel>> pixels = image.getPixels();
    StringBuilder ppm = new StringBuilder().append("P3\n")
            .append(pixels.get(0).size()).append(" ")
            .append(pixels.size()).append("\n255\n");
    for (int i = 0; i < pixels.size(); i++) {
      for (int j = 0; j < pixels.get(0).size(); j++) {
        IPixel currPix = pixels.get(i).get(j);
        ppm.append(currPix.getColor().getRed()).append(" ")
                .append(currPix.getColor().getGreen()).append(" ")
                .append(currPix.getColor().getBlue()).append(" ");
      }
      ppm.append("\n");
    }
    return ppm.toString();
  }
}
