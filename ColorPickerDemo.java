/*
color picker demo
https://o7planning.org/en/11135/javafx-colorpicker-tutorial

button base code
http://tutorials.jenkov.com/javafx/button.html

group tutorial
http://tutorials.jenkov.com/javafx/group.html

popup tutorial
https://www.geeksforgeeks.org/javafx-popup-class/
*/


import javafx.application.Application;
import javafx.collections.*; 
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.BoxBlur;
import javafx.scene.Group;
import javafx.scene.layout.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text.*; 
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.stage.Popup;

import javafx.stage.PopupWindow.AnchorLocation;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;

//linear gradient
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import javafx.scene.control.ToggleGroup;
import javafx.scene.control.ToggleButton;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.Toggle;
import javafx.beans.value.ObservableValue;

 
public class ColorPickerDemo extends Application {


    // square and padding size
    private int squareSize = 100;
    private int padSize = 25;

    // create the three main squares that will be filled with colors
    private final Rectangle square1 = new Rectangle(squareSize,squareSize);
    private final Rectangle square2 = new Rectangle(squareSize,squareSize);
    private final Rectangle square3 = new Rectangle(squareSize,squareSize);
    
    // store the colors of the three main squares; stored in hex String format with presecing pound ("#FFFFFF")
    private String color1;
    private String color2;
    private String color3;
    
    // create the text that will overlay the three main squares
    private Text text1 = new Text();
    private Text text2 = new Text();
    private Text text3 = new Text();
    
    // set descriptors (or, 'types') for each of the three main squares
    private String init = "Initial\n";
    private String midp = "Midpoint\n";
    private String resu = "Result\n";
    
    // store the colors of the text that will overlay the three main squares
    Color text1Color;
    Color text2Color;
    Color text3Color;
    
    // create HexColorJumper object
    HexColorJumper jump = new HexColorJumper();
    
    // create alert button
    Button alert = new Button("!");
    
    Rectangle gradientTopHalf = new Rectangle(squareSize*3, padSize*1.5 );
    Rectangle gradientBig = new Rectangle(squareSize*3, squareSize);



    /* Returns the hexadecimal String format of a Color.
     * 
     * @param color The color to convert
     * @return The hexadecimal String representation
     *         of the given Color; Color.WHITE -> "#FFFFFF"
     */
    private String ColorToHexString(Color color) {
      return String.format("#%02X%02X%02X",
             (int)(color.getRed()*255),
             (int)(color.getGreen()*255),
             (int)(color.getBlue()*255));
    }
    
    
    /* Updates the given square information: square color, text, and text color.
     *
     * @param rect Specifies which of the three main squares to update [NOT NECESSARY?]
     * @param text The text that overlays the square
     * @param type Specifies which of the text blocks to use, helps in retreiving correct square color
     * @param squareColor The color to fill the square
     * @param textColor The color of the text
     */
    private void updateSquare(Rectangle rect, Text text, String type, String squareColor, Color textColor) {
       rect.setFill(Color.web(squareColor));
       //rect.setStroke(Color.BLACK);
       
       int num = 1;
       if (type.equals(midp))
            num = 2;
       else if (type.equals(resu))
            num = 3;
       
       text.setText(type + squareColor + "\n(" + jump.getRGB("R",num) + ", " + jump.getRGB("G",num) + ", " + jump.getRGB("B",num) + ")");
       text.setFill(textColor);
       text.setFont(Font.font("Arial", FontWeight.BOLD, 12));
    }
    
    
    /* Determines whether the text color overlaying a square should be
     * black or white.
     *
     * Sources:
     * https://stackoverflow.com/a/46470178
     * https://harthur.github.io/brain/
     *
     * @param text The text that will change color [NOT NECESSARY?]
     * @param r The red value of the text
     * @param g The green value of the text
     * @param b The blue value of the text
     * @return The color of text that should overlay the corresponding square
     */
    private Color setTextColor(Text text, int r, int g, int b) {
      int yiq = ( (r * 299) + (g * 587) + (b * 114) ) / 1000;
      //return (yiq >= 128) ? Color.BLACK : Color.WHITE;
      return (yiq >= 186) ? Color.BLACK : Color.WHITE;
    }
    
    
    //https://stackoverflow.com/a/26851888
    private void buttonStyle(Button button, boolean disable) {
      button.setDisable(disable);
      if (!disable) {
         button.setVisible(true);
         button.setStyle(
                "-fx-background-radius: 5em; " +
                "-fx-min-width: 20px; " +
                "-fx-min-height: 20px; " +
                "-fx-max-width: 20px; " +
                "-fx-max-height: 20px;" +
                "-fx-text-fill: #FFFFFF;" +
                "-fx-background-color: #FF0000;" +
                "-fx-background-insets: 0px; " +
                "-fx-padding: 0px;"
         );
      }
      else {
         button.setVisible(false);
      }
      
    
    }
    
    
    
    
    
