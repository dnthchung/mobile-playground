package com.chungdoan.mob2041.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper  {

    /**
     * 1. Implement onCreate() and onUpgrade() methods.
     * 2. Constructor with context.
     *
     */

    public DbHelper(Context context) {
        super(context, "mob2041_libraryManagement", null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        /**
         * 1. Create table - table có PK trước rồi mới reference đến FK của table khác.
         * 2. sdt : text => start = number 0
         * 3. role : 1- user \ 2 - thủ thư \ 3 - admin
         * List of tables:
         * - LOAISACH (maloai, tenloai)
         * - SACH (masach, tensach, tacgia, giaban, maloai)
         * - NGUOIDUNG (mand, tennd, diachi, sdt, tendangnhap, matkhau, role)
         * - PHIEUMUON (mapm, ngaymuon, ngaytra, mand)
         * - CHITIETPHIEUMUON (mapm, masach, soluong)
         *
         */
        String tLoaiSach = "CREATE TABLE LOAISACH (maloai integer PRIMARY KEY AUTOINCREMENT, tenloai TEXT)";
        db.execSQL(tLoaiSach);

        String tSach = "CREATE TABLE SACH (masach integer PRIMARY KEY AUTOINCREMENT, tensach TEXT, tacgia TEXT, giaban INTEGER, maloai INTEGER REFERENCES LOAISACH(maloai))";
        db.execSQL(tSach);

        String tNguoiDung = "CREATE TABLE NGUOIDUNG (mand integer PRIMARY KEY AUTOINCREMENT, tennd TEXT, diachi TEXT, sdt TEXT, tendangnhap TEXT, matkhau TEXT, role INTEGER)";
        db.execSQL(tNguoiDung);

        String tPhieuMuon = "CREATE TABLE PHIEUMUON (mapm integer PRIMARY KEY AUTOINCREMENT, ngaymuon TEXT, ngaytra TEXT, mand INTEGER REFERENCES NGUOIDUNG(mand))";
        db.execSQL(tPhieuMuon);

        String tChiTietPhieuMuon = "CREATE TABLE CHITIETPHIEUMUON (mapm INTEGER PRIMARY KEY REFERENCES PHIEUMUON(mapm), masach PRIMARY KEY INTEGER REFERENCES SACH(masach), soluong INTEGER)";
        db.execSQL(tChiTietPhieuMuon);


        // Insert sample data into table
        // LOAISACH
        db.execSQL("INSERT INTO LOAISACH (tenloai) VALUES ('Fiction')");
        db.execSQL("INSERT INTO LOAISACH (tenloai) VALUES ('Non-fiction')");
        db.execSQL("INSERT INTO LOAISACH (tenloai) VALUES ('Science')");
        db.execSQL("INSERT INTO LOAISACH (tenloai) VALUES ('History')");

        // SACH
        db.execSQL("INSERT INTO SACH (tensach, tacgia, giaban, maloai) VALUES ('Book 1', 'Author 1', 100, 1)");
        db.execSQL("INSERT INTO SACH (tensach, tacgia, giaban, maloai) VALUES ('Book 2', 'Author 2', 150, 2)");
        db.execSQL("INSERT INTO SACH (tensach, tacgia, giaban, maloai) VALUES ('Book 3', 'Author 3', 200, 3)");
        db.execSQL("INSERT INTO SACH (tensach, tacgia, giaban, maloai) VALUES ('Book 4', 'Author 4', 250, 4)");

        // NGUOIDUNG
        db.execSQL("INSERT INTO NGUOIDUNG (tennd, diachi, sdt, tendangnhap, matkhau, role) VALUES ('User 1', 'Address 1', '0123456789', 'user1', '123', 1)");
        db.execSQL("INSERT INTO NGUOIDUNG (tennd, diachi, sdt, tendangnhap, matkhau, role) VALUES ('User 2', 'Address 2', '0987654321', 'user2', '123', 2)");
        db.execSQL("INSERT INTO NGUOIDUNG (tennd, diachi, sdt, tendangnhap, matkhau, role) VALUES ('User 3', 'Address 3', '0111222333', 'user3', '123', 3)");

        // PHIEUMUON
        db.execSQL("INSERT INTO PHIEUMUON (ngaymuon, ngaytra, mand) VALUES ('2024-08-01', '2024-08-10', 1)");
        db.execSQL("INSERT INTO PHIEUMUON (ngaymuon, ngaytra, mand) VALUES ('2024-08-02', '2024-08-12', 2)");
        db.execSQL("INSERT INTO PHIEUMUON (ngaymuon, ngaytra, mand) VALUES ('2024-08-03', '2024-08-13', 3)");

        // CHITIETPHIEUMUON
        db.execSQL("INSERT INTO CHITIETPHIEUMUON (mapm, masach, soluong) VALUES (1, 1, 2)");
        db.execSQL("INSERT INTO CHITIETPHIEUMUON (mapm, masach, soluong) VALUES (2, 2, 1)");
        db.execSQL("INSERT INTO CHITIETPHIEUMUON (mapm, masach, soluong) VALUES (3, 3, 4)");



    }
    //chạy khi version của database thay đổi
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion != newVersion){
            db.execSQL("DROP TABLE IF EXISTS LOAISACH");
            db.execSQL("DROP TABLE IF EXISTS SACH");
            db.execSQL("DROP TABLE IF EXISTS NGUOIDUNG");
            db.execSQL("DROP TABLE IF EXISTS PHIEUMUON");
            db.execSQL("DROP TABLE IF EXISTS CHITIETPHIEUMUON");
            onCreate(db);
        }
    }
}
