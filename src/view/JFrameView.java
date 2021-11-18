package view;

import controller.IFeatures;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.io.File;
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
import javax.swing.filechooser.FileNameExtensionFilter;

public class JFrameView extends JFrame implements IGUI {

  private IFeatures features;
  private JPanel mainPanel;
  private JScrollPane mainScrollPane;
  private JPanel dialogBoxesPanel;

  private JLabel fileOpenDisplay;


  //TODO ADD GET COMPONENT BUTTONS
  private JButton loadButton;
  private JButton saveButton;
  private JButton blurButton;
  private JButton sharpenButton;
  private JButton greyscaleButton;
  private JButton sepiaButton;
  private JButton horizontalFlipButton;
  private JButton verticalFlipButton;
  private JButton brightenButton;
  private JButton darkenButton;


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

    //dialog boxes
    dialogBoxesPanel = new JPanel();
    dialogBoxesPanel.setBorder(BorderFactory.createTitledBorder("Dialog boxes"));
    dialogBoxesPanel.setLayout(new BoxLayout(dialogBoxesPanel, BoxLayout.PAGE_AXIS));
    mainPanel.add(dialogBoxesPanel);

    //show an image with a scrollbar
    JPanel imagePanel = new JPanel();
    //a border around the panel with a caption
    imagePanel.setBorder(BorderFactory.createTitledBorder("Showing an image"));
    imagePanel.setLayout(new GridLayout(1, 0, 10, 10));
    //imagePanel.setMaximumSize(null);
    mainPanel.add(imagePanel);

    //file open
    JPanel fileopenPanel = new JPanel();
    fileopenPanel.setLayout(new FlowLayout());
    dialogBoxesPanel.add(fileopenPanel);
    JButton fileOpenButton = new JButton("Open a file");
    fileOpenButton.setActionCommand("Open file");
    fileOpenButton.addActionListener(evt -> this.features.load("s"));
    fileopenPanel.add(fileOpenButton);
    fileOpenDisplay = new JLabel("File path will appear here");
    fileopenPanel.add(fileOpenDisplay);

    pack();
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
  public void addFeatures(IFeatures features) {

  }

  @Override
  public void actionPerformed(ActionEvent arg0) {

  }

/*
  @Override
  public void actionPerformed(ActionEvent arg0) {
    // TODO Auto-generated method stub
    switch (arg0.getActionCommand()) {
      case "Open file": {
        final JFileChooser fchooser = new JFileChooser(".");
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "JPG, BMP, and PNG Images", "jpg", "bmp", "png");
        fchooser.setFileFilter(filter);
        int retvalue = fchooser.showOpenDialog(SwingFeaturesFrame.this);
        if (retvalue == JFileChooser.APPROVE_OPTION) {
          File f = fchooser.getSelectedFile();
          this.features.load(f.getPath());
          fileOpenDisplay.setText(f.getAbsolutePath());
        }
      }
    }
  }

 */
}
