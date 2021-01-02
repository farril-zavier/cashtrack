package id.ac.ui.cs.mobileprogramming.farrilzavierfernaldy.cashtrack.views;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

import id.ac.ui.cs.mobileprogramming.farrilzavierfernaldy.cashtrack.OpenGLRenderer;

public class AboutOpenGLView extends GLSurfaceView {

    public AboutOpenGLView(Context context) {
        super(context);
        init();
    }

    public AboutOpenGLView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        setEGLContextClientVersion(2);
        setPreserveEGLContextOnPause(true);
        setRenderer(new OpenGLRenderer());
    }
}
