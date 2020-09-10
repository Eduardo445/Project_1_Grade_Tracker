package edu.csumb.esotorodriguez.project1gradetracker;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {User.class, Course.class, Assignment.class, GradeCategory.class}, version = 1)
public abstract class TrackerDB extends RoomDatabase {

    private static TrackerDB instance;

    public abstract UserDao userDao();
    public abstract CourseDao courseDao();
    public abstract AssignmentDao assignmentDao();
    public abstract GradeCategoryDao gradeCategoryDao();

    public static synchronized TrackerDB getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    TrackerDB.class, "tracker_database")
                    .fallbackToDestructiveMigration().build();
        }
        return instance;
    }

    // May add something later
}
