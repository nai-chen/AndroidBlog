precision mediump float;

uniform sampler2D u_textureUnit;
varying vec2 v_TextureCoordinates;

void main()
{
     gl_FragColor = texture2D(u_textureUnit, v_TextureCoordinates);
}
