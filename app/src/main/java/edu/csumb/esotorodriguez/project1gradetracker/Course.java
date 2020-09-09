package edu.csumb.esotorodriguez.project1gradetracker;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "course_table", foreignKeys =
        @ForeignKey(entity = User.class, parentColumns = "userID", childColumns = "UserID", onDelete = CASCADE))
public class Course {

    @PrimaryKey(autoGenerate = true)
    private int courseID;

    private String instructor;

    private String title;

    private String description;

    // check for adding dates https://www.youtube.com/watch?v=CNGLsYPZd_o
    private String startDate;

    private String endDate;

    private int UserID;

    public Course(String instructor, String title, String description, String startDate, String endDate, int UserID) {
        this.instructor = instructor;
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.UserID = UserID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public int getCourseID() {
        return courseID;
    }

    public String getInstructor() {
        return instructor;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    // This may not be necessary
    public int getUserID() {
        return UserID;
    }
}
