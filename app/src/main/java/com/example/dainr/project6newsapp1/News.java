package com.example.dainr.project6newsapp1;

class News {

    //class declaration with 4 instance variables for this fragment

    //section name
    private final String articleSection;

    //article name
    private final String articleName;

    //article's author
    private final String articleAuthor;

    //article's date
    private final String articleDate;

    //article's Url
    private final String articleUrl;

    // constructors in which we instantiate the instance variables
    News(String articleSection,String articleName, String articleAuthor, String articleDate, String articleUrl){
        this.articleSection = articleSection;
        this.articleName = articleName;
        this.articleAuthor = articleAuthor;
        this.articleDate = articleDate;
        this.articleUrl=articleUrl;
    }

    // get methods that return Strings
    String getArticleSection(){
        return articleSection;
    }

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

