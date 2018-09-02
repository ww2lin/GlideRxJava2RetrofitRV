package com.example.alexlin.sample;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.alexlin.sample.model.RedditPost;
import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {
    private List<RedditPost> data = new ArrayList<>();
    private MainActivity.Callback callback;

    public void setCallback(MainActivity.Callback callback) {
        this.callback = callback;
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public ImageView image;
        public TextView text;
        public MyViewHolder(View v) {
            super(v);
            image = v.findViewById(R.id.image);
            text = v.findViewById(R.id.text);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public void setData(List<RedditPost> data) {
        this.data = data;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.panel, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        Glide.with(holder.itemView).load(data.get(position).thumbnail).into(holder.image);
        holder.text.setText(data.get(position).title);
        holder.itemView.setOnClickListener((e) -> callback.onClick(data.get(position)));
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }
}
