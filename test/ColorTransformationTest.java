
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import filters.ColorTransformation.Greyscale.BlueGreyscale;
import filters.ColorTransformation.Greyscale.GreenGreyscale;
import filters.ColorTransformation.Greyscale.LumaGreyscale;
import filters.ColorTransformation.Greyscale.RedGreyscale;
import filters.ColorTransformation.IntensityChange;
import filters.ColorTransformation.ValueChange;

import model.Imaging.Color;
import model.Imaging.Image;
import model.Imaging.ImageOfPixel;
import model.Imaging.Posn;
import model.Imaging.pixel.IPixel;
import model.Imaging.pixel.Pixel;

import static org.junit.Assert.assertEquals;

public class ColorTransformationTest {

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
  public void testBlueTransformation() {
    ImageOfPixel image = new Image(list2D);

    ImageOfPixel blueImage = new BlueGreyscale().applyColorTransformation(image);

    assertEquals(blueImage.getPixels().get(0).get(0).getColor().getRed(), 0);
    assertEquals(blueImage.getPixels().get(0).get(0).getColor().getGreen(), 0);
    assertEquals(blueImage.getPixels().get(0).get(0).getColor().getBlue(), 0);

    assertEquals(blueImage.getPixels().get(0).get(1).getColor().getRed(), 0);
    assertEquals(blueImage.getPixels().get(0).get(1).getColor().getGreen(), 0);
    assertEquals(blueImage.getPixels().get(0).get(1).getColor().getBlue(), 0);

    assertEquals(blueImage.getPixels().get(1).get(0).getColor().getRed(), 255);
    assertEquals(blueImage.getPixels().get(1).get(0).getColor().getGreen(), 255);
    assertEquals(blueImage.getPixels().get(1).get(0).getColor().getBlue(), 255);

    assertEquals(blueImage.getPixels().get(1).get(1).getColor().getRed(), 255);
    assertEquals(blueImage.getPixels().get(1).get(1).getColor().getGreen(), 255);
    assertEquals(blueImage.getPixels().get(1).get(1).getColor().getBlue(), 255);

  }

  @Test
  public void testRedTransformation() {

    ImageOfPixel image = new Image(list2D);

    ImageOfPixel redImage = new RedGreyscale().applyColorTransformation(image);

    assertEquals(redImage.getPixels().get(0).get(0).getColor().getRed(), 255);
    assertEquals(redImage.getPixels().get(0).get(0).getColor().getGreen(), 255);
    assertEquals(redImage.getPixels().get(0).get(0).getColor().getBlue(), 255);

    assertEquals(redImage.getPixels().get(0).get(1).getColor().getRed(), 0);
    assertEquals(redImage.getPixels().get(0).get(1).getColor().getGreen(), 0);
    assertEquals(redImage.getPixels().get(0).get(1).getColor().getBlue(), 0);

    assertEquals(redImage.getPixels().get(1).get(0).getColor().getRed(), 0);
    assertEquals(redImage.getPixels().get(1).get(0).getColor().getGreen(), 0);
    assertEquals(redImage.getPixels().get(1).get(0).getColor().getBlue(), 0);

    assertEquals(redImage.getPixels().get(1).get(1).getColor().getRed(), 255);
    assertEquals(redImage.getPixels().get(1).get(1).getColor().getGreen(), 255);
    assertEquals(redImage.getPixels().get(1).get(1).getColor().getBlue(), 255);

  }

  @Test
  public void testGreenTransformation() {

    ImageOfPixel image = new Image(list2D);

    ImageOfPixel greenImage = new GreenGreyscale().applyColorTransformation(image);

    assertEquals(greenImage.getPixels().get(0).get(0).getColor().getRed(), 0);
    assertEquals(greenImage.getPixels().get(0).get(0).getColor().getGreen(), 0);
    assertEquals(greenImage.getPixels().get(0).get(0).getColor().getBlue(), 0);

    assertEquals(greenImage.getPixels().get(0).get(1).getColor().getRed(), 255);
    assertEquals(greenImage.getPixels().get(0).get(1).getColor().getGreen(), 255);
    assertEquals(greenImage.getPixels().get(0).get(1).getColor().getBlue(), 255);

    assertEquals(greenImage.getPixels().get(1).get(0).getColor().getRed(), 0);
    assertEquals(greenImage.getPixels().get(1).get(0).getColor().getGreen(), 0);
    assertEquals(greenImage.getPixels().get(1).get(0).getColor().getBlue(), 0);

    assertEquals(greenImage.getPixels().get(1).get(1).getColor().getRed(), 255);
    assertEquals(greenImage.getPixels().get(1).get(1).getColor().getGreen(), 255);
    assertEquals(greenImage.getPixels().get(1).get(1).getColor().getBlue(), 255);

  }

