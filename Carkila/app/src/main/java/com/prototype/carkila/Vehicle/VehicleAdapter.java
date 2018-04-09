package com.prototype.carkila.Vehicle;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.prototype.carkila.R;

import java.util.List;


public class VehicleAdapter extends RecyclerView.Adapter<VehicleAdapter.VehicleViewHolder> {


    private Context mCtx;
    private List<Vehicle> VehicleList;

    public VehicleAdapter (Context mCtx, List<Vehicle> VehicleList) {
        this.mCtx = mCtx;
        this.VehicleList = VehicleList;
    }

    @Override
    public VehicleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.activity_vehicle_list, null);
        return new VehicleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(VehicleViewHolder holder, int position) {
        final Vehicle vehicle = VehicleList.get(position);

        //loading the image
        Glide.with(mCtx)
                .load(vehicle.getImage())
                .into(holder.imageView);

/*      holder.textViewMake.setText(vehicle.getMake());
        holder.textViewModel.setText(vehicle.getModel());
        holder.textViewCapacity.setText(vehicle.getSeatingCapacity() + " persons");*/
        holder.textViewRentalRate.setText("₱" + vehicle.getRentalRate());

        holder.BtnVehicle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(mCtx, Vehicle_details.class);


                i.putExtra("CarImage", vehicle.getImage());
                i.putExtra("CarModel", vehicle.getModel());
                i.putExtra("CarMake", vehicle.getMake());
                i.putExtra("CarYear", vehicle.getYear());
                i.putExtra("CarCapacity", vehicle.getSeatingCapacity());
                i.putExtra("CarRate", "₱" + vehicle.getRentalRate());
                mCtx.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return VehicleList.size();
    }

    class VehicleViewHolder extends RecyclerView.ViewHolder {

        TextView textViewRentalRate;
        ImageView imageView;

        LinearLayout BtnVehicle;

        public VehicleViewHolder (View itemView) {
            super(itemView);

/*            textViewMake = itemView.findViewById(R.id.textMake);
            textViewModel = itemView.findViewById(R.id.textModel);
            textViewCapacity = itemView.findViewById(R.id.textCapacity);*/
            textViewRentalRate = itemView.findViewById(R.id.textRentalRate);
            imageView = itemView.findViewById(R.id.imageView);

            BtnVehicle = itemView.findViewById(R.id.BtnVehicle);

        }
    }
}