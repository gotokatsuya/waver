package com.goka.wave;

import org.rajawali3d.materials.shaders.FragmentShader;
import org.rajawali3d.materials.textures.ATexture;
import org.rajawali3d.util.RawShaderLoader;

import android.opengl.GLES20;

/**
 * Created by katsuyagoto on 15/09/03.
 */
public class RippleFragmentShader extends FragmentShader {

    public RippleFragmentShader()
    {
        super();
        mNeedsBuild = false;
        mShaderString = RawShaderLoader.fetch(R.raw.ripple_fragment_shader);
    }


}
