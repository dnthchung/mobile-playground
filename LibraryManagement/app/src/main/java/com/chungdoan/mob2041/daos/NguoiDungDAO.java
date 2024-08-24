package com.chungdoan.mob2041.daos;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.chungdoan.mob2041.database.DbHelper;
import com.chungdoan.mob2041.models.NguoiDung;

public class NguoiDungDAO {
    /**
     * 1. call db helper
     * 2. Create UserDAO constructor with context.
     * 3. Create logic functions you want to use.
     */
    private DbHelper dbHelper;
    public NguoiDungDAO(Context context) {
        dbHelper = new DbHelper(context);
    }

    //1a. login boolean
    public boolean checkLogin(String userName, String password){
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        String sql = "SELECT * FROM NGUOIDUNG WHERE tendangnhap = ? AND matkhau = ?";
        Cursor cursor = sqLiteDatabase.rawQuery(sql, new String[]{userName, password});
        if(cursor.getCount() > 0){
            return true;
        }
        return false;
    }

    // Retrieve user by ID (mand)
    public NguoiDung getUserById(int mand) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        String sql = "SELECT * FROM NGUOIDUNG WHERE mand = ?";
        Cursor cursor = sqLiteDatabase.rawQuery(sql, new String[]{String.valueOf(mand)});

        if (cursor.moveToFirst()) {
            String tennd = cursor.getString(cursor.getColumnIndexOrThrow("tennd"));
            String diachi = cursor.getString(cursor.getColumnIndexOrThrow("diachi"));
            String sdt = cursor.getString(cursor.getColumnIndexOrThrow("sdt"));
            String tendangnhap = cursor.getString(cursor.getColumnIndexOrThrow("tendangnhap"));
            String matkhau = cursor.getString(cursor.getColumnIndexOrThrow("matkhau"));
            int role = cursor.getInt(cursor.getColumnIndexOrThrow("role"));

            cursor.close();
            return new NguoiDung(mand, tennd, diachi, sdt, tendangnhap, matkhau, role);
        }

        cursor.close();
        return null;
    }





    //2. sign up

    //3. check email exist (forgot password)

    //4. change password


}
