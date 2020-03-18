package com.example.tema2;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = User.class, version = 1)
public abstract class UserDatabase extends RoomDatabase {
    public abstract UserDAO userDao();

    private static UserDatabase userDatabaseInstance;

    static UserDatabase getUserDatabase(Context context) {
        if (userDatabaseInstance == null) {
            userDatabaseInstance = Room.databaseBuilder(context.getApplicationContext(),
                    UserDatabase.class, "user_database").build();
        }
        return userDatabaseInstance;
    }
}
