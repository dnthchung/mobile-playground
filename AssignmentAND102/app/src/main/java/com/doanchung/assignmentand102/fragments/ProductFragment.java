package com.doanchung.assignmentand102.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

        return view;
    }
}
