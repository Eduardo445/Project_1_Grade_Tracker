package edu.csumb.esotorodriguez.project1gradetracker;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Room;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.util.List;

public class CourseActivity extends AppCompatActivity {

    private CourseDao courseDB;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        courseDB = Room.databaseBuilder(this, TrackerDB.class, TrackerDB.TRACKER_DB).allowMainThreadQueries().build().courseDao();

        RecyclerView recyclerView = findViewById(R.id.recycler_course_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        CourseAdapter adapter = new CourseAdapter();
        recyclerView.setAdapter(adapter);


    }

    public static Intent getIntent(Context context, int value) {
        Intent intent = new Intent(context, CourseActivity.class);
        intent.putExtra("COURSE_ID", value);
        return intent;

    }
}

