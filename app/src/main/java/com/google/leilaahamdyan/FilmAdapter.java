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

public class FilmAdapter extends RecyclerView.Adapter<FilmAdapter.ViewHolder> {


    ArrayList<FilmItem> filmItems;
    private LayoutInflater mInflater;
    Activity activity;

    public FilmAdapter(Activity activity, ArrayList<FilmItem> filmItems) {
        this.activity  = activity;
        this.filmItems = filmItems;
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
        FilmItem filmItem = filmItems.get(position);
        holder.tvTitle.setText(filmItem.getTitle());
        holder.tvLink.setText(filmItem.getLink());
        holder.rootLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(activity, filmItem.getTitle() + "", Toast.LENGTH_SHORT).show();
                String url = filmItem.getLink();
                if (!url.startsWith("http://") && !url.startsWith("https://"))
                    url = "http://" + url;
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                activity.startActivity(browserIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return filmItems.size();
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
