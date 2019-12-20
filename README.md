# colorJumper

### What is colorJumper?

colorJumper works as a counterpart to a color blender. There are many color blenders available that return the midpoint of two color values. colorJumper takes two colors - an **initial color** and a **midpoint color** - and returns a **result** such that the midpoint of the **initial color** and the **result** is equal to the **midpoint color**.

For example, color blenders behave like this:

Parameter 1 | Result   | Parameter 2 | Calculation
----------- | ------   | ----------- |:-----------
Color1      | Midpoint | Color2      | `Result = Midpoint = midpoint(Color1,Color2)`

<img src="/README_images/colorBlenders.png" width="300" />

However, colorJumper behaves like this:

Parameter 1 | Parameter 2 | Result | Calculation
----------- | ----------- | ------ |:-----------
Color1      | Midpoint    | Color2 | `Result = Color2 such that Midpoint = midpoint(Color1,Color2)`

<img src="/README_images/colorJumper.png" width="300" />

---

### How to run

For now, run colorJumper_Swatches.java with no CLI, and the parameters hardcoded in the main method. I have plans to allow for CLI in the near future. Additionally, there is current support for hexadecimal representation of colors only.

---

### A note about the color gray

A "pure gray" (midpoint(black,white) = midpoint(0x000000,0xffffff) = 0x808080) used as a midpoint will return the complement of the initial color. Example:

<img src="/README_images/pureGrayCreatesComplement.png" width="300" />

However, not every (gray) color is achievable as a blend of only two colors. Using a gray that is darker or lighter than "pure gray" may not give the intended result. As far as I know, there is no remedy to this problem, as it is a consequence of additive mixing of two colors. Some examples:

<img src="/README_images/offGrayDarkCreatesMisc.png" width="300" />
<img src="/README_images/offGrayLightCreatesMisc.png" width="300" />

---

### Code Credit

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