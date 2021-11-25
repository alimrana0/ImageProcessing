package controller;

import java.awt.image.BufferedImage;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import controller.filereading.IReadMultiLayered;
import controller.filereading.ImageReadFile;
import controller.filereading.ReadMultiLayered;
import controller.filereading.PPMReadFile;
import controller.filewriting.JPEGWriteFile;
import controller.filewriting.WriteMultiLayered;
import controller.filewriting.PNGWriteFile;
import controller.filewriting.PPMWriteFile;
import model.IImageProcessingSession;
import model.imaging.ImageOfPixel;
import view.IViewListener;
import view.IImageProcessingGUIView;
import view.ImageProcessingGUIView;

/**
 * Class representing the controller for an ImageProcessor that deals with multilayered images via
 * the use of a Graphical User Interface. This interface can be used to perform manipulations on
 * these images, and can be loaded and saved in following file formats: PPN, PNG, and JPEG.
 */
public class ImageProcessingControllerGUI implements ImageController, IViewListener {

  private final IImageProcessingSession model;
  private final IImageProcessingGUIView view;
  private String selected;

  /**
   * Constructs an instance of a GUI controller.
   *
   * @param model the image processor's model.
   * @throws IllegalArgumentException if given a null argument.
   */
  public ImageProcessingControllerGUI(IImageProcessingSession model)
          throws IllegalArgumentException {
    if (model == null) {
      throw new IllegalArgumentException("Null parameter.");
    }
    this.model = model;
    this.view = new ImageProcessingGUIView(this);
    this.selected = null;
  }

  @Override
  public void run() throws IllegalStateException {
    this.view.setImage(this.getTopVisibleImage());
    this.view.run();
  }

  @Override
  public String getSelectedLayerID() {
    return this.selected;
  }

  private String getTopVisibleID() throws IllegalArgumentException {
    for (Map.Entry<String, ImageOfPixel> item : this.model.getImages().entrySet()) {
      if (!this.model.getInvisible().contains(item.getKey())) {
        return item.getKey();
      }
    }
    throw new IllegalArgumentException("No visible images.");
  }

  @Override
  public void saveLayerHandler(String filename, String filetype) {
    switch (filetype.toLowerCase()) {
      case "ppm":
        try {
          new PPMWriteFile()
                  .writeFile(filename + ".ppm", this.model.getImage(getTopVisibleID()));
        } catch (IllegalArgumentException e) {
          handleRender(e.getMessage());
        } catch (IOException e) {
          throw new IllegalStateException();
        }
        break;
      case "png":
        try {
          new PNGWriteFile().writeFile(filename + ".png",
                  this.model.getImage(getTopVisibleID()));
        } catch (IllegalArgumentException e) {
          handleRender(e.getMessage());
        } catch (IOException e) {
          throw new IllegalStateException();
        }
        break;
      case "jpeg":
        try {
          new JPEGWriteFile()
                  .writeFile(filename + ".jpeg", this.model.getImage(getTopVisibleID()));
        } catch (IllegalArgumentException e) {
          handleRender(e.getMessage());
        } catch (IOException e) {
          throw new IllegalStateException();
        }
        break;
      default:
        handleRender("The file type is invalid");
    }
  }

  @Override
  public void loadLayerHandler(String filename, String filetype, String layerName) {
    switch (filetype.toLowerCase()) {
      case "png":
      case "jpeg":
        try {
          handleAdd(layerName, new ImageReadFile().readImage(filename));
        } catch (IllegalArgumentException e) {
          handleRender(e.getMessage());
        }
        break;
      case "ppm":
        try {
          handleAdd(layerName, new PPMReadFile().readImage(filename));
        } catch (IllegalArgumentException e) {
          handleRender(e.getMessage());
        }
        break;
      default:
        handleRender("The file type is invalid");
    }
  }

  @Override
  public void saveAllHandler(String fileName, String fileType)
          throws IllegalStateException {
    try {
      new WriteMultiLayered()
              .writeFile(fileName, fileType, this.model.getImages(),
                      this.model.getInvisible());
    } catch (IllegalArgumentException e) {
      handleRender(e.getMessage());
    } catch (IOException io) {
      throw new IllegalStateException();
    }

  }

  @Override
  public void loadAllHandler(String filename) {
    try {
      IReadMultiLayered reader = new ReadMultiLayered();
      Map<String, ImageOfPixel> layers = reader.readImages(filename);
      List<String> visibility = reader.findVisibility();
      this.model.addMultipleImages(layers, visibility);
      this.view.setImage(this.getTopVisibleImage());
      for (String layer : this.model.getImages().keySet()) {
        this.view.addImage(layer);
      }
    } catch (IllegalArgumentException e) {
      handleRender(e.getMessage());
    }
  }

