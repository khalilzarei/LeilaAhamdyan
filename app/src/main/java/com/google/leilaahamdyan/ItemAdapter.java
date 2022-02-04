package com.google.leilaahamdyan;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {


    ArrayList<Item> items;
    private LayoutInflater mInflater;
    Activity activity;

    public ItemAdapter(Activity activity, ArrayList<Item> items) {
        this.activity  = activity;
        this.items     = items;
        this.mInflater = LayoutInflater.from(activity);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_row_film, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Item item = items.get(position);
        holder.tvTitle.setText(item.getTitle());
        holder.tvLink.setText(item.getLink());
        holder.rootLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(activity, item.getTitle() + "", Toast.LENGTH_SHORT).show();
                String url = item.getLink();
                if (!url.startsWith("http://") && !url.startsWith("https://"))
                    url = "http://" + url;
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                activity.startActivity(browserIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvLink;
        LinearLayout rootLayout;

        public ViewHolder(@NonNull View view) {
            super(view);
            tvTitle    = view.findViewById(R.id.tvTitle);
            tvLink     = view.findViewById(R.id.tvLink);
            rootLayout = view.findViewById(R.id.rootLayout);
        }
    }
}
