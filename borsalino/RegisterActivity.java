package com.example.borsalino;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.borsalino.Common.Common;
import com.example.borsalino.Model.APIResponse;
import com.example.borsalino.Remote.IMyAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    TextView txt_sign_in;
    EditText edt_email,edt_password,edt_name,edt_apellido;
    Button btn_register;

    IMyAPI mService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //iniciar servicio
        mService = Common.getAPI();
        //iniciar vista
        txt_sign_in = (TextView)findViewById(R.id.txt_login);
        edt_email = (EditText) findViewById(R.id.edt_email);
        edt_password = (EditText) findViewById(R.id.edt_password);
        edt_name = (EditText) findViewById(R.id.edt_name);
        edt_apellido = (EditText) findViewById(R.id.edt_apellido);
        btn_register = (Button) findViewById(R.id.btn_register);
        //eventos
        txt_sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewUser(edt_name.getText().toString(),edt_apellido.getText().toString(),edt_email.getText().toString(), edt_password.getText().toString());
            }
        });
    }

    private void createNewUser(String name, String apellido, String email, String password) {
        mService.registerUser(name, apellido, email, password)
                .enqueue(new Callback<APIResponse>() {
                    @Override
                    public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                        APIResponse result = response.body();
                        if(result.isError())
                            Toast.makeText(RegisterActivity.this, result.getError_msg(), Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(RegisterActivity.this, "Usuario creado", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<APIResponse> call, Throwable t) {
                        Toast.makeText(RegisterActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
