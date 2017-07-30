package gui;

import com.jfoenix.controls.*;
import com.jfoenix.controls.events.JFXDialogEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import model.*;

import java.net.URL;
import java.util.ResourceBundle;


/**
 * This class is for solving
 */
public class Contoller implements Initializable {
    @FXML private JFXTextArea idealtext;
    @FXML private JFXTextField variabletext;
    @FXML private ToggleGroup ordering;
    @FXML private JFXButton solvebutton;
    @FXML private JFXSpinner waiting;
    @FXML private Pane cover;
    @FXML private JFXButton calculating;
    @FXML private JFXRadioButton lex;
    @FXML private JFXRadioButton grlex;
    @FXML private JFXRadioButton grevlex;
    @FXML private JFXTextArea answerarea;

    @FXML private StackPane stackpane;
    @FXML private Pane dialoglabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lex.setUserData("lex");
        grevlex.setUserData("grevlex");
        grlex.setUserData("grlex");
        stackpane.setVisible(false);
        dialoglabel.setVisible(false);
    }

    @FXML
    private void onSolveButtonPressed(ActionEvent event) {
        String errormessage = "Some Error Happened";

        waiting.setVisible(true);
        cover.setVisible(true);
        calculating.setVisible(true);

        try {
            String var = variabletext.getText();
            String id = idealtext.getText();

            Order order = null;

            if (ordering.getSelectedToggle().getUserData().equals("lex")) {
                order = new LexicoOrder();
            } else if (ordering.getSelectedToggle().getUserData().equals("grlex")) {
                order = new GrlexOrder();
            } else if (ordering.getSelectedToggle().getUserData().equals("grevlex")) {
                order = new GrevlexOrder();
            }

            VariableList varList = Interpreter.interpretVarList(var);
            Ideal ideal = Interpreter.interpretIdeal(id, varList);

            GroebnerBasis gb = Buchburger.buchburger(ideal, order);
            gb.minimalize();
            gb.reduce(order);

            String groebnerBasis = Interpreter.verbalizeGroebnerBasis(gb, varList);

            answerarea.setText(groebnerBasis);
        }
        catch (Exception e) {
            answerarea.setText(errormessage);
        }

        waiting.setVisible(false);
        cover.setVisible(false);
        calculating.setVisible(false);
    }

    @FXML
    // deprecated (temporary)
    private void onLatexButtonPressed(ActionEvent event) {
        stackpane.setVisible(true);
        dialoglabel.setVisible(true);

        JFXDialogLayout content = new JFXDialogLayout();
        content.setBody(dialoglabel);
        JFXButton button = new JFXButton("okay");

        JFXDialog dialog = new JFXDialog(stackpane, content, JFXDialog.DialogTransition.CENTER);
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dialog.close();

                stackpane.setVisible(false);
            }
        });
        dialog.setOnDialogClosed(new EventHandler<JFXDialogEvent>() {
            @Override
            public void handle(JFXDialogEvent event) {
                stackpane.setVisible(false);
            }
        });
        content.setActions(button);
        dialog.show();
    }
}
