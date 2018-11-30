package com.example.dainr.project6newsapp1;

class News {

    //class declaration with 4 instance variables for this fragment

    //section name
    private String articleSection;

    //article name
    private String articleName;

     //article's date
     private String articleDate;

     //article's Url
    private String articleUrl;

     //article's author
     private String articleAuthor;


    // constructors in which we instantiate the instance variables
    News(String articleSection,String articleName, String articleDate, String articleUrl, String articleAuthor){
        this.articleSection = articleSection;
        this.articleName = articleName;
        this.articleDate = articleDate;
        this.articleUrl=articleUrl;
        this.articleAuthor = articleAuthor;


    }

    // get methods that return Strings
    String getArticleSection(){
        return articleSection;
    }

    String getArticleName(){
        return articleName;
    }

   String getArticleDate(){
        return articleDate;
    }

    String getArticleUrl(){
        return articleUrl;
    }
     String getArticleAuthor(){
         return articleAuthor;
     }
}

