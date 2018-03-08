# randimg

Randimg is a Java program used to constantly generate random images. To save storage space, it does not save files (yet) but instead repeatedly displays them on screen at a set interval. It takes advantage of Java's `ThreadLocalRandom` class to efficiently create random numbers to build images off of.

## Features

Randimg is still fairly new, and is primarily a side project, so there are not far too many features. It does allow:

- Changing the minimum and maximum RGB values for the pixels of the image
- Changing how quickly a new image is generated

More features are on the way.

## Installation

All you need to run Randimg is Java 8 or above on whatever system you prefer. However, more RAM is recommended for higher resolution screens. The processor in your system also affects how quickly the images can be generated. Once Java is installed, just double-click the Jar file in the `bin/` folder to run it.

## FAQ

**Q.** Can it be used as a library?

**A.** Not at this time, but I am working on that.

**Q.** How do I save an image?

**A.** At this time you cannot save images.

**Q.** Can it generate shapes?

**A.** No. And I probably won't work on them because that is too much work for just a side project.