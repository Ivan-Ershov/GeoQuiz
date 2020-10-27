package com.example.geoquiz;

import java.io.Serializable;

public class Question implements Serializable {

    private final int mTextResId;
    private final boolean mAnswerTrue;
    private Boolean mUserAnswer = null;
    private boolean mHasCheat = false;

    public Question (int textResId, boolean answerTrue) {

        mTextResId = textResId;
        mAnswerTrue = answerTrue;

    }

    public boolean isHasCheat() {return mHasCheat;}

    public boolean isAnswerTrue() {
        return mAnswerTrue;
    }

    public int getTextResId() {
        return mTextResId;
    }

    public void hasCheat() {
        this.mHasCheat = true;
    }

    public void setUserAnswer(Boolean userAnswer) throws Exception{

        if (mUserAnswer == null) {
            this.mUserAnswer = userAnswer;
        } else {
            throw new Exception("Repeated response.");
        }
    }

    public boolean checkAnswer () {

        if (mUserAnswer == null) {
            return false;
        }

        return mUserAnswer == mAnswerTrue;

    }

}
