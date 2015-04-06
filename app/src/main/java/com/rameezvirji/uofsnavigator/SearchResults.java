package com.rameezvirji.uofsnavigator;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;



public class SearchResults extends ActionBarActivity {

    public String[] locations;
    String query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
        Intent intent = getIntent();
        query = intent.getStringExtra(MainActivity.SPARAMS);
        final TextView qLabel = (TextView) findViewById(R.id.sQueryDisplay);
        qLabel.setText(" Results for \"" + query + "\":");
        createArray();
        populateList();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search_results, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

       public void createArray() {
           locations = new String[1];
           locations[0] = "Bob's Burgers";
           addToArray("Tim Hortons");
           addToArray("Tim Robins Steak House");
           addToArray("John Cena's House of Pancakes");
           addToArray("McDonald's");
       }

        public void addToArray(String name) {
            String[] locationsBak = locations;
            locations = new String[locationsBak.length + 1];
            for (int i=0; i<locationsBak.length; i++) {
                locations[i] = locationsBak[i];
            }
            locations[locationsBak.length] = name;
        }

        public void populateList() {
            ListView resultList = (ListView) findViewById(R.id.searchResults);
            ArrayList<String> results = new ArrayList<>();
            for (int i=0; i<locations.length; i++) {
                if (locations[i].toLowerCase().contains(query.toLowerCase())) {
                    results.add(locations[i]);
                }
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, results);
            resultList.setAdapter(adapter);
        }

}
