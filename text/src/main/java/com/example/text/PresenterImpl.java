package com.example.text;

import android.os.Handler;
import android.provider.Settings;

import com.example.interfacePackage.PersentInterface;
import com.example.interfacePackage.ViewInterface;

public class PresenterImpl implements PersentInterface {


    private PersentInterface pre;
    private ViewInterface viwe;
    private final ModelImp modelImp;


    public PresenterImpl(ViewInterface viwe) {
        this.viwe = viwe;
        modelImp = new ModelImp(this);
    }

    @Override
    public void requestSucess(String scuess) {
        viwe.hideDialog();
        viwe.showMessaga(scuess);

    }

    @Override
    public void requestFaile(String fail) {
        viwe.hideDialog();
        viwe.showErr(fail);
    }

    @Override
    public void request(String str) {
        viwe.showDialog();
        modelImp.requestData();


    }
}
