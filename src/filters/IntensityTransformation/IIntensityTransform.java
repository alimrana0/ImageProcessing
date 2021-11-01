package filters.IntensityTransformation;

import java.awt.*;

import model.IImageProcessingModel;
import model.Imaging.ImageOfPixel;

public interface IIntensityTransform {

  /**
   * Applies some transformation on the intensity of the given image.
   * @param image Image to apply the transformation to.
   * @return The transformed image.
   * @throws IllegalArgumentException If the supplied image is null.
   */
  ImageOfPixel applyTransformation(ImageOfPixel image, int val) throws IllegalArgumentException;
}

