package com.example.ihelperbetalol;

import java.util.UUID;

public class Answer{

    private static int increment =0;
    private String text;
    //Что-то мне не нравится эта идея, как будто, я не догадался просто сделать что-то получше
    private  int id;

    public Answer(String text) {
        this.text=text;
        this.id = increment++;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "answer='" + text + '\'' +
                '}';
    }
}
