precision mediump float;

uniform samplerCube u_textureUnit;
varying vec3 v_Position;

void main()
{
     gl_FragColor = textureCube(u_textureUnit, v_Position);
}
