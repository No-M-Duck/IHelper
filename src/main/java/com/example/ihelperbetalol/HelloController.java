package com.example.ihelperbetalol;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class HelloController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnCorrect;

    @FXML
    private TextArea text;

    @FXML
    private Button btnGetAnswer;

    @FXML
    private Button btnGetQuestion;

    @FXML
    private Label error;

    @FXML
    void getAnswer(ActionEvent event) {
        try {
            text.setText(Service.getNumberAnswer().getText());
            btnCorrect.setDisable(false);
            btnGetQuestion.setDisable(false);
            btnGetAnswer.setDisable(true);
        }catch ( IOException exception){
            error.setText("Кажется возникла проблема с загрузкой файлов. Будь умницей, проверь всё еще раз");
        }
    }

    @FXML
    void getQuestion(ActionEvent event) {
        try {
            text.setText(Service.getQuestion().getText());
            btnCorrect.setDisable(true);
            btnGetQuestion.setDisable(true);
            btnGetAnswer.setDisable(false);
        }catch (IOException exception){
            error.setText("Кажется возникла проблема с загрузкой файлов. Будь умницей, проверь всё еще раз");
        }

    }
    @FXML
    void upPrioritet(ActionEvent event) {
        try{
            Service.upPriority();
            getQuestion(event);
        }catch (IOException exception){
            error.setText("Ошибка повышения рейтинга вопроса");
        }
    }

    @FXML
    void initialize() {
        assert btnGetAnswer != null : "fx:id=\"btnGetAnswer\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert btnCorrect != null : "fx:id=\"btnCorrect\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert btnGetQuestion != null : "fx:id=\"btnGetQuestion\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert error != null : "fx:id=\"error\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert text != null : "fx:id=\"text\" was not injected: check your FXML file 'hello-view.fxml'.";
        text.setEditable(false);
        btnCorrect.setDisable(true);
        btnGetAnswer.setDisable(true);
    }

}
