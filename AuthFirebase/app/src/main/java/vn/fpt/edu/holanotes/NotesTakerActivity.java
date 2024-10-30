package vn.fpt.edu.holanotes;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

import vn.fpt.edu.holanotes.Models.Notes;

public class NotesTakerActivity extends AppCompatActivity {

    // UI components
    EditText editText_notes, editText_title;
    Button imageView_back, imageView_save;

    // Note object to store the data
    Notes notes;

    // Flag to determine if this is an old note
    boolean isOldNote = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_taker);

        // Initialize UI components
        imageView_save = findViewById(R.id.imageView_save);
        editText_title = findViewById(R.id.editText_title);
        editText_notes = findViewById(R.id.editText_notes);

        // Initialize a new Notes object
        notes = new Notes();

        // Change: Check if old note exists in the intent and retrieve it
        if (getIntent().hasExtra("old_notes")) { // Check if "old_notes" is passed
            notes = (Notes) getIntent().getSerializableExtra("old_notes");
            if (notes != null) {
                // Populate the title and notes if this is an old note
                editText_title.setText(notes.getTitle());
                editText_notes.setText(notes.getNotes());
                isOldNote = true; // Set the flag to indicate this is an existing note
            }
        }

        // Save button click listener to save the note
        imageView_save.setOnClickListener(v -> saveNote());
    }

    /**
     * Save the note with the current input from EditText fields.
     */
    private void saveNote() {
        // Retrieve the title and description entered by the user
        String title = editText_title.getText().toString().trim(); // Change: Use trim() to avoid leading/trailing spaces
        String description = editText_notes.getText().toString().trim();

        // Change: Prevent saving if both title and description are empty
        if (title.isEmpty() && description.isEmpty()) {
            Toast.makeText(this, "Cannot save empty notes!!!", Toast.LENGTH_SHORT).show();
            return; // Stop further execution if input is empty
        }

        // Get the current date and format it
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd/MM/yyyy HH:mm");
        Date date = new Date();

        // Change: Only create a new note if it's not an old one
        if (!isOldNote) {
            notes = new Notes(); // Create new note object if this is not an old note
        }

        // Set the data to the Notes object
        notes.setTitle(title);
        notes.setNotes(description);
        notes.setDate(sdf.format(date)); // Set the current date in the desired format

        // Prepare the intent to send the result back to the calling activity
        Intent intent = new Intent();
        intent.putExtra("notes", notes); // Send the updated/new note object
        setResult(RESULT_OK, intent); // Set the result as OK to indicate success
        finish(); // Close the activity and go back to the previous one
    }
}
