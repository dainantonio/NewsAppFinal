package com.example.dainr.project6newsapp1;

import android.content.Context;
import android.content.AsyncTaskLoader;

import java.util.List;

public class NewsLoader extends AsyncTaskLoader<List<News>> {

    //** URL for news data from the guardian dataset */
    private static final String GUARDIAN_REQUEST_URL =
            "https://content.guardianapis.com/search?api-key=c42de5ac-889c-4752-997c-0a064727fc76";

    NewsLoader(Context context) {
        super(context);

    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    /**
     * This is on a background thread.
     */
    @Override
    public List<News> loadInBackground() {
        if (GUARDIAN_REQUEST_URL == null) {
            return null;
        }

        // Perform the network request, parse the response, and extract a list of NewsItems.
        return QueryUtils.fetchNewsData(GUARDIAN_REQUEST_URL);
    }
}
