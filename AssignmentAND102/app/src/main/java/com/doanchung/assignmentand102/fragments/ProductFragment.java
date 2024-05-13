package com.doanchung.assignmentand102.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.doanchung.assignmentand102.R;

public class ProductFragment extends Fragment {
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


        return view;
    }
}
