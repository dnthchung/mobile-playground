//package vn.fpt.edu.holanotes.Adapters;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.cardview.widget.CardView;
//import androidx.recyclerview.widget.RecyclerView;
//
//import java.util.List;
//
//import vn.fpt.edu.holanotes.Models.Notes;
//import vn.fpt.edu.holanotes.NotesClickListener;
//import vn.fpt.edu.holanotes.R;
//
//public class NotesListAdapter extends RecyclerView.Adapter<NotesViewHolder> {
//
//    Context context;
//    List<Notes> notesList;
//    NotesClickListener listener;
//
//    public NotesListAdapter(Context context, List<Notes> notesList, NotesClickListener listener) {
//        this.context = context;
//        this.notesList = notesList;
//        this.listener = listener;
//    }
//
//    @NonNull
//    @Override
//    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        return new NotesViewHolder(
//                LayoutInflater.from(context).inflate(R.layout.notes_list, parent, false)
//        );
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull NotesViewHolder holder, int position) {
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return 0;
//    }
//}
//
//class NotesViewHolder extends RecyclerView.ViewHolder {
//
//    CardView notes_container;
//    TextView textView_title, textView_notes, textView_date;
//    ImageView imageView_pin;
//
//    public NotesViewHolder(@NonNull View itemView) {
//        super(itemView);
//        notes_container = itemView.findViewById(R.id.notes_container);
//        textView_title = itemView.findViewById(R.id.textView_title);
//        textView_notes = itemView.findViewById(R.id.textView_notes);
//        textView_date = itemView.findViewById(R.id.textView_date);
//        imageView_pin = itemView.findViewById(R.id.imageView_pin);
//    }
//
//}


package vn.fpt.edu.holanotes.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import vn.fpt.edu.holanotes.Models.Notes;
import vn.fpt.edu.holanotes.NotesClickListener;
import vn.fpt.edu.holanotes.R;

public class NotesListAdapter extends RecyclerView.Adapter<NotesListAdapter.NotesViewHolder> {

    Context context;
    List<Notes> notesList;
    NotesClickListener listener;

    public NotesListAdapter(Context context, List<Notes> notesList, NotesClickListener listener) {
        this.context = context;
        this.notesList = notesList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NotesViewHolder(
                LayoutInflater.from(context).inflate(R.layout.notes_list, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder holder, int position) {
        holder.textView_title.setText(notesList.get(position).getTitle());
        holder.textView_title.setSelected(true);

        holder.textView_notes.setText(notesList.get(position).getNotes());

        holder.textView_date.setText(notesList.get(position).getDate());
        holder.textView_date.setSelected(true);

        if(notesList.get(position).isPinned()){
            holder.imageView_pin.setVisibility(View.VISIBLE);
        } else {
            holder.imageView_pin.setVisibility(View.GONE);
        }

        int color_code = getRandomColor();
        holder.notes_container.setCardBackgroundColor(holder.itemView.getResources().getColor(color_code,null));

        holder.notes_container.setOnClickListener(v -> listener.onClick(notesList.get(holder.getAdapterPosition())));
        holder.notes_container.setOnLongClickListener(v -> {
            listener.onLongClick(notesList.get(holder.getAdapterPosition()), holder.notes_container);
            return true;
        });


    }

    private int getRandomColor() {
        List<Integer> colorCode = new ArrayList<>();
        colorCode.add(R.color.color1);
        colorCode.add(R.color.color2);
        colorCode.add(R.color.color3);
        colorCode.add(R.color.color4);
        colorCode.add(R.color.color5);
        colorCode.add(R.color.color6);
        colorCode.add(R.color.color7);
        colorCode.add(R.color.color8);
        colorCode.add(R.color.color9);
        colorCode.add(R.color.color10);

        Random random = new Random();
        return colorCode.get(random.nextInt(colorCode.size()));
    }


    @Override
    public int getItemCount() {
        return notesList.size(); // Return the correct size of the notes list
    }

    // Make the ViewHolder class static to avoid memory leaks
    public static class NotesViewHolder extends RecyclerView.ViewHolder {
        CardView notes_container;
        TextView textView_title, textView_notes, textView_date;
        ImageView imageView_pin;

        public NotesViewHolder(@NonNull View itemView) {
            super(itemView);
            notes_container = itemView.findViewById(R.id.notes_container);
            textView_title = itemView.findViewById(R.id.textView_title);
            textView_notes = itemView.findViewById(R.id.textView_notes);
            textView_date = itemView.findViewById(R.id.textView_date);
            imageView_pin = itemView.findViewById(R.id.imageView_pin);
        }
    }
}
