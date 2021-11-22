package com.huthfy.pickemonapp;

import android.app.Application;

import dagger.hilt.android.HiltAndroidApp;

@HiltAndroidApp // let dagger hilt knows the main application to work in the whole app
public class BaseApplication extends Application {
}
