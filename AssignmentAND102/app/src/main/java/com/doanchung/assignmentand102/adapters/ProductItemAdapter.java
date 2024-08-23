package com.doanchung.assignmentand102.adapters;

import androidx.appcompat.app.AlertDialog;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.doanchung.assignmentand102.R;
import com.doanchung.assignmentand102.dao.ProductDAO;
import com.doanchung.assignmentand102.models.Product;

import java.text.DecimalFormat;
import java.text.NumberFormat;
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
     */
    private Context context;
    private ArrayList<Product> listProduct;
    private ProductDAO productDAO;


//    public ProductItemAdapter(Context context, ArrayList<Product> listProduct) {
//        this.context = context;
//        this.listProduct = listProduct;
//    }

    //new constructor for update
    public ProductItemAdapter(Context context, ArrayList<Product> listProduct, ProductDAO productDAO) {
        this.context = context;
        this.listProduct = listProduct;
        this.productDAO = productDAO;
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

        //formattedNumber is equal to 1,000,000
        NumberFormat formatter = new DecimalFormat("#,###");
//        double myNumber = product.getProductPrice();
        double myNumber = listProduct.get(position).getProductPrice();
        String formattedNumber = formatter.format(myNumber);

        //gán dữ liệu vào view
        holder.tvName.setText(product.getProductName());
        holder.tvPrice.setText(formattedNumber + " VND");
        holder.tvNumber.setText("SL: " + product.getProductNumber());

        //edit
        holder.tvEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //chuyển sang màn hình sửa sản phẩm
                showDialogEdit(listProduct.get(holder.getAdapterPosition()));
            }
        });
    }

    public void showDialogEdit(Product product) {
        //trong adapter không thể sử dụng this vì không có sẵn context
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        //ép kiểu giống xử lý trên recycler view(line 49)
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_product_edit, null);
        builder.setView(view);

        AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(false);
        alertDialog.show();
        alertDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        //ánh xạ
        EditText edtProductName = view.findViewById(R.id.edtProductName);
        EditText edtProductPrice = view.findViewById(R.id.edtProductPrice);
        EditText edtProductNumber = view.findViewById(R.id.edtProductNumber);
        Button btnCancel = view.findViewById(R.id.btnCancel);
        Button btnEdit = view.findViewById(R.id.btnEdit);

        //gán dữ liệu vào view
        edtProductName.setText(product.getProductName());
        edtProductPrice.setText(String.valueOf(product.getProductPrice()));
        edtProductNumber.setText(String.valueOf(product.getProductNumber()));

        //cancel
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        //edit
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = product.getId();
                String productName = edtProductName.getText().toString();
                String productPrice = edtProductPrice.getText().toString();
                String productNumber = edtProductNumber.getText().toString();

                if (productName.isEmpty() || productPrice.isEmpty() || productNumber.isEmpty()) {
                    //nếu có 1 trường nào đó trống thì thông báo
                    //context là activity hiện tại
                    Toast.makeText(context, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();

                } else {
                    Product productEdit = new Product(id, productName, Integer.parseInt(productPrice), Integer.parseInt(productNumber));
                    boolean check = productDAO.editProduct(productEdit);
                    if (check) {
                        Toast.makeText(context, "Sửa sản phẩm thành công", Toast.LENGTH_SHORT).show();
                        //cập nhật lại listProduct
                        listProduct.clear();
                        listProduct= productDAO.getAllProduct();
                        notifyDataSetChanged();
                        alertDialog.dismiss();
                    }else {
                        Toast.makeText(context, "Sửa sản phẩm thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

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
