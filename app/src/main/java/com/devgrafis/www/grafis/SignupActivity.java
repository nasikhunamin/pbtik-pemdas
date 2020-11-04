package com.devgrafis.www.grafis;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.devgrafis.www.grafis.api.Api;
import com.devgrafis.www.grafis.model.value;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignupActivity extends AppCompatActivity {
    private EditText getName, getAddress, getPhone, getEmail, getPass;
    private Button signup;

    public static final String URL = "https://grafisdev.000webhostapp.com/api/";
    //private static final String URL = "http://192.168.1.64/grafis/";
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        //ButterKnife.bind(this);

        getName = findViewById(R.id.nameText);
        getAddress = findViewById(R.id.addressText);
        getPhone = findViewById(R.id.phoneText);
        getEmail = findViewById(R.id.emailText);
        getPass = findViewById(R.id.passText);

        signup = findViewById(R.id.signupButtonv2);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               signupNow();
            }
        });
    }

    private void signupNow(){
        String name = getName.getText().toString();
        String address = getAddress.getText().toString();
        String email = getEmail.getText().toString();
        String phone = getPhone.getText().toString();
        String pass = getPass.getText().toString();

        if (name.isEmpty()){
            getName.setError("E-mail is required");
            getName.requestFocus();
            return;
        }

        if (address.isEmpty()){
            getAddress.setError("Address is required");
            getAddress.requestFocus();
            return;
        }

        if (email.isEmpty()){
            getEmail.setError("E-mail is required");
            getEmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            getEmail.setError("Enter a valid e-mail");
            getEmail.requestFocus();
            return;
        }

        if (phone.isEmpty()){
            getPhone.setError("Phone is required");
            getPhone.requestFocus();
            return;
        }

        if (pass.isEmpty()){
            getPass.setError("Password is required");
            getPass.requestFocus();
            return;
        }
        progressDialog = new ProgressDialog(SignupActivity.this);
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
        Call<value> call = api.register(name, email, address, phone, pass);
        Log.e("json",name + " " + email + " " + address + " " + phone + " " + pass);

        call.enqueue(new Callback<value>() {
            @Override
            public void onResponse(Call<value> call, Response<value> response) {
                try{
                    String value = response.body().getValue();
                    String message = response.body().getMessage();
                    progressDialog.dismiss();
                    if (value.equals(1)){
                        Toast.makeText(SignupActivity.this, message, Toast.LENGTH_SHORT).show();
                        finish();
                        startActivity(new Intent(SignupActivity.this, LoginActivity.class));
                    }else{
                        Toast.makeText(SignupActivity.this, message, Toast.LENGTH_SHORT).show();
                        finish();
                        startActivity(new Intent(SignupActivity.this, LoginActivity.class));
                    }
                }catch (Exception ex){
                    ex.printStackTrace();
                    progressDialog.dismiss();
                    Toast.makeText(SignupActivity.this, ex.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<value> call, Throwable t) {
                t.printStackTrace();
                progressDialog.dismiss();
                Toast.makeText(SignupActivity.this, "Network Error ! "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(SignupActivity.this, LoginActivity.class));
    }
}
