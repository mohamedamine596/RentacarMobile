package com.example.rentacar.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.rentacar.R;
import com.example.rentacar.database.models.Vehicle;
import java.util.List;

public class VehicleAdapter extends RecyclerView.Adapter<VehicleAdapter.VehicleViewHolder> {

    private List<Vehicle> vehicleList;

    public VehicleAdapter(List<Vehicle> vehicleList) {
        this.vehicleList = vehicleList;
    }

    @NonNull
    @Override
    public VehicleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_item_vehicle, parent, false);
        return new VehicleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VehicleViewHolder holder, int position) {
        Vehicle vehicle = vehicleList.get(position);
        holder.textViewVehicleName.setText(vehicle.getName());
        holder.textViewVehicleType.setText(vehicle.getType());
        holder.textViewDailyRate.setText(String.valueOf(vehicle.getDailyRate()));
        holder.textViewAvailability.setText(vehicle.isAvailable() ? "Available" : "Not Available");
    }

    @Override
    public int getItemCount() {
        return vehicleList.size();
    }

    public static class VehicleViewHolder extends RecyclerView.ViewHolder {
        TextView textViewVehicleName;
        TextView textViewVehicleType;
        TextView textViewDailyRate;
        TextView textViewAvailability;

        public VehicleViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewVehicleName = itemView.findViewById(R.id.textViewVehicleName);
            textViewVehicleType = itemView.findViewById(R.id.textViewVehicleType);
            textViewDailyRate = itemView.findViewById(R.id.textViewDailyRate);
            textViewAvailability = itemView.findViewById(R.id.textViewAvailability);
        }
    }
}