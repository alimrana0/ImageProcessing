import org.junit.Test;

import model.Imaging.Color;
import model.Imaging.IColor;
import model.Imaging.pixel.IPixel;
import model.Imaging.pixel.Pixel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Test class for Color.
 */
public class ColorTest {

  IColor red = new Color(255, 0, 0);
  IColor green = new Color(0, 255, 0);
  IColor blue = new Color(0, 0, 255);
  IColor white = new Color(255, 255, 255);

  @Test(expected = IllegalArgumentException.class)
  public void testRedOver() {
    new Color(1000, 0, 0);
  }
  @Test(expected = IllegalArgumentException.class)
  public void testContructorGreenOver() {
    new Color(0, 1000, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testContructorBlueOver() {
    new Color(0, 0, 1000);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testContructorRedUnder() {
    new Color(-1000, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testContructorGreenUnder() {
    new Color(0, -1000, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testContructorBlueUnder() {
    new Color(0, 0, -1000);
  }

  @Test
  public void testEquals() {
    assertTrue(this.red.equals(new Color(255, 0, 0)));
    assertTrue(this.green.equals(new Color(0, 255, 0)));
    assertTrue(this.blue.equals(new Color(0, 0, 255)));

    assertFalse(this.red.equals(new Color(255, 10, 0)));
    assertFalse(this.green.equals(new Color(10, 255, 0)));
    assertFalse(this.blue.equals(new Color(0, 10, 255)));
  }

  @Test
  public void testHashCodeRed() {
    assertEquals(this.red.hashCode(), 274846);
  }

  @Test
  public void testHashCodeGreen() {
    assertEquals(this.green.hashCode(), 37696);
  }

  @Test
  public void testHashCodeBlue() {
    assertEquals(this.blue.hashCode(), 30046);
  }

  @Test
  public void testHashCodeWhite() {
    assertEquals(this.white.hashCode(), 283006);
  }

  @Test
  public void testGetRed() {
    assertEquals(255, red.getRed());
    assertEquals(0, green.getRed());
    assertEquals(0, blue.getRed());
  }

  @Test
  public void testGetGreen() {
    assertEquals(0, red.getGreen());
    assertEquals(255, green.getGreen());
    assertEquals(0, blue.getGreen());
  }

  @Test
  public void testGetBlue() {
    assertEquals(0, red.getBlue());
    assertEquals(0, green.getBlue());
    assertEquals(255, blue.getBlue());
  }
}

