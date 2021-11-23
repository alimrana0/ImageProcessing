package controller.filereading;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import model.imaging.ImageOfPixel;

/**
 * Class to represent a function object to read in MultiLayerImages from a txt file. File
 * specifications are outlined in the interface documentation. Invisible image ids are stored in an
 * internal list.
 */
public class MultiLayerFileReader implements IMultiLayerReader {

  private final List<String> visibility;

  /**
   * Constructs a new instance of the file reader.
   */
  public MultiLayerFileReader() {
    this.visibility = new ArrayList<>();
  }

  @Override
  public Map<String, ImageOfPixel> readImages(String filename)
      throws IllegalArgumentException {
    Scanner sc;
    Map<String, ImageOfPixel> layers = new HashMap<>();

    if (filename == null) {
      throw new IllegalArgumentException("Name cannot be null");
    }

    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("File could not be found");
    }

    StringBuilder builder = new StringBuilder();

    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }

    sc = new Scanner(builder.toString());

    String token;
    String type = sc.nextLine();

    while (sc.hasNextLine()) {
      token = sc.nextLine();
      String[] line = token.split(" ");

      ImageOfPixel image = null;
      switch (type.toLowerCase()) {
        case "png":
        case "jpeg":
          image = new ImageIOFileReader().readImageFromFile(line[0]);
          break;
        case "ppm":
          image = new PPMFileReader().readImageFromFile(line[0]);
          break;
        default:
          throw new IllegalArgumentException("Invalid multi layer text.");

      }
      String id = line[1];
      this.checkVisibility(id, line[2]);
      layers.put(id, image);
    }
    return layers;
  }

  /**
   * Adds the id of the image to the internal list if it is marked as invisible in the txt file.
   *
   * @param id     Id of the image.
   * @param status Visibility status, either invisible or visible.
   */
  private void checkVisibility(String id, String status) {
    if (status.equals("invisible")) {
      this.visibility.add(id);
    }
  }

  @Override
  public List<String> readVisibility() {
    return new ArrayList<>(this.visibility);
  }
}
