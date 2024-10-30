//package vn.fpt.edu.holanotes.Database;
//
//import static androidx.room.OnConflictStrategy.REPLACE;
//
//import androidx.room.Dao;
//import androidx.room.Insert;
//import androidx.room.Query;
//
//import java.util.List;
//
//import vn.fpt.edu.holanotes.Models.Notes;
//
//@Dao
//public interface MainDAO {
//
//    @Insert(onConflict = REPLACE)
//    void insert(Notes notes);
//
//    @Query("SELECT * FROM notes ORDER BY id DESC")
//    List<Notes> getAll();
//
//    @Query("UPDATE notes SET title = :title, notes = :notes WHERE ID = :id")
//    void update(int id, String title, String notes);
//
//
////    void delete(Notes notes);
//}

package vn.fpt.edu.holanotes.Database;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Delete;

import java.util.List;

import vn.fpt.edu.holanotes.Models.Notes;

@Dao
public interface MainDAO {

    // Insert a new note with conflict strategy to replace if the ID exists
    @Insert(onConflict = REPLACE)
    void insert(Notes notes);

    // Retrieve all notes from the database, ordered by ID in descending order
    @Query("SELECT * FROM notes ORDER BY id DESC")
    List<Notes> getAll();

    // Update an existing note's title, content, and pinned status by its ID
    @Query("UPDATE notes SET title = :title, notes = :notes WHERE ID = :id")
    void update(int id, String title, String notes);

    // Delete a note from the database
    @Delete
    void delete(Notes notes);
}
