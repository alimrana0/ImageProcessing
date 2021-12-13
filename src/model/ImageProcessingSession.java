package model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import model.imaging.Image;
import model.imaging.ImageOfPixel;
import model.imaging.pixel.IPixel;
import util.ImageUtil;

/**
 * Represents an image processing session where multiple images are loaded and manipulated.
 */
public class ImageProcessingSession {

  private HashMap<String, IImageProcessingModel> images;

  /**
   * Initializes a new image processing session by making a hashmap to store images that are loaded
   * and manipulated.
   */
  public ImageProcessingSession() {
    this.images = new HashMap<String, IImageProcessingModel>();
  }

  /**
   * Loads an image into the image processing session.
   *
   * @param filepath  The location and name of the file.
   * @param modelName The name the file is stored under during the session.
   */
  public void load(String filepath, String modelName) {

    String[] splitAtFormat = filepath.split("\\.", 2);
    if (splitAtFormat[1].equals("ppm")) {
      images.put(modelName,
              new ImageProcessingModel(new Image(ImageUtil.getPixels(filepath))));
    } else {
      images.put(modelName,
              new ImageProcessingModel(new Image(ImageUtil.readImage(filepath))));
    }
  }

  /**
   * Saves an image from the image processing session to a specified file output.
   *
   * @param saveLocation The name and location of the file to output.
   * @param modelName    The name of the image that will be saved.
   * @throws IOException if there is an issue writing to the output file.
   */
  public void save(String saveLocation, String modelName) throws IOException {
    if (this.images.containsKey(modelName)) {

      String[] splitAtFormat = saveLocation.split("\\.", 2);
      if (splitAtFormat[1].equals("ppm")) {
        this.images.get(modelName).saveImageAsPPM(saveLocation);
      } else {
        this.images.get(modelName).saveImageAs(saveLocation);
      }
    } else {
      throw new IllegalArgumentException("Invalid model name");
    }
  }

