package com.goka.wave;

import org.rajawali3d.Object3D;
import org.rajawali3d.animation.Animation;
import org.rajawali3d.animation.Animation3D;
import org.rajawali3d.animation.RotateAnimation3D;
import org.rajawali3d.lights.DirectionalLight;
import org.rajawali3d.materials.textures.ATexture;
import org.rajawali3d.materials.textures.Texture;
import org.rajawali3d.math.Plane;
import org.rajawali3d.math.vector.Vector3;
import org.rajawali3d.postprocessing.PostProcessingManager;
import org.rajawali3d.primitives.Cube;
import org.rajawali3d.renderer.RajawaliRenderer;
import org.rajawali3d.surface.IRajawaliSurface;
import org.rajawali3d.surface.IRajawaliSurfaceRenderer;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;

import java.util.Random;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by katsuyagoto on 15/08/24.
 */
public class WaveView extends View implements View.OnTouchListener {

    private Point mScreenSize;

    protected IRajawaliSurface mRajawaliSurface;
    protected IRajawaliSurfaceRenderer mRenderer;

    public WaveView(Context context) {
        super(context);
        Display display = getActivity().getWindowManager().getDefaultDisplay();
        mScreenSize = new Point();
        display.getSize(mScreenSize);
    }

    public Activity getActivity() {
        if (getContext() instanceof Activity) {
            return (Activity)getContext();
        }
        return null;
    }

    @Override
    public void onFinishInflate() {
        super.onFinishInflate();
        mRajawaliSurface = (IRajawaliSurface) findViewById(R.id.rajwali_surface);
        mRenderer = new TouchRipplesRenderer(getContext());
        mRajawaliSurface.setSurfaceRenderer(mRenderer);
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            ((TouchRipplesRenderer) mRenderer).setTouch(event.getX()
                    / mScreenSize.x, 1.0f - (event.getY() / mScreenSize.y));
        }
        return ((View) mRajawaliSurface).onTouchEvent(event);
    }

    private final class TouchRipplesRenderer extends RajawaliRenderer {
        private final int NUM_CUBES_H = 2;
        private final int NUM_CUBES_V = 2;
        private final int NUM_CUBES = NUM_CUBES_H * NUM_CUBES_V;
        private Animation3D[] mAnims;
        private RipplePass mFilter;
        private PostProcessingManager mEffects;
        private long frameCount;

        public TouchRipplesRenderer(Context context) {
            super(context);
        }

        protected void initScene() {
            mAnims = new Animation3D[NUM_CUBES];
            mFilter = new RipplePass();
            mFilter.setRippleSize(62);
        }

        @Override
        protected void onRender(long ellapsedRealtime, double deltaTime) {
            super.onRender(ellapsedRealtime, deltaTime);
            mFilter.setTime((float) frameCount++ * .05f);
        }

        @Override
        public void onRenderSurfaceSizeChanged(GL10 gl, int width, int height) {
            super.onRenderSurfaceSizeChanged(gl, width, height);
            mFilter.setScreenSize(width, height);
        }

        @Override
        public void onOffsetsChanged(float v, float v1, float v2, float v3, int i, int i1) {
        }

        @Override
        public void onTouchEvent(MotionEvent motionEvent) {
        }

        @Override
        public void onRenderSurfaceCreated(EGLConfig config, GL10 gl, int width, int height) {
            super.onRenderSurfaceCreated(config, gl, width, height);
        }

        public void setTouch(float x, float y) {
            mFilter.addTouch(x, y, frameCount * .05f);
        }
    }
}
