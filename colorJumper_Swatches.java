import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class colorJumper_Swatches {


   private static void drawString(Graphics g2, String text, int x, int y) {
        for (String line : text.split("\n"))
            g2.drawString(line, x, y += g2.getFontMetrics().getHeight());
    }


   protected static void paintThis(Graphics graph, int squareSize, int padSize, int r, int g, int b, String type) {
      Graphics2D g2 = (Graphics2D) graph;
      
      // Set location and size of square
      int x = padSize;
      if (type == "Initial") {
      //if (type == "Color 1") {
      }  
      else if (type == "Midpoint") {
         x = squareSize + padSize;             
      }
      else if (type == "Result") {
      //else if (type == "Color 2") {
         x = squareSize*2 + padSize;
      }
      int y = padSize;
      int width = squareSize;
      int height = squareSize;
      
      // Prepare text font
      g2.setFont(new Font("Arial", Font.BOLD, 12));
      FontMetrics textMetrics = g2.getFontMetrics();
      String text = "";
      
      // Set sqaure and text colors. Text color is set to white unless the square
      // color is itself close to white. (Then text color is set to black).
      Color square = new Color(r, g, b);
      Color label = new Color(255-r, 255-g, 255-b);
      g2.setColor(square);
      if ((r > 0xBB) || (g > 0xBB) || (b > 0xBB))
         label = Color.black;
      else
         label = Color.white;
      
      
      // Set text String
      String rString = String.format("%02X", r);
      String gString = String.format("%02X", g);
      String bString = String.format("%02X", b);
      text += "#" + rString + gString + bString;
      
      // Draw outline and fill in color for square
      g2.fillRect(x, y, width, height);
      g2.drawRect(x, y, width, height);
      
      // Draw text String inside square
      g2.setColor(label);
      int a = (int)(x + (width - textMetrics.stringWidth(text)) / 2);
      int c = (int)(y + ((height - textMetrics.getHeight()) / 2) + textMetrics.getAscent());
      drawString(g2, type + "\n" + text, a, c);
                        
   }



    public static void main(String[] args) {
    
      SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("colorJumper Swatches");
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                
                
                // size for width/height of squares, and border padding
                int squareSize = 100;
                int padSize = 50;
                
                
                
                frame.add(new JPanel() {
                    @Override
                    protected void paintComponent(Graphics graph) {
                    
                        int r = 0;
                        int g = 0;
                        int b = 0;
                        String type = "";
                        
                        
                        
                        String arr[] = {""};
                        ColorPickerSample.main(arr);
                        String input1 = ColorPickerSample.getColorPickedInitial();
                        String input2 = ColorPickerSample.getColorPickedMidpoint();
                        
                       
                        HexColorJumper jump = new HexColorJumper(input1, input2);
                        String c = jump.calculate(jump.getC1(), jump.getC2());
                      
                        r = jump.getR1_int();
                        g = jump.getG1_int();
                        b = jump.getB1_int();
                        type = "Initial";
                        //type = "Color 1";
                        paintThis(graph, squareSize, padSize, r, g, b, type);
                        
                        r = jump.getR2_int();
                        g = jump.getG2_int();
                        b = jump.getB2_int();
                        type = "Midpoint";
                        paintThis(graph, squareSize, padSize, r, g, b, type);
                        
                        r = jump.getRfinal();
                        g = jump.getGfinal();
                        b = jump.getBfinal();
                        type = "Result";
                        //type = "Color 2";
                        paintThis(graph, squareSize, padSize, r, g, b, type);
                        
                        System.out.println("Result: " + c);
                        
                        //
                        
                    }
                }, BorderLayout.CENTER);

                frame.pack();
                frame.setSize(new Dimension((squareSize*3 + padSize*2), (squareSize + (padSize*2 + padSize/2))));
                frame.setVisible(true);
            }
        });
    
    
    
    
    
    
    }
}