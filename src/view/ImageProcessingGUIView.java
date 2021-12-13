package view;


import java.awt.BorderLayout;
import java.awt.ScrollPane;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.Cursor;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JMenuBar;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;


import javax.swing.filechooser.FileNameExtensionFilter;

import model.graph.Line;
import view.graph.GraphPanel;


/**
 * Class representing the GUI view implementation of an image processor application. This GUI will
 * respond the actions done by the user through buttons and general interactions, which are handled
 * accordingly with
 */
public class ImageProcessingGUIView extends JFrame implements IImageProcessingGUIView,
    ActionListener, AdjustmentListener {

  private final IViewListener vl;
  private final JLayeredPane layerNames;

  private final JMenuItem save;
  private final JMenu filters;
  private final JMenu transformations;

  private final JLabel imgName;
  private final List<JTextField> images;
  private final JMenuItem delImg;
  private final JMenuItem showImg;
  private final JMenuItem hideImg;
  private BufferedImage topImg;


  private final GraphPanel graph;
  private final ImagePreviewGUI previewGUI;


  private int adjustedHeight;
  private int adjustedWidth;


  private int newHeight;
  private int newWidth;
  private JScrollPane previewScroll;


  /**
   * Constructor for the foundation GUI that will be manipulated in the future by the user.
   *
   * @param vl the GUI listener.
   */
  public ImageProcessingGUIView(IViewListener vl) {
    super();
    if (vl == null) {
      throw new IllegalArgumentException("Arguments can't be null.");
    }
    this.images = new ArrayList<>();
    this.vl = vl;
    this.topImg = null;
    setTitle("Image Editor");
    setSize(1800, 810);


    //Set up main panels and menu
    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new BorderLayout());
    JScrollPane mainScrollPane = new JScrollPane(mainPanel);
    add(mainScrollPane);

    JMenuBar menu = new JMenuBar();

    //Set up main menus
    JMenu file = new JMenu("File");
    file.getAccessibleContext().setAccessibleDescription(
        "Menu for file operations");

    save = new JMenu("Save");

    JMenuItem layerSave = new JMenuItem("Save First Image");
    layerSave.getAccessibleContext().setAccessibleDescription("Saves the first selected image");
    layerSave.setActionCommand("Save");
    layerSave.addActionListener(this);
    save.add(layerSave);

    JMenuItem saveAllLayers = new JMenuItem("Save All Images");
    saveAllLayers.getAccessibleContext().setAccessibleDescription("Saves all images");
    saveAllLayers.setActionCommand("Save All");
    saveAllLayers.addActionListener(this);
    save.add(saveAllLayers);

    save.setEnabled(false);
    file.add(save);

    menu.add(file);

    JMenu edit = new JMenu("Edit");
    edit.getAccessibleContext().setAccessibleDescription("Menu for editing");

    transformations = new JMenu("Transform");
    transformations.getAccessibleContext()
        .setAccessibleDescription("Menu for color transformations");
    transformations.setEnabled(false);

    filters = new JMenu("Filter");
    filters.getAccessibleContext().setAccessibleDescription("Menu for filter operations");
    filters.setEnabled(false);

    //Create menu items
    JMenuItem redComponent = new JMenuItem("Red Grayscale");
    redComponent.getAccessibleContext().setAccessibleDescription("Red Grayscale transformation");
    redComponent.setActionCommand("Red Grayscale");
    redComponent.addActionListener(this);
    transformations.add(redComponent);

    JMenuItem greenComponent = new JMenuItem("Green Grayscale");
    greenComponent.getAccessibleContext().
        setAccessibleDescription("Green Grayscale transformation");
    greenComponent.setActionCommand("Green Grayscale\"");
    greenComponent.addActionListener(this);
    transformations.add(greenComponent);

    JMenuItem blueComponent = new JMenuItem("Blue Grayscale");
    blueComponent.getAccessibleContext().setAccessibleDescription("Blue Grayscale transformation");
    blueComponent.setActionCommand("Blue Grayscale");
    blueComponent.addActionListener(this);
    transformations.add(blueComponent);

    JMenuItem grayscale = new JMenuItem("Grayscale");
    grayscale.getAccessibleContext().setAccessibleDescription("Grayscale transformation");
    grayscale.setActionCommand("Grayscale");
    grayscale.addActionListener(this);
    transformations.add(grayscale);

    JMenuItem valueComponent = new JMenuItem("Value Component");
    valueComponent.getAccessibleContext().
        setAccessibleDescription("Value Component transformation");
    valueComponent.setActionCommand("Value Component\"");
    valueComponent.addActionListener(this);
    transformations.add(valueComponent);

    JMenuItem intensityComponent = new JMenuItem("Intensity Component");
    intensityComponent.getAccessibleContext().
        setAccessibleDescription("Intensity Component transformation");
    intensityComponent.setActionCommand("Intensity Component");
    intensityComponent.addActionListener(this);
    transformations.add(intensityComponent);

    JMenuItem flipVertical = new JMenuItem("Vertical Flip");
    flipVertical.getAccessibleContext().setAccessibleDescription("Vertical Flip transformation");
    flipVertical.setActionCommand("Vertical Flip");
    flipVertical.addActionListener(this);
    transformations.add(flipVertical);

    JMenuItem flipHorizontal = new JMenuItem("Horizontal Flip");
    flipHorizontal.getAccessibleContext().
        setAccessibleDescription("Horizontal Flip transformation");
    flipHorizontal.setActionCommand("Horizontal Flip");
    flipHorizontal.addActionListener(this);
    transformations.add(flipHorizontal);

    JMenuItem blur = new JMenuItem("Blur");
    blur.getAccessibleContext().setAccessibleDescription("Blur transformation");
    blur.setActionCommand("Blur");
    blur.addActionListener(this);
    filters.add(blur);

    JMenuItem sharpen = new JMenuItem("Sharpen");
    sharpen.getAccessibleContext().setAccessibleDescription("Sharpen transformation");
    sharpen.setActionCommand("Sharpen");
    sharpen.addActionListener(this);
    filters.add(sharpen);
    edit.add(filters);

    JMenuItem sepia = new JMenuItem("Sepia");
    sepia.getAccessibleContext().setAccessibleDescription("Sepia transformation");
    sepia.setActionCommand("Sepia");
    sepia.addActionListener(this);
    transformations.add(sepia);
    edit.add(transformations);

    JMenuItem brighten = new JMenuItem("Brighten");
    brighten.getAccessibleContext().setAccessibleDescription("Brighten transformation");
    brighten.setActionCommand("Brighten");
    brighten.addActionListener(this);
    transformations.add(brighten);

    JMenuItem darken = new JMenuItem("Darken");
    darken.getAccessibleContext().setAccessibleDescription("Darken transformation");
    darken.setActionCommand("Darken");
    darken.addActionListener(this);
    transformations.add(darken);

    JButton downscaleButton = new JButton("Downscale");
    downscaleButton.setActionCommand("Downscale");
    downscaleButton.addActionListener(evt -> getNewImageDimensions());

    menu.add(edit);

    JMenu image = new JMenu("Image");
    image.getAccessibleContext().setAccessibleDescription("Menu for image usage");

    JMenuItem addImage = new JMenu("Add Image");
    addImage.getAccessibleContext().setAccessibleDescription("Used to add an image");

    JMenuItem loadLayer = new JMenuItem("Load a PPM/PNG/JPEG File");
    loadLayer.setActionCommand("Add Image");
    loadLayer.addActionListener(this);
    addImage.add(loadLayer);

    image.add(addImage);

    delImg = new JMenuItem("Delete Image");
    delImg.getAccessibleContext().setAccessibleDescription("Used to delete an image");
    delImg.setActionCommand("Delete Image");
    delImg.addActionListener(this);
    delImg.setEnabled(false);
    image.add(delImg);

    JSeparator s2 = new JSeparator();
    s2.setOrientation(SwingConstants.HORIZONTAL);
    image.add(s2);

    showImg = new JMenuItem("Show Image");
    showImg.getAccessibleContext().setAccessibleDescription("Shows the selected image");
    showImg.setActionCommand("Show Image");
    showImg.addActionListener(this);
    showImg.setEnabled(false);
    image.add(showImg);

    hideImg = new JMenuItem("Hide Image");
    hideImg.getAccessibleContext().setAccessibleDescription("Hides the selected image");
    hideImg.setActionCommand("Hide Image");
    hideImg.addActionListener(this);
    hideImg.setEnabled(false);
    image.add(hideImg);

    menu.add(image);

    mainPanel.add(menu, BorderLayout.PAGE_START);

    JPanel imgPanel = new JPanel();
    imgPanel.setBorder(BorderFactory.createTitledBorder("Image Editor"));
    imgPanel.setLayout(new GridLayout(1, 0, 20, 20));
    mainPanel.add(imgPanel, BorderLayout.CENTER);

    imgName = new JLabel();
    //Set up main imaging actions
    JScrollPane imageScrollPane = new JScrollPane(imgName);
    imageScrollPane.setPreferredSize(new Dimension(300, 500));
    imgName.setHorizontalAlignment(imageScrollPane.getWidth() / 2);
    imgPanel.add(imageScrollPane, BorderLayout.CENTER);

    JPanel manipulatePanel = new JPanel();
    manipulatePanel.setBorder(BorderFactory.createTitledBorder("Image Manipulations"));
    mainPanel.add(manipulatePanel, BorderLayout.AFTER_LAST_LINE);

    // Panel for image operation commands.
    JPanel streamPanel = new JPanel();
    streamPanel.setLayout(new GridLayout(2, 8));
    manipulatePanel.add(streamPanel);

    layerNames = new JLayeredPane();
    layerNames.setLayout(new FlowLayout());
    layerNames.setBorder(BorderFactory.createTitledBorder("Layers"));
    layerNames.setPreferredSize(new Dimension(125, 200));

    JScrollPane labelScroll = new JScrollPane(layerNames);

    mainPanel.add(labelScroll, BorderLayout.WEST);

    //GRAPH PANEL CREATED
    graph = new GraphPanel();
    ScrollPane histogram = new ScrollPane();
    histogram.add(graph);

    graph.setBorder(BorderFactory.createTitledBorder("Selected Image Histogram"));
    histogram.setPreferredSize(new Dimension(280, 200));

    JScrollPane histogramScroll = new JScrollPane(histogram);
    mainPanel.add(histogramScroll, BorderLayout.EAST);

    //BUTTONS CREATED


    //buttons for actions
    JButton redGrayscaleButton = new JButton("Red Grayscale");
    redGrayscaleButton.setActionCommand("Red Grayscale");
    redGrayscaleButton.addActionListener(this);

    JButton greenGrayscaleButton = new JButton("Green Grayscale");
    greenGrayscaleButton.setActionCommand("Green Grayscale");
    greenGrayscaleButton.addActionListener(this);

    JButton blueGrayscaleButton = new JButton("Blue Grayscale");
    blueGrayscaleButton.setActionCommand("Blue Grayscale");
    blueGrayscaleButton.addActionListener(this);

    JButton grayscaleButton = new JButton("Grayscale");
    grayscaleButton.setActionCommand("Grayscale");
    grayscaleButton.addActionListener(this);

    JButton valueButton = new JButton("Value Component");
    valueButton.setActionCommand("Value Component");
    valueButton.addActionListener(this);

    JButton intensityButton = new JButton("Intensity Component");
    intensityButton.setActionCommand("Intensity Component");
    intensityButton.addActionListener(this);

    JButton horizontalButton = new JButton("Horizontal Flip");
    horizontalButton.setActionCommand("Horizontal Flip");
    horizontalButton.addActionListener(this);

    JButton verticalButton = new JButton("Vertical Flip");
    verticalButton.setActionCommand("Vertical Flip");
    verticalButton.addActionListener(this);

    JButton blurButton = new JButton("Blur");
    blurButton.setActionCommand("Blur");
    blurButton.addActionListener(this);

    JButton sharpenButton = new JButton("Sharpen");
    sharpenButton.setActionCommand("Sharpen");
    sharpenButton.addActionListener(this);

    JButton sepiaButton = new JButton("Sepia");
    sepiaButton.setActionCommand("Sepia");
    sepiaButton.addActionListener(this);

    JButton darkenButton = new JButton("Darken");
    darkenButton.setActionCommand("Darken");
    darkenButton.addActionListener(this);

    JButton brightenButton = new JButton("Brighten");
    brightenButton.setActionCommand("Brighten");
    brightenButton.addActionListener(this);

    JButton showButton = new JButton("Show Image");
    showButton.setActionCommand("Show Image");
    showButton.addActionListener(this);

    JButton hideButton = new JButton("Hide Image");
    hideButton.setActionCommand("Hide Image");
    hideButton.addActionListener(this);

    streamPanel.add(redGrayscaleButton);
    streamPanel.add(greenGrayscaleButton);
    streamPanel.add(blueGrayscaleButton);
    streamPanel.add(grayscaleButton);
    streamPanel.add(valueButton);
    streamPanel.add(intensityButton);
    streamPanel.add(verticalButton);
    streamPanel.add(horizontalButton);
    streamPanel.add(blurButton);
    streamPanel.add(sharpenButton);
    streamPanel.add(sepiaButton);
    streamPanel.add(brightenButton);
    streamPanel.add(darkenButton);
    streamPanel.add(downscaleButton);

    streamPanel.add(showButton);
    streamPanel.add(hideButton);



    //preview JPanel made
    previewGUI = new ImagePreviewGUI(this.topImg);
    previewScroll = new JScrollPane(previewGUI);

    previewScroll.setPreferredSize(new Dimension(200,200));

    previewScroll.getVerticalScrollBar().addAdjustmentListener(this);
    previewScroll.getHorizontalScrollBar().addAdjustmentListener(this);
    //previewScroll.getVerticalScrollBar().setPreferredSize(new Dimension(10, 0));
    //previewScroll.getHorizontalScrollBar().setPreferredSize(new Dimension(0, 10));

    //create and set frame parameters
    JFrame previewFrame = new JFrame("Operation Preview");
    previewFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    previewFrame.setPreferredSize(new Dimension(200, 200));
    previewFrame.setResizable(false);

    previewFrame.setContentPane(previewScroll);
    previewFrame.pack();
    previewFrame.setVisible(true);

  }

  @Override
  public void run() {
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setVisible(true);
  }

  @Override
  public void renderMessage(String msg) throws IllegalArgumentException {
    JOptionPane.showMessageDialog(null, msg, "Error Occurred", JOptionPane.ERROR_MESSAGE);
  }

  @Override
  public void addImage(String name) {
    JTextField layer = new JTextField();
    layer.setText(name);
    layer.setPreferredSize(new Dimension(100, 50));
    layer.setHorizontalAlignment(JLabel.CENTER);
    layer.setBorder(BorderFactory.createLineBorder(Color.PINK));
    layer.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    layer.setEditable(false);

    this.images.add(layer);

    layer.addMouseListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent e) {
        vl.selectLayer(name);
        delImg.setEnabled(true);
        showImg.setEnabled(true);
        hideImg.setEnabled(true);
        filters.setEnabled(true);
        transformations.setEnabled(true);

        for (JTextField j : images) {
          if (j.equals(layer)) {
            j.setBorder(BorderFactory.createLineBorder(Color.RED));
          }
        }
      }
    });
    layerNames.add(layer, layerNames.getComponentCount() + 1, 0);
    repaint();
    revalidate();
  }

  @Override
  public void setImage(BufferedImage img) {
    this.topImg = img;
    this.imgName.setIcon(new ImageIcon(this.topImg));
    save.setEnabled(true);

    previewGUI.setImageToShow(img);
    previewGUI.repaint();
    previewGUI.revalidate();

    repaint();
    revalidate();
  }


  @Override
  public void actionPerformed(ActionEvent a) {
    //used for brightness changes
    int increment = 25;
    switch (a.getActionCommand()) {
      case "Red Grayscale":
        sendRedGrayscaleInstruction();
        break;
      case "Green Grayscale":
        sendGreenGrayscaleInstruction();
        break;
      case "Blue Grayscale":
        sendBlueGrayscaleInstruction();
        break;
      case "Grayscale":
        sendGrayscaleLayerInstruction();
        break;
      case "Value Component":
        sendValueInstruction();
        break;
      case "Intensity Component":
        sendIntensityInstruction();
        break;
      case "Vertical Flip":
        sendFlipVertical();
        break;
      case "Horizontal Flip":
        sendFlipHorizontal();
        break;
      case "Blur":
        sendBlurInstruction();
        break;
      case "Sharpen":
        sendSharpenInstruction();
        break;
      case "Sepia":
        sendSepiaInstruction();
        break;
      case "Brighten":
        sendBrightenInstruction(increment);
        break;
      case "Darken":
        sendDarkenInstruction(increment);
        break;
      case "Downscale":
        emitDownscaleEvent(640, 426);
        break;
      case "Load Multi":
        sendLoadAllInstructions();
        break;
      case "Add Image":
        sendLoadImageInstruction();
        break;
      case "Delete Image":
        sendDeleteInstruction();
        break;
      case "Save":
        sendSaveInstruction();
        break;
      case "Save All":
        sendSaveAllInstructions();
        break;
      case "Show Image":
        sendShowInstruction();
        break;
      case "Hide Image":
        sendHideInstruction();
        break;
      default:
        break;
    }
  }

  /**
   * Sends a preview instruction for the specific command.
   * private void send previewInstruction
   */


  /**
   * Sends a red grayscale Instruction, which instructs the listener to grayscale the selected
   * image according to its red component in the multilayered image using the path of the file.
   */
  private void sendRedGrayscaleInstruction() {
    vl.redComponentHandler();
  }

  /**
   * Sends a green grayscale Instruction, which instructs the listener to grayscale the
   * selected image according to its green component in the multilayered image using the path of
   * the file.
   */
  private void sendGreenGrayscaleInstruction() {
    vl.greenComponentHandler();
  }

  /**
   * Sends a blue grayscale Instruction, which instructs the listener to grayscale the selected
   * image according to its blue component in the multilayered image using the path of the file.
   */
  private void sendBlueGrayscaleInstruction() {
    vl.blueComponentHandler();
  }

  /**
   * Sends a grayscale Instruction, which instructs the listener to grayscale the selected image
   * in the multilayered image using the path of the file.
   */
  private void sendGrayscaleLayerInstruction() {
    vl.grayscaleHandler();
  }

  /**
   * Tells the listener to apply a vertical flip to the current layer in the image.
   */
  private void sendFlipVertical() {
    vl.handleFlipVertical();
  }

  /**
   * Tells the listener to apply a horizontal flip to the current layer in the image.
   */
  private void sendFlipHorizontal() {
    vl.handleFlipHorizontal();
  }

  /**
   * Sends a value Instruction, which instructs the listener to value change the selected image
   * in the multilayered image using the path of the file.
   */
  private void sendValueInstruction() {
    vl.valueHandler();
  }

  /**
   * Sends an intensity Ins
   * truction, which instructs the listener to intensity change the selected
   * image in the multilayered image using the path of the file.
   */
  private void sendIntensityInstruction() {
    vl.intensityHandler();
  }

  /**
   * Sends a blur Instruction, which instructs the listener to blur the selected image
   * in the multilayered image using the path of the file.
   */
  private void sendBlurInstruction() {
    vl.blurHandler();
  }

  /**
   * Sends a sharpen Instruction, which instructs the listener to sharpen the selected image
   * in the multilayered image using the path of the file.
   */
  private void sendSharpenInstruction() {
    vl.sharpenHandler();
  }


  /**
   * Sends a sepia Instruction, which instructs the listener to sepia the selected image
   * in the multilayered image using the path of the file.
   */
  private void sendSepiaInstruction() {
    vl.sepiaHandler();
  }


  /**
   * Sends a brightening Instruction, which instructs the listener to brighten the selected image
   * in the multilayered image using the path of the file.
   */
  private void sendBrightenInstruction(int val) {
    vl.brightenHandler(val);
  }

  /**
   * Sends a darkening Instruction, which instructs the listener to darken the selected image
   * in the multilayered image using the path of the file.
   */
  private void sendDarkenInstruction(int val) {
    vl.darkenHandler(val);
  }

  private void emitDownscaleEvent(int adjustedHeight, int adjustedWidth) {
    vl.handleDownscale(adjustedHeight, adjustedWidth);
  }
  /**
   * Sends a load Instruction, which instructs the listener to load a selected image
   * in the multilayered image using the path of the file, and a user-input name for the layer.
   */
  private void sendLoadImageInstruction() {
    String[] types = {"PPM", "PNG", "JPEG"};
    int typeVal = JOptionPane
        .showOptionDialog(this,
            "Select the file type you would like to import", "File Types",
            JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, types,
            null);

    if (typeVal != -1) {
      String name = JOptionPane.showInputDialog("Please enter the designated name for this layer");

      if (name != null) {
        final JFileChooser fc = new JFileChooser(".");

        setChooser(typeVal, fc);
        int result = fc.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
          File f = fc.getSelectedFile();
          vl
              .loadLayerHandler(f.getAbsolutePath(), types[typeVal],
                  name);
        }
      }
    }
  }


  /**
   * Sends a load all Instruction, which instructs the listener to load in the multilayered image
   * using the path of the file.
   */
  private void sendLoadAllInstructions() {
    final JFileChooser fc = new JFileChooser(".");
    FileNameExtensionFilter filter = new FileNameExtensionFilter(
        "TXT", "txt");
    fc.setFileFilter(filter);
    int result = fc.showOpenDialog(this);
    if (result == JFileChooser.APPROVE_OPTION) {
      File f = fc.getSelectedFile();
      this.images.clear();
      this.layerNames.removeAll();
      vl.loadAllHandler(f.getAbsolutePath());
    }
  }

  /**
   * Sends a deletion Instruction, which instructs the listener to delete the selected layer
   * in the multilayered image using the path of the file.
   */
  private void sendDeleteInstruction() {
    vl.removeLayer();
    this.images.removeIf(jtf -> jtf.getText().equals(this.vl.getSelectedLayerID()));
    this.layerNames.removeAll();

    for (JTextField j : this.images) {
      this.layerNames.add(j);
    }

    this.delImg.setEnabled(false);
    this.filters.setEnabled(false);
    this.transformations.setEnabled(false);
    this.showImg.setEnabled(false);
    this.hideImg.setEnabled(false);

    repaint();
    revalidate();
  }

  /**
   * Sends a save Instruction, which instructs the listener to save the top visible image
   * in the multilayered image using the path of the file.
   */
  private void sendSaveInstruction() {
    if (!this.images.isEmpty()) {
      String[] types = {"PPM", "PNG", "JPEG"};
      int typeVal = JOptionPane
          .showOptionDialog(this,
              "Select the file type you would like to save as", "File Types",
              JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null,
              types,
              null);
      final JFileChooser fc = new JFileChooser(".");
      if (typeVal != -1) {
        setChooser(typeVal, fc);
        int result = fc.showSaveDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
          File f = fc.getSelectedFile();
          vl.saveLayerHandler(f.getAbsolutePath(), types[typeVal]);
        }
      }
    } else {
      JOptionPane.showMessageDialog(null,
          "Please add an image before trying to save");
    }
  }

  /**
   * Sends a save all Instruction, which instructs the listener to save the multilayered image
   * using the path of the file.
   */
  private void sendSaveAllInstructions() {
    String[] types = {"PPM", "PNG", "JPEG"};
    int filetypeValue = JOptionPane
        .showOptionDialog(this,
            "Select the file type you would like to save as", "File Types",
            JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null,
            types,
            null);
    final JFileChooser fc = new JFileChooser(".");
    FileNameExtensionFilter filter = new FileNameExtensionFilter(
        "TXT", "txt");
    fc.setFileFilter(filter);
    int result = fc.showSaveDialog(this);
    if (result == JFileChooser.APPROVE_OPTION) {
      File f = fc.getSelectedFile();
      vl.saveAllHandler(f.getAbsolutePath(), types[filetypeValue]);
    }
  }

  /**
   * Chooses the file type by the given type value and file chooser.
   *
   * @param typeVal the file type value.
   * @param fc      the chooser being processed.
   */
  private void setChooser(int typeVal, JFileChooser fc) {
    if (typeVal == 0) {
      FileNameExtensionFilter filter = new FileNameExtensionFilter(
          "PPM", "ppm");
      fc.setFileFilter(filter);
    } else if (typeVal == 1) {
      FileNameExtensionFilter filter = new FileNameExtensionFilter(
          "PNG", "png");
      fc.setFileFilter(filter);
    } else if (typeVal == 2) {
      FileNameExtensionFilter filter = new FileNameExtensionFilter(
          "JPEG", "jpeg");
      fc.setFileFilter(filter);
    }
  }

  /**
   * Sends a show Instruction, which instructs the listener to show the selected layer
   * in the multilayered image using the path of the file.
   */
  private void sendShowInstruction() {
    vl.show();
  }

  /**
   * Sends a hide Instruction, which instructs the listener to hide the selected layer
   * in the multilayered image using the path of the file.
   */
  private void sendHideInstruction() {
    vl.hide();
  }

  /**
   * Given a list of lines this method updates the histogram with the given lines.
   *
   * @param lines A list of lines that are drawn to make a histogram.
   */
  public void updateGraph(List<Line> lines) {
    this.repaint();
    this.graph.repaint();
    this.graph.setLines(lines);
  }

  private void getNewImageDimensions() {
    String newDimensions = JOptionPane.showInputDialog("Please enter the desired downscale dimensions" +
        "in the following format: \"HeightxWidth.\" For Example: 640x426");

    String[] dim = newDimensions.split("x", 3);
    adjustedHeight = Integer.parseInt(dim[0]);
    adjustedWidth = Integer.parseInt(dim[1]);
    emitDownscaleEvent(adjustedHeight, adjustedWidth);

  }


  @Override
  public void adjustmentValueChanged(AdjustmentEvent e) {
    System.out.println(previewScroll.getVerticalScrollBar().getValue());
    System.out.println(previewScroll.getHorizontalScrollBar().getValue());
  }
}