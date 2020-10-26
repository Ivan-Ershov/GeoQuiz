package com.example.geoquiz;

import java.io.Serializable;

public class Question implements Serializable {

    private final int mTextResId;
    private final boolean mAnswerTrue;
    private Boolean mUserAnswer = null;

    public Question (int textResId, boolean answerTrue) {

        mTextResId = textResId;
        mAnswerTrue = answerTrue;

    }

    public boolean isAnswerTrue() {
        return mAnswerTrue;
    }

    public int getTextResId() {
        return mTextResId;
    }

    public void setUserAnswer(Boolean userAnswer) throws Exception{

        if (mUserAnswer == null) {
            this.mUserAnswer = userAnswer;
        } else {
            throw new Exception("Repeated response.");
        }
    }

    public boolean checkAnswer () {
        return mUserAnswer == mAnswerTrue;
    }

}
