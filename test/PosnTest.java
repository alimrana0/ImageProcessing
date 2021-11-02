import org.junit.Test;

import model.Imaging.Posn;

import static org.junit.Assert.assertEquals;

/**
 * Test class for Posns.
 */
public class PosnTest {

  Posn p1 = new Posn(0,0);
  Posn p2 = new Posn(0,1);
  Posn p3 = new Posn(1,0);
  Posn p4 = new Posn(1, 0);

  @Test
  public void getPosnComponents() {
    assertEquals(p1.getX(), 0);
    assertEquals(p1.getY(), 0);
    assertEquals(p2.getX(), 0);
    assertEquals(p2.getY(), 1);
    assertEquals(p3.getX(), 1);
    assertEquals(p3.getY(), 0);
  }

  @Test
  public void testEquals() {
    assertEquals(p3.equals(p4), true);
    assertEquals(p3.equals(p1), false);
  }



}
