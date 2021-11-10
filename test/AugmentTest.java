import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import filters.BlurFilter;
import filters.SharpenFilter;
import model.imaging.Color;
import model.imaging.Image;
import model.imaging.ImageOfPixel;
import model.imaging.Posn;
import model.imaging.pixel.IPixel;
import model.imaging.pixel.Pixel;

import static org.junit.Assert.assertEquals;

/**
 * Test class for general ImageProcessing filters.
 */
public class AugmentTest {

  Color red = new Color(255, 0, 0);
  Color green = new Color(0, 255, 0);
  Color blue = new Color(0, 0, 255);
  Color white = new Color(255, 255, 255);

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
  public void testBlurFilter() {
    ImageOfPixel image = new Image(list2D);
    ImageOfPixel blurredImage = new BlurFilter().transform(image);

    assertEquals(blurredImage.getPixels().get(0).get(0).getColor().getRed(), 78);
    assertEquals(blurredImage.getPixels().get(0).get(0).getColor().getGreen(), 46);
    assertEquals(blurredImage.getPixels().get(0).get(0).getColor().getBlue(), 46);

    assertEquals(blurredImage.getPixels().get(0).get(1).getColor().getRed(), 62);
    assertEquals(blurredImage.getPixels().get(0).get(1).getColor().getGreen(), 46);
    assertEquals(blurredImage.getPixels().get(0).get(1).getColor().getBlue(), 94);

    assertEquals(blurredImage.getPixels().get(1).get(0).getColor().getRed(), 62);
    assertEquals(blurredImage.getPixels().get(1).get(0).getColor().getGreen(), 94);
    assertEquals(blurredImage.getPixels().get(1).get(0).getColor().getBlue(), 46);

    assertEquals(blurredImage.getPixels().get(1).get(1).getColor().getRed(), 78);
    assertEquals(blurredImage.getPixels().get(1).get(1).getColor().getGreen(), 94);
    assertEquals(blurredImage.getPixels().get(1).get(1).getColor().getBlue(), 94);
  }

  @Test
  public void testSharpenFilter() {
    ImageOfPixel image = new Image(list2D);
    ImageOfPixel sharpenedImage = new SharpenFilter().transform(image);

    assertEquals(sharpenedImage.getPixels().get(0).get(0).getColor().getRed(), 255);
    assertEquals(sharpenedImage.getPixels().get(0).get(0).getColor().getGreen(), 126);
    assertEquals(sharpenedImage.getPixels().get(0).get(0).getColor().getBlue(), 126);

    assertEquals(sharpenedImage.getPixels().get(0).get(1).getColor().getRed(), 126);
    assertEquals(sharpenedImage.getPixels().get(0).get(1).getColor().getGreen(), 126);
    assertEquals(sharpenedImage.getPixels().get(0).get(1).getColor().getBlue(), 255);

    assertEquals(sharpenedImage.getPixels().get(1).get(0).getColor().getRed(), 126);
    assertEquals(sharpenedImage.getPixels().get(1).get(0).getColor().getGreen(), 255);
    assertEquals(sharpenedImage.getPixels().get(1).get(0).getColor().getBlue(), 126);

    assertEquals(sharpenedImage.getPixels().get(1).get(1).getColor().getRed(), 255);
    assertEquals(sharpenedImage.getPixels().get(1).get(1).getColor().getGreen(), 255);
    assertEquals(sharpenedImage.getPixels().get(1).get(1).getColor().getBlue(), 255);

  }
}
