package com.example.user.satchain;

import android.content.Context;
import android.opengl.GLES10;
import android.opengl.GLSurfaceView;
import android.opengl.GLSurfaceView.Renderer;
import android.opengl.GLU;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by user on 2/26/2016.
 */
public class GLRenderer  implements Renderer {


    private long lastTime;


     public ndCuboid mCuboid;

    public doubleSphere dSphere;
    public triSphere tSphere;
    public createSphere mSphere;
    public static int  rowX,colY,turn;



   public static  int matrix[][]={ {0,0,0,0,0,0},{0,0,0,0,0,0},{0,0,0,0,0,0},{0,0,0,0,0,0},{0,0,0,0,0,0},{0,0,0,0,0,0},{0,0,0,0,0,0},{0,0,0,0,0,0},{0,0,0,0,0,0}};

   public static int flag=1;


    private static float anglePyramid = 0; // Rotational angle in degree for pyramid (NEW)
    private static float angleCube = 360;    // Rotational angle in degree for cube (NEW)
    private static float speedPyramid = 2.0f; // Rotational speed for pyramid (NEW)
    private static float speedCube = 2.0f;   // Rotational speed for cube (NEW)





    public  GLRenderer(Context context){

        lastTime=System.currentTimeMillis();
         mCuboid=new ndCuboid();


    }





    public void onPause()
    {
        /* Do stuff to pause the renderer */

    }

    public void onResume()
    {
        /* Do stuff to resume the renderer */
        lastTime = System.currentTimeMillis();
    }



    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {

        gl.glClearColor(.0f, 0.0f, 0.0f, 0.0f);
        gl.glClearDepthf(1.0f);            // Set depth's clear-value to farthest
        gl.glEnable(GL10.GL_DEPTH_TEST);   // Enables depth-buffer for hidden surface removal
        gl.glDepthFunc(GL10.GL_LEQUAL);    // The type of depth testing to do
        gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST);  // nice perspective view
        gl.glShadeModel(GL10.GL_SMOOTH);   // Enable smooth shading of color
        gl.glDisable(GL10.GL_DITHER);

    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {

        if (height == 0) height = 1;   // To prevent divide by zero
        float aspect = (float)width / height;

        gl.glViewport(0, 0, width, height);

        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity();

        GLU.gluPerspective(gl, 45, aspect, 0.1f, 100.f);

        gl.glMatrixMode(GL10.GL_MODELVIEW);  // Select model-view matrix
        gl.glLoadIdentity();


    }

    @Override
    public  void onDrawFrame(GL10 gl) {

            gl.glClear(GLES10.GL_COLOR_BUFFER_BIT | GLES10.GL_DEPTH_BUFFER_BIT);
            gl.glLoadIdentity();
            if (flag == 1) {
                if (turn == 1) {
                    gl.glColor4f(1.0f, 0.0f, 0.0f, 0.0f);
                } else {
                    if (turn == 2) {
                        gl.glColor4f(0.0f, 1.0f, 0.0f, 0.0f);
                    } else {
                        gl.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
                    }
                }
            }else {
                if (turn == 1) {
                     gl.glColor4f(0.0f, 1.0f, 0.0f, 0.0f);
                } else {
                    if (turn == 2) {
                        gl.glColor4f(1.0f, 0.0f, 0.0f, 0.0f);
                    } else {
                        gl.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
                    }
                }
            }
            gl.glTranslatef(-1.5f, 0.0f, -6.0f);
            gl.glScalef(0.8f, 0.8f, 0.8f);
            mCuboid.draw(gl);

           for(int i=0;i<9;i++){
               for(int j=0;j<6;j++){


                   if(matrix[i][j]%10==0){

                   }else{
                       if(matrix[i][j]%10==1){
                           float cenX=j+0.5f;
                           float cenY=i+0.5f;
                           mSphere=new createSphere(cenX,cenY);
                           gl.glLoadIdentity();
                           if(matrix[i][j]/10==1) {
                               gl.glColor4f(0.0f, 1.0f, 0.0f, 0.0f);
                           }else{
                               if(matrix[i][j]/10==2){
                                   gl.glColor4f(1.0f, 0.0f, 0.0f, 0.0f);
                               }
                           }
                           gl.glTranslatef(-1.177f, 1.81f, 0.0f);
                           gl.glTranslatef(cenX*0.4f, cenY*(-0.4f), -4.52f);
                           gl.glRotatef(angleCube, 0.0f, 0.0f, 3.0f);
                           gl.glTranslatef(-cenX * 0.4f, cenY * (0.4f), 4.52f);
                           mSphere.draw(gl);

                       }else{
                           if(matrix[i][j]%10==2){
                               float cenX=j+0.5f;
                               float cenY=i+0.5f;
                               dSphere=new doubleSphere(cenX,cenY);
                               gl.glLoadIdentity();

                               if(matrix[i][j]/10==1) {
                                   gl.glColor4f(0.0f, 1.0f, 0.0f, 0.0f);
                               }else{
                                   if(matrix[i][j]/10==2){
                                       gl.glColor4f(1.0f, 0.0f, 0.0f, 0.0f);
                                   }

                               }
                               gl.glTranslatef(-1.177f, 1.81f, 0.0f);
                               gl.glTranslatef(cenX*0.4f, cenY*(-0.4f), -4.52f);
                               gl.glRotatef(angleCube, 0.0f, 0.0f, 3.0f);
                               gl.glTranslatef(-cenX*0.4f,cenY*(0.4f), 4.52f);
                               dSphere.draw(gl);

                           }else{
                               if(matrix[i][j]%10==3){

                                   float cenX=j+0.5f;
                                   float cenY=i+0.5f;
                                   tSphere=new triSphere(cenX,cenY);
                                   gl.glLoadIdentity();
                                   if(matrix[i][j]/10==1) {
                                       gl.glColor4f(0.0f, 1.0f, 0.0f, 0.0f);
                                   }else {
                                       if(matrix[i][j]/10==2){
                                           gl.glColor4f(1.0f, 0.0f, 0.0f, 0.0f);
                                       }
                                   }
                                   gl.glTranslatef(-1.177f, 1.81f, 0.0f);
                                   gl.glTranslatef(cenX*0.4f, cenY*(-0.4f), -4.52f);
                                   gl.glRotatef(angleCube, 0.0f, 0.0f, 3.0f);
                                   gl.glTranslatef(-cenX*0.4f,cenY*(0.4f), 4.52f);
                                   tSphere.draw(gl);

                               }
                           }
                       }
                   }
               }
           }
    }





