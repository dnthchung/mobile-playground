package com.doanchung.assignmentand102.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.doanchung.assignmentand102.R;
import com.doanchung.assignmentand102.models.Product;

import java.util.ArrayList;

public class ProductItemAdapter extends RecyclerView.Adapter<ProductItemAdapter.ViewHolder> {

    /**
     * ==== step create adapter ====
     * step 1 : extends RecyclerView.Adapter<ProductItemAdapter.ViewHolder>
     * step 2 : tạo class ViewHolder extends RecyclerView.ViewHolder
     * step 3 : implement 3 phương thức của RecyclerView.Adapter
     * step 4 : tạo 2 biến : context và listProduct + constructor
     * step 5 : implement 3 phương thức của RecyclerView.Adapter
     * step 6 : khai báo biến để ánh xạ trong function ViewHolder
     * step 7 : onCreateViewHolder được xử dụng để tạo ra ViewHolder mới và được gọi khi RecyclerView cần một ViewHolder mới ( giống khi lmà việc với fragment)
     * step 8 : gán dữ liệu vào view trong function onBindViewHolder
     * step 9 : trả về số lượng item trong listProduct trong function getItemCount (trả về size() của listProduct)
     *
     *
     */
    private Context context;
    private ArrayList<Product> listProduct;


    public ProductItemAdapter(Context context, ArrayList<Product> listProduct) {
        this.context = context;
        this.listProduct = listProduct;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        //xử lý này giống của thằng fragment
        View view = inflater.inflate(R.layout.item_product, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductItemAdapter.ViewHolder holder, int position) {
        //lấy ra sản phẩm tại vị trí position
        Product product = listProduct.get(position);
        //gán dữ liệu vào view
        holder.tvName.setText(product.getProductName());
        holder.tvPrice.setText(product.getProductPrice() + "");
        holder.tvNumber.setText(product.getProductNumber() + "");
    }

    @Override
    public int getItemCount() {
        return listProduct.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        //khai báo để ánh xạ
        TextView tvName;
        TextView tvPrice;
        TextView tvNumber;
        TextView tvEdit, tvDelete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //ánh xạ
            tvName = itemView.findViewById(R.id.tvName);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvNumber = itemView.findViewById(R.id.tvNumber);
            tvEdit = itemView.findViewById(R.id.tvEdit);
            tvDelete = itemView.findViewById(R.id.tvDelete);

        }
    }
}
