package com.example.instagramclone;

import android.content.Context;
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

import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder>{
    private Context context;
    private List<Post> posts;

    public PostsAdapter(Context context, List<Post> posts){
        this.context = context;
        this.posts = posts;
    }


    @NonNull
    @Override
    public PostsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_post,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostsAdapter.ViewHolder holder, int position) {
        Post post = posts.get(position);
        holder.bind(post);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public void clear() {
        posts.clear();
        notifyDataSetChanged();
    }
    public void addAll(List<Post> newPosts){
        posts.addAll(newPosts);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivProfilePicture;
        private TextView tvUsername;
        private ImageView ivPicture;
        private ImageView ivLikes;
        private ImageView ivComments;
        private ImageView ivDirect;
        private ImageView ivSave;
        private TextView tvLikesCount;
        private TextView tvDescription;


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
            ParseFile image = post.getImage();
            ParseFile profileImage = post.getProfileImage();

            if(image != null){
                Glide.with(context).load(image.getUrl()).into(ivPicture);

            }
            if(profileImage != null){
                Glide.with(context).load(profileImage.getUrl()).into(ivProfilePicture);
            }

            tvDescription.setText(post.getDescription());
        }
    }
}

