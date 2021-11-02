import org.junit.Test;

import java.util.ArrayList;

import filters.FlippingTransformation.FlipHorizontal;
import filters.FlippingTransformation.FlipVertical;
import model.Imaging.Color;
import model.Imaging.IColor;
import model.Imaging.Image;
import model.Imaging.ImageOfPixel;
import model.Imaging.Posn;
import model.Imaging.pixel.IPixel;
import model.Imaging.pixel.Pixel;

import static org.junit.Assert.assertEquals;

public class FlippingTransformationTest {

  Color red = new Color(255, 0, 0);
  Color green = new Color(0, 255, 0);
  Color blue = new Color(0, 0, 255);
  Color white = new Color(255, 255, 255);

  IPixel pixel1 = new Pixel(new Posn(0, 0), red);
  IPixel pixel2 = new Pixel(new Posn(0, 1), green);
  IPixel pixel3 = new Pixel(new Posn(1, 0), blue);
  IPixel pixel4 = new Pixel(new Posn(1, 1), white);

  ArrayList<ArrayList<IPixel>> list2D = new ArrayList<>();
  ArrayList<IPixel> temp1 = new ArrayList<>();
  ArrayList<IPixel> temp2 = new ArrayList<>();


  public void setUp() {
    temp1.add(pixel1);
    temp1.add(pixel2);
    list2D.add(temp1);

    temp2.add(pixel3);
    temp2.add(pixel4);
    list2D.add(temp2);

  }

  @Test
  public void testHorizontalFlipTransformation() {
    ImageOfPixel image = new Image(list2D);

    ImageOfPixel horizontalFlip = new FlipHorizontal().flipTransform(image);

    assertEquals(horizontalFlip.getPixels().get(0).get(0).getColor().getRed(), 255);
    assertEquals(horizontalFlip.getPixels().get(0).get(0).getColor().getGreen(), 0);
    assertEquals(horizontalFlip.getPixels().get(0).get(0).getColor().getBlue(), 0);

    assertEquals(horizontalFlip.getPixels().get(0).get(1).getColor().getRed(), 255);
    assertEquals(horizontalFlip.getPixels().get(0).get(1).getColor().getGreen(), 0);
    assertEquals(horizontalFlip.getPixels().get(0).get(1).getColor().getBlue(), 0);

    assertEquals(horizontalFlip.getPixels().get(1).get(0).getColor().getRed(), 255);
    assertEquals(horizontalFlip.getPixels().get(1).get(0).getColor().getGreen(), 255);
    assertEquals(horizontalFlip.getPixels().get(1).get(0).getColor().getBlue(), 255);

    assertEquals(horizontalFlip.getPixels().get(1).get(1).getColor().getRed(), 255);
    assertEquals(horizontalFlip.getPixels().get(1).get(1).getColor().getGreen(), 255);
    assertEquals(horizontalFlip.getPixels().get(1).get(1).getColor().getBlue(), 255);

  }

  @Test
  public void testVerticalFlipTransformation() {
    ImageOfPixel image = new Image(list2D);

    ImageOfPixel verticalFlip = new FlipVertical().flipTransform(image);

    assertEquals(verticalFlip.getPixels().get(0).get(0).getColor().getRed(), 255);
    assertEquals(verticalFlip.getPixels().get(0).get(0).getColor().getGreen(), 0);
    assertEquals(verticalFlip.getPixels().get(0).get(0).getColor().getBlue(), 0);

    assertEquals(verticalFlip.getPixels().get(0).get(1).getColor().getRed(), 255);
    assertEquals(verticalFlip.getPixels().get(0).get(1).getColor().getGreen(), 0);
    assertEquals(verticalFlip.getPixels().get(0).get(1).getColor().getBlue(), 0);

    assertEquals(verticalFlip.getPixels().get(1).get(0).getColor().getRed(), 255);
    assertEquals(verticalFlip.getPixels().get(1).get(0).getColor().getGreen(), 255);
    assertEquals(verticalFlip.getPixels().get(1).get(0).getColor().getBlue(), 255);

    assertEquals(verticalFlip.getPixels().get(1).get(1).getColor().getRed(), 255);
    assertEquals(verticalFlip.getPixels().get(1).get(1).getColor().getGreen(), 255);
    assertEquals(verticalFlip.getPixels().get(1).get(1).getColor().getBlue(), 255);

  }
}
