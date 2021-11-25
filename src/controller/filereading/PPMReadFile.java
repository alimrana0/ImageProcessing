package controller.filereading;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import model.imaging.Color;
import model.imaging.Image;
import model.imaging.ImageOfPixel;
import model.imaging.Posn;
import model.imaging.pixel.IPixel;
import model.imaging.pixel.Pixel;


/**
 * This class contains utility methods to read a PPM image from file and simply print its contents.
 * Feel free to change this method as required.
 */
public class PPMReadFile implements IReadFile {

  @Override
  public ImageOfPixel readImage(String name) throws IllegalArgumentException {
    Scanner sc;

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

    token = sc.next();
    if (!token.equals("P3")) {
      throw new IllegalArgumentException("Invalid PPM file: plain RAW file should begin with P3");
    }
    int width = sc.nextInt();
    int height = sc.nextInt();
    int maxValue = sc.nextInt();

    ArrayList<ArrayList<IPixel>> pixels = new ArrayList<>();
    for (int i = 0; i < height; i++) {
      ArrayList<IPixel> row = new ArrayList<>();
      for (int j = 0; j < width; j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        row.add(new Pixel(new Posn(j, i), new Color(r, g, b)));

      }
      pixels.add(row);
    }
    return new Image(pixels);
  }

}

