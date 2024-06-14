package com.doanchung.assignmentand102.fragments;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.doanchung.assignmentand102.R;
import com.doanchung.assignmentand102.adapters.ProductItemAdapter;
import com.doanchung.assignmentand102.dao.ProductDAO;
import com.doanchung.assignmentand102.models.Product;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ProductFragment extends Fragment {

    //recyclerView
    private RecyclerView recyclerViewProduct;
    private FloatingActionButton fabAddProduct;
    private ProductDAO productDAO;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        /**
         * (@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceStat) lần lượt l :
         * 1. LayoutInflater: Dùng để inflate layout từ file xml
         * 2. ViewGroup: Dùng để chứa layout
         * 3. Bundle: Dùng để truyền dữ liệu giữa các Activity
         *  -----------------------------------------------------
         * View view = inflater.inflate(R.layout.fragment_product, container, false); -> inflate layout từ file fragment_product.xml
         */
        View view = inflater.inflate(R.layout.fragment_product, container, false);

        //code
        //ánh xạ
        recyclerViewProduct = view.findViewById(R.id.recyclerProduct);
        fabAddProduct = view.findViewById(R.id.fabAddProduct);

        //giao diện - layout của từng item

        //data
        productDAO = new ProductDAO(getContext());
        ArrayList<Product> listProduct = productDAO.getAllProduct();

        //adapter
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerViewProduct.setLayoutManager(linearLayoutManager);
        ProductItemAdapter productItemAdapter = new ProductItemAdapter(getContext(), listProduct);
        recyclerViewProduct.setAdapter(productItemAdapter);

        //handle button add new product
        fabAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //chuyển sang màn hình thêm sản phẩm
                showDialogAdd();
            }
        });

        return view;
    }

    private void loadData() {
        ArrayList<Product> listProduct = productDAO.getAllProduct();
        ProductItemAdapter productItemAdapter = new ProductItemAdapter(getContext(), listProduct);
        recyclerViewProduct.setAdapter(productItemAdapter);
    }

    private void showDialogAdd(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_product_add, null);
        builder.setView(view);

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Button btnCancel = view.findViewById(R.id.btnCancel);
        Button btnAdd = view.findViewById(R.id.btnAdd);
        EditText edtProductName = view.findViewById(R.id.edtProductName);
        EditText edtProductPrice = view.findViewById(R.id.edtProductPrice);
        EditText edtProductNumber = view.findViewById(R.id.edtProductNumber);

        alertDialog.setCancelable(false);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String productName = edtProductName.getText().toString();
                String productPrice = edtProductPrice.getText().toString();
                String productNumber = edtProductNumber.getText().toString();

                if(productName.isEmpty() || productPrice.isEmpty() || productNumber.isEmpty()){
                    Toast.makeText(getContext(), "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }else {
                    //parse string to int here vì nếu mà parse ở dòng 120 thì khi có lỗi input null
                    //thì sẽ không thể catch được, mà hệ thống sẽ bị crash do input null -> ko parse được
                    int productPrice1 = Integer.parseInt(edtProductPrice.getText().toString());
                    int productNumber1 = Integer.parseInt(edtProductNumber.getText().toString());

                    //Need new constructor in Product.java cause id is auto increment
                    Product product = new Product(productName, productPrice1, productNumber1);
                    boolean resultCheck = productDAO.insertProduct(product);
                    if(resultCheck){
                        //thêm thành công
                        alertDialog.dismiss();
                        Toast.makeText(getContext(), "Thêm sản phẩm thành công", Toast.LENGTH_SHORT).show();
                        //cập nhật lại dữ liệu
                        loadData();
                    }else {
                        //thêm thất bại
                        Toast.makeText(getContext(), "Thêm sản phẩm thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

}
