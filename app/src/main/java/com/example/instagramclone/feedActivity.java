package com.example.instagramclone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.List;

public class feedActivity extends AppCompatActivity {
    ImageView ivTakePicture;
    FeedsAdapter adapter;
    List<Post> posts;
    RecyclerView rv;
    public static final String TAG = "feedActivity";
    List<Post> myPosts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
        ivTakePicture = findViewById(R.id.ivTakePicture);
        rv = findViewById(R.id.rvFeed);
        rv.setLayoutManager(new LinearLayoutManager(this));
        queryPosts();
        adapter = new FeedsAdapter(this,myPosts);
        rv.setAdapter(adapter);


        ivTakePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //launch mainActivity
            }
        });
    }



    private void queryPosts() {
        ParseQuery<Post> query = ParseQuery.getQuery(Post.class);
        query.include(Post.KEY_USER);
        query.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> posts, ParseException e) {
                if(e != null){
                    Log.e(TAG,"Issue with getting posts",e);
                    return;
                }
                myPosts.addAll(posts);
                adapter.notifyDataSetChanged();
                for(Post post:posts){
                    Log.i(TAG,"Post: " + post.getDescription() + ", username: " + post.getUser().getUsername());
                }
            }
        });

    }
}