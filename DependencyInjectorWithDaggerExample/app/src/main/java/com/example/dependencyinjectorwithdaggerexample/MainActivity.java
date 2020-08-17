package com.example.dependencyinjectorwithdaggerexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText mUserName, mNumber;
    Button mSave, mGet;
    MyComponent mComponent;
    @Inject
    SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        mComponent = DaggerMyComponent.builder().sharedPrefModule(new SharedPrefModule(this)).build();
        mComponent.inject(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSave:
                SharedPreferences.Editor editor = mSharedPreferences.edit();
                editor.putString("username", mUserName.getText().toString());
                editor.putString("number", mNumber.getText().toString());
                editor.apply();
                break;
            case R.id.btnGet:
                mUserName.setText(mSharedPreferences.getString("username", null));
                mNumber.setText(mSharedPreferences.getString("number", null));
                break;
        }
    }

    private void initViews() {
        mUserName = findViewById(R.id.inUsername);
        mNumber = findViewById(R.id.inNumber);
        mSave = findViewById(R.id.btnSave);
        mGet = findViewById(R.id.btnGet);
        mSave.setOnClickListener(this);
        mGet.setOnClickListener(this);
    }
}