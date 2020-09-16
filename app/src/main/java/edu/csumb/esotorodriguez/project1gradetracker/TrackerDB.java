package edu.csumb.esotorodriguez.project1gradetracker;


import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {User.class, Course.class, Assignment.class, GradeCategory.class}, version = 1)
public abstract class TrackerDB extends RoomDatabase {

    public static final String TRACKER_DB = "trackerDB";

    public abstract UserDao userDao();
    public abstract CourseDao courseDao();
    public abstract AssignmentDao assignmentDao();
    public abstract GradeCategoryDao gradeCategoryDao();

}
