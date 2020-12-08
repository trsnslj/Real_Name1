package com.realname.marketclient.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.school.marketclient.R;

public class PasswordResetActivity extends AppCompatActivity implements  View.OnClickListener {
    EditText mPasswordView1;
    EditText mPasswordView2;
    Button mCommitView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_reset);
        findViewById(R.id.register_back).setOnClickListener(this);
        mPasswordView1=(EditText)findViewById(R.id.et_password_reset_password1);
        mPasswordView2=(EditText)findViewById(R.id.et_password_reset_password2);
        mCommitView=(Button)findViewById(R.id.btn_password_reset_commit);
        mCommitView.setOnClickListener(this);
        initView();
    }

    public void initView(){
        InputTextHelper.with(this)
                .addView(mPasswordView1)
                .addView(mPasswordView2)
                .setMain(mCommitView)
                .setListener(new InputTextHelper.OnInputTextListener() {

                    @Override
                    public boolean onInputChange(InputTextHelper helper) {
                        return mPasswordView1.getText().toString().length() >= 6 &&
                                mPasswordView1.getText().toString().equals(mPasswordView2.getText().toString());
                    }
                })
                .build();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register_back: {
                finish();
                break;
            }
            case R.id.btn_password_reset_commit: {

                String strPwd1=String.valueOf(mPasswordView1.getText().toString());
                String strPwd2=String.valueOf(mPasswordView2.getText().toString());
                Log.e("zpx","旧密码："+strPwd1+"");
                Log.e("zpx","新密码："+strPwd2+"");
                break;
            }
            default:
                break;
        }

    }
}
