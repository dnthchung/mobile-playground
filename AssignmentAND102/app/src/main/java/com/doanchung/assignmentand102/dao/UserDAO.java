package com.doanchung.assignmentand102.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.doanchung.assignmentand102.database.DbHelper;

public class UserDAO {
    private DbHelper dbHelper;

    public UserDAO (Context context) {
        dbHelper = new DbHelper(context);
    }

    //1. login
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

    //2. sign up
    public boolean signUp(String userName, String password, String fullName){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        //để thêm 1 dòng data hay là thay đổi thông tin gì đó  -> dùng content values
        //put : key(key đúng với tên của column trong database), value
        ContentValues contentValues = new ContentValues();
        contentValues.put("userName", userName);
        contentValues.put("password", password);
        contentValues.put("fullName", fullName);

        //long ? vì thằng insert của sqlite trả về 1 số, mà con số chính là cái id của data mà mình insert vào
        long check = sqLiteDatabase.insert("USER", null, contentValues);

        //why -1, check sqlite web insert : ok -> return id, fail -> return -1
        //return check != -1
        if(check != -1) {
            return true;
        }
        return false;

    }



}
