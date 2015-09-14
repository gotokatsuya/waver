precision mediump float;

uniform mat4 uMVPMatrix;

attribute vec4 aPosition;
attribute vec2 aTextureCoord;
attribute vec4 aColor;

uniform vec2 uTouch1;
uniform float uTouch1Start;
varying vec2 vDisplace1;

uniform float uTime;
uniform float uDuration;
uniform vec2 uRatio;
uniform float uRippleSpeed;
uniform float uRippleSize;

varying vec2 vTextureCoord;

vec2 processTouch(vec2 touch, float time) {
	vec2 pos = vec2(1.0 - (aPosition.y + .5), 1.0 - (aPosition.x + .5));
	pos *= uRatio;
	touch *= uRatio;
	vec2 diff = normalize(touch - pos);
	float dist = distance(touch, pos);
	float strength = max(0.0, uDuration-time) * 0.01;
	float timedist = min(1.0, time / (dist * uDuration));
	vec2 displ = diff * cos(dist*uRippleSize-time*uRippleSpeed) * strength * timedist;
    return displ;
}

void main() {
	gl_Position = uMVPMatrix * aPosition;
	vDisplace1 = processTouch(uTouch1, uTime - uTouch1Start);
	vTextureCoord = aTextureCoord;
}