package vn.edu.fpt.holaquizz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private TextView welcomeTextView;
    private Button logoutButton;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // Initialize UI components
        welcomeTextView = findViewById(R.id.welcomeTextView);
        logoutButton = findViewById(R.id.logoutButton);

        // Display the username from SharedPreferences
        String username = getUsernameFromSharedPreferences();
        welcomeTextView.setText("Welcome, " + username + "!");

        // Set the logout button click listener
        logoutButton.setOnClickListener(view -> logoutUser());

        // Enable Edge-to-Edge support
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(android.R.id.content), (v, insets) -> {
            Insets systemInsets = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemInsets.left, systemInsets.top, systemInsets.right, systemInsets.bottom);
            return WindowInsetsCompat.CONSUMED;
        });
    }

    private String getUsernameFromSharedPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences("HolaQuizzPrefs", MODE_PRIVATE);
        return sharedPreferences.getString("userName", "Guest");
    }

    private void logoutUser() {
        // Sign out from Firebase
        mAuth.signOut();

        // Clear SharedPreferences
        clearSharedPreferences();

        // Navigate back to LoginActivity
        startActivity(new Intent(MainActivity.this, LoginActivity.class));
        finish(); // Close MainActivity
    }

    private void clearSharedPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences("HolaQuizzPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}
