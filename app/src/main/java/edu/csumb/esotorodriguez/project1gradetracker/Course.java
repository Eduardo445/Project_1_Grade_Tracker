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

    private int UserID;

    public Course(String instructor, String title, String description, int UserID) {
        this.instructor = instructor;
        this.title = title;
        this.description = description;
        this.UserID = UserID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUserID(int userID) {
        UserID = userID;
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

    public int getUserID() {
        return UserID;
    }
}
