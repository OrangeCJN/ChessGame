package Control;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * a new window to prompt player to confirm information, when the confirm box
 * is created, player must to click yes or no in order to continue
 */
class ConfirmBox {
    static boolean isConfirmed = false;

    /**
     * create and display the confirm box
     * @param title window title
     * @param message message to display
     */
    static void display(String title, String message){
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);

        Text messageText = new Text(message);
        Button yesButton = new Button("Yes");
        Button noButton = new Button("No");

        yesButton.setOnAction(event -> {
            isConfirmed = true;
            window.close();
        });

        noButton.setOnAction(event -> {
            isConfirmed = false;
            window.close();
        });

        VBox layout = new VBox();
        HBox buttons = new HBox();

        buttons.setAlignment(Pos.CENTER);
        buttons.setSpacing(10);

        layout.setAlignment(Pos.CENTER);
        layout.setSpacing(10);

        buttons.getChildren().addAll(yesButton, noButton);
        layout.getChildren().addAll(messageText, buttons);

        window.setScene(new Scene(layout));
        window.showAndWait();
    }
}
