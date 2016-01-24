package com.taro.tusk.closet.type;


/**
 * Created by Michelle Xiong on 04/08/2015.
 * Interface ArticleType
 *
 * Use to create more categories under article types
 */
public interface ArticleType {

    public boolean getIsEnabled();
    public void setIsEnabled (boolean isEnabled);

    public boolean getIsNotified();
    public void setIsNotified (boolean isNotified);

    //if disabled, than cannot notify
    //Note: "Warning: enabledEffectsNotified ()is never used" is incorrect
    public void enabledEffectsNotified();
}
