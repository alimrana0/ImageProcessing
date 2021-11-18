package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.ImageProcessingSession;
import view.IGUI;
import view.JFrameView;

public class GUIImageController implements IFeatures, ActionListener {

  private ImageProcessingSession model;
  private IGUI view;

  public GUIImageController(ImageProcessingSession model, IGUI view) {
    this.model = model;
    this.view = view;
    this.view.addFeatures(this);
  }

/*
Image is loaded in and converted to pixels, the image is displayed,
after every operation a png is made and displayed
 */

  @Override
  public void actionPerformed(ActionEvent e) {
    System.out.println("ACTION PERFORMED");

    // TODO Auto-generated method stub
    switch (e.getActionCommand()) {
      case "Open file":
        String filename = this.view.getFile();
        if (!filename.equals(null)) {
          this.load(filename);
        }
        break;
    }
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

