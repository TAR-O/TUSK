package com.taro.tusk.closet.type;

/**
 * Created by Michelle Xiong on 05/08/2015.
 * Object Bottom
 *
 * Use for any type of bottom
 */
public class  Bottom implements ArticleType {
    private boolean isEnabled;
    private boolean isNotified;

    public Bottom ( boolean isEnabled, boolean isNotified){
        this.isEnabled=isEnabled;
        this.isNotified=isNotified;
        enabledEffectsNotified();
    }
    public boolean getIsEnabled(){
        return isEnabled;
    }
    public void setIsEnabled (boolean isEnable){
        this.isEnabled=isEnable;
        enabledEffectsNotified();
    }
    public boolean getIsNotified(){
        return isNotified;
    }
    public void setIsNotified (boolean isNotified){
        this.isNotified=isNotified;
        enabledEffectsNotified();
    }

    public void enabledEffectsNotified(){
        if(!isEnabled){
            this.isNotified=false;
        }
    }
}
