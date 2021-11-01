package filters.IntensityTransformation;

import java.util.ArrayList;
import java.util.List;


import model.Imaging.Image;
import model.Imaging.ImageOfPixel;
import model.Imaging.pixel.IPixel;
import model.Imaging.pixel.Pixel;

public abstract class AbstractIntensityTransformation implements IIntensityTransform {

    /**
     * Creates an AbstractIntensityTransform with the given value that the image should be brightened/
     * darkened by.
     */
    protected AbstractIntensityTransformation() {
    }

    /**
     * Applies the correct transformation to the given pixel by changing its red, green, and blue
     * values. If the RGB value is out of range 0-255, it will be clamped to the value.
     *
     * @param pixel
     * @return
     */
    protected abstract IPixel intensityTransform(IPixel pixel, int val);


    public ImageOfPixel applyTransformation(ImageOfPixel image, int val) throws IllegalArgumentException {
      if (image == null) {
        throw new IllegalArgumentException("Image cannot be null.");
      }
      List<List<IPixel>> imagePixels = image.getPixels();
      return new Image(transform(imagePixels, val));
    }

    protected List transform(List<List<IPixel>> imageOfPixels,
                                               int val) {
      List<ArrayList<IPixel>> updated = new ArrayList<>();
      for (List<IPixel> r : imageOfPixels) {
        ArrayList<IPixel> row = new ArrayList<>();
        for (IPixel p : r) {
          row.add(intensityTransform(p, val));
        }
        updated.add(row);
      }
      return updated;
    }



  }

