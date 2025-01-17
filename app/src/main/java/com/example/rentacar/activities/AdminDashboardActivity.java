package com.example.rentacar.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rentacar.R;

public class AdminDashboardActivity extends AppCompatActivity {
    private EditText vehicleName_txt;
    private EditText vehicleModel_txt;
    private final EditText price_txt;

    public AdminDashboardActivity(EditText priceTxt) {
        price_txt = priceTxt;
    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        // Initialize EditText fields
        vehicleName_txt = findViewById(R.id.vehicleName_txt);
        vehicleModel_txt = findViewById(R.id.vehicleModel_txt);
          // Added missing initialization

        Button buttonAddNewVehicle = findViewById(R.id.buttonAddNewVehicle);
        // Replace anonymous OnClickListener with lambda
        buttonAddNewVehicle.setOnClickListener(this::addNewVehicle);
    }

    public void addNewVehicle(View view) {
        // Get text from EditText fields
        String vehicleName = vehicleName_txt.getText().toString().trim();
        String vehicleModel = vehicleModel_txt.getText().toString().trim();

        // Validate vehicle name
        if(vehicleName.isEmpty()) {
            Toast.makeText(this, "Please input a name for the vehicle!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Validate vehicle model
        if(vehicleModel.isEmpty()) {
            Toast.makeText(this, "Please input a model for the vehicle!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Parse price with error handling
        try {
            String priceText = price_txt.getText().toString().trim();
            if(priceText.isEmpty()) {
                Toast.makeText(this, "Please input a price!", Toast.LENGTH_SHORT).show();
                return;
            }
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Please input a valid price!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Create new Vehicle object

        // TODO: Replace this with proper database integration
        // You need to implement your actual database manager here
        boolean success = saveVehicleToDatabase();

        if (!success) {
            Toast.makeText(this, "Failed to add vehicle!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Vehicle added successfully", Toast.LENGTH_SHORT).show();
            clearInputFields();
            reloadVehicleList();
        }
    }

    private boolean saveVehicleToDatabase() {
        // TODO: Implement your database logic here
        // Return true if successful, false otherwise
        return false;
    }

    private void clearInputFields() {
        vehicleName_txt.setText("");
        vehicleModel_txt.setText("");
        price_txt.setText("");
    }

    private void reloadVehicleList() {
        Intent intent = new Intent(this, AdminDashboardActivity.class);
        startActivity(intent);
        finish();  // Added to prevent activity stack buildup
    }
}