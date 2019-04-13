import Control.Control;
import Model.Model;
import View.View;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {
    private static final int WINDOW_HEIGHT = View.BOARD_SIDE_LENGTH + View.INFORMATION_HEIGHT;
    private static final int WINDOW_WIDTH = View.BOARD_SIDE_LENGTH;

    @Override
    public void start(Stage primaryStage){
        Model model = new Model();
        View view = new View();
        Control control = new Control();

        view.setModel(model);
        control.setView(view);

        Scene primaryScene = new Scene(view.getRoot(), WINDOW_WIDTH, WINDOW_HEIGHT);
        primaryStage.setTitle("Chess");
        primaryScene.setFill(Color.GRAY);
        primaryStage.setResizable(false);
        primaryStage.setScene(primaryScene);
        primaryStage.show();
    }
}