  @Test
  public void testGreyScaleTransformation() {

    ImageOfPixel image = new Image(list2D);

    ImageOfPixel greyScale = new LumaGreyscale().applyColorTransformation(image);

    assertEquals(greyScale.getPixels().get(0).get(0).getColor().getRed(), 54);
    assertEquals(greyScale.getPixels().get(0).get(0).getColor().getGreen(), 54);
    assertEquals(greyScale.getPixels().get(0).get(0).getColor().getBlue(), 54);

    assertEquals(greyScale.getPixels().get(0).get(1).getColor().getRed(), 182);
    assertEquals(greyScale.getPixels().get(0).get(1).getColor().getGreen(), 182);
    assertEquals(greyScale.getPixels().get(0).get(1).getColor().getBlue(), 182);

    assertEquals(greyScale.getPixels().get(1).get(0).getColor().getRed(), 18);
    assertEquals(greyScale.getPixels().get(1).get(0).getColor().getGreen(), 18);
    assertEquals(greyScale.getPixels().get(1).get(0).getColor().getBlue(), 18);

    assertEquals(greyScale.getPixels().get(1).get(1).getColor().getRed(), 254);
    assertEquals(greyScale.getPixels().get(1).get(1).getColor().getGreen(), 254);
    assertEquals(greyScale.getPixels().get(1).get(1).getColor().getBlue(), 254);

  }

  @Test
  public void testIntensityTransformation() {

    ImageOfPixel image = new Image(list2D);

    ImageOfPixel intensityImage = new IntensityChange().applyColorTransformation(image);

    assertEquals(intensityImage.getPixels().get(0).get(0).getColor().getRed(), 85);
    assertEquals(intensityImage.getPixels().get(0).get(0).getColor().getGreen(), 85);
    assertEquals(intensityImage.getPixels().get(0).get(0).getColor().getBlue(), 85);

    assertEquals(intensityImage.getPixels().get(0).get(1).getColor().getRed(), 170);
    assertEquals(intensityImage.getPixels().get(0).get(1).getColor().getGreen(), 170);
    assertEquals(intensityImage.getPixels().get(0).get(1).getColor().getBlue(), 170);

    assertEquals(intensityImage.getPixels().get(1).get(0).getColor().getRed(), 0);
    assertEquals(intensityImage.getPixels().get(1).get(0).getColor().getGreen(), 0);
    assertEquals(intensityImage.getPixels().get(1).get(0).getColor().getBlue(), 0);

    assertEquals(intensityImage.getPixels().get(1).get(1).getColor().getRed(), 255);
    assertEquals(intensityImage.getPixels().get(1).get(1).getColor().getGreen(), 255);
    assertEquals(intensityImage.getPixels().get(1).get(1).getColor().getBlue(), 255);

  }

  @Test
  public void testValueTransformation() {

    ImageOfPixel image = new Image(list2D);

    ImageOfPixel valueImage = new ValueChange().applyColorTransformation(image);

    assertEquals(valueImage.getPixels().get(0).get(0).getColor().getRed(), 255);
    assertEquals(valueImage.getPixels().get(0).get(0).getColor().getGreen(), 255);
    assertEquals(valueImage.getPixels().get(0).get(0).getColor().getBlue(), 255);

    assertEquals(valueImage.getPixels().get(0).get(1).getColor().getRed(), 255);
    assertEquals(valueImage.getPixels().get(0).get(1).getColor().getGreen(), 255);
    assertEquals(valueImage.getPixels().get(0).get(1).getColor().getBlue(), 255);

    assertEquals(valueImage.getPixels().get(1).get(0).getColor().getRed(), 255);
    assertEquals(valueImage.getPixels().get(1).get(0).getColor().getGreen(), 255);
    assertEquals(valueImage.getPixels().get(1).get(0).getColor().getBlue(), 255);

    assertEquals(valueImage.getPixels().get(1).get(1).getColor().getRed(), 255);
    assertEquals(valueImage.getPixels().get(1).get(1).getColor().getGreen(), 255);
    assertEquals(valueImage.getPixels().get(1).get(1).getColor().getBlue(), 255);

  }

}
