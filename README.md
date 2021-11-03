# ImageProcessing
# Running the program
- The commands used to control the image processor are based off of the example given 
in the assignment with a slight variation on the get component command.
- First load the image and give it a name: load stars.ppm stars
  - Now you can manipulate the image stars, below example commands using stars are listed.
  - load (filename) (name of image to be used in program)
- get-component (component) (image name) (name of new saved image) 
  - get-component red stars stars-red
  - you can replace component in parentheses with any of the following
    - red
    - green
    - blue
    - luma
    - value
    - intensity
- brighten (integer) (image name) (name of new saved image)
  - brighten 10 stars stars-bright
- darken (integer) (image name) (name of new saved image)
  - darken 10 stars stars-dark
- vertical-flip (image name) (name of new saved image)
  - vertical-flip stars stars-vflipped
- horizontal-flip (image name) (name of new saved image)
  - horizontal-flip stars stars-hflipped
- save (file name) (image to be saved as new ppm file)
  - save stars-copy.ppm stars

- Or you can copy and paste the text below
  - Text to copy:
    load stars.ppm stars
    get-component red stars red-stars
    save redstars.ppm red-stars
    get-component green stars green-stars
    save greenstars.ppm green-stars
    get-component blue stars blue-stars
    save bluestars.ppm blue-stars
    get-component luma stars lumastars
    save lumastars.ppm lumastars
    get-component value stars valuestars
    save valuestars.ppm valuestars
    get-component intensity stars intensitystars
    save intensitystars.ppm intensitystars
    brighten 10 stars brightstars
    save brightstars.ppm brightstars
    darken 10 stars darkstars
    save darkstars.ppm darkstars
    vertical-flip stars verticalstars
    save verticalstars.ppm verticalstars
    horizontal-flip stars horizontalstars
    save horizontalstars.ppm horizontalstars
    q



#Citation
The image stars was free to use and taken from https://filesamples.com/formats/ppm
and converted into the correct ppm format using ImageMagick.


#Class Diagrams

![alt text](https://github.com/alimrana0/ImageProcessing/blob/main/diagram1.PNG)

![alt text](https://github.com/alimrana0/ImageProcessing/blob/main/diagram1.PNG)

![alt text](https://github.com/alimrana0/ImageProcessing/blob/main/diagram1.PNG)

#Design Overview
**-- ImageProcessor**
The main processor that executes the runtime of an ImageProcessor.
#model
**-- IImageProcessorModel**     
The IImageProcessingModel interface is our primary model interface. It contains all current image processing methods for our model, which are the transformations on an Image that the processor supports.   
**-- ImageProcessingModel**   
The ImageProcessingModelImpl is the primary model class, which functions as a caller on transformations on an image.
**-- ImageProcessingSession**
The ImageProcessingSession is a class that handles the saving/loading of files. It acts as a transient holder for images being processed in the controller.
**-- ImageUtil**
The ImageUtil is a class that deals with reading and loading an image from its file and getting the image's pixels.
##Imaging
**-- IColor**.  
The color interface contains methods to retrieve red,green, and blue color values from wherever they are stored.   
**-- Color**   
The Color class implements the Color interface. It represents an RGB color. It contains methods that override the equal and hashcode methods to find color equivalency.   
**-- Posn**
The Posn class Represents an 2D Cartesian coordinate.
**-- ImageOfPixel**.   
The ImageOfPixel interface is an interface that represents an Image contrived of pixels. The two methods in this interface either retrieve a 2D list of pixels representing the image or saves the image as a PPM file.
**-- Image**.  
The Image class implements the ImageOfPixel interface. It replicates the pixels in the image and saves them.  
###pixel
**-- IPixel**
The IPixel interface represents a pixel. It contains methods to get the color of the position of the pixel in an image.
**-- Pixel**
The Pixel class implements the Pixel interface. It overrides the equals and hashcode to find pixel equivalency.
#filters
**-- FilterClamp** 
The FilterClamp class clamps any RGB value to a minimum value of 0 or maximum value of 255.
##ColorTransformation
**-- AbstractColorTransformation**    
This is an abstract class that represents the many transformations we can do on the color of an image/pixel. Includes things like greyscaling an image, visualizing the red, green, and blue components of an image, or visualizing the intensity of an image.  
**-- IColorTransform**
The IColorTransform interface contains one method that applies a color transformation to an image.
**-- IntensityChange**    
A class that represents an intensity transformation, which just sets each pixel to the average of the three components for that pixel.
**-- ValueChange**   
This is a class that represents a value transformation, which sets each rgb value to the maximum value of the three components for each pixel.
###Greyscale
**-- BlueGreyscale**.    
The BlueGreyscale class represents a greyscale transformation on the blue component of an image. All RGB color values are set to the image's blue component value.   
**-- GreenGreyscale**.     
The GreenGreyscale class represents a greyscale transformation on the green component of an image. All RGB color values are set to the image's green component value. **--RedGreyscale**.    
**-- RedGreyscale**
The RedGreyscale class represents a greyscale transformation on the red component of an image. All RGB color values are set to the image's red component value.
**-- LumaGreyscale**   
The LumaGreyscale class represents a luma greyscale transformation, which uses the weighted sum of 0.2126r + 0.7152g + 0.0722b to set the RGB values to greyscale the image.
##IntensityTransformation
**-- AbstractIntensityTransformation**   
The AbstractIntensityTransformation applies some intensity transformation on a given image and returns the transformed image.  
**-- IIntensityTransform**
The IIntensityTransform interface contains one method that applies an intensity transformation to an image.
**-- BrightenTransform**   
The BrightenTransform class represents a transformation that adds certain value to the RGB components of an image which effectively brightens the image.
**-- DarkenTransform**
The DarkenTransform class represents a transformation that subtracts certain value to the RGB components of an image which effectively darkens the image.
##FlippingTransformation
**-- IFlipTransform**
The IFlipTransform interface contains one method that applies a translational transformation to an image.
**-- HorizontalFlip**   
The HorizontalFlip class represents an image being flipped over the vertical middle axis. 
**-- VerticalFlip**.
The VerticalFlip class represents an image being flipped over the horizontal middle axis.
#controller
**-- ImageController**
The ImageController interface is the primary controller interface for the Image Processor. It contains a single method called run(), which runs the Image Processor based off user input.  
**-- ImageControllerImpl**. 
The ImageControllerImpl class implements the ImageController interface. It is the primary controller for an Image Processor. This class takes in user input and delegates processing to the model depending on the given commands. It also allows for transformed images to be read and saved.
#view
**-- IImageProcessingView**
The IImageProcessingView interface contains two methods to display. One renders a given message to the System, and the other shows the client options for commands.
**-- ImageProcessingView**
The ImageProcessingView class implements the IImageProcessingView interface. It performs transformations on the given image based on user inputs. The user may use current files or transform previously saved files.

