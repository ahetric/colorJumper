/*
 * @author Amelia Hetrick
 * Last Updated: Dec 20 2019
 */

import java.lang.Math;

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
   
   // Getter for result
   public RGB getC3() {
      return color3;
   }
   
   
   // Adjust Edge Cases
   private static ColorComponent adjustEdgeCases(ColorComponent ofColor) {
      if (ofColor.getComponent() == 0x7F)
         ofColor.setComponent(0x80);
      return ofColor;
   }
   
   // Calculate new R, G, or B
   private static ColorComponent calculateComponent(ColorComponent ofColor1, ColorComponent ofColor2) {
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
        
        return ofColor3;
   }
   
   
   // Do the main arithmetic
   public static String calculate(RGB c1, RGB c2) {
   
        
        color1.setRed(adjustEdgeCases(c1.getRed()));
        color1.setGreen(adjustEdgeCases(c1.getGreen()));
        color1.setBlue(adjustEdgeCases(c1.getBlue()));
        color2.setRed(adjustEdgeCases(c2.getRed()));
        color2.setGreen(adjustEdgeCases(c2.getGreen()));
        color2.setBlue(adjustEdgeCases(c2.getBlue()));



        ColorComponent resultRed = calculateComponent(color1.getRed(), color2.getRed());
        ColorComponent resultGreen = calculateComponent(color1.getGreen(), color2.getGreen());
        ColorComponent resultBlue = calculateComponent(color1.getBlue(), color2.getBlue());
        
        
        // Convert new R, G, B to Strings for printing output
        String r = String.format("%02X", resultRed.getComponent() );
        String g = String.format("%02X", resultGreen.getComponent() );
        String b = String.format("%02X", resultBlue.getComponent() );
        
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
        
        //RGB c1 = new RGB(new ColorComponent(255),new ColorComponent(0),new ColorComponent(0));
        //RGB c2 = new RGB(new ColorComponent(0),new ColorComponent(255),new ColorComponent(0));
        
        RGB c1 = new RGB(new ColorComponent(0xFF),new ColorComponent(0),new ColorComponent(0));
        RGB c2 = new RGB(new ColorComponent(0xB3),new ColorComponent(0x33),new ColorComponent(0x4D));
        
        HexColorJumperPaint jump = new HexColorJumperPaint(c1, c2);
        String c = jump.calculate(c1, c2);
        System.out.println("\n" + c);
    }
}