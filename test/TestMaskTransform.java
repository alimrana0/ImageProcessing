import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import filters.BlurFilter;
import filters.SharpenFilter;
import filters.colortransformation.GreyscaleTransformation;
import filters.colortransformation.IntensityChange;
import filters.colortransformation.SepiaTransformation;
import filters.colortransformation.ValueChange;
import filters.colortransformation.greyscale.BlueGreyscale;
import filters.colortransformation.greyscale.GreenGreyscale;
import filters.colortransformation.greyscale.LumaGreyscale;
import filters.colortransformation.greyscale.RedGreyscale;
import filters.intensitytransformation.BrightenTransformation;
import filters.intensitytransformation.DarkenTransformation;
import model.imaging.Color;
import model.imaging.IColor;
import model.imaging.Image;
import model.imaging.ImageOfPixel;
import model.imaging.Posn;
import model.imaging.pixel.IPixel;
import model.imaging.pixel.Pixel;

import static org.junit.Assert.assertEquals;

/**
 * Test class for the mask transformations on an image.
 */
public class TestMaskTransform {
  IColor black = new Color(0,0,0);
  IColor white = new Color(255, 255, 255);
  IColor dark = new Color(100, 50, 50); 
  IPixel pixel1 = new Pixel(new Posn(0,0), dark);
  IPixel pixel2 = new Pixel(new Posn(0,1), dark);
  IPixel pixel3 = new Pixel(new Posn(1,0), dark);
  IPixel pixel4 = new Pixel(new Posn(1,1), dark);

  IPixel maskPixel1 = new Pixel(new Posn(0,0), black);
  IPixel maskPixel2 = new Pixel(new Posn(0,1), black);
  IPixel maskPixel3 = new Pixel(new Posn(1,0), black);
  IPixel maskPixel4 = new Pixel(new Posn(1,1), black);

  IPixel maskPixel5 = new Pixel(new Posn(0,0), black);
  IPixel maskPixel6 = new Pixel(new Posn(0,1), white);
  IPixel maskPixel7 = new Pixel(new Posn(1,0), white);
  IPixel maskPixel8 = new Pixel(new Posn(1,1), black);

  ArrayList<ArrayList<IPixel>> list2D = new ArrayList<ArrayList<IPixel>>();
  ArrayList<ArrayList<IPixel>> list2DMask = new ArrayList<ArrayList<IPixel>>();
  ArrayList<ArrayList<IPixel>> list2DMask2 = new ArrayList<ArrayList<IPixel>>();
  ArrayList<IPixel> temp1 = new ArrayList<IPixel>();
  ArrayList<IPixel> temp2 = new ArrayList<IPixel>();
  ArrayList<IPixel> temp3 = new ArrayList<IPixel>();
  ArrayList<IPixel> temp4 = new ArrayList<IPixel>();
  ArrayList<IPixel> temp5 = new ArrayList<IPixel>();
  ArrayList<IPixel> temp6 = new ArrayList<IPixel>();




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

    temp5.add(maskPixel5);
    temp5.add(maskPixel6);
    list2DMask2.add(temp5);

    temp6.add(maskPixel7);
    temp6.add(maskPixel8);
    list2DMask2.add(temp6);
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



    ImageOfPixel maskImage2 = new Image(list2DMask2);
    ImageOfPixel brightenedImageMask2 = new BrightenTransformation().applyTransformation(image, 2, maskImage2);

    assertEquals(brightenedImageMask2.getPixels().get(0).get(0).getColor().getRed(), 102);
    assertEquals(brightenedImageMask2.getPixels().get(0).get(0).getColor().getGreen(), 52);
    assertEquals(brightenedImageMask2.getPixels().get(0).get(0).getColor().getBlue(), 52);

    assertEquals(brightenedImageMask2.getPixels().get(0).get(1).getColor().getRed(), 100);
    assertEquals(brightenedImageMask2.getPixels().get(0).get(1).getColor().getGreen(), 50);
    assertEquals(brightenedImageMask2.getPixels().get(0).get(1).getColor().getBlue(), 50);

    assertEquals(brightenedImageMask2.getPixels().get(1).get(0).getColor().getRed(), 100);
    assertEquals(brightenedImageMask2.getPixels().get(1).get(0).getColor().getGreen(), 50);
    assertEquals(brightenedImageMask2.getPixels().get(1).get(0).getColor().getBlue(), 50);

