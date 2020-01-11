/*
color picker demo
https://o7planning.org/en/11135/javafx-colorpicker-tutorial


button base code
http://tutorials.jenkov.com/javafx/button.html

group tutorial
http://tutorials.jenkov.com/javafx/group.html


*/
import javafx.scene.Group;
import javafx.scene.layout.StackPane;
import javafx.geometry.Pos;



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



    private Text text;
    private Text text2;
    private Text text3;


    private String ColorToHexString(Color color) {
      return String.format("#%02X%02X%02X",
    (int)(color.getRed()*255),
    (int)(color.getGreen()*255),
    (int)(color.getBlue()*255));
   
    }

   

 
    @Override
    public void start(Stage stage) {
    
    
        final ColorPicker colorPicker = new ColorPicker();
        colorPicker.setValue(Color.RED);
        
        final ColorPicker colorPicker2 = new ColorPicker();
        colorPicker2.setValue(Color.BLUE);
        
        //
        
        
        final Rectangle circle = new Rectangle(50,50,100,100);
        circle.setFill(colorPicker.getValue());
        
        final Rectangle circle2 = new Rectangle(150,50,100,100);
        circle2.setFill(colorPicker2.getValue());
        
        final Rectangle circle3 = new Rectangle(250,50,100,100);
        circle3.setFill(Color.BLACK);
        
        //
        
        String input1 = "Initial\n" + ColorToHexString(colorPicker.getValue());
        text = new Text(input1);
        text.setFill(Color.WHITE);
        text.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        
        String input2 = ColorToHexString(colorPicker2.getValue());
        text2 = new Text(input2);
        text2.setFill(Color.WHITE);
        text2.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        
        String result = "# ?";
        text3 = new Text(result);
        text3.setFill(Color.WHITE);
        text3.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        
        //
        
        Label initialLabel  = new Label("\nPick an initial color:");
        Label midpointLabel = new Label("\nPick a midpoint color:"); 
        
        //
 
        
        
 
        colorPicker.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
                circle.setFill(colorPicker.getValue());
                String input1 = ColorToHexString(colorPicker.getValue());
                text.setText(input1);
            }
        });
        
        colorPicker2.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
                circle2.setFill(colorPicker2.getValue());
                String input2 = ColorToHexString(colorPicker2.getValue());
                text2.setText(input2);
            }
        });
        
        //
        
        Button button = new Button("Go");
        
        button.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
                String input1 = ColorToHexString(colorPicker.getValue());
                String input2 = ColorToHexString(colorPicker2.getValue());
                
                HexColorJumper jump = new HexColorJumper(input1, input2);
                String c = jump.calculate(jump.getC1(), jump.getC2());
                
                int r = jump.getRfinal();
                int g = jump.getGfinal();
                int b = jump.getBfinal();
                Color result = Color.rgb(r, g, b);
                
                circle3.setFill(result);
                
                String input3 = c;
                text3.setText(input3);
            }
        });
        
 
        /*FlowPane root = new FlowPane();
        root.setPadding(new Insets(10));
        root.setHgap(10);
        root.getChildren().add(circle);
        root.getChildren().add(circle2);
        root.getChildren().add(circle3);
        root.getChildren().add(initialLabel);
        root.getChildren().add(colorPicker);
        root.getChildren().add(midpointLabel); 
        root.getChildren().add(colorPicker2);
        root.getChildren().add(button);*/
        
        /*Group root = new Group();
        root.getChildren().add(circle);
        root.getChildren().add(circle2);
        root.getChildren().add(circle3);
        root.getChildren().add(initialLabel);
        root.getChildren().add(colorPicker);
        root.getChildren().add(midpointLabel); 
        root.getChildren().add(colorPicker2);
        root.getChildren().add(button);
        final Rectangle buttonsLayout = new Rectangle(50,200,100,100);
        HBox hbox = new HBox(10, colorPicker, colorPicker2, button);
        //hbox.setAlignment(Pos.BOTTOM_CENTER);
        root.getChildren().add(hbox);*/
        
        StackPane firstCircle = new StackPane();
        firstCircle.getChildren().add(circle);
        firstCircle.getChildren().add(text);
        
        StackPane secondCircle = new StackPane();
        secondCircle.getChildren().add(circle2);
        secondCircle.getChildren().add(text2);
        
        StackPane thirdCircle = new StackPane();
        thirdCircle.getChildren().add(circle3);
        thirdCircle.getChildren().add(text3);
        
        FlowPane root = new FlowPane();
        root.getChildren().add(firstCircle);
        root.getChildren().add(secondCircle);
        root.getChildren().add(thirdCircle);
        //
        root.getChildren().add(initialLabel);
        root.getChildren().add(colorPicker);
        root.getChildren().add(midpointLabel); 
        root.getChildren().add(colorPicker2);
        root.getChildren().add(button);
        
        //
 
        Scene scene = new Scene(root, 400, 300);
 
        stage.setTitle("JavaFX ColorPicker (o7planning.org)");
 
        stage.setScene(scene);
        stage.show();
    }
 
    public static void main(String[] args) {
        launch(args);
    }
 
}