package com.sourcey.materiallogindemo;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import butterknife.ButterKnife;

public class TodoActivity extends ListActivity {
//public class TodoActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_todo);
        ButterKnife.bind(this);

        String[] clubList = {"Arsenal", "Chelsea", "Everton",
                "Liverpool", "Man City", "Man Utd", "Spurs"};

//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
//                R.layout.activity_todo, clubList);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, clubList);

        ArrayAdapter adapter2 = new ArrayAdapter<String>(this, R.layout.activity_todo, R.id.text1, clubList);




        setListAdapter(adapter2);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}