package com.sourcey.materiallogindemo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class EditPasswordActivity extends AppCompatActivity {
    View mainLayout;
    View infoLayout;
    EditText _currentPassword;
    EditText _newPassword;
    EditText _confirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_password);

        mainLayout = findViewById(R.id.mainLayout);
        Button _btnUpdateProfiles = (AppCompatButton) mainLayout.findViewWithTag("btnSavePassword");
        _btnUpdateProfiles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatePassword();
            }
        });
    }

    public void updatePassword() {
        infoLayout = findViewById(R.id.infoLayout);
        _currentPassword = (EditText) infoLayout.findViewWithTag("et-current-password");
        _newPassword = (EditText) infoLayout.findViewWithTag("et-new-password");
        _confirmPassword = (EditText) infoLayout.findViewWithTag("et-confirm-password");

        if (!validate()) {
            onUpdateFailed();
            return;
        }

        LoginData.password = _newPassword.getText().toString();
        LoginData.reEnterPassword = _confirmPassword.getText().toString();

        final ProgressDialog progressDialog = new ProgressDialog(EditPasswordActivity.this,
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
    }

    public void onBackPressed() {
        backToProfileScreen();
    }

    private void backToProfileScreen() {
        Intent intent = new Intent(getApplicationContext(), ProfilesActivity.class);
        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    public boolean validate() {
        boolean valid = true;

        LinearLayout _inlineCurrentPassword = (LinearLayout) this.infoLayout.findViewWithTag("inline-current-password");
        LinearLayout _inlineNewPassword = (LinearLayout) infoLayout.findViewWithTag("inline-new-password");
        LinearLayout _inlineConfirmPassword = (LinearLayout) infoLayout.findViewWithTag("inline-confirm-password");

        TextView _inlineCurrentPasswordTxt = (TextView) infoLayout.findViewWithTag("inline-current-password-msg");
        TextView _inlineNewPasswordTxt = (TextView) infoLayout.findViewWithTag("inline-new-password-msg");
        TextView _inlineConfirmPasswordTxt = (TextView) infoLayout.findViewWithTag("inline-confirm-password-msg");

        String currentPassword = _currentPassword.getText().toString();
        String password = _newPassword.getText().toString();
        String reEnterPassword = _confirmPassword.getText().toString();

        if (currentPassword.isEmpty() || currentPassword.length() < 4 || currentPassword.length() > 10 || !currentPassword.equals(LoginData.password)) {
            _inlineCurrentPasswordTxt.setText("Password Incorrect");
            _inlineCurrentPassword.setVisibility(View.VISIBLE);
            valid = false;
        } else {
            _inlineCurrentPassword.setVisibility(View.GONE);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _inlineNewPasswordTxt.setText("Between 4 and 10 alphanumeric characters");
            _inlineNewPassword.setVisibility(View.VISIBLE);
            valid = false;
        } else {
            _inlineNewPassword.setVisibility(View.GONE);
        }

        if (reEnterPassword.isEmpty() || reEnterPassword.length() < 4 || reEnterPassword.length() > 10 || !(reEnterPassword.equals(password))) {
            _inlineConfirmPasswordTxt.setText("Between 4 and 10 alphanumeric characters");
            if (!reEnterPassword.equals(password)) {
                _inlineConfirmPasswordTxt.setText("Password Do not match");
            }
            _inlineConfirmPassword.setVisibility(View.VISIBLE);
            valid = false;
        } else {
            _inlineConfirmPassword.setVisibility(View.GONE);
        }

        return valid;
    }
}