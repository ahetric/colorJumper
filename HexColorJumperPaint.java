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
   private static int calculateRGB(ColorComponent ofColor1, ColorComponent ofColor2) {
        int temp = Math.abs(ofColor2.getComponent() - ofColor1.getComponent());
        if (temp == 0x80) //is this necessary?
            temp = 0x7f;
        else if (temp == 0x7F)
            temp = 0x80;
        
        ColorComponent ofColor3 = new ColorComponent(0);
        
        
        
        switch (typeOfClosure) {
           case ROLLOVER:
              
              if (ofColor2.getComponent() >= ofColor1.getComponent())
                  ofColor3.setComponent(Math.abs(temp+ofColor2.getComponent()));
              else
                  ofColor3.setComponent(Math.abs(ofColor2.getComponent()-temp));
               
              color3inBounds = !ofColor3.isCompromised();
              
              ofColor3.setComponent(ofColor3.getComponent() % 0x100);
              break;
              
            case STOP_AT_BOUND:
               int rgb3temp = -1;
               if (ofColor2.getComponent() >= ofColor1.getComponent()) {
                  rgb3temp = Math.abs((temp+ofColor2.getComponent()));
                  ofColor3.setComponent(Math.min(rgb3temp, 0xFF));
               }
               else {
                  rgb3temp = Math.abs((temp-ofColor2.getComponent()));
                  ofColor3.setComponent(Math.max(rgb3temp, 0x00));
               }
                  
               color3inBounds = !ofColor3.isCompromised();
                  
               break;
            default:
               ofColor3.setComponent(0);
               
        }
        
        return ofColor3.getComponent();
   }
   
   
   // Do the main arithmetic
   public static String calculate(RGB color1, RGB color2) {
   
        //updateInputColors();
        
        // Adjust for edge cases
        /*initialRed = adjustEdgeCases(initialRed);
        midpointRed = adjustEdgeCases(midpointRed);
        initialGreen = adjustEdgeCases(initialGreen);
        midpointGreen = adjustEdgeCases(midpointGreen);
        initialBlue = adjustEdgeCases(initialBlue);
        midpointBlue = adjustEdgeCases(midpointBlue);*/



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
        
        RGB c1 = new RGB(new ColorComponent(255),new ColorComponent(0),new ColorComponent(0));
        RGB c2 = new RGB(new ColorComponent(0),new ColorComponent(255),new ColorComponent(0));
        
        HexColorJumperPaint jump = new HexColorJumperPaint(c1, c2);
        String c = jump.calculate(c1, c1);
        System.out.println("\n" + c);
    }
}