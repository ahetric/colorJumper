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

For now, run colorJumper_Swatches.java with no CLI; the parameters are hardcoded in the main method. I have plans to add in CLI in the near future. Currently, this program only works with hexadecimal representation of colors as input/output. I have tentative plans to add RGB representation along with CLI, but that is low priority.

`colorJumper_Swatches.java` is the runner, handles the graphics, and calls HexColorJumper.java.\
`HexColorJumper.java` does the calculations.\
`HexColorJumperTest.java` consists of JUnit tests.

---

### A note about the color gray

A "pure gray" `midpoint(black,white)` used as a midpoint will return the complement of the initial color. Example:

<img src="/README_images/pureGrayCreatesComplement.png" width="300" />

However, not every (gray) color is achievable as a blend of only two colors. Using a gray that is darker or lighter than "pure gray" may not give the intended result. As far as I know, there is no remedy to this problem, as it is a consequence of additive mixing of two colors. Some examples:

<img src="/README_images/offGrayDarkCreatesMisc.png" width="300" />
<img src="/README_images/offGrayLightCreatesMisc.png" width="300" />

---

### Code credit

###### HexColorJumper.java
Base code for a color blender in Java by [Valentin Deleplace on StackOverflow].

###### HexColorJumperTest.java
Results of test cases determined with [Eric A. Meyer's Color Blender].

###### colorJumper_Swatches.java
Base code for rectangle 2D graphics in Java by [Wayan Saryada on Kode Java].\
Method for handling new lines with Java Graphics2D drawString by [Andreas Lundblad on StackOverflow].




[Valentin Deleplace on StackOverflow]: https://stackoverflow.com/a/14482509
[Eric A. Meyer's Color Blender]: https://meyerweb.com/eric/tools/color-blend/
[Wayan Saryada on Kode Java]: https://kodejava.org/how-do-i-draw-a-rectangle-in-java-2d/
[Andreas Lundblad on StackOverflow]: https://stackoverflow.com/a/4413153