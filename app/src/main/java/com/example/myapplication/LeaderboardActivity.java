package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myapplication.data.ProfileManager;
import com.example.myapplication.db.DDBBUser;
import com.example.myapplication.db.User;

import java.util.ArrayList;
import java.util.Map;

public class LeaderboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        Button backBtn = findViewById(R.id.backBtn);

        updateRanking();
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(LeaderboardActivity.this, LandingActivity.class);
                startActivity(myIntent);
            }
        });
    }

    public void updateRanking(){
        ArrayList<User> myRankingText = new DDBBUser(LeaderboardActivity.this).selectRanking();


        TextView top1Name = findViewById(R.id.top1Text);
        TextView top1Points = findViewById(R.id.top1Points);

        TextView top2Name = findViewById(R.id.top2Text);
        TextView top2Points = findViewById(R.id.top2Points);

        TextView top3Name = findViewById(R.id.top3Text);
        TextView top3Points = findViewById(R.id.top3Points);

        top1Name.setText(myRankingText.get(0).username);
        String aux = ""+myRankingText.get(0).puntos;
        top1Points.setText(aux);

        top2Name.setText(myRankingText.get(1).username);
        aux = "" + myRankingText.get(1).puntos;
        top2Points.setText(aux);

        top3Name.setText(myRankingText.get(2).username);
        aux = "" + myRankingText.get(2).puntos;
        top3Points.setText(aux);

    }


}