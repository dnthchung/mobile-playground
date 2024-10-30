// path : app/src/main/java/vn/fpt/edu/holanotes/Models/Notes.java
package vn.fpt.edu.holanotes.Models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "notes")
public class Notes implements Serializable {

    // Primary Key with auto-generation
    @PrimaryKey(autoGenerate = true)
    int ID = 0;

    // Column for note title
    @ColumnInfo(name = "title")
    String title = "";

    // Column for the note content
    @ColumnInfo(name = "notes")
    String notes = "";

    // Column for the date the note was created/updated
    @ColumnInfo(name = "date")
    String date = "";

    // Column to indicate if the note is pinned
    @ColumnInfo(name = "pinned")
    boolean pinned = false;

    // Getter and Setter for ID
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    // Getter and Setter for Title
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // Getter and Setter for Notes content
    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    // Getter and Setter for Date
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    // Getter and Setter for Pinned
    public boolean isPinned() {
        return pinned;
    }

    public void setPinned(boolean pinned) {
        this.pinned = pinned;
    }
}
