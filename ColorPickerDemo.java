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



    private Text text = new Text();
    private Text text2 = new Text();
    private Text text3 = new Text();
    private String init = "Initial\n";
    private String midp = "Midpoint\n";
    private String resu = "Result\n";
    private String color1;
    private String color2;
    private String color3;
    private int squareSize = 100;
    private int padSize = 25;
    
    final Rectangle square = new Rectangle(squareSize,squareSize);
    final Rectangle square2 = new Rectangle(squareSize,squareSize);
    final Rectangle square3 = new Rectangle(squareSize,squareSize);
    
    Color text1Color = Color.WHITE;
    Color text2Color = Color.WHITE;
    Color text3Color = Color.WHITE;


    private String ColorToHexString(Color color) {
      return String.format("#%02X%02X%02X",
    (int)(color.getRed()*255),
    (int)(color.getGreen()*255),
    (int)(color.getBlue()*255));
    }
    
    private void updateSquare(Rectangle rect, Text text, String type, String squareColor, Color textColor) {
       rect.setFill(Color.web(squareColor));
       //rect.setStroke(Color.BLACK);
       text.setText(type + squareColor);
       text.setFill(textColor);
       text.setFont(Font.font("Arial", FontWeight.BOLD, 12));
    }
    
    private Color setTextColor(Text text, int r, int g, int b) {
      if ((r > 0xBB) || (g > 0xBB) || (b > 0xBB)) {
         return Color.BLACK;
      }//else
         return Color.WHITE;
    }

   

 
    @Override
    public void start(Stage stage) {
    
    
        final ColorPicker colorPicker = new ColorPicker();
        colorPicker.setValue(Color.WHITE);
        
        final ColorPicker colorPicker2 = new ColorPicker();
        colorPicker2.setValue(Color.PINK);
        
        //
        
        color1 = ColorToHexString(colorPicker.getValue());
        color2 = ColorToHexString(colorPicker2.getValue());
        
        HexColorJumper jump = new HexColorJumper(color1, color2);
        color3 = jump.calculate(color1, color2);
        
        
        text1Color = setTextColor(text, jump.getRGB("R",1), jump.getRGB("G",1), jump.getRGB("B",1));
        text2Color = setTextColor(text2, jump.getRGB("R",2), jump.getRGB("G",2), jump.getRGB("B",2));
        text3Color = setTextColor(text3, jump.getRGB("R",3), jump.getRGB("G",3), jump.getRGB("B",3));
        updateSquare(square, text, init, color1, text1Color);
        updateSquare(square2, text2, midp, color2, text2Color);
        updateSquare(square3, text3, resu, color3, text3Color);
        
        //
 
        
        
 
        colorPicker.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
                color1 = ColorToHexString(colorPicker.getValue());
                jump.setC1(color1);
                jump.updateInputColors();
                text1Color = setTextColor(text, jump.getRGB("R",1), jump.getRGB("G",1), jump.getRGB("B",1));
                updateSquare(square, text, init, color1, text1Color);
            }
        });
        
        colorPicker2.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
                color2 = ColorToHexString(colorPicker2.getValue());
                jump.setC2(color2);
                jump.updateInputColors();
                text2Color = setTextColor(text2, jump.getRGB("R",2), jump.getRGB("G",2), jump.getRGB("B",2));
                updateSquare(square2, text2, midp, color2, text2Color);
            }
        });
        
        //
        
        Button button = new Button("Get Result");
        
        button.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
                //HexColorJumper jump = new HexColorJumper();
                color3 = jump.calculate(color1, color2);
                
                text3Color = setTextColor(text3, jump.getRGB("R",3), jump.getRGB("G",3), jump.getRGB("B",3));
                updateSquare(square3, text3, resu, color3, text3Color);
                
                System.out.println("(" + color1 + ", " + color2 + "): " + color3);
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