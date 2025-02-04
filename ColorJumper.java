/*
 * @author Amelia Hetrick
 * Last Updated: Sept 5 2020
 *
 * Calculations for colorJumper
 */

import java.lang.Math;

public class ColorJumper {

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
   private static boolean compromised;
   private static boolean redCompromised;
   private static boolean greenCompromised;
   private static boolean blueCompromised;
   
   private static Closure typeOfClosure = Closure.STOP_AT_BOUND;
   
   // enumerate the possible ways of dealing with operations
   // in the range 0-255 not being closed under addition/subtraction
   // ROLLOVER: mod the result by 255 to remain in the proper range
   // STOP_AT_BOUND: if a number were to fall out of the range, instead cap it at the lowest/highest possible value
   public void setClosure(Closure c) {
      typeOfClosure = c;
   }
   
   // Constructor
   public ColorJumper() {
      // Purposefully empty, use setters later
   }
   
   // Constructor
   public ColorJumper(String color1, String color2) {
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
   
   public boolean getRedCompromised() {
      return redCompromised;
   }
   
   public boolean getGreenCompromised() {
      return greenCompromised;
   }
   
   public boolean getBlueCompromised() {
      return blueCompromised;
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
   private static int calculateRGB(int rgb1, int rgb2) {
        int rgbtemp = Math.abs(rgb2-rgb1);
        if (rgbtemp == 0x80) //is this necessary?
            rgbtemp = 0x7f;
        else if (rgbtemp == 0x7F)
            rgbtemp = 0x80;
        
        int rgb3;
        
        
        switch (typeOfClosure) {
           case ROLLOVER:
              
              if (rgb2 >= rgb1)
                  rgb3 = Math.abs((rgbtemp+rgb2));
              else
                  rgb3 = Math.abs((rgb2-rgbtemp));
              
              //withinBounds = true;
               if (rgb3 > 0xFF || rgb3 < 0x00) {
                  withinBounds = false;
                  compromised = true;
               }
              
              rgb3 = rgb3 % 0x100;
              break;
              
            case STOP_AT_BOUND:
               int rgb3temp = -1;
               if (rgb2 >= rgb1) {
                  rgb3temp = Math.abs((rgbtemp+rgb2));
                  rgb3 = Math.min(rgb3temp, 0xFF);
               }
               else {
                  rgb3temp = Math.abs((rgbtemp-rgb2));
                  rgb3 = Math.max(rgb3temp, 0x00);
               }
                  
               if (rgb3temp > 0xFF || rgb3temp < 0x00) {
                  withinBounds = false;
                  compromised = true;
               }
                  
               break;
            default:
               rgb3 = 0;
               
        }
        
        return rgb3;
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
        compromised = false;
        redCompromised = false;
        greenCompromised = false;
        blueCompromised = false;
        resultRed = calculateRGB(initialRed, midpointRed);
        if (compromised)
            redCompromised = true;
        compromised = false;
        resultGreen = calculateRGB(initialGreen, midpointGreen);
        if (compromised)
            greenCompromised = true;
        compromised = false;
        resultBlue = calculateRGB(initialBlue, midpointBlue);
        if (compromised)
            blueCompromised = true;
        compromised = false;
        
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
    public static void main(String args[]) {
        ColorJumper jump = new ColorJumper("#FF0000", "#B3334D");
        //ColorJumper jump = new ColorJumper("#FE0000", "#FF0000");
        String c = jump.calculate(initial, midpoint);
        System.out.println("\n" + c);
        
        if (redCompromised || greenCompromised || blueCompromised)
            System.out.println("Note - this color is out of gamut and has been capped at a lower or upper bound.");
    }
}