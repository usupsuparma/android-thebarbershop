package id.usup.thebarbershop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.usup.thebarbershop.controller.User;
import id.usup.thebarbershop.model.DbHelperUser;

public class RegistrationActivity extends AppCompatActivity {
    DbHelperUser dbUser;
    @BindView(R.id.editText_name_registration)
    EditText mName;
    @BindView(R.id.editText_noHandphome_registration)
    EditText mNoHP;
    @BindView(R.id.editText_email_registration)
    EditText mEmail;
    @BindView(R.id.editText_password_registration)
    EditText mPassword;

    @BindView(R.id.checkbox_aggree_registration)
    CheckBox mAggree;
    @BindView(R.id.radio_group_registration)
    RadioGroup mRadioGroup;

    @BindView(R.id.button_registration)
    Button mRegistration;
    @BindView(R.id.switchId)
    Switch mSwitch;

    @OnClick(R.id.switchId) void setmSwitch(){
        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    mPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }else {
                    mPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });
    }

    @OnClick(R.id.checkbox_aggree_registration) void checkBox(){
        if (mAggree.isChecked()){
            mRegistration.setEnabled(true);
        }
    }

    @OnClick(R.id.button_registration) void  registration(){
        if (mName.getText().toString().equals("")){
            mName.setError("Please Insert Your name");
            mName.requestFocus();
        }else if (mNoHP.getText().toString().equals("")){
            mNoHP.setError("Please Insert Your number phone");
            mNoHP.requestFocus();
        }else if (mPassword.getText().toString().equals("")){
            mPassword.getText().toString().equals("");
            mPassword.requestFocus();
        }else {

            int idSelectedGender = mRadioGroup.getCheckedRadioButtonId();
            //search id radio button
            RadioButton radioButtonSex = findViewById(idSelectedGender);
            final String gender = radioButtonSex.getText().toString().trim();
            Boolean result = dbUser.insertData(mName.getText().toString(),gender,mNoHP.getText().toString(),mEmail.getText().toString(),mPassword.getText().toString());
            if (result){
                Toast.makeText(getApplicationContext(),"Registration Success", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                finish();
            }else {
                Toast.makeText(getApplicationContext(),"Registration Failed",Toast.LENGTH_SHORT).show();
            }

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        ButterKnife.bind(this);
        mRegistration.setEnabled(false);
        dbUser = new DbHelperUser(RegistrationActivity.this);
    }

    @Override
    protected void onDestroy() {
        dbUser.close();
        super.onDestroy();
    }
}
