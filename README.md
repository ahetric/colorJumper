# colorJumper

### What is colorJumper?

colorJumper works as a counterpart to a color blender. There are many color blenders available that return the midpoint of two color values. colorJumper takes two colors - an **initial color** and a **midpoint color** - and returns a **result** such that the midpoint of the **initial color** and the **result** is equal to the **midpoint color**.

For example, color blenders behave like this:

 Parameter 1 | Output   | Parameter 2 | Calculation
:-----------:|:--------:|:-----------:|:-----------
 Color1      | Midpoint | Color2      | `Midpoint = midpoint(Color1,Color2)`

<img src="/README_images/colorBlenders.png" width="300" />

However, colorJumper behaves like this:

 <sub>Parameter 1</sub> | Parameter 2 | Output | Calculation
:-----------:|:-----------:|:--------:|:-----------
 Initial     | Midpoint    | Result   | `Result such that Midpoint = midpoint(Initial,Result)`

<img src="/README_images/colorJumper.png" width="300" />

---

### How to run and explanation of files

For now, run colorJumper_Swatches.java with no CLI; the parameters are hardcoded in the main method. I have plans to add in CLI in the near future. Currently, this program only works with hexadecimal representation of colors as input/output. I have tentative plans to add RGB representation along with CLI, but that is low priority.

_colorJumper_Swatches.java_ is the runner, handles the graphics, and calls HexColorJumper.java.\
_HexColorJumper.java_ does the calculations.\
_HexColorJumperTest.java_ consists of JUnit tests.

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