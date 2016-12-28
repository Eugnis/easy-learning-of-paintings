package com.eugnis.easylearningofpaintings;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.eugnis.easylearningofpaintings.data.model.Painter;
import com.eugnis.easylearningofpaintings.data.model.Painting;
import com.eugnis.easylearningofpaintings.data.model.Quiz;
import com.eugnis.easylearningofpaintings.data.model.Style;
import com.eugnis.easylearningofpaintings.data.repo.PaintingsRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class QuizGameActivity extends AppCompatActivity {
    public static final String TAG = QuizGameActivity.class.getSimpleName();
    List<Quiz> qList;
    Quiz currentQuiz;
    int quantity;
    int currentQuizPosition;

    QuizSetFragment quizSetFragment;
    SingleQuizFragment singleQuizFragment;

    String resultText="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_game);

        quizSetFragment = new QuizSetFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.quizMainLayout, quizSetFragment).commit();
    }

    public void startQuiz(View v){
        quantity = quizSetFragment.getQQuantity();
        currentQuizPosition = 0;
        qList = setUpQuiz(Painter.TAG);
        for (Quiz q: qList){
            Log.d(TAG, "OBJ: " + q.getQuizObj().getName());
            for (Painting p: q.getAnswerOptions())
                Log.d(TAG, "Answ: " + p.getName() + " " + p.getPainter().getName());
        }

        singleQuizFragment = new SingleQuizFragment();   // instantiate fragment
        getSupportFragmentManager().beginTransaction().replace(R.id.quizMainLayout, singleQuizFragment).addToBackStack(null).commit();  //  replace original fragment with new fragment, add original to backstack

    }

    public Quiz getCurrentQuiz(boolean forward){
        if (currentQuizPosition<quantity)
        {
            if (forward) currentQuiz = qList.get(currentQuizPosition++);
            else currentQuiz = qList.get(--currentQuizPosition);
            return currentQuiz;
        }
        else {
            int countGood=0;
            //resultText = "RESULT";
            String answers="";
            for (Quiz q: qList)
            {
                if (q.getAnswerResult()) countGood++;
                else answers+="\nПомилка: \"" + q.getQuizObj().getName() + "\"";


            }
            Fragment fg = new QuizSetFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.quizMainLayout, fg).commit();

            resultText+="\nРезультат "+countGood+"/"+quantity;
            resultText+=answers;
            QuizSetFragment qsf = new QuizSetFragment();
            //qsf.setResultText(resultText);
            //if (countGood<quantity) Toast.makeText(this, "Лошара! Всего " + countGood + " из " + quantity, Toast.LENGTH_LONG).show();
            Log.d(TAG, resultText);
            return null;
        }

    }

    public String getResult(){
        return resultText;
    }

    public int getQuizQuantity(){
        return quantity;
    }

    private List<Quiz> setUpQuiz(String quizTypeTag){
        List<Quiz> quizList = new ArrayList<>();
        List<Painting> paintingsList = (new PaintingsRepo()).getPaintings(true, quantity*4);

        for (int i=1;i<=quantity;i++){
            Quiz q = new Quiz();
            q.setQuizTypeModelTag(quizTypeTag);

            Painting quizObj = paintingsList.get(new Random().nextInt(paintingsList.size()));
            q.setQuizObj(quizObj);
            paintingsList.remove(quizObj);
            q.setAnswerOptions(paintingsList);

            quizList.add(q);
        }
        return quizList;
    }
}