  @Override
  public void blurHandler() {
    if (this.selected == null) {
      handleRender("No image has been selected.");
    } else {
      try {
        this.model.replaceImage(selected, this.model.blur(selected));
        this.view.setImage(this.getTopVisibleImage());
        this.updateHistogram();
      } catch (IllegalArgumentException e) {
        handleRender(e.getMessage());
      }
    }
  }

  @Override
  public void sharpenHandler() {
    if (this.selected == null) {
      handleRender("No image has been selected.");
    } else {
      try {
        this.model.replaceImage(selected, this.model.sharpen(selected));
        this.view.setImage(this.getTopVisibleImage());
        this.updateHistogram();

      } catch (IllegalArgumentException e) {
        handleRender(e.getMessage());
      }
    }
  }

  @Override
  public void grayscaleHandler() {
    if (this.selected == null) {
      handleRender("No image has been selected.");
    } else {
      try {
        this.model.replaceImage(selected, this.model.grayscale(selected));
        this.view.setImage(this.getTopVisibleImage());
        this.updateHistogram();

      } catch (IllegalArgumentException e) {
        handleRender(e.getMessage());
      }
    }
  }

  @Override
  public void sepiaHandler() {
    if (this.selected == null) {
      handleRender("No image has been selected.");
    } else {
      try {
        this.model.replaceImage(selected, this.model.sepia(selected));
        this.view.setImage(this.getTopVisibleImage());
        this.updateHistogram();

      } catch (IllegalArgumentException e) {
        handleRender(e.getMessage());
      }
    }
  }

  @Override
  public void redComponentHandler() {
    if (this.selected == null) {
      handleRender("No image has been selected.");
    } else {
      try {
        this.model.replaceImage(selected, this.model.redGrayscale(selected));
        this.view.setImage(this.getTopVisibleImage());
        this.updateHistogram();

      } catch (IllegalArgumentException e) {
        handleRender(e.getMessage());
      }
    }
  }

  @Override
  public void greenComponentHandler() {
    if (this.selected == null) {
      handleRender("No image has been selected.");
    } else {
      try {
        this.model.replaceImage(selected, this.model.greenGrayscale(selected));
        this.view.setImage(this.getTopVisibleImage());
        this.updateHistogram();

      } catch (IllegalArgumentException e) {
        handleRender(e.getMessage());
      }
    }
  }

  @Override
  public void blueComponentHandler() {
    if (this.selected == null) {
      handleRender("No image has been selected.");
    } else {
      try {
        this.model.replaceImage(selected, this.model.blueGrayscale(selected));
        this.view.setImage(this.getTopVisibleImage());
        this.updateHistogram();

      } catch (IllegalArgumentException e) {
        handleRender(e.getMessage());
      }
    }
  }

  @Override
  public void valueHandler() {
    if (this.selected == null) {
      handleRender("No image has been selected.");
    } else {
      try {
        this.model.replaceImage(selected, this.model.valueComponent(selected));
        this.view.setImage(this.getTopVisibleImage());
        this.updateHistogram();

      } catch (IllegalArgumentException e) {
        handleRender(e.getMessage());
      }
    }
  }

  @Override
  public void intensityHandler() {
    if (this.selected == null) {
      handleRender("No image has been selected.");
    } else {
      try {
        this.model.replaceImage(selected, this.model.intensityComponent(selected));
        this.view.setImage(this.getTopVisibleImage());
        this.updateHistogram();

      } catch (IllegalArgumentException e) {
        handleRender(e.getMessage());
      }
    }
  }

  @Override
  public void brightenHandler(int val) {
    if (this.selected == null) {
      handleRender("No image has been selected.");
    } else {
      try {
        this.model.replaceImage(selected, this.model.brighten(selected, val));
        this.view.setImage(this.getTopVisibleImage());
        this.updateHistogram();

      } catch (IllegalArgumentException e) {
        handleRender(e.getMessage());
      }
    }
  }

  @Override
  public void darkenHandler(int val) {
    if (this.selected == null) {
      handleRender("No image has been selected.");
    } else {
      try {
        this.model.replaceImage(selected, this.model.darken(selected, val));
        this.view.setImage(this.getTopVisibleImage());
        this.updateHistogram();

      } catch (IllegalArgumentException e) {
        handleRender(e.getMessage());
      }
    }
  }

