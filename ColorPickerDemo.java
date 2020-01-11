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




 
public class ColorPickerDemo extends Application {



    private Text text;
    private Text text2;
    private Text text3;
    private String init = "Initial\n";
    private String midp = "Midpoint\n";
    private String resu = "Result\n";
    private String color1;
    private String color2;
    private String color3;


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
        
        
        final Rectangle square = new Rectangle(50,50,100,100);
        square.setFill(colorPicker.getValue());
        //square.setStroke(Color.BLACK);
        
        final Rectangle square2 = new Rectangle(150,50,100,100);
        square2.setFill(colorPicker2.getValue());
        
        final Rectangle square3 = new Rectangle(250,50,100,100);
        square3.setFill(Color.BLACK);
        
        //
        
        color1 = ColorToHexString(colorPicker.getValue());
        text = new Text(init + color1);
        text.setFill(Color.WHITE);
        text.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        
        color2 = ColorToHexString(colorPicker2.getValue());
        text2 = new Text(midp + color2);
        text2.setFill(Color.WHITE);
        text2.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        
        color3 = "# ?";
        text3 = new Text(resu + color3);
        text3.setFill(Color.WHITE);
        text3.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        
        //
        
        Label initialLabel  = new Label("\nPick an initial color:");
        Label midpointLabel = new Label("\nPick a midpoint color:"); 
        
        //
 
        
        
 
        colorPicker.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
                square.setFill(colorPicker.getValue());
                color1 = ColorToHexString(colorPicker.getValue());
                text.setText(init + color1);
            }
        });
        
        colorPicker2.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
                square2.setFill(colorPicker2.getValue());
                color2 = ColorToHexString(colorPicker2.getValue());
                text2.setText(midp + color2);
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
                color3 = jump.calculate(jump.getC1(), jump.getC2());
                
                /*int r = jump.getRfinal();
                int g = jump.getGfinal();
                int b = jump.getBfinal();
                Color result = Color.rgb(r, g, b);
                
                square3.setFill(result);*/
                square3.setFill(Color.web(color3));
                
                text3.setText(resu + color3);
                
                System.out.println("(" + input1 + ", " + input2 + "): " + color3);
            }
        });
        
 
        /*FlowPane root = new FlowPane();
        root.setPadding(new Insets(10));
        root.setHgap(10);
        root.getChildren().add(square);
        root.getChildren().add(square2);
        root.getChildren().add(square3);
        root.getChildren().add(initialLabel);
        root.getChildren().add(colorPicker);
        root.getChildren().add(midpointLabel); 
        root.getChildren().add(colorPicker2);
        root.getChildren().add(button);*/
        
        /*Group root = new Group();
        root.getChildren().add(square);
        root.getChildren().add(square2);
        root.getChildren().add(square3);
        root.getChildren().add(initialLabel);
        root.getChildren().add(colorPicker);
        root.getChildren().add(midpointLabel); 
        root.getChildren().add(colorPicker2);
        root.getChildren().add(button);
        final Rectangle buttonsLayout = new Rectangle(50,200,100,100);
        HBox hbox = new HBox(10, colorPicker, colorPicker2, button);
        //hbox.setAlignment(Pos.BOTTOM_CENTER);
        root.getChildren().add(hbox);*/
        
        StackPane firstSquare = new StackPane();
        firstSquare.getChildren().add(square);
        firstSquare.getChildren().add(text);
        
        StackPane secondSquare = new StackPane();
        secondSquare.getChildren().add(square2);
        secondSquare.getChildren().add(text2);
        
        StackPane thirdSquare = new StackPane();
        thirdSquare.getChildren().add(square3);
        thirdSquare.getChildren().add(text3);
        
        FlowPane root = new FlowPane();
        root.getChildren().add(firstSquare);
        root.getChildren().add(secondSquare);
        root.getChildren().add(thirdSquare);
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