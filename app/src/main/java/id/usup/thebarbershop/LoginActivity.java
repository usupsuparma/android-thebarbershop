package id.usup.thebarbershop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.usup.thebarbershop.model.DbHelperUser;

public class LoginActivity extends AppCompatActivity {
    DbHelperUser dbUser;

    @BindView(R.id.editText_email_login)
    EditText mEmail;
    @BindView(R.id.editText_password_login)
    EditText mPassword;


    @OnClick(R.id.button_login) void login(Button button){
        if (mEmail.getText().toString().equals("")){
            mEmail.setError("Please insert email");
            mEmail.requestFocus();
        }else if (mPassword.getText().toString().equals("")){
            mPassword.setError("Please insert password");
            mPassword.requestFocus();
        }else {
            boolean result = dbUser.checkUser(mEmail.getText().toString().trim(),mPassword.getText().toString().trim());
            if (result){
                Toast.makeText(getApplicationContext(),"Login Success",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();
            }else {
                Toast.makeText(getApplicationContext(),"Login Failed",Toast.LENGTH_SHORT).show();
            }

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        dbUser = new DbHelperUser(this);
    }
}
