import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
//import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


//colorPickerSample imports for Label
import javafx.scene.control.*; 
import javafx.scene.layout.*; 
import javafx.event.ActionEvent; 
import javafx.event.EventHandler; 
import javafx.collections.*; 
import javafx.scene.text.Text.*; 
import javafx.scene.paint.*; 
import javafx.scene.text.*;



//jenkov button tut
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.FileInputStream;


//colorJumperSwatches
//import javax.swing.*;
//import java.awt.*;
//import java.awt.geom.Rectangle2D;



 
public class ColorPickerDemo extends Application {



    private String RGBtoHex(Color color) {
      return String.format("#%02X%02X%02X",
    (int)(color.getRed()*255),
    (int)(color.getGreen()*255),
    (int)(color.getBlue()*255));
   
    }

   

 
    @Override
    public void start(Stage stage) {
    
        Label initialLabel  = new Label("\nPick an initial color:");
        Label midpointLabel = new Label("\nPick a midpoint color:"); 
 
        final ColorPicker colorPicker = new ColorPicker();
        colorPicker.setValue(Color.RED);
        
        final ColorPicker colorPicker2 = new ColorPicker();
        colorPicker2.setValue(Color.BLUE);
        
        //
 
        final Rectangle circle = new Rectangle(100,100);
        circle.setFill(colorPicker.getValue());
        
        final Rectangle circle2 = new Rectangle(100,100);
        circle2.setFill(colorPicker2.getValue());
        
        final Rectangle circle3 = new Rectangle(100,100);
        circle3.setFill(Color.BLACK);
 
        colorPicker.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
                circle.setFill(colorPicker.getValue());
            }
        });
        
        colorPicker2.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
                circle2.setFill(colorPicker2.getValue());
            }
        });
        
        //
        
        Button button = new Button("Go");
        
        button.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
                String input1 = RGBtoHex(colorPicker.getValue());
                String input2 = RGBtoHex(colorPicker2.getValue());
                
                HexColorJumper jump = new HexColorJumper(input1, input2);
                String c = jump.calculate(jump.getC1(), jump.getC2());
                
                int r = jump.getRfinal();
                int g = jump.getGfinal();
                int b = jump.getBfinal();
                Color result = Color.rgb(r, g, b);
                
                circle3.setFill(result);
            }
        });
        
 
        FlowPane root = new FlowPane();
        root.setPadding(new Insets(10));
        root.setHgap(10);
        //root.getChildren().addAll(circle, circle2, circle3, colorPicker, colorPicker2);
        root.getChildren().add(circle);
        root.getChildren().add(circle2);
        root.getChildren().add(circle3);
        root.getChildren().add(initialLabel);
        root.getChildren().add(colorPicker);
        root.getChildren().add(midpointLabel); 
        root.getChildren().add(colorPicker2);
        root.getChildren().add(button);
 
        Scene scene = new Scene(root, 400, 300);
 
        stage.setTitle("JavaFX ColorPicker (o7planning.org)");
 
        stage.setScene(scene);
        stage.show();
    }
 
    public static void main(String[] args) {
        launch(args);
    }
 
}