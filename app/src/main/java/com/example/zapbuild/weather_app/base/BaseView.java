package com.example.zapbuild.weather_app.base;

import android.support.annotation.StringRes;


public interface BaseView {
    void onShowProgress(String message);


    void onShowProgress(@StringRes int message);


    void onHideProgress();

    void onShowError(String errorMessage);

    void onHideError();

    void onShowMessage(String message);

    void onShowRetryView(String title, String description);

    void onHideRetryView();
}
