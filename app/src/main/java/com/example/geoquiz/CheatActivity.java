package com.example.geoquiz;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class CheatActivity extends AppCompatActivity {
    //private static final String TAG = CheatActivity.class.getSimpleName();
    private static final String EXTRA_ANSWER_TRUE = "com.example.geoquiz.answer_is_true";
    private static final String EXTRA_SHOW_ANSWER = "com.example.geoquiz.show_answer";
    private static final String EXTRA_CHEAT_COUNT = "com.example.geoquiz.cheat_count";
    private static final String KEY_IS_CHEAT = "is_cheat";

    private TextView mAnswerTextView;
    private Button mShowAnswerButton;
    private TextView mCheatCountTextView;

    private boolean mAnswerTrue;
    private int mCheatCount;
    private boolean mIsCheat = false;

    public static Intent newIntent (Context packageContext, boolean answerTrue, int cheatCount) {

        Intent newIntent = new Intent(packageContext, CheatActivity.class);

        newIntent.putExtra(EXTRA_ANSWER_TRUE, answerTrue);

        newIntent.putExtra(EXTRA_CHEAT_COUNT, cheatCount);

        return newIntent;

    }

    public static boolean wasShowAnswer (Intent intent) {
        return intent.getBooleanExtra(EXTRA_SHOW_ANSWER, false);
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            mIsCheat = savedInstanceState.getBoolean(KEY_IS_CHEAT, false);
        }

        mAnswerTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_TRUE, false);
        mCheatCount = getIntent().getIntExtra(EXTRA_CHEAT_COUNT, 0);

        setContentView(R.layout.activity_cheat);

        mAnswerTextView = findViewById(R.id.answer_text_view);
        mShowAnswerButton = findViewById(R.id.show_answer_button);
        mCheatCountTextView = findViewById(R.id.cheat_count_text_view);

        mCheatCountTextView.setText("" + mCheatCount);

        if (mIsCheat) {

            showAnswer();

            setResultShowAnswerTrue();

            mCheatCountTextView.setVisibility(View.INVISIBLE);
            mShowAnswerButton.setVisibility(View.INVISIBLE);

        }

        mShowAnswerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showAnswer();

                setResultShowAnswerTrue();

                animation();

            }
        });

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putBoolean(KEY_IS_CHEAT, mIsCheat);

    }

    private void showAnswer () {

        if (mAnswerTrue) {
            mAnswerTextView.setText(R.string.true_button);
        } else {
            mAnswerTextView.setText(R.string.false_button);
        }

    }

    private void animation () {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            int cx = mShowAnswerButton.getWidth() / 2;

            int cy = mShowAnswerButton.getHeight() / 2;

            float radius = mShowAnswerButton.getWidth();

            Animator anim = ViewAnimationUtils.createCircularReveal(mShowAnswerButton, cx, cy, radius, 0);

            anim.addListener(new AnimatorListenerAdapter() {

                @Override
                public void onAnimationEnd(Animator animation) {

                    super.onAnimationEnd(animation);

                    mShowAnswerButton.setVisibility(View.INVISIBLE);

                }

            });

            anim.start();

        } else {
            mShowAnswerButton.setVisibility(View.INVISIBLE);
        }

        mCheatCountTextView.setVisibility(View.INVISIBLE);

    }

    private void setResultShowAnswerTrue () {

        Intent data = new Intent();

        data.putExtra(EXTRA_SHOW_ANSWER, true);

        setResult(RESULT_OK, data);

        mIsCheat = true;

    }

}