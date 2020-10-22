package com.example.geoquiz;

public class Question {

    private int mTextResId;
    private boolean mAnswerTrue;

    public Question (int textResId, boolean answerTrue) {

        mTextResId = textResId;
        mAnswerTrue = answerTrue;

    }

    public int getTextResId() {
        return mTextResId;
    }

    public int isAnswerTrue() {
        return mTextResId;
    }

    public void setTextResId(int mTextResId) {
        this.mTextResId = mTextResId;
    }

    public void setAnswerTrue(boolean mAnswerTrue) {
        this.mAnswerTrue = mAnswerTrue;
    }
}
