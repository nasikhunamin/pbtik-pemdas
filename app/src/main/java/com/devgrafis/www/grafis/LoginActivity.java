package com.devgrafis.www.grafis;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.devgrafis.www.grafis.api.Api;
import com.devgrafis.www.grafis.model.User;
import com.devgrafis.www.grafis.model.value;
import com.devgrafis.www.grafis.sharedpreferences.SaveSharedPreferences;

import java.io.InputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {
    private Button login, signup;
    public static final String URL = "https://grafisdev.000webhostapp.com/api/";
    private ProgressDialog progressDialog;
    private EditText email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = findViewById(R.id.loginButton);
        signup = findViewById(R.id.signupButton);
        email = findViewById(R.id.emailText);
        password = findViewById(R.id.passText);

        if(SaveSharedPreferences.getLoggedStatus(getApplicationContext()) == true){
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogin();
                //startActivity(new Intent(LoginActivity.this, MainActivity.class));
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(LoginActivity.this, SignupActivity.class));
            }
        });
    }

    private void userLogin() {
        final String emailFix = email.getText().toString();
        final String passwordFix = password.getText().toString();
        if (emailFix.isEmpty()){
            email.setError("E-mail is required");
            email.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(emailFix).matches()){
            email.setError("Enter a valid e-mail");
            email.requestFocus();
            return;
        }

        if (passwordFix.isEmpty()){
            password.setError("Address is required");
            password.requestFocus();
            return;
        }



        validLogin(emailFix, passwordFix);
    }

    private void validLogin(String emailFix, String passwordFix) {
        progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading");
        progressDialog.show();


        //Gson gson = new GsonBuilder().setLenient().create();
        /*OkHttpClient client = new OkHttpClient();*/

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                //.client(new OkHttpClient())
                //.addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setLenient().create()))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);
        Call<value> call = api.login(emailFix, passwordFix);
        Log.e("json",emailFix + " " + passwordFix);

        call.enqueue(new Callback<value>() {
            @Override
            public void onResponse(Call<value> call, Response<value> response) {
                try{
                    String value = response.body().getValue();
                    String message = response.body().getMessage();
                    progressDialog.dismiss();
                    if (value.equals(1)){
                        Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        //SaveSharedPreferences.setLoggedIn(getApplicationContext(), true);
                    }else{
                        Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    }
                }catch (Exception ex){
                    ex.printStackTrace();
                    progressDialog.dismiss();
                    Toast.makeText(LoginActivity.this, ex.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<value> call, Throwable t) {
                t.printStackTrace();
                progressDialog.dismiss();
                Toast.makeText(LoginActivity.this, "Network Error ! "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
