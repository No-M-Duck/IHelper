package com.example.ihelperbetalol;

public class Question extends Answer {

    private int count = 0;

    public Question(String question) {
        super(question);

    }

    public int getCount() {
        return count;
    }

    public void prioritize(){
        count++;
    }

    @Override
    public String toString() {
        return "Question{" +
                "question='" + getText() + '\'' +
                ", count=" + count +
                '}';
    }
}
