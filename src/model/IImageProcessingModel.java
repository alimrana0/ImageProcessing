package model;

import filters.FlippingTransformation.FlipHorizontal;
import java.io.IOException;
import model.Imaging.ImageOfPixel;
import model.Imaging.ImageOfPixel;

public interface IImageProcessingModel {

  ImageOfPixel brighten(int val) throws IllegalArgumentException;

  ImageOfPixel darken(int val) throws IllegalArgumentException;

  ImageOfPixel blueComponent() throws IllegalArgumentException;

  ImageOfPixel redComponent() throws IllegalArgumentException;

  ImageOfPixel greenComponent() throws IllegalArgumentException;

  ImageOfPixel intensity() throws IllegalArgumentException;

  ImageOfPixel valueImage() throws IllegalArgumentException;

  ImageOfPixel luma() throws IllegalArgumentException;

  public ImageOfPixel horizontalFlip() throws IllegalArgumentException;

  public ImageOfPixel verticalFlip() throws IllegalArgumentException;

    void saveImageAsPPM(String filename) throws IOException;

}
