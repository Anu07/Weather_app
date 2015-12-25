package com.example.zapbuild.weather_app.base;

/**
 * Created by zapbuild on 24/12/15.
 */

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;


import butterknife.ButterKnife;


/**
 * Created by vardan sharma on 17/12/14.
 * Contains the logic for Toolbar.In order to use the Toolbar you need to remove the Action Bar using styles and include toolbar layout in your main xml.
 */
public abstract class BaseActivity extends AppCompatActivity implements BaseView {



    protected ProgressDialog mProgressDialog;
    private AlertDialog mDialog;

  /*  @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        ButterKnife.inject(this);
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
            if (getSupportActionBar() != null)
                getSupportActionBar().setDisplayShowHomeEnabled(true);
            mToolbar.setNavigationIcon(R.drawable.ic_back);
            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
        }

    }*/

    @Override
    public void onShowProgress(String message) {
        onHideError();
        onHideRetryView();
        mProgressDialog = ProgressDialog.show(BaseActivity.this, "", message, true, false);
    }

    @Override
    public void onShowProgress(@StringRes int message) {
        onHideError();
        onHideRetryView();
        mProgressDialog = ProgressDialog.show(BaseActivity.this, "", getString(message), true, false);
    }

    @Override
    public void onHideProgress() {
        if (mProgressDialog != null) mProgressDialog.dismiss();
    }

    @Override
    public void onShowError(String message) {
        String msg[] = message.split("-");
        String desc = msg[0];
        String title = (msg.length == 1) ? "Oops" : msg[1];
        onHideError();
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        mDialog = alertDialogBuilder
                .setTitle(title)
                .setMessage(desc)
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mDialog.dismiss();
                    }
                }).create();
        mDialog.show();
    }

    @Override
    public void onHideError() {
        if (mDialog != null) {
            mDialog.dismiss();
            mDialog = null;
        }
    }


    @Override
    public void onShowMessage(String message) {
        Toast.makeText(getBaseContext(),"Message",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onShowRetryView(String title, String description) {

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
//        if(BuildConfig.ENABLE_VIEW_SERVER)ViewServer.get(this).removeWindow(this);
    }


    @Override
    protected void onResume() {
        super.onResume();
//        if(BuildConfig.ENABLE_VIEW_SERVER)ViewServer.get(this).setFocusedWindow(this);
    }


    @Override
    public void onHideRetryView() {
//        if (mParentLayout != null) mParentLayout.setVisibility(View.GONE);

    }

    /**
     * Override this to implement retry button click event
     */

    public void onRetryClicked() {
    }
}
