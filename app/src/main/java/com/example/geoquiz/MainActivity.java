package com.example.geoquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button mTrueButton;
    private Button mFalseButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTrueButton = findViewById(R.id.true_button);
        mFalseButton = findViewById(R.id.false_button);

        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast toast = Toast.makeText(MainActivity.this, R.string.correct_toast, Toast.LENGTH_LONG);

                toast.setGravity(Gravity.CENTER, Gravity.AXIS_X_SHIFT, Gravity.AXIS_Y_SHIFT);
                toast.show();

            }
        });

        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast toast = Toast.makeText(MainActivity.this, R.string.incorrect_toast, Toast.LENGTH_LONG);

                toast.setGravity(Gravity.CENTER, Gravity.AXIS_X_SHIFT, Gravity.AXIS_Y_SHIFT);
                toast.show();

            }
        });
    }
}