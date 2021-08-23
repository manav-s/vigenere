import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class VigenereCipherView extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    // title of the window
    primaryStage.setTitle("Vigenere Cipher");

    // the image logo to be displayed
    Image img = new Image("logoimage.png", 250, 250,
        false, false);
    ImageView image = new ImageView(img);
    image.setPreserveRatio(true);

    // the labels and the message and key field
    Label messageName = new Label("Message:");
    TextField messageText = new TextField();
    Label keyName = new Label("Key:");
    PasswordField keyText = new PasswordField();

    // creating the stack pane that has the image inside it
    StackPane imagePane = new StackPane();
    imagePane.getChildren().add(image);
    StackPane.setAlignment(image, Pos.CENTER);

    // the decode button and setting its action
    Button decodeButton = new Button();
    decodeButton.setText("Decode");
    decodeButton.setOnAction(e -> {
      // check if the fields are empty
      String message = messageText.getText();
      String key = keyText.getText();
      if (message.equals("") || key.equals("")) {
        // pop up  a warning dialog
        warning();
      } else {
        // solves the encoding
        VigenereCipherModel code = new VigenereCipherModel();
        String result = code.decode(key, message);

        // pop up window
        Stage stage = new Stage();

        // encoded text
        Text text = new Text("Decrypted message: " + result + " \n Original: " + message + "\n Key: " + key);
        text.setFont(Font.font(null, FontWeight.BOLD, 25));
        text.setFill(Color.BLACK);

        StackPane stackHelp = new StackPane();
        stackHelp.getChildren().add(text);

        Scene scene = new Scene(stackHelp, 650, 400);
        stage.setTitle("Result");
        stage.setScene(scene);
        stage.show();
        messageText.clear();
        keyText.clear();
      }
    });

    // the encode button and setting its action
    Button encodeButton = new Button("Encode");
    encodeButton.setOnAction(e -> {
      // check if the fields are empty
      String message = messageText.getText();
      String key = keyText.getText();
      if (message.equals("") || key.equals("")) {
        warning();
      } else {
        // solves the encoding
        VigenereCipherModel code = new VigenereCipherModel();
        String result = code.encode(key, message);

        // pop up window
        Stage stage = new Stage();

        // encoded text
        Text text = new Text("Encoded message: " + result + " \n Original: " + message + "\n Key: " + key);
        text.setFont(Font.font(null, FontWeight.BOLD, 25));
        text.setFill(Color.BLACK);

        StackPane stackHelp = new StackPane();
        stackHelp.getChildren().add(text);

        Scene scene = new Scene(stackHelp, 650, 400);
        stage.setTitle("Result");
        stage.setScene(scene);
        stage.show();

        messageText.clear();
        keyText.clear();
      }
    });

    // the help button and setting its action
    Button helpButton = new Button("Help");
    helpButton.setOnAction(e -> {
      Stage stage = new Stage();
      Text text = new Text("How the cipher works: \n \n"
          + "The Vigenere cipher is an advanced iteration of the Caesar cipher. "
          + "It is a poly-alphabetic cipher that \n is used to encrypt text using a key that can both encode and decode. "
          + "In a Caesar cipher, every letter \n is shifted in the alphabet. "
          + "For example, if there was a shift of 3 or “C”, every letter would be shifted 3 \n forwards."
          + "“A” becomes “D”, “B” becomes “E”, and “C” becomes “F”."
          + "The Vigenere cipher takes \n this one step further by repeating the key along the message, "
          + "so that every letter of the message \n may have a different number of letters to be shifted by. \n \n"
          + "However, no cipher is unbreakable. There are a few methods to decrypt the messages "
          + "including \nKasiski examination, Friedman testing, frequency analysis, and key elimination."
          + " For higher \n security, use a key that is the same length as the original message. This measure "
          + "leaves the \n Friedman and Kasiski methods of decryption ineffective. "
          + "Another measure to increase security \n even further would be to use a variety of keys.\n \n"
          + "Created by Manav Sharma, Northeastern University");
      text.setFont(Font.font(null, FontWeight.BOLD, 15));
      text.setFill(Color.BLACK);
      StackPane stackPaneHelp = new StackPane();
      stackPaneHelp.getChildren().add(text);

      Scene scene = new Scene(stackPaneHelp, 650, 400);
      stage.setTitle("Help");
      stage.setScene(scene);
      stage.show();
        }
    );

    // the grid pane for the labels and the message/key fields
    GridPane gridPaneanelayout = new GridPane();
    gridPaneanelayout.setAlignment(Pos.CENTER);
    gridPaneanelayout.setHgap(10);
    gridPaneanelayout.setVgap(10);

    // the horizontal box to hold the buttons
    HBox hbButtons = new HBox();
    hbButtons.setAlignment(Pos.CENTER);
    hbButtons.setSpacing(10.0);
    hbButtons.getChildren().addAll(encodeButton, decodeButton, helpButton);

    Region spacer = new Region();
    spacer.setMaxHeight(50);
    VBox.setVgrow(spacer, Priority.ALWAYS);

    VBox vBox = new VBox();
    vBox.getChildren().addAll(spacer, imagePane, gridPaneanelayout);

    // the scene of the application
    Scene scene = new Scene(vBox, 450, 550);

    // adding the message label
    gridPaneanelayout.add(messageName, 0, 3);

    // adding the message field
    gridPaneanelayout.add(messageText, 1, 3);

    // adding the key label
    gridPaneanelayout.add(keyName, 0, 4);

    // adding the key field
    gridPaneanelayout.add(keyText, 1, 4);

    // adding the buttons
    gridPaneanelayout.add(hbButtons, 0, 7, 2, 1);

    // set the scene, and make it visible
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  private void warning() {
    // pop up  a warning dialog
    Stage stage = new Stage();
    Text text = new Text("Warning: \n The key or message fields cannot be empty.");
    text.setFont(Font.font(null, FontWeight.BOLD, 15));
    text.setFill(Color.BLACK);
    StackPane stackPaneHelp = new StackPane();
    stackPaneHelp.getChildren().add(text);

    Scene scene = new Scene(stackPaneHelp, 500, 200);
    stage.setTitle("Error!");
    stage.setScene(scene);
    stage.show();
  }
}
