package com.example.sandilemazibuko.grooveapp_beta_01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Profile extends AppCompatActivity {

    LocalStorage userDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        /**
         * go to map fragment
         * */
        Button goToMap = (Button) findViewById(R.id.btnMap);
        goToMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profile.this, MapsActivity.class);
                startActivity(intent);
            }
        });

    }

    /**
     * -------------------------------------------------------------------------------------------------------------
     * MENU NAVIGATION
     * -------------------------------------------------------------------------------------------------------------
     * */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        userDatabase = new LocalStorage(this);

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Toast.makeText(Profile.this, "Settings", Toast.LENGTH_SHORT).show();
        }else if(id == R.id.action_about_this_app){
            Toast.makeText(Profile.this, "About This App", Toast.LENGTH_SHORT).show();
        }else if(id == R.id.action_instructions){
            Toast.makeText(Profile.this, "App Instructions", Toast.LENGTH_SHORT).show();
        }else if(id == R.id.action_faqs){
            Toast.makeText(Profile.this, "FAQ's", Toast.LENGTH_SHORT).show();
        }else if(id == R.id.action_logout){
            userDatabase.clearUserData();
            Intent intent = new Intent(Profile.this, MainActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
