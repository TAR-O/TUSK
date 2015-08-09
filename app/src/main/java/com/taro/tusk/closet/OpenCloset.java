package com.taro.tusk.closet;

import com.taro.tusk.closet.type.ArticleType;
import com.taro.tusk.closet.type.Bottom;
import com.taro.tusk.closet.type.Top;

/**
 * Created by Michelle Xiong on 04/08/2015.
 *
 * ***static ArticleType variables used for whole app***
 *
 * use static import to use these variables
 */
public class OpenCloset {
    public static ArticleType tShirt = new Top(true,true);
    public static ArticleType tank = new Top (true,true);
    public static ArticleType shorts = new Bottom(false,true);
    public static ArticleType skirt = new Bottom (false,false);

}
