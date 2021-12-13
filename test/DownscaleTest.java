import filters.DownscaleTransform;

import model.imaging.CheckerboardMaker;
import model.imaging.Color;
import model.imaging.Image;
import model.imaging.ImageOfPixel;
import model.imaging.Posn;
import model.imaging.pixel.IPixel;
import model.imaging.pixel.Pixel;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * Test class for downscaling.
 */
public class DownscaleTest {

  private ImageOfPixel board;
  private DownscaleTransform downscale;
  ArrayList<ArrayList<IPixel>> list2D = new ArrayList<ArrayList<IPixel>>();

  @Before
  public void setUp() {
    board = new CheckerboardMaker(25, 25,
            new ArrayList<>(Arrays.asList(new Color(255, 0, 0), new Color(0, 0, 0))))
            .generateImage();
    downscale = new DownscaleTransform();

    ArrayList<ArrayList<IPixel>> list2D = new ArrayList<ArrayList<IPixel>>();
    IPixel pixel1 = new Pixel(new Posn(0, 0), new Color(100, 50, 50));
    IPixel pixel2 = new Pixel(new Posn(0, 1), new Color(100, 50, 50));
    ArrayList<IPixel> temp1 = new ArrayList<IPixel>();
    ArrayList<IPixel> temp2 = new ArrayList<IPixel>();
    temp1.add(pixel1);
    list2D.add(temp1);
    temp2.add(pixel2);
    list2D.add(temp2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullImage() {
    downscale.scale(null, 1, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeHeight() {

    ImageOfPixel list = new Image(list2D);
    downscale.scale(list, 1, -1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeWidth() {
    ImageOfPixel list = new Image(list2D);
    downscale.scale(list, -1, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testHeightTooLarge() {
    ImageOfPixel list = new Image(list2D);
    downscale.scale(list, 10, 26);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testWidthTooLarge() {
    ImageOfPixel list = new Image(list2D);
    downscale.scale(list, 26, 1);
  }

  @Test
  public void testDownscale20x20() {
    ImageOfPixel downscale15x15 = downscale.scale(board, 20, 20);
    assertEquals(downscale15x15.getPixels().size(), 20);
    assertEquals(downscale15x15.getPixels().get(0).size(), 20);
  }

  @Test
  public void testDownscale15x20() {
    ImageOfPixel downscale15x15 = downscale.scale(board, 15, 20);
    assertEquals(downscale15x15.getPixels().size(), 20);
    assertEquals(downscale15x15.getPixels().get(0).size(), 15);
  }

  @Test
  public void testDownscale20x15() {
    ImageOfPixel downscale15x15 = downscale.scale(board, 20, 15);
    assertEquals(downscale15x15.getPixels().size(), 15);
    assertEquals(downscale15x15.getPixels().get(0).size(), 20);
  }

  @Test
  public void testDownscale10x10() {
    ImageOfPixel downscale15x15 = downscale.scale(board, 10, 10);
    assertEquals(downscale15x15.getPixels().size(), 10);
    assertEquals(downscale15x15.getPixels().get(0).size(), 10);
  }

  @Test
  public void testDownscale1x1() {
    ImageOfPixel downscale15x15 = downscale.scale(board, 1, 1);
    assertEquals(downscale15x15.getPixels().size(), 1);
    assertEquals(downscale15x15.getPixels().get(0).size(), 1);
  }
}

