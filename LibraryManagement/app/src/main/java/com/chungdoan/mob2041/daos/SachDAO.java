package com.chungdoan.mob2041.daos;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.chungdoan.mob2041.database.DbHelper;
import com.chungdoan.mob2041.models.Sach;

import java.util.ArrayList;

public class SachDAO {

    private DbHelper dbHelper;

    public SachDAO(Context context) {
        dbHelper = new DbHelper(context);
    }

    //get all books
    public ArrayList<Sach> getAllBooks (){
        ArrayList<Sach> sachList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM SACH", null);
        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            do {
                sachList.add(new Sach(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getInt(3),
                        cursor.getInt(4)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return sachList;
    }
}
