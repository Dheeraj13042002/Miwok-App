package com.example.miwok;

public class Word {

    private String mDefaultLanguage;

    private String mMiwakanguage;

    private int mResourceId;

    private int mImage = 0;


    public Word(String defaultTranslation,String MiwokTranslation, int ResourceId){
        mDefaultLanguage = defaultTranslation;
        mMiwakanguage = MiwokTranslation;
        mResourceId = ResourceId;
    }

    public Word(String defaultTranslation,String MiwokTranslation, int imageaddress, int ResourceId){
        mDefaultLanguage = defaultTranslation;
        mMiwakanguage = MiwokTranslation;
        mImage = imageaddress;
        mResourceId = ResourceId;
    }

    public String getDefaultTranlation(){
        return mDefaultLanguage;
    }

    public String getMiwokTranlation(){
        return mMiwakanguage;
    }

    public int getImageAddress(){ return mImage;};

    public boolean hasImage(){ return mImage != 0 ;}

    public int getAudioResourceId(){return mResourceId;}
}
