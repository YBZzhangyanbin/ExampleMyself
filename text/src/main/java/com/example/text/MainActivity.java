package com.example.text;

import android.app.AlertDialog;
import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.interfacePackage.ViewInterface;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ViewInterface {

    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initMessage();


    }

    private void initMessage() {
        final PresenterImpl presenter = new PresenterImpl(this);
        findViewById(R.id.tvRequestSucess).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.request("群殴供求");
            }
        });
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("数据加载中");
        dialog = builder.create();
    }

    @Override
    public void showDialog() {
        dialog.show();
    }

    @Override
    public void hideDialog() {
        dialog.cancel();
    }

    @Override
    public void showMessaga(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void showErr(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

    }

}
