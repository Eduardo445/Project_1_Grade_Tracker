package edu.csumb.esotorodriguez.project1gradetracker;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

@Dao
public interface GradeCategoryDao {

    @Insert
    void insert(GradeCategory gradeCategory);

    @Update
    void update(GradeCategory gradeCategory);

    @Delete
    void delete(GradeCategory gradeCategory);

    // will add more later
}
