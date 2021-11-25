package controller;

import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

import controller.filereading.IReadMultiLayered;
import controller.filereading.ImageReadFile;
import controller.filereading.ReadMultiLayered;
import controller.filereading.PPMReadFile;
import controller.filewriting.IImageWriteFile;
import controller.filewriting.JPEGWriteFile;
import controller.filewriting.WriteMultiLayered;
import controller.filewriting.PNGWriteFile;
import controller.filewriting.PPMWriteFile;
import model.IImageProcessingSession;
import model.imaging.ImageOfPixel;
import view.IImageProcessingView;
import view.ImageProcessingView;

/**
 * Class representing the controller for an ImageProcessor that deals with multilayered images.
 * These images can be loaded and saved in following file formats: PPN, PNG, and JPEG.
 */
public class ImageProcessingController implements ImageController {

  private final IImageProcessingSession model;
  private final Readable r;
  private final IImageProcessingView view;
  private String selected;

  /**
   * Creates an instance of the controller.
   *
   * @param model the model being processed.
   * @param r     Readable to receive inputs.
   * @param a     appendable to receive outputs.
   * @throws IllegalArgumentException If any argument is null.
   */
  public ImageProcessingController(IImageProcessingSession model, Readable r,
                                   Appendable a) throws IllegalArgumentException {
    if (model == null || r == null || a == null) {
      throw new IllegalArgumentException("Parameters can't be null");
    }
    this.model = model;
    this.r = r;
    this.view = new ImageProcessingView(a);

  }

  @Override
  public void run() throws IllegalStateException {
    this.selected = null;

    Scanner scan = new Scanner(this.r);

    if (!scan.hasNext()) {
      throw new IllegalStateException();
    }

    while (scan.hasNext()) {
      String str = scan.nextLine();

      String[] instruction = str.split("\\s+");
      if (instruction.length == 0) {
        handleRender("Don't use spaces before a instruction.\n");
      } else {
        checkInstructions(instruction);
      }
    }
  }

  /**
   * Creates instructions based on whether they are recognized by the controller.
   *
   * @param instruction string array representing input.
   */
  private void checkInstructions(String[] instruction) {

    switch (instruction[0]) {
      case "create":
        createInstruction(instruction);
        break;
      case "remove":
        removeInstruction(instruction);
        break;
      case "current":
        currentInstruction(instruction);
        break;
      case "blur":
        blurInstruct(instruction);
        break;
      case "sharpen":
        sharpenInstruct(instruction);
        break;
      case "vertical":
        verticalInstruct(instruction);
        break;
      case "horizontal":
        horizontalInstruct(instruction);
        break;
      case "grayscale":
        grayscaleInstruct(instruction);
        break;
      case "sepia":
        sepiaInstruct(instruction);
        break;
      case "show":
        showInstruction(instruction);
        break;
      case "hide":
        hideInstruction(instruction);
        break;
      case "save":
        saveInstruction(instruction);
        break;
      case "saveall":
        saveAllInstructions(instruction);
        break;
      case "addmultiple":
        addMultipleInstructions(instruction);
        break;
      case "add":
        handleRender("Create instruction handles the instruction addition.");
        break;
      case "checkerboard":
        handleRender("Create instruction handles the checkerboard instruction.");
        break;
      case "exit":
        System.exit(0);
        break;
      default:
        handleRender("Invalid instruction");
    }
  }

  /**
   * Checks if the given instruction is valid given its syntax. If it is valid, it creates a layer
   * using either add instruction or checkerboard instruction.
   *
   * @param instruction string array containing the instruction.
   */
  private void createInstruction(String[] instruction) {
    if (instruction.length == 5) {
      if (instruction[2].equals("add")) {
        try {
          if (instruction[4].equals("ppm")) {
            handleAdd(instruction[1], new PPMReadFile()
                    .readImage(instruction[3]));
          } else {
            handleAdd(instruction[1], new ImageReadFile()
                    .readImage(instruction[3]));
          }
        } catch (IllegalArgumentException e) {
          handleRender(e.getMessage());
        }
      } else {
        handleRender("Invalid syntax for create instruction.");
      }
    } else {
      handleRender("Invalid syntax for create instruction.");
    }
  }

