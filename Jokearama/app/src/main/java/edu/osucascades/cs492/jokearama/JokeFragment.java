package edu.osucascades.cs492.jokearama;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import java.util.UUID;

public class JokeFragment extends Fragment {
    private static final String ARG_JOKE_ID = "joke_id";

    private Joke mJoke;
    private TextView mJokeNameField;
    private TextView mKnockKnock;
    private TextView mWhoIsTheir;
    private TextView mSetUp;
    private TextView mSetUpWho;
    private TextView mPunchLine;
    private ConstraintLayout mJokePage;
    int mStep;

    public static JokeFragment newInstance(UUID jokeId){
        Bundle args = new Bundle();
        args.putSerializable(ARG_JOKE_ID,jokeId);

        JokeFragment fragment = new JokeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID jokeId = (UUID) getArguments().getSerializable(ARG_JOKE_ID);
        mJoke = JokeLab.get(getActivity()).getJoke(jokeId);
        mStep = mJoke.getStep();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_joke, container, false);

        mJokeNameField = v.findViewById(R.id.joke_name);
        mJokeNameField.append(mJoke.getTitle());



        mKnockKnock = v.findViewById(R.id.knock_knock);
        mWhoIsTheir = v.findViewById(R.id.who_is_there);
        mSetUp = v.findViewById(R.id.set_up);
        mSetUpWho = v.findViewById(R.id.set_up_who);
        mPunchLine = v.findViewById(R.id.punch_line);

        if(mJoke.isFinished()){
            mKnockKnock.append("Knock Knock");
            mWhoIsTheir.append("Who's there?");
            mSetUp.append(mJoke.getSetUp());
            mSetUpWho.append(mJoke.getSetUp() +" Who");
            mPunchLine.append(mJoke.getPunchLine());
        }

        switch (mStep){
            case 0:
                break;
            case 1:
                mKnockKnock.append("Knock Knock");
                break;
            case 2:
                mKnockKnock.append("Knock Knock");
                mWhoIsTheir.append("Who's there?");
                break;
            case 3:
                mKnockKnock.append("Knock Knock");
                mWhoIsTheir.append("Who's there?");
                mSetUp.append(mJoke.getSetUp());
                break;
            case 4:
                mKnockKnock.append("Knock Knock");
                mWhoIsTheir.append("Who's there?");
                mSetUp.append(mJoke.getSetUp());
                mSetUpWho.append(mJoke.getSetUp() +" Who");
                break;
        }

        mJokePage = v.findViewById(R.id.joke);
        mJokePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mJoke.isFinished()){
                    return;
                }
                switch (mStep){
                    case 0:
                        mKnockKnock.append("Knock Knock");
                        mStep++;
                        mJoke.setStep(mStep);
                        break;
                    case 1:
                        mWhoIsTheir.append("Who's there?");
                        mStep++;
                        mJoke.setStep(mStep);
                        break;
                    case 2:
                        mSetUp.append(mJoke.getSetUp());
                        mStep++;
                        mJoke.setStep(mStep);
                        break;
                    case 3:
                        mSetUpWho.append(mJoke.getSetUp() +" Who");
                        mStep++;
                        mJoke.setStep(mStep);
                        break;
                    case 4:
                        mPunchLine.append(mJoke.getPunchLine());
                        mJoke.setFinished(true);
                        break;
                    default:
                        break;
                }

            }
        });


        return v;
    }

}
