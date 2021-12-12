package model;

import java.io.IOException;

import model.imaging.Image;
import model.imaging.ImageOfPixel;

/**
 * Interface to represent an image processing model. Contains transformation methods and a
 * saving images as ppm method.
 */
public interface IImageProcessingModel {

  /**
   * Brightens a given image.
   * @param val value that the image will be brightened by.
   * @return the brightened image.
   * @throws IllegalArgumentException if the image is null
   */
  ImageOfPixel brighten(int val) throws IllegalArgumentException;

  ImageOfPixel brighten(int val, ImageOfPixel maskedImage) throws IllegalArgumentException;

  /**
   * Darkens a given image.
   * @param val value that the image will be darkened by.
   * @return the darkened image.
   * @throws IllegalArgumentException if the image is null
   */
  ImageOfPixel darken(int val) throws IllegalArgumentException;

  ImageOfPixel darken(int val, ImageOfPixel maskedImage) throws IllegalArgumentException;

  /**
   * Greyscale an image based on the blue component.
   * @return the greyscale image.
   * @throws IllegalArgumentException if the image is null
   */
  ImageOfPixel blueComponent() throws IllegalArgumentException;

  ImageOfPixel blueComponent(ImageOfPixel maskedImage) throws IllegalArgumentException;


  /**
   * Greyscale an image based on the red component.
   * @return the greyscale image.
   * @throws IllegalArgumentException if the image is null
   */
  ImageOfPixel redComponent() throws IllegalArgumentException;

  ImageOfPixel redComponent(ImageOfPixel maskedImage) throws IllegalArgumentException;

  /**
   * Greyscale an image based on the green component.
   * @return the greyscale image.
   * @throws IllegalArgumentException if the image is null
   */
  ImageOfPixel greenComponent() throws IllegalArgumentException;

  ImageOfPixel greenComponent(ImageOfPixel maskedImage) throws IllegalArgumentException;

  /**
   * Alters an image's intensity.
   * @return The intensified image.
   * @throws IllegalArgumentException if image is null
   */
  ImageOfPixel intensity() throws IllegalArgumentException;

  ImageOfPixel intensity(ImageOfPixel maskedImage) throws IllegalArgumentException;

  /**
   * Alters an image's pixels' value.
   * @return The image with changed values.
   * @throws IllegalArgumentException if image is null
   */
  ImageOfPixel valueImage() throws IllegalArgumentException;

  ImageOfPixel valueImage(ImageOfPixel maskedImage) throws IllegalArgumentException;

  /**
   * Greyscale an image based on the luma of the components.
   * @return the greyscale image.
   * @throws IllegalArgumentException if the image is null
   */
  ImageOfPixel luma() throws IllegalArgumentException;

  ImageOfPixel luma(ImageOfPixel maskedImage) throws IllegalArgumentException;

  /**
   * Flips the image horizontally.
   * @return The horizontally flipped image.
   * @throws IllegalArgumentException if the image is null.
   */
  ImageOfPixel horizontalFlip() throws IllegalArgumentException;

  /**
   * Flips the image vertically.
   * @return The vertically flipped image.
   * @throws IllegalArgumentException if the image is null.
   */
  ImageOfPixel verticalFlip() throws IllegalArgumentException;

  /**
   * Filters the image by blurring the image.
   * @return The blurred image.
   */
  ImageOfPixel blur() throws IllegalArgumentException;

  ImageOfPixel blur(ImageOfPixel maskedImage) throws IllegalArgumentException;

  /**
   * Filters the image by sharpening the image.
   * @return The sharpened image.
   */
  ImageOfPixel sharpen() throws IllegalArgumentException;

  ImageOfPixel sharpen(ImageOfPixel maskedImage) throws IllegalArgumentException;

  /**
   * Transforms the image into a sepia colored image.
   * @return The transformed image.
   */
  ImageOfPixel sepia() throws IllegalArgumentException;

  ImageOfPixel sepia(ImageOfPixel maskedImage) throws IllegalArgumentException;

  /**
   * Greyscale an image by using a matrix for component conversion.
   *
   * @return the greyscale image.
   */
  ImageOfPixel greyscale() throws IllegalArgumentException;

  ImageOfPixel greyscale(ImageOfPixel maskedImage) throws IllegalArgumentException;

  /**
   * Saves a file of the given filename as a PPM file.
   * @param filename the file name of the image
   * @throws IOException if the filename is invalid
   */
  void saveImageAsPPM(String filename) throws IOException;

  void saveImageAs(String outputName) throws IOException;

  ImageOfPixel returnMaskedImage() throws IllegalArgumentException;

  ImageOfPixel downscale(int newWidth, int newHeight) throws IllegalArgumentException;

}
