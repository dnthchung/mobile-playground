package vn.fpt.edu.holanotes;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

import vn.fpt.edu.holanotes.Adapters.NotesListAdapter;
import vn.fpt.edu.holanotes.Database.RoomDB;
import vn.fpt.edu.holanotes.Models.Notes;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    RecyclerView recyclerView;
    NotesListAdapter notesListAdapter;
    List<Notes> notes = new ArrayList<>();
    RoomDB database;
    FloatingActionButton fab_add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        // ============================== Initialize Firebase Auth ==============================
//        mAuth = FirebaseAuth.getInstance();
//
//        // Mapping log out button
//        Button btnLogout = findViewById(R.id.btn_logout);
//
//        // Log out the user when the button is clicked
//        btnLogout.setOnClickListener(v -> {
//            mAuth.signOut();
//            Toast.makeText(MainActivity.this, "Logged out successfully", Toast.LENGTH_SHORT).show();
//            // Redirect to LoginActivity after log out
//            startActivity(new Intent(MainActivity.this, LoginActivity.class));
//            finish(); // Close MainActivity to prevent returning with back button
//        });
        // ==================================================================================

        recyclerView = findViewById(R.id.recycler_home);
        fab_add = findViewById(R.id.fab_add);

        database = RoomDB.getInstance(this);
        notes = database.mainDAO().getAll();

        updateRecycler(notes);


    }

    private void updateRecycler(List<Notes> notes) {
        recyclerView.setHasFixedSize(true);
        notesListAdapter = new NotesListAdapter(MainActivity.this,notes, noteClickListener);
        recyclerView.setAdapter(notesListAdapter);


    }

    private final NotesClickListener noteClickListener = new NotesClickListener() {
        @Override
        public void onClick(Notes notes) {
            Intent intent = new Intent(MainActivity.this, EditActivity.class);
            intent.putExtra("notes", notes);
            startActivity(intent);
        }

        @Override
        public void onLongClick(Notes notes, CardView cardView) {
            database.mainDAO().delete(notes);
            notesListAdapter.notifyDataSetChanged();
        }
    };
}
