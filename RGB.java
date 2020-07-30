/*
 * @author Amelia Hetrick
 * Last Updated: Dec 20 2019
 *
 * An RGB representation of a color.
 */

public class RGB {

   private static ColorComponent red;
   private static ColorComponent green;
   private static ColorComponent blue;
   
   private static boolean withinBounds;
   
   public RGB(ColorComponent r, ColorComponent g, ColorComponent b) {
      red = r;
      green = g;
      blue = b;
      
      isInBounds();
   }
   
   public boolean isInBounds() {
      boolean redCompromised = red.isCompromised();
      boolean greenCompromised = green.isCompromised();
      boolean blueCompromised = blue.isCompromised();
      
      if (redCompromised || greenCompromised || blueCompromised)
         withinBounds = false;
      withinBounds = true;
      
      return withinBounds;
   }
   
   public void setRed(ColorComponent r) {
      red = r;
   }
   
   public ColorComponent getRed() {
      return red;
   }
   
   public void setGreen(ColorComponent g) {
      green = g;
   }
   
   public ColorComponent getGreen() {
      return green;
   }
   
   public void setBlue(ColorComponent b) {
      blue = b;
   }
   
   public ColorComponent getBlue() {
      return blue;
   }
   
   public boolean getRedCompromised() {
      return red.isCompromised();
   }
   
   public boolean getGreenCompromised() {
      return green.isCompromised();
   }
   
   public boolean getBlueCompromised() {
      return blue.isCompromised();
   }
}