package com.karla.cookyhome.utils;

import android.app.Application;
import android.content.Context;

import androidx.appcompat.app.AppCompatDelegate;

public class CookyHomeApplication extends Application {
  private static Context myApplicationContext;
  public static CookyHomeApplication instance;

  public CookyHomeApplication(){
    instance = this;
  }

  @Override
  public void onCreate() {
    super.onCreate();
    AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    myApplicationContext = this;


  }
  public static Context getMyApplicationContext(){
    return myApplicationContext;
  }

  public static CookyHomeApplication getInstance(){
    if(instance == null )instance = new CookyHomeApplication();
    return instance;
  }

  public String getApplicationName() {
    return CookyHomeApplication.getInstance().getApplicationInfo().loadLabel(getBaseContext().getPackageManager()).toString();
  }

}
