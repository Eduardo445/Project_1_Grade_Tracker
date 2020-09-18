package edu.csumb.esotorodriguez.project1gradetracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

public class EditCourseScreen extends AppCompatActivity {
    public static final String EXTRA_ID =
            "edu.csumb.esotorodriguez.Project_1_Grade_Tracker.EXTRA_ID";
    public static final String EXTRA_TITLE =
            "edu.csumb.esotorodriguez.Project_1_Grade_Tracker.EXTRA_TITLE";
    public static final String EXTRA_INSTRUCTOR =
            "edu.csumb.esotorodriguez.Project_1_Grade_Tracker.EXTRA_INSTRUCTOR";
    public static final String EXTRA_DESCRIPTION =
            "edu.csumb.esotorodriguez.Project_1_Grade_Tracker.EXTRA_DESCRIPTION";

    private EditText editTextTitle;
    private EditText editTextInstructorName;
    private EditText editTextDescription;

    private CourseDao courseDB;

    @Override
    protected  void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_course_screen);

        courseDB = Room.databaseBuilder(this, TrackerDB.class, TrackerDB.TRACKER_DB).allowMainThreadQueries().build().courseDao();

        editTextTitle = findViewById(R.id./*insert edit text name here*/);
        editTextInstructorName = findViewById(R.id./*insert edit text name here*/);
        editTextDescription = findViewById(R.id./*insert edit text name here*/);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        Intent intent = getIntent();
        if(intent.hasExtra(EXTRA_ID)){
            setTitle("Edit Note");
            editTextTitle.setText(intent.getStringExtra(Intent.EXTRA_TITLE));
            editTextInstructorName.setText(intent.getStringExtra(Intent.EXTRA_INSTRUCTOR));
            editTextDescription.setText(intent.getStringExtra(Intent.EXTRA_DESCRIPTION));

        } else {
            setTitle("Add Note");
        }
    }

    private void saveCourse(){
        String title = editTextTitle.getText().toString();
        String instructor = editTextInstructorName.getText().toString();
        String description = editTextDescription.getText().toString();
        String startDate = editTextStartDate.getText().toString();
        String endDate = editTextEndDate.getText().toString();

        if(title.trim().isEmpty() || instructor.trim().isEmpty() || description.trim().isEmpty() || startDate.trim().isEmpty() || endDate.trim().isEmpty()){
            Toast.makeText(this, "Please insert a title, instructor, description, start date, and end date", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_TITLE, title);
        data.putExtra(EXTRA_INSTRUCTOR, instructor);
        data.putExtra(EXTRA_DESCRIPTION, description);
        data.putExtra(EXTRA_STARTDATE, startDate);
        data.putExtra(EXTRA_ENDDATE, endDate);

        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if(id != -1){
            data.putExtra(EXTRA_ID, id);
        }

        setResult(RESULT_OK, data);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_user_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case.R.id.save_note:
                saveCourse();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
