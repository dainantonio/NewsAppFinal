package com.example.dainr.project6newsapp1;

public class News {

    //class declaration with 4 instance variables for this fragment
    private final String articleName;

    private final String articleAuthor;

    private final String articleDate;

    private final String articleUrl;

    // constructors in which we instantiate the instance variables
    News(String articleName, String articleAuthor, String articleDate, String articleUrl){
        this.articleName= articleName;
        this.articleAuthor=articleAuthor;
        this.articleDate=articleDate;
        this.articleUrl=articleUrl;
    }

    // get methods that return Strings and an integer
    String getArticleName(){
        return articleName;
    }

    String getArticleAuthor(){
        return articleAuthor;
    }

    String getArticleDate(){
        return articleDate;
    }

    String getArticleUrl(){
        return articleUrl;
    }


}

