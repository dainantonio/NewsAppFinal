package com.example.dainr.project6newsapp1;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class NewsActivity extends ArrayAdapter<News> {

    public NewsActivity(Context context, ArrayList<News> news) {
        super(context, 0, news);

    }
    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        View listItemView  = convertView;
        if (listItemView == null) {
            //The inflater is used to map the instance variables to the TextView in the
            listItemView= LayoutInflater.from(getContext()).inflate(R.layout.card_view_display, parent, false);
        }

        // get object located at the position
       News currentNews= getItem(position);

        //The different objects at each position located by each of their Id's


        assert convertView != null;
        TextView sectionView = listItemView.findViewById(R.id.article_section);
        TextView nameView = listItemView.findViewById(R.id.article_name);
        TextView authorView = listItemView.findViewById(R.id.article_author);
        TextView dateView= listItemView.findViewById(R.id.article_date);

        assert currentNews != null;
        sectionView.setText(currentNews.getArticleSection());
        nameView.setText(currentNews.getArticleName());
        authorView.setText(currentNews.getArticleAuthor());
        dateView.setText(currentNews.getArticleDate());

        return listItemView;
    }
}