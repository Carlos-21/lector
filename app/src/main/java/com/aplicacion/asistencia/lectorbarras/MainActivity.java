package com.aplicacion.asistencia.lectorbarras;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class MainActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler{
    private ZXingScannerView escanerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void EscannerQR(View view){
        escanerView = new ZXingScannerView(this);
        setContentView(escanerView);
        escanerView.setResultHandler(this);
        escanerView.startCamera();

    }

    @Override
    protected void onPause() {
        super.onPause();
        escanerView.stopCamera();
    }

    @Override
    public void handleResult(Result result) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Resultado del escaner");
        builder.setMessage("Resultado : "+result.getText()+"\n"+"Formato : "+result.getBarcodeFormat());
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        escanerView.resumeCameraPreview(this);
    }
}
