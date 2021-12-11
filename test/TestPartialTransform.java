import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import filters.intensitytransformation.BrightenTransformation;
import model.imaging.Color;
import model.imaging.IColor;
import model.imaging.Image;
import model.imaging.ImageOfPixel;
import model.imaging.Posn;
import model.imaging.pixel.IPixel;
import model.imaging.pixel.Pixel;

import static org.junit.Assert.assertEquals;

public class TestPartialTransform {
  IColor black = new Color(0,0,0); 
  IColor dark = new Color(100, 50, 50); 
  IPixel pixel1 = new Pixel(new Posn(0,0), dark);
  IPixel pixel2 = new Pixel(new Posn(0,1), dark);
  IPixel pixel3 = new Pixel(new Posn(1,0), dark);
  IPixel pixel4 = new Pixel(new Posn(1,1), dark);

  IPixel maskPixel1 = new Pixel(new Posn(0,0), black);
  IPixel maskPixel2 = new Pixel(new Posn(0,1), black);
  IPixel maskPixel3 = new Pixel(new Posn(1,0), black);
  IPixel maskPixel4 = new Pixel(new Posn(1,1), black);

  ArrayList<ArrayList<IPixel>> list2D = new ArrayList<ArrayList<IPixel>>();
  ArrayList<ArrayList<IPixel>> list2DMask = new ArrayList<ArrayList<IPixel>>();
  ArrayList<IPixel> temp1 = new ArrayList<IPixel>();
  ArrayList<IPixel> temp2 = new ArrayList<IPixel>();
  ArrayList<IPixel> temp3 = new ArrayList<IPixel>();
  ArrayList<IPixel> temp4 = new ArrayList<IPixel>();




  @Before
  public void setUp() {
    temp1.add(pixel1);
    temp1.add(pixel2);
    list2D.add(temp1);

    temp2.add(pixel3);
    temp2.add(pixel4);
    list2D.add(temp2);

    temp3.add(maskPixel1);
    temp3.add(maskPixel2);
    list2DMask.add(temp3);

    temp4.add(maskPixel3);
    temp4.add(maskPixel4);
    list2DMask.add(temp4);
  }

  @Test
  public void testBrightenTransformation() {

    ImageOfPixel image = new Image(list2D);
    ImageOfPixel maskImage = new Image(list2DMask);

    ImageOfPixel brightenedImageMask = new BrightenTransformation().applyTransformation(image, 2, maskImage);

    assertEquals(brightenedImageMask.getPixels().get(0).get(0).getColor().getRed(), 102);
    assertEquals(brightenedImageMask.getPixels().get(0).get(0).getColor().getGreen(), 52);
    assertEquals(brightenedImageMask.getPixels().get(0).get(0).getColor().getBlue(), 52);

    assertEquals(brightenedImageMask.getPixels().get(0).get(1).getColor().getRed(), 102);
    assertEquals(brightenedImageMask.getPixels().get(0).get(1).getColor().getGreen(), 52);
    assertEquals(brightenedImageMask.getPixels().get(0).get(1).getColor().getBlue(), 52);

    assertEquals(brightenedImageMask.getPixels().get(1).get(0).getColor().getRed(), 102);
    assertEquals(brightenedImageMask.getPixels().get(1).get(0).getColor().getGreen(), 52);
    assertEquals(brightenedImageMask.getPixels().get(1).get(0).getColor().getBlue(), 52);

    assertEquals(brightenedImageMask.getPixels().get(1).get(1).getColor().getRed(), 102);
    assertEquals(brightenedImageMask.getPixels().get(1).get(1).getColor().getGreen(), 52);
    assertEquals(brightenedImageMask.getPixels().get(1).get(1).getColor().getBlue(), 52);

  }






}
