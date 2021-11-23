package view;

import controller.IFeatures;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import model.imaging.Color;
import model.imaging.IColor;
import model.imaging.Posn;
import model.imaging.pixel.IPixel;
import model.imaging.pixel.Pixel;
import view.graph.GraphPanel;

public class JFrameView extends JFrame implements IGUI {

  private JPanel mainPanel;
  private JScrollPane mainScrollPane;



  public JFrameView() {
    super();
    setTitle("Image Processing Program");
    setSize(600, 600);



    mainPanel = new JPanel();
    //for elements to be arranged vertically within this panel
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
    //scroll bars around this main panel
    mainScrollPane = new JScrollPane(mainPanel);
    add(mainScrollPane);


    List<List<IPixel>> listPixels = new ArrayList<>();
    for(int i = 0; i <256; i++) {
      ArrayList<IPixel> pixels = new ArrayList<>();
      IColor test = new model.imaging.Color(i, 0, 0);
      IColor test1 = new model.imaging.Color(i, i, 0);
      IColor test2 = new model.imaging.Color(i, i, i);

      IPixel pixel1 = new Pixel(new Posn(0, 0), test);
      IPixel pixel2 = new Pixel(new Posn(0, 0), test1);
      IPixel pixel3 = new Pixel(new Posn(0, 0), test2);
      pixels.add(pixel1);
      pixels.add(pixel2);
      pixels.add(pixel3);
      listPixels.add(pixels);
    }
    for(int i = 0; i <600; i++) {
      ArrayList<IPixel> pixels = new ArrayList<>();
      IColor test = new model.imaging.Color(200, 0, 0);

      IPixel pixel1 = new Pixel(new Posn(0, 0), test);
      pixels.add(pixel1);
      listPixels.add(pixels);
    }
    for(int i = 0; i <600; i++) {
      ArrayList<IPixel> pixels = new ArrayList<>();
      IColor test = new model.imaging.Color(0, 100, 0);

      IPixel pixel1 = new Pixel(new Posn(0, 0), test);
      pixels.add(pixel1);
      listPixels.add(pixels);
    }
    JPanel graph = new GraphPanel(listPixels);
    JScrollPane scrollGraph = new JScrollPane(graph);
    mainPanel.add(scrollGraph);

   pack();
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
  }

  @Override
  public void displayImage() {

  }

  @Override
  public void displayHistogram() {

  }

  @Override
  public void displayPopUp(String message) {

  }

  @Override
  public void addFeatures(ActionListener features) {
  }

  @Override
  public String getFile() {
    /*
    System.out.println("HERE");
    final JFileChooser fchooser = new JFileChooser(".");
    FileNameExtensionFilter filter = new FileNameExtensionFilter(
        "JPG, BMP, and PNG Images", "jpg", "bmp", "png");
    fchooser.setFileFilter(filter);
    int retvalue = fchooser.showOpenDialog(this);
    if (retvalue == JFileChooser.APPROVE_OPTION) {
      File f = fchooser.getSelectedFile();
      fileOpenDisplay.setText(f.getAbsolutePath());
      return f.getPath();
    }
    else {
      return null;

     */
    return null;
  }
}



