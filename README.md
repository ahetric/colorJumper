# colorJumper

### What is colorJumper?

colorJumper works as a counterpart to a color blender. There are many color blenders available that return the midpoint of two color values. colorJumper takes two colors - an **initial color** and a **midpoint color** - and returns a **result** such that the midpoint of the **initial color** and the **result** is equal to the **midpoint color**. In other words, colorJumper returns a **result** such that the **initial color** and **result** are equidistant to the **midpoint**.

For example, color blenders behave like this:

 <sub>Parameter 1</sub> | <sub>Color Result</sub>| <sub>Parameter 2</sub> | <sub>Calculation</sub>
:----------------------:|:----------------------:|:----------------------:|:-----------
 Color1                 | Midpoint               | Color2                 | `Midpoint = midpoint(Color1,Color2)`

<img src="/README_images/color_blenders.png" width="300" />

However, colorJumper behaves like this:

 <sub>Parameter 1</sub> | <sub>Parameter 2</sub> | <sub>Color Result</sub> | <sub>Calculation</sub>
:----------------------:|:----------------------:|:-----------------------:|:-----------
 Initial                | Midpoint               | Result                  | `Result such that Midpoint = midpoint(Initial,Result)`

<img src="/README_images/colorJumper.png" width="300" />

---

### Features

colorJumper has a simple GUI. Upon launch, two random colors will be chosen as inputs, the result will be calculated, and all three colors will be displayed. Each color can be selected with a javafx color picker.

<img src="/README_images/see_colors.png" width="300" />

There is also a button on that will switch to displaying the three colors as a gradient rather than three distinct colors. The same button will return the original display.

<img src="/README_images/see_gradient.png" width="300" />

The user can also shuffle the two input colors.

---

### Problem with Closure

This program uses the RGB color model to calculate colors. As such, there will be many instances where the result cannot be properly displayed because it falls out of bounds of the allotted 0-255 range given to each component of RGB. The displayed result may look out of place.

When this happens, the square containing the result color will have a small notification button in the top right corner that can be clicked for more information. A text box will be displayed over the color squares telling the user which color(s) was out of gamut, or out of range.

<img src="/README_images/stop_at_bounds_out_of_gamut.png" width="300" />

The user is given two options to work around this issue: Stop at Bounds or Rollover.

The default option is "Stop at Bounds". With this setting, the component(s) of RGB that were out of range get capped at the lowest or highest value available. For example, if the red value was calculated to be -20, it would be returned as 0. If the red value were calculated to be 270, it would be returned as 255.

<img src="/README_images/stop_at_bounds.png" width="300" />

The other option is "Rollover" which takes the out of range value modulo 256. In this case, if the red value was calculated to be -20, it would be returned as 236. If the red value were calculated to be 270, it would be returned as 14.

<img src="/README_images/rollover.png" width="300" />

---

### How to run and explanation of files

Run `ColorJumperDemo.java` for the GUI.

---

### Code credit

###### ColorJumper.java
Base code for a color blender in Java by [Valentin Deleplace on StackOverflow].

Results of test cases determined with [Eric A. Meyer's Color Blender].




[Valentin Deleplace on StackOverflow]: https://stackoverflow.com/a/14482509
[Eric A. Meyer's Color Blender]: https://meyerweb.com/eric/tools/color-blend/