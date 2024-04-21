package com.doanchung.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.doanchung.assignmentand101.R;
import com.doanchung.model.PhongBan;
import java.util.ArrayList;

public class PhongBanAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<PhongBan> listPB;
    public PhongBanAdapter(Context context, ArrayList<PhongBan> listPB) {
        this.context = context;
        this.listPB = listPB;
    }

    @Override
    public int getCount() {
        return listPB.size();
    }

    @Override
    public Object getItem(int position) {
        return listPB.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        /**
         * - Gán lần lượt từng data vào từng item của listview
         * - Để liên kết được giữa layout item với data -> dùng LayoutInflater
         * - LayoutInflater cần xác định được môi trường cần chạy
         * - (layout của 1 item, parent của listview (ViewGroup), không xử lý -> false)
         * - có view rồi thì cần xử lý từng cái widget trong view đó
         *      + ánh xạ widget
         *      + phải xác định rõ xem đang ánh xạ từ layout nào -> phải gọi view ra
         * - set data cho từng widget
         */
        //1. Call layout inflater & view
        LayoutInflater myIn = ((Activity) context).getLayoutInflater();
        View myView = myIn.inflate(R.layout.item_phong_ban, parent, false);
        //2. Anh xa widget
        TextView tvPhongBanId = myView.findViewById(R.id.tvPhongBanId);
        TextView tvPhongBanName = myView.findViewById(R.id.tvPhongBanName);
        //3. Set data
        PhongBan pb = listPB.get(position);
        tvPhongBanId.setText(pb.getMaPhongBan());
        tvPhongBanName.setText(pb.getTenPhongBan());

        return myView;
    }
}
