package edu.csumb.esotorodriguez.project1gradetracker;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "grade_category_table", foreignKeys =
        @ForeignKey(entity = Course.class, parentColumns = "courseID", childColumns = "CourseID", onDelete = CASCADE))
public class GradeCategory {

    @PrimaryKey(autoGenerate = true)
    private int categoryID;

    private String title;

    private int weight;

    private int CourseID;

    public GradeCategory(String title, int weight, int CourseID) {
        this.title = title;
        this.weight = weight;
        this.CourseID = CourseID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public String getTitle() {
        return title;
    }

    public int getWeight() {
        return weight;
    }

    public int getCourseID() {
        return CourseID;
    }
}
