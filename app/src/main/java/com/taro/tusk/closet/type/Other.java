package com.taro.tusk.closet.type;

/**
 * Created by Michelle Xiong on 10/08/2015.
 */
public class Other implements ArticleType {
    private boolean isEnabled;
    private boolean isNotified;

    public Other ( boolean isEnabled, boolean isNotified){
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
