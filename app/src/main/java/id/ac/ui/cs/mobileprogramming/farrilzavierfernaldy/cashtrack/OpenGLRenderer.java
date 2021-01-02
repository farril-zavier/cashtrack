package id.ac.ui.cs.mobileprogramming.farrilzavierfernaldy.cashtrack;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class OpenGLRenderer implements GLSurfaceView.Renderer {

    private double redValue = 1f;
    private double greenValue = 1f;
    private double blueValue = 1f;
    public static final double RED_FLASH_DURATION = 1000;
    public static final double GREEN_FLASH_DURATION = 3000;
    public static final double BLUE_FLASH_DURATION = 5000;

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        GLES20.glClearColor((float) redValue, (float) greenValue, (float) blueValue, 1f);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {

    }

    @Override
    public void onDrawFrame(GL10 gl) {
        GLES20.glClearColor((float) redValue, (float) greenValue, (float) blueValue, 1f);
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);

        redValue = ((Math.sin(System.currentTimeMillis() * 2 * Math.PI / RED_FLASH_DURATION) * 0.5) + 0.5);
        greenValue = ((Math.sin(System.currentTimeMillis() * 2 * Math.PI / GREEN_FLASH_DURATION) * 0.5) + 0.5);
        blueValue = ((Math.sin(System.currentTimeMillis() * 2 * Math.PI / BLUE_FLASH_DURATION) * 0.5) + 0.5);
    }
}
