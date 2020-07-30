/*
 * @author Amelia Hetrick
 * Last Updated: Dec 20 2019
 */

import java.lang.Math;

import javafx.scene.paint.Color;

public class HexColorJumperPaint {

   private static RGB color1;
   private static RGB color2;
   
   private static RGB color3;
   
   private static boolean color3inBounds;
   
   private static Closure typeOfClosure = Closure.ROLLOVER;
   
   // enumerate the possible ways of dealing with operations
   // in the range 0-255 not being closed under addition/subtraction
   // ROLLOVER: mod the result by 255 to remain in the proper range
   // STOP_AT_BOUND: if a number were to fall out of the range, instead 'cap' it at the most extreme possible value
   public void setClosure(Closure c) {
      typeOfClosure = c;
   }
   
   // Constructor
   public HexColorJumperPaint(RGB c1, RGB c2) {
      color1 = c1;
      color2 = c2;
   }
   
   // Setter for initial
   public void setC1(RGB c1) {
      color1 = c1;
   }
   
   // Setter for midpoint
   public void setC2(RGB c2) {
      color2 = c2;
   }
   
   // Getter for initial
   public RGB getC1() {
      return color1;
   }
   
   // Getter for midpoint
   public RGB getC2() {
      return color2;
   }
   
   
   
   
   
   /*public static void updateInputColors() {
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
   }*/
   
   // Adjust Edge Cases
   private static int adjustEdgeCases(int rgb) {
      if (rgb == 0x7F)
         rgb = 0x80;
      return rgb;
   }
   
   // Calculate new R, G, or B
   private static int calculateRGB(int componentOfColor1, int componentOfColor2) {
        int temp = Math.abs(componentOfColor2 - componentOfColor1);
        if (temp == 0x80) //is this necessary?
            temp = 0x7f;
        else if (temp == 0x7F)
            temp = 0x80;
        
        int componentOfColor3;
        
        
        
        switch (typeOfClosure) {
           case ROLLOVER:
              
              if (componentOfColor2 >= componentOfColor1)
                  componentOfColor3 = Math.abs((temp+componentOfColor2));
              else
                  componentOfColor3 = Math.abs((componentOfColor2-temp));
               
              color3inBounds = componentOfColor3.isInBounds();
              
              componentOfColor3 = componentOfColor3 % 0x100;
              break;
              
            case STOP_AT_BOUND:
               int rgb3temp = -1;
               if (componentOfColor2 >= componentOfColor1) {
                  rgb3temp = Math.abs((temp+componentOfColor2));
                  componentOfColor3 = Math.min(rgb3temp, 0xFF);
               }
               else {
                  rgb3temp = Math.abs((temp-componentOfColor2));
                  componentOfColor3 = Math.max(rgb3temp, 0x00);
               }
                  
               color3inBounds = componentOfColor3.isInBounds();
                  
               break;
            default:
               componentOfColor = 0;
               
        }
        
        return componentOfColor3;
   }
   
   
   // Do the main arithmetic
   public static String calculate(String initial, String midpoint) {
   
        //updateInputColors();
        
        // Adjust for edge cases
        initialRed = adjustEdgeCases(initialRed);
        midpointRed = adjustEdgeCases(midpointRed);
        initialGreen = adjustEdgeCases(initialGreen);
        midpointGreen = adjustEdgeCases(midpointGreen);
        initialBlue = adjustEdgeCases(initialBlue);
        midpointBlue = adjustEdgeCases(midpointBlue);



        int resultRed = calculateRGB(color1.getRed(), color2.getRed());
        int resultGreen = calculateRGB(color1.getGreen(), color2.getGreen());
        int resultBlue = calculateRGB(color1.getBlue(), color2.getBlue());
        
        
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
        //HexColorJumper jump = new HexColorJumper("#FF0000", "#B3334D");
        HexColorJumperPaint jump = new HexColorJumperPaint(new RGB(255,0,0), new RGB(0,255,0));
        String c = jump.calculate(color1, color2);
        System.out.println("\n" + c);
    }
}