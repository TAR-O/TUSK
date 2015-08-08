package com.taro.tusk.closet.type;

/**
 * Created by Michelle Xiong on 05/08/2015.
 */
public class Top implements ArticleType {
    private boolean isEnabled;
    private boolean isNotified;

    public Top ( boolean isEnabled, boolean isNotified){
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
