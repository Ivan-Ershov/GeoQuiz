package com.example.geoquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private Button mPrevButton;
    private TextView mQuestionTextView;

    private Question[] mQuestionBank = new Question[] {

            new Question(R.string.question_australia, true),
            new Question(R.string.question_africa, false),
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_americas, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_asia, true)

    };

    private int mCurrentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTrueButton = findViewById(R.id.true_button);
        mFalseButton = findViewById(R.id.false_button);
        mNextButton = findViewById(R.id.next_button);
        mPrevButton = findViewById(R.id.prev_button);
        mQuestionTextView = findViewById(R.id.question_text_view);

        updateQuestion();

        mQuestionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNextQuestion();
            }
        });

        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast toast = Toast.makeText(MainActivity.this, checkAnswer(true), Toast.LENGTH_LONG);

                toast.setGravity(Gravity.CENTER, Gravity.AXIS_X_SHIFT, Gravity.AXIS_Y_SHIFT);
                toast.show();

            }
        });

        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast toast = Toast.makeText(MainActivity.this, checkAnswer(false), Toast.LENGTH_LONG);

                toast.setGravity(Gravity.CENTER, Gravity.AXIS_X_SHIFT, Gravity.AXIS_Y_SHIFT);
                toast.show();

            }
        });

        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNextQuestion();
            }
        });

        mPrevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPrevQuestion();
            }
        });

    }

    private void showPrevQuestion () {

        mCurrentIndex--;

        if (mCurrentIndex < 0) {
            mCurrentIndex = mQuestionBank.length - 1;
        }

        updateQuestion();

    }

    private void showNextQuestion () {

        mCurrentIndex++;
        mCurrentIndex %= mQuestionBank.length;

        updateQuestion();

    }

    private void updateQuestion () {
        mQuestionTextView.setText(mQuestionBank[mCurrentIndex].getTextResId());
    }

    private int checkAnswer (boolean userAnswer) {

        return (userAnswer == mQuestionBank[mCurrentIndex].isAnswerTrue()) ? R.string.correct_toast : R.string.incorrect_toast;

    }

}