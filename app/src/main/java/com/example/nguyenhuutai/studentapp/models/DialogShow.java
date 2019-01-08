package com.example.nguyenhuutai.studentapp.models;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;

import com.example.nguyenhuutai.studentapp.R;

public class DialogShow {

    private Dialog dialog;

    public DialogShow(Context context){
        dialog =  new Dialog(context);
    }

    public void showDialog(){

        dialog.setTitle("Attention!");
        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentView(R.layout.network_dialog);
        dialog.show();

        Button btnOk = dialog.findViewById(R.id.ok);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }


}
