import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

import filters.intensityTransformation.BrightenTransformation;
import filters.intensityTransformation.DarkenTransformation;
import model.imaging.Color;
import model.imaging.IColor;
import model.imaging.Image;
import model.imaging.ImageOfPixel;
import model.imaging.Posn;
import model.imaging.pixel.IPixel;
import model.imaging.pixel.Pixel;

/**
 * Test class for intensity transformation.
 */
public class IntensityTransformationTest {

  IColor red = new Color(255, 0, 0);
  IColor green = new Color(0, 255, 0);
  IColor blue = new Color(0, 0, 255);
  IColor white = new Color(255, 255, 255);

  IPixel pixel1 = new Pixel(new Posn(0, 0), red);
  IPixel pixel2 = new Pixel(new Posn(0, 1), green);
  IPixel pixel3 = new Pixel(new Posn(1, 0), blue);
  IPixel pixel4 = new Pixel(new Posn(1, 1), white);

  ArrayList<ArrayList<IPixel>> list2D = new ArrayList<ArrayList<IPixel>>();
  ArrayList<IPixel> temp1 = new ArrayList<IPixel>();
  ArrayList<IPixel> temp2 = new ArrayList<IPixel>();


  @Before
  public void setUp() {
    temp1.add(pixel1);
    temp1.add(pixel2);
    list2D.add(temp1);

    temp2.add(pixel3);
    temp2.add(pixel4);
    list2D.add(temp2);
  }
  @Test
  public void testImageCreatedCorrectlyFirstPixel() {

    ImageOfPixel image = new Image(list2D);
    assertEquals(image.getPixels().get(0).get(0).getColor().getRed(), 255);
    assertEquals(image.getPixels().get(0).get(0).getColor().getGreen(), 0);
    assertEquals(image.getPixels().get(0).get(0).getColor().getBlue(), 0);


  }

  @Test
  public void testImageCreatedCorrectlySecondPixel() {

    ImageOfPixel image = new Image(list2D);
    assertEquals(image.getPixels().get(0).get(1).getColor().getRed(), 0);
    assertEquals(image.getPixels().get(0).get(1).getColor().getGreen(), 255);
    assertEquals(image.getPixels().get(0).get(1).getColor().getBlue(), 0);

  }

  @Test
  public void testImageCreatedCorrectlyThirdPixel() {

    ImageOfPixel image = new Image(list2D);
    assertEquals(image.getPixels().get(1).get(0).getColor().getRed(), 0);
    assertEquals(image.getPixels().get(1).get(0).getColor().getGreen(), 0);
    assertEquals(image.getPixels().get(1).get(0).getColor().getBlue(), 255);

  }

  @Test
  public void testImageCreatedCorrectlyFourthPixel() {

    ImageOfPixel image = new Image(list2D);
    assertEquals(image.getPixels().get(1).get(1).getColor().getRed(), 255);
    assertEquals(image.getPixels().get(1).get(1).getColor().getGreen(), 255);
    assertEquals(image.getPixels().get(1).get(1).getColor().getBlue(), 255);

  }

  @Test(expected = IllegalArgumentException.class)
  public void testBrightenTransformationThrows() {
    ImageOfPixel brightenedImage =
            new BrightenTransformation().applyTransformation(null, 2);
  }

  @Test
  public void testBrightenTransformation() {

    ImageOfPixel image = new Image(list2D);

    ImageOfPixel brightenedImage = new BrightenTransformation().applyTransformation(image, 2);

    assertEquals(brightenedImage.getPixels().get(0).get(0).getColor().getRed(), 255);
    assertEquals(brightenedImage.getPixels().get(0).get(0).getColor().getGreen(), 2);
    assertEquals(brightenedImage.getPixels().get(0).get(0).getColor().getBlue(), 2);

    assertEquals(brightenedImage.getPixels().get(0).get(1).getColor().getRed(), 2);
    assertEquals(brightenedImage.getPixels().get(0).get(1).getColor().getGreen(), 255);
    assertEquals(brightenedImage.getPixels().get(0).get(1).getColor().getBlue(), 2);

    assertEquals(brightenedImage.getPixels().get(1).get(0).getColor().getRed(), 2);
    assertEquals(brightenedImage.getPixels().get(1).get(0).getColor().getGreen(), 2);
    assertEquals(brightenedImage.getPixels().get(1).get(0).getColor().getBlue(), 255);

    assertEquals(brightenedImage.getPixels().get(1).get(1).getColor().getRed(), 255);
    assertEquals(brightenedImage.getPixels().get(1).get(1).getColor().getGreen(), 255);
    assertEquals(brightenedImage.getPixels().get(1).get(1).getColor().getBlue(), 255);

  }

  @Test(expected = IllegalArgumentException.class)
  public void testDarkenTransformationThrows() {
    ImageOfPixel darkenedImage =
            new DarkenTransformation().applyTransformation(null, 2);
  }

  @Test
  public void testDarkenTransformation() {

    ImageOfPixel image = new Image(list2D);

    ImageOfPixel darkenedImage = new DarkenTransformation().applyTransformation(image, 2);

    assertEquals(darkenedImage.getPixels().get(0).get(0).getColor().getRed(), 253);
    assertEquals(darkenedImage.getPixels().get(0).get(0).getColor().getGreen(), 0);
    assertEquals(darkenedImage.getPixels().get(0).get(0).getColor().getBlue(), 0);

    assertEquals(darkenedImage.getPixels().get(0).get(1).getColor().getRed(), 0);
    assertEquals(darkenedImage.getPixels().get(0).get(1).getColor().getGreen(), 253);
    assertEquals(darkenedImage.getPixels().get(0).get(1).getColor().getBlue(), 0);

    assertEquals(darkenedImage.getPixels().get(1).get(0).getColor().getRed(), 0);
    assertEquals(darkenedImage.getPixels().get(1).get(0).getColor().getGreen(), 0);
    assertEquals(darkenedImage.getPixels().get(1).get(0).getColor().getBlue(), 253);

    assertEquals(darkenedImage.getPixels().get(1).get(1).getColor().getRed(), 253);
    assertEquals(darkenedImage.getPixels().get(1).get(1).getColor().getGreen(), 253);
    assertEquals(darkenedImage.getPixels().get(1).get(1).getColor().getBlue(), 253);


  }






}
