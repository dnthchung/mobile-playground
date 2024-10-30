//package vn.fpt.edu.holanotes;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.widget.Button;
//import android.widget.Toast;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.cardview.widget.CardView;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//import androidx.recyclerview.widget.StaggeredGridLayoutManager;
//
//import com.google.android.material.floatingactionbutton.FloatingActionButton;
//import com.google.firebase.auth.FirebaseAuth;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import vn.fpt.edu.holanotes.Adapters.NotesListAdapter;
//import vn.fpt.edu.holanotes.Database.RoomDB;
//import vn.fpt.edu.holanotes.Models.Notes;
//
//public class MainActivity extends AppCompatActivity {
//
//    private FirebaseAuth mAuth;
//
//    RecyclerView recyclerView;
//    NotesListAdapter notesListAdapter;
//    List<Notes> notes = new ArrayList<>();
//    RoomDB database;
//    FloatingActionButton fab_add;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
////        // ============================== Initialize Firebase Auth ==============================
////        mAuth = FirebaseAuth.getInstance();
////
////        // Mapping log out button
////        Button btnLogout = findViewById(R.id.btn_logout);
////
////        // Log out the user when the button is clicked
////        btnLogout.setOnClickListener(v -> {
////            mAuth.signOut();
////            Toast.makeText(MainActivity.this, "Logged out successfully", Toast.LENGTH_SHORT).show();
////            // Redirect to LoginActivity after log out
////            startActivity(new Intent(MainActivity.this, LoginActivity.class));
////            finish(); // Close MainActivity to prevent returning with back button
////        });
//        // ==================================================================================
//
//        recyclerView = findViewById(R.id.recycler_home);
//        fab_add = findViewById(R.id.fab_add);
//
//        database = RoomDB.getInstance(this);
//        notes = database.mainDAO().getAll();
//
//        updateRecycler(notes);
//
//        fab_add.setOnClickListener(v -> {
//                Intent intent = new Intent(MainActivity.this, NotesTakerActivity.class);
//                startActivityForResult(intent, 101);
//        });
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == 101) {
//            if (resultCode == RESULT_OK) {
//                Notes new_notes = (Notes) data.getSerializableExtra("notes");
//                database.mainDAO().insert(new_notes);
//                notes.clear();
//                notes.addAll(database.mainDAO().getAll());
//                notesListAdapter.notifyDataSetChanged();
//            }
//        } else if (requestCode == 102) {
//            if (resultCode == RESULT_OK) {
//                Notes new_notes = (Notes) data.getSerializableExtra("notes");
//                database.mainDAO().update(new_notes.getID(), new_notes.getTitle(), new_notes.getNotes());
//                notes.addAll(database.mainDAO().getAll());
//                notesListAdapter.notifyDataSetChanged();
//            }
//
//        }
//    }
//
//    private void updateRecycler(List<Notes> notes) {
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL));
//        notesListAdapter = new NotesListAdapter(MainActivity.this,notes, noteClickListener);
//        recyclerView.setAdapter(notesListAdapter);
//
//
//    }
//
//    private final NotesClickListener noteClickListener = new NotesClickListener() {
//        @Override
//        public void onClick(Notes notes) {
//            Intent intent = new Intent(MainActivity.this, NotesTakerActivity.class);
//            intent.putExtra("old_note", notes);
//            startActivityForResult(intent, 102);
//        }
//
//        @Override
//        public void onLongClick(Notes notes, CardView cardView) {
////            database.mainDAO().delete(notes);
////            notesListAdapter.notifyDataSetChanged();
//        }
//    };
//}


package vn.fpt.edu.holanotes;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

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

        // Initialize Firebase Auth (uncomment if needed)
        // mAuth = FirebaseAuth.getInstance();
        // Button btnLogout = findViewById(R.id.btn_logout);
        // btnLogout.setOnClickListener(v -> logoutUser());

        // Initialize UI components
        recyclerView = findViewById(R.id.recycler_home);
        fab_add = findViewById(R.id.fab_add);

        // Initialize database and get all notes
        database = RoomDB.getInstance(this);
        notes = database.mainDAO().getAll();  // Retrieve notes from database

        updateRecycler(notes);  // Set up RecyclerView with notes

        // Handle FloatingActionButton click to add a new note
        fab_add.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, NotesTakerActivity.class);
            startActivityForResult(intent, 101);  // Request code 101 for new note
        });
    }

    /**
     * Handles results from NotesTakerActivity.
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data != null) {  // Check if data is not null to avoid crashes
            Notes new_notes = (Notes) data.getSerializableExtra("notes");

            if (requestCode == 101 && resultCode == RESULT_OK) {
                // Adding a new note
                database.mainDAO().insert(new_notes);
                refreshNotesList();  // Refresh the notes list

            } else if (requestCode == 102 && resultCode == RESULT_OK) {
                // Updating an existing note
                database.mainDAO().update(new_notes.getID(), new_notes.getTitle(), new_notes.getNotes());
                refreshNotesList();  // Refresh the notes list
            }
        }
    }

    /**
     * Sets up the RecyclerView with the given list of notes.
     */
    private void updateRecycler(List<Notes> notes) {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL));
        notesListAdapter = new NotesListAdapter(MainActivity.this, notes, noteClickListener);
        recyclerView.setAdapter(notesListAdapter);
    }

    /**
     * Refreshes the notes list and updates the adapter.
     */
    private void refreshNotesList() {
        notes.clear();  // Clear current list
        notes.addAll(database.mainDAO().getAll());  // Reload all notes from database
        notesListAdapter.notifyDataSetChanged();  // Notify adapter of data changes
    }

    /**
     * Listener for note clicks and long clicks.
     */
    private final NotesClickListener noteClickListener = new NotesClickListener() {
        @Override
        public void onClick(Notes note) {
            // Open NotesTakerActivity for editing the selected note
            Intent intent = new Intent(MainActivity.this, NotesTakerActivity.class);
            intent.putExtra("old_notes", note);  // Use correct key "old_notes"
            startActivityForResult(intent, 102);  // Request code 102 for note update
        }

        @Override
        public void onLongClick(Notes note, CardView cardView) {
            // Optional: Handle long click for deleting a note (if needed)
            database.mainDAO().delete(note);  // Delete the note from database
            refreshNotesList();  // Refresh the notes list
            Toast.makeText(MainActivity.this, "Note deleted", Toast.LENGTH_SHORT).show();
        }
    };

    /**
     * Optional: Logs out the user and redirects to LoginActivity.
     */
    private void logoutUser() {
        mAuth.signOut();
        Toast.makeText(MainActivity.this, "Logged out successfully", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(MainActivity.this, LoginActivity.class));
        finish();  // Close MainActivity to prevent back navigation
    }
}
