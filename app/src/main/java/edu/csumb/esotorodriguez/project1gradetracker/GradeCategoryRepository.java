package edu.csumb.esotorodriguez.project1gradetracker;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class GradeCategoryRepository {
    private GradeCategoryDao gradeCategoryDao;
    private LiveData<List<GradeCategory>> allGradeCategories;

    public GradeCategoryRepository(Application application) {
        TrackerDB database = TrackerDB.getInstance(application);
        gradeCategoryDao = database.gradeCategoryDao();
        allGradeCategories = gradeCategoryDao.getAllGradeCategories();
    }

    public void insert(GradeCategory gradeCategory) {
        new InsertGradeCategoryAsyncTask(gradeCategoryDao).execute(gradeCategory);
    }

    public void update(GradeCategory gradeCategory) {
        new UpdateGradeCategoryAsyncTask(gradeCategoryDao).execute(gradeCategory);
    }

    public void delete(GradeCategory gradeCategory) {
        new DeleteGradeCategoryAsyncTask(gradeCategoryDao).execute(gradeCategory);
    }

    public LiveData<List<GradeCategory>> getAllGradeCategories() {
        return allGradeCategories;
    }

    private static class InsertGradeCategoryAsyncTask extends AsyncTask<GradeCategory, Void, Void> {
        private GradeCategoryDao gradeCategoryDao;

        private InsertGradeCategoryAsyncTask(GradeCategoryDao gradeCategoryDao){
            this.gradeCategoryDao = gradeCategoryDao;
        }

        @Override
        protected Void doInBackground(GradeCategory... gradeCategories) {
            gradeCategoryDao.insert(gradeCategories[0]);
            return null;
        }
    }

    private static class UpdateGradeCategoryAsyncTask extends AsyncTask<GradeCategory, Void, Void> {
        private GradeCategoryDao gradeCategoryDao;

        private UpdateGradeCategoryAsyncTask(GradeCategoryDao gradeCategoryDao){
            this.gradeCategoryDao = gradeCategoryDao;
        }

        @Override
        protected Void doInBackground(GradeCategory... gradeCategories) {
            gradeCategoryDao.update(gradeCategories[0]);
            return null;
        }
    }

    private static class DeleteGradeCategoryAsyncTask extends AsyncTask<GradeCategory, Void, Void> {
        private GradeCategoryDao gradeCategoryDao;

        private DeleteGradeCategoryAsyncTask(GradeCategoryDao gradeCategoryDao){
            this.gradeCategoryDao = gradeCategoryDao;
        }

        @Override
        protected Void doInBackground(GradeCategory... gradeCategories) {
            gradeCategoryDao.delete(gradeCategories[0]);
            return null;
        }
    }
}

