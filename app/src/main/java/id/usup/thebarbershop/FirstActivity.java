package id.usup.thebarbershop;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class FirstActivity extends AppCompatActivity {
    private boolean doubleBackToExitPressedOnce =false;
    private int i;

    @OnClick(R.id.button_login_first) void button(Button button){
        startActivity(new Intent(getApplicationContext(),LoginActivity.class));

    }
    @OnClick(R.id.button_registration_first) void regis(Button button){
        startActivity(new Intent(getApplicationContext(),RegistrationActivity.class));
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        ButterKnife.bind(this);
    }

    public void onBackPressed() {
        i++;
        if (i == 1) {
            Toast.makeText(FirstActivity.this, "Press back once more to exit.",
                    Toast.LENGTH_SHORT).show();
        } else if(i>1) {
            finish();
            super.onBackPressed();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        i = 0;
    }
}
