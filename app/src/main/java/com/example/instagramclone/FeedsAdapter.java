package com.example.instagramclone;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.parse.ParseFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FeedsAdapter extends RecyclerView.Adapter<FeedsAdapter.ViewHolder>{
    Context context;
    List<Post> posts;

    public FeedsAdapter(Context context, List<Post> posts){
        this.context = context;
        this.posts = posts;
        Toast.makeText(context,posts.size() + "",Toast.LENGTH_LONG).show();
    }


    @NonNull
    @Override
    public FeedsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_post,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FeedsAdapter.ViewHolder holder, int position) {
        Post post = posts.get(position);
        holder.bind(post);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivProfilePicture;
        TextView tvUsername;
        ImageView ivPicture;
        ImageView ivLikes;
        ImageView ivComments;
        ImageView ivDirect;
        ImageView ivSave;
        TextView tvLikesCount;
        TextView tvDescription;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivProfilePicture = itemView.findViewById(R.id.ivProfileImage);
            tvUsername = itemView.findViewById(R.id.tvUsername);
            ivPicture = itemView.findViewById(R.id.ivPicture);
            ivLikes = itemView.findViewById(R.id.ivLikes);
            ivComments = itemView.findViewById(R.id.ivComments);
            ivDirect = itemView.findViewById(R.id.ivDirect);
            ivSave = itemView.findViewById(R.id.ivSave);
            tvLikesCount = itemView.findViewById(R.id.tvLikesCount);
            tvDescription = itemView.findViewById(R.id.tvDescription);
        }

        public void bind(Post post) {
            tvUsername.setText(post.getUser().getUsername());
            //fill imageView with post's image
            //Glide.with(context).load(post.getImage()).into(ivPicture);
            tvDescription.setText(post.getDescription());
        }
    }
}

