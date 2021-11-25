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
 * Class representing an object that can read and name files of different types in the IO
 * library for Image. It takes in a filepath and keeps track of the images' information using
 * their contents.
 */
public class ReadMultiLayered implements IReadMultiLayered {

  private final List<String> invisible;

  /**
   * Constructor for a multilayered file reader.
   */
  public ReadMultiLayered() {
    this.invisible = new ArrayList<>();
  }

  @Override
  public Map<String, ImageOfPixel> readImages(String name)
          throws IllegalArgumentException {
    Scanner sc;
    Map<String, ImageOfPixel> layers = new HashMap<>();

    if (name == null) {
      throw new IllegalArgumentException("Name can't' be null");
    }

    try {
      sc = new Scanner(new FileInputStream(name));
    } catch (FileNotFoundException exception) {
      throw new IllegalArgumentException("File couldn't be found");
    }
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }

    //now set up the scanner to read from the string we just built
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
          image = new ImageReadFile().readImage(line[0]);
          break;
        case "ppm":
          image = new PPMReadFile().readImage(line[0]);
          break;
        default:
          throw new IllegalArgumentException("Invalid layered text.");

      }
      String id = line[1];
      this.checkVisibility(id, line[2]);
      layers.put(id, image);
    }
    return layers;
  }

  /**
   * Adds the image's ID to the list of current invisible images if it is invisible.
   *
   * @param id     image ID.
   * @param status visibility status.
   */
  private void checkVisibility(String id, String status) {
    if (status.equals("invisible")) {
      this.invisible.add(id);
    }
  }

  @Override
  public List<String> findVisibility() {
    return new ArrayList<>(this.invisible);
  }
}
