package edu.csumb.esotorodriguez.project1gradetracker;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "grade_category", foreignKeys = {
        @ForeignKey(entity = Course.class, parentColumns = "courseID", childColumns = "CourseID", onDelete = CASCADE),
        @ForeignKey(entity = Assignment.class, parentColumns = "assignmentID", childColumns = "AssignmentID", onDelete = CASCADE),
        @ForeignKey(entity = User.class, parentColumns = "userID", childColumns = "UserID", onDelete = CASCADE)
})
public class Grade {

    @PrimaryKey(autoGenerate = true)
    private int gradeID;

    private int score;


}
