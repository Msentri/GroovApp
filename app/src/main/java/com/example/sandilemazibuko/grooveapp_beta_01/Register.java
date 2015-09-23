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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Register extends AppCompatActivity {

    EditText txtname,txtsurname,txtemail,txtid,txtusername,txtpassword,txtcellphone;
    String respond = "";

    Button btnGoToRegister;

    LocalStorage userDatabase;


    // Progress Dialog
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        txtname = (EditText) findViewById(R.id.txtname);
        txtsurname = (EditText) findViewById(R.id.txtsurname);
        txtemail = (EditText) findViewById(R.id.txtemail);
        txtid = (EditText) findViewById(R.id.txtidnumber);
        txtusername = (EditText) findViewById(R.id.txtusername);
        txtpassword = (EditText) findViewById(R.id.txtpassword);

        btnGoToRegister = (Button) findViewById(R.id.btnReg);

        btnGoToRegister.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (isOnline()) {
                    requestData("http://groovapp.msentri.co.za/create_user.php");
                } else {
                    Toast.makeText(Register.this, "Network isn't available", Toast.LENGTH_LONG).show();
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

    private void requestData(String uri) {

        txtname = (EditText) findViewById(R.id.txtname);
        txtsurname = (EditText) findViewById(R.id.txtsurname);
        txtemail = (EditText) findViewById(R.id.txtemail);
        txtid = (EditText) findViewById(R.id.txtidnumber);
        txtusername = (EditText) findViewById(R.id.txtusername);
        txtpassword = (EditText) findViewById(R.id.txtpassword);
        txtcellphone = (EditText) findViewById(R.id.txtcellphone);

        String name = txtname.getText().toString();
        String surname = txtsurname.getText().toString();
        String email = txtemail.getText().toString();
        String age = txtid.getText().toString();
        String username = txtusername.getText().toString();
        String password  = txtpassword.getText().toString();
        String cellphone = txtcellphone.getText().toString();

        RequestPackage p = new RequestPackage();
        p.setMethod("GET");
        p.setUri(uri);
        p.setParam("name", name);
        p.setParam("surname", surname);
        p.setParam("email", email);
        p.setParam("id", age);
        p.setParam("username", username);
        p.setParam("password", password);
        p.setParam("cellphone", cellphone);



        MyTask task = new MyTask();
        task.execute(p);
    }



    private class MyTask extends AsyncTask<RequestPackage, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Register.this);
            pDialog.setMessage("Registering New User Please wait...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected String doInBackground(RequestPackage... params) {
            String content = HttpManager.getData(params[0]);




            try {
                JSONObject saObj = new JSONObject(content);

                respond = saObj.getString("success");
                Log.v("TEST PROCESS ???? -  ", saObj.getString("success"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return respond;
        }

        @Override
        protected void onPostExecute(String result) {
            // dismiss the dialog after getting all products
            pDialog.dismiss();

            if(result.equals("1")) {
                Intent goToLogin = new Intent(Register.this, Login.class);
                startActivity(goToLogin);


                //Toast.makeText(Register.this, "User Register Successfully", Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(Register.this, "User Not Register Successfully", Toast.LENGTH_LONG).show();
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
            Toast.makeText(Register.this, "Settings", Toast.LENGTH_SHORT).show();
        }else if(id == R.id.action_about_this_app){
            Toast.makeText(Register.this, "About This App", Toast.LENGTH_SHORT).show();
        }else if(id == R.id.action_instructions){
            Toast.makeText(Register.this, "App Instructions", Toast.LENGTH_SHORT).show();
        }else if(id == R.id.action_faqs){
            Toast.makeText(Register.this, "FAQ's", Toast.LENGTH_SHORT).show();
        }else if(id == R.id.action_logout){
            userDatabase.clearUserData();
            Intent intent = new Intent(Register.this, MainActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }




}
