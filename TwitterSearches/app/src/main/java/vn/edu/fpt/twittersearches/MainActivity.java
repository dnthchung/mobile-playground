package vn.edu.fpt.twittersearches;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String SEARCHES = "searches";

    private EditText queryEditText;
    private EditText tagEditText;
    private FloatingActionButton saveFloatingActionButton;
    private SharedPreferences savedSearches;
    private List<String> tags;
    private SearchesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        queryEditText = ((TextInputLayout) findViewById(R.id.queryTextInputLayout)).getEditText();
        tagEditText = ((TextInputLayout) findViewById(R.id.tagTextInputLayout)).getEditText();


        savedSearches = getSharedPreferences(SEARCHES, MODE_PRIVATE);
        tags = new ArrayList<>(savedSearches.getAll().keySet());
        Collections.sort(tags, String.CASE_INSENSITIVE_ORDER);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new SearchesAdapter(tags, itemClickListener, itemLongClickListener);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new ItemDivider(this));

        saveFloatingActionButton = findViewById(R.id.fab);
        saveFloatingActionButton.setOnClickListener(saveButtonListener);

        queryEditText.addTextChangedListener(textWatcher);
        tagEditText.addTextChangedListener(textWatcher);
        updateSaveFAB();
    }

    private final TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            updateSaveFAB();
        }

        @Override
        public void afterTextChanged(Editable s) {}
    };

    private void updateSaveFAB() {
        if (queryEditText.getText().toString().isEmpty() ||
                tagEditText.getText().toString().isEmpty()) {
            saveFloatingActionButton.hide();
        } else {
            saveFloatingActionButton.show();
        }
    }

    private final View.OnClickListener saveButtonListener = view -> {
        String query = queryEditText.getText().toString();
        String tag = tagEditText.getText().toString();

        if (!query.isEmpty() && !tag.isEmpty()) {
            ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE))
                    .hideSoftInputFromWindow(view.getWindowToken(), 0);

            addTaggedSearch(tag, query);
            queryEditText.setText("");
            tagEditText.setText("");
            queryEditText.requestFocus();
        }
    };

    private void addTaggedSearch(String tag, String query) {
        SharedPreferences.Editor preferencesEditor = savedSearches.edit();
        preferencesEditor.putString(tag, query);
        preferencesEditor.apply();

        if (!tags.contains(tag)) {
            tags.add(tag);
            Collections.sort(tags, String.CASE_INSENSITIVE_ORDER);
            adapter.notifyDataSetChanged();
        }
    }

    private final View.OnClickListener itemClickListener = view -> {
        String tag = ((TextView) view).getText().toString();
        String urlString = getString(R.string.search_URL) +
                Uri.encode(savedSearches.getString(tag, ""), "UTF-8");
        Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(urlString));
        startActivity(webIntent);
    };

    private final View.OnLongClickListener itemLongClickListener = view -> {
        final String tag = ((TextView) view).getText().toString();
        new AlertDialog.Builder(MainActivity.this)
                .setTitle(getString(R.string.share_edit_delete_title, tag))
                .setItems(R.array.dialog_items, (dialog, which) -> {
                    switch (which) {
                        case 0:
                            shareSearch(tag);
                            break;
                        case 1:
                            tagEditText.setText(tag);
                            queryEditText.setText(savedSearches.getString(tag, ""));
                            break;
                        case 2:
                            deleteSearch(tag);
                            break;
                    }
                })
                .setNegativeButton(getString(R.string.cancel), null)
                .create().show();
        return true;
    };

    private void shareSearch(String tag) {
        String urlString = getString(R.string.search_URL) +
                Uri.encode(savedSearches.getString(tag, ""), "UTF-8");
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.share_subject));
        shareIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.share_message, urlString));
        shareIntent.setType("text/plain");
        startActivity(Intent.createChooser(shareIntent, getString(R.string.share_search)));
    }

    private void deleteSearch(final String tag) {
        new AlertDialog.Builder(this)
                .setMessage(getString(R.string.confirm_message, tag))
                .setPositiveButton(getString(R.string.delete), (dialog, id) -> {
                    tags.remove(tag);
                    savedSearches.edit().remove(tag).apply();
                    adapter.notifyDataSetChanged();
                })
                .setNegativeButton(getString(R.string.cancel), null)
                .create().show();
    }
}
