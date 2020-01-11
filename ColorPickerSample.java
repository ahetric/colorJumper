/*
//https://docs.oracle.com/javase/8/javafx/user-interface-tutorial/color-picker.htm
import javafx.application.Application;
import javafx.event.*;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
 
public class ColorPickerSample extends Application {  








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
      g2.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 12));
      FontMetrics textMetrics = g2.getFontMetrics();
      String text = "";
      
      // Set sqaure and text colors. Text color is set to white unless the square
      // color is itself close to white. (Then text color is set to black).
      java.awt.Color square = new java.awt.Color(r, g, b);
      java.awt.Color label = java.awt.Color.black;
      g2.setColor(square);
      if ((r > 0xBB) || (g > 0xBB) || (b > 0xBB))
         label = java.awt.Color.black;
      else
         label = java.awt.Color.white;
      
      
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












   private static String colorPicked;
   
   public void setColorPicked(String newColor) {
      colorPicked = newColor;
   } 
   
   public static String getColorPicked() {
      return colorPicked;
   } 
   


   private String RGBtoHex(Color color) {
      return String.format("#%02X%02X%02X",
    (int)(color.getRed()*255),
    (int)(color.getGreen()*255),
    (int)(color.getBlue()*255));
   
   }
 
    public static void main(String[] args) {
        launch(args);
    }
 
    @Override
    public void start(Stage stage) {
    
        int squareSize = 100;
        int padSize = 50;
    
        stage.setTitle("ColorPicker");
        Scene scene = new Scene(new HBox(20), 400, 100);
        HBox box = (HBox) scene.getRoot();
        box.setPadding(new Insets(5, 5, 5, 5));          
        final ColorPicker colorPicker = new ColorPicker();
        colorPicker.setValue(Color.CORAL);
        
        final Text text = new Text("Try the color picker!");
        text.setFont(javafx.scene.text.Font.font ("Verdana", 20));
        text.setFill(colorPicker.getValue());
        
        
        JFrame frame = new JFrame("colorJumper Swatches");
        frame.add(new JPanel() {
         @Override
         protected void paintComponent(Graphics graph) {
            paintThis(graph, 100, 100, 255, 0, 0, "Initial");
         }
        }, BorderLayout.CENTER);
        frame.pack();
        frame.setSize(new Dimension((squareSize*3 + padSize*2), (squareSize + (padSize*2 + padSize/2))));
        frame.setVisible(true);
        
        colorPicker.setOnAction((ActionEvent t) -> {
            text.setFill(colorPicker.getValue());
            System.out.println(RGBtoHex(colorPicker.getValue()));
            setColorPicked(RGBtoHex(colorPicker.getValue()));
            
        });
        
        //
        ColorPicker colorPicker2 = new ColorPicker(Color.BLUE);
        
        
        //
 
        box.getChildren().addAll(colorPicker, text);
        
 
        stage.setScene(scene);
        stage.show();
    }
}
*/



// Java Program to create color picker of three different appearance 
//https://www.geeksforgeeks.org/javafx-colorpicker-with-examples/
import javafx.application.Application; 
import javafx.scene.Scene; 
import javafx.scene.control.*; 
import javafx.scene.layout.*; 
import javafx.event.ActionEvent; 
import javafx.event.EventHandler; 
import javafx.collections.*; 
import javafx.stage.Stage; 
import javafx.scene.text.Text.*; 
import javafx.scene.paint.*; 
import javafx.scene.text.*;

public class ColorPickerSample extends Application { 
    // labels 
    Label initialLabel; 
    Label midpointLabel;
    
    private static String colorPickedInitial;
    private static String colorPickedMidpoint;
   
    public void setColorPickedInitial(String newColor) {
      colorPickedInitial = newColor;
    }
    
    public void setColorPickedMidpoint(String newColor) {
      colorPickedMidpoint = newColor;
    } 
   
    public static String getColorPickedInitial() {
      return colorPickedInitial;
    }  
   
    public static String getColorPickedMidpoint() {
      return colorPickedMidpoint;
    } 
    
    private String RGBtoHex(Color color) {
      return String.format("#%02X%02X%02X",
    (int)(color.getRed()*255),
    (int)(color.getGreen()*255),
    (int)(color.getBlue()*255));
   
    }
  
    // launch the application 
    public void start(Stage s) 
    { 
        // set title for the stage 
        s.setTitle("ColorPicker"); 
  
        // create a tile pane 
        TilePane r = new TilePane(); 
        r.setPrefColumns(2);
        //r.setHgap(10);
        //r.setVgap(10);
  
        // create a label 
        initialLabel  = new Label("\nPick an initial color:");
        midpointLabel = new Label("\nPick a midpoint color:"); 
  
        // create a color picker 
        ColorPicker colorPickerInitial = new ColorPicker(Color.BLACK);
        colorPickerInitial.setOnAction((ActionEvent t) -> {
            System.out.println(RGBtoHex(colorPickerInitial.getValue()));
            setColorPickedInitial(RGBtoHex(colorPickerInitial.getValue()));
        });
  
        // create a color picker 
        ColorPicker colorPickerMidpoint = new ColorPicker(Color.BLACK);
        colorPickerMidpoint.setOnAction((ActionEvent t) -> {
            System.out.println(RGBtoHex(colorPickerMidpoint.getValue()));
            setColorPickedMidpoint(RGBtoHex(colorPickerMidpoint.getValue()));
        });
  
        // add label 
        r.getChildren().add(initialLabel); 
        r.getChildren().add(colorPickerInitial);
        r.getChildren().add(midpointLabel); 
        r.getChildren().add(colorPickerMidpoint);
  
        // create a scene 
        //Scene sc = new Scene(r, 200, 200); 
        Scene sc = new Scene(r);
  
        // set the scene 
        s.setScene(sc); 
  
        s.show(); 
    } 
  
    public static void main(String args[]) 
    { 
        // launch the application 
        launch(args); 
    } 
}