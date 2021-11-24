import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import model.graph.Line;
import view.IViewListener;
import view.ImageProcessorGUIViewImpl;

public class MockWindow extends ImageProcessorGUIViewImpl {

  IViewListener listener;

  /**
   * Constructs the initial instance of the GUI.
   *
   * @param listener Listener for this GUI
   */
  public MockWindow(IViewListener listener) {
    super(listener);
    this.listener = listener;
  }

  @Override
  public void runGUI() {
    ImageProcessorGUIViewImpl.setDefaultLookAndFeelDecorated(false);

    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setVisible(true);
  }

  @Override
  public void renderMessage(String msg) throws IllegalArgumentException {
    JOptionPane.showMessageDialog(null, msg, "Error", JOptionPane.ERROR_MESSAGE);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {
      case "Save":
        emitSaveEvent();
        break;
      case "Blur":
        emitBlurLayerEvent();
        break;
      case "Sharpen":
        emitSharpenLayerEvent();
        break;
      case "Grayscale":
        emitGrayscaleLayerEvent();
        break;
      case "Sepia":
        emitSepiaLayerEvent();
        break;
      case "Red Component":
        emitRedComponentLayerEvent();
        break;
      case "Green Component":
        emitGreenComponentLayerEvent();
        break;
      case "Blue Component":
        emitBlueComponentLayerEvent();
        break;
      case "Value Component":
        emitValueComponentLayerEvent();
        break;
      case "Intensity Component":
        emitIntensityComponentLayerEvent();
        break;
      case "Darken":
        emitDarkenEvent(5);
        break;
      case "Brighten":
        emitBrightenEvent(3);
        break;
      case "Vertical Flip":
        emitVerticalFlipEvent();
        break;
      case "Horizontal Flip":
        emitHorizontalFlipEvent();
        break;
      case "Add Image":
        emitLoadImageEvent();
        break;
      case "Delete Image":
        emitDeleteLayerEvent();
        break;
    /*  case "Select Image":
        emitSelectLayerEvent();
        break;*/
      case "Show Image":
        emitShowLayerEvent();
        break;
      case "Hide Image":
        emitHideLayerEvent();
        break;
      case "Save All":
        emitSaveAllEvent();
        break;
      case "Load Multi":
        emitLoadAllEvent();
    }
  }

  /**
   * Emits a load all event and tells the listener to load in the multi layer image at the selected
   * file path.
   */
  private void emitLoadAllEvent() {
      listener.handleLoadAllLayerEvent("f.getAbsolutePath()");

  }

  /**
   * Tells the listener to save the top most visible layer image with the given file type at the
   * selected file path.
   */
  private void emitSaveEvent() {
          listener.handleSaveLayerEvent("f.getAbsolutePath()",
              "optionsFileType[filetypeValue]");

  }
  /**
   * Tells the listener to save the whole image with the specified type from the user and the
   * selected path from the user.
   */
  private void emitSaveAllEvent() {
      listener.handleSaveAllLayerEvent("name", "type");
  }

  /**
   * Tells the listener to blur the current layer in the image.
   */
  private void emitBlurLayerEvent() {
    listener.handleBlurEvent();
  }

  /**
   * Tells the listener to sharpen the current layer in the image.
   */
  private void emitSharpenLayerEvent() {
    listener.handleSharpenEvent();
  }

  /**
   * Tells the listener to grayscale the current layer in the image.
   */
  private void emitGrayscaleLayerEvent() {
    listener.handleGrayscaleEvent();
  }

  /**
   * Tells the listener to sepia the current layer in the image.
   */
  private void emitSepiaLayerEvent() {
    listener.handleSepiaEvent();
  }

  /**
   * Tells the listener to apply the red component to the current image.
   */
  private void emitRedComponentLayerEvent() {
    listener.handleRedEvent();
  }

  /**
   * Tells the listener to apply the green component to the current layer in the image.
   */
  private void emitGreenComponentLayerEvent() {
    listener.handleGreenEvent();
  }

  /**
   * Tells the listener to apply the blue component to the current layer in the image.
   */
  private void emitBlueComponentLayerEvent() {
    listener.handleBlueEvent();
  }


  /**
   * Tells the listener to apply the value component to the current layer in the image.
   */
  private void emitValueComponentLayerEvent() {
    listener.handleValueEvent();
  }

  /**
   * Tells the listener to apply the intensity component to the current layer in the image.
   */
  private void emitIntensityComponentLayerEvent() {
    listener.handleIntensityEvent();
  }

  /**
   * Tells the listener to apply a darken transformation with the given val to the current
   * layer in the image.
   */
  private void emitDarkenEvent(int val) {
    listener.handleDarkenEvent(val);
  }

  /**
   * Tells the listener to apply a brighten transformation with the given val to the current
   * layer in the image.
   */
  private void emitBrightenEvent(int val) {
    listener.handleBrightenEvent(val);
  }


  /**
   * Tells the listener to apply a vertical flip to the current layer in the image.
   */
  private void emitVerticalFlipEvent() {
    listener.handleVerticalFlipEvent();
  }

  /**
   * Tells the listener to apply a horizontal flip to the current layer in the image.
   */
  private void emitHorizontalFlipEvent() {
    listener.handleHorizontalFlipEvent();
  }



  /**
   * Tells the listener to remove the current layer. It then updates the list of layers in the GUI
   * with the new list of layers.
   */
  private void emitDeleteLayerEvent() {
    listener.removeLayerEvent();
  }

  /**
   * Tells the layer to set the layer with the given id from the user as the current layer. Also
   * sets the board of the layer in the list to red if it has been selected as the current.
   */
  private void emitSelectLayerEvent() {
    String layerName = JOptionPane.showInputDialog("Layer Name");
      listener.setCurrentLayerEvent(layerName);
  }

  /**
   * Tells the listener to set the current layer to visible.
   */
  private void emitShowLayerEvent() {
    listener.showEvent();
  }

  /**
   * Tells the listener to make the current layer invisible.
   */
  private void emitHideLayerEvent() {
    listener.hideEvent();
  }

  /**
   * Tells the listener to load in the image with the user selected file type and at the selected
   * file path as a layer in the program. The name of the layer is also taken from the user.
   */
  private void emitLoadImageEvent() {
    listener.handleLoadLayerEvent("name","type", "name");

  }



}
