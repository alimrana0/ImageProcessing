package filters.FlippingTransformation;

import model.Imaging.ImageOfPixel;

public interface IFlipTransform {

  ImageOfPixel flipTransform(ImageOfPixel image) throws IllegalArgumentException;

}