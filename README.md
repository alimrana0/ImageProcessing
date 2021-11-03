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

#Citation
The image stars was free to use and taken from https://filesamples.com/formats/ppm
and converted into the correct ppm format using ImageMagick.


#Class Diagrams
![alt text](diagram1.png)

![alt text](diagram2.png)

![alt text](diagram3.png)