package com.example.text;

import android.os.Handler;

import com.example.interfacePackage.ModelInterface;
import com.example.interfacePackage.PersentInterface;

public class ModelImp implements ModelInterface {

    private PersentInterface persentInterface;

    public ModelImp(PersentInterface persentInterface) {
        this.persentInterface = persentInterface;
    }

    @Override
    public void requestData() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                long tiume = System.currentTimeMillis();

                if (tiume % 2 == 1) {
                    persentInterface.requestSucess("请求成功");
                } else {
                    persentInterface.requestFaile("请求成功");
                }

            }
        }, 3000);


    }
}
