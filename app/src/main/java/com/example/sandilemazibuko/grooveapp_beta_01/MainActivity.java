package com.example.sandilemazibuko.grooveapp_beta_01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    LocalStorage userDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            Button btnGoToRegister,btnGoToLogin;



            btnGoToRegister = (Button) findViewById(R.id.btnRegister);
            btnGoToLogin = (Button) findViewById(R.id.btnLogin);

            btnGoToLogin.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this, Login.class);
                    startActivity(intent);
                }
            });

            btnGoToRegister.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this, Register.class);
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
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        userDatabase = new LocalStorage(this);

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Toast.makeText(MainActivity.this, "Settings", Toast.LENGTH_SHORT).show();
        }else if(id == R.id.action_about_this_app){
            Toast.makeText(MainActivity.this, "About This App", Toast.LENGTH_SHORT).show();
        }else if(id == R.id.action_instructions){
            Toast.makeText(MainActivity.this, "App Instructions", Toast.LENGTH_SHORT).show();
        }else if(id == R.id.action_faqs){
            Toast.makeText(MainActivity.this, "FAQ's", Toast.LENGTH_SHORT).show();
        }else if(id == R.id.action_logout){
            userDatabase.clearUserData();
            Intent intent = new Intent(MainActivity.this, MainActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }



}
