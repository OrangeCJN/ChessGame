package Control;

import Model.Model;
import Model.Piece;
import View.View;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

/**
 * controls the whole game, including inputs and outputs
 */
public class Control {
    private View view;
    private Model model;
    private double homeX, homeY, marginX, marginY;
    private boolean isWhiteTurn;
    private String whiteName, blackName;
    private int whiteScore, blackScore;

    /**
     * set the view to control
     * @param view view to control
     */
    public void setView(View view) {
        this.view = view;
        this.model = view.getModel();
        initControl();
    }

    /**
     * initialise all components related to controls
     */
    private void initControl(){
        Button start = view.getStart();
        start.setDisable(true);
        view.getNameBlackField().setOnKeyTyped(event -> view.getStart().setDisable(! validNames(view.getNameWhiteField().getText(), view.getNameBlackField().getText())));
        view.getNameWhiteField().setOnKeyTyped(event -> view.getStart().setDisable(! validNames(view.getNameWhiteField().getText(), view.getNameBlackField().getText())));
        start.setOnAction(event -> {
            String whiteName = view.getNameWhiteField().getText();
            String blackName = view.getNameBlackField().getText();
            if (validNames(whiteName, blackName)){
                this.whiteName = whiteName;
                this.blackName = blackName;
                start();
            }
        });
    }

    /**
     * initialise all button listeners
     */
    private void initButtonsListener(){
        initDraw();
        initResign();
        initRedo();
    }

    /**
     * initialise draw button listener
     */
    private void initDraw(){
        view.getDraw().setOnAction(event -> {
            String title = "Confirm";
            String message = (isWhiteTurn ? whiteName : blackName) + " wish to make a draw to this game.\n" +
                    (isWhiteTurn ? blackName : whiteName) + ", do you agree?";
            ConfirmBox.display(title, message);
            if (ConfirmBox.isConfirmed){
                draw();
            }
        });
    }

    /**
     * initialise resign button listener
     */
    private void initResign(){
        view.getResign().setOnAction(event -> {
            String title = "Confirm";
            String message = (isWhiteTurn ? whiteName : blackName) + ", do you really wish to resign?";
            ConfirmBox.display(title, message);
            if (ConfirmBox.isConfirmed){
                if (isWhiteTurn){
                    blackScore++;
                } else {
                    whiteScore++;
                }
                view.getPrompt().setText((isWhiteTurn ? whiteName : blackName) + " resigned");
                prepareRestart();
            }
        });
    }

    /**
     * initialise redo button listener
     */
    private void initRedo(){
        view.getUndo().setOnAction(event -> {
            String title = "Confirm";
            String message = (isWhiteTurn ? whiteName : blackName) + ", do you really wish to redo?";
            ConfirmBox.display(title, message);
            if (ConfirmBox.isConfirmed){
                model.undo();
                view.getUndo().setDisable(true);
                isWhiteTurn = !isWhiteTurn;
                view.refreshPieces();
                initListener();
                updateInformation();
            }
        });
    }

    /**
     * change the GUI to restart-pending status
     */
    private void prepareRestart(){
        view.getScore().setText(getScore());
        view.getControlsBox().getChildren().clear();
        view.getControlsBox().getChildren().addAll(view.getRestart());
        view.refreshPieces();
        view.getRestart().setOnAction(event -> restart());
    }

    /**
     * clear the current game and start a new round of the game
     */
    private void restart(){
        model.init();
        view.refreshPieces();
        start();
    }

    /**
     * handles draw events
     */
    private void draw(){
        view.getPrompt().setText("Game draw");
        prepareRestart();
    }

    /**
     * start the game
     */
    private void start(){
        if (askCustom()){
            model.initCustom();
            view.refreshPieces();
        }
        view.getPromptsBox().getChildren().clear();
        view.getPromptsBox().getChildren().addAll(view.getPrompt(), view.getScore(), view.getCheckWarn());
        view.getControlsBox().getChildren().clear();
        view.getControlsBox().getChildren().addAll(view.getDraw(), view.getResign(), view.getUndo());
        view.getUndo().setDisable(true);
        view.getCheckWarn().setVisible(false);
        isWhiteTurn = true;
        updateInformation();
        initListener();
    }

    /**
     * asks whether to play custom pieces
     */
    private boolean askCustom(){
        String title = "Custom mode?";
        String message = "Now the game mode is classical mode. \nDo you wish to play custom pieces?";
        ConfirmBox.display(title, message);
        return ConfirmBox.isConfirmed;
    }

