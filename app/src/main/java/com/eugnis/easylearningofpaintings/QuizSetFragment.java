package com.eugnis.easylearningofpaintings;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class QuizSetFragment extends Fragment {

    NumberPicker np;
    TextView resultTextView;
    View v;

    public QuizSetFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_quiz_set, container, false);
        np = (NumberPicker) v.findViewById(R.id.qQuantity);
        np.setMinValue(10);
        np.setMaxValue(50);
        resultTextView = (TextView) v.findViewById(R.id.resultTextView);
        // Inflate the layout for this fragment
        return v;
    }

    @Override
    public void onActivityCreated (Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        resultTextView.setText("");
    }

    public int getQQuantity(){
        return np.getValue();
    }

    @Override
    public void onStart(){
        super.onStart();
        resultTextView.setText(((QuizGameActivity)this.getActivity()).getResult());
    }

}
