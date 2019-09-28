/*******************************************************************************
 * Copyright 2019 metaphore
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package com.crashinvaders.vfx.effects;

import com.badlogic.gdx.Gdx;
import com.crashinvaders.vfx.gl.VfxGLUtils;

public class ChromaticAberrationEffect extends ShaderVfxEffect {

    private static final String U_TEXTURE0 = "u_texture0";
    private static final String U_MAX_DISTORTION = "u_maxDistortion";

    private float maxDistortion = 1.2f;

    public ChromaticAberrationEffect(int passes) {
        super(VfxGLUtils.compileShader(
                Gdx.files.classpath("shaders/screenspace.vert"),
                Gdx.files.classpath("shaders/chromatic-aberration.frag"),
                "#define PASSES " + passes));
        rebind();
    }

    @Override
    public void rebind() {
        super.rebind();
        program.begin();
        program.setUniformi(U_TEXTURE0, TEXTURE_HANDLE0);
        program.setUniformf(U_MAX_DISTORTION, maxDistortion);
        program.end();
    }

    public float getMaxDistortion() {
        return maxDistortion;
    }

    public void setMaxDistortion(float maxDistortion) {
        this.maxDistortion = maxDistortion;
        setUniform(U_MAX_DISTORTION, maxDistortion);
    }
}
