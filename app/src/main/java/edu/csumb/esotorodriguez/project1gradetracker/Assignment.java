package edu.csumb.esotorodriguez.project1gradetracker;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "assignment_table", foreignKeys = {
        @ForeignKey(entity = GradeCategory.class, parentColumns = "categoryID", childColumns = "CategoryID", onDelete = CASCADE),
        @ForeignKey(entity = Course.class, parentColumns = "courseID", childColumns = "CourseID", onDelete = CASCADE)
})
public class Assignment {

    @PrimaryKey(autoGenerate = true)
    private int assignmentID;

    private String details;

    private int maxScore;

    private int earnedScore;

    private String assignedDate;

    private String dueDate;

    private int CategoryID;

    private int CourseID;

    public Assignment(String details, int maxScore, int earnedScore, String assignedDate, String dueDate, int CategoryID, int CourseID) {
        this.details = details;
        this.maxScore = maxScore;
        this.earnedScore = earnedScore;
        this.assignedDate = assignedDate;
        this.dueDate = dueDate;
        this.CategoryID = CategoryID;
        this.CourseID = CourseID;
    }

    public void setAssignmentID(int assignmentID) {
        this.assignmentID = assignmentID;
    }

    public int getAssignmentID() {
        return assignmentID;
    }

    public String getDetails() {
        return details;
    }

    public int getMaxScore() {
        return maxScore;
    }

    public int getEarnedScore() {
        return earnedScore;
    }

    public String getAssignedDate() {
        return assignedDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    // These last two may not be needed
    public int getCategoryID() {
        return CategoryID;
    }

    public int getCourseID() {
        return CourseID;
    }
}
