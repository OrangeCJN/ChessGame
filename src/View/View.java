package View;

import Model.Model;
import Model.Piece;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.ArrayList;

/**
 * game GUI
 */
public class View {
    private Group root;
    private Pane piecePane;
    private Model model;
    private ImageView background;
    private ArrayList<ImageView> pieceViews;
    private VBox information;
    private HBox controls, prompts;
    private Text prompt, promptNameWhite, promptNameBlack, score, checkWarn;
    private TextField nameWhiteField, nameBlackField;
    private Button start, restart, draw, resign, undo;

    public static final int BOARD_SIDE_NUM = 8;
    public static final int PIECE_SIDE_LENGTH = 75;
    public static final int BOARD_SIDE_LENGTH = 600;
    public static final int INFORMATION_HEIGHT = PIECE_SIDE_LENGTH;

    // all the URLs to images
    private final String URL_BASE = "file:images/";
    private final String URL_WHITE = "w";
    private final String URL_BLACK = "b";
    private final String URL_SUFFIX = ".png";
    private final String URL_BOARD = URL_BASE + "board" + URL_SUFFIX;
    private final String URL_B_ROOK = URL_BASE + URL_BLACK + "r" + URL_SUFFIX;
    private final String URL_B_KNIGHT = URL_BASE + URL_BLACK + "n" + URL_SUFFIX;
    private final String URL_B_BISHOP = URL_BASE + URL_BLACK + "b" + URL_SUFFIX;
    private final String URL_B_QUEEN = URL_BASE + URL_BLACK + "q" + URL_SUFFIX;
    private final String URL_B_KING = URL_BASE + URL_BLACK + "k" + URL_SUFFIX;
    private final String URL_B_PAWN = URL_BASE + URL_BLACK + "p" + URL_SUFFIX;
    private final String URL_B_CAMEL = URL_BASE + URL_BLACK + "camel" + URL_SUFFIX;
    private final String URL_B_ZEBRA = URL_BASE + URL_BLACK + "zebra" + URL_SUFFIX;
    private final String URL_W_ROOK = URL_BASE + URL_WHITE + "r" + URL_SUFFIX;
    private final String URL_W_KNIGHT = URL_BASE + URL_WHITE + "n" + URL_SUFFIX;
    private final String URL_W_BISHOP = URL_BASE + URL_WHITE + "b" + URL_SUFFIX;
    private final String URL_W_QUEEN = URL_BASE + URL_WHITE + "q" + URL_SUFFIX;
    private final String URL_W_KING = URL_BASE + URL_WHITE + "k" + URL_SUFFIX;
    private final String URL_W_PAWN = URL_BASE + URL_WHITE + "p" + URL_SUFFIX;
    private final String URL_W_CAMEL = URL_BASE + URL_WHITE + "camel" + URL_SUFFIX;
    private final String URL_W_ZEBRA = URL_BASE + URL_WHITE + "zebra" + URL_SUFFIX;

    /* all piece images loaded */
    private Image[][] images;

    public View() {
        piecePane = new Pane();
        pieceViews = new ArrayList<>();
        root = new Group();
        information = new VBox();
        prompts = new HBox();
        controls = new HBox();
        initInformation();
    }

    /**
     * initialise all the information to display when game starts
     */
    private void initInformation() {
        information.setLayoutX(0);
        information.setLayoutY(BOARD_SIDE_LENGTH);
        information.setMaxSize(BOARD_SIDE_LENGTH, INFORMATION_HEIGHT);
        information.setPadding(new Insets(5, 5, 0, 5));

        prompts.setSpacing(10);
        controls.setSpacing(10);

        prompts.setPrefWidth(BOARD_SIDE_LENGTH);
        controls.setPrefWidth(BOARD_SIDE_LENGTH);

        prompt = new Text("Enter unique names to start.");
        promptNameWhite = new Text("White: ");
        promptNameBlack = new Text("Black: ");
        nameWhiteField = new TextField();
        nameBlackField = new TextField();
        start = new Button("Start");

        nameWhiteField.setPrefHeight(20);
        nameBlackField.setPrefHeight(20);

        prompt.setFill(Color.ORANGE);
        prompt.setFont(Font.font("Helvetica", FontWeight.BOLD, 40));

        promptNameWhite.setFont(Font.font("Helvetica", FontWeight.NORMAL, 15));
        promptNameBlack.setFont(Font.font("Helvetica", FontWeight.NORMAL, 15));

        start.setFont(Font.font("Helvetica", FontWeight.NORMAL, 15));

        prompts.setAlignment(Pos.CENTER);
        controls.setAlignment(Pos.CENTER);
        information.setAlignment(Pos.CENTER);

        prompts.getChildren().addAll(prompt);
        controls.getChildren().addAll(promptNameWhite, nameWhiteField, promptNameBlack, nameBlackField, start);
        information.getChildren().addAll(prompts, controls);
        root.getChildren().add(information);
        initInvisibleInformation();
    }

