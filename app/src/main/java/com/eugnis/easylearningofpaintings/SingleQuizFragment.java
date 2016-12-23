package com.eugnis.easylearningofpaintings;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.eugnis.easylearningofpaintings.data.model.Painting;
import com.eugnis.easylearningofpaintings.data.model.Quiz;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class SingleQuizFragment extends Fragment {

    public static final String TAG = SingleQuizFragment.class.getSimpleName();

    ImageView quizPicture;
    Button buttonAnswer1, buttonAnswer2, buttonAnswer3, buttonAnswer4, backQuizBtn, forwardQuizBtn;
    Quiz curQuiz;
    ProgressBar progressBar;
    TextView progressCountText;
    List<Painting> answerOptions;

    int countQuiz;
    int quizQuantity;

    public SingleQuizFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_single_quiz, container, false);
        quizPicture = (ImageView) v.findViewById(R.id.quizPicture);
        buttonAnswer1 = (Button) v.findViewById(R.id.buttonAnswer1);
        buttonAnswer2 = (Button) v.findViewById(R.id.buttonAnswer2);
        buttonAnswer3 = (Button) v.findViewById(R.id.buttonAnswer3);
        buttonAnswer4 = (Button) v.findViewById(R.id.buttonAnswer4);
        backQuizBtn = (Button) v.findViewById(R.id.backQuizBtn);
        forwardQuizBtn = (Button) v.findViewById(R.id.forwardQuizBtn);
        backQuizBtn.setOnClickListener(onAnswerClickListener);
        forwardQuizBtn.setOnClickListener(onAnswerClickListener);
        buttonAnswer1.setOnClickListener(onAnswerClickListener);
        buttonAnswer2.setOnClickListener(onAnswerClickListener);
        buttonAnswer3.setOnClickListener(onAnswerClickListener);
        buttonAnswer4.setOnClickListener(onAnswerClickListener);
        progressBar = (ProgressBar) v.findViewById(R.id.progressBar);
        progressCountText = (TextView) v.findViewById(R.id.progressCountText);
        // Inflate the layout for this fragment

        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        quizQuantity = ((QuizGameActivity)this.getActivity()).getQuizQuantity();
        progressBar.setMax(quizQuantity);
        loadQuiz(true);

    }

    private View.OnClickListener onAnswerClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.buttonAnswer1:
                    //buttonAnswer1.setBackgroundResource(android.R.drawable.btn_default);
                    buttonAnswer1.setEnabled(false);
                    buttonAnswer2.setEnabled(false);
                    buttonAnswer3.setEnabled(false);
                    buttonAnswer4.setEnabled(false);
                    if (curQuiz.CheckAnswer(answerOptions.get(0))) {
                        buttonAnswer1.setBackgroundResource(R.drawable.menubut2);
                        Log.d(TAG, "Answer OK " + curQuiz.getQuizObj().getName());
                    }
                    else {
                        buttonAnswer1.setBackgroundResource(android.R.drawable.btn_default);
                        Log.d(TAG, "Answer FAIL " + curQuiz.getQuizObj().getName());
                    }
                    //DO something
                    break;
                case R.id.buttonAnswer2:
                    buttonAnswer1.setEnabled(false);
                    buttonAnswer2.setEnabled(false);
                    buttonAnswer3.setEnabled(false);
                    buttonAnswer4.setEnabled(false);
                    if (curQuiz.CheckAnswer(answerOptions.get(1))) {
                        buttonAnswer2.setBackgroundResource(R.drawable.menubut2);
                        Log.d(TAG, "Answer OK " + curQuiz.getQuizObj().getName());
                    }
                    else {
                        buttonAnswer2.setBackgroundResource(android.R.drawable.btn_default);
                        Log.d(TAG, "Answer FAIL " + curQuiz.getQuizObj().getName());
                    }
                    //DO something
                    break;
                case R.id.buttonAnswer3:
                    buttonAnswer1.setEnabled(false);
                    buttonAnswer2.setEnabled(false);
                    buttonAnswer3.setEnabled(false);
                    buttonAnswer4.setEnabled(false);
                    if (curQuiz.CheckAnswer(answerOptions.get(2))) {
                        buttonAnswer3.setBackgroundResource(R.drawable.menubut2);
                        Log.d(TAG, "Answer OK " + curQuiz.getQuizObj().getName());
                    }
                    else {
                        buttonAnswer3.setBackgroundResource(android.R.drawable.btn_default);
                        Log.d(TAG, "Answer FAIL " + curQuiz.getQuizObj().getName());
                    }
                    //DO something
                    break;
                case R.id.buttonAnswer4:
                    buttonAnswer1.setEnabled(false);
                    buttonAnswer2.setEnabled(false);
                    buttonAnswer3.setEnabled(false);
                    buttonAnswer4.setEnabled(false);
                    if (curQuiz.CheckAnswer(answerOptions.get(3))) {
                        buttonAnswer4.setBackgroundResource(R.drawable.menubut2);
                        Log.d(TAG, "Answer OK " + curQuiz.getQuizObj().getName());
                    }
                    else {
                        buttonAnswer4.setBackgroundResource(android.R.drawable.btn_default);
                        Log.d(TAG, "Answer FAIL " + curQuiz.getQuizObj().getName());
                    }
                    //DO something
                    break;
                case R.id.forwardQuizBtn:
                    loadQuiz(true);
                    break;
                case R.id.backQuizBtn:
                    loadQuiz(false);
                    break;
            }


        }
    };


    private void loadQuiz(boolean forward){
        curQuiz = ((QuizGameActivity)this.getActivity()).getCurrentQuiz(forward);
        if (curQuiz!=null)
        {
            if (forward) countQuiz++;
            else countQuiz--;

            curQuiz.getQuizObj().setPicture();
            quizPicture.setImageBitmap(curQuiz.getQuizObj().getPicture());

            answerOptions = curQuiz.getAnswerOptions();
            buttonAnswer1.setText("\""+answerOptions.get(0).getName() + "\"\n" + answerOptions.get(0).getPainter().getName());
            buttonAnswer2.setText("\""+answerOptions.get(1).getName() + "\"\n" + answerOptions.get(1).getPainter().getName());
            buttonAnswer3.setText("\""+answerOptions.get(2).getName() + "\"\n" + answerOptions.get(2).getPainter().getName());
            buttonAnswer4.setText("\""+answerOptions.get(3).getName() + "\"\n" + answerOptions.get(3).getPainter().getName());

            buttonAnswer1.setEnabled(true);
            buttonAnswer2.setEnabled(true);
            buttonAnswer3.setEnabled(true);
            buttonAnswer4.setEnabled(true);

            progressBar.setProgress(countQuiz);
            progressCountText.setText(countQuiz + "/" + quizQuantity);
        }
        buttonAnswer1.setBackgroundResource(android.R.drawable.btn_default);
        buttonAnswer2.setBackgroundResource(android.R.drawable.btn_default);
        buttonAnswer3.setBackgroundResource(android.R.drawable.btn_default);
        buttonAnswer4.setBackgroundResource(android.R.drawable.btn_default);

    }

}
