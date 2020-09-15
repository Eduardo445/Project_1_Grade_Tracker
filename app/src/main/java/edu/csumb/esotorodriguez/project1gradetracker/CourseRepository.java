package edu.csumb.esotorodriguez.project1gradetracker;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class CourseRepository {
    private CourseDao courseDao;
    private LiveData<List<Course>> allCourses;

    public CourseRepository(Application application) {
        TrackerDB database = TrackerDB.getInstance(application);
        courseDao = database.courseDao();
        allCourses = courseDao.getAllCourses();
    }

    public void insert(Course course) {
        new InsertCourseAsyncTask(courseDao).execute(course);
    }

    public void update(Course course) {
        new UpdateCourseAsyncTask(courseDao).execute(course);
    }

    public void delete(Course course) {
        new DeleteCourseAsyncTask(courseDao).execute(course);
    }

    public LiveData<List<Course>> getAllCourses() {
        return allCourses;
    }

    private static class InsertCourseAsyncTask extends AsyncTask<Course, Void, Void> {
        private CourseDao courseDao;

        private InsertCourseAsyncTask(CourseDao courseDao) {
            this.courseDao = courseDao;
        }

        @Override
        protected Void doInBackground(Course... courses) {
            courseDao.insert(courses[0]);
            return null;
        }
    }

    private static class UpdateCourseAsyncTask extends AsyncTask<Course, Void, Void> {
        private CourseDao courseDao;

        private UpdateCourseAsyncTask(CourseDao courseDao) {
            this.courseDao = courseDao;
        }

        @Override
        protected Void doInBackground(Course... courses) {
            courseDao.update(courses[0]);
            return null;
        }
    }

    private static class DeleteCourseAsyncTask extends AsyncTask<Course, Void, Void> {
        private CourseDao coursesDao;

        private DeleteCourseAsyncTask(CourseDao courseDao) {
            this.coursesDao = courseDao;
        }

        @Override
        protected Void doInBackground(Course... courses) {
            coursesDao.delete(courses[0]);
            return null;
        }

    }
}
