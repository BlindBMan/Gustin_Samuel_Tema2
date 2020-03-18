package com.example.tema2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class UserRepository {
    private UserDAO userDAO;
    private UserDatabase userDatabase;
    private LiveData<List<User>> allUsers;

    public UserRepository(Context context) {
        userDatabase = UserDatabase.getUserDatabase(context);
        userDAO = userDatabase.userDao();
        allUsers = userDAO.getAllUsers();
    }

    public void insert(User user) {
        new InsertAsyncTask(userDAO).execute(user);
    }

    LiveData<List<User>> getAllUsers() {
        return allUsers;
    }

    public void delete(User user) {
        new DeleteAsyncTask(userDAO).execute(user);
    }

    private class InsertAsyncTask extends AsyncTask<User, Void, Void> {
        private UserDAO localUserDAO;

        public InsertAsyncTask(UserDAO userDAO) {
            this.localUserDAO = userDAO;
        }

        @Override
        protected Void doInBackground(User... users) {
            localUserDAO.insert(users[0]);
            return null;
        }
    }

    private class DeleteAsyncTask extends AsyncTask<User, Void, Void> {
        private UserDAO localUserDao;

        public DeleteAsyncTask(UserDAO userDAO) {
            this.localUserDao = userDAO;
        }

        @Override
        protected Void doInBackground(User... users) {
            localUserDao.delete(users[0]);
            return null;
        }
    }
}
