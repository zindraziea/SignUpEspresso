package com.sourcey.materiallogindemo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;

public class EditProfilesActivity extends AppCompatActivity {

    View mainLayout;
    View infoLayout;
    EditText _nameText;
    EditText _emailText;
    EditText _addressText;
    EditText _mobileText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profiles);
        setDataDetail();

        mainLayout = findViewById(R.id.mainLayout);
        Button _btnUpdateProfiles = (AppCompatButton) mainLayout.findViewWithTag("btnSaveProfiles");
        _btnUpdateProfiles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateProfiles();
            }
        });
    }

    public void updateProfiles() {
        if (!validate()) {
            onUpdateFailed();
            return;
        }

        infoLayout = findViewById(R.id.infoLayout);
        _nameText = (EditText) infoLayout.findViewWithTag("et-name");
        _emailText = (EditText) infoLayout.findViewWithTag("et-email");
        _addressText = (EditText) infoLayout.findViewWithTag("et-address");
        _mobileText = (EditText) infoLayout.findViewWithTag("et-mobile");

        LoginData.name = _nameText.getText().toString();
        LoginData.address = _addressText.getText().toString();
        LoginData.email = _emailText.getText().toString();
        LoginData.mobile = _mobileText.getText().toString();

        final ProgressDialog progressDialog = new ProgressDialog(EditProfilesActivity.this,
                R.style.Theme_AppCompat);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Update Account...");
        progressDialog.show();
        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onSignupSuccess or onSignupFailed
                        // depending on success
                        onUpdateSuccess();
                        // onSignupFailed();
                        progressDialog.dismiss();
                    }
                }, 2000);
    }

    public void onUpdateFailed() {
        Toast.makeText(getBaseContext(), "Update failed", Toast.LENGTH_LONG).show();
    }

    public void onUpdateSuccess() {
        backToProfileScreen();
//        Intent intent = new Intent(getApplicationContext(), ProfilesActivity.class);
//        startActivity(intent);
//        finish();
//        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    public boolean validate() {
        boolean valid = true;

        infoLayout = findViewById(R.id.infoLayout);
        EditText _nameText = (EditText) infoLayout.findViewWithTag("et-name");
        EditText _emailText = (EditText) infoLayout.findViewWithTag("et-email");
        EditText _addressText = (EditText) infoLayout.findViewWithTag("et-address");
        EditText _mobileText = (EditText) infoLayout.findViewWithTag("et-mobile");

        String name = _nameText.getText().toString();
        String address = _addressText.getText().toString();
        String email = _emailText.getText().toString();
        String mobile = _mobileText.getText().toString();

        if (name.isEmpty() || name.length() < 3) {
            _nameText.setError("at least 3 characters");
            valid = false;
        } else {
            _nameText.setError(null);
        }

        if (address.isEmpty()) {
            _addressText.setError("Enter Valid Address");
            valid = false;
        } else {
            _addressText.setError(null);
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("enter a valid email address");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (mobile.isEmpty() || mobile.length() != 10) {
            _mobileText.setError("Enter Valid Mobile Number");
            valid = false;
        } else {
            _mobileText.setError(null);
        }

        return valid;
    }

    public void onBackPressed() {
        backToProfileScreen();
    }

    private void setDataDetail() {
        infoLayout = findViewById(R.id.infoLayout);
        EditText tvName = (EditText) infoLayout.findViewWithTag("et-name");
        EditText tvEmail = (EditText) infoLayout.findViewWithTag("et-email");
        EditText tvAddress = (EditText) infoLayout.findViewWithTag("et-address");
        EditText tvMobile = (EditText) infoLayout.findViewWithTag("et-mobile");

        tvName.setText(LoginData.name);
        tvEmail.setText(LoginData.email);
        tvAddress.setText(LoginData.address);
        tvMobile.setText(LoginData.mobile);
    }

    private void backToProfileScreen(){
        Intent intent = new Intent(getApplicationContext(), ProfilesActivity.class);
        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }
}