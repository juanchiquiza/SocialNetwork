package com.socialnetwork.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.socialnetwork.R;
import com.socialnetwork.Utils.PreferencesManager;

public class ProfileActivity extends AppCompatActivity {

    private TextView nameText;
    private TextView emailText;
    private PreferencesManager preferencesManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        preferencesManager = new PreferencesManager(getApplicationContext());

        nameText = findViewById(R.id.userEmail);
        emailText = findViewById(R.id.userName);
        nameText.setText(preferencesManager.getUserName());
        emailText.setText(preferencesManager.getUserEmail());
    }
}