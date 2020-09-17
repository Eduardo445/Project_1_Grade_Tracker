package edu.csumb.esotorodriguez.project1gradetracker;

import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class EditCourseScreen extends AppCompatActivity {
    public static final String EXTRA_ID =
            "edu.csumb.esotorodriguez.loginandlanding.EXTRA_ID";
    public static final String EXTRA_TITLE =
            "edu.csumb.esotorodriguez.loginandlanding.EXTRA_TITLE";
    public static final String EXTRA_DESCRIPTION =
            "edu.csumb.esotorodriguez.loginandlanding.EXTRA_DESCRIPTION";
    public static final String EXTRA_PRIORITY =
            "edu.csumb.esotorodriguez.loginandlanding.EXTRA_PRIORITY";

    private EditText editTextTitle;
    private EditText editTextInstructorName;
    private EditText editTextDescription;
    private EditText editTextStartDate;
    private EditText editTextEndDate;

    @Override
    protected  void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_course_screen);

        editTextTitle = findViewById(R.id./*insert edit text name here*/);
        editTextInstructorName = findViewById(R.id./*insert edit text name here*/);
        editTextDescription = findViewById(R.id./*insert edit text name here*/);
        editTextStartDate = findViewById(R.id./*insert edit text name here*/);
        editTextEndDate = findViewById(R.id./*insert edit text name here*/);

    }

}
