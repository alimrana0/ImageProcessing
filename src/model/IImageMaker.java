package model;

import model.imaging.ImageOfPixel;

/**
 * Interface to represent the generation of an image of pixel.
 */
public interface IImageMaker {


    /**
     * Creates an image of pixel.
     *
     * @return The produced image.
     */
    ImageOfPixel generateImage();
}
