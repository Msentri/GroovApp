package com.example.sandilemazibuko.grooveapp_beta_01;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Login extends AppCompatActivity {

    EditText txtuser,txtpass;
    Button btnLog;
    JSONObject saObj;

    // Progress Dialog
    private ProgressDialog pDialog;

    LocalStorage userDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        

        userDatabase = new LocalStorage(this);

        btnLog = (Button) findViewById(R.id.btnlog);

        txtuser = (EditText) findViewById(R.id.txtuser);
        txtpass = (EditText) findViewById(R.id.txtpass);



        btnLog.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (!txtpass.getText().toString().equals("")) {
                    if (!txtuser.getText().toString().equals("") ) {
                        if (isOnline()) {
                            requestData("http://groovapp.msentri.co.za/get_user.php",txtuser.getText().toString(),txtpass.getText().toString() );
                        } else {
                            Toast.makeText(Login.this, "Network isn't available", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        //txtpass.setError("Please Enter Password");
                        Toast.makeText(Login.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    //txtuser.setError("Please Enter Username");
                    Toast.makeText(Login.this,"Please Enter Username",Toast.LENGTH_SHORT).show();;

                }
            }
        });
    }

    private void requestData(String uri, String username,String password) {

        RequestPackage p = new RequestPackage();
        p.setMethod("POST");
        p.setUri(uri);
        p.setParam("username", username);
        p.setParam("password", password);


        MyTask task = new MyTask();
        task.execute(p);
    }

    protected boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * THESE PRIVATE CLASS PROCESS THE CONNECTION FROM ONLINE WEB SERVICE SERVER TO ACCESS
     * ONLINE DATABASE DOING IT IN THE BACKGROUND THREAD
     * */
    private class MyTask extends AsyncTask<RequestPackage, String, JSONObject> {

        /**
         * THESE FUNCTION PROCESS INFORMATION FROM FIRST TIME BEFORE RUNNING THE DO BACKGROUND TASK
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Login.this);
            pDialog.setMessage("User Login Please wait...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        /**
         * THESE FUNCTION PROCESS DOES THE WHOLE FUNCTIONALITY ON ACCESSING THE ONLINE INFORMATION
         * THAT IS BEING GENERATED AS JSON FORMAT AND PRESENT IT TO NODE FORMAT
         * */
        @Override
        protected JSONObject doInBackground(RequestPackage... params) {
            String content = HttpManager.getData(params[0]);
            try {
                 saObj = new JSONObject(content);
                //Log.v("******************************************* -  ", saObj.toString());

            } catch (JSONException e) {
                e.printStackTrace();
            }
            return saObj;
        }

        @Override
        protected void onPostExecute(JSONObject result) {
            // dismiss the dialog after getting all products
            pDialog.dismiss();

            try {
                String SERVER_RESPOND = result.getString("success");
                if(!SERVER_RESPOND.equals("0")){

                    JSONArray jsonArray = result.getJSONArray("user");
                    JSONObject finalJSONOBArray = jsonArray.getJSONObject(0);

                    String user_id = finalJSONOBArray.getString("user_id");
                    String user_name = finalJSONOBArray.getString("name");
                    String user_surname = finalJSONOBArray.getString("surname");
                    String user_id_number = finalJSONOBArray.getString("id_number");
                    String user_email = finalJSONOBArray.getString("email");
                    String user_cellphone = finalJSONOBArray.getString("cellphone");
                    String user_username = finalJSONOBArray.getString("username");
                    String password = finalJSONOBArray.getString("password");

                    User user = new User(user_id,user_name,user_surname,user_id_number,user_email,
                            user_cellphone,user_username,password);
                    userDatabase.storeUserDetailsOnPreference(user);

                    //Toast.makeText(Login.this,"User Logged in",Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(Login.this, Profile.class);
                    startActivity(intent);

                    //Log.v("--------------------------------------- ", user_cellphone);
                    //Toast.makeText(Login.this,user_email,Toast.LENGTH_LONG).show();


                }else{
                    Toast.makeText(Login.this,"User Not Found",Toast.LENGTH_LONG).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

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
        userDatabase = new LocalStorage(this);


        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Toast.makeText(Login.this, "Settings", Toast.LENGTH_SHORT).show();
        }else if(id == R.id.action_about_this_app){
            Toast.makeText(Login.this, "About This App", Toast.LENGTH_SHORT).show();
        }else if(id == R.id.action_instructions){
            Toast.makeText(Login.this, "App Instructions", Toast.LENGTH_SHORT).show();
        }else if(id == R.id.action_faqs){
            Toast.makeText(Login.this, "FAQ's", Toast.LENGTH_SHORT).show();
        }else if(id == R.id.action_logout){
            userDatabase.clearUserData();
            Intent intent = new Intent(Login.this, MainActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
