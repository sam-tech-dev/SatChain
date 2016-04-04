package com.example.user.satchain;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ConfigurationInfo;
import android.opengl.GLSurfaceView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {


    private GLSurfaceView  mGLview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        if (hasGl2()) {


           Display display = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
            int maxX= display.getWidth();
            int maxY= display.getHeight();

            mGLview = new Gsurf(this,maxX,maxY);
         /*   mGLview.setEGLContextClientVersion(2);
            mGLview.setPreserveEGLContextOnPause(true);
            */
        }


        RelativeLayout layout = (RelativeLayout) findViewById(R.id.layout);

        RelativeLayout.LayoutParams glParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        layout.addView(mGLview, glParams);



    }


    @Override
    protected void onPause() {
        super.onPause();
        mGLview.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mGLview.onResume();
    }




        public boolean hasGl2(){

            ActivityManager acManage=(ActivityManager)getSystemService(Context.ACTIVITY_SERVICE);


            ConfigurationInfo info= acManage.getDeviceConfigurationInfo();

            return info.reqGlEsVersion >= 0x20000;
            //return true;
        }




    }



