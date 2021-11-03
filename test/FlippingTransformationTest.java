import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;


import filters.flippingTransformation.FlipHorizontal;
import filters.flippingTransformation.FlipVertical;
import model.imaging.Color;
import model.imaging.IColor;
import model.imaging.Image;
import model.imaging.ImageOfPixel;
import model.imaging.Posn;
import model.imaging.pixel.IPixel;
import model.imaging.pixel.Pixel;

import static org.junit.Assert.assertEquals;

/**
 * Test class for flipping transformation.
 */
public class FlippingTransformationTest {

  IColor red = new Color(255, 0, 0);
  IColor green = new Color(0, 255, 0);
  IColor blue = new Color(0, 0, 255);
  IColor white = new Color(255, 255, 255);

  IPixel pixel1 = new Pixel(new Posn(0, 0), red);
  IPixel pixel2 = new Pixel(new Posn(0, 1), green);
  IPixel pixel3 = new Pixel(new Posn(1, 0), blue);
  IPixel pixel4 = new Pixel(new Posn(1, 1), white);

  ArrayList<ArrayList<IPixel>> list2D = new ArrayList<ArrayList<IPixel>>();
  ArrayList<IPixel> test1 = new ArrayList<IPixel>();
  ArrayList<IPixel> test2 = new ArrayList<IPixel>();


  @Before
  public void setUp() {
    test1.add(pixel1);
    test1.add(pixel2);
    list2D.add(test1);

    test2.add(pixel3);
    test2.add(pixel4);
    list2D.add(test2);
    
  }
  @Test
  public void testFlipHorizontalTransformation() {
 
    ImageOfPixel image = new Image(list2D);

    ImageOfPixel horizontalFlip = new FlipHorizontal().flipTransform(image);

    assertEquals(horizontalFlip.getPixels().get(0).get(0).getColor().getRed(), 0);
    assertEquals(horizontalFlip.getPixels().get(0).get(0).getColor().getGreen(), 255);
    assertEquals(horizontalFlip.getPixels().get(0).get(0).getColor().getBlue(), 0);

    assertEquals(horizontalFlip.getPixels().get(0).get(1).getColor().getRed(), 255);
    assertEquals(horizontalFlip.getPixels().get(0).get(1).getColor().getGreen(), 0);
    assertEquals(horizontalFlip.getPixels().get(0).get(1).getColor().getBlue(), 0);

    assertEquals(horizontalFlip.getPixels().get(1).get(0).getColor().getRed(), 255);
    assertEquals(horizontalFlip.getPixels().get(1).get(0).getColor().getGreen(), 255);
    assertEquals(horizontalFlip.getPixels().get(1).get(0).getColor().getBlue(), 255);

    assertEquals(horizontalFlip.getPixels().get(1).get(1).getColor().getRed(), 0);
    assertEquals(horizontalFlip.getPixels().get(1).get(1).getColor().getGreen(), 0);
    assertEquals(horizontalFlip.getPixels().get(1).get(1).getColor().getBlue(), 255);

  }

  @Test(expected = IllegalArgumentException.class)
  public void testFlipHorizontalTransformationThrows() {
    ImageOfPixel horizontalFlip = new FlipHorizontal().flipTransform(null);
  }

  @Test
  public void testFlipVerticalTransformation() {

    ImageOfPixel image = new Image(list2D);

    ImageOfPixel verticalFlip = new FlipVertical().flipTransform(image);

    assertEquals(verticalFlip.getPixels().get(0).get(0).getColor().getRed(), 0);
    assertEquals(verticalFlip.getPixels().get(0).get(0).getColor().getGreen(), 0);
    assertEquals(verticalFlip.getPixels().get(0).get(0).getColor().getBlue(), 255);

    assertEquals(verticalFlip.getPixels().get(0).get(1).getColor().getRed(), 255);
    assertEquals(verticalFlip.getPixels().get(0).get(1).getColor().getGreen(), 255);
    assertEquals(verticalFlip.getPixels().get(0).get(1).getColor().getBlue(), 255);

    assertEquals(verticalFlip.getPixels().get(1).get(0).getColor().getRed(), 255);
    assertEquals(verticalFlip.getPixels().get(1).get(0).getColor().getGreen(), 0);
    assertEquals(verticalFlip.getPixels().get(1).get(0).getColor().getBlue(), 0);

    assertEquals(verticalFlip.getPixels().get(1).get(1).getColor().getRed(), 0);
    assertEquals(verticalFlip.getPixels().get(1).get(1).getColor().getGreen(), 255);
    assertEquals(verticalFlip.getPixels().get(1).get(1).getColor().getBlue(), 0);

  }

  @Test(expected = IllegalArgumentException.class)
  public void testFlipVerticalTransformationThrows() {
    ImageOfPixel verticalFlip = new FlipVertical().flipTransform(null);
  }
}
