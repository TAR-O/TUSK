package com.taro.tusk.closet;

/**
 * Created by Michelle Xiong on 04/08/2015.
 *
 *
 * Clothing article class to create different article objects
 */


public class ClothingArticle {

    private int id;
    private String article;
    private String articleType;
    private int isEnabled; //do try catch
    private int isNotified; //do try catch

    public ClothingArticle ( int id, String article, String articleType, int isEnabled, int isNotified){
        this.id = id;
        this.article=article;
        checkArticleType(articleType);
        this.isEnabled=isEnabled;
        this.isNotified=isNotified;
        enabledEffectsNotified();
    }

    public ClothingArticle ( String article, String articleType, int isEnabled, int isNotified){
        this.article=article;
        checkArticleType(articleType);
        this.isEnabled=isEnabled;
        this.isNotified=isNotified;
        enabledEffectsNotified();
    }

    public int getID (){
        return id;
    }
    public void setID(int id){
        this.id=id;
    }

    public String getArticle (){
        return article;
    }
    public void setArticle(String article){
        this.article=article;
    }

    public String getArticleType (){
        return articleType;
    }
    public void setArticleType(String articleType){
        checkArticleType(articleType);
       // this.articleType=articleType;
    }

    public int getIsEnabled(){
        return isEnabled;
    }
    public void setIsEnabled (int isEnabled){
        this.isEnabled=isEnabled;
        enabledEffectsNotified();
    }

    public int getIsNotified(){
        return isNotified;
    }
    public void setIsNotified (int isNotified){
        this.isNotified=isNotified;
        enabledEffectsNotified();
    }

    public void enabledEffectsNotified(){
        if(isEnabled==0){
            this.isNotified=0;
        }
    }

    public void checkArticleType(String articleType){
        switch (articleType){
            case "top": this.articleType=articleType;
                break;
            case "bottom": this.articleType=articleType;
                break;
            case "outerwear" : this.articleType=articleType;
                break;
            case "shoes": this.articleType=articleType;
                break;
            case "others": this.articleType=articleType;
                break;
            default:
                throw new StringIndexOutOfBoundsException("Cannot resolve value " + articleType);
                //change this?? another type of throw? or use try and catch insteaD?

        }
    }
}

