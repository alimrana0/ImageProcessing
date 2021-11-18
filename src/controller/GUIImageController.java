package controller;

import model.ImageProcessingSession;
import view.IGUI;

public class GUIImageController implements IFeatures{
  private ImageProcessingSession model;
  private IGUI view;

  public GUIImageController (ImageProcessingSession model, IGUI view) {

  }

  @Override
  public void load(String filepath) {

  }

  @Override
  public void save(String filepath) {

  }

  @Override
  public void getComponent() {

  }

  @Override
  public void blur() {

  }

  @Override
  public void sharpen() {

  }

  @Override
  public void greyscale() {

  }

  @Override
  public void sepia() {

  }

  @Override
  public void horizontalFlip() {

  }

  @Override
  public void verticalFlip() {

  }

  @Override
  public void brighten(Integer value) {

  }

  @Override
  public void darken(Integer value) {

  }

//model view

  /*

  controller takes in view and model,

  passes itself to view in constructor

  add

  method in controller

  -> features.blur
  -> blur hit in view features.blur



   */

}
