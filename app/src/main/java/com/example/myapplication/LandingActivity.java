package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.data.ProfileManager;
import com.example.myapplication.db.DDBBUser;

public class LandingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        System.out.println(""+ ProfileManager.getPoints());

        TextView userName = findViewById(R.id.lb_Username);
        userName.setText(ProfileManager.getUsername());

        updateScreen();

        Button recycleBtn = findViewById(R.id.btn_Recycle);
        Button leaderBoardBtn = findViewById(R.id.btn_Leaderboard);
        Button logOutBtn = findViewById(R.id.btn_LogOut);

        recycleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProfileManager.updatePoints(ProfileManager.getPoints()+10, LandingActivity.this);
                updateScreen();
            }
        });

        logOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProfileManager.Reset();
                Intent myIntent = new Intent(LandingActivity.this, MainActivity.class);
                startActivity(myIntent);
            }
        });

        leaderBoardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(LandingActivity.this, LeaderboardActivity.class);
                startActivity(myIntent);
            }
        });

    }

    private void updateScreen(){
        TextView pointCounter = findViewById(R.id.lb_Points);
        pointCounter.setText(""+ProfileManager.getPoints());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mainmenuloged, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.mi_removeAcc:
                DDBBUser myDB = new DDBBUser(LandingActivity.this);
                myDB.deleteRow();

                Toast.makeText(LandingActivity.this, "Account removed", Toast.LENGTH_SHORT).show();

                ProfileManager.Reset();
                Intent myIntent = new Intent(LandingActivity.this, MainActivity.class);
                startActivity(myIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}