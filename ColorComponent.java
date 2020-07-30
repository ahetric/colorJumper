/*
 * @author Amelia Hetrick
 * Last Updated: Dec 20 2019
 *
 * 
 */

public class ColorComponent {

   private static int component;
   
   private static boolean componentCompromised;
   
   public ColorComponent(int c) {
      component = c;
      
      isCompromised();
   }
   
   public boolean isCompromised() {
      if (component < 0 || component > 255)
         componentCompromised = true;
      componentCompromised = false;
      
      return componentCompromised;
   }
   
   public void setComponent(int c) {
      component = c;
   }
   
   public int getComponent() {
      return component;
   }
}