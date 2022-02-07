package com.example.logintest;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Signup extends AppCompatActivity {
    private EditText mNameView;
    private EditText mTypeView;
    private EditText mJobView;
    private EditText mAgeView;
    private Button mSignupButton;
    private ServiceApi service;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        mNameView = (EditText) findViewById(R.id.name);
        mTypeView = (EditText) findViewById(R.id.type);
        mJobView = (EditText) findViewById(R.id.job);
        mAgeView = (EditText) findViewById(R.id.age);
        mSignupButton = (Button) findViewById(R.id.signupbtn);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ServiceApi.DJANGO_SITE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(ServiceApi.class);


        mSignupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attempJoin();
            }
        });
    }


    private void attempJoin() {
        mNameView.setError(null);
        mTypeView.setError(null);
        mJobView.setError(null);
        mAgeView.setError(null);

        String name = mNameView.getText().toString();
        String type = mTypeView.getText().toString();
        String job = mJobView.getText().toString();
        Integer age = mAgeView.getText().length();

        boolean cancel = false;
        View focusView = null;

        if (name.isEmpty()) {
            mTypeView.setError("비밀번호를 입력해주세요.");
            focusView = mTypeView;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            startJoin(new JoinData(name,type,job,age));
//            showProgress(true);
        }
    }

    private void startJoin(JoinData data){
        Gson gson = new Gson();
        String obj = gson.toJson(data);
        JsonObject object = gson.fromJson(obj,JsonObject.class);
        Call<RequestBody> call = service.addPostVoditel(object);

        call.enqueue(new Callback<RequestBody>() {
            @Override
            public void onResponse(Call<RequestBody> call, Response<RequestBody> response) {
                
            }

            @Override
            public void onFailure(Call<RequestBody> call, Throwable t) {

            }
        });

    }



}
