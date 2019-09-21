package com.huseyinerenguler.techchallenge.views.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;

import com.huseyinerenguler.techchallenge.R;
import com.huseyinerenguler.techchallenge.utils.StaticParameters;

public class LoginActivity extends AppCompatActivity {

    private EditText et_username;
    private EditText et_password;
    private Switch switch_remember_me;
    private TextView tv_submit;
    private TextView tv_validate;
    private ProgressBar pb_validate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initUI();
    }

    private void initUI() {

        et_username = findViewById(R.id.et_username);
        et_password = findViewById(R.id.et_password);
        switch_remember_me = findViewById(R.id.switch_remember_me);
        tv_submit = findViewById(R.id.tv_submit);
        tv_validate = findViewById(R.id.tv_validate);
        pb_validate = findViewById(R.id.pb_validate);

        et_username.setTextSize(TypedValue.COMPLEX_UNIT_PX, StaticParameters.screenWidth / 20);
        et_password.setTextSize(TypedValue.COMPLEX_UNIT_PX, StaticParameters.screenWidth / 20);
        switch_remember_me.setTextSize(TypedValue.COMPLEX_UNIT_PX, StaticParameters.screenWidth / 22);
        tv_submit.setTextSize(TypedValue.COMPLEX_UNIT_PX, StaticParameters.screenWidth / 20);
        tv_submit.setTextSize(TypedValue.COMPLEX_UNIT_PX, StaticParameters.screenWidth / 22);

        switch_remember_me.setSwitchPadding(StaticParameters.screenWidth / 54);
    }

    public void onClick(View view) {

        if (view.getId() == R.id.tv_submit) {
            login();
        }
    }

    private void login() {

        String username = et_username.getText().toString();
        String password = et_password.getText().toString();

        if (username.length() > 0 && password.length() > 0) {

            tv_submit.setEnabled(false);
            tv_validate.setVisibility(View.INVISIBLE);
            pb_validate.setVisibility(View.VISIBLE);

            // valid username and password
            if (username.equals("kariyer") && password.equals("2019ADev")) {

                // check remember me
                if (switch_remember_me.isChecked()) {
                    SharedPreferences.Editor editor = getSharedPreferences(StaticParameters.SHARED_PREFERENCES_NAME, MODE_PRIVATE).edit();
                    editor.putBoolean(StaticParameters.SHARED_PREFERENCES_KEY_REMEMBER_ME, true);
                    editor.apply();
                }

                startOrderActivity();
            }

            // invalid username or password
            else {
                tv_validate.setText(R.string.validate_wrong_username_password);
                pb_validate.setVisibility(View.INVISIBLE);
                tv_validate.setVisibility(View.VISIBLE);
                tv_submit.setEnabled(true);
            }
        }

        // empty username or password
        else {
            tv_validate.setText(R.string.validate_empty_username_password);
            tv_validate.setVisibility(View.VISIBLE);
        }
    }

    private void startOrderActivity() {

        startActivity(new Intent(LoginActivity.this, OrderActivity.class));
        finish();
    }

}