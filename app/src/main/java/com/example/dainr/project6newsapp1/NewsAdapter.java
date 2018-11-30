package com.example.dainr.project6newsapp1;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * An {@link NewsAdapter} knows how to create a list item layout for each news
 * in the data source (a list of {@link News} objects).
 *
 * These list item layouts will be provided to an adapter view like ListView
 * to be displayed to the user.
 */
class NewsAdapter extends ArrayAdapter<News> {

    /**
     * Constructs a new {@link NewsAdapter}.
     *
     * @param context of the app
     * @param news is the list of news, which is the data source of the adapter
     */
    public NewsAdapter(Context context, ArrayList<News> news) {
        super(context, 0, news);

    }
    /**
     * Returns a list item view that displays information about the news at the given position
     * in the list of news.
     */


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if there is an existing list item view (called convertView) that we can reuse,
        // otherwise, if convertView is null, then inflate a new list item layout.
        View listItemView  = convertView;
        if (listItemView == null) {
            //The inflater is used to map the instance variables to the TextView in the
            listItemView= LayoutInflater.from(getContext()).inflate(R.layout.card_view_display, parent, false);
        }
        // get object located at the position
        News currentNews= getItem(position);

        //The different objects at each position located by each of their Id's

        TextView sectionView = listItemView.findViewById(R.id.article_section);
        sectionView.setText(currentNews.getArticleSection());

        TextView nameView = listItemView.findViewById(R.id.article_name);
        nameView.setText(currentNews.getArticleName());

        TextView authorView = listItemView.findViewById(R.id.article_author);
        authorView.setText(currentNews.getArticleAuthor());

        TextView dateView = listItemView.findViewById(R.id.article_date);
        dateView.setText(currentNews.getArticleDate());

        return listItemView;
    }
}
