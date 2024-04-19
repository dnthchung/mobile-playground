package com.doanchung.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.doanchung.app1.R;
import com.doanchung.model.Student;

import java.util.ArrayList;

public class StudentAdapter extends BaseAdapter {
    /**
     * - phải biết dc nó chạy chỗ nào, --> cần context
     * - phải bết dc cần truyền data gì --> cần list data
     * - mục đích cần 1 constructor: để khi khởi tạo adapter này bên main activity,
     * chỉ cần truyền data vào trong constructor
     */
    private Context context;
    private ArrayList<Student> listStudent;

    public StudentAdapter(Context context, ArrayList<Student> listStudent) {
        this.context = context;
        this.listStudent = listStudent;
    }

    @Override
    public int getCount() {
        return listStudent.size();
    }

    @Override
    public Object getItem(int position) {
        return listStudent.get(position);
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
         * - Gán lần lượt từng data vào từng item của listview
         * - Để liên kết được giữa layout item với data -> dùng LayoutInflater
         * - LayoutInflater cần xác định được môi trường cần chạy
         * - (layout của 1 item, parent của listview (ViewGroup), không xử lý -> false)
         * - có view rồi thì cần xử lý từng cái widget trong view đó
         *      + ánh xạ widget
         *      + phải xác định rõ xem đang ánh xạ từ layout nào -> phải gọi view ra
         * - set data cho từng widget
         */
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.activity_lvmain_item,parent ,false);

        //ánh xạ
        TextView studentId = view.findViewById(R.id.studentId);
        TextView studentName = view.findViewById(R.id.studentName);
        TextView studentAVG = view.findViewById(R.id.studentAVG);

        //set data
        studentId.setText(listStudent.get(position).getStudentId());
        studentName.setText(listStudent.get(position).getStudentName());
        //setText thì phải là String nên phải ép kiểu thằng điểm trung bình
        studentAVG.setText(String.valueOf(listStudent.get(position).getAvgPoint()));

        return view;
    }
}
