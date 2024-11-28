package com.example.myapp3;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class Test_2_Main_Activity extends AppCompatActivity {
    private AlertDialog dialog;
    private Toast toast = null;
    // 声明成员变量
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showLoginDialog();
        }

    public void showLoginDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(R.layout.alertdialog_test);
        dialog = builder.create(); // 赋值给成员变量
        dialog.show();

        Button cancelButton = dialog.findViewById(R.id.cancel_button);
        Button signInButton = dialog.findViewById(R.id.signin_button);

        cancelButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        signInButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText usernameEditText = dialog.findViewById(R.id.username);
                EditText passwordEditText = dialog.findViewById(R.id.password);
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                toast = Toast.makeText(Test_2_Main_Activity.this, "welcome,"+username+"\npassword: "+password, Toast.LENGTH_SHORT);
                toast.show();
                dialog.dismiss();
            }
        });
    }
}
