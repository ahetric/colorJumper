/*
 * @author Amelia Hetrick
 * Last Updated: Dec 20 2019
 *
 * An RGB representation of a color.
 */

public class RGB {

   private static int red;
   private static int green;
   private static int blue;
   
   private static boolean redCompromised;
   private static boolean greenCompromised;
   private static boolean blueCompromised;
   
   private static boolean withinBounds;
   
   public RGB(int r, int g, int b) {
      red = r;
      green = g;
      blue = b;
      
      isInBounds();
   }
   
   public boolean isInBounds() {
      if (red < 0 || red > 255)
         redCompromised = true;
      redCompromised = false;
      
      if (green < 0 || green > 255)
         greenCompromised = true;
      greenCompromised = false;
      
      if (blue < 0 || blue > 255)
         blueCompromised = true;
      blueCompromised = false;
      
      if (redCompromised || greenCompromised || blueCompromised)
         withinBounds = false;
      withinBounds = true;
      
      return withinBounds;
   }
   
   public void setRed(int r) {
      red = r;
   }
   
   public int getRed() {
      return red;
   }
   
   public void setGreen(int g) {
      green = g;
   }
   
   public int getGreen() {
      return green;
   }
   
   public void setBlue(int b) {
      blue = b;
   }
   
   public int getBlue() {
      return blue;
   }
   
   public void setRedCompromised(boolean c) {
      redCompromised = c;
   }
   
   public boolean getRedCompromised() {
      return redCompromised;
   }
   
   public void setGreenCompromised(boolean c) {
      greenCompromised = c;
   }
   
   public boolean getGreenCompromised() {
      return greenCompromised;
   }
   
   public void setBlueCompromised(boolean c) {
      blueCompromised = c;
   }
   
   public boolean getBlueCompromised() {
      return blueCompromised;
   }
}