package com.class_project.stupa;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.class_project.model.TripPlan;

import java.util.ArrayList;
import java.util.List;

public class MyPlanAdapter extends RecyclerView.Adapter<MyPlanAdapter.ViewHolder> {

    List<TripPlan> tripList;

    List<String> place_name;
    List<String> location;
    List<Integer> image;
    LayoutInflater inflater;

    public MyPlanAdapter(Context context, List<String> place_name, List<String> location, List<Integer> image, List<TripPlan> tripList, RecyclerViewClickListener listener) {
        this.place_name = place_name;
        this.location = location;
        this.image = image;
        this.listener = listener;
        this.inflater = LayoutInflater.from(context);
        this.tripList = tripList;
    }

    RecyclerViewClickListener listener;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name;
        TextView loc;
        ImageView img;
        private MyPlanAdapter adapter;

        public ViewHolder(View itemView){
            super(itemView);
            name = itemView.findViewById(R.id.item_name);
            loc = itemView.findViewById(R.id.item_location);
            img = itemView.findViewById(R.id.item_image);
            itemView.findViewById(R.id.delete_plan).setOnClickListener(view -> {
                adapter.tripList.remove(getAdapterPosition());
                adapter.place_name.remove(getAdapterPosition());
                adapter.location.remove(getAdapterPosition());
                adapter.image.remove(getAdapterPosition());
                adapter.notifyItemRemoved(getAdapterPosition());
            });

//            itemView.setOnClickListener(this);
        }

        public ViewHolder linkAdapter(MyPlanAdapter adapter){
            this.adapter = adapter;
            return this;
        }

        @Override
        public void onClick(View view) {
            listener.onClick(view, getAdapterPosition());
        }
    }

    public interface RecyclerViewClickListener{
        void onClick(View v, int position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.template_plan_page, parent, false);
        return new ViewHolder(view).linkAdapter(this);
    }

    @Override
    public void onBindViewHolder(@NonNull MyPlanAdapter.ViewHolder holder, int position) {
        holder.img.setImageResource(image.get(position));
        holder.name.setText(place_name.get(position));
        holder.loc.setText(location.get(position));
    }

    @Override
    public int getItemCount() {
        return place_name.size();
    }
}