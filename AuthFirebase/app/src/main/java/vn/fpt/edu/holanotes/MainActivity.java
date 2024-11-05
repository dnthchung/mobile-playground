package vn.fpt.edu.holanotes;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.Collections;
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
         mAuth = FirebaseAuth.getInstance();
         Button btnLogout = findViewById(R.id.btn_logout);
         btnLogout.setOnClickListener(v -> logoutUser());

        // Initialize Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Initialize UI components
        recyclerView = findViewById(R.id.recycler_home);
        fab_add = findViewById(R.id.fab_add);

        // Initialize database and get all notes
        database = RoomDB.getInstance(this);
        notes = database.mainDAO().getAll();

        updateRecycler(notes);

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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_filter, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.filter_newest) {
            // Sort notes by newest date
            Collections.sort(notes, (n1, n2) -> n2.getDate().compareTo(n1.getDate()));
            notesListAdapter.notifyDataSetChanged();
            Toast.makeText(this, "Sorted by Newest", Toast.LENGTH_SHORT).show();
            return true;
        } else if (itemId == R.id.filter_oldest) {
            // Sort notes by oldest date
            Collections.sort(notes, (n1, n2) -> n1.getDate().compareTo(n2.getDate()));
            notesListAdapter.notifyDataSetChanged();
            Toast.makeText(this, "Sorted by Oldest", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }




}
