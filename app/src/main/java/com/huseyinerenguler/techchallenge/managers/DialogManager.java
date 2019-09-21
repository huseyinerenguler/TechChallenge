package com.huseyinerenguler.techchallenge.managers;

import android.app.Dialog;
import android.content.Context;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.huseyinerenguler.techchallenge.R;
import com.huseyinerenguler.techchallenge.utils.StaticParameters;

public class DialogManager {

    public interface onDialogListener {
        void onAction();
    }

    public Dialog initLogoutDialog(final onDialogListener listener, Context context) {

        final Dialog dialog_logout = new Dialog(context);
        dialog_logout.setContentView(R.layout.dialog_logout);

        TextView tv_question = dialog_logout.findViewById(R.id.tv_question);
        TextView tv_cancel = dialog_logout.findViewById(R.id.tv_cancel);
        TextView tv_submit = dialog_logout.findViewById(R.id.tv_submit);

        ((ConstraintLayout) tv_question.getParent()).setLayoutParams(
                new FrameLayout.LayoutParams((int) (StaticParameters.screenWidth * 0.85), ViewGroup.LayoutParams.WRAP_CONTENT));

        tv_question.setTextSize(TypedValue.COMPLEX_UNIT_PX, StaticParameters.screenWidth / 22);
        tv_cancel.setTextSize(TypedValue.COMPLEX_UNIT_PX, StaticParameters.screenWidth / 22);
        tv_submit.setTextSize(TypedValue.COMPLEX_UNIT_PX, StaticParameters.screenWidth / 22);

        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_logout.dismiss();
            }
        });

        tv_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onAction();
            }
        });

        return dialog_logout;
    }

}