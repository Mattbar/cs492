package edu.osucascades.cs492.jokearama;


import java.util.UUID;

public class Joke {
    private UUID mId;
    private String mTitle;
    private String mSetUp;
    private String mPunchLine;
    private int mStep = 0;
    private boolean mFinished;

    public Joke() {
        mId = UUID.randomUUID();
    }

    public UUID getId() {
        return mId;
    }

    public String getTitle(){
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getSetUp(){return mSetUp;}
    public void setSetUp (String setUp) {mSetUp = setUp;}

    public String getPunchLine(){return mPunchLine;}
    public void setPunchLine (String punchLine){mPunchLine = punchLine;}

    public int getStep(){return mStep;}
    public void setStep(int step){mStep = step; }

    public boolean isFinished() {
        return mFinished;
    }

    public void setFinished(boolean finished) {
        mFinished = finished;
    }
}