    /**
     * refresh the information to display on the screen, including prompts, warns, scores and
     * operation buttons
     */
    private void updateInformation(){
        view.getPrompt().setText(getPrompt());
        view.getScore().setText(getScore());
    }

    /**
     * validate input names, two names should be unique and not empty in order to be valid
     * @param whiteName name string of white player
     * @param blackName name string of black player
     * @return whether the names valid
     */
    private boolean validNames(String whiteName, String blackName){
        return whiteName.length() > 0 && blackName.length() > 0 &&
                !whiteName.equalsIgnoreCase(blackName);
    }

    /**
     * initialise all listeners, including pieces and buttons
     */
    private void initListener() {
        initPieceListener();
        initButtonsListener();
    }

    /**
     * initialise piece listeners
     */
    private void initPieceListener() {
        for (ImageView imageView : view.getPieceViews()) {
            int col = (int) imageView.getX() / View.PIECE_SIDE_LENGTH;
            int row = (int) imageView.getY() / View.PIECE_SIDE_LENGTH;
            Piece piece = model.getPiece(row, col);
            if (isWhiteTurn == piece.isFirstPlayer()) {
                initPiecePressed(imageView);
                initPieceDragged(imageView);
                initPieceReleased(imageView);
            }
        }
    }

    /**
     * initialise piece listeners when the given piece is pressed
     * @param imageView the piece to initialise
     */
    private void initPiecePressed(ImageView imageView) {
        imageView.setOnMousePressed(event -> {
            homeX = imageView.getX();
            homeY = imageView.getY();
            marginX = event.getX() - homeX;
            marginY = event.getY() - homeY;
        });
    }

    /**
     * initialise piece listeners when the given piece is dragged
     * @param imageView the piece to initialise
     */
    private void initPieceDragged(ImageView imageView) {
        imageView.setOnMouseDragged(event -> {
            imageView.setX(event.getX() - marginX);
            imageView.setY(event.getY() - marginY);
        });
    }

    /**
     * initialise piece listeners when the given piece is released
     * @param imageView the piece to initialise
     */
    private void initPieceReleased(ImageView imageView) {
        imageView.setOnMouseReleased(event -> {
            int startCol = (int) homeX / View.PIECE_SIDE_LENGTH;
            int startRow = (int) homeY / View.PIECE_SIDE_LENGTH;
            int toCol = (int) event.getX() / View.PIECE_SIDE_LENGTH;
            int toRow = (int) event.getY() / View.PIECE_SIDE_LENGTH;
            if (move(startRow, startCol, toRow, toCol)) {
                view.refreshPieces();
                if (! checkGameStatus()){
                    return;
                }
                isWhiteTurn = !isWhiteTurn;
                initListener();
                updateInformation();
                view.getCheckWarn().setVisible(model.isKingInCheck());
                view.getUndo().setDisable(false);
            } else {
                imageView.setX(homeX);
                imageView.setY(homeY);
            }
        });
    }

    /**
     * try to move the piece at given position to given destination
     * @param startRow start row of the piece to move
     * @param startCol start column of the piece to move
     * @param toRow destination row of the piece to move
     * @param toCol destination column of the piece to move
     * @return whether the piece is moved successfully
     */
    private boolean move(int startRow, int startCol, int toRow, int toCol) {
        return model.getPiece(startRow, startCol).moveTo(toRow, toCol);
    }

    /**
     * check and respond to the game status, including win and draw
     * @return if the game can continue
     */
    private boolean checkGameStatus() {
        int res = model.getWinner();
        if (res == Model.DRAW) {
            draw();
            return false;
        } else if (res == Model.WHITE) {
            whiteScore++;
            view.getPrompt().setText(whiteName + " win!");
            prepareRestart();
            return false;
        } else if (res == Model.BLACK) {
            blackScore++;
            view.getPrompt().setText(blackName + " win!");
            prepareRestart();
            return false;
        }
        return true;
    }

    /**
     * get the prompt to display
     * @return prompt string to display
     */
    private String getPrompt(){
        return "Now " + (isWhiteTurn ? "White" : "Black");
    }

    /**
     * get the score to display
     * @return score string to display
     */
    private String getScore(){
        return whiteName + " : " + blackName + "  -  " + whiteScore + " : " + blackScore;
    }
}
