package vn.fpt.edu.holanotes;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // Mapping log out button
        Button btnLogout = findViewById(R.id.btn_logout);

        // Log out the user when the button is clicked
        btnLogout.setOnClickListener(v -> {
            mAuth.signOut();
            Toast.makeText(MainActivity.this, "Logged out successfully", Toast.LENGTH_SHORT).show();
            // Redirect to LoginActivity after log out
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish(); // Close MainActivity to prevent returning with back button
        });
    }
}
