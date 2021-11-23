package model.graph;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.imaging.pixel.IPixel;

public class Histogram {
  private List<List<IPixel>> pixels;
  private List<Line> lines;
  private Map<Integer, Integer> red;
  private Map<Integer, Integer> blue;
  private Map<Integer, Integer> green;
  private Map<Integer, Integer> intensity;

  public Histogram(List<List<IPixel>> pixels) {

    this.pixels = pixels;
    lines = new ArrayList<Line>();

    this.red = new HashMap<>();
    this.green = new HashMap<>();
    this.blue = new HashMap<>();
    this.intensity = new HashMap<>();
    //initialize maps for storing values
    for (int i = 0; i < 256; i++) {
      red.put(i, 0);
      blue.put(i, 0);
      green.put(i, 0);
      intensity.put(i, 0);
    }
    this.fillMapsWithFrequencies();
    this.addLinesFromFrequencyMaps();
  }

  private void fillMapsWithFrequencies() {
    //fill maps with corresponding frequencies
    for (List<IPixel> list : this.pixels) {
      for (IPixel pixel : list) {
        int redValue = pixel.getColor().getRed();
        int blueValue = pixel.getColor().getBlue();
        int greenValue = pixel.getColor().getGreen();
        int intensityValue = (redValue + greenValue + blueValue) / 3;

        this.red.put(redValue, this.red.get(redValue) + 1);
        this.blue.put(blueValue, this.blue.get(blueValue) + 1);
        this.green.put(greenValue, this.green.get(greenValue) + 1);
        this.intensity.put(intensityValue, this.intensity.get(intensityValue) + 1);
      }
    }
  }

  private void addLinesFromFrequencyMaps() {
    //create lines
    for (int i = 0; i < 255; i++) {
      Line redLine = new Line(new Position2D(i, red.get(i)),
          new Position2D(i + 1, red.get(i + 1)));

      Line greenLine = new Line(new Position2D(i, green.get(i)),
          new Position2D(i + 1, green.get(i + 1)));

      Line blueLine = new Line(new Position2D(i, blue.get(i)),
          new Position2D(i + 1, blue.get(i + 1)));

      Line intensityLine = new Line(new Position2D(i, intensity.get(i)),
          new Position2D(i + 1, intensity.get(i + 1)));
      this.lines.add(redLine);
      this.lines.add(greenLine);
      this.lines.add(blueLine);
      this.lines.add(intensityLine);
    }
  }

  public List<Line> getLines(){
    return this.lines;
  }
}
