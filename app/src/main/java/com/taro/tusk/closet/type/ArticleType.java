package com.taro.tusk.closet.type;


/**
 * Created by Michelle Xiong on 04/08/2015.
 */
public interface ArticleType {

    public boolean getIsEnabled();
    public void setIsEnabled (boolean isEnabled);

    public boolean getIsNotified();
    public void setIsNotified (boolean isNotified);

    public void enabledEffectsNotified();
}
