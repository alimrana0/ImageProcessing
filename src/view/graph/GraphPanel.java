package view.graph;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JPanel;
import model.graph.Line;
import model.graph.Position2D;
import model.imaging.pixel.IPixel;

public class GraphPanel extends JPanel {

  private List<List<IPixel>> pixels;
  private List<Line> lines;
  private Map<Integer, Integer> red;
  private Map<Integer, Integer> blue;
  private Map<Integer, Integer> green;
  private Map<Integer, Integer> intensity;
  //the rectangle within which all lines lie
  private Position2D minD, maxD;


  public GraphPanel(List<List<IPixel>> pixels) {
    super();

    this.pixels = pixels;
    lines = new ArrayList<Line>();
    this.setBackground(Color.WHITE);
    minD = new Position2D(0, 0);
    maxD = new Position2D(0, 0);

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


  public void setLines(List<Line> lines) {
    this.lines = new ArrayList<Line>(lines);

    List<Position2D> points = new ArrayList<Position2D>();
    for (Line l : this.lines) {
      points.add(new Position2D(l.start));
      points.add(new Position2D(l.end));
    }
    if (points.size() > 0) {

      minD = points.get(0);
      maxD = points.get(1);

      for (Position2D p : points) {
        if (p.getX() < minD.getX()) {
          minD = new Position2D(p.getX(), minD.getY());
        }
        if (p.getY() > minD.getY()) {
          minD = new Position2D(minD.getX(), p.getY());
        }
      }

      for (Position2D p : points) {
        if (p.getX() > maxD.getX()) {
          maxD = new Position2D(p.getX(), maxD.getY());
        }
        if (p.getY() > maxD.getY()) {
          maxD = new Position2D(maxD.getX(), p.getY());
        }
      }
    }
  }

  /**
   * Override the paintComponent method of the JPanel Do NOT override paint!
   *
   * @param g
   */

  @Override
  protected void paintComponent(Graphics g) {
    //never forget to call super.paintComponent!
    super.paintComponent(g);

    Graphics2D g2d = (Graphics2D) g;


    /*
    the origin of the panel is top left. In order
    to make the origin bottom left, we must "flip" the
    y coordinates so that y = height - y

    We do that by using an affine transform. The flip
    can be specified as scaling y by -1 and then
    translating by height.
     */

    AffineTransform originalTransform = g2d.getTransform();

    //the order of transforms is bottom-to-top
    //so as a result of the two lines below,
    //each y will first be scaled, and then translated
    g2d.translate(0, this.getPreferredSize().getHeight());
    g2d.scale(1, -1);

    List colors = new ArrayList();
    colors.add(Color.RED);
    colors.add(Color.GREEN);
    colors.add(Color.BLUE);
    colors.add(Color.BLACK);
    int color = 0;
    for (Line l : lines) {
      switch (color) {
        case 0:
          g2d.setColor((Color) colors.get(color));
          color++;
          break;
        case 1:
          g2d.setColor((Color) colors.get(color));
          color++;
          break;
        case 2:
          g2d.setColor((Color) colors.get(color));
          color++;
          break;
        case 3:
          g2d.setColor((Color) colors.get(color));
          color = 0;
          break;
      }

      Position2D start = l.start;
      Position2D end = l.end;
      g2d.drawLine((int) start.getX(), (int) start.getY(),
          (int) end.getX(), (int) end.getY());
    }
  }
}

