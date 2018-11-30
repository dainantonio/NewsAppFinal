package com.example.dainr.project6newsapp1;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

class NewsLoader extends AsyncTaskLoader<List<News>> {

    //** URL for news data from the guardian dataset */
    //private static String GUARDIAN_REQUEST_URL = "http://content.guardianapis.com/search?&show-tags=contributor&q=%27tech%27&api-key=c42de5ac-889c-4752-997c-0a064727fc76";

    private String mUrl;

    public NewsLoader(Context context, String url) {
        super(context);
        this.mUrl = url;

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
        if (mUrl == null) {
            return null;
        }

        // Perform the network request, parse the response, and extract a list of NewsItems.
        return QueryUtils.fetchNewsData(mUrl);
    }
}