  @Override
  public void handleFlipVertical() {
    if (this.selected == null) {
      handleRender("No image has been selected.");
    } else {
      try {
        this.model.replaceImage(selected, this.model.verticalFlip(selected));
        this.view.setImage(this.getTopVisibleImage());
        this.updateHistogram();

      } catch (IllegalArgumentException e) {
        handleRender(e.getMessage());
      }
    }
  }

  @Override
  public void handleFlipHorizontal() {
    if (this.selected == null) {
      handleRender("No image has been selected.");
    } else {
      try {
        this.model.replaceImage(selected, this.model.horizontalFlip(selected));
        this.view.setImage(this.getTopVisibleImage());
        this.updateHistogram();

      } catch (IllegalArgumentException e) {
        handleRender(e.getMessage());
      }
    }
  }

  @Override
  public void show() {
    if (this.selected == null) {
      handleRender("No image has been selected.");
    } else {
      try {
        this.model.showImage(selected);
        this.view.setImage(this.getTopVisibleImage());
        this.updateHistogram();

      } catch (IllegalArgumentException e) {
        handleRender(e.getMessage());
      }
    }
  }

  @Override
  public void hide() {
    if (this.selected == null) {
      handleRender("No image has been selected.");
    } else {
      try {
        this.model.hideImage(selected);
        this.view.setImage(this.getTopVisibleImage());
      } catch (IllegalArgumentException e) {
        handleRender(e.getMessage());
      }
    }
  }

  @Override
  public void selectLayer(String id) {
    if (this.model.getImages().containsKey(id)) {
      this.selected = id;
      this.updateHistogram();
    } else {
      handleRender("This image does not exist");
    }
  }

  @Override
  public void removeLayer() {
    if (this.selected == null) {
      handleRender("No image has been selected.");
    } else {
      try {
        this.model.removeImage(this.selected);
        this.view.setImage(this.getTopVisibleImage());
      } catch (IllegalArgumentException e) {
        handleRender(e.getMessage());
      }
    }
  }

  @Override
  public void useScript(String filename) {
    try {
      new ImageProcessingController(this.model, new FileReader(filename), System.out)
              .run();
      this.view.setImage(this.getTopVisibleImage());
      for (String layer : this.model.getImages().keySet()) {
        this.view.addImage(layer);
      }
    } catch (IOException e) {
      handleRender("The script has failed to run.");
    }
  }

  /**
   * Retrieves the top visible layer of the model and returns a buffered image representation of it.
   * If no image is visible, then return a blank image with arbitrary dimensions.
   *
   * @return a representation of the layer through a buffered image
   */
  private BufferedImage getTopVisibleImage() {
    for (Map.Entry<String, ImageOfPixel> layer : this.model.getImages().entrySet()) {
      if (!this.model.getInvisible().contains(layer.getKey())) {
        return this.getBuffImage(layer.getValue());
      }
    }
    return new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
  }

  /**
   * Creates a buffered image of the given image.
   *
   * @param image image to be converted into a buffered image.
   * @return A buffered image representing the given image.
   */
  private BufferedImage getBuffImage(ImageOfPixel image) {
    int height = image.getPixels().size();
    int width = image.getPixels().get(0).size();

    BufferedImage outputImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int red = image.getPixels().get(i).get(j).getColor().getRed();
        int green = image.getPixels().get(i).get(j).getColor().getGreen();
        int blue = image.getPixels().get(i).get(j).getColor().getBlue();

        java.awt.Color c = new java.awt.Color(red, green, blue);
        outputImage.setRGB(j, i, c.getRGB());
      }
    }
    return outputImage;
  }

  /**
   * Handles the addition of the given image to the model under a specified name, as well as
   * updating the GUI view with the new layer of the image, which will result in a new top visible
   * image.
   *
   * @param imageName Name of the layer.
   * @param image     Image for the layer.
   */
  private void handleAdd(String imageName, ImageOfPixel image) {
    try {
      this.model.addImage(imageName, image);
      this.view.setImage(this.getTopVisibleImage());
      this.view.addImage(imageName);
    } catch (IllegalArgumentException e) {
      handleRender(e.getMessage());
    }
  }

  /**
   * Renders the given message into a popup in the GUI as a view.
   *
   * @param msg message to be rendered.
   */
  private void handleRender(String msg) {
    try {
      this.view.renderMessage(msg);
    } catch (IOException e) {
      throw new IllegalStateException();
    }
  }

  /**
   * Updates the histogram on the GUI view to correspond with the information for the currently
   * selected image.
   */
  private void updateHistogram() {
    this.view.updateGraph(this.model.getImage(selected).getLines());
  }
}
