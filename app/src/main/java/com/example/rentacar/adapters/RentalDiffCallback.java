package com.example.rentacar.adapters;

import androidx.recyclerview.widget.DiffUtil;
import com.example.rentacar.database.models.Rental;
import java.util.List;

public class RentalDiffCallback extends DiffUtil.Callback {

    private final List<Rental> oldList;
    private final List<Rental> newList;

    public RentalDiffCallback(List<Rental> oldList, List<Rental> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition).getId() == newList.get(newItemPosition).getId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        Rental oldRental = oldList.get(oldItemPosition);
        Rental newRental = newList.get(newItemPosition);
        return oldRental.equals(newRental);
    }
}