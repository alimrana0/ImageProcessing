package controller;


import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import model.IImageProcessingModel;
import model.ImageProcessingModel;
import model.ImageUtil;
import model.Imaging.Image;
import view.IImageProcessingView;


/**
 * Represents an Image Controller that is used to run the image processing application given a view
 * and readable object.
 */
public class ImageControllerImpl implements ImageController {

  private IImageProcessingView view;
  private Scanner in;
  private HashMap<String, IImageProcessingModel> images;
  String filepath;
  String modelName;
  String newName;

  /**
   * Creates a new ImageControllerImpl given a view and readable object. Given a variety of commands
   * such as load, save, get-component (component), horizontal-flip, vertical-flip, brighten,
   * darken, and q to quite the controller creates and manipulates images.
   *
   * @param view  The view of the application that is in control of displaying messages.
   * @param input The input of the application that is used to read in commands.
   * @throws IllegalArgumentException when view or input are null.
   */
  public ImageControllerImpl(IImageProcessingView view, Readable input)
      throws IllegalArgumentException {
    if (view == null || input == null) {
      throw new IllegalArgumentException("Parameters for controller cannot be null");
    }
  }


  @Override
  public void run() throws IOException {
    boolean quit = false;
    while (!quit) {
      //tell view to show options
      view.showOptions();
      //accept user input
      String option = in.next();

      switch (option) {
        //loads an image from the given filepath and places it into the hashmap with the given key.
        case "load":
          this.readFilepathAndModelName();
          images.put(modelName,
              new ImageProcessingModel(new Image(ImageUtil.getPixels(this.filepath))));
          break;

        //saves an image with the same name as the one given from the hashmap as a PPM file.
        case "save":
          this.readFilepathAndModelName();
          if (this.images.containsKey(modelName)) {
            try {
              this.images.get(modelName).saveImageAsPPM(filepath);
            } catch (IOException e) {
              this.view.renderMessage(e.toString());
            }
          } else {
            throw new IllegalArgumentException("Invalid name");
          }
          break;

        //Creates a new image after greyscaling and obtaining the specified component,
        // then stores it in the hashmap.
        case "get-component":
          String component = in.next();
          this.readModelNameAndNewName();
          if (!this.images.containsKey(modelName)) {
            this.view.renderMessage("Invalid name");
            break;
          }
          this.getComponent(component, modelName, newName);
          break;

        //Creates a new image after horizontally flipping the image of the given name and
        //then stores it in the hashmap
        case "horizontal-flip":
          this.readModelNameAndNewName();
          if (!this.images.containsKey(modelName)) {
            this.view.renderMessage("Invalid name");
            break;
          }
          images.put(newName,
              new ImageProcessingModel(this.images.get(modelName).horizontalFlip()));
          break;

        //Creates a new image after vertically flipping the image of the given name and
        //then stores it in the hashmap
        case "vertical-flip":
          this.readModelNameAndNewName();
          if (!this.images.containsKey(modelName)) {
            this.view.renderMessage("Invalid name");
            break;
          }
          images.put(newName,
              new ImageProcessingModel(this.images.get(modelName).verticalFlip()));
          break;

        //Creates a new image after brightening the image of the given name and
        //then stores it in the hashmap
        case "brighten":
          this.readModelNameAndNewName();
          if (!this.images.containsKey(modelName)) {
            this.view.renderMessage("Invalid name");
            break;
          }
          int brightenValue = Integer.parseInt(in.next());
          images.put(newName,
              new ImageProcessingModel(this.images.get(modelName).brighten(brightenValue)));
          break;

        //Creates a new image after darkening the image of the given name and
        //then stores it in the hashmap
        case "darken":
          this.readModelNameAndNewName();
          if (!this.images.containsKey(modelName)) {
            this.view.renderMessage("Invalid name");
            break;
          }

          int darkenValue = Integer.parseInt(in.next());
          images.put(newName,
              new ImageProcessingModel(this.images.get(modelName).darken(darkenValue)));
          break;

        case "Q":
        case "q":
          quit = true;
          break;
        default:
          view.renderMessage("Unknown Command Entered");
      }
    }
  }

  /**
   * Returns a new image processing model with the specified greyscale component applied.
   *
   * @param component The name of the component to be applied.
   * @param modelName The name of the model in the hashmap to apply it to.
   * @param newName The name of the created model to be stored in the hashmap.
   */
  private void getComponent(String component, String modelName, String newName) {
    switch (component) {

      case "red":
        images.put(newName,
            new ImageProcessingModel(this.images.get(modelName).redComponent()));
        break;

      case "green":
        images.put(newName,
            new ImageProcessingModel(this.images.get(modelName).greenComponent()));
        break;

      case "blue":
        images.put(newName,
            new ImageProcessingModel(this.images.get(modelName).blueComponent()));
        break;

      case "value":
        images.put(newName,
            new ImageProcessingModel(this.images.get(modelName).valueImage()));
        break;

      case "luma":
        images.put(newName,
            new ImageProcessingModel(this.images.get(modelName).luma()));
        break;

      case "intensity":
        images.put(newName,
            new ImageProcessingModel(this.images.get(modelName).intensity()));
        break;
    }
  }

  /**
   * Reads in the filepath and the model name.
   * Used in load and save switch cases.
   */
  private void readFilepathAndModelName() {
    this.filepath = in.next();
    this.modelName = in.next();

  }

  /**
   * Reads in the model name and the new model name.
   * Used in image manipulation switch cases.
   */
  private void readModelNameAndNewName() {
    this.modelName = in.next();
    this.newName = in.next();
  }

}

