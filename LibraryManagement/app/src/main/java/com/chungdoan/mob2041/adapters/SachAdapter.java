package com.chungdoan.mob2041.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chungdoan.mob2041.R;
import com.chungdoan.mob2041.models.Sach;

import java.util.ArrayList;

public class SachAdapter extends RecyclerView.Adapter<SachAdapter.ViewHoler> {
    private ArrayList<Sach> sachList;
    private Context myContext;

    //constructor
    public SachAdapter(ArrayList<Sach> sachList, Context myContext) {
        this.sachList = sachList;
        this.myContext = myContext;
    }

    @NonNull
    @Override
    public ViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) myContext).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_book, parent, false);
        return new ViewHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoler holder, int position) {
        //get data from position
        Sach sachNe = sachList.get(position);

        //set data to view
        holder.textMaLoai.setText(String.valueOf(sachNe.getMaloai()));
        holder.textViewPrice.setText(String.valueOf(sachNe.getGiaban()));
        holder.textViewTitle.setText(sachNe.getTensach());

        // Lấy text hiện tại từ textViewAuthor và nối thêm dữ liệu vào sau
        String currentText = holder.textViewAuthor.getText().toString();
        holder.textViewAuthor.setText(currentText + " " + sachNe.getTacgia());

    }

    @Override
    public int getItemCount() {
        return sachList.size();
    }

    public class ViewHoler extends RecyclerView.ViewHolder{

        //khai báo các thành phần trong item
        TextView textMaLoai, textViewPrice, textViewTitle, textViewAuthor;

        public ViewHoler(@NonNull View itemView) {
            super(itemView);
            //Mapping
            textMaLoai = itemView.findViewById(R.id.textMaLoai);
            textViewPrice = itemView.findViewById(R.id.textViewPrice);
            textViewTitle = itemView.findViewById(R.id.textViewBookTitle);
            textViewAuthor = itemView.findViewById(R.id.textViewAuthor);
        }
    }
}
