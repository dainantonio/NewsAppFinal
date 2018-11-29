package com.example.dainr.project6newsapp1;

import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class NewsActivity extends AppCompatActivity implements LoaderCallbacks<List<News>> {

    private NewsAdapter adapter;
    /** TextView that is displayed when the list is empty */
    private TextView mEmptyStateTextView;
    private static String GUARDIAN_REQUEST_URL = "http://content.guardianapis.com/search?";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_news_list_view);

        // Find a reference to the {@link ListView} in the layout
        ListView newsListView = findViewById(R.id.list);

        mEmptyStateTextView = findViewById(R.id.empty_view);
        newsListView.setEmptyView(mEmptyStateTextView);

        // Create a new {@link ArrayAdapter} of news
        adapter = new NewsAdapter(this, new ArrayList<News>());

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        newsListView.setAdapter(adapter);

        // Set an item click listener on the ListView, which sends an intent to a web browser
        // to open a website with more information about the selected article
        newsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Find the current news that was clicked on
                News currentNews = adapter.getItem(position);

                // Convert the String URL into a URI object (to pass into the Intent constructor)
                Uri newsUri = Uri.parse(currentNews.getArticleUrl());

                // Create a new intent to view the earthquake URI
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, newsUri);

                // Send the intent to launch a new activity
                startActivity(websiteIntent);
            }
        });

        // Get a reference to the ConnectivityManager to check state of network connectivity
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        // Get details on the currently active default data network
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        // If there is a network connection, fetch data
        if (networkInfo != null && networkInfo.isConnected()) {
            // Get a reference to the LoaderManager, in order to interact with loaders.
            LoaderManager loaderManager = getLoaderManager();

            // Initialize the loader. Pass in the int ID constant defined above and pass in null for
            // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
            // because this activity implements the LoaderCallbacks interface).
            loaderManager.initLoader(0, null, this);
        } else {
            // Otherwise, display error
            // First, hide loading indicator so error message will be visible
            View loadingIndicator = findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(View.GONE);

            // Update empty state with no connection error message
            mEmptyStateTextView.setText(R.string.no_internet_connection);
        }
    }

    //onCreateLoader instantiates and returns a new Loader for the given ID
@Override
    public Loader<List<News>> onCreateLoader(int id, Bundle args) {
        // Create a new loader for the given URL

    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

    // getString retrieves a String value from the preferences.
    // The second parameter is the default value for this preference.
    String orderBy = sharedPreferences.getString(getString(R.string.settings_order_by_key), getString(R.string.settings_order_by_default));
    String category = sharedPreferences.getString(getString(R.string.settings_category_news_key), getString(R.string.settings_category_default));

    // parse breaks apart the URI string that's passed into its parameter
    Uri baseUri = Uri.parse(GUARDIAN_REQUEST_URL);

    // buildUpon prepares the baseUri that we just parsed so we can add query parameters to it
    Uri.Builder uriBuilder = baseUri.buildUpon();

    //// Append query parameter and its value. For example, the `format=geojson`
    uriBuilder.appendQueryParameter("api-key", "test");
    uriBuilder.appendQueryParameter("show-tags", "contributor");

    uriBuilder.appendQueryParameter("order-by", orderBy);

    if (!category.equals(getString(R.string.settings_category_default))) {
        uriBuilder.appendQueryParameter("category", category);
    }

    // // Return the completed uri `http://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&limit=10&minmag=minMagnitude&orderby=time
    return new NewsLoader(this, uriBuilder.toString());
}


    public void onLoadFinished(Loader<List<News>> loader, List<News> NewsItems) {
        // Hide loading indicator because the data has been loaded
        View loadingIndicator = findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.GONE);

        // Set empty state text to display "No NewsItems found."
        mEmptyStateTextView.setText(R.string.no_news);

        adapter.clear();

        if (NewsItems != null && !NewsItems.isEmpty()) {
            adapter.addAll(NewsItems);
        }
    }


    public void onLoaderReset(Loader<List<News>>loader) {
        // Loader reset, so we can clear out our existing data.
        adapter.clear();

    }

    @Override
    // This method initialize the contents of the Activity's options menu.
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the Options Menu we specified in XML
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    // method is where we can setup the specific action that occurs
    // when any of the items in the Options Menu are selected.
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //determine which item was selected and what action to take
        int id = item.getItemId();
        if (id == R.id.action_setting) {
            Intent settingsIntent = new Intent(this, SettingsActivity.class);
            startActivity(settingsIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}