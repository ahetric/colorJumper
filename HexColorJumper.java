import java.lang.Math;
/*
 * The opposite of a hex color blender. Typical color blenders
 * take the midpoint of two extreme colors. For example, midpoint
 * of Black and White is Gray. This program takes two colors,
 * one extreme like White and one midpoint like Gray, and finds
 * the other extreme, like Black.
 *
 * Color 1 must be the extreme color, color 2 must be the midpoint.
 * Output is the other extreme that blends with Color 1 to produce
 * color 2.
 *
 * Example usage:
 *  String c1 = "#FFFF00"; // Yellow
 *  String c2 = "#FF8080"; // Magenta
 *     //Result: #FF8080   // Salmon
 *
 * Note: if color is way off, try changing sign of: xfinal = (xtemp+x2_int)
 * It may just be going the "wrong direction"
 */

public class HexColorJumper {

   private static String c1;
   private static String c2;
   
   // Constructor
   public HexColorJumper() {
      // Purposefully empty
   }
   
   // Constructor
   public HexColorJumper(String color1, String color2) {
      c1 = color1;
      c2 = color2;
   }
   
   // Setter for C1
   public void setC1(String color1) {
      c1 = color1;
   }
   
   // Setter for C2
   public void setC2(String color2) {
      c2 = color2;
   }
   
   // Getter for C1
   public String getC1() {
      return c1;
   }
   
   // Getter for C2
   public String getC2() {
      return c2;
   }
   
   // Adjust Edge Cases
   private static int adjustEdgeCases(int rgb) {
      if (rgb == 0x7F)
         rgb = 0x80;
      return rgb;
   }
   
   // Calculate new R, G, or B
   private static int calculateRGB(int rgb1_int, int rgb2_int) {
        int rgbtemp = Math.abs(rgb2_int-rgb1_int);
        if (rgbtemp == 0x80) //is this necessary?
            rgbtemp = 0x7f;
        else if (rgbtemp == 0x7F)
            rgbtemp = 0x80;
        
        int sign = (rgbtemp+rgb2_int);
        System.out.println(sign);
        int rgbfinal;
        if (rgb2_int >= rgb1_int)
            rgbfinal = Math.abs((rgbtemp+rgb2_int))%0x100;
        else
            rgbfinal = Math.abs((rgbtemp-rgb2_int))%0x100;
        
        return rgbfinal;
   }
   
   
   // Do the main arithmetic
   public static String calculate(String c1, String c2) {
        
        // Extract RGB of color 1
        String r1 = c1.substring(1,3);
        String g1 = c1.substring(3,5);
        String b1 = c1.substring(5,7);
        
        // Extract RGB of color 2
        String r2 = c2.substring(1,3);
        String g2 = c2.substring(3,5);
        String b2 = c2.substring(5,7);
        
        // Convert RGB Strings of each color to ints
        int r1_int = Integer.parseInt(r1, 16);
        int r2_int = Integer.parseInt(r2, 16);
        int g1_int = Integer.parseInt(g1, 16);
        int g2_int = Integer.parseInt(g2, 16);
        int b1_int = Integer.parseInt(b1, 16);
        int b2_int = Integer.parseInt(b2, 16);
        
        // Adjust for edge cases
        r1_int = adjustEdgeCases(r1_int);
        r2_int = adjustEdgeCases(r2_int);
        g1_int = adjustEdgeCases(g1_int);
        g2_int = adjustEdgeCases(g2_int);
        b1_int = adjustEdgeCases(b1_int);
        b2_int = adjustEdgeCases(b2_int);

        // Calculate new R, G, and B; adjust for edge cases
        int rfinal = calculateRGB(r1_int, r2_int);
        int gfinal = calculateRGB(g1_int, g2_int);
        int bfinal = calculateRGB(b1_int, b2_int);
        
        // Convert new R, G, B to Strings for printing output
        String r = String.format("%02X", rfinal );
        String g = String.format("%02X", gfinal );
        String b = String.format("%02X", bfinal );
        
        // Concatenate new R, G, B
        String c = "#" + r + g + b;

        // Print outputs
        System.out.println("r1: " + r1 + ", r2: " + r2 + ", new R: " + String.format("%02X", rfinal ) );
        System.out.println("g1: " + g1 + ", g2: " + g2 + ", new G: " + String.format("%02X", gfinal ) );
        System.out.println("b1: " + b1 + ", b2: " + b2 + ", new B: " + String.format("%02X", bfinal ) );
        //System.out.println("\n" + c);
        
        return c;
   
   }
   
   
   
    
    public static void main(String args[]) {
        HexColorJumper jump = new HexColorJumper("#FF0000", "#B3334D");
        String c = calculate(c1, c2);
        System.out.println("\n" + c);
    }
}