  /**
   * Stores a new image processing model with the specified greyscale component applied.
   *
   * @param component The name of the component to be applied.
   * @param modelName The name of the model in the hashmap to apply it to.
   * @param newName   The name of the created model to be stored in the hashmap.
   */
  public void getComponent(String component, String modelName, String newName) {
    if (this.images.containsKey(modelName)) {
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
        default:
          throw new IllegalArgumentException("invalid component");

      }

    } else {
      throw new IllegalArgumentException("invalid inputs");

    }

  }

  public void getComponent(String component, String modelName, String maskedName, String newName) {
    if (this.images.containsKey(modelName) && this.images.containsKey(maskedName)) {
      ImageOfPixel maskedImage = this.images.get(maskedName).returnMaskedImage();
      switch (component) {
        case "red":
          images.put(newName,
                  new ImageProcessingModel(this.images.get(modelName).redComponent(maskedImage)));
          break;

        case "green":
          images.put(newName,
                  new ImageProcessingModel(this.images.get(modelName).greenComponent(maskedImage)));
          break;

        case "blue":
          images.put(newName,
                  new ImageProcessingModel(this.images.get(modelName).blueComponent(maskedImage)));
          break;

        case "value":
          images.put(newName,
                  new ImageProcessingModel(this.images.get(modelName).valueImage(maskedImage)));
          break;

        case "luma":
          images.put(newName,
                  new ImageProcessingModel(this.images.get(modelName).luma(maskedImage)));
          break;

        case "intensity":
          images.put(newName,
                  new ImageProcessingModel(this.images.get(modelName).intensity(maskedImage)));
          break;
        default:
          throw new IllegalArgumentException("invalid component");

      }

    } else {
      throw new IllegalArgumentException("invalid inputs");

    }

  }

  /**
   * Given the name of the stored image and a name to save the manipulation this method makes a
   * horizontally flipped image.
   *
   * @param modelName Name of image to be flipped.
   * @param newName   What the flipped image is stored as.
   */
  public void horizontalFlip(String modelName, String newName) {
    if (this.images.containsKey(modelName)) {
      images.put(newName,
              new ImageProcessingModel(this.images.get(modelName).horizontalFlip()));
    } else {
      throw new IllegalArgumentException("invalid inputs");

    }
  }

  /**
   * Given the name of the stored image and a name to save the manipulation this method makes a
   * vertically flipped image.
   *
   * @param modelName Name of image to be flipped.
   * @param newName   What the flipped image is stored as.
   */
  public void verticalFlip(String modelName, String newName) {
    if (this.images.containsKey(modelName)) {
      images.put(newName,
              new ImageProcessingModel(this.images.get(modelName).verticalFlip()));
    } else {
      throw new IllegalArgumentException("invalid inputs");
    }
  }

  /**
   * Given a value, a name of a stored image, and the name to store the manipulated image under this
   * method creates a brightened version of the image.
   *
   * @param value     The value to brighten the image by.
   * @param modelName The image to be brightened.
   * @param newName   The name of the brightened image made.
   */
  public void brighten(int value, String modelName, String newName) {
    if (this.images.containsKey(modelName)) {
      images.put(newName,
              new ImageProcessingModel(this.images.get(modelName).brighten(value)));
    } else {
      throw new IllegalArgumentException("invalid inputs");
    }
  }

  public void brighten(int value, String modelName, String maskedName, String newName) {

    if (this.images.containsKey(modelName) && this.images.containsKey(maskedName)) {
      ImageOfPixel maskedImage = this.images.get(maskedName).returnMaskedImage();
      images.put(newName,
              new ImageProcessingModel(this.images.get(modelName).brighten(value, maskedImage)));
    } else {
      throw new IllegalArgumentException("invalid inputs");
    }
  }

  /**
   * Given a value, a name of a stored image, and the name to store the manipulated image under this
   * method creates a darkened version of the image.
   *
   * @param value     The value to darken the image by.
   * @param modelName The image to be darkened.
   * @param newName   The name of the darkened image made.
   */
  public void darken(int value, String modelName, String newName) {
    if (this.images.containsKey(modelName)) {
      images.put(newName,
              new ImageProcessingModel(this.images.get(modelName).darken(value)));
    } else {
      throw new IllegalArgumentException("invalid inputs");
    }
  }


  public void darken(int value, String modelName, String maskedName, String newName) {

    if (this.images.containsKey(modelName) && this.images.containsKey(maskedName)) {
      ImageOfPixel maskedImage = this.images.get(maskedName).returnMaskedImage();
      images.put(newName,
              new ImageProcessingModel(this.images.get(modelName).darken(value, maskedImage)));
    } else {
      throw new IllegalArgumentException("invalid inputs");
    }
  }

  /**
   * Given the name of the stored image and a name to save the manipulation this method makes a
   * blurred image.
   *
   * @param modelName Name of image to be blurred.
   * @param newName   What the blurred image is stored as.
   */
  public void blur(String modelName, String newName) {
    if (this.images.containsKey(modelName)) {
      images.put(newName,
              new ImageProcessingModel(this.images.get(modelName).blur()));
    } else {
      throw new IllegalArgumentException("invalid inputs");
    }
  }

  public void blur(String modelName, String maskedName, String newName) {
    ImageOfPixel maskedImage = this.images.get(maskedName).returnMaskedImage();
    if (this.images.containsKey(modelName) && this.images.containsKey(maskedName)) {
      images.put(newName,
              new ImageProcessingModel(this.images.get(modelName).blur(maskedImage)));
    } else {
      throw new IllegalArgumentException("invalid inputs");
    }
  }

  /**
   * Given the name of the stored image and a name to save the manipulation this method makes a
   * sharpened image.
   *
   * @param modelName Name of image to be sharpened.
   * @param newName   What the sharpened image is stored as.
   */
  public void sharpen(String modelName, String newName) {
    if (this.images.containsKey(modelName)) {
      images.put(newName,
              new ImageProcessingModel(this.images.get(modelName).sharpen()));
    } else {
      throw new IllegalArgumentException("invalid inputs");
    }
  }

  public void sharpen(String modelName, String maskedName, String newName) {
    if (this.images.containsKey(modelName) && this.images.containsKey(maskedName)) {
      images.put(newName,
              new ImageProcessingModel(this.images.get(modelName)
                      .sharpen(this.images.get(maskedName).returnMaskedImage())));
    } else {
      throw new IllegalArgumentException("invalid inputs");
    }
  }

  /**
   * Given the name of the stored image and a name to save the manipulation this method makes a
   * greyscale image.
   *
   * @param modelName Name of image to be Greyscaled.
   * @param newName   What the greyScaled image is stored as.
   */
  public void greyscale(String modelName, String newName) {
    if (this.images.containsKey(modelName)) {
      images.put(newName,
              new ImageProcessingModel(this.images.get(modelName).greyscale()));
    } else {
      throw new IllegalArgumentException("invalid inputs");
    }
  }

  public void greyscale(String modelName, String maskedName, String newName) {
    if (this.images.containsKey(modelName) && this.images.containsKey(maskedName)) {
      ImageOfPixel maskedImage = this.images.get(maskedName).returnMaskedImage();
      images.put(newName,
              new ImageProcessingModel(this.images.get(modelName).greyscale(maskedImage)));
    } else {
      throw new IllegalArgumentException("invalid inputs");
    }
  }

  /**
   * Given the name of the stored image and a name to save the manipulation this method makes an
   * image with sepia applied.
   *
   * @param modelName Name of image sepia is applied to.
   * @param newName   What the image after applying sepia is stored as.
   */
  public void sepia(String modelName, String newName) {
    if (this.images.containsKey(modelName)) {
      images.put(newName,
              new ImageProcessingModel(this.images.get(modelName).sepia()));
    } else {
      throw new IllegalArgumentException("invalid inputs");
    }
  }

  public void sepia(String modelName, String maskedName, String newName) {
    if (this.images.containsKey(modelName) && this.images.containsKey(maskedName)) {
      ImageOfPixel maskedImage = this.images.get(maskedName).returnMaskedImage();
      images.put(newName,
              new ImageProcessingModel(this.images.get(modelName).sepia(maskedImage)));
    } else {
      throw new IllegalArgumentException("invalid inputs");
    }
  }

  public boolean locateImage(String modelName) {
    return this.images.containsKey(modelName);
  }

  public void downscale(int newWidth, int newHeight, String modelName, String newName) {
    if (this.images.containsKey(modelName)) {
      images.put(newName,
              new ImageProcessingModel(this.images.get(modelName).downscale(newWidth, newHeight)));
    } else {
      throw new IllegalArgumentException("invalid inputs");
    }
  }
}
