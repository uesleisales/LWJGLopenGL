import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

import java.nio.ByteBuffer;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.stb.STBImage.stbi_image_free;
import static org.lwjgl.stb.STBImage.stbi_load;

//import org.lwjgl.opengl.GLContext;
//import org.lwjgl.system.MemoryUtil;
//import static org.lwjgl.system.MemoryUtil.*;
import java.nio.IntBuffer;
import org.lwjgl.system.Configuration;

public class NewTutorial07 {

    int SCREEN_WIDTH = 640;
    int SCREEN_HEIGHT = 480;
    private static int id;
    private static float mTextureWidth;
    private static float mTextureHeight;
    private LFRect[] gArrowClips = new LFRect[4];

    public NewTutorial07() {

        if (!glfwInit()) {
            System.err.println("Falha ao inicializar GLFW!");
            System.exit(1);
        }

        long win = glfwCreateWindow(SCREEN_WIDTH, SCREEN_HEIGHT, "Janela", 0, 0);

        glfwShowWindow(win);
        glfwMakeContextCurrent(win);
        GL.createCapabilities();
        glEnable(GL_TEXTURE_2D);

        //Define a viewport
        glViewport( 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT );

        //Inicializa a Matriz de projeção
        glMatrixMode( GL_PROJECTION );
        glLoadIdentity();
        glOrtho( 0.0, SCREEN_WIDTH, SCREEN_HEIGHT, 0.0, 1.0, -1.0 );

        //Inicializa a Matriz Modelview
        glMatrixMode( GL_MODELVIEW );
        glLoadIdentity();

       

        while (!glfwWindowShouldClose(win)) {

            glfwPollEvents();
            glClear(GL_COLOR_BUFFER_BIT);

    			
    		IntBuffer width = BufferUtils.createIntBuffer(1);
    		IntBuffer height = BufferUtils.createIntBuffer(1);
    		IntBuffer comp = BufferUtils.createIntBuffer(1);		
    		ByteBuffer data = stbi_load("res/arrows.png" , width, height, comp, 4);
    		id = glGenTextures();	
    		glBindTexture(GL_TEXTURE_2D, id);		
    		glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
    		glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);		
    		glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width.get(), height.get(), 0, GL_RGBA, GL_UNSIGNED_BYTE, data);
    		stbi_image_free(data);
            
    		mTextureWidth = width.get(0);
            mTextureHeight = height.get(0);            
          

            //Define o recorte dos retângulos
            gArrowClips[0] = new LFRect();
            gArrowClips[0].x = 0.f;
            gArrowClips[0].y = 0.f;
            gArrowClips[0].w = 128.f;
            gArrowClips[0].h = 128.f;

            gArrowClips[1] = new LFRect();
            gArrowClips[1].x = 128.f;
            gArrowClips[1].y = 0.f;
            gArrowClips[1].w = 128.f;
            gArrowClips[1].h = 128.f;

            gArrowClips[2] = new LFRect();
            gArrowClips[2].x = 0.f;
            gArrowClips[2].y = 128.f;
            gArrowClips[2].w = 128.f;
            gArrowClips[2].h = 128.f;

            gArrowClips[3] = new LFRect();
            gArrowClips[3].x = 128.f;
            gArrowClips[3].y = 128.f;
            gArrowClips[3].w = 128.f;
            gArrowClips[3].h = 128.f;

            //Renderiza as setas            
            render(0.f, 0.f, gArrowClips[0]);
            render(SCREEN_WIDTH - gArrowClips[1].w, 0.f, gArrowClips[1]);
            render(0.f, SCREEN_HEIGHT - gArrowClips[2].h, gArrowClips[2]);
            render(SCREEN_WIDTH - gArrowClips[3].w, SCREEN_HEIGHT - gArrowClips[3].h, gArrowClips[3]);

            glfwSwapBuffers(win);

        }
        glfwTerminate();
    }

    private void render(float x, float y, LFRect clip) {
    	//Remova quaisquer transformações anteriores
        glLoadIdentity();

        //Coordenadas de textura
        float texTop = 0.f;
        float texBottom = 1.f;
        float texLeft = 0.f;
        float texRight = 1.f;

        //Coordenadas do vertex
        float quadWidth = mTextureWidth/2;
        float quadHeight = mTextureHeight/2;

        //Manipula o recorte
        if (clip != null) {        	
            //Coordenadas de textura
            texLeft = clip.x / mTextureWidth;
            texRight = (clip.x + clip.w) / mTextureWidth;
            texTop = clip.y / mTextureHeight;
            texBottom = (clip.y + clip.h) / mTextureHeight;

            //Coordenadas do vertex
            quadWidth = clip.w;
            quadHeight = clip.h;
        }
        
        //Move para o ponto de renderização
        glTranslatef( x, y, 0.f );
        
        //Define o ID da textura
        glBindTexture(GL_TEXTURE_2D, id);

        //Renderiza o quadrado com textura
        glBegin(GL_QUADS);        
        glTexCoord2f(  texLeft,    texTop ); glVertex2f(       0.f,        0.f );
        glTexCoord2f( texRight,    texTop ); glVertex2f( quadWidth,        0.f );
        glTexCoord2f( texRight, texBottom ); glVertex2f( quadWidth, quadHeight );
        glTexCoord2f(  texLeft, texBottom ); glVertex2f(       0.f, quadHeight );
        glEnd();
    }

  
    private class LFRect {
        float x;
        float y;
        float w;
        float h;
    }
        
  
    public static void main(String[] args) {
    	//Configuration.DEBUG.set(true);
        new NewTutorial07();
    }

}
