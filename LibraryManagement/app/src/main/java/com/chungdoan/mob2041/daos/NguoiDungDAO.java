package com.chungdoan.mob2041.daos;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.chungdoan.mob2041.database.DbHelper;
import com.chungdoan.mob2041.models.NguoiDung;

public class NguoiDungDAO {
    /**
     * 1. call db helper
     * 2. Create UserDAO constructor with context.
     * 3. Create logic functions you want to use.
     * 4. Reference to the DAO in the activity. => save data vào shared preferences. để các màn hình khác có thể lôi ra sử dụng.
     *      - Khai báo biến toàn cục cho SharedPreferences
     *      - sửa thêm nó vào trong constructor
     */

    private DbHelper dbHelper;
    SharedPreferences sharedPreferences ;

    public NguoiDungDAO(Context context) {

        dbHelper = new DbHelper(context);
        sharedPreferences = context.getSharedPreferences("DATA_USER_MOB2041", Context.MODE_PRIVATE);
    }




    //1a. login boolean
    public boolean checkLogin1(String userName, String password){
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        String sql = "SELECT * FROM NGUOIDUNG WHERE tendangnhap = ? AND matkhau = ?";
        Cursor cursor = sqLiteDatabase.rawQuery(sql, new String[]{userName, password});


        //check & save role account to shared preferences
        if(cursor.getCount() > 0){
            //move to first row - because cursor is pointing to -1
            cursor.moveToFirst();
            SharedPreferences.Editor editor = sharedPreferences.edit();
            //ColumnIndexOrThrow("role") or getInt(6) => 6 is the index of role column
            editor.putInt("role", cursor.getInt(cursor.getInt(6)));
//            Log.d("NguoiDungDAO", "Role: " + cursor.getInt(cursor.getInt(6)));
            editor.apply();

            return true;
        }
        return false;
    }

    public boolean checkLogin(String userName, String password) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        String sql = "SELECT * FROM NGUOIDUNG WHERE tendangnhap = ? AND matkhau = ?";
        Cursor cursor = sqLiteDatabase.rawQuery(sql, new String[]{userName, password});

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            /**
             * 2 ways to retrieve the role value:
             * - int role = cursor.getInt(cursor.getColumnIndexOrThrow("role"));
             * - int role = cursor.getInt(6);
             */
            int role = cursor.getInt(6); // Correctly retrieve the role value

            // Save the role to SharedPreferences
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("role", role);
            editor.apply();

            cursor.close(); // Always close the cursor after use
            return true;
        }

        cursor.close(); // Always close the cursor after use
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
