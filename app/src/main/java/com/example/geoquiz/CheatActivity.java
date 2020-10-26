package com.example.geoquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {
    private static final String TAG = CheatActivity.class.getSimpleName();
    private static final String EXTRA_ANSWER_TRUE = "com.example.geoquiz.answer_is_true";
    private static final String EXTRA_SHOW_ANSWER = "com.example.geoquiz.show_answer";

    private TextView mAnswerTextView;
    private Button mShowAnswerButton;

    private boolean mAnswerTrue;

    public static Intent newIntent (Context packageContext, boolean answerTrue) {

        Intent newIntent = new Intent(packageContext, CheatActivity.class);

        newIntent.putExtra(EXTRA_ANSWER_TRUE, answerTrue);

        return newIntent;

    }

    public static boolean wasShowAnswer (Intent intent) {
        return intent.getBooleanExtra(EXTRA_SHOW_ANSWER, false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAnswerTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_TRUE, false);

        setContentView(R.layout.activity_cheat);

        mAnswerTextView = findViewById(R.id.answer_text_view);
        mShowAnswerButton = findViewById(R.id.show_answer_button);

        mShowAnswerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mAnswerTrue) {
                    mAnswerTextView.setText(R.string.true_button);
                } else {
                    mAnswerTextView.setText(R.string.false_button);
                }

                setResultShowAnswerTrue();

            }
        });

    }

    private void setResultShowAnswerTrue() {

        Intent data = new Intent();

        data.putExtra(EXTRA_SHOW_ANSWER, true);

        setResult(RESULT_OK, data);

        Log.d(TAG, "setResult called");

    }

}