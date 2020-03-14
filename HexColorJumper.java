/*
 * @author Amelia Hetrick
 * Last Updated: Dec 20 2019
 */

import java.lang.Math;

public class HexColorJumper {

   private static String c1;
   private static String c2;
   private static int r1_int;
   private static int r2_int;
   private static int rfinal;
   private static int g1_int;
   private static int g2_int;
   private static int gfinal;
   private static int b1_int;
   private static int b2_int;
   private static int bfinal;
   
   private static boolean withinBounds = true;
   
   // enumerate the possible ways of dealing with operations
   // in the range 0-255 not being closed under addition/subtraction
   // ROLLOVER: mod the result by 255 to remain in the proper range
   // STOP_AT_BOUND: if a number were to fall out of the range, instead 'cap' it at the most extreme possible value
   //public static enum Closure {ROLLOVER, STOP_AT_BOUND};
   
   private static String ensureClosure;
   public void setClosure(String c) {
      ensureClosure = c;
   }
   
   // Constructor
   public HexColorJumper() {
      // Purposefully empty, use setters later
   }
   
   // Constructor
   public HexColorJumper(String color1, String color2) {
      c1 = color1;
      c2 = color2;
   }
   
   // Setter for c1
   public void setC1(String color1) {
      c1 = color1;
   }
   
   // Setter for c2
   public void setC2(String color2) {
      c2 = color2;
   }
   
   // Getter for c1
   public String getC1() {
      return c1;
   }
   
   // Getter for c2
   public String getC2() {
      return c2;
   }
   
   // Getter for r1_int
   public int getR1_int() {
      return r1_int;
   }
   
   // Getter for r2_int
   public int getR2_int() {
      return r2_int;
   }
   
   // Getter for rfinal
   public int getRfinal() {
      return rfinal;
   }
   
   // Getter for g1_int
   public int getG1_int() {
      return g1_int;
   }
   
   // Getter for g2_int
   public int getG2_int() {
      return g2_int;
   }
   
   // Getter for gfinal
   public int getGfinal() {
      return gfinal;
   }
   
   // Getter for b1_int
   public int getB1_int() {
      return b1_int;
   }
   
   // Getter for b2_int
   public int getB2_int() {
      return b2_int;
   }
   
   // Getter for bfinal
   public int getBfinal() {
      return bfinal;
   }
   
   public int getRGB(String mode, int number) {
      switch(mode) {
         case "R":
            switch(number) {
               case 1:
                  return r1_int;
               case 2:
                  return r2_int;
               case 3:
                  return rfinal;
            }
         case "G":
            switch(number) {
               case 1:
                  return g1_int;
               case 2:
                  return g2_int;
               case 3:
                  return gfinal;
            }
         case "B":
            switch(number) {
               case 1:
                  return b1_int;
               case 2:
                  return b2_int;
               case 3:
                  return bfinal;
            }
      }
      return 0x00;
   }
   
   public boolean getWithinBounds() {
      return withinBounds;
   }
   
   
   
   
   
   
   
   
   public static void updateInputColors() {
        // Extract RGB of color 1 in format "#rrggbb"
        String r1 = c1.substring(1,3);
        String g1 = c1.substring(3,5);
        String b1 = c1.substring(5,7);
        
        // Extract RGB of color 2 in format "#rrggbb"
        String r2 = c2.substring(1,3);
        String g2 = c2.substring(3,5);
        String b2 = c2.substring(5,7);
        
        // Convert RGB Strings of each color to ints
        r1_int = Integer.parseInt(r1, 16);
        r2_int = Integer.parseInt(r2, 16);
        g1_int = Integer.parseInt(g1, 16);
        g2_int = Integer.parseInt(g2, 16);
        b1_int = Integer.parseInt(b1, 16);
        b2_int = Integer.parseInt(b2, 16);
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
        
        int rgbfinal;
        /*if (rgb2_int >= rgb1_int)
            rgbfinal = Math.abs((rgbtemp+rgb2_int))%0x100;
        else
            rgbfinal = Math.abs((rgbtemp-rgb2_int))%0x100;*/
        /*if (rgb2_int >= rgb1_int)
            rgbfinal = Math.min(Math.abs((rgbtemp+rgb2_int)), 0xFF);
        else
            rgbfinal = Math.max(Math.abs((rgbtemp-rgb2_int)), 0x00);*/
        
        
        switch (ensureClosure) {
           case "ROLLOVER":
              if (rgb2_int >= rgb1_int)
                  rgbfinal = Math.abs((rgbtemp+rgb2_int));
              else
                  rgbfinal = Math.abs((rgbtemp-rgb2_int));
              
              withinBounds = true;
               if (rgbfinal > 0xFF || rgbfinal < 0x00)
                  withinBounds = false;
              
              rgbfinal = rgbfinal % 0x100;
              break;
              
            case "STOP_AT_BOUND":
               if (rgb2_int >= rgb1_int)
                  rgbfinal = Math.min(Math.abs((rgbtemp+rgb2_int)), 0xFF);
               else
                  rgbfinal = Math.max(Math.abs((rgbtemp-rgb2_int)), 0x00);
                  
               withinBounds = true;
               if (rgbfinal == 0xFF || rgbfinal == 0x00)
                  withinBounds = false;
                  
               break;
            default:
               rgbfinal = 0;
               
        }
        
        return rgbfinal;
   }
   
   
   // Do the main arithmetic
   public static String calculate(String c1, String c2) {
   
        updateInputColors();
        
        // Adjust for edge cases
        r1_int = adjustEdgeCases(r1_int);
        r2_int = adjustEdgeCases(r2_int);
        g1_int = adjustEdgeCases(g1_int);
        g2_int = adjustEdgeCases(g2_int);
        b1_int = adjustEdgeCases(b1_int);
        b2_int = adjustEdgeCases(b2_int);

        // Calculate new R, G, and B; adjust for edge cases
        rfinal = calculateRGB(r1_int, r2_int);
        gfinal = calculateRGB(g1_int, g2_int);
        bfinal = calculateRGB(b1_int, b2_int);
        
        // Convert new R, G, B to Strings for printing output
        String r = String.format("%02X", rfinal );
        String g = String.format("%02X", gfinal );
        String b = String.format("%02X", bfinal );
        
        // Concatenate new R, G, B
        String c = "#" + r + g + b;

        // Print outputs
        /*System.out.println("r1: " + r1 + ", r2: " + r2 + ", new R: " + String.format("%02X", rfinal ) );
        System.out.println("g1: " + g1 + ", g2: " + g2 + ", new G: " + String.format("%02X", gfinal ) );
        System.out.println("b1: " + b1 + ", b2: " + b2 + ", new B: " + String.format("%02X", bfinal ) );*/
        
        return c;
   
   }
   
   
   
    //runner within this class
    /*public static void main(String args[]) {
        HexColorJumper jump = new HexColorJumper("#FF0000", "#B3334D");
        String c = jump.calculate(c1, c2);
        System.out.println("\n" + c);
    }*/
}