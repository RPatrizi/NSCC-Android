package com.example.cruddypizza;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class History extends AppCompatActivity {

    // create views
    Button btNewOrder, btLoadOrder;
    ListView lvOrders;

    // create variables
    String [] langEng, langPort;
    SharedPreferences prefs;
    String language;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        // set up views
        btLoadOrder = findViewById(R.id.btLoadOrder);
        btNewOrder = findViewById(R.id.btNewOrder);
        lvOrders = findViewById(R.id.lvOrders);

        // Create Shared preferences instance
        prefs = getSharedPreferences("ChosenPrefs", MODE_PRIVATE);
        language = prefs.getString("language", "english");

        // get string arrays
        Resources res = getResources();
        langEng = res.getStringArray(R.array.english);
        langPort = res.getStringArray(R.array.portuguese);

        // Set the language based on the saved language in shared preferences
        if (language.equals("english")) {
            setLang(langEng);
        } else {
            setLang(langPort);
        }

        // Set click listener classes
        btLoadOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(History.this, Edit_Order.class);
                intent.putExtra("language", language);
                startActivity(intent);
            }
        });

        btNewOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(History.this, Order.class);
                intent.putExtra("language", language);
                startActivity(intent);
            }
        });

    } // end of onCreate

    private void setLang(String[] langArray) {
        btLoadOrder.setText(langArray[14]);
        btNewOrder.setText(langArray[15]);
    }
} // end of History class