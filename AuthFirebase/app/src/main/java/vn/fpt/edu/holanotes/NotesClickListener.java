package vn.fpt.edu.holanotes;

import androidx.cardview.widget.CardView;

import vn.fpt.edu.holanotes.Models.Notes;

public interface NotesClickListener {
    void onClick(Notes notes);
    void onLongClick(Notes notes, CardView cardView);
    //43:49
}
