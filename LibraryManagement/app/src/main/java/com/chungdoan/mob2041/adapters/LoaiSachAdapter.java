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
import com.chungdoan.mob2041.models.LoaiSach;

import java.util.List;

/**
 * 1. tạo class 'ViewHolder extends RecyclerView.ViewHolder' trước
 * 2. tạo constructor bên trong class 'ViewHolder'
 * 3. Sau 2 bước trên mới bắt đầu cho Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>
 * 4. Truyền vào trong thằng Adapter 1 ViewHolder (AdapterName(-LoaiSachAdapter).ViewHolder) vì thằng ViewHolder này nằm trong class Adapter
 * 5. Override 3 phương thức: onCreateViewHolder, onBindViewHolder, getItemCount
 * 6. Tạo constructor cho Adapter gồm 2 loại: Danh sách cần hiển thị + Context
 * 7. Solve onCreateViewHolder
 * 8. Solve ViewHolder -> look at item_category.xml -> see how many TextViews or something must be changed -> solve them
 *      - Mapping UI view
 * 9. Solve onBindViewHolder
 *      - Lấy ra các thành phần tại vị trí position trong list/ArrayList
 *      - Gán dữ liệu vào view
 *      - (Nếu có) Các thao tác khác, vd: show dialog, ...
 * 10. Solve getItemCount:
 *      - trả về số lượng item trong list/ArrayList
 *      - return (Tên Mảng).size();
 *11. Xong hết thì về lại Activity/Fragment sử dụng Adapter.
 *
 *
 * -----------------------------------------------------------------------------------------------------------------------
 * Nhiệm vụ của:
 * 1. onCreateViewHolder: Quản lý giao diện của từng item hiển thị lên recyclerView
 * 2. viewHolder: Quản lý các thành phần trong item hiển thị lên recyclerView -> mapping các thành phần trong item
 * 3. onBindViewHolder: Gán dữ liệu vào các thành phần trong item
 *
 */
public class LoaiSachAdapter extends RecyclerView.Adapter<LoaiSachAdapter.ViewHolder> {

    private List<LoaiSach> loaiSachList;
    private Context myContext;
    public LoaiSachAdapter(List<LoaiSach> loaiSachList, Context myContext) {
        this.loaiSachList = loaiSachList;
        this.myContext = myContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) myContext ).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_category, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //1. lấy ra data tại vị trí position
        LoaiSach loaiSach = loaiSachList.get(position);

        //2. gán dữ liệu vào view
        holder.textViewCategoryTitle.setText(loaiSach.getTenloai());
    }

    @Override
    public int getItemCount() {
        return loaiSachList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        //mapping
        TextView textViewCategoryTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //mapping
            textViewCategoryTitle = itemView.findViewById(R.id.textViewCategoryTitle);
        }
    }
}
