package com.doanchung.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.doanchung.model.Student;

import java.util.ArrayList;

public class StudentAdapter extends BaseAdapter {

    //phải biết dc nó chạy chỗ nào, --> cần context
    private Context context;
    ArrayList<Student> students;

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
        //data phải là kiểu long nó mới rt dc, mà ko phải lúc nào
        //cũng trả về kiểu long được, nên có thể skip qua phần này
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        /**
         * Gán lần lượt từng data vào từng item của listview
         *
         */
        return null;
    }
}
