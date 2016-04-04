package com.example.user.satchain;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;

import static android.support.v4.app.ActivityCompat.finishAffinity;
import static android.support.v4.app.ActivityCompat.startActivity;

/**
 * Created by user on 2/26/2016.
 */
public class Gsurf extends GLSurfaceView{

       public  GLRenderer mRenderer;




    private int Xmax;
    private int Ymax;
    public static  int tur=2;
    public static  int firstExit=0;





    public Gsurf(Context context,int maxX,int maxY) {
        super(context);
         Xmax=maxX;
         Ymax=maxY;

        setEGLContextClientVersion(1);
        setPreserveEGLContextOnPause(true);

        mRenderer = new GLRenderer(context);
        setRenderer(mRenderer);

        // Render the view only when there is a change in the drawing data
       // setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
        setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);


    }



    @Override
    public void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        mRenderer.onPause();
    }

    @Override

    public void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        mRenderer.onResume();
    }


    @Override
    public boolean onTouchEvent(MotionEvent e) {
        float touchX = e.getX();
        float touchY = e.getY();
          if(touchY>440){
              touchY=touchY+50.0f;
          }
        if(touchY>711){
            touchY=touchY+50.0f;
        }

        if(touchY>853){
            touchY=touchY+20.0f;
        }

        if(touchY>853){
            touchY=touchY+40.0f;
        }


          int frameX=(int)(touchX*6)/Xmax;
          int frameY=(int)(touchY*9/Ymax);



        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN:

                if(GLRenderer.flag==0){
                    if(tur==1){
                        tur=2;
                    }else{
                        if(tur==2){
                            tur=1;
                        }
                    }
                    GLRenderer.flag=1;
                }

                if(tur==1){
                    tur=2;
                }else{
                    if(tur==2){
                        tur=1;
                    }
                }
                GLRenderer.mainLogic(frameX, frameY, tur, 0);
                requestRender();
                if(firstExit==1){
                    winnerCheck();
                }
                 firstExit=1;
                break;
        }
        return true;
    }


    public  void winnerCheck(){
         int winner1=0;
         int winner2=0;
        for(int i=0;i<9;i++){
            for(int j=0;j<6;j++){
                if(GLRenderer.matrix[i][j]/10==0||GLRenderer.matrix[i][j]/10==1){
                    winner1++;
                }else{
                    if(GLRenderer.matrix[i][j]/10==0||GLRenderer.matrix[i][j]/10==2){
                       winner2++;
                    }
                }
            }
        }

         if(winner1==54){

             try {
                 Thread.sleep(1000);
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
             popup(1);
         }else{
             if(winner2==54){
                 try {
                     Thread.sleep(1000);
                 } catch (InterruptedException e) {
                     e.printStackTrace();
                 }
               popup(2);
             }
         }

    }




    public  void popup(int wino){

        AlertDialog.Builder alert= new AlertDialog.Builder(getContext());

        alert.setTitle("*Congratulations*");
        alert.setMessage("Player "+wino+" Won!");


        alert.setPositiveButton("Play Again", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                Intent intent =new Intent(getContext(),MainActivity.class);
                startActivity(null,intent,null);


                dialog.dismiss();
            }
        });
        alert.setNegativeButton("Exit!", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                int pid = android.os.Process.myPid();
                android.os.Process.killProcess(pid);
                System.exit(0);
                dialog.dismiss();
            }
        });

        alert.show();



    }


}
