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

/**
 * Test class for flipping transformation.
 */
public class FlippingTransformationTest {

  IColor red = new Color(255, 0, 0);
  IColor green = new Color(0, 255, 0);
  IColor blue = new Color(0, 0, 255);
  IColor white = new Color(255, 255, 255);

  IPixel p1 = new Pixel(new Posn(0, 0), red);
  IPixel p2 = new Pixel(new Posn(0, 1), green);
  IPixel p3 = new Pixel(new Posn(1, 0), blue);
  IPixel p4 = new Pixel(new Posn(1, 1), white);

  ArrayList<ArrayList<IPixel>> pixel2DList = new ArrayList<ArrayList<IPixel>>();
  ArrayList<IPixel> pixelList1 = new ArrayList<IPixel>();
  ArrayList<IPixel> pixelList2 = new ArrayList<IPixel>();

  @Test
  public void testHorizontalFlipTransformation() {
    pixelList1.add(p1);
    pixelList1.add(p2);
    pixel2DList.add(pixelList1);

    pixelList2.add(p3);
    pixelList2.add(p4);
    pixel2DList.add(pixelList2);
    ImageOfPixel image = new Image(pixel2DList);

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

  @Test
  public void testVerticalFlipTransformation() {
    pixelList1.add(p1);
    pixelList1.add(p2);
    pixel2DList.add(pixelList1);

    pixelList2.add(p3);
    pixelList2.add(p4);
    pixel2DList.add(pixelList2);
    ImageOfPixel image = new Image(pixel2DList);

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
}
