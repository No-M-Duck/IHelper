package com.example.ihelperbetalol;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class HelloController {

    private boolean info = true;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button uploadQandA;

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
        } catch (IOException exception) {
            error.setText("Кажется возникла проблема с загрузкой файлов. Будь умницей, проверь всё еще раз");
        }
    }

    @FXML
    void getQuestion(ActionEvent event) {
        try {
            lastQuestionMessage(Service.lastQestion());
            System.out.println(Loader.getQuestionList().size());
            System.out.println(Service.lastQestion());
            text.setText(Service.getQuestion().getText());
            btnCorrect.setDisable(true);
            btnGetQuestion.setDisable(true);
            btnGetAnswer.setDisable(false);

        } catch (IOException exception) {
            error.setText("Кажется возникла проблема с загрузкой файлов. Будь умницей, проверь всё еще раз");
        }

    }
    private void lastQuestionMessage(boolean lastQ){
        if(lastQ) {
            error.setText("Последний вопрос");
        }else {
            error.setText("");
        }
    }

    @FXML
    void upPrioritet(ActionEvent event) {
        try {
            Service.upPriority();
            getQuestion(event);
        } catch (IOException exception) {
            error.setText("Ошибка повышения рейтинга вопроса");
        }
    }

    @FXML
    void uploadQandA(ActionEvent event) {
        if (info) {
            text.setText("Ты можешь загрузить свои вопросы и ответы для отработки конкретных тем. " +
                    "Для это кликни еще раз по кнопке\n" +
                    "Сперва загрузи вопросы, а затем в НОВОМ окне ответы" +
                    "\nДа, пока что это неудобно и может быть лучше, но мне пофиг, мб потом переделаю");
        }
        else{
            FileChooser fileChooser = new FileChooser();

            FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("TEXT files (*.txt)", "*.txt");
            fileChooser.getExtensionFilters().add(extensionFilter);

            Stage stage = (Stage) uploadQandA.getScene().getWindow();
            File fileQ = fileChooser.showOpenDialog(stage);
            File fileA = fileChooser.showOpenDialog(stage);


            if (fileQ != null) {
                try {
                    System.out.println("Файл выбран: " + fileQ.getAbsolutePath());
                    Loader.loadDoc(fileQ, true);
                    Loader.loadDoc(fileA,false);
                } catch (IOException exception) {
                    error.setText("Кажется возникла проблема с загрузкой файлов. Будь умницей, проверь всё еще раз");
                }
            } else {
                System.out.println("Файл не выбран.");
            }

        }
        info = !info;
    }


    @FXML
    void initialize() {
        assert btnGetAnswer != null : "fx:id=\"btnGetAnswer\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert btnCorrect != null : "fx:id=\"btnCorrect\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert btnGetQuestion != null : "fx:id=\"btnGetQuestion\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert error != null : "fx:id=\"error\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert text != null : "fx:id=\"text\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert uploadQandA != null : "fx:id=\"uploadQandA\" was not injected: check your FXML file 'hello-view.fxml'.";
        text.setEditable(false);
        btnCorrect.setDisable(true);
        btnGetAnswer.setDisable(true);
    }

}
