package edu.csumb.esotorodriguez.project1gradetracker;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface GradeCategoryDao {

    @Insert
    void insert(GradeCategory gradeCategory);

    @Update
    void update(GradeCategory gradeCategory);

    @Delete
    void delete(GradeCategory gradeCategory);

    @Query("SELECT * FROM grade_category_table ORDER BY categoryID DESC")
    LiveData<List<GradeCategory>> getAllGradeCategories();
}
