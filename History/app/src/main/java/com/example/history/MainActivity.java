package com.example.history;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private Spinner dropdownMenu;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> dropdownItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize Dropdown Menu (Spinner)
        dropdownMenu = findViewById(R.id.dropdownMenu);
        dropdownItems = new ArrayList<>();

        // Add default item
        dropdownItems.add("View Logs");

        // Automatically add the current month with the year
        addCurrentMonthEntry();

        // Set up the adapter
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, dropdownItems);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdownMenu.setAdapter(adapter);

        dropdownMenu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedOption = parent.getItemAtPosition(position).toString();

                // If "View Logs" is selected, open the dialog with the table
                if (selectedOption.equals("View Logs")) {
                    showTableDialog();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    // Function to add the current month entry dynamically
    private void addCurrentMonthEntry() {
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH); // 0 = Jan, 1 = Feb, etc.
        int year = calendar.get(Calendar.YEAR);

        String[] monthNames = {"January", "February", "March", "April", "May", "June", "July",
                "August", "September", "October", "November", "December"};

        // Format: "2025 January"
        String newMonthEntry = year + " " + monthNames[month];

        // Avoid duplicate entries
        if (!dropdownItems.contains(newMonthEntry)) {
            dropdownItems.add(newMonthEntry);
        }
    }

    // Function to show the log table
    private void showTableDialog() {
        // Your existing function
    }
}
