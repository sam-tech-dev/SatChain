package com.example.user.satchain;

import java.nio.FloatBuffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by user on 2/27/2016.
 */
public class ndCuboid {





    private FloatBuffer vertexBuffer;
    private ByteBuffer  mIndexBuffer;
    private int numFaces = 6;

    private float[][] colors = {
            {1.0f, 0.5f, 0.0f, 1.0f},
            {1.0f, 0.0f, 1.0f, 1.0f},
            {0.0f, 1.0f, 0.0f, 1.0f},
            {0.0f, 0.0f, 1.0f, 1.0f},
            {1.0f, 0.0f, 0.0f, 1.0f},
            {1.0f, 1.0f, 0.0f, 1.0f}
    };


    private float[] vertices = {  // Vertices of the 6 faces







            0.2f, -2.61f, 0.5f,
            3.56f, -2.61f, 0.5f,
            3.56f,  2.61f, 0.5f,
            0.2f,  2.61f, 0.5f,
            0.2f, -2.61f,  1.0f,
            3.56f, -2.61f, 1.0f,
            3.56f,  2.61f,  1.0f,
            0.2f,  2.61f,  1.0f,
            0.76f,  2.61f, 0.5f,
            0.76f,  -2.61f, 0.5f,
            0.76f,  -2.61f, 1.0f,
            0.76f,  2.61f, 1.0f,
            1.32f,  2.61f, 0.5f,
            1.32f,  -2.61f, 0.5f,
            1.32f,  -2.61f, 1.0f,
            1.32f,  2.61f, 1.0f,
            1.88f,  2.61f, 0.5f,
            1.88f,  -2.61f, 0.5f,
            1.88f,  -2.61f, 1.0f,
            1.88f,  2.61f, 1.0f,
            2.44f,  2.61f, 0.5f,
            2.44f,  -2.61f, 0.5f,
            2.44f,  -2.61f, 1.0f,
            2.44f,  2.61f, 1.0f,
            3.0f,  2.61f, 0.5f,
            3.0f,  -2.61f, 0.5f,
            3.0f,  -2.61f, 1.0f,
            3.0f,  2.61f, 1.0f,

            3.56f, 2.03f, 0.5f,
            0.2f, 2.03f, 0.5f,
            0.2f, 2.03f,1.0f,
            3.56f,2.03f,1.0f,

            3.56f, 1.45f, 0.5f,
            0.2f, 1.45f, 0.5f,
            0.2f, 1.45f,1.0f,
            3.56f,1.45f,1.0f,

            3.56f, 0.87f, 0.5f,
            0.2f, 0.87f, 0.5f,
            0.2f, 0.87f,1.0f,
            3.56f,0.87f,1.0f,

            3.56f, 0.29f, 0.5f,
            0.2f, 0.29f, 0.5f,
            0.2f, 0.29f,1.0f,
            3.56f,0.29f,1.0f,

            3.56f, -2.03f, 0.5f,
            0.2f, -2.03f, 0.5f,
            0.2f, -2.03f,1.0f,
            3.56f,-2.03f,1.0f,

            3.56f, -1.45f, 0.5f,
            0.2f, -1.45f, 0.5f,
            0.2f, -1.45f,1.0f,
            3.56f,-1.45f,1.0f,

            3.56f, -0.87f, 0.5f,
            0.2f, -0.87f, 0.5f,
            0.2f, -0.87f,1.0f,
            3.56f,-0.87f,1.0f,

            3.56f, -0.29f, 0.5f,
            0.2f, -0.29f, 0.5f,
            0.2f, -0.29f,1.0f,
            3.56f,-0.29f,1.0f








    };




    private byte indices[] = {

            0,1,1,2,2,3,3,0,
            4,5,5,6,6,7,7,4,
            0,4,3,7,2,6,1,5,
            8,9,9,10,10,11,11,8,
            12,13,13,14,14,15,15,12,
            16,17,17,18,18,19,19,16,
            20,21,21,22,22,23,23,20,
            24,25,25,26,26,27,27,24,
            28,29,29,30,30,31,31,28,
            32,33,33,34,34,35,35,32,
            36,37,37,38,38,39,39,36,
            40,41,41,42,42,43,43,40,
            44,45,45,46,46,47,47,44,
            48,49,49,50,50,51,51,48,
            52,53,53,54,54,55,55,52,
            56,57,57,58,58,59,59,56




    };



    public  ndCuboid(){



        ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length * 4);
        vbb.order(ByteOrder.nativeOrder()); // Use native byte order
        vertexBuffer = vbb.asFloatBuffer(); // Convert from byte to float
        vertexBuffer.put(vertices);         // Copy data into buffer
        vertexBuffer.position(0);




        mIndexBuffer = ByteBuffer.allocateDirect(indices.length);
        mIndexBuffer.put(indices);
        mIndexBuffer.position(0);



    }







           public void draw(GL10 gl) {
               gl.glFrontFace(GL10.GL_CCW);    // Front face in counter-clockwise orientation
               gl.glEnable(GL10.GL_CULL_FACE); // Enable cull face
               // gl.glCullFace(GL10.GL_BACK);    // Cull the back face (don't display)

               gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
               gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);




                  gl.glDrawElements(GL10.GL_LINES, 128, GL10.GL_UNSIGNED_BYTE, mIndexBuffer);


               gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
               gl.glDisable(GL10.GL_CULL_FACE);



       }

}
