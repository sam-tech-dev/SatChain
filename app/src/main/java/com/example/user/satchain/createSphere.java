package com.example.user.satchain;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by user on 2/28/2016.
 */
public class createSphere {




    float	angleA, angleB;
    float	cos, sin;
    float	r1, r2;
    float	h1, h2;
    float	step = 2.0f;




    float  radius=0.1f;
    float centrex;
    float centrey;
    float centrez=-4.52f;

    float[][] v = new float[32][3];
    ByteBuffer vbb;
    FloatBuffer vBuf;





    public  createSphere( float x,float y){



        centrex=x*0.4f;
        centrey=y*(-0.4f);



        vbb = ByteBuffer.allocateDirect(v.length * v[0].length * 4);
        vbb.order(ByteOrder.nativeOrder());
        vBuf = vbb.asFloatBuffer();



    }





    public void draw(GL10 gl) {


       // gl.glColor4f(0.5f, 0.5f, 1.0f, 1.0f);
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glEnableClientState(GL10.GL_NORMAL_ARRAY);

        for (angleA = -90.0f; angleA <90.0f; angleA += step) {
            int	n = 0;

            r1 = radius*(float)Math.cos(angleA * Math.PI / 180.0);
            r2 = radius*(float)Math.cos((angleA + step) * Math.PI / 180.0);
            h1 = radius*(float)Math.sin(angleA * Math.PI / 180.0);
            h2 = radius*(float)Math.sin((angleA + step) * Math.PI / 180.0);

            // Fixed latitude, 360 degrees rotation to traverse a weft
            for (angleB = 0.0f; angleB <= 360.0f; angleB += step) {

                cos = (float)Math.cos(angleB * Math.PI / 180.0);
                sin = -(float)Math.sin(angleB * Math.PI / 180.0);

                v[n][0] = centrex+(r2 * cos);
                v[n][1] = centrey+(h2);
                v[n][2] = centrez+(r2 * sin);
                v[n + 1][0] = centrex+(r1 * cos);
                v[n + 1][1] = centrey+(h1);
                v[n + 1][2] = centrez+(r1 * sin);

                vBuf.put(v[n]);
                vBuf.put(v[n + 1]);

                n += 2;

                if(n> 31){
                    vBuf.position(0);

                    gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vBuf);
                    gl.glNormalPointer(GL10.GL_FLOAT, 0, vBuf);

                    gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, n);

                    n = 0;
                    angleB -= step;
                }

            }
            vBuf.position(0);

            gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vBuf);
            gl.glNormalPointer(GL10.GL_FLOAT, 0, vBuf);
            gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, n);
        }

        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glDisableClientState(GL10.GL_NORMAL_ARRAY);
    }




    }