    private void handleEventSquare1() {
      //color1 = ColorToHexString(colorPicker1.getValue());
      jump.setC1(color1);
      jump.updateInputColors();
      text1Color = setTextColor(text1, jump.getRGB("R",1), jump.getRGB("G",1), jump.getRGB("B",1));
      updateSquare(square1, text1, init, color1, text1Color);
                
      buttonStyle(alert, true);
    }
    
    private void handleEventSquare2() {
      //color2 = ColorToHexString(colorPicker2.getValue());
      jump.setC2(color2);
      jump.updateInputColors();
      text2Color = setTextColor(text2, jump.getRGB("R",2), jump.getRGB("G",2), jump.getRGB("B",2));
      updateSquare(square2, text2, midp, color2, text2Color);
                
      buttonStyle(alert, true);
    }
    
    private void handleEventSquare3() {
      color3 = jump.calculate(color1, color2);
      
      buttonStyle(alert, true);
      if (!jump.getWithinBounds()) {
         buttonStyle(alert, false);
      }
                
      text3Color = setTextColor(text3, jump.getRGB("R",3), jump.getRGB("G",3), jump.getRGB("B",3));
      updateSquare(square3, text3, resu, color3, text3Color);
                
      System.out.println("(" + color1 + ", " + color2 + "): " + color3);
      
      Stop[] stops = new Stop[] { new Stop(0, Color.web(color1)), new Stop(1, Color.web(color3))};
      LinearGradient lg1 = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, stops);
      gradientTopHalf.setFill(lg1);
      gradientBig.setFill(lg1);
      
    }
    

   

 
    @Override
    public void start(Stage stage) {
    
        //jump.setClosure("STOP_AT_BOUND");
        jump.setClosure("ROLLOVER");
    
        // Set up first color picker and starting 'initial' color
        final ColorPicker colorPicker1 = new ColorPicker();
        Color initialColor = Color.color(Math.random(), Math.random(), Math.random());
        colorPicker1.setValue(initialColor);
        
        // Set up second color picker the the starting 'midpoint' color
        final ColorPicker colorPicker2 = new ColorPicker();
        colorPicker2.setValue(initialColor.darker());
        
        // Convert the Color objects to hexadecimal String format
        color1 = ColorToHexString(colorPicker1.getValue());
        color2 = ColorToHexString(colorPicker2.getValue());
        
        // make sure that both starting colors of the HexColorJumper are initialized
        jump.setC1(color1);
        jump.setC2(color2);
        
        // Set up the three main squares
        handleEventSquare1();
        handleEventSquare2();
        handleEventSquare3();
        
        // Set up the "Get Result" button
        Button getResults = new Button("Get Result");
        
        
        
        
        // Update elements of square1 when colorPicker1 Color is chosen
        colorPicker1.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
                color1 = ColorToHexString(colorPicker1.getValue());
                handleEventSquare1();
            }
        });
        
        // Update elements of square2 when colorPicker2 Color is chosen
        colorPicker2.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
                color2 = ColorToHexString(colorPicker2.getValue());
                handleEventSquare2();
            }
        });
        
        // Update elements of square3 when "Get Result" button is pushed
        getResults.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
                handleEventSquare3();
            }
        });
        
        
        
        
        
        
        
        
        
        
        
        Button close = new Button("X");
        buttonStyle(close, true);
        
        //BoxBlur boxBlur = new BoxBlur();
        
        Rectangle notificationBackgroundBig = new Rectangle(squareSize*3, squareSize);
        Rectangle notificationBackground = new Rectangle(squareSize*3 - 15, squareSize - 15);
        notificationBackground.setFill(Color.rgb(255,255,255,0.85));
        Text diagnostics = new Text("Here's what went wrong:");
        diagnostics.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        StackPane notification = new StackPane();
        notification.getChildren().add(notificationBackgroundBig);
        notification.getChildren().add(notificationBackground);
        notification.getChildren().add(diagnostics);
        
        close.setTranslateX(squareSize + squareSize/2);
        close.setTranslateY(-squareSize/2);
        notification.getChildren().add(close);
        
        
        alert.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
            
                Stop[] stops = new Stop[] { new Stop(0, Color.web(color1)), new Stop(1, Color.web(color3))};
                LinearGradient lg1 = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, stops);
                notificationBackgroundBig.setFill(lg1);
            
                notification.toFront();
                //close.toFront();
                buttonStyle(alert, true);
                buttonStyle(close, false);
                
                /*text1.setEffect(boxBlur);
                text2.setEffect(boxBlur);
                text3.setEffect(boxBlur);*/
                
                
                close.setOnAction(new EventHandler<ActionEvent>() {
                   @Override
                   public void handle(ActionEvent event) {
                   
                      notificationBackgroundBig.setFill(Color.TRANSPARENT);
                   
                      notification.toBack();
                      buttonStyle(alert, false);
                      buttonStyle(close, true);
                      
                      /*text1.setEffect(null);
                      text2.setEffect(null);
                      text3.setEffect(null);*/
                   }
                });
                
                
            }
        });
        
        
        
        
        
        //
        
        Button shuffle = new Button("Shuffle");
        
        shuffle.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
                
                Color initialColor = Color.color(Math.random(), Math.random(), Math.random());
                colorPicker1.setValue(initialColor);
                color1 = ColorToHexString(initialColor);
                
                text1Color = setTextColor(text1, jump.getRGB("R",1), jump.getRGB("G",1), jump.getRGB("B",1));
                if (text1Color.equals(Color.WHITE)) {
                     colorPicker2.setValue(initialColor.brighter());
                     color2 = ColorToHexString(initialColor.brighter());
                }
                else {
                     colorPicker2.setValue(initialColor.darker());
                     color2 = ColorToHexString(initialColor.darker());
                }
                
                handleEventSquare1();
                handleEventSquare2();
                handleEventSquare3();
            }
        });
        
        
        
        
        
        
        Button gradient = new Button("See Gradient");
        Button closeGradient = new Button("See Colors");
        closeGradient.setDisable(true);
        closeGradient.setVisible(false);
        
        //Rectangle gradientBig = new Rectangle(squareSize*3, squareSize);
        gradientBig.setFill(Color.WHITE);
        StackPane grad = new StackPane();
        grad.getChildren().add(gradientBig);
        //grad.getChildren().add(closeGradient);
        
        StackPane gradientButtons = new StackPane();
        gradientButtons.getChildren().add(gradient);
        gradientButtons.getChildren().add(closeGradient);
        
        gradient.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
                
                   grad.toFront();
                   Stop[] stops = new Stop[] { new Stop(0, Color.web(color1)), new Stop(1, Color.web(color3))};
                   LinearGradient lg1 = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, stops);
                   gradientBig.setFill(lg1);
                   gradient.setDisable(true);
                   gradient.setVisible(false);
                   closeGradient.setDisable(false);
                   closeGradient.setVisible(true);
                
                
                   closeGradient.setOnAction(new EventHandler<ActionEvent>() {
                      @Override
                      public void handle(ActionEvent event) {
                      
                         grad.toBack();
                         gradientBig.setFill(Color.WHITE);
                         gradient.setDisable(false);
                         gradient.setVisible(true);
                         closeGradient.setDisable(true);
                         closeGradient.setVisible(false);
                         //alert.toFront();
                      }
                   });
                   
                
                
            }
        });
        
        
        gradientTopHalf.setTranslateX(squareSize);
        //gradientTopHalf.setTranslateY(-(int)squareSize/2);
        //gradientTopHalf.setTranslateY(-15);
        gradientTopHalf.setTranslateY(squareSize - 4);
        Stop[] stops = new Stop[] { new Stop(0, Color.web(color1)), new Stop(1, Color.web(color3))};
        LinearGradient lg1 = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, stops);
        gradientTopHalf.setFill(lg1);
        
        
        
        
        
        
        //
        
        /* Set Stage */
        
        // horizontal center align the text that will overlay the three main squares
        text1.setTextAlignment(TextAlignment.CENTER);
        text2.setTextAlignment(TextAlignment.CENTER);
        text3.setTextAlignment(TextAlignment.CENTER);
        
        // vertical baseline align the text that will overlay the three main squares
        text1.setTranslateY(padSize);
        text2.setTranslateY(padSize);
        text3.setTranslateY(padSize);
        
 
        StackPane firstSquare = new StackPane();
        firstSquare.getChildren().add(square1);
        firstSquare.getChildren().add(text1);
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
        alert.setTranslateX(squareSize/2);
        alert.setTranslateY(-squareSize/2);
        thirdSquare.getChildren().add(alert);
        
        
        colorPicker1.setMinWidth(squareSize);
        colorPicker1.setMaxWidth(squareSize);
        colorPicker1.getStyleClass().add("button");
        colorPicker2.setMinWidth(squareSize);
        colorPicker2.setMaxWidth(squareSize);
        colorPicker2.getStyleClass().add("button");
        getResults.setMinWidth(squareSize);
        getResults.setMaxWidth(squareSize);
        
        HBox buttons = new HBox();
        buttons.getChildren().add(colorPicker1);
        buttons.getChildren().add(colorPicker2);
        buttons.getChildren().add(getResults);
        buttons.setTranslateX(padSize);
        buttons.setTranslateY(padSize);
        
        HBox buttons2 = new HBox();
        shuffle.setMinWidth(squareSize);
        shuffle.setMaxWidth(squareSize);
        gradient.setMinWidth(squareSize);
        gradient.setMaxWidth(squareSize);
        closeGradient.setMinWidth(squareSize);
        closeGradient.setMaxWidth(squareSize);
        buttons2.getChildren().add(shuffle);
        //
        buttons2.getChildren().add(gradientButtons);
        //
        buttons2.setTranslateX(squareSize + padSize);
        buttons2.setTranslateY(padSize*1.5);
        
        
        
        StackPane squares = new StackPane();
        //
        grad.setTranslateX(squareSize);
        grad.setTranslateY(padSize);
        squares.getChildren().add(grad);
        //
        notification.setTranslateX(squareSize);
        notification.setTranslateY(padSize);
        squares.getChildren().add(notification);
        //
        firstSquare.setTranslateX(0);
        squares.getChildren().add(firstSquare);
        secondSquare.setTranslateX(squareSize);
        squares.getChildren().add(secondSquare);
        thirdSquare.setTranslateX(2*squareSize);
        squares.getChildren().add(thirdSquare);
        //squares.setTranslateX(-squareSize/2 - padSize/2);
        squares.setTranslateX(-squareSize + padSize);
        //
        squares.getChildren().add(gradientTopHalf);
        
        FlowPane root = new FlowPane();
        /*root.getChildren().add(firstSquare);
        root.getChildren().add(secondSquare);
        root.getChildren().add(thirdSquare);*/
        root.getChildren().add(squares);
        root.getChildren().add(buttons);
        root.getChildren().add(buttons2);
        
        //
 
        int sceneWidth = (squareSize*3 + padSize*2);
        int sceneHeight = (squareSize + (padSize*2 + padSize/2));
        Scene scene = new Scene(root, sceneWidth, sceneHeight + 15 + 25);
 
        stage.setTitle("colorJumper GUI");
 
        stage.setScene(scene);
        stage.show();
    }
 
    public static void main(String[] args) {
        launch(args);
    }
 
}