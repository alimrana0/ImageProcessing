package controller;

import java.io.File;
import java.util.Scanner;

/**
 * The view interface. This provides features that the controller would need to invoke on the
 * view while running the image processing program.
 */
public interface IFeatures {


  public void load(String filepath);

  public void save(String filepath);

  public void getComponent();

  public void blur();

  public void sharpen();

  public void greyscale();

  public void sepia();

  public void horizontalFlip();

  public void verticalFlip();

  public void brighten(Integer value);

  public void darken(Integer value);

}
