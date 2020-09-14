package edu.csumb.esotorodriguez.project1gradetracker;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

import androidx.lifecycle.LiveData;

public class UserRepository {
    private UserDao userDao;
    private LiveData<List<User>> allUsers;

    public UserRepository(Application application) {
        TrackerDB database = TrackerDB.getInstance(application);
        userDao = database.userDao();
        allUsers = userDao.getAllUsers();
    }

    public void insert(User user) {
        new UserRepository.InsertUserAsyncTask(userDao).execute(user);
    }

    public void update(Assignment assignment) {
        new AssignmentRepository.UpdateUserAsyncTask(assignmentDao).execute(assignment);
    }

    public void delete(Assignment assignment) {
        new AssignmentRepository.DeleteUserAsyncTask(assignmentDao).execute(assignment);
    }

    public LiveData<List<Assignment>> getAllAssignments() {
        return allAssignments;
    }

    private static class InsertUserAsyncTask extends AsyncTask<User, Void, Void> {
        private UserDao userDao;

        private InsertUserAsyncTask(UserDao userDao){
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDao.insert(users[0]);
            return null;
        }
    }

    private static class UpdateUserAsyncTask extends AsyncTask<Assignment, Void, Void> {
        private AssignmentDao assignmentDao;

        private UpdateUserAsyncTask(AssignmentDao assignmentDao){
            this.assignmentDao = assignmentDao;
        }

        @Override
        protected Void doInBackground(Assignment... assignments) {
            assignmentDao.update(assignments[0]);
            return null;
        }
    }

    private static class DeleteUserAsyncTask extends AsyncTask<Assignment, Void, Void> {
        private AssignmentDao assignmentDao;

        private DeleteUserAsyncTask(AssignmentDao assignmentDao){
            this.assignmentDao = assignmentDao;
        }

        @Override
        protected Void doInBackground(Assignment... assignments) {
            assignmentDao.delete(assignments[0]);
            return null;
        }
    }
}
