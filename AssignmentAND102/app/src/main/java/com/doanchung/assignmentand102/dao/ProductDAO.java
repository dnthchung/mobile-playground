package com.doanchung.assignmentand102.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.doanchung.assignmentand102.database.DbHelper;
import com.doanchung.assignmentand102.models.Product;

import java.util.ArrayList;

public class ProductDAO {
     private DbHelper dbHelper;

     public ProductDAO(Context context) {
         dbHelper = new DbHelper(context);
     }
     //lấy danh sách sản phẩm
    public ArrayList<Product> getAllProduct(){
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM PRODUCT", null);
        ArrayList<Product> productList = new ArrayList<>();
        if(cursor.getCount() > 0 ){
            cursor.moveToFirst();
            do {
                productList.add(new Product(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getInt(2),
                        cursor.getInt(3)));
            }while (cursor.moveToNext());
        }
        return productList;
    }


}
