package model;

import filters.ColorTransformation.Greyscale.BlueGreyscale;
import filters.ColorTransformation.Greyscale.GreenGreyscale;
import filters.ColorTransformation.Greyscale.LumaGreyscale;
import filters.ColorTransformation.Greyscale.RedGreyscale;
import filters.ColorTransformation.IntensityChange;
import filters.ColorTransformation.ValueChange;
import filters.FlippingTransformation.FlipHorizontal;
import filters.FlippingTransformation.FlipVertical;
import filters.IntensityTransformation.BrightenTransformation;
import filters.IntensityTransformation.DarkenTransformation;
import java.io.IOException;
import model.Imaging.ImageOfPixel;

/**
 * Class representing a model for an ImageProcessor.
 */
public class ImageProcessingModel implements IImageProcessingModel {

  private ImageOfPixel image;

  /**
   * Constructor for an image processing model that uses a given image to set its image field.
   * @param image
   */
  public ImageProcessingModel(ImageOfPixel image) {
    this.image = image;
  }

  /**
   * Brightens a given image.
   * @param val value that the image will be brightened by.
   * @return the brightened image.
   * @throws IllegalArgumentException if the image is null
   */
  @Override
  public ImageOfPixel brighten(int val) throws IllegalArgumentException {
    if (image == null) {
      throw new IllegalArgumentException("Image cannot be null");
    }
    return new BrightenTransformation().applyTransformation(this.image, val);
  }

  /**
   * Darkens a given image.
   * @param val value that the image will be darkened by.
   * @return the darkened image.
   * @throws IllegalArgumentException if the image is null
   */
  @Override
  public ImageOfPixel darken(int val) throws IllegalArgumentException {
    if (image == null) {
      throw new IllegalArgumentException("Image cannot be null");
    }
    return new DarkenTransformation().applyTransformation(this.image, val);
  }

  /**
   * Greyscale an image based on the red component.
   * @return the greyscale image.
   * @throws IllegalArgumentException if the image is null
   */
  @Override
  public ImageOfPixel redComponent() throws IllegalArgumentException {
    if (image == null) {
      throw new IllegalArgumentException("Image cannot be null");
    }
    return new RedGreyscale().applyColorTransformation(this.image);
  }


  /**
   * Greyscale an image based on the green component.
   * @return the greyscale image.
   * @throws IllegalArgumentException if the image is null
   */
  @Override
  public ImageOfPixel greenComponent() throws IllegalArgumentException {
    if (image == null) {
      throw new IllegalArgumentException("Image cannot be null");
    }
    return new GreenGreyscale().applyColorTransformation(this.image);
  }

  /**
   * Greyscale an image based on the blue component.
   * @return the greyscale image.
   * @throws IllegalArgumentException if the image is null
   */
  @Override
  public ImageOfPixel blueComponent() throws IllegalArgumentException {
    if (image == null) {
      throw new IllegalArgumentException("Image cannot be null");
    }
    return new BlueGreyscale().applyColorTransformation(this.image);
  }

  /**
   * Alters an image's intensity.
   * @return The intensified image.
   * @throws IllegalArgumentException if image is null
   */
  @Override
  public ImageOfPixel intensity() throws IllegalArgumentException {
    if (image == null) {
      throw new IllegalArgumentException("Image cannot be null");
    }
    return new IntensityChange().applyColorTransformation(this.image);
  }

  /**
   * Alters an image's pixels' value.
   * @return The image with changed values.
   * @throws IllegalArgumentException if image is null
   */
  @Override
  public ImageOfPixel valueImage() throws IllegalArgumentException {
    if (image == null) {
      throw new IllegalArgumentException("Image cannot be null");
    }
    return new ValueChange().applyColorTransformation(this.image);
  }

  /**
   * Greyscale an image based on the luma of the components.
   * @return the greyscale image.
   * @throws IllegalArgumentException if the image is null
   */
  @Override
  public ImageOfPixel luma() throws IllegalArgumentException {
    if (image == null) {
      throw new IllegalArgumentException("Image cannot be null");
    }
    return new LumaGreyscale().applyColorTransformation(this.image);
  }

  /**
   * Saves a file of the given filename as a PPM file.
   * @param filename the file name of the image
   * @throws IOException if the filename is invalid
   */
  @Override
  public void saveImageAsPPM(String filename) throws IOException {
    this.image.saveImageAsPPM(filename);
  }

  /**
   * Flips the image horizontally.
   * @return The horizontally flipped image.
   * @throws IllegalArgumentException if the image is null.
   */
  @Override
  public ImageOfPixel horizontalFlip() throws IllegalArgumentException {
    if (image == null) {
      throw new IllegalArgumentException("Image cannot be null");
    }

    return new FlipHorizontal().flipTransform(this.image);
  }

  /**
   * Flips the image vertically.
   * @return The vertically flipped image.
   * @throws IllegalArgumentException if the image is null.
   */
  @Override
  public ImageOfPixel verticalFlip() throws IllegalArgumentException {
    if (image == null) {
      throw new IllegalArgumentException("Image cannot be null");
    }

    return new FlipVertical().flipTransform(this.image);
  }

}
