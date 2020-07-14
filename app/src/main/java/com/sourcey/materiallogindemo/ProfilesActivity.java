package com.sourcey.materiallogindemo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.ButterKnife;

public class ProfilesActivity extends AppCompatActivity {

    View mainLayout;
    View infoLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profiles);

        setDataDetail();

        ButterKnife.bind(this);

        mainLayout = findViewById(R.id.mainLayout);
        Button _logout = (AppCompatButton) mainLayout.findViewWithTag("btnLogout");

        _logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });

    }

    private void setDataDetail() {
//        TextView tvEmail = findViewById(R.id.txt1);
//        tvEmail.setText(LoginData.email);

        infoLayout = findViewById(R.id.infoLayout);
        TextView tvName = (TextView) infoLayout.findViewWithTag("info-name");
        TextView tvEmail = (TextView) infoLayout.findViewWithTag("info-email");
        TextView tvAddress = (TextView) infoLayout.findViewWithTag("info-address");
        TextView tvMobile = (TextView) infoLayout.findViewWithTag("info-mobile");

        tvName.setText(LoginData.name);
        tvEmail.setText(LoginData.email);
        tvAddress.setText(LoginData.address);
        tvMobile.setText(LoginData.mobile);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_edit_profile) {
            Intent intent = new Intent(getApplicationContext(), EditProfilesActivity.class);
            startActivity(intent);
            finish();
            overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
        }

        if (id == R.id.menu_ticket) {
            Intent intent = new Intent(getApplicationContext(), TicketActivity.class);
            startActivity(intent);
//            finish();
            overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
        }

        if (id == R.id.menu_logout) {
            logout();
        }

        return super.onOptionsItemSelected(item);
    }

    public void logout() {
        new AlertDialog.Builder(this)
                .setTitle("Warning")
                .setMessage("Do you really want to exit?")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
//                        Toast.makeText(ProfilesActivity.this, "Yaay", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(intent);
                        finish();
                        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                    }
                })
                .setNegativeButton(android.R.string.no, null).show();
    }

    public void onBackPressed() {
        logout();
    }

    private void backToLoginScreen() {
        // Finish the registration screen and return to the Login activity
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }
}
