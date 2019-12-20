# colorJumper

_Current support for hexadecimal representation of colors only._  
_Be wary of grays used as midpoint colors: While you can find a color's complement using a pure gray (0x080808) as a midpoint, other grays may not give intended results. Not every (gray) color is achievable as a blend of only two colors._

colorJumper works as the opposite of a color blender. There are many color blenders available that take two colors and return the midpoint of their RBG values. colorJumper takes two colors - one "extreme" color and one "midpoint" - and returns a result, such that the midpoint of the initial "extreme" color and the result is equal to the initial "midpoint" input.

For example, color blenders behave like this:

Parameter 1 | Result   | Parameter 2 | Calculation
----------- | ------   | ----------- |:-----------
Color 1     | Midpoint | Color 2     | `Result = midpoint(Color1,Color2)`
White       | Gray     | Black       | `Gray   = midpoint(White , Black) = Gray`

<img src="/README_images/colorBlenders.png" width="300" />

However, colorJumper behaves like this:

Parameter 1 | Parameter 2 | Result  | Calculation
----------- | ----------- | ------  |:-----------
Color 1     | Midpoint    | Color 2 | `Result = Color2 such that Midpoint = midpoint(Color1,Color2)`
White       | Gray        | Black   | `Result = Black  such that Gray     = midpoint(White , Black)`

<img src="/README_images/colorJumper.png" width="300" />

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