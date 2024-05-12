package com.doanchung.assignmentand102.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {

    //must create constructor
    //context để khi mà muốn gọi dbhelper ra thì cần phải truyền vào context
    public DbHelper(Context context) {
        //context , db name, factory -> ko dung , version -> dung de update db
        super(context, "AND102", null, 2);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        //db  là sqlite database mà có sẵn trong onCreate
        // Create USER table
        String qUserTable = "CREATE TABLE USER ( userName TEXT, password TEXT, fullName TEXT, email TEXT )";
        db.execSQL(qUserTable);

        // Create PRODUCT table
        String qProductTable = "CREATE TABLE PRODUCT ( id INTEGER PRIMARY KEY AUTOINCREMENT, productName TEXT, productPrice INTEGER, productNumber INTEGER )";
        db.execSQL(qProductTable);

        // Insert sample data into USER table
        String dUser = "INSERT INTO USER (userName, password, fullName, email) VALUES ('admin1', '123', 'Doan Chung', 'chungdt03.work@gmail.com')";
        db.execSQL(dUser);

        // Insert sample data into PRODUCT table
        String dProduct = "INSERT INTO PRODUCT (id, productName, productPrice, productNumber) VALUES (1, 'product1', 1000, 10),(2, 'product2', 2000, 20)";
        db.execSQL(dProduct);

    }

    //chạy khi version của database thay đổi
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion != newVersion){
            db.execSQL("DROP TABLE IF EXISTS USER");
            db.execSQL("DROP TABLE IF EXISTS PRODUCT");
            onCreate(db);
        }
    }
}
