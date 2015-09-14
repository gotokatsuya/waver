package com.goka.wave;

import org.rajawali3d.materials.shaders.VertexShader;
import org.rajawali3d.postprocessing.passes.BlendPass;
import org.rajawali3d.util.RawShaderLoader;

import android.opengl.GLES20;

/**
 * Created by katsuyagoto on 15/09/03.
 */
public class RippleVertexShader extends VertexShader {

    private static final int mNumRipples = 1;

    private int muDurationHandle;
    private int muRatioHandle;
    private int muRippleSpeedHandle;
    private int muRippleSizeHandle;
    private int[] muTouchHandles = new int[mNumRipples];;
    private int[] muTouchStartHandles = new int[mNumRipples];;

    private float mDuration = 6.0f;
    private float[] mRatio = new float[]{1, 1};;
    private float mRippleSpeed = 10;
    private float mRippleSize = 42;
    private float[][] mTouches = new float[mNumRipples][2];;
    private float[] mTouchStartTimes = new float[mNumRipples];;

    public RippleVertexShader() {
        super();
        for (int i = 0; i < mNumRipples; ++i) {
            mTouchStartTimes[i] = -1000;
        }
        mNeedsBuild = false;
        mShaderString = RawShaderLoader.fetch(R.raw.ripple_vertex_shader);
    }

    @Override
    public void applyParams() {
        super.applyParams();
        for (int i = 0; i < mNumRipples; ++i) {
            GLES20.glUniform2fv(muTouchHandles[i], 1, mTouches[i], 0);
            GLES20.glUniform1f(muTouchStartHandles[i], mTouchStartTimes[i]);
        }
        GLES20.glUniform1f(muDurationHandle, mDuration);
        GLES20.glUniform2fv(muRatioHandle, 1, mRatio, 0);
        GLES20.glUniform1f(muRippleSizeHandle, mRippleSize);
        GLES20.glUniform1f(muRippleSpeedHandle, mRippleSpeed);
    }

    @Override
    public void setLocations(int programHandle) {
        for (int i = 0; i < mNumRipples; ++i) {
            muTouchHandles[i] = getUniformLocation(programHandle, "uTouch" + i);
            muTouchStartHandles[i] = getUniformLocation(programHandle, "uTouch" + i + "Start");
        }
        muDurationHandle = getUniformLocation(programHandle, "uDuration");
        muRatioHandle = getUniformLocation(programHandle, "uRatio");
        muRippleSizeHandle = getUniformLocation(programHandle, "uRippleSize");
        muRippleSpeedHandle = getUniformLocation(programHandle, "uRippleSpeed");
        super.setLocations(programHandle);
    }

}
