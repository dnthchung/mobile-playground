package com.doanchung.assignmentand102.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {

    //must create constructor
    //context để khi mà muốn gọi dbhelper ra thì cần phải truyền vào context
    public DbHelper(Context context) {
        //context , db name, factory -> ko dung , version -> dung de update db
        super(context, "AND102", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String qUserTable = "CREATE TABLE USER ( userName TEXT, password TEXT, fullName TEXT )";
        //db  là sqlite database mà có sẵn trong onCreate
        db.execSQL(qUserTable);

        String qProductTable = "CREATE TABLE PRODUCT ( id INTEGER PRIMARY KEY AUTOINCREMENT, productName TEXT, productPrice INTEGER, poductNumber INTEGER )";
        db.execSQL(qProductTable);

        String dUser = "INSERT INTO USER (userName, password, fullName) VALUES ('admin1', '123', 'admin1'),('admin2', '123', 'admin2')";
        db.execSQL(dUser);

        String dProduct = "INSERT INTO PRODUCT (productName, productPrice, productNumber) VALUES (1, 'product1', 1000, 10),(2, 'product2', 2000, 20)";
        db.execSQL(dProduct);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion != newVersion){
            db.execSQL("DROP TABLE IF EXISTS USER");
            db.execSQL("DROP TABLE IF EXISTS PRODUCT");
            onCreate(db);
        }
    }
}
