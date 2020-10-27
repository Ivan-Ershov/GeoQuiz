package com.example.geoquiz;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String KEY_CURRENT_INDEX = "current_index";
    private static final String KEY_COUNT_ANSWER = "count_answer";
    private static final String KEY_QUESTION_BANK = "question_bank";
    private static final String KEY_COUNT_USE_CHEAT = "count_use_cheat";
    private static final int REQUEST_CODE_CHEAT_ACTIVITY = 0;

    private Button mTrueButton;
    private Button mFalseButton;
    private Button mCheatButton;
    private ImageButton mNextButton;
    private ImageButton mPrevButton;
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
    private int mCountAnswer = 0;
    private int mCountUseCheat = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(TAG, "onCreate(Bundle) called");

        if (savedInstanceState != null) {

            mCurrentIndex = savedInstanceState.getInt(KEY_CURRENT_INDEX, 0);
            mCountAnswer = savedInstanceState.getInt(KEY_COUNT_ANSWER, 0);
            mCountUseCheat = savedInstanceState.getInt(KEY_COUNT_USE_CHEAT, 0);

            Question[] newQuestionBank = new Question[mQuestionBank.length];

            for (int i = 0; i < mQuestionBank.length; i++) {
                newQuestionBank[i] = (Question) savedInstanceState.getSerializable(KEY_QUESTION_BANK + i);
            }

            mQuestionBank = newQuestionBank;

        }

        setContentView(R.layout.activity_main);

        mTrueButton = findViewById(R.id.true_button);
        mFalseButton = findViewById(R.id.false_button);
        mCheatButton = findViewById(R.id.cheat_button);
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

                if (mQuestionBank[mCurrentIndex].isHasCheat()) {

                    showToastCheater();

                    return;

                }

                try {

                    mQuestionBank[mCurrentIndex].setUserAnswer(Boolean.TRUE);

                    mCountAnswer++;

                    showToastCheckAnswer();

                    if (mCountAnswer == (mQuestionBank.length - mCountUseCheat)) {
                        showToastMark();
                    }

                } catch (Exception ex) {
                    //Log.e(TAG, "mTrueButton Exception", ex);
                }

            }
        });

        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mQuestionBank[mCurrentIndex].isHasCheat()) {

                    showToastCheater();

                    return;

                }

                try {

                    mQuestionBank[mCurrentIndex].setUserAnswer(Boolean.FALSE);

                    mCountAnswer++;

                    showToastCheckAnswer();

                    if (mCountAnswer == (mQuestionBank.length - mCountUseCheat)) {
                        showToastMark();
                    }

                    Log.d(TAG, "" + mCountAnswer + " " + mQuestionBank.length + " " + mCountUseCheat);

                } catch (Exception ex) {
                    //Log.e(TAG, "mFalseButton Exception", ex);
                }

            }
        });

        mCheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(CheatActivity.newIntent(MainActivity.this, mQuestionBank[mCurrentIndex].isAnswerTrue()), REQUEST_CODE_CHEAT_ACTIVITY);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != RESULT_OK) {
            return;
        }

        if ((requestCode == REQUEST_CODE_CHEAT_ACTIVITY) && (data != null) && CheatActivity.wasShowAnswer(data)) {

            mQuestionBank[mCurrentIndex].hasCheat();

            mCountUseCheat++;

            if (mCountAnswer == (mQuestionBank.length - mCountUseCheat)) {
                showToastMark();
            }

        }

    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.d(TAG, "onStart() called");

    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.d(TAG, "onStop() called");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.d(TAG, "onDestroy() called");

    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d(TAG, "onResume() called");

    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.d(TAG, "onPause() called");

    }

    @Override
    protected void onSaveInstanceState (@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        Log.i(TAG, "onSaveInstanceState");

        outState.putInt(KEY_CURRENT_INDEX, mCurrentIndex);
        outState.putInt(KEY_COUNT_ANSWER, mCountAnswer);
        outState.putInt(KEY_COUNT_USE_CHEAT, mCountUseCheat);

        for (int i = 0; i < mQuestionBank.length; i++) {
            outState.putSerializable(KEY_QUESTION_BANK + i, mQuestionBank[i]);
        }

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

    private void showToastCheckAnswer () {

        Toast toast = Toast.makeText(MainActivity.this, mQuestionBank[mCurrentIndex].checkAnswer() ? R.string.correct_toast : R.string.incorrect_toast, Toast.LENGTH_LONG);

        toast.setGravity(Gravity.CENTER, Gravity.AXIS_X_SHIFT, Gravity.AXIS_Y_SHIFT);

        toast.show();

    }

    private void showToastCheater () {

        Toast toast = Toast.makeText(MainActivity.this, R.string.judgment_toast, Toast.LENGTH_LONG);

        toast.setGravity(Gravity.CENTER, Gravity.AXIS_X_SHIFT, Gravity.AXIS_Y_SHIFT);

        toast.show();

    }

    private void showToastMark () {

        int trueAnswers = 0;

        for (Question question : mQuestionBank) {
            if (question.checkAnswer()) trueAnswers++;
        }

        double mark = ((double)trueAnswers/mCountAnswer) * 100;

        Toast toast = Toast.makeText(MainActivity.this, Double.toString(mark), Toast.LENGTH_LONG);

        toast.setGravity(Gravity.CENTER, Gravity.AXIS_X_SHIFT, Gravity.AXIS_Y_SHIFT);
        toast.show();

    }

    private void updateQuestion () {
        mQuestionTextView.setText(mQuestionBank[mCurrentIndex].getTextResId());
    }

}