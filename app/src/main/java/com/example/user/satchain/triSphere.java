package com.example.user.satchain;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by user on 2/28/2016.
 */
public class triSphere {


    float coordx;
    float  coordy;




    public triSphere(float x,float y){

        coordx=x;
        coordy=y;

    }


    public void draw(GL10 gl){

        new createSphere(coordx-0.15f,coordy-0.15f).draw(gl);
        new createSphere(coordx+0.15f,coordy).draw(gl);
        new createSphere(coordx,coordy+0.15f).draw(gl);

    }


}

