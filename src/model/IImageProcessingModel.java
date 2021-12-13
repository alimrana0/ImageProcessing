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

  /**
   * Brightens a given image based on the pixels of the given masked image.
   * @param val value that the image will be brightened by.
   * @param maskedImage the black and white version of the model's image
   * @return the brightened image.
   * @throws IllegalArgumentException if an image is null.
   */
  ImageOfPixel brighten(int val, ImageOfPixel maskedImage) throws IllegalArgumentException;

  /**
   * Darkens a given image.
   * @param val value that the image will be darkened by.
   * @return the darkened image.
   * @throws IllegalArgumentException if the image is null
   */
  ImageOfPixel darken(int val) throws IllegalArgumentException;

  /**
   * Darkens a given image based on the pixels of the given masked image.
   * @param val value that the image will be darkened by.
   * @param maskedImage the black and white version of the model's image
   * @return the darkened image.
   * @throws IllegalArgumentException if an image is null.
   */
  ImageOfPixel darken(int val, ImageOfPixel maskedImage) throws IllegalArgumentException;

  /**
   * Greyscale an image based on the blue component.
   * @return the greyscale image.
   * @throws IllegalArgumentException if the image is null
   */
  ImageOfPixel blueComponent() throws IllegalArgumentException;

  /**
   * Greyscale an image based on the blue component based on the given masked image's pixels.
   * @param maskedImage the black and white version of the model's image
   * @return the greyscale image
   * @throws IllegalArgumentException if an image is null
   */
  ImageOfPixel blueComponent(ImageOfPixel maskedImage) throws IllegalArgumentException;


  /**
   * Greyscale an image based on the red component.
   * @return the greyscale image.
   * @throws IllegalArgumentException if the image is null
   */
  ImageOfPixel redComponent() throws IllegalArgumentException;

  /**
   * Greyscale an image based on the red component based on the given masked image's pixels.
   * @param maskedImage the black and white version of the model's image
   * @return the greyscale image
   * @throws IllegalArgumentException if an image is null
   */
  ImageOfPixel redComponent(ImageOfPixel maskedImage) throws IllegalArgumentException;

  /**
   * Greyscale an image based on the green component.
   * @return the greyscale image.
   * @throws IllegalArgumentException if the image is null
   */
  ImageOfPixel greenComponent() throws IllegalArgumentException;

  /**
   * Greyscale an image based on the green component based on the given masked image's pixels.
   * @param maskedImage the black and white version of the model's image
   * @return the greyscale image
   * @throws IllegalArgumentException if an image is null
   */
  ImageOfPixel greenComponent(ImageOfPixel maskedImage) throws IllegalArgumentException;

  /**
   * Alters an image's intensity.
   * @return The intensified image.
   * @throws IllegalArgumentException if image is null
   */
  ImageOfPixel intensity() throws IllegalArgumentException;

  /**
   * Alterns an image's intensity based on the given masked image's pixels.
   * @param maskedImage the black and white version of the model's image
   * @return the intensified image.
   * @throws IllegalArgumentException if an image is null
   */
  ImageOfPixel intensity(ImageOfPixel maskedImage) throws IllegalArgumentException;

  /**
   * Alters an image's pixels' value.
   * @return The image with changed values.
   * @throws IllegalArgumentException if image is null
   */
  ImageOfPixel valueImage() throws IllegalArgumentException;

  /**
   * Alterns an image's value based on the given masked image's pixels.
   * @param maskedImage the black and white version of the model's image
   * @return the value changed image.
   * @throws IllegalArgumentException if an image is null
   */
  ImageOfPixel valueImage(ImageOfPixel maskedImage) throws IllegalArgumentException;

  /**
   * Greyscale an image based on the luma of the components.
   * @return the greyscale image.
   * @throws IllegalArgumentException if the image is null
   */
  ImageOfPixel luma() throws IllegalArgumentException;

  /**
   * Greyscale an image based on luma values; this is based on the given masked image's pixels.
   * @param maskedImage the black and white version of the model's image
   * @return the greyscale image
   * @throws IllegalArgumentException if an image is null
   */
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

  /**
   * Filters the image by blurring the image based on the pixels of the given masked image.
   * @param maskedImage the black and white version of the given model's image.
   * @return the blurred image.
   * @throws IllegalArgumentException if an image is null.
   */
  ImageOfPixel blur(ImageOfPixel maskedImage) throws IllegalArgumentException;

  /**
   * Filters the image by sharpening the image.
   * @return The sharpened image.
   */
  ImageOfPixel sharpen() throws IllegalArgumentException;

  /**
   * Filters the image by sharpening the image based on the pixels of the given masked image.
   * @param maskedImage the black and white version of the given model's image.
   * @return the sharpened image.
   * @throws IllegalArgumentException if an image is null.
   */
  ImageOfPixel sharpen(ImageOfPixel maskedImage) throws IllegalArgumentException;

  /**
   * Transforms the image into a sepia colored image.
   * @return The transformed image.
   */
  ImageOfPixel sepia() throws IllegalArgumentException;

  /**
   * Creates a sepia version of the image based on the masked image's pixels.
   * @param maskedImage the black and white version of the model's image.
   * @return the sepia version of the image.
   * @throws IllegalArgumentException if an image is null.
   */
  ImageOfPixel sepia(ImageOfPixel maskedImage) throws IllegalArgumentException;

  /**
   * Greyscale an image by using a matrix for component conversion.
   *
   * @return the greyscale image.
   */
  ImageOfPixel greyscale() throws IllegalArgumentException;

  /**
   * Greyscale an image based on the given masked image's pixels.
   * @param maskedImage the black and white version of the model's image
   * @return the greyscale image
   * @throws IllegalArgumentException if an image is null
   */
  ImageOfPixel greyscale(ImageOfPixel maskedImage) throws IllegalArgumentException;

  /**
   * Saves a file of the given filename as a PPM file.
   * @param filename the file name of the image
   * @throws IOException if the filename is invalid
   */
  void saveImageAsPPM(String filename) throws IOException;

  /**
   * Saves an image as a file other than PPM.
   * @param outputName the name of the file being created.
   * @throws IOException if the file creation processes fails.
   */
  void saveImageAs(String outputName) throws IOException;

  /**
   * Replicated a model's image and returns it.
   * @return a copy of the model's image.
   * @throws IllegalArgumentException if the image is null.
   */
  ImageOfPixel returnMaskedImage() throws IllegalArgumentException;

  /**
   * Downscales a given image to the given width and height.
   * @param newWidth the width of the target image.
   * @param newHeight the height of the target image.
   * @return the image downscaled to the given dimensions
   * @throws IllegalArgumentException if an image is null.
   */
  ImageOfPixel downscale(int newWidth, int newHeight) throws IllegalArgumentException;

}
