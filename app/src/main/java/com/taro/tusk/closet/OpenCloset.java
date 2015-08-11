package com.taro.tusk.closet;

import com.taro.tusk.closet.type.ArticleType;
import com.taro.tusk.closet.type.Bottom;
import com.taro.tusk.closet.type.Other;
import com.taro.tusk.closet.type.Outerwear;
import com.taro.tusk.closet.type.Shoes;
import com.taro.tusk.closet.type.Top;

/**
 * Created by Michelle Xiong on 04/08/2015.
 *
 * ***static ArticleType variables used for whole app***
 *
 * use static import to use these variables
 */
public class OpenCloset {
    public static ArticleType tShirt = new Top(true,false);
    public static ArticleType tank = new Top (true,false);
    public static ArticleType shorts = new Bottom(true,false);
    public static ArticleType skirt = new Bottom (false,false);
    public static ArticleType sweater = new Outerwear(true,true);
    public static ArticleType raincoat = new Outerwear (true,true);
    public static ArticleType boots = new Shoes (true,false);
    public static ArticleType sandals = new Shoes(false,false);
    public static ArticleType sneakers = new Shoes(true,false);
    public static ArticleType umbrella = new Other(true,true);

}
