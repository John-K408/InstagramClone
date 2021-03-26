package com.example.instagramclone.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.instagramclone.LoginActivity;
import com.example.instagramclone.Post;
import com.example.instagramclone.PostsAdapter;
import com.example.instagramclone.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;


// A simple {@link Fragment} subclass.

public class PostFragment extends Fragment {
   private RecyclerView rvPosts;
   public static final String TAG = "PostFragment";
   protected PostsAdapter adapter;
   protected List<Post> allPosts;
   ImageView instagramLogo;
   SwipeRefreshLayout swipeContainer;




    public PostFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_post, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvPosts = view.findViewById(R.id.rvPosts);
        instagramLogo = view.findViewById(R.id.ivInstagramLogo);
        swipeContainer = view.findViewById(R.id.swipeContainer);
        allPosts = new ArrayList<>();
        adapter = new PostsAdapter(getContext(),allPosts);





        rvPosts.setAdapter(adapter);
        rvPosts.setLayoutManager(new LinearLayoutManager(getContext()));


        instagramLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ParseUser.logOut();
                goToLoginActivity();
            }
        });
        queryPosts();

        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Your code to refresh the list here.
                Log.i("PostFragment","About to query posts");
                queryPosts();
                // Make sure you call swipeContainer.setRefreshing(false)
                swipeContainer.setRefreshing(false);
                // once the network request has completed successfully.
                //fetchTimelineAsync(0);

            }
        });

    }

    protected void queryPosts() {
        ParseQuery<Post> query = ParseQuery.getQuery(Post.class);
        Log.i("PostFragment","Querying everything");
        query.include(Post.KEY_USER);
        query.setLimit(20);
        query.addDescendingOrder(Post.KEY_CREATED);
        query.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> posts, ParseException e) {
                if(e != null) {
                    Log.e(TAG, "Issue with getting posts", e);
                    return;
                }

//                for(Post post:posts){
//
//                    Log.i(TAG,"Post: " + post.getDescription() + ", username: " + post.getUser().getUsername());
//                }
                adapter.clear();
                adapter.addAll(posts);
                adapter.notifyDataSetChanged();


            }
        });

    }

    private void goToLoginActivity(){
        Intent intent  =  new Intent(getContext(), LoginActivity.class);
        startActivity(intent);
        getActivity().finish();
    }
}