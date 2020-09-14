package edu.csumb.esotorodriguez.project1gradetracker;

import android.app.Application;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class AssignmentViewModel extends AndroidViewModel {
    private AssignmentRepository repository;
    private LiveData<List<Assignment>> allAssignments;

    public AssignmentViewModel(@NonNull Application application) {
        super(application);
        repository = new AssignmentRepository(application);
        allAssignments = repository.getAllAssignments();
    }

    public void insert(Assignment assignment) {
        repository.insert(assignment);
    }

    public void update(Assignment assignment) {
        repository.update(assignment);
    }

    public void delete(Assignment assignment) {
        repository.delete(assignment);
    }

    public LiveData<List<Assignment>> getAllAssignments() {
        return allAssignments;
    }
}
