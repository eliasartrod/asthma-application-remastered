package com.example.asthmaapplication.main.repository;

import android.app.Application;

import androidx.annotation.NonNull;

import com.example.asthmaapplication.main.model.AsthmaInfoModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class FirebaseDatabaseRepository extends Application {
    private Application application;
    private FirebaseDatabase database;
    private DatabaseReference reference;
    private List<AsthmaInfoModel> models = new ArrayList<>();

    public interface DataStatus {
        void DataIsLoaded(List<AsthmaInfoModel> models, List<String> keys);
    }

    @Inject
    public FirebaseDatabaseRepository(Application application) {
        this.application = application;
        this.database = FirebaseDatabase.getInstance();
    }

    public void getSection(String sectionNumber) {
        reference = database.getReference(sectionNumber);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                models.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    AsthmaInfoModel model = dataSnapshot.getValue(AsthmaInfoModel.class);
                    models.add(model);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
