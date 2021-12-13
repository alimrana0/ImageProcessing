package controller;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import model.ImageProcessingSession;
import view.IImageProcessingView;


/**
 * Represents an Image Controller that is used to run the image processing application given a view
 * and readable object.
 */
public class ImageControllerImpl implements ImageController {

  private IImageProcessingView view;
  private Scanner in;
  private String filepath;
  private String firstImgName;
  private String secondImgName;
  private String thirdImgName;
  private ImageProcessingSession model;

  /**
   * Creates a new ImageControllerImpl given a session, view and readable object. Given a variety of
   * commands such as load, save, get-component (component), horizontal-flip, vertical-flip,
   * brighten, darken, and q to quit the controller creates and manipulates images.
   *
   * @param input The input of the application that is used to read in commands.
   * @throws IllegalArgumentException input is null.
   */
  public ImageControllerImpl(ImageProcessingSession model, IImageProcessingView view,
      Readable input)
      throws IllegalArgumentException {
    if (model == null || view == null || input == null) {
      throw new IllegalArgumentException("Parameters for controller cannot be null");
    }

    this.in = new Scanner(input);
    //initial view has no model, needed to display intro message
    //Not being used yet from rendering messages
    this.view = view;
    this.model = model;
  }

  /**
   * Runs the controller that handles the Processing session, view, and input to conduct the Image
   * Processing.
   *
   * @throws IOException if given inputs fail to transmit inside the Image Processor.
   */
  @Override
  public void run() throws IOException {
    boolean quit = false;



    while (!quit) {

      //accept user input
      String option = in.next();

      switch (option) {
        //loads an image from the given filepath and places it into the hashmap with the given key.
        case "load":
          this.readFilepathAndModelName();
          try {
            this.model.load(this.filepath, this.firstImgName);
            this.view.renderMessage("\nImage Loaded");

          } catch (IllegalArgumentException e) {
            this.view.renderMessage("\n" + e.getMessage());
          }

          break;

        //saves an image with the same name as the one given from the hashmap as a PPM file.
        case "save":
          this.readFilepathAndModelName();
          try {
            this.model.save(this.filepath, this.firstImgName);
            this.view.renderMessage("\nImage saved");
          } catch (IllegalArgumentException e) {
            this.view.renderMessage("\n" + e.getMessage());
          }

          break;

        //Creates a new image after greyscaling and obtaining the specified component,
        // then stores it in the hashmap.
        case "get-component":
          String component = in.next();
          this.readModelNameAndNewName();
          if(!model.locateImage(secondImgName)) {
              try {
                this.model.getComponent(component, this.firstImgName, this.secondImgName);
                this.view.renderMessage("\nComponent Image made");
              } catch (IllegalArgumentException e) {
                this.view.renderMessage("\n" + e.getMessage());

            }
              break;
          }
          this.thirdImgName = in.next();
          try {
            this.model.getComponent(component, this.firstImgName, this.secondImgName, this.thirdImgName);
            this.view.renderMessage("\nComponent Image made");
          } catch (IllegalArgumentException e) {
            this.view.renderMessage("\n" + e.getMessage());
          }
          break;

        //Creates a new image after blurring
        // then stores it in the hashmap.
        case "blur":
          this.readModelNameAndNewName();
          if(!model.locateImage(secondImgName)) {
              try {
                this.model.blur(this.firstImgName, this.secondImgName);
                this.view.renderMessage("\nBlurred Image Made");
              } catch (IllegalArgumentException e) {
                this.view.renderMessage("\n" + e.getMessage());
              }
              break;
          }
          this.thirdImgName = in.next();
          try {
            this.model.blur(this.firstImgName, this.secondImgName, this.thirdImgName);
            this.view.renderMessage("\nBlurred Image from mask made");
          } catch (IllegalArgumentException e) {
            this.view.renderMessage("\n" + e.getMessage());
          }
          break;

        //Creates a new image after sharpening
        // then stores it in the hashmap.
        case "sharpen":
          this.readModelNameAndNewName();
          if(!model.locateImage(secondImgName)) {
              try {
                this.model.sharpen(this.firstImgName, this.secondImgName);
                this.view.renderMessage("\nSharpened Image Made");
              } catch (IllegalArgumentException e) {
                this.view.renderMessage("\n" + e.getMessage());
              }
              break;
          }
          this.thirdImgName = in.next();
          try {
            this.model.sharpen(this.firstImgName, this.secondImgName, this.thirdImgName);
            this.view.renderMessage("\nSharpened Image from mask made");
          } catch (IllegalArgumentException e) {
            this.view.renderMessage("\n" + e.getMessage());
          }
          break;

        //Creates a new image after greyscale
        // then stores it in the hashmap.
        case "greyscale":
          this.readModelNameAndNewName();
          if(!model.locateImage(secondImgName)) {
              try {
                this.model.greyscale(this.firstImgName, this.secondImgName);
                this.view.renderMessage("\nGreyscaled Image Made");
              } catch (IllegalArgumentException e) {
                this.view.renderMessage("\n" + e.getMessage());
              }
              break;
            }
          this.thirdImgName = in.next();
          try {
            this.model.greyscale(this.firstImgName, this.secondImgName, this.thirdImgName);
            this.view.renderMessage("\nGreyscaled Mask Image Made");
          } catch (IllegalArgumentException e) {
            this.view.renderMessage("\n" + e.getMessage());
          }
          break;

        //Creates a new image after sepia
        // then stores it in the hashmap.
        case "sepia":
          this.readModelNameAndNewName();
          if(!model.locateImage(secondImgName)) {
              try {
                this.model.sepia(this.firstImgName, this.secondImgName);
                this.view.renderMessage("\nSepia Image Made");
              } catch (IllegalArgumentException e) {
                this.view.renderMessage("\n" + e.getMessage());
              }
              break;
          }
            this.thirdImgName = in.next();
            try {
              this.model.sepia(this.firstImgName, this.secondImgName, this.thirdImgName);
              this.view.renderMessage("\nSepia Image Mask Made");
            } catch (IllegalArgumentException e) {
              this.view.renderMessage("\n" + e.getMessage());
            }
          break;

        //Creates a new image after horizontally flipping the image of the given name and
        //then stores it in the hashmap
        case "horizontal-flip":
          this.readModelNameAndNewName();
          try {
            this.model.horizontalFlip(this.firstImgName, this.secondImgName);
            this.view.renderMessage("\nHorizontal Image made");
          } catch (IllegalArgumentException e) {
            this.view.renderMessage("\n" + e.getMessage());
          }
          break;

        //Creates a new image after vertically flipping the image of the given name and
        //then stores it in the hashmap
        case "vertical-flip":
          this.readModelNameAndNewName();
          try {
            this.model.verticalFlip(this.firstImgName, this.secondImgName);
            this.view.renderMessage("\nVertical Image made");
          } catch (IllegalArgumentException e) {
            this.view.renderMessage("\n" + e.getMessage());
          }
          break;

        //Creates a new image after brightening the image of the given name and
        //then stores it in the hashmap
        case "brighten":
          int brightenValue;
          try {
            brightenValue = Integer.parseInt(in.next());
          } catch (NumberFormatException e) {
            this.view.renderMessage("\nMust enter an integer");
            break;
          }
          this.readModelNameAndNewName();
          if(!model.locateImage(secondImgName)) {
              try {
                this.model.brighten(brightenValue, this.firstImgName, this.secondImgName);
                this.view.renderMessage("\nBrightened Image made");
              } catch (IllegalArgumentException e) {
                this.view.renderMessage("\n" + e.getMessage());
              }
              break;
          }
          this.thirdImgName = in.next();
          try {
            this.model.brighten(brightenValue, this.firstImgName, this.secondImgName, this.thirdImgName);
            this.view.renderMessage("\nBrightened Image from mask made");
          } catch (IllegalArgumentException e) {
            this.view.renderMessage("\n" + e.getMessage());
          }

          break;

        //Creates a new image after darkening the image of the given name and
        //then stores it in the hashmap
        case "darken":
          int darkenValue;
          try {
            darkenValue = Integer.parseInt(in.next());
          } catch (NumberFormatException e) {
            this.view.renderMessage("\nMust enter an integer");
            break;
          }
          this.readModelNameAndNewName();
          if(!model.locateImage(secondImgName)) {
              try {
                this.model.darken(darkenValue, this.firstImgName, this.secondImgName);
                this.view.renderMessage("\nDarkened Image made");
              } catch (IllegalArgumentException e) {
                this.view.renderMessage("\n" + e.getMessage());
              }
              break;
          }
          this.thirdImgName = in.next();
          try {
            this.model.darken(darkenValue, this.firstImgName, this.secondImgName, this.thirdImgName);
            this.view.renderMessage("\nDarkened Image from mask made");
          } catch (IllegalArgumentException e) {
            this.view.renderMessage("\n" + e.getMessage());
          }
          break;
        case "downscale":
          int newWidth;
          int newHeight;
          try {
            newWidth = Integer.parseInt(in.next());
            newHeight = Integer.parseInt(in.next());
          }
          catch (NumberFormatException e) {
            this.view.renderMessage("\nMust enter an integer");
            break;
          }
          this.readModelNameAndNewName();
          try {
            this.model.downscale(newWidth, newHeight, this.firstImgName, this.secondImgName);
            this.view.renderMessage("\nImage made from downscale");
          } catch (IllegalArgumentException e) {
            this.view.renderMessage("\n" + e.getMessage());
          }
          break;

        //runs the controller based off of a script
        case "-file":
          String fileName = in.next();
          File file = new File(fileName);
          this.in = new Scanner(file);
          break;



        case "Q":
        case "q":
          quit = true;
          break;
        default:
          view.renderMessage("\nUnknown Command Entered");
      }
    }

  }

  /**
   * Reads in the filepath and the model name. Used in load and save switch cases.
   */
  private void readFilepathAndModelName() {
    this.filepath = in.next();
    this.firstImgName = in.next();

  }

  /**
   * Reads in the model name and the new model name. Used in image manipulation switch cases.
   */
  private void readModelNameAndNewName() {
    this.firstImgName = in.next();
    this.secondImgName = in.next();
  }


}
