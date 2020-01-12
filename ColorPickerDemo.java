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
    private int squareSize = 100;
    private int padSize = 25;


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
        
        
        final Rectangle square = new Rectangle(squareSize,squareSize);
        square.setFill(colorPicker.getValue());
        //square.setStroke(Color.BLACK);
        
        final Rectangle square2 = new Rectangle(squareSize,squareSize);
        square2.setFill(colorPicker2.getValue());
        
        final Rectangle square3 = new Rectangle(squareSize,squareSize);
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
        
        Button button = new Button("Get Result");
        
        button.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
                String input1 = ColorToHexString(colorPicker.getValue());
                String input2 = ColorToHexString(colorPicker2.getValue());
                
                HexColorJumper jump = new HexColorJumper(input1, input2);
                color3 = jump.calculate(jump.getC1(), jump.getC2());
                
                square3.setFill(Color.web(color3));
                
                text3.setText(resu + color3);
                
                System.out.println("(" + input1 + ", " + input2 + "): " + color3);
            }
        });
        
 
        StackPane firstSquare = new StackPane();
        firstSquare.getChildren().add(square);
        firstSquare.getChildren().add(text);
        firstSquare.setTranslateX(padSize);
        firstSquare.setTranslateY(padSize);
        
        StackPane secondSquare = new StackPane();
        secondSquare.getChildren().add(square2);
        secondSquare.getChildren().add(text2);
        secondSquare.setTranslateX(padSize);
        secondSquare.setTranslateY(padSize);
        
        StackPane thirdSquare = new StackPane();
        thirdSquare.getChildren().add(square3);
        thirdSquare.getChildren().add(text3);
        thirdSquare.setTranslateX(padSize);
        thirdSquare.setTranslateY(padSize);
        
        colorPicker.setMinWidth(squareSize);
        colorPicker.setMaxWidth(squareSize);
        colorPicker.getStyleClass().add("button");
        colorPicker2.setMinWidth(squareSize);
        colorPicker2.setMaxWidth(squareSize);
        colorPicker2.getStyleClass().add("button");
        button.setMinWidth(squareSize);
        button.setMaxWidth(squareSize);
        
        HBox buttons = new HBox();
        buttons.getChildren().add(colorPicker);
        buttons.getChildren().add(colorPicker2);
        buttons.getChildren().add(button);
        buttons.setTranslateX(padSize);
        buttons.setTranslateY(padSize);
        
        FlowPane root = new FlowPane();
        root.getChildren().add(firstSquare);
        root.getChildren().add(secondSquare);
        root.getChildren().add(thirdSquare);
        root.getChildren().add(buttons);
        
        //
 
        int sceneWidth = (squareSize*3 + padSize*2);
        int sceneHeight = (squareSize + (padSize*2 + padSize/2));
        Scene scene = new Scene(root, sceneWidth, sceneHeight + 15);
 
        stage.setTitle("colorJumper GUI");
 
        stage.setScene(scene);
        stage.show();
    }
 
    public static void main(String[] args) {
        launch(args);
    }
 
}