    assertEquals(brightenedImageMask2.getPixels().get(1).get(1).getColor().getRed(), 102);
    assertEquals(brightenedImageMask2.getPixels().get(1).get(1).getColor().getGreen(), 52);
    assertEquals(brightenedImageMask2.getPixels().get(1).get(1).getColor().getBlue(), 52);

  }

  @Test
  public void testDarkenTransformation() {

    ImageOfPixel image = new Image(list2D);
    ImageOfPixel maskImage = new Image(list2DMask);

    ImageOfPixel darkenedImageMask = new DarkenTransformation()
            .applyTransformation(image, 2, maskImage);

    assertEquals(darkenedImageMask.getPixels().get(0).get(0).getColor().getRed(), 98);
    assertEquals(darkenedImageMask.getPixels().get(0).get(0).getColor().getGreen(), 48);
    assertEquals(darkenedImageMask.getPixels().get(0).get(0).getColor().getBlue(), 48);

    assertEquals(darkenedImageMask.getPixels().get(0).get(1).getColor().getRed(), 98);
    assertEquals(darkenedImageMask.getPixels().get(0).get(1).getColor().getGreen(), 48);
    assertEquals(darkenedImageMask.getPixels().get(0).get(1).getColor().getBlue(), 48);

    assertEquals(darkenedImageMask.getPixels().get(1).get(0).getColor().getRed(), 98);
    assertEquals(darkenedImageMask.getPixels().get(1).get(0).getColor().getGreen(), 48);
    assertEquals(darkenedImageMask.getPixels().get(1).get(0).getColor().getBlue(), 48);

    assertEquals(darkenedImageMask.getPixels().get(1).get(1).getColor().getRed(), 98);
    assertEquals(darkenedImageMask.getPixels().get(1).get(1).getColor().getGreen(), 48);
    assertEquals(darkenedImageMask.getPixels().get(1).get(1).getColor().getBlue(), 48);


    ImageOfPixel maskImage2 = new Image(list2DMask2);
    ImageOfPixel darkenedImageMask2 = new DarkenTransformation().applyTransformation(image, 2, maskImage2);

    assertEquals(darkenedImageMask2.getPixels().get(0).get(0).getColor().getRed(), 98);
    assertEquals(darkenedImageMask2.getPixels().get(0).get(0).getColor().getGreen(), 48);
    assertEquals(darkenedImageMask2.getPixels().get(0).get(0).getColor().getBlue(), 48);

    assertEquals(darkenedImageMask2.getPixels().get(0).get(1).getColor().getRed(), 100);
    assertEquals(darkenedImageMask2.getPixels().get(0).get(1).getColor().getGreen(), 50);
    assertEquals(darkenedImageMask2.getPixels().get(0).get(1).getColor().getBlue(), 50);

    assertEquals(darkenedImageMask2.getPixels().get(1).get(0).getColor().getRed(), 100);
    assertEquals(darkenedImageMask2.getPixels().get(1).get(0).getColor().getGreen(), 50);
    assertEquals(darkenedImageMask2.getPixels().get(1).get(0).getColor().getBlue(), 50);

    assertEquals(darkenedImageMask2.getPixels().get(1).get(1).getColor().getRed(), 98);
    assertEquals(darkenedImageMask2.getPixels().get(1).get(1).getColor().getGreen(), 48);
    assertEquals(darkenedImageMask2.getPixels().get(1).get(1).getColor().getBlue(), 48);

  }

  @Test
  public void testBlurTransformation() {

    ImageOfPixel image = new Image(list2D);
    ImageOfPixel maskImage = new Image(list2DMask);

    ImageOfPixel blurred = new BlurFilter().transform(image, maskImage);

    assertEquals(blurred.getPixels().get(0).get(0).getColor().getRed(), 55);
    assertEquals(blurred.getPixels().get(0).get(0).getColor().getGreen(), 27);
    assertEquals(blurred.getPixels().get(0).get(0).getColor().getBlue(), 27);

    assertEquals(blurred.getPixels().get(0).get(1).getColor().getRed(), 55);
    assertEquals(blurred.getPixels().get(0).get(1).getColor().getGreen(), 27);
    assertEquals(blurred.getPixels().get(0).get(1).getColor().getBlue(), 27);

    assertEquals(blurred.getPixels().get(1).get(0).getColor().getRed(), 55);
    assertEquals(blurred.getPixels().get(1).get(0).getColor().getGreen(), 27);
    assertEquals(blurred.getPixels().get(1).get(0).getColor().getBlue(), 27);

    assertEquals(blurred.getPixels().get(1).get(1).getColor().getRed(), 55);
    assertEquals(blurred.getPixels().get(1).get(1).getColor().getGreen(), 27);
    assertEquals(blurred.getPixels().get(1).get(1).getColor().getBlue(), 27);

    ImageOfPixel maskImage2 = new Image(list2DMask2);
    ImageOfPixel blurred2 = new BlurFilter().transform(image, maskImage2);

    assertEquals(blurred2.getPixels().get(0).get(0).getColor().getRed(), 55);
    assertEquals(blurred2.getPixels().get(0).get(0).getColor().getGreen(), 27);
    assertEquals(blurred2.getPixels().get(0).get(0).getColor().getBlue(), 27);

    assertEquals(blurred2.getPixels().get(0).get(1).getColor().getRed(), 100);
    assertEquals(blurred2.getPixels().get(0).get(1).getColor().getGreen(), 50);
    assertEquals(blurred2.getPixels().get(0).get(1).getColor().getBlue(), 50);

    assertEquals(blurred2.getPixels().get(1).get(0).getColor().getRed(), 100);
    assertEquals(blurred2.getPixels().get(1).get(0).getColor().getGreen(), 50);
    assertEquals(blurred2.getPixels().get(1).get(0).getColor().getBlue(), 50);

    assertEquals(blurred2.getPixels().get(1).get(1).getColor().getRed(), 55);
    assertEquals(blurred2.getPixels().get(1).get(1).getColor().getGreen(), 27);
    assertEquals(blurred2.getPixels().get(1).get(1).getColor().getBlue(), 27);

  }

  @Test
  public void testSharpenTransformation() {

    ImageOfPixel image = new Image(list2D);
    ImageOfPixel maskImage = new Image(list2DMask);

    ImageOfPixel sharpened = new SharpenFilter().transform(image, maskImage);

    assertEquals(sharpened.getPixels().get(0).get(0).getColor().getRed(), 175);
    assertEquals(sharpened.getPixels().get(0).get(0).getColor().getGreen(), 86);
    assertEquals(sharpened.getPixels().get(0).get(0).getColor().getBlue(), 86);

    assertEquals(sharpened.getPixels().get(0).get(1).getColor().getRed(), 175);
    assertEquals(sharpened.getPixels().get(0).get(1).getColor().getGreen(), 86);
    assertEquals(sharpened.getPixels().get(0).get(1).getColor().getBlue(), 86);

    assertEquals(sharpened.getPixels().get(1).get(0).getColor().getRed(), 175);
    assertEquals(sharpened.getPixels().get(1).get(0).getColor().getGreen(), 86);
    assertEquals(sharpened.getPixels().get(1).get(0).getColor().getBlue(), 86);

    assertEquals(sharpened.getPixels().get(1).get(1).getColor().getRed(), 175);
    assertEquals(sharpened.getPixels().get(1).get(1).getColor().getGreen(), 86);
    assertEquals(sharpened.getPixels().get(1).get(1).getColor().getBlue(), 86);

    ImageOfPixel maskImage2 = new Image(list2DMask2);
    ImageOfPixel sharpened2 = new SharpenFilter().transform(image, maskImage2);

    assertEquals(sharpened2.getPixels().get(0).get(0).getColor().getRed(), 175);
    assertEquals(sharpened2.getPixels().get(0).get(0).getColor().getGreen(), 86);
    assertEquals(sharpened2.getPixels().get(0).get(0).getColor().getBlue(), 86);

    assertEquals(sharpened2.getPixels().get(0).get(1).getColor().getRed(), 100);
    assertEquals(sharpened2.getPixels().get(0).get(1).getColor().getGreen(), 50);
    assertEquals(sharpened2.getPixels().get(0).get(1).getColor().getBlue(), 50);

    assertEquals(sharpened2.getPixels().get(1).get(0).getColor().getRed(), 100);
    assertEquals(sharpened2.getPixels().get(1).get(0).getColor().getGreen(), 50);
    assertEquals(sharpened2.getPixels().get(1).get(0).getColor().getBlue(), 50);

    assertEquals(sharpened2.getPixels().get(1).get(1).getColor().getRed(), 175);
    assertEquals(sharpened2.getPixels().get(1).get(1).getColor().getGreen(), 86);
    assertEquals(sharpened2.getPixels().get(1).get(1).getColor().getBlue(), 86);
  }

  @Test
  public void testRedGreyscale() {

    ImageOfPixel image = new Image(list2D);
    ImageOfPixel maskImage = new Image(list2DMask);

    ImageOfPixel red = new RedGreyscale().applyColorTransformation(image, maskImage);

    assertEquals(red.getPixels().get(0).get(0).getColor().getRed(), 100);
    assertEquals(red.getPixels().get(0).get(0).getColor().getGreen(), 100);
    assertEquals(red.getPixels().get(0).get(0).getColor().getBlue(), 100);

    assertEquals(red.getPixels().get(0).get(1).getColor().getRed(), 100);
    assertEquals(red.getPixels().get(0).get(1).getColor().getGreen(), 100);
    assertEquals(red.getPixels().get(0).get(1).getColor().getBlue(), 100);

    assertEquals(red.getPixels().get(1).get(0).getColor().getRed(), 100);
    assertEquals(red.getPixels().get(1).get(0).getColor().getGreen(), 100);
    assertEquals(red.getPixels().get(1).get(0).getColor().getBlue(), 100);

    assertEquals(red.getPixels().get(1).get(1).getColor().getRed(), 100);
    assertEquals(red.getPixels().get(1).get(1).getColor().getGreen(), 100);
    assertEquals(red.getPixels().get(1).get(1).getColor().getBlue(), 100);

    ImageOfPixel maskImage2 = new Image(list2DMask2);
    ImageOfPixel red2 = new RedGreyscale().applyColorTransformation(image, maskImage2);

    assertEquals(red2.getPixels().get(0).get(0).getColor().getRed(), 100);
    assertEquals(red2.getPixels().get(0).get(0).getColor().getGreen(), 100);
    assertEquals(red2.getPixels().get(0).get(0).getColor().getBlue(), 100);

    assertEquals(red2.getPixels().get(0).get(1).getColor().getRed(), 100);
    assertEquals(red2.getPixels().get(0).get(1).getColor().getGreen(), 50);
    assertEquals(red2.getPixels().get(0).get(1).getColor().getBlue(), 50);

    assertEquals(red2.getPixels().get(1).get(0).getColor().getRed(), 100);
    assertEquals(red2.getPixels().get(1).get(0).getColor().getGreen(), 50);
    assertEquals(red2.getPixels().get(1).get(0).getColor().getBlue(), 50);

    assertEquals(red2.getPixels().get(1).get(1).getColor().getRed(), 100);
    assertEquals(red2.getPixels().get(1).get(1).getColor().getGreen(), 100);
    assertEquals(red2.getPixels().get(1).get(1).getColor().getBlue(), 100);
  }

  @Test
  public void testGreenGreyscale() {

    ImageOfPixel image = new Image(list2D);
    ImageOfPixel maskImage = new Image(list2DMask);

    ImageOfPixel green = new GreenGreyscale().applyColorTransformation(image, maskImage);

    assertEquals(green.getPixels().get(0).get(0).getColor().getRed(), 50);
    assertEquals(green.getPixels().get(0).get(0).getColor().getGreen(), 50);
    assertEquals(green.getPixels().get(0).get(0).getColor().getBlue(), 50);

    assertEquals(green.getPixels().get(0).get(1).getColor().getRed(), 50);
    assertEquals(green.getPixels().get(0).get(1).getColor().getGreen(), 50);
    assertEquals(green.getPixels().get(0).get(1).getColor().getBlue(), 50);

    assertEquals(green.getPixels().get(1).get(0).getColor().getRed(), 50);
    assertEquals(green.getPixels().get(1).get(0).getColor().getGreen(), 50);
    assertEquals(green.getPixels().get(1).get(0).getColor().getBlue(), 50);

    assertEquals(green.getPixels().get(1).get(1).getColor().getRed(), 50);
    assertEquals(green.getPixels().get(1).get(1).getColor().getGreen(), 50);
    assertEquals(green.getPixels().get(1).get(1).getColor().getBlue(), 50);

    ImageOfPixel maskImage2 = new Image(list2DMask2);
    ImageOfPixel green2 = new GreenGreyscale().applyColorTransformation(image, maskImage2);

    assertEquals(green2.getPixels().get(0).get(0).getColor().getRed(), 50);
    assertEquals(green2.getPixels().get(0).get(0).getColor().getGreen(), 50);
    assertEquals(green2.getPixels().get(0).get(0).getColor().getBlue(), 50);

    assertEquals(green2.getPixels().get(0).get(1).getColor().getRed(), 100);
    assertEquals(green2.getPixels().get(0).get(1).getColor().getGreen(), 50);
    assertEquals(green2.getPixels().get(0).get(1).getColor().getBlue(), 50);

    assertEquals(green2.getPixels().get(1).get(0).getColor().getRed(), 100);
    assertEquals(green2.getPixels().get(1).get(0).getColor().getGreen(), 50);
    assertEquals(green2.getPixels().get(1).get(0).getColor().getBlue(), 50);

    assertEquals(green2.getPixels().get(1).get(1).getColor().getRed(), 50);
    assertEquals(green2.getPixels().get(1).get(1).getColor().getGreen(), 50);
    assertEquals(green2.getPixels().get(1).get(1).getColor().getBlue(), 50);
  }

  @Test
  public void testBlueGreyscale() {

    ImageOfPixel image = new Image(list2D);
    ImageOfPixel maskImage = new Image(list2DMask);

    ImageOfPixel blue = new BlueGreyscale().applyColorTransformation(image, maskImage);

    assertEquals(blue.getPixels().get(0).get(0).getColor().getRed(), 50);
    assertEquals(blue.getPixels().get(0).get(0).getColor().getGreen(), 50);
    assertEquals(blue.getPixels().get(0).get(0).getColor().getBlue(), 50);

    assertEquals(blue.getPixels().get(0).get(1).getColor().getRed(), 50);
    assertEquals(blue.getPixels().get(0).get(1).getColor().getGreen(), 50);
    assertEquals(blue.getPixels().get(0).get(1).getColor().getBlue(), 50);

    assertEquals(blue.getPixels().get(1).get(0).getColor().getRed(), 50);
    assertEquals(blue.getPixels().get(1).get(0).getColor().getGreen(), 50);
    assertEquals(blue.getPixels().get(1).get(0).getColor().getBlue(), 50);

    assertEquals(blue.getPixels().get(1).get(1).getColor().getRed(), 50);
    assertEquals(blue.getPixels().get(1).get(1).getColor().getGreen(), 50);
    assertEquals(blue.getPixels().get(1).get(1).getColor().getBlue(), 50);


    ImageOfPixel maskImage2 = new Image(list2DMask2);
    ImageOfPixel blue2 = new BlueGreyscale().applyColorTransformation(image, maskImage2);

    assertEquals(blue2.getPixels().get(0).get(0).getColor().getRed(), 50);
    assertEquals(blue2.getPixels().get(0).get(0).getColor().getGreen(), 50);
    assertEquals(blue2.getPixels().get(0).get(0).getColor().getBlue(), 50);

    assertEquals(blue2.getPixels().get(0).get(1).getColor().getRed(), 100);
    assertEquals(blue2.getPixels().get(0).get(1).getColor().getGreen(), 50);
    assertEquals(blue2.getPixels().get(0).get(1).getColor().getBlue(), 50);

    assertEquals(blue2.getPixels().get(1).get(0).getColor().getRed(), 100);
    assertEquals(blue2.getPixels().get(1).get(0).getColor().getGreen(), 50);
    assertEquals(blue2.getPixels().get(1).get(0).getColor().getBlue(), 50);

    assertEquals(blue2.getPixels().get(1).get(1).getColor().getRed(), 50);
    assertEquals(blue2.getPixels().get(1).get(1).getColor().getGreen(), 50);
    assertEquals(blue2.getPixels().get(1).get(1).getColor().getBlue(), 50);
  }

  @Test
  public void testLumaGreyscale() {

    ImageOfPixel image = new Image(list2D);
    ImageOfPixel maskImage = new Image(list2DMask);

    ImageOfPixel luma = new LumaGreyscale().applyColorTransformation(image, maskImage);

    assertEquals(luma.getPixels().get(0).get(0).getColor().getRed(), 60);
    assertEquals(luma.getPixels().get(0).get(0).getColor().getGreen(), 60);
    assertEquals(luma.getPixels().get(0).get(0).getColor().getBlue(), 60);

    assertEquals(luma.getPixels().get(0).get(1).getColor().getRed(), 60);
    assertEquals(luma.getPixels().get(0).get(1).getColor().getGreen(), 60);
    assertEquals(luma.getPixels().get(0).get(1).getColor().getBlue(), 60);

    assertEquals(luma.getPixels().get(1).get(0).getColor().getRed(), 60);
    assertEquals(luma.getPixels().get(1).get(0).getColor().getGreen(), 60);
    assertEquals(luma.getPixels().get(1).get(0).getColor().getBlue(), 60);

    assertEquals(luma.getPixels().get(1).get(1).getColor().getRed(), 60);
    assertEquals(luma.getPixels().get(1).get(1).getColor().getGreen(), 60);
    assertEquals(luma.getPixels().get(1).get(1).getColor().getBlue(), 60);


    ImageOfPixel maskImage2 = new Image(list2DMask2);
    ImageOfPixel luma2 = new LumaGreyscale().applyColorTransformation(image, maskImage2);

    assertEquals(luma2.getPixels().get(0).get(0).getColor().getRed(), 60);
    assertEquals(luma2.getPixels().get(0).get(0).getColor().getGreen(), 60);
    assertEquals(luma2.getPixels().get(0).get(0).getColor().getBlue(), 60);

    assertEquals(luma2.getPixels().get(0).get(1).getColor().getRed(), 100);
    assertEquals(luma2.getPixels().get(0).get(1).getColor().getGreen(), 50);
    assertEquals(luma2.getPixels().get(0).get(1).getColor().getBlue(), 50);

    assertEquals(luma2.getPixels().get(1).get(0).getColor().getRed(), 100);
    assertEquals(luma2.getPixels().get(1).get(0).getColor().getGreen(), 50);
    assertEquals(luma2.getPixels().get(1).get(0).getColor().getBlue(), 50);

    assertEquals(luma2.getPixels().get(1).get(1).getColor().getRed(), 60);
    assertEquals(luma2.getPixels().get(1).get(1).getColor().getGreen(), 60);
    assertEquals(luma2.getPixels().get(1).get(1).getColor().getBlue(), 60);
  }

  @Test
  public void testGreyscaleTransformation() {

    ImageOfPixel image = new Image(list2D);
    ImageOfPixel maskImage = new Image(list2DMask);

    ImageOfPixel greyscale = new GreyscaleTransformation().transform(image, maskImage);

    assertEquals(greyscale.getPixels().get(0).get(0).getColor().getRed(), 60);
    assertEquals(greyscale.getPixels().get(0).get(0).getColor().getGreen(), 60);
    assertEquals(greyscale.getPixels().get(0).get(0).getColor().getBlue(), 60);

    assertEquals(greyscale.getPixels().get(0).get(1).getColor().getRed(), 60);
    assertEquals(greyscale.getPixels().get(0).get(1).getColor().getGreen(), 60);
    assertEquals(greyscale.getPixels().get(0).get(1).getColor().getBlue(), 60);

    assertEquals(greyscale.getPixels().get(1).get(0).getColor().getRed(), 60);
    assertEquals(greyscale.getPixels().get(1).get(0).getColor().getGreen(), 60);
    assertEquals(greyscale.getPixels().get(1).get(0).getColor().getBlue(), 60);

    assertEquals(greyscale.getPixels().get(1).get(1).getColor().getRed(), 60);
    assertEquals(greyscale.getPixels().get(1).get(1).getColor().getGreen(), 60);
    assertEquals(greyscale.getPixels().get(1).get(1).getColor().getBlue(), 60);


    ImageOfPixel maskImage2 = new Image(list2DMask2);
    ImageOfPixel greyscale2 = new GreyscaleTransformation().transform(image, maskImage2);

    assertEquals(greyscale2.getPixels().get(0).get(0).getColor().getRed(), 60);
    assertEquals(greyscale2.getPixels().get(0).get(0).getColor().getGreen(), 60);
    assertEquals(greyscale2.getPixels().get(0).get(0).getColor().getBlue(), 60);

    assertEquals(greyscale2.getPixels().get(0).get(1).getColor().getRed(), 100);
    assertEquals(greyscale2.getPixels().get(0).get(1).getColor().getGreen(), 50);
    assertEquals(greyscale2.getPixels().get(0).get(1).getColor().getBlue(), 50);

    assertEquals(greyscale2.getPixels().get(1).get(0).getColor().getRed(), 100);
    assertEquals(greyscale2.getPixels().get(1).get(0).getColor().getGreen(), 50);
    assertEquals(greyscale2.getPixels().get(1).get(0).getColor().getBlue(), 50);

    assertEquals(greyscale2.getPixels().get(1).get(1).getColor().getRed(), 60);
    assertEquals(greyscale2.getPixels().get(1).get(1).getColor().getGreen(), 60);
    assertEquals(greyscale2.getPixels().get(1).get(1).getColor().getBlue(), 60);
  }

  @Test
  public void testIntensityTranformation() {

    ImageOfPixel image = new Image(list2D);
    ImageOfPixel maskImage = new Image(list2DMask);

    ImageOfPixel intensity = new IntensityChange().applyColorTransformation(image, maskImage);

    assertEquals(intensity.getPixels().get(0).get(0).getColor().getRed(), 66);
    assertEquals(intensity.getPixels().get(0).get(0).getColor().getGreen(), 66);
    assertEquals(intensity.getPixels().get(0).get(0).getColor().getBlue(), 66);

    assertEquals(intensity.getPixels().get(0).get(1).getColor().getRed(), 66);
    assertEquals(intensity.getPixels().get(0).get(1).getColor().getGreen(), 66);
    assertEquals(intensity.getPixels().get(0).get(1).getColor().getBlue(), 66);

    assertEquals(intensity.getPixels().get(1).get(0).getColor().getRed(), 66);
    assertEquals(intensity.getPixels().get(1).get(0).getColor().getGreen(), 66);
    assertEquals(intensity.getPixels().get(1).get(0).getColor().getBlue(), 66);

    assertEquals(intensity.getPixels().get(1).get(1).getColor().getRed(), 66);
    assertEquals(intensity.getPixels().get(1).get(1).getColor().getGreen(), 66);
    assertEquals(intensity.getPixels().get(1).get(1).getColor().getBlue(), 66);


    ImageOfPixel maskImage2 = new Image(list2DMask2);
    ImageOfPixel intensity2 = new IntensityChange().applyColorTransformation(image, maskImage2);

    assertEquals(intensity2.getPixels().get(0).get(0).getColor().getRed(), 66);
    assertEquals(intensity2.getPixels().get(0).get(0).getColor().getGreen(), 66);
    assertEquals(intensity2.getPixels().get(0).get(0).getColor().getBlue(), 66);

    assertEquals(intensity2.getPixels().get(0).get(1).getColor().getRed(), 100);
    assertEquals(intensity2.getPixels().get(0).get(1).getColor().getGreen(), 50);
    assertEquals(intensity2.getPixels().get(0).get(1).getColor().getBlue(), 50);

    assertEquals(intensity2.getPixels().get(1).get(0).getColor().getRed(), 100);
    assertEquals(intensity2.getPixels().get(1).get(0).getColor().getGreen(), 50);
    assertEquals(intensity2.getPixels().get(1).get(0).getColor().getBlue(), 50);

    assertEquals(intensity2.getPixels().get(1).get(1).getColor().getRed(), 66);
    assertEquals(intensity2.getPixels().get(1).get(1).getColor().getGreen(), 66);
    assertEquals(intensity2.getPixels().get(1).get(1).getColor().getBlue(), 66);
  }

  @Test
  public void testValueTranformation() {

    ImageOfPixel image = new Image(list2D);
    ImageOfPixel maskImage = new Image(list2DMask);

    ImageOfPixel value = new ValueChange().applyColorTransformation(image, maskImage);

    assertEquals(value.getPixels().get(0).get(0).getColor().getRed(), 100);
    assertEquals(value.getPixels().get(0).get(0).getColor().getGreen(), 100);
    assertEquals(value.getPixels().get(0).get(0).getColor().getBlue(), 100);

    assertEquals(value.getPixels().get(0).get(1).getColor().getRed(), 100);
    assertEquals(value.getPixels().get(0).get(1).getColor().getGreen(), 100);
    assertEquals(value.getPixels().get(0).get(1).getColor().getBlue(), 100);

    assertEquals(value.getPixels().get(1).get(0).getColor().getRed(), 100);
    assertEquals(value.getPixels().get(1).get(0).getColor().getGreen(), 100);
    assertEquals(value.getPixels().get(1).get(0).getColor().getBlue(), 100);

    assertEquals(value.getPixels().get(1).get(1).getColor().getRed(), 100);
    assertEquals(value.getPixels().get(1).get(1).getColor().getGreen(), 100);
    assertEquals(value.getPixels().get(1).get(1).getColor().getBlue(), 100);


    ImageOfPixel maskImage2 = new Image(list2DMask2);
    ImageOfPixel value2 = new ValueChange().applyColorTransformation(image, maskImage2);

    assertEquals(value2.getPixels().get(0).get(0).getColor().getRed(), 100);
    assertEquals(value2.getPixels().get(0).get(0).getColor().getGreen(), 100);
    assertEquals(value2.getPixels().get(0).get(0).getColor().getBlue(), 100);

    assertEquals(value2.getPixels().get(0).get(1).getColor().getRed(), 100);
    assertEquals(value2.getPixels().get(0).get(1).getColor().getGreen(), 50);
    assertEquals(value2.getPixels().get(0).get(1).getColor().getBlue(), 50);

    assertEquals(value2.getPixels().get(1).get(0).getColor().getRed(), 100);
    assertEquals(value2.getPixels().get(1).get(0).getColor().getGreen(), 50);
    assertEquals(value2.getPixels().get(1).get(0).getColor().getBlue(), 50);

    assertEquals(value2.getPixels().get(1).get(1).getColor().getRed(), 100);
    assertEquals(value2.getPixels().get(1).get(1).getColor().getGreen(), 100);
    assertEquals(value2.getPixels().get(1).get(1).getColor().getBlue(), 100);
  }

  @Test
  public void testSepiaTransformation() {

    ImageOfPixel image = new Image(list2D);
    ImageOfPixel maskImage = new Image(list2DMask);

    ImageOfPixel sepia = new SepiaTransformation().transform(image, maskImage);

    assertEquals(sepia.getPixels().get(0).get(0).getColor().getRed(), 87);
    assertEquals(sepia.getPixels().get(0).get(0).getColor().getGreen(), 77);
    assertEquals(sepia.getPixels().get(0).get(0).getColor().getBlue(), 60);

    assertEquals(sepia.getPixels().get(0).get(1).getColor().getRed(), 87);
    assertEquals(sepia.getPixels().get(0).get(1).getColor().getGreen(), 77);
    assertEquals(sepia.getPixels().get(0).get(1).getColor().getBlue(), 60);

    assertEquals(sepia.getPixels().get(1).get(0).getColor().getRed(), 87);
    assertEquals(sepia.getPixels().get(1).get(0).getColor().getGreen(), 77);
    assertEquals(sepia.getPixels().get(1).get(0).getColor().getBlue(), 60);

    assertEquals(sepia.getPixels().get(1).get(1).getColor().getRed(), 87);
    assertEquals(sepia.getPixels().get(1).get(1).getColor().getGreen(), 77);
    assertEquals(sepia.getPixels().get(1).get(1).getColor().getBlue(), 60);

    ImageOfPixel maskImage2 = new Image(list2DMask2);
    ImageOfPixel sepia2 = new SepiaTransformation().transform(image, maskImage2);

    assertEquals(sepia2.getPixels().get(0).get(0).getColor().getRed(), 87);
    assertEquals(sepia2.getPixels().get(0).get(0).getColor().getGreen(), 77);
    assertEquals(sepia2.getPixels().get(0).get(0).getColor().getBlue(), 60);

    assertEquals(sepia2.getPixels().get(0).get(1).getColor().getRed(), 100);
    assertEquals(sepia2.getPixels().get(0).get(1).getColor().getGreen(), 50);
    assertEquals(sepia2.getPixels().get(0).get(1).getColor().getBlue(), 50);

    assertEquals(sepia2.getPixels().get(1).get(0).getColor().getRed(), 100);
    assertEquals(sepia2.getPixels().get(1).get(0).getColor().getGreen(), 50);
    assertEquals(sepia2.getPixels().get(1).get(0).getColor().getBlue(), 50);

    assertEquals(sepia2.getPixels().get(1).get(1).getColor().getRed(), 87);
    assertEquals(sepia2.getPixels().get(1).get(1).getColor().getGreen(), 77);
    assertEquals(sepia2.getPixels().get(1).get(1).getColor().getBlue(), 60);

  }










}
