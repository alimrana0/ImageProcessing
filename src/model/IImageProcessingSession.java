package model;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import model.imaging.ImageOfPixel;

import java.util.List;
import java.util.Map;
import model.imaging.pixel.IPixel;

/**
 * Interface representing the model session for an image processor that is dealing with multiple
 * layered images. It can add/remove/replace and perform manipulations on the image contents as
 * well as image visibility.
 */
public interface IImageProcessingSession {

  /**
   * Performs a blur transformation on the image associated with the given ID.
   *
   * @param id image's ID being filtered.
   * @return The filtered image.
   * @throws IllegalArgumentException If ID is null or session is invalid.
   */
  ImageOfPixel blur(String id) throws IllegalArgumentException;

  /**
   * Performs a sharpen transformation on the image associated with the given ID.
   *
   * @param id image's ID being filtered.
   * @return The filtered image.
   * @throws IllegalArgumentException If ID is null or session is invalid.
   */
  ImageOfPixel sharpen(String id) throws IllegalArgumentException;

  /**
   * Performs a grayscale transformation on the image associated with the given ID.
   *
   * @param id image's ID being filtered.
   * @return The filtered image.
   * @throws IllegalArgumentException If ID is null or session is invalid.
   */
  ImageOfPixel grayscale(String id) throws IllegalArgumentException;

  /**
   * Performs a sepia transformation on the image associated with the given ID.
   *
   * @param id image's ID being filtered.
   * @return The filtered image.
   * @throws IllegalArgumentException If ID is null or session is invalid.
   */
  ImageOfPixel sepia(String id) throws IllegalArgumentException;

  /**
   * Performs a red grayscale transformation on the image associated with the given ID.
   *
   * @param id image's ID being filtered.
   * @return The filtered image.
   * @throws IllegalArgumentException If ID is null or session is invalid.
   */
  ImageOfPixel redGrayscale(String id) throws IllegalArgumentException;

  /**
   * Performs a green grayscale transformation on the image associated with the given ID.
   *
   * @param id image's ID being filtered.
   * @return The filtered image.
   * @throws IllegalArgumentException If ID is null or session is invalid.
   */
  ImageOfPixel greenGrayscale(String id) throws IllegalArgumentException;

  /**
   * Performs a blue grayscale transformation on the image associated with the given ID.
   *
   * @param id image's ID being filtered.
   * @return The filtered image.
   * @throws IllegalArgumentException If ID is null or session is invalid.
   */
  ImageOfPixel blueGrayscale(String id) throws IllegalArgumentException;

  /**
   * Performs a value transformation on the image associated with the given ID.
   *
   * @param id image's ID being filtered.
   * @return The filtered image.
   * @throws IllegalArgumentException If ID is null or session is invalid.
   */
  ImageOfPixel valueComponent(String id) throws IllegalArgumentException;

  /**
   * Performs an instensity transformation on the image associated with the given ID.
   *
   * @param id image's ID being filtered.
   * @return The filtered image.
   * @throws IllegalArgumentException If ID is null or session is invalid.
   */
  ImageOfPixel intensityComponent(String id) throws IllegalArgumentException;


  /**
   * Performs a darken transformation on the image associated with the given ID.
   *
   * @param id image's ID being filtered.
   * @return The filtered image.
   * @throws IllegalArgumentException If ID is null or session is invalid.
   */
  ImageOfPixel darken(String id, int val) throws IllegalArgumentException;

  /**
   * Performs a brighten transformation on the image associated with the given ID.
   *
   * @param id image's ID being filtered.
   * @return The filtered image.
   * @throws IllegalArgumentException If ID is null or session is invalid.
   */
  ImageOfPixel brighten(String id, int val) throws IllegalArgumentException;

  /**
   * Performs a horizontal flip transformation on the image associated with the given ID.
   *
   * @param id image's ID being filtered.
   * @return The filtered image.
   * @throws IllegalArgumentException If ID is null or session is invalid.
   */
  ImageOfPixel horizontalFlip(String id) throws IllegalArgumentException;

  /**
   * Performs a vertical flip transformation on the image associated with the given ID.
   *
   * @param id image's ID being filtered.
   * @return The filtered image.
   * @throws IllegalArgumentException If ID is null or session is invalid.
   */
  ImageOfPixel verticalFlip(String id) throws IllegalArgumentException;

  ImageOfPixel downscale(String id, int newWidth, int newHeight) throws IllegalArgumentException;


  /**
   * Makes the given image specified by the ID visible.
   *
   * @param id image name.
   * @throws IllegalArgumentException If id is null, visible, or nonexistent.
   */
  void showImage(String id) throws IllegalArgumentException;

  /**
   * Makes the given image specified by the ID invisible.
   *
   * @param id image name.
   * @throws IllegalArgumentException If id is null, visible, or nonexistent.
   */
  void hideImage(String id) throws IllegalArgumentException;

  /**
   * Allows for the addition of several images to be added in the model.
   *
   * @param images    a map of images containing their corresponding names.
   * @param invisible a list of invisible image's marked by their IDs.
   * @throws IllegalArgumentException If the layers don't have same dimensions, arguments are null,
   *                                  or IDs aren't unique.
   */
  void addMultipleImages(Map<String, ImageOfPixel> images, List<String> invisible)
      throws IllegalArgumentException;

  /**
   * Gets the list of invisible images marked by their IDs.
   *
   * @return list of invisible image ids.
   */
  List<String> getInvisible();

  /**
   * Retrieves the images that are layered via a map that contains their IDs.
   *
   * @return map of images and their corresponding IDs.
   */
  Map<String, ImageOfPixel> getImages();

  /**
   * Adds the given image to the list of images using the given ID as its name.
   *
   * @param id    name of the image.
   * @param image image being added.
   * @throws IllegalArgumentException If arguments are null or ID is invalid/used.
   */
  void addImage(String id, ImageOfPixel image) throws IllegalArgumentException;

  /**
   * Removes the image by using the given corresponding ID.
   *
   * @param id image ID.
   * @throws IllegalArgumentException If ID is null or invalid.
   */
  void removeImage(String id) throws IllegalArgumentException;

  /**
   * Replaces the image of given ID with the given image.
   *
   * @param id    image's ID being replaced.
   * @param image image to replace the original.
   * @throws IllegalArgumentException If arguments are null or ID is invalid.
   */
  void replaceImage(String id, ImageOfPixel image) throws IllegalArgumentException;


  /**
   * Gets the image that is associated with the given ID.
   *
   * @param id id of the desired image.
   * @return the associated image.
   * @throws IllegalArgumentException If ID is invalid or no such image can be found.
   */
  ImageOfPixel getImage(String id) throws IllegalArgumentException;

  public List<ArrayList<IPixel>> updatePreview(int horizontal, int vertical, String operation,
      BufferedImage image);
}
