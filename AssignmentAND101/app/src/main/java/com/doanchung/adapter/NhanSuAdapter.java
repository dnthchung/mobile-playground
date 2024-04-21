package com.doanchung.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.doanchung.assignmentand101.R;
import com.doanchung.model.NhanSu;
import java.util.ArrayList;

public class NhanSuAdapter extends BaseAdapter{

    private Context context;
    private ArrayList<NhanSu> listNhanSu;

    public NhanSuAdapter(Context context, ArrayList<NhanSu> listNhanSu) {
        this.context = context;
        this.listNhanSu = listNhanSu;
    }

    @Override
    public int getCount() {
        return listNhanSu.size();
    }

    @Override
    public Object getItem(int position) {
        return listNhanSu.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //1. Call layout inflater & view
        LayoutInflater myInflater = ((Activity)context).getLayoutInflater();
        View myView = myInflater.inflate(R.layout.item_nhan_su, parent, false);
        //2. Anh xa widget
        TextView tvNhanSuId = myView.findViewById(R.id.tvId);
        TextView tvNhanSuName = myView.findViewById(R.id.tvName);
        TextView tvPhongBanName = myView.findViewById(R.id.tvPhongBanName);
        //3. Set data
        NhanSu ns = listNhanSu.get(position);
        tvNhanSuId.setText(ns.getMaNhanVien());
        tvNhanSuName.setText(ns.getTenNhanVien());
        tvPhongBanName.setText(ns.getTenPhongBan());

        return myView;
    }
}
