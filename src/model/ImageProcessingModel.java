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

public class ImageProcessingModel implements IImageProcessingModel {

  private ImageOfPixel image;

  public ImageProcessingModel(ImageOfPixel image) {
    this.image = image;
  }

  @Override
  public ImageOfPixel brighten(int val) throws IllegalArgumentException {
    if (image == null) {
      throw new IllegalArgumentException("Image cannot be null");
    }
    return new BrightenTransformation().applyTransformation(this.image, val);
  }

  @Override
  public ImageOfPixel darken(int val) throws IllegalArgumentException {
    if (image == null) {
      throw new IllegalArgumentException("Image cannot be null");
    }
    return new DarkenTransformation().applyTransformation(this.image, val);
  }
  @Override
  public ImageOfPixel redComponent() throws IllegalArgumentException {
    if (image == null) {
      throw new IllegalArgumentException("Image cannot be null");
    }
    return new RedGreyscale().applyColorTransformation(this.image);
  }


  @Override
  public ImageOfPixel greenComponent() throws IllegalArgumentException {
    if (image == null) {
      throw new IllegalArgumentException("Image cannot be null");
    }
    return new GreenGreyscale().applyColorTransformation(this.image);
  }

  @Override
  public ImageOfPixel blueComponent() throws IllegalArgumentException {
    if (image == null) {
      throw new IllegalArgumentException("Image cannot be null");
    }
    return new BlueGreyscale().applyColorTransformation(this.image);
  }

  @Override
  public ImageOfPixel intensity() throws IllegalArgumentException {
    if (image == null) {
      throw new IllegalArgumentException("Image cannot be null");
    }
    return new IntensityChange().applyColorTransformation(this.image);
  }

  @Override
  public ImageOfPixel valueImage() throws IllegalArgumentException {
    if (image == null) {
      throw new IllegalArgumentException("Image cannot be null");
    }
    return new ValueChange().applyColorTransformation(this.image);
  }

  @Override
  public ImageOfPixel luma() throws IllegalArgumentException {
    if (image == null) {
      throw new IllegalArgumentException("Image cannot be null");
    }
    return new LumaGreyscale().applyColorTransformation(this.image);
  }

  @Override
  public void saveImageAsPPM(String filename) throws IOException {
    this.image.saveImageAsPPM(filename);
  }

  @Override
  public ImageOfPixel horizontalFlip() throws IllegalArgumentException {
    if (image == null) {
      throw new IllegalArgumentException("Image cannot be null");
    }

    return new FlipHorizontal().flipTransform(this.image);
  }

  @Override
  public ImageOfPixel verticalFlip() throws IllegalArgumentException {
    if (image == null) {
      throw new IllegalArgumentException("Image cannot be null");
    }

    return new FlipVertical().flipTransform(this.image);
  }

}
