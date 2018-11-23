package com.example.dainr.project6newsapp1;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

/**
 * An {@link NewsAdapter} knows how to create a list item layout for each news
 * in the data source (a list of {@link News} objects).
 *
 * These list item layouts will be provided to an adapter view like ListView
 * to be displayed to the user.
 */
public class NewsAdapter extends ArrayAdapter<News> {

    /**
     * Constructs a new {@link NewsAdapter}.
     *
     * @param context of the app
     * @param news is the list of news, which is the data source of the adapter
     */
    NewsAdapter(Context context, ArrayList<News> news) {
        super(context, 0, news);

    }
    /**
     * Returns a list item view that displays information about the news at the given position
     * in the list of news.
     */

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        // Check if there is an existing list item view (called convertView) that we can reuse,
        // otherwise, if convertView is null, then inflate a new list item layout.
        View listItemView  = convertView;
        if (listItemView == null) {
            //The inflater is used to map the instance variables to the TextView in the
            listItemView= LayoutInflater.from(getContext()).inflate(R.layout.main_news_list_view, parent, false);
        }
        // get object located at the position
        News currentNews= getItem(position);

        //The different objects at each position located by each of their Id's

        TextView sectionView = listItemView.findViewById(R.id.article_section);
        sectionView.setText(Objects.requireNonNull(currentNews).getArticleSection());

        TextView nameView = listItemView.findViewById(R.id.article_name);
        nameView.setText(currentNews.getArticleName());

        TextView authorView = listItemView.findViewById(R.id.article_author);
        authorView.setText(currentNews.getArticleAuthor());

        TextView dateView = listItemView.findViewById(R.id.article_date);
        dateView.setText(currentNews.getArticleDate());




        return listItemView;
    }

    /**
     * Return the formatted date string (i.e. "1984 - 03 - 03") from a Date object.
     */
    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        return dateFormat.format(dateObject);
    }

    /**
     * Return the formatted date string (i.e. "4:30 PM") from a Date object.
     */
    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat( "MMM dd, yyyy ", Locale.US );
        return timeFormat.format(dateObject);
    }
}
