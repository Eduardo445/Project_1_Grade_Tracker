package edu.csumb.esotorodriguez.project1gradetracker;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class GradeCategoryViewModel extends AndroidViewModel {
    private GradeCategoryRepository repository;
    private LiveData<List<GradeCategory>> allGradeCategories;

    public GradeCategoryViewModel(@NonNull Application application) {
        super(application);
        repository = new GradeCategoryRepository(application);
        allGradeCategories = repository.getAllGradeCategories();
    }

    public void insert(GradeCategory gradeCategory) {
        repository.insert(gradeCategory);
    }

    public void update(GradeCategory gradeCategory) {
        repository.update(gradeCategory);
    }

    public void delete(GradeCategory gradeCategory) {
        repository.delete(gradeCategory);
    }

    public LiveData<List<GradeCategory>> getAllGradeCategories() {
        return allGradeCategories;
    }
}

