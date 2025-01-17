package com.example.rentacar.activities;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.rentacar.R;
import com.example.rentacar.database.DatabaseHelper;
import com.example.rentacar.database.models.Rental;
import java.util.List;

public class RentalHistoryActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RentalAdapter rentalAdapter;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rental_history);

        recyclerView = findViewById(R.id.recyclerViewRentalHistory);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        databaseHelper = new DatabaseHelper(this);
        List<Rental> rentalList = databaseHelper.getAllRentals();

        rentalAdapter = new RentalAdapter(rentalList);
        recyclerView.setAdapter(rentalAdapter);
    }
}