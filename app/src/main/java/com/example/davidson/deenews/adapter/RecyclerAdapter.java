package com.example.davidson.deenews.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.davidson.deenews.R;
import com.example.davidson.deenews.fragments.DetailActivity;
import com.example.davidson.deenews.model.News;


import java.util.ArrayList;
import java.util.List;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private List<News> news;
    private Context context;

    public RecyclerAdapter(List<News> news, Context context) {
        this.news = news;
        this.context = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_news_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter.ViewHolder holder, int position) {
        holder.title.setText(news.get(position).getTitle());
        holder.authour.setText(news.get(position).getAuthor());
        Glide.with(context).load(news.get(position).getUrlToImage()).thumbnail(0.5f).into(holder.articleImage);

    }

    @Override
    public int getItemCount() {
        return news.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, authour ;
        ImageView articleImage;
        public ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.NewsTitle);
            articleImage = itemView.findViewById(R.id.imageView);
            authour = itemView.findViewById(R.id.Authur);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    News myNews = news.get(getAdapterPosition());

                    Context context = view.getContext();
                    Intent intent = new Intent(context, DetailActivity.class);
                    intent.putExtra("news_description" , myNews.getDescription());
                    intent.putExtra("news_title" , myNews.getTitle());
                    intent.putExtra("news_image", myNews.getUrlToImage());
                    intent.putExtra("time", myNews.getPublishedAt());
                    context.startActivity(intent);
                }
            });

        }
    }

    public void setFilter(List<News> current){
        news = new ArrayList<>();
          news.addAll(current);
        notifyDataSetChanged();
    }

}
