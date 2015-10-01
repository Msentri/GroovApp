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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.nispok.snackbar.Snackbar;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class Register extends AppCompatActivity {

    EditText txtname,txtsurname,txtemail,txtusername,txtpassword,txtcellphone,txtyear;
    String respond = "";

    Button btnGoToRegister;

    LocalStorage userDatabase;


    Spinner spMonth,spDay,spType;


    // Progress Dialog
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        /**
         * DATE OF BIRTH AREA.....
         */
        spMonth = (Spinner) findViewById(R.id.spMonths);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.month,android.R.layout.simple_spinner_item);
        spMonth.setAdapter(adapter);

        spDay = (Spinner) findViewById(R.id.spDay);
        ArrayAdapter adapter1 = ArrayAdapter.createFromResource(this, R.array.day,android.R.layout.simple_spinner_item);
        spDay.setAdapter(adapter1);

        /**
         * DATE OF BIRTH AREA.....
         */


        spType = (Spinner) findViewById(R.id.spType);
        ArrayAdapter adapterSP_type = ArrayAdapter.createFromResource(this, R.array.type,android.R.layout.simple_spinner_item);
        spType.setAdapter(adapterSP_type);





        txtname = (EditText) findViewById(R.id.txtname);
        txtsurname = (EditText) findViewById(R.id.txtsurname);
        txtemail = (EditText) findViewById(R.id.txtemail);

        txtpassword = (EditText) findViewById(R.id.txtpassword);
        txtyear = (EditText) findViewById(R.id.txtYear);

        btnGoToRegister = (Button) findViewById(R.id.btnReg);

        btnGoToRegister.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (isOnline()) {
                    if(!spMonth.getSelectedItem().toString().equals("Month")) {
                        if(!spDay.getSelectedItem().toString().equals("Day")) {
                            if(!txtyear.getText().toString().equals("Year")) {
                                if(!spType.getSelectedItem().toString().equals("None")) {
                                    new RegisterTask().execute();
                                }else{
                                    Toast.makeText(Register.this, "Please Select Membership Type", Toast.LENGTH_LONG).show();
                                }
                            }else{
                                Toast.makeText(Register.this, "Please Enter Numeric Year you were born at.", Toast.LENGTH_LONG).show();
                            }
                        }else {
                            Toast.makeText(Register.this, "Please Select Day you were born at.", Toast.LENGTH_LONG).show();
                        }
                    }else{
                        Toast.makeText(Register.this, "Please Select Month you were born at.", Toast.LENGTH_LONG).show();
                    }
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

    private class RegisterTask extends AsyncTask<String, String, String>{

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
        protected String doInBackground(String... params) {



            String obj = Create_user();
            String test = null;

            try {
                JSONObject Jobject = new JSONObject(obj);
                test = Jobject.getString("message");
            } catch (JSONException e) {
                e.printStackTrace();
            }


            //Log.v("--------------------------------------- ", obj);
            return test;
        }

        @Override
        protected void onPostExecute(String result) {
            // dismiss the dialog after getting all products
            pDialog.dismiss();

            if(result != null){
                Toast.makeText(Register.this, result, Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(Register.this, "User Not Register Successfully", Toast.LENGTH_LONG).show();
            }
        }
    }

    public String Create_user(){

        txtname = (EditText) findViewById(R.id.txtname);
        txtsurname = (EditText) findViewById(R.id.txtsurname);
        txtemail = (EditText) findViewById(R.id.txtemail);

        txtpassword = (EditText) findViewById(R.id.txtpassword);
        txtcellphone = (EditText) findViewById(R.id.txtcellphone);

        txtyear = (EditText) findViewById(R.id.txtYear);

        String name = txtname.getText().toString();
        String surname = txtsurname.getText().toString();
        String email = txtemail.getText().toString();
        String password  = txtpassword.getText().toString();
        String cellphone = txtcellphone.getText().toString();
        String year = txtyear.getText().toString();



        /**
         * DATE OF BIRTH AREA.....
         */
            String dob = spDay.getSelectedItem().toString() + "-" + spMonth.getSelectedItem().toString() + "-" + year;
        /**
         * DATE OF BIRTH AREA.....
         */


        OkHttpClient client = new OkHttpClient();

        String url = "http://groovapp.msentri.co.za/Register.php";

        String obj = "";

        String type = "";

        String spTypes = spType.getSelectedItem().toString();

        if(spTypes.equals("Black Opening")){
            type = "1";
        }else if(spTypes.equals("Gold")){
            type = "2";
        }else if(spTypes.equals("Platinum")){
            type = "3";
        }

        RequestBody formBody = new FormEncodingBuilder()
                .add("name", name)
                .add("surname", surname)
                .add("password", password)
                .add("id", "1991")
                .add("cellphone", cellphone)
                .add("email", email)
                .add("dob", dob)
                .add("type", type)
                .build();

        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();

        Response response = null;
        try {

            response = client.newCall(request).execute();
            obj= response.body().string();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return obj;
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
