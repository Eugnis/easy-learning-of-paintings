package com.eugnis.easylearningofpaintings.data.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by Eugnis on 23.12.2016.
 */

public class Quiz {
    public static final String TAG = Quiz.class.getSimpleName();

    private String quizTypeModelTag;
    private Painting option1, option2, option3, option4;
    private Painting quizObj;

    private boolean answer;

    public String getQuizTypeModelTag(){
        return quizTypeModelTag;
    }

    public void setQuizTypeModelTag(String quizTypeModelTag){
        this.quizTypeModelTag = quizTypeModelTag;
    }

    public void setQuizObj(Painting quizObj){ this.quizObj=quizObj;}
    public Painting getQuizObj(){ return this.quizObj;}

    public void setAnswerOptions(List<Painting> chooseList){
        Random r = new Random();
        this.option1 = chooseList.get(r.nextInt(chooseList.size()));
        chooseList.remove(this.option1);
        this.option2 = chooseList.get(r.nextInt(chooseList.size()));
        chooseList.remove(this.option2);
        this.option3 = chooseList.get(r.nextInt(chooseList.size()));
        chooseList.remove(this.option3);
        this.option4 = this.quizObj;
    }
    public ArrayList<Painting> getAnswerOptions(){
        ArrayList<Painting> answerOptions=new ArrayList<>();
        answerOptions.add(option1);
        answerOptions.add(option2);
        answerOptions.add(option3);
        answerOptions.add(option4);
        Collections.shuffle(answerOptions);
        return answerOptions;
    }

    public boolean CheckAnswer(Painting answerObj){
        this.answer = this.quizObj.equals(answerObj);
        return this.answer;
    }

    public boolean getAnswerResult(){
        return answer;
    }
}
