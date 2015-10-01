package com.example.sandilemazibuko.grooveapp_beta_01;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;


public class Login extends AppCompatActivity {


    EditText txtEmail,txtPassword;
    String email = null,password = null;

    // Progress Dialog
    private ProgressDialog pDialog;

    LocalStorage userDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userDatabase = new LocalStorage(this);

         txtEmail = (EditText) findViewById(R.id.txtemail);
         txtPassword = (EditText) findViewById(R.id.txtpass);

         email = txtEmail.getText().toString();
         password = txtPassword.getText().toString();

        Button btnLogin = (Button) findViewById(R.id.btnlog);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                txtEmail = (EditText) findViewById(R.id.txtemail);
                txtPassword = (EditText) findViewById(R.id.txtpass);

                email = txtEmail.getText().toString();
                password = txtPassword.getText().toString();



                if(!email.equals("")){
                    if(!password.equals("")){
                        if(isOnline()){
                            String[] myTaskParams = {email,password};

                            new LoginTask().execute(myTaskParams);

                        }else{
                            Toast.makeText(Login.this,"Your Device is not Connected",Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(Login.this,"Please Enter Password",Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(Login.this,"Please Enter Email",Toast.LENGTH_SHORT).show();
                }


            }
        });



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

    private class LoginTask extends AsyncTask<String, String, JSONObject> {

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

        @Override
        protected JSONObject doInBackground(String... params) {
            OkHttpClient client = new OkHttpClient();

            String url = "http://groovapp.msentri.co.za/get_user.php";


            RequestBody formBody = new FormEncodingBuilder()
                    .add("email", params[0])
                    .add("password", params[1])
                    .build();

            Request request = new Request.Builder()
                    .url(url)
                    .post(formBody)
                    .build();

            Response response = null;
            JSONObject Jobject = null;
            try {
                response = client.newCall(request).execute();
                String StringRespons = response.body().string();
                Jobject = new JSONObject(StringRespons);

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }


            return Jobject;
        }

        @Override
        protected void onPostExecute(JSONObject result) {
            // dismiss the dialog after getting all products
            pDialog.dismiss();

                JSONArray Jarray = null;
            try {
                Jarray = result.getJSONArray("user");
                JSONObject object = Jarray.getJSONObject(0);






                if(!object.getString("name").equals("")) {
                    Toast.makeText(Login.this, object.getString("name"), Toast.LENGTH_SHORT).show();

                    //setContentView(R.layout.activity_profile);
//                    String user_id = object.getString("user_id");
//                    String user_name = object.getString("name");
//                    String user_surname = object.getString("surname");
//                    String user_picture = object.getString("user_picture");
//                    String user_password = object.getString("user_password");
//                    String user_email = object.getString("user_email");
//                    String user_dob = object.getString("user_dob");
//                    String user_membership_type = object.getString("user_membership_type");
//
//                    User user = new User(user_id,user_name,user_surname,user_picture,user_password,
//                            user_email,user_dob,user_membership_type);
//                    userDatabase.storeUserDetailsOnPreference(user);


                    Intent intent = new Intent(Login.this, Profile.class);
                    startActivity(intent);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }





}
