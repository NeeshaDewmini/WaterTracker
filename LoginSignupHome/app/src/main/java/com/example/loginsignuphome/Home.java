package com.example.loginsignuphome;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Home extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ImageView menuButton;
    private LinearLayout home, addWater, history, reminder, tips, rewards, profile, logout;

    ProgressBar progressBar;
    TextView progresstext;
    int progress=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Initialize Drawer Layout and Menu Button
        drawerLayout = findViewById(R.id.main);
        menuButton = findViewById(R.id.menu);

        // Initialize Navigation Drawer Items
        home = findViewById(R.id.home);
        addWater = findViewById(R.id.addwater);
        history = findViewById(R.id.history);
        reminder = findViewById(R.id.reminder);
        tips = findViewById(R.id.tips);
        rewards = findViewById(R.id.rewards);
        profile = findViewById(R.id.profile);
        logout = findViewById(R.id.logout);


        menuButton.setOnClickListener(view -> drawerLayout.openDrawer(GravityCompat.START));

        // Set Click Listeners for Drawer Items
        home.setOnClickListener(view -> showToast("Home Selected"));
        addWater.setOnClickListener(view -> showToast("Add Water Intake Selected"));
        history.setOnClickListener(view -> showToast("History Selected"));
        reminder.setOnClickListener(view -> showToast("Reminder Selected"));
        tips.setOnClickListener(view -> showToast("Tips & Education Selected"));
        rewards.setOnClickListener(view -> showToast("Rewards Selected"));
        profile.setOnClickListener(view -> showToast("Profile Selected"));
        logout.setOnClickListener(view -> showToast("Logging Out..."));

        progressBar=findViewById(R.id.progressbar);
        progresstext=findViewById(R.id.progresstext);

        changeporgress();
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        drawerLayout.closeDrawer(GravityCompat.START);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void changeporgress() {


        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        Runnable task = () -> {
            if (progress >= 100) {
                progress = 0;
            }
            if (progress >= 0 && progress <= 100) {
                runOnUiThread(() -> {
                    progressBar.setProgress(progress);
                    progresstext.setText(progress + "%");
                });
                progress+=5;
            }
        };

        // Schedule the task to run every 1000 milliseconds (1 second)
        executor.scheduleAtFixedRate(task, 0, 1000, TimeUnit.MILLISECONDS);
    }
}