    /**
     * initialise all information to display later
     */
    private void initInvisibleInformation() {
        score = new Text();
        checkWarn = new Text("Check!");
        draw = new Button("Draw");
        resign = new Button("Resign");
        undo = new Button("Undo");
        restart = new Button("Restart");
        checkWarn.setFill(Color.RED);
        checkWarn.setFont(Font.font("Helvetica", FontWeight.BOLD, 40));
        score.setFont(Font.font("Helvetica", FontWeight.NORMAL, 15));
        draw.setFont(Font.font("Helvetica", FontWeight.NORMAL, 15));
        resign.setFont(Font.font("Helvetica", FontWeight.NORMAL, 15));
        undo.setFont(Font.font("Helvetica", FontWeight.NORMAL, 15));
        restart.setFont(Font.font("Helvetica", FontWeight.NORMAL, 15));
    }

    /**
     * set the model to display
     * @param model model to display
     */
    public void setModel(Model model) {
        this.model = model;
        initView();
    }

    /**
     * @return model currently displaying
     */
    public Model getModel() {
        return model;
    }

    /**
     * initialise the game GUI
     */
    private void initView() {
        loadImages();
        initBackground();
        refreshPieces();
        root.getChildren().add(piecePane);
    }

    /**
     * load all piece images
     */
    private void loadImages() {
        images = new Image[2][11];
        images[0][Piece.ROOK] = new Image(URL_B_ROOK);
        images[0][Piece.KNIGHT] = new Image(URL_B_KNIGHT);
        images[0][Piece.BISHOP] = new Image(URL_B_BISHOP);
        images[0][Piece.QUEEN] = new Image(URL_B_QUEEN);
        images[0][Piece.KING] = new Image(URL_B_KING);
        images[0][Piece.PAWN] = new Image(URL_B_PAWN);
        images[0][Piece.CAMEL] = new Image(URL_B_CAMEL);
        images[0][Piece.ZEBRARIDER] = new Image(URL_B_ZEBRA);
        images[1][Piece.ROOK] = new Image(URL_W_ROOK);
        images[1][Piece.KNIGHT] = new Image(URL_W_KNIGHT);
        images[1][Piece.BISHOP] = new Image(URL_W_BISHOP);
        images[1][Piece.QUEEN] = new Image(URL_W_QUEEN);
        images[1][Piece.KING] = new Image(URL_W_KING);
        images[1][Piece.PAWN] = new Image(URL_W_PAWN);
        images[1][Piece.CAMEL] = new Image(URL_W_CAMEL);
        images[1][Piece.ZEBRARIDER] = new Image(URL_W_ZEBRA);
    }

    /**
     * initialise the piece board image
     */
    private void initBackground() {
        background = new ImageView(URL_BOARD);
        root.getChildren().add(background);
    }

    /**
     * refresh the pieces displaying
     */
    public void refreshPieces() {
        pieceViews.clear();
        for (int y = 0; y < BOARD_SIDE_NUM; y++) {
            for (int x = 0; x < BOARD_SIDE_NUM; x++) {
                Piece piece = model.getPiece(y, x);
                if (piece == null) {
                    continue;
                }
                ImageView view = new ImageView(images[piece.isFirstPlayer() ? 1 : 0][piece.getType()]);
                view.setY(piece.getRow() * PIECE_SIDE_LENGTH);
                view.setX(piece.getCol() * PIECE_SIDE_LENGTH);
                pieceViews.add(view);
            }
        }
        piecePane.getChildren().clear();
        piecePane.getChildren().addAll(pieceViews);
    }

    public ArrayList<ImageView> getPieceViews() {
        return pieceViews;
    }

    public Group getRoot() {
        return root;
    }

    public Text getPrompt() {
        return prompt;
    }

    public Text getScore(){
        return score;
    }

    public Text getCheckWarn(){
        return checkWarn;
    }

    public Text getPromptNameWhite() {
        return promptNameWhite;
    }

    public Text getPromptNameBlack() {
        return promptNameBlack;
    }

    public TextField getNameWhiteField() {
        return nameWhiteField;
    }

    public TextField getNameBlackField() {
        return nameBlackField;
    }

    public Button getStart() {
        return start;
    }

    public Button getDraw() {
        return draw;
    }

    public Button getResign() {
        return resign;
    }

    public Button getUndo() {
        return undo;
    }

    public Button getRestart(){
        return restart;
    }

    public HBox getControlsBox() {
        return controls;
    }

    public HBox getPromptsBox() {
        return prompts;
    }
}