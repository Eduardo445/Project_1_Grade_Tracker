package edu.csumb.esotorodriguez.project1gradetracker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseHolder> {
    private List<Course> courses = new ArrayList<>();

    @NonNull
    @Override
    public CourseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.course_item, parent, false);
        return new CourseHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseHolder holder, int position) {
        Course currentCourse = courses.get(position);
        holder.textViewTitle.setText(currentCourse.getTitle());
        holder.textViewInstructor.setText(currentCourse.getInstructor());
        holder.textViewDescription.setText(currentCourse.getDescription());
        holder.textViewCourseID.setText(currentCourse.getCourseID());
        holder.textViewUserID.setText(String.valueOf(currentCourse.getUserID()));
    }

    @Override
    public int getItemCount() {
        return courses.size();
    }

    class CourseHolder extends RecyclerView.ViewHolder{
        private TextView textViewTitle;
        private TextView textViewInstructor;
        private TextView textViewDescription;
        private TextView textViewCourseID;
        private TextView textViewUserID;

        public CourseHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.text_view_title);
            textViewInstructor = itemView.findViewById(R.id.text_view_instructor);
            textViewDescription = itemView.findViewById(R.id.text_view_description);
            textViewUserID = itemView.findViewById(R.id.text_view_userID);
            textViewCourseID = itemView.findViewById(R.id.text_view_courseID);
        }


    }
}



