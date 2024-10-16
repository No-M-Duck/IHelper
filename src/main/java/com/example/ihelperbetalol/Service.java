package com.example.ihelperbetalol;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

/** Значит хочу случайный вопрос из списка, но их нужно фильтровать, поэтому решил пусть фильтрует Stream API
 * НО он не умеет получать случайный элемент из потока.
 * Поэтому придется снова превратить этот поток в список и затем взять случайный элемент.
 * Казалось бы, бери сразу случайный элемент, зачем тебе сортировать?(ЛОВУШКА)
 * При постоянной выборке случайного элемента, они будут переходить в множество "высокого приоритета", чтобы вопрос не попадался нам часто.
 * Однако когда множество вопросов "низкого приоритета" будет мало, то вероятность попасть в его элемент также уменьшается.
 * Значит надо сортировать и стрелять точечно в малую группу!
 * Поэтому мы фильтруем поток, затем делаем его списком и берем случайный элемент
 * Если ты знаешь как это сделать лучше, то напиши мне в Telegram https://t.me/amoraltobudet
 */
public class Service {
    private static Random random = new Random();
    private static int number = 0;

    private static int priority = 1;


    public static Question getQuestion()  throws IOException {
        List<Question> sortedQuestions = Loader.getQuestionList().stream().filter(q->q.getCount()<priority).collect(Collectors.toList());
        if(sortedQuestions.isEmpty()){
            priority++;
            return getQuestion();
        }
        Question question = sortedQuestions.get(random.nextInt(sortedQuestions.size()));
        question.prioritize();
        number = question.getId();
        return question;

    }

    public static Answer getNumberAnswer() throws IOException{
        return Loader.getAnswerList().get(number);
    }
}
