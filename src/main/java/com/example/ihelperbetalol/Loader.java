package com.example.ihelperbetalol;

import java.io.*;
import java.util.ArrayList;


public class Loader {

    private static final String marker="*";
    private static String userHome = System.getProperty("user.dir");

    private static ArrayList<Question> questionList = new ArrayList<>();
    private static ArrayList<Answer> answerList = new ArrayList<>();


    public static void loadDoc(File path,boolean isQuestion) throws IOException {
        String line;
        BufferedReader reader = new BufferedReader(new FileReader(path));
        StringBuilder builder = new StringBuilder();
        while ((line=reader.readLine())!=null) {
            if(line.equals(marker)){
                if(isQuestion){
                    questionList.add(new Question(builder.toString()));
                    builder.setLength(0);
                    continue;
                }else {
                    answerList.add(new Answer(builder.toString()));
                    builder.setLength(0);
                    continue;
                }

            }
            builder.append(line).append("\n");

        }
        reader.close();
    }

    private static void loadQuestions() throws IOException {
        String line;
        System.out.println(userHome);
        BufferedReader reader = new BufferedReader(new FileReader(userHome+"/docs/questions.txt"));
        StringBuilder builder = new StringBuilder();
        while ((line=reader.readLine())!=null) {
            if(line.equals(marker)){
                questionList.add(new Question(builder.toString()));
                builder.setLength(0);
                continue;
            }
            builder.append(line).append("\n");

        }
        reader.close();
    }

    private static void loadAnswers() throws IOException {
        String line;
        BufferedReader reader = new BufferedReader(new FileReader(userHome+"/docs/answers.txt"));
        StringBuilder builder = new StringBuilder();
        while ((line=reader.readLine())!=null) {
            if(line.equals(marker)){
                answerList.add(new Answer(builder.toString()));
                builder.setLength(0);
                continue;
            }
            builder.append(line).append("\n");

        }

        reader.close();
    }


    public static ArrayList<Question> getQuestionList()  throws IOException{
        if (questionList.isEmpty()) loadQuestions();
        return questionList;
    }

    public static ArrayList<Answer> getAnswerList()  throws IOException{
        if (answerList.isEmpty()) loadAnswers();
        return answerList;
    }
}
