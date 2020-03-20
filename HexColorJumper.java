/*
 * @author Amelia Hetrick
 * Last Updated: Dec 20 2019
 */

import java.lang.Math;

public class HexColorJumper {

   private static String initial;
   private static String midpoint;
   
   private static int initialRed;
   private static int initialGreen;
   private static int initialBlue;
   
   private static int midpointRed;
   private static int midpointGreen;
   private static int midpointBlue;
   
   private static int resultRed;
   private static int resultGreen;
   private static int resultBlue;
   
   private static boolean withinBounds;
   
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
      initial = color1;
      midpoint = color2;
   }
   
   // Setter for initial
   public void setC1(String color1) {
      initial = color1;
   }
   
   // Setter for midpoint
   public void setC2(String color2) {
      midpoint = color2;
   }
   
   // Getter for initial
   public String getC1() {
      return initial;
   }
   
   // Getter for midpoint
   public String getC2() {
      return midpoint;
   }
   
   // Getter for initialRed
   public int getR1_int() {
      return initialRed;
   }
   
   // Getter for midpointRed
   public int getR2_int() {
      return midpointRed;
   }
   
   // Getter for resultRed
   public int getRfinal() {
      return resultRed;
   }
   
   // Getter for initialGreen
   public int getG1_int() {
      return initialGreen;
   }
   
   // Getter for midpointGreen
   public int getG2_int() {
      return midpointGreen;
   }
   
   // Getter for resultGreen
   public int getGfinal() {
      return resultGreen;
   }
   
   // Getter for initialBlue
   public int getB1_int() {
      return initialBlue;
   }
   
   // Getter for midpointBlue
   public int getB2_int() {
      return midpointBlue;
   }
   
   // Getter for resultBlue
   public int getBfinal() {
      return resultBlue;
   }
   
   public int getRGB(char mode, int number) {
      switch(mode) {
         case 'R':
            switch(number) {
               case 1:
                  return initialRed;
               case 2:
                  return midpointRed;
               case 3:
                  return resultRed;
            }
         case 'G':
            switch(number) {
               case 1:
                  return initialGreen;
               case 2:
                  return midpointGreen;
               case 3:
                  return resultGreen;
            }
         case 'B':
            switch(number) {
               case 1:
                  return initialBlue;
               case 2:
                  return midpointBlue;
               case 3:
                  return resultBlue;
            }
      }
      return 0x00;
   }
   
   public boolean getWithinBounds() {
      return withinBounds;
   }
   
   
   
   
   
   
   
   
   public static void updateInputColors() {
        // Extract RGB of color 1 in format "#rrggbb"
        String r1 = initial.substring(1,3);
        String g1 = initial.substring(3,5);
        String b1 = initial.substring(5,7);
        
        // Extract RGB of color 2 in format "#rrggbb"
        String r2 = midpoint.substring(1,3);
        String g2 = midpoint.substring(3,5);
        String b2 = midpoint.substring(5,7);
        
        // Convert RGB Strings of each color to ints
        initialRed = Integer.parseInt(r1, 16);
        midpointRed = Integer.parseInt(r2, 16);
        initialGreen = Integer.parseInt(g1, 16);
        midpointGreen = Integer.parseInt(g2, 16);
        initialBlue = Integer.parseInt(b1, 16);
        midpointBlue = Integer.parseInt(b2, 16);
   }
   
   // Adjust Edge Cases
   private static int adjustEdgeCases(int rgb) {
      if (rgb == 0x7F)
         rgb = 0x80;
      return rgb;
   }
   
   // Calculate new R, G, or B
   private static int calculateRGB(int rginitialBlue, int rgmidpointBlue) {
        int rgbtemp = Math.abs(rgmidpointBlue-rginitialBlue);
        if (rgbtemp == 0x80) //is this necessary?
            rgbtemp = 0x7f;
        else if (rgbtemp == 0x7F)
            rgbtemp = 0x80;
        
        int rgresultBlue;
        /*if (rgmidpointBlue >= rginitialBlue)
            rgresultBlue = Math.abs((rgbtemp+rgmidpointBlue))%0x100;
        else
            rgresultBlue = Math.abs((rgbtemp-rgmidpointBlue))%0x100;*/
        /*if (rgmidpointBlue >= rginitialBlue)
            rgresultBlue = Math.min(Math.abs((rgbtemp+rgmidpointBlue)), 0xFF);
        else
            rgresultBlue = Math.max(Math.abs((rgbtemp-rgmidpointBlue)), 0x00);*/
        
        
        switch (ensureClosure) {
           case "ROLLOVER":
              /*if (rgmidpointBlue >= rginitialBlue)
                  rgresultBlue = Math.abs((rgbtemp+rgmidpointBlue));
              else
                  rgresultBlue = Math.abs((rgbtemp-rgmidpointBlue));
              
              withinBounds = true;
               if (rgresultBlue > 0xFF || rgresultBlue < 0x00)
                  withinBounds = false;
              
              rgresultBlue = rgresultBlue % 0x100;
              break;*/
              
              if (rgmidpointBlue >= rginitialBlue)
                  rgresultBlue = Math.abs((rgbtemp+rgmidpointBlue));
              else
                  //rgresultBlue = Math.abs((rgbtemp-rgmidpointBlue));
                  rgresultBlue = Math.abs((rgmidpointBlue-rgbtemp));
              
              //withinBounds = true;
               if (rgresultBlue > 0xFF || rgresultBlue < 0x00)
                  withinBounds = false;
              
              rgresultBlue = rgresultBlue % 0x100;
              break;
              
            case "STOP_AT_BOUND":
               if (rgmidpointBlue >= rginitialBlue)
                  rgresultBlue = Math.min(Math.abs((rgbtemp+rgmidpointBlue)), 0xFF);
               else
                  rgresultBlue = Math.max(Math.abs((rgbtemp-rgmidpointBlue)), 0x00);
                  
               //withinBounds = true;
               if (rgresultBlue == 0xFF || rgresultBlue == 0x00)
                  withinBounds = false;
                  
               break;
            default:
               rgresultBlue = 0;
               
        }
        
        return rgresultBlue;
   }
   
   
   // Do the main arithmetic
   public static String calculate(String initial, String midpoint) {
   
        updateInputColors();
        
        // Adjust for edge cases
        initialRed = adjustEdgeCases(initialRed);
        midpointRed = adjustEdgeCases(midpointRed);
        initialGreen = adjustEdgeCases(initialGreen);
        midpointGreen = adjustEdgeCases(midpointGreen);
        initialBlue = adjustEdgeCases(initialBlue);
        midpointBlue = adjustEdgeCases(midpointBlue);

        // Calculate new R, G, and B; adjust for edge cases
        withinBounds = true;
        resultRed = calculateRGB(initialRed, midpointRed);
        resultGreen = calculateRGB(initialGreen, midpointGreen);
        resultBlue = calculateRGB(initialBlue, midpointBlue);
        
        // Convert new R, G, B to Strings for printing output
        String r = String.format("%02X", resultRed );
        String g = String.format("%02X", resultGreen );
        String b = String.format("%02X", resultBlue );
        
        // Concatenate new R, G, B
        String c = "#" + r + g + b;

        // Print outputs
        /*System.out.println("r1: " + r1 + ", r2: " + r2 + ", new R: " + String.format("%02X", resultRed ) );
        System.out.println("g1: " + g1 + ", g2: " + g2 + ", new G: " + String.format("%02X", resultGreen ) );
        System.out.println("b1: " + b1 + ", b2: " + b2 + ", new B: " + String.format("%02X", resultBlue ) );*/
        
        return c;
   
   }
   
   
   
    //runner within this class
    /*public static void main(String args[]) {
        HexColorJumper jump = new HexColorJumper("#FF0000", "#B3334D");
        String c = jump.calculate(initial, midpoint);
        System.out.println("\n" + c);
    }*/
}