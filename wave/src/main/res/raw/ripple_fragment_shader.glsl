precision highp float;

varying vec2 vTextureCoord;

uniform sampler2D uFrameBufferTexture;

varying vec2 vDisplace1;

void main() {
    vec2 texCoord = vTextureCoord;
    texCoord += vDisplace1;
    gl_FragColor = texture2D(uFrameBufferTexture, texCoord);
}