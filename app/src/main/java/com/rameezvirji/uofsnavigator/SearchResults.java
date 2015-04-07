package com.rameezvirji.uofsnavigator;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Hashtable;


public class SearchResults extends ActionBarActivity {

    public String[] locations;
    Hashtable locHash = new Hashtable<String, LocationMap>();
    String query;
    float currX;
    float currY;
    int floor;
    ListView resultList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
        Bundle extras = getIntent().getExtras();
        query = extras.getString(MainActivity.SQUERY);
        currX = extras.getFloat(MainActivity.CURRX);
        currY = extras.getFloat(MainActivity.CURRY);
        floor = extras.getInt(MainActivity.CURRF);
        resultList = (ListView) findViewById(R.id.searchResults);
        final TextView qLabel = (TextView) findViewById(R.id.sQueryDisplay);
        if (query.length() == 0) {
            qLabel.setText("All Results:");
        } else {
            qLabel.setText("Results for \"" + query + "\":");
        }
        createArray();
        populateList();

        resultList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String key = resultList.getItemAtPosition(position).toString();
                //Toast.makeText(getApplicationContext(), locHash.get(key).toString(), Toast.LENGTH_LONG).show();
                doDirections(key);
            }
        });
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
       locations[0] = "Thorv 159";
       locHash.put("Thorv 159", new LocationMap(0, 1, 1));
       addToArray("Thorv 105", 0, 0, 1);
       addToArray("Thorv 124", 1, 0, 1);
       addToArray("Thorv 129", 1, 0, 1);

       addToArray("Thorv 205a", 0, 0, 2);
       addToArray("Thorv 271", 0, 1, 2);
    }

    public void addToArray(String name, int nsval, int ewval, int fval) {
        String[] locationsBak = locations;
        locations = new String[locationsBak.length + 1];
        for (int i=0; i<locationsBak.length; i++) {
            locations[i] = locationsBak[i];
        }
        locations[locationsBak.length] = name;
        locHash.put(name, new LocationMap(nsval, ewval, fval));
    }

    public void populateList() {
        ArrayList<String> results = new ArrayList<>();
        for (int i=0; i<locations.length; i++) {
            if (locations[i].toLowerCase().contains(query.toLowerCase())) {
                results.add(locations[i]);
            }
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, results);
        resultList.setAdapter(adapter);
    }

    public void doDirections (String key) {
        Intent intent = new Intent(this, Directions.class);

        int ns, ew;
        if (currX > 118) {
            ew = 1;
        } else {
            ew = 0;
        }
        if (currY > 164) {
            ns = 0;
        } else {
            ns = 1;
        }

        intent.putExtra("currNS", ns);
        intent.putExtra("currEW", ew);
        intent.putExtra("currF", floor);
        intent.putExtra("destName", key);
        intent.putExtra("destNS", ((LocationMap)locHash.get(key)).getNS());
        intent.putExtra("destEW", ((LocationMap)locHash.get(key)).getEW());
        intent.putExtra("destF", ((LocationMap)locHash.get(key)).getF());

        startActivity(intent);
    }
}
