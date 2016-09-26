package com.example.topleaguev2;

import android.app.ListActivity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.HashMap;

public class TopLeagueActivity extends ListActivity {

    /**
     * XML Parser, which will parse the fetched data.
     */
    private XMLParser xmlParser;

    /**
     * URL to fetch data from.
     */
    private static final String URL = "https://spiider.eu/topLeague.xml";

    /**
     * Key to item node.
     */
    private static final String KEY_ITEM = "item";

    /**
     * Key to id node.
     */
    private static final String KEY_ID = "id";

    /**
     * Key to name node.
     */
    private static final String KEY_NAME = "name";

    /**
     * Key to node score.
     */
    private static final String KEY_SCORE = "score";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            this.xmlParser = new XMLParser();
            new DownloadWebPageText().execute(URL);
        }
    }

    /**
     * Display the result of the fetch in the UI.
     *
     * @param stringXML Result to be displayed.
     */
    private void setListView(String stringXML) {
        ArrayList<HashMap<String, String>> menuItems = new ArrayList<>();

        // Get DOM
        Document document = this.xmlParser.getDomElement(stringXML);
        NodeList nodeList = document.getElementsByTagName(KEY_ITEM);

        // Iterates over all nodes.
        for (int node = 0; node < nodeList.getLength(); node++) {
            // Creation of the HashMap.
            HashMap<String, String> hashMap = new HashMap<>();
            Element element = (Element) nodeList.item(node);

            // Adding the child nodes to the HashMap | key => value
            hashMap.put(KEY_ID, this.xmlParser.getValue(element, KEY_ID));
            hashMap.put(KEY_NAME, this.xmlParser.getValue(element, KEY_NAME));
            hashMap.put(KEY_SCORE, this.xmlParser.getValue(element, KEY_SCORE));

            // Adding the HashMap to the ArrayList
            menuItems.add(hashMap);
        }

        // Adds the menu items to the list view.
        ListAdapter listAdapter = new SimpleAdapter(this, menuItems, R.layout.list_item,
                new String[] { KEY_NAME, KEY_SCORE }, new int[] { R.id.name, R.id.score});
        setListAdapter(listAdapter);
    }

    private class DownloadWebPageText extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... strings) {
            return TopLeagueActivity.this.xmlParser.getXMLFromURL(strings[0]);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            // If we want to maintain information on the progress...
        }

        @Override
        protected void onPostExecute(String result) {
            setListView(result);
        }

    }

}
