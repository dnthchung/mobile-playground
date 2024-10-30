package vn.fpt.edu.holanotes;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.SimpleDateFormat;
import java.util.Date;

import vn.fpt.edu.holanotes.Models.Notes;

public class NotesTakerActivity extends AppCompatActivity {


    EditText editText_notes, editText_title;
    ImageView imageView_back, imageView_save;
    Notes notes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_taker);

        imageView_save = findViewById(R.id.imageView_save);
        editText_title = findViewById(R.id.editText_title);
        editText_notes = findViewById(R.id.editText_notes);

        imageView_save.setOnClickListener(v -> {
            String title = editText_title.getText().toString();
            String description = editText_notes.getText().toString();

            if (title.isEmpty() && description.isEmpty()) {
                Toast.makeText(this, "Cannot save empty notes!!!", Toast.LENGTH_SHORT).show();
                return;
            }
            @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd/MM/yyyy HH:mm");
            Date date = new Date();

            notes = new Notes( );
            notes.setTitle(title);
            notes.setNotes(description);
            notes.setDate(sdf.format(date));

            Intent intent = new Intent();
            intent.putExtra("notes", notes);
            setResult(RESULT_OK, intent);
            finish();
        });

    }
}