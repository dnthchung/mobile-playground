package com.doanchung.assignmentand102.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.doanchung.assignmentand102.database.DbHelper;

public class UserDAO {
    private DbHelper dbHelper;

    public UserDAO (Context context) {
        dbHelper = new DbHelper(context);
    }

    //login
    public boolean checkLogin(String userName, String password){
        //getReadableDatabase sử dụng khi chức năng cần là đọc dữ liệu, không ảnh hưởng đến db
        //getWritableDatabase sử dụng khi chức năng cần là ghi dữ liệu, ảnh hưởng đến db
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        //2 tham số: 1 là câu lệnh sql, 2 là mảng string chứa các tham số truyền vào câu lệnh sql
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM USER WHERE userName = ? AND password = ?", new String[]{userName, password});
        if(cursor.getCount() > 0){
            return true;
        }
        return false;
    }


}
