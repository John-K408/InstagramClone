package com.example.instagramclone;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class feedActivity extends AppCompatActivity {
    ImageView ivTakePicture;
    FeedsAdapter adapter;
    RecyclerView rvPosts;
    public static final String TAG = "feedActivity";
    List<Post> myPosts;
    public static final int REQUEST_CODE = 22;
    SwipeRefreshLayout swipeContainer;
    ImageView ivInstagramLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
        myPosts = new ArrayList<>();
        queryPosts();
        ivTakePicture = findViewById(R.id.ivTakePicture);
        rvPosts = findViewById(R.id.rvFeed);
        swipeContainer = findViewById(R.id.swipeContainer);
        ivInstagramLogo = findViewById(R.id.ivInstagramLogo);
        // Configure the refreshing colors
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.i(TAG,"fetching new data");
                queryPosts();
            }
        });
        rvPosts.setLayoutManager(new LinearLayoutManager(this));
        adapter = new FeedsAdapter(this,myPosts);
        rvPosts.setAdapter(adapter);


        ivTakePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //launch mainActivity
                goMainActivity();
            }
        });

        //logout when user clicks instagram logo
        ivInstagramLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ParseUser.logOut();
                goLogInActivity();
            }
        });
    }

    private void goLogInActivity() {
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
    }


    private void queryPosts() {
        ParseQuery<Post> query = ParseQuery.getQuery(Post.class);
        query.include(Post.KEY_USER);
        query.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> posts, ParseException e) {
                if(e != null) {
                    Log.e(TAG, "Issue with getting posts", e);
                    return;
                }
                swipeContainer.setRefreshing(false);
                myPosts.clear();
                for(Post post:posts){
                    if(ParseUser.getCurrentUser().getUsername().equals(post.getUser().getUsername()))myPosts.add(post);
                   Log.i(TAG, ParseUser.getCurrentUser().getUsername() + " " + post.getUser().getUsername());

                    Log.i(TAG,"Post: " + post.getDescription() + ", username: " + post.getUser().getUsername());
                }
                adapter.notifyDataSetChanged();

            }
        });

    }

    private void goMainActivity() {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//
//        Log.i(TAG,"About to query post");
//        if(requestCode == REQUEST_CODE && resultCode == RESULT_OK){
//            queryPosts();
////            adapter.notifyDataSetChanged();
//        }
//        else{
//            Log.i(TAG,"Did not query posts");
//        }
//        super.onActivityResult(requestCode, resultCode, data);
//
//    }

}