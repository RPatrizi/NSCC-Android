package com.example.cruddypizza;

// images source: https://www.flaticon.com/free-icons/pizza

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.*;
import android.content.*;
import android.widget.ToggleButton;
import android.view.View;
import android.content.Intent;
import android.os.Bundle;

public class Order extends AppCompatActivity {

    // create views
    TextView tvWelcome, tvCustInfo, tvCustName, tvCustPhone, tvCustAddress, tvChoose, tvSize, tvTopping1, tvTopping2, tvTopping3;
    EditText etCustName, etCustPhone, etCustAddress;
    Spinner spinSize, spinTopping1, spinTopping2, spinTopping3;
    Button btSubmit, btViewOrder;
    ToggleButton togBtEnPt;

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
        setContentView(R.layout.activity_order);

        // set up views
        tvWelcome = findViewById(R.id.tvWelcome);
        tvCustInfo = findViewById(R.id.tvCustInfo);
        tvCustName = findViewById(R.id.tvCustName);
        tvCustPhone = findViewById(R.id.tvCustPhone);
        tvCustAddress = findViewById(R.id.tvCustAddress);
        tvChoose = findViewById(R.id.tvChoose);
        tvSize = findViewById(R.id.tvSize);
        tvTopping1 = findViewById(R.id.tvTopping1);
        tvTopping2 = findViewById(R.id.tvTopping2);
        tvTopping3 = findViewById(R.id.tvTopping3);
        etCustName = findViewById(R.id.etCustName);
        etCustPhone = findViewById(R.id.etCustPhone);
        etCustAddress = findViewById(R.id.etCustAddress);
        spinSize = findViewById(R.id.spinSize);
        spinTopping1 = findViewById(R.id.spinTopping1);
        spinTopping2 = findViewById(R.id.spinTopping2);
        spinTopping3 = findViewById(R.id.spinTopping3);
        btSubmit = findViewById(R.id.btSubmit);
        btViewOrder = findViewById(R.id.btViewOrder);
        togBtEnPt = findViewById(R.id.togBtEnPt);

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
            togBtEnPt.setChecked(true);
        } else {
            setLang(langPort);
            togBtEnPt.setChecked(false);
        }

        // set Toggle Button
        togBtEnPt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (togBtEnPt.isChecked()) { // controls in english and tg portuguese
                    setLang(langEng);
                    togBtEnPt.setChecked(true);
                } else {
                    setLang(langPort);
                    togBtEnPt.setChecked(false);
                }
            }
        });

//        // Set click listener classes
//        btSubmit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(Order.this, Edit_Order.class);
//                intent.putExtra("language", language);
//                startActivity(intent);
//            }
//        });

        btViewOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Order.this, History.class);
                intent.putExtra("language", language);
                startActivity(intent);
            }
        });
    } // end of onCreate

    private void setLang(String[] langArray) {
        tvWelcome.setText(langArray[0]);
        tvCustInfo.setText(langArray[1]);
        tvCustName.setText(langArray[3]);
        tvCustPhone.setText(langArray[4]);
        tvCustAddress.setText(langArray[5]);
        tvChoose.setText(langArray[6]);
        tvSize.setText(langArray[8]);
        tvTopping1.setText(langArray[9]);
        tvTopping2.setText(langArray[10]);
        tvTopping3.setText(langArray[11]);
        btSubmit.setText(langArray[12]);
        btViewOrder.setText(langArray[13]);


        if(togBtEnPt.isChecked()) {
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
            spinSize.setAdapter(adapterSize);
            spinTopping1.setAdapter(adapterTop1);
            spinTopping2.setAdapter(adapterTop2);
            spinTopping3.setAdapter(adapterTop3);

            // Save the selected language in shared preferences
            SharedPreferences prefs = getPreferences(MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("language", language);
            editor.commit();
    } // end of setLang
} // end of Order class