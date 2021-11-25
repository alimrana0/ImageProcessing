package controller.filewriting;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import model.imaging.ImageOfPixel;

/**
 * Class representing a multilayered file writer that creates a text file based on the image data,
 * name its path, file type, and images.
 */
public class WriteMultiLayered implements IWriteMultiLayered {

  @Override
  public void writeFile(String name, String type, Map<String, ImageOfPixel> layers,
                        List<String> invisible) throws IllegalArgumentException, IOException {
    if (name == null || type == null || layers == null || invisible == null) {
      throw new IllegalArgumentException("Arguments can't be null");
    }
    StringBuilder txt = new StringBuilder().append(type).append("\n");

    for (Map.Entry<String, ImageOfPixel> image : layers.entrySet()) {
      String imageName = "";
      switch (type.toLowerCase()) {
        case "ppm":
          imageName = name + "\\" + image.getKey() + ".ppm";
          new PPMWriteFile().writeFile(imageName, image.getValue());
          txt.append(imageName).append(" ").append(image.getKey()).append(" ")
                  .append(this.visibility(image.getKey(), invisible));
          break;
        case "png":
          imageName = name + "\\" + image.getKey() + ".png";
          new PNGWriteFile().writeFile(imageName, image.getValue());
          txt.append(imageName).append(" ").append(image.getKey()).append(" ")
                  .append(this.visibility(image.getKey(), invisible));
          break;
        case "jpeg":
          imageName = name + "\\" + image.getKey() + ".jpeg";
          new JPEGWriteFile().writeFile(imageName, image.getValue());
          txt.append(imageName).append(" ").append(image.getKey()).append(" ")
                  .append(this.visibility(image.getKey(), invisible));
          break;
        default:
          throw new IllegalArgumentException("Invalid txt type.");
      }
      txt.append("\n");
    }
    String p = Pattern.quote(System.getProperty("file.separator"));
    String[] tokens = name.split(p);
    File file = new File(name + "\\" + tokens[tokens.length - 1] + ".txt");
    file.getParentFile().mkdirs();
    FileOutputStream stream = new FileOutputStream(file);
    stream.write(txt.toString().getBytes());
    stream.close();
  }

  /**
   * Checks the visibility status of a given image using its ID.
   *
   * @param key       image ID.
   * @param invisible list of IDs corresponding to invisible images.
   * @return the visibility status of the image in string format.
   */
  private String visibility(String key, List<String> invisible) {
    if (invisible.contains(key)) {
      return "invisible";
    } else {
      return "visible";
    }
  }
}
