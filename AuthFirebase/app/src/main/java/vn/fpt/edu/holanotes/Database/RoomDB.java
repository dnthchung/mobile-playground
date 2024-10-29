package vn.fpt.edu.holanotes.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {vn.fpt.edu.holanotes.Models.Notes.class}, version = 1, exportSchema = false)
public abstract class RoomDB extends RoomDatabase {
    private static RoomDB database;
    private  static String DATABASE_NAME = "HolaNotes";

    public synchronized static RoomDB getInstance(Context context){
        if (database == null){
            database = androidx.room.Room.databaseBuilder(context.getApplicationContext(), RoomDB.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return database;
    }

    public abstract MainDAO mainDAO();


}
