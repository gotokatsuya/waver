package com.goka.wave;

import org.rajawali3d.materials.Material;
import org.rajawali3d.materials.textures.ATexture;
import org.rajawali3d.postprocessing.passes.EffectPass;

/**
 * Created by katsuyagoto on 15/08/30.
 */
public class RipplePass extends EffectPass {



    private float mDuration = 6.0f;
    private float[] mRatio = new float[]{1, 1};;
    private float mRippleSpeed = 10;
    private float mRippleSize = 42;
    private float[][] mTouches = new float[mNumRipples][2];;
    private float[] mTouchStartTimes = new float[mNumRipples];;
    protected ATexture mFrameBufferTexture;

    public RipplePass(ATexture frameBufferTexture) {
        super();
        mFrameBufferTexture = frameBufferTexture;
    }

    @Override
    public void setShaderParams() {
        super.setShaderParams();
        mMaterial.bindTextureByName("uFrameBufferTexture", 1, mFrameBufferTexture);
    }

    @Override
    public void setSize(int width, int height) {
        super.setSize(width, height);
     }

}