    public static void mainLogic(int X,int Y,int tn,int check){

        rowX=X;
        colY=Y;
        turn=tn;
        if( matrix[colY][rowX] != 0) {

            if ((matrix[colY][rowX] / 10 == turn)||(matrix[colY][rowX] / 10 != turn&&check==1)) {

                if ((rowX == 0 || rowX == 5) && colY == 0) {

                    if (matrix[colY][rowX] % 10 == 1) {
                        matrix[colY][rowX] = 0;
                        int tempX=rowX;
                        int tempY=colY;
                        if (rowX == 0) {
                            mainLogic(tempX+1,tempY,turn,1);
                        } else {
                            mainLogic(tempX-1,tempY,turn,1);
                        }
                        mainLogic(tempX,tempY+1,turn,1);
                    }
                } else {
                    if ((rowX == 0 || rowX == 5) && colY == 8) {
                        if (matrix[colY][rowX] % 10 == 1){
                            matrix[colY][rowX] = 0;
                            int tempX=rowX;
                            int tempY=colY;
                            if (rowX == 0) {
                                mainLogic(tempX+1,tempY,turn,1);
                            } else {
                                mainLogic(tempX-1,tempY,turn,1);
                            }
                            mainLogic(tempX,tempY-1,turn,1);
                        }
                    } else {
                        if ((colY == 0 || colY == 8) && (rowX > 0) && (rowX < 5)) {
                            if (matrix[colY][rowX] % 10 == 2) {
                                matrix[colY][rowX] = 0;
                                int tempX=rowX;
                                int tempY=colY;
                                if (colY == 0) {
                                    mainLogic(tempX,tempY+1,turn,1);
                                } else {
                                    mainLogic(tempX,tempY-1,turn,1);
                                }
                                mainLogic(tempX-1,tempY,turn,1);
                                mainLogic(tempX+1,tempY,turn,1);

                            } else {
                                matrix[colY][rowX] = (turn * 10) + (matrix[colY][rowX] % 10) + 1;
                            }

                        } else {
                            if ((rowX == 0 || rowX == 5) && (colY > 0) && (colY < 8)) {
                                if (matrix[colY][rowX] % 10 == 2) {
                                    matrix[colY][rowX] = 0;
                                    int tempX=rowX;
                                    int tempY=colY;
                                    if (rowX == 0) {
                                        mainLogic(tempX+1,tempY,turn,1);
                                    } else {
                                        mainLogic(tempX-1,tempY,turn,1);
                                    }
                                    mainLogic(tempX,tempY+1,turn,1);
                                    mainLogic(tempX,tempY-1,turn,1);
                                } else {
                                    matrix[colY][rowX] = (turn * 10) + (matrix[colY][rowX] % 10) + 1;
                                }

                            } else {
                                if (matrix[colY][rowX] % 10 == 3) {

                                    matrix[colY][rowX] = 0;
                                    int tempX=rowX;
                                    int tempY=colY;
                                    mainLogic(tempX+1,tempY,turn,1);
                                    mainLogic(tempX-1,tempY,turn,1);
                                    mainLogic(tempX,tempY+1,turn,1);
                                    mainLogic(tempX,tempY-1,turn,1);

                                } else {
                                    matrix[colY][rowX] = (turn * 10) + (matrix[colY][rowX] % 10) + 1;
                                }
                            }
                        }
                    }
                }

            } else {
                 flag=0;
                return;
            }

        }else {
            matrix[colY][rowX] = (turn * 10) + 1;
        }


    }





}
