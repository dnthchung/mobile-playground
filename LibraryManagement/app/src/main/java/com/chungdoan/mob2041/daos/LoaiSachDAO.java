package com.chungdoan.mob2041.daos;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.chungdoan.mob2041.database.DbHelper;
import com.chungdoan.mob2041.models.LoaiSach;

import java.util.ArrayList;
import java.util.List;

public class LoaiSachDAO {
    /**
     * 1. call db helper
     * 2. Create UserDAO constructor with context.
     * 3. Create logic functions you want to use.
     */
    private DbHelper dbHelper;
    public LoaiSachDAO(Context context) {
        dbHelper = new DbHelper(context);
    }

    //get all LoaiSach
    public List<LoaiSach> getAllLoaiSach() {
        List<LoaiSach> loaiSachList = new ArrayList<>();

        try (SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
             Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM LOAISACH", null)) {

            if (cursor != null && cursor.moveToFirst()) {
                do {
                    int maloaisach = cursor.getInt(cursor.getColumnIndexOrThrow("maloai"));
                    String tenloaisach = cursor.getString(cursor.getColumnIndexOrThrow("tenloai"));

                    LoaiSach loaiSach = new LoaiSach(maloaisach, tenloaisach);
                    loaiSachList.add(loaiSach);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return loaiSachList;
    }

    public ArrayList<LoaiSach> getAllLoaiSach2() {
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM LOAISACH", null);
        ArrayList<LoaiSach> loaiSachList = new ArrayList<>();
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                loaiSachList.add(new LoaiSach(
                        cursor.getInt(0),
                        cursor.getString(1)));
            } while (cursor.moveToNext());
        }
        return loaiSachList;
    }

}
