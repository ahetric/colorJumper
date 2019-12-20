# colorJumper

_Current support for hexadecimal representation of colors only._

colorJumper works as the opposite of a color blender. There are many color blenders available that take two colors and return the midpoint of their RBG values. colorJumper takes two colors - one "extreme" color and one "midpoint" - and returns a result, such that the midpoint of the initial "extreme" color and the result is equal to the initial "midpoint" input.

For example, color blenders behave like this:

Parameter 1 | Parameter 2 | Result
----------- | ----------- | ------
"Extreme" color 1 | "Extreme" color 2 | Midpoint
White | Black | Gray

However, colorJumper behaves like this:

Parameter 1 | Parameter 2 | Result
----------- | ----------- | ------
"Extreme" color 1 | Midpoint | "Extreme" color 2
White | Gray | Black

---

Base code for a color blender in Java by [Valentin Deleplace on StackOverflow].\
Results of test cases determined with [Eric A. Meyer's Color Blender].\
Base code for rectangle 2D graphics in Java by [Wayan Saryada on Kode Java].\
Method for handling new lines with Java Graphics2D drawString by [Andreas Lundblad on StackOverflow].



[Valentin Deleplace on StackOverflow]: https://stackoverflow.com/a/14482509
[Eric A. Meyer's Color Blender]: https://meyerweb.com/eric/tools/color-blend/
[Wayan Saryada on Kode Java]: https://kodejava.org/how-do-i-draw-a-rectangle-in-java-2d/
[Andreas Lundblad on StackOverflow]: https://stackoverflow.com/a/4413153