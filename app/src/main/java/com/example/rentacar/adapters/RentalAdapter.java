package com.example.rentacar.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.example.rentacar.R;
import com.example.rentacar.database.models.Rental;
import java.util.List;

public class RentalAdapter extends RecyclerView.Adapter<RentalAdapter.RentalViewHolder> {

    private List<Rental> rentalList;

    public RentalAdapter(List<Rental> rentalList) {
        this.rentalList = rentalList;
    }

    @NonNull
    @Override
    public RentalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_item_rental, parent, false);
        return new RentalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RentalViewHolder holder, int position) {
        Rental rental = rentalList.get(position);
        // Bind data to the views here
    }

    @Override
    public int getItemCount() {
        return rentalList.size();
    }

    public void updateRentalList(List<Rental> newRentalList) {
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new RentalDiffCallback(this.rentalList, newRentalList));
        this.rentalList.clear();
        this.rentalList.addAll(newRentalList);
        diffResult.dispatchUpdatesTo(this);
    }

    public static class RentalViewHolder extends RecyclerView.ViewHolder {
        // Define view elements here

        public RentalViewHolder(@NonNull View itemView) {
            super(itemView);
            // Initialize view elements here
        }
    }
}