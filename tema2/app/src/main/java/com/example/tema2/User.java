package com.example.tema2;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Users")
public class User {
    @PrimaryKey
    @NonNull
    private String uid;

    @ColumnInfo(name = "full_name")
    private String fullName;

    @ColumnInfo(name = "mark")
    private int mark;

    public User(String uid, String fullName, int mark) {
        this.uid = uid;
        this.fullName = fullName;
        this.mark = mark;
    }

    public String getUid() {
        return uid;
    }

    public String getFullName() {
        return fullName;
    }

    public int getMark() {
        return mark;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }
}
