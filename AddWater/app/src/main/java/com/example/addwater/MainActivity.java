package com.example.addwater;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button cup1, cup2, cup3, cup4, cup5, cup6, custom;
    TextView currentSize;
    EditText customSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initializing buttons
        cup1 = findViewById(R.id.cup100ml);
        cup2 = findViewById(R.id.cup150ml);
        cup3 = findViewById(R.id.cup200ml);
        cup4 = findViewById(R.id.cup250ml);
        cup5 = findViewById(R.id.cup300ml);
        cup6 = findViewById(R.id.cup400ml);
        custom = findViewById(R.id.cupCustom);
        customSize = findViewById(R.id.customizeEditText);
        currentSize = findViewById(R.id.CupSizeText);

        // Setting click listeners
        cup1.setOnClickListener(this);
        cup2.setOnClickListener(this);
        cup3.setOnClickListener(this);
        cup4.setOnClickListener(this);
        cup5.setOnClickListener(this);
        cup6.setOnClickListener(this);
        custom.setOnClickListener(this);

        // Load saved cup size from SharedPreferences
        SharedPreferences sh = getSharedPreferences("cupsize", Context.MODE_PRIVATE);
        String temp = sh.getString("cupsize", "200"); // Default to 200ml
        currentSize.setText(temp);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.cup100ml) {
            updateCupSize(100);
        } else if (id == R.id.cup150ml) {
            updateCupSize(150);
        } else if (id == R.id.cup200ml) {
            updateCupSize(200);
        } else if (id == R.id.cup250ml) {
            updateCupSize(250);
        } else if (id == R.id.cup300ml) {
            updateCupSize(300);
        } else if (id == R.id.cup400ml) {
            updateCupSize(400);
        } else if (id == R.id.cupCustom) {
            handleCustomCupSize();
        }
    }

    private void updateCupSize(int size) {
        SharedPreferences sh = getSharedPreferences("cupsize", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sh.edit();
        currentSize.setText(String.valueOf(size));
        editor.putString("cupsize", String.valueOf(size));
        editor.apply();
        Toast.makeText(this, "Current cup size: " + size + "ml", Toast.LENGTH_SHORT).show();
    }

    private void handleCustomCupSize() {
        String sizeStr = customSize.getText().toString().trim();

        if (TextUtils.isEmpty(sizeStr)) {
            customSize.setError("Please enter a cup size!");
            return;
        }

        try {
            int size = Integer.parseInt(sizeStr);
            if (size <= 0) {
                customSize.setError("Enter a positive number!");
                Toast.makeText(this, "Please enter a valid positive cup size!", Toast.LENGTH_SHORT).show();
                return;
            }
            updateCupSize(size);
        } catch (NumberFormatException e) {
            customSize.setError("Invalid input!");
            Toast.makeText(this, "Please enter a valid number!", Toast.LENGTH_SHORT).show();
        }
    }
}

// A simple Cups class to store the cup size
class Cups {
    private int cupSize;

    public void setCupSize(int size) {
        this.cupSize = size;
    }

    public int getCupSize() {
        return cupSize;
    }
}