  /**
   * Checks if the given instruction is valid given its syntax. If it is valid, it sets the current
   * layer
   *
   * @param instruction string array containing the instruction.
   */
  private void currentInstruction(String[] instruction) {
    if (instruction.length == 2) {
      if (!this.model.getImages().isEmpty()) {
        if (this.model.getImages().containsKey(instruction[1])) {
          this.selected = instruction[1];
        } else {
          handleRender("Nonexistent layer.");
        }
      } else {
        handleRender("No layers have been made.");
      }
    } else {
      handleRender("Invalid syntax for current instruction.");
    }
  }

  /**
   * Checks if the given instruction is valid given its syntax. If it is valid, it removes the layer
   *
   * @param instruction string array containing the instruction.
   */
  private void removeInstruction(String[] instruction) {
    if (instruction.length == 2) {
      try {
        this.model.removeImage(instruction[1]);
      } catch (IllegalArgumentException e) {
        handleRender(e.getMessage());
      }
    } else {
      handleRender("Invalid syntax for remove instruction.");
    }
  }

  /**
   * Checks if the given instruction is valid given its syntax. If it is valid, it blurs the
   * current image.
   *
   * @param instruction string array containing the instruction.
   */
  private void blurInstruct(String[] instruction) {
    if (instruction.length == 1) {
      if (selected != null) {
        handleReplace(selected, this.model.blur(selected));
      } else {
        handleRender("No current image has been set.");
      }
    } else {
      handleRender("Invalid syntax for blur instruction");
    }
  }

  /**
   * Checks if the given instruction is valid given its syntax. If it is valid, it sharpens the
   * current image.
   *
   * @param instruction string array containing the instruction.
   */
  private void sharpenInstruct(String[] instruction) {
    if (instruction.length == 1) {
      if (this.selected != null) {
        handleReplace(this.selected, this.model.sharpen(this.selected));
      } else {
        handleRender("No current image has been set.");
      }
    } else {
      handleRender("Invalid syntax for sharpen instruction");
    }
  }

  /**
   * Checks if the given instruction is valid given its syntax. If it is valid, it grayscales the
   * current image.
   *
   * @param instruction string array containing the instruction.
   */
  private void grayscaleInstruct(String[] instruction) {
    if (instruction.length == 1) {
      if (this.selected != null) {
        handleReplace(this.selected, this.model.grayscale(this.selected));
      } else {
        handleRender("No current image has been set.");
      }
    } else {
      handleRender("Invalid syntax for grayscale instruction");
    }
  }

  /**
   * Checks if the given instruction is valid given its syntax. If it is valid, it sepia transforms
   * the current image.
   *
   * @param instruction string array containing the instruction.
   */
  private void sepiaInstruct(String[] instruction) {
    if (instruction.length == 1) {
      if (this.selected != null) {
        handleReplace(this.selected, this.model.sepia(this.selected));
      } else {
        handleRender("No current image has been set.");
      }
    } else {
      handleRender("Invalid syntax for sepia instruction");
    }
  }

  /**
   * Checks if the given instruction is valid given its syntax. If it is valid, it vertically flips
   * the current image.
   *
   * @param instruction string array containing the instruction.
   */
  private void verticalInstruct(String[] instruction) {
    if (instruction.length == 1) {
      if (this.selected != null) {
        handleReplace(this.selected, this.model.verticalFlip(this.selected));
      } else {
        handleRender("No current image has been set.");
      }
    } else {
      handleRender("Invalid syntax for vertical flip instruction");
    }
  }

  /**
   * Checks if the given instruction is valid given its syntax. If it is valid, it horizontally
   * flips the current image.
   *
   * @param instruction string array containing the instruction.
   */
  private void horizontalInstruct(String[] instruction) {
    if (instruction.length == 1) {
      if (this.selected != null) {
        handleReplace(this.selected, this.model.horizontalFlip(this.selected));
      } else {
        handleRender("No current image has been set.");
      }
    } else {
      handleRender("Invalid syntax for horizontal flip instruction");
    }
  }

  /**
   * Checks if the given instruction is valid given its syntax. If it is valid, it shows the
   * current image.
   *
   * @param instruction string array containing the instruction.
   */
  private void showInstruction(String[] instruction) {
    if (instruction.length == 1) {
      if (!this.model.getImages().isEmpty()) {
        try {
          this.model.showImage(this.selected);
        } catch (IllegalArgumentException e) {
          handleRender(e.getMessage());
        }
      } else {
        handleRender("No layers have been made.");
      }
    } else {
      handleRender("Invalid syntax for show instruction");
    }
  }

  /**
   * Checks if the given instruction is valid given its syntax. If it is valid, it hides the
   * current image.
   *
   * @param instruction string array containing the instruction.
   */
  private void hideInstruction(String[] instruction) {
    if (instruction.length == 1) {
      if (!this.model.getImages().isEmpty()) {
        try {
          this.model.hideImage(this.selected);
        } catch (IllegalArgumentException e) {
          handleRender(e.getMessage());
        }
      } else {
        handleRender("No layers have been made.");
      }
    } else {
      handleRender("Invalid syntax for show instruction");
    }
  }

  /**
   * Checks if the given instruction is valid given its syntax. If it is valid, it saves the
   * current image.
   *
   * @param instruction string array containing the instruction.
   */
  private void saveInstruction(String[] instruction) {
    if (instruction.length == 3) {
      if (this.selected != null) {
        switch (instruction[1]) {
          case "ppm":
            writeFileHandler(instruction[2], new PPMWriteFile(), instruction[1]);
            break;
          case "jpeg":
            writeFileHandler(instruction[2], new JPEGWriteFile(), instruction[1]);
            break;
          case "png":
            writeFileHandler(instruction[2], new PNGWriteFile(), instruction[1]);
            break;
          default:
            handleRender("File type is invalid");
        }
      }
    } else {
      handleRender("Invalid syntax for save instruction");
    }
  }

  /**
   * Checks if the given instruction is valid given its syntax. If it is valid, it saves the
   * current layers.
   *
   * @param instruction string array containing the instruction.
   */
  private void saveAllInstructions(String[] instruction) {
    if (instruction.length == 3) {
      try {
        new WriteMultiLayered()
                .writeFile(instruction[2], instruction[1], this.model.getImages(),
                        this.model.getInvisible());
      } catch (IllegalArgumentException e) {
        handleRender(e.getMessage());
      } catch (IOException io) {
        throw new IllegalStateException();
      }
    } else {
      handleRender("Invalid syntax for save all instructions");
    }
  }

  /**
   * Checks if the given instructions are valid given their syntax. If they are valid, it saves the
   * current layers.
   *
   * @param instruction string array containing the instruction.
   */
  private void addMultipleInstructions(String[] instruction) {
    if (instruction.length == 2) {
      try {
        IReadMultiLayered newMulti = new ReadMultiLayered();
        Map<String, ImageOfPixel> newMultiImages = newMulti.readImages(instruction[1]);
        try {
          this.model.addMultipleImages(newMultiImages, newMulti.findVisibility());
        } catch (IllegalArgumentException e) {
          handleRender(e.getMessage());
        }
      } catch (IllegalArgumentException e) {
        handleRender(e.getMessage());
      }
    } else {
      handleRender("Invalid add multiple instructions syntax.");
    }
  }


  /**
   * Handles the writing process from the image into an image file.
   *
   * @param s      the file name.
   * @param writer writer for the given file type.
   * @param type   the file type.
   */
  private void writeFileHandler(String s, IImageWriteFile writer, String type) {
    String id = this.getTopVisible();
    try {
      writer.writeFile(s + "." + type, this.model.getImage(id));
    } catch (IllegalArgumentException e) {
      handleRender(e.getMessage());
    } catch (IOException io) {
      throw new IllegalStateException("Failed to write to file.");
    }
  }


  /**
   * Handles the addition of layers to the model.
   *
   * @param name  name of the image being added.
   * @param image image being added.
   */
  private void handleAdd(String name, ImageOfPixel image) {
    try {
      this.model.addImage(name, image);
    } catch (IllegalArgumentException e) {
      handleRender(e.getMessage());
    }
  }

  /**
   * Handles the replacement of an image with the ID of the new image.
   *
   * @param current the current image's ID.
   * @param image   image being set to the current ID.
   */
  private void handleReplace(String current, ImageOfPixel image) {
    try {
      this.model.replaceImage(current, image);
    } catch (IllegalArgumentException e) {
      handleRender(e.getMessage());
    }
  }

  /**
   * Handles a message being rendered and added to the appendable.
   *
   * @param msg message to be rendered.
   */
  private void handleRender(String msg) {
    try {
      view.renderMessage(msg + "\n");
    } catch (IOException io) {
      throw new IllegalStateException();
    }
  }

  /**
   * Gets the top visible image in the multilayer.
   *
   * @return top visible image.
   */
  private String getTopVisible() {
    for (Map.Entry<String, ImageOfPixel> item : this.model.getImages().entrySet()) {
      if (!this.model.getInvisible().contains(item.getKey())) {
        return item.getKey();
      }
    }
    return null;
  }

}
