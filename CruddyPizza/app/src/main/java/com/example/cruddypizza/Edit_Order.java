package com.example.cruddypizza;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ToggleButton;

public class Edit_Order extends AppCompatActivity {

    // create views
    TextView tvEditWelcome, tvEditCustInfo, tvEditCustName, tvEditCustPhone, tvEditCustAddress, tvEditChoose, tvEditSize, tvEditTopping1, tvEditTopping2, tvEditTopping3;
    EditText etEditCustName, etEditCustPhone, etEditCustAddress;
    Spinner spinEditSize, spinEditTopping1, spinEditTopping2, spinEditTopping3;
    Button btEditEditOrder, btEditDelOrder, btEditNewOrder;
    ToggleButton togBtEditEnPt;

    // create variables
    String [] langEng, langPort;
    SharedPreferences prefs;
    String language;

    ArrayAdapter<CharSequence> adapterSize;
    ArrayAdapter<CharSequence> adapterTop1;
    ArrayAdapter<CharSequence> adapterTop2;
    ArrayAdapter<CharSequence> adapterTop3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_order);

        // set up views
        tvEditWelcome = findViewById(R.id.tvEditWelcome);
        tvEditCustInfo = findViewById(R.id.tvEditCustInfo);
        tvEditCustName = findViewById(R.id.tvEditCustName);
        tvEditCustPhone = findViewById(R.id.tvEditCustPhone);
        tvEditCustAddress = findViewById(R.id.tvEditCustAddress);
        tvEditChoose = findViewById(R.id.tvEditChoose);
        tvEditSize = findViewById(R.id.tvEditSize);
        tvEditTopping1 = findViewById(R.id.tvEditTopping1);
        tvEditTopping2 = findViewById(R.id.tvEditTopping2);
        tvEditTopping3 = findViewById(R.id.tvEditTopping3);
        etEditCustName = findViewById(R.id.etEditCustName);
        etEditCustPhone = findViewById(R.id.etEditCustPhone);
        etEditCustAddress = findViewById(R.id.etEditCustAddress);
        spinEditSize = findViewById(R.id.spinEditSize);
        spinEditTopping1 = findViewById(R.id.spinEditTopping1);
        spinEditTopping2 = findViewById(R.id.spinEditTopping2);
        spinEditTopping3 = findViewById(R.id.spinEditTopping3);
        btEditEditOrder = findViewById(R.id.btEditEditOrder);
        btEditDelOrder = findViewById(R.id.btEditDelOrder);
        btEditNewOrder = findViewById(R.id.btEditNewOrder);
        togBtEditEnPt = findViewById(R.id.togBtEditEnPt);

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
            togBtEditEnPt.setChecked(true);
        } else {
            setLang(langPort);
            togBtEditEnPt.setChecked(false);
        }

        // set Toggle Button
        togBtEditEnPt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (togBtEditEnPt.isChecked()) { // controls in english and tg portuguese
                    setLang(langEng);
                    togBtEditEnPt.setChecked(true);
                } else {
                    setLang(langPort);
                    togBtEditEnPt.setChecked(false);
                }
            }
        });

        // Set click listener classes
//        btEditEditOrder.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(Order.this, History.class);
//                intent.putExtra("language", language);
//                startActivity(intent);
//            }
//        });

//        btEditDelOrder.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(Order.this, History.class);
//                intent.putExtra("language", language);
//                startActivity(intent);
//            }
//        });

        btEditNewOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Edit_Order.this, Order.class);
                intent.putExtra("language", language);
                startActivity(intent);
            }
        });
    } // end of onCreate

    private void setLang(String[] langArray) {
        tvEditWelcome.setText(langArray[0]);
        tvEditCustInfo.setText(langArray[2]);
        tvEditCustName.setText(langArray[3]);
        tvEditCustPhone.setText(langArray[4]);
        tvEditCustAddress.setText(langArray[5]);
        tvEditChoose.setText(langArray[7]);
        tvEditSize.setText(langArray[8]);
        tvEditTopping1.setText(langArray[9]);
        tvEditTopping2.setText(langArray[10]);
        tvEditTopping3.setText(langArray[11]);
        btEditEditOrder.setText(langArray[17]);
        btEditDelOrder.setText(langArray[16]);
        btEditNewOrder.setText(langArray[15]);

        if(togBtEditEnPt.isChecked()) {
            // Size spinner
            adapterSize = ArrayAdapter.createFromResource(this,
                    R.array.sizeEng, android.R.layout.simple_spinner_item);

            // Topping1 Spinner
            adapterTop1 = ArrayAdapter.createFromResource(this,
                    R.array.toppingEng, android.R.layout.simple_spinner_item);

            // Topping2 Spinner
            adapterTop2 = ArrayAdapter.createFromResource(this,
                    R.array.toppingEng, android.R.layout.simple_spinner_item);

            // Topping3 Spinner
            adapterTop3 = ArrayAdapter.createFromResource(this,
                    R.array.toppingEng, android.R.layout.simple_spinner_item);

        } else{
            // Size spinner
            adapterSize = ArrayAdapter.createFromResource(this,
                    R.array.sizePt, android.R.layout.simple_spinner_item);

            // Topping1 Spinner
            adapterTop1 = ArrayAdapter.createFromResource(this,
                    R.array.toppingPt, android.R.layout.simple_spinner_item);

            // Topping2 Spinner
            adapterTop2 = ArrayAdapter.createFromResource(this,
                    R.array.toppingPt, android.R.layout.simple_spinner_item);

            // Topping3 Spinner
            adapterTop3 = ArrayAdapter.createFromResource(this,
                    R.array.toppingPt, android.R.layout.simple_spinner_item);
        }

        // set Adapters
        adapterSize.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterTop1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterTop2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterTop3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapters to the spinners
        spinEditSize.setAdapter(adapterSize);
        spinEditTopping1.setAdapter(adapterTop1);
        spinEditTopping2.setAdapter(adapterTop2);
        spinEditTopping3.setAdapter(adapterTop3);

        // Save the selected language in shared preferences
        SharedPreferences prefs = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("language", language);
        editor.commit();
    } // end of setLang

} // end of Edit_Order class