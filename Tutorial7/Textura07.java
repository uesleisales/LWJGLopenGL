import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.opengl.GL;




public class Textura07{
int SCREEN_WIDTH = 640;
int SCREEN_HEIGHT = 480;
int
VIEWPORT_MODE_FULL = 0;
int VIEWPORT_MODE_HALF_CENTER = 1;
int VIEWPORT_MODE_HALF_TOP = 2;
int VIEWPORT_MODE_QUAD = 3;
int VIEWPORT_MODE_RADAR = 4;
// This prevents our window from crashing later on.
private GLFWKeyCallback keyCallback;
    public Textura07() {
   
        if (!glfwInit()) {
            System.err.println("Falha ao inicializar GLFW!");
            System.exit(1);
        }
        System.out.println(VIEWPORT_MODE_FULL);
        long win = glfwCreateWindow(SCREEN_WIDTH, SCREEN_HEIGHT, "Window", 0, 0);
// Primeiro 0 serve para permitir que mudemos a tela para Tela cheia. (Caso queira, substituir 0 por glfwGetPrimaryMonitor()

        
        
        glfwShowWindow(win);
        glfwMakeContextCurrent(win);
//cria o contexto e permite que o open gl desenhe nele
        GL.createCapabilities();
        
        initGL();
        glEnable(GL_TEXTURE_2D);

        
      int  gViewportMode = VIEWPORT_MODE_FULL;
        
      
      Textura tex = new Textura("./res/arrows.png");
      
        while (!glfwWindowShouldClose(win)) {
            glfwPollEvents();
            glClear(GL_COLOR_BUFFER_BIT);
           // Input.keys[GLFW_KEY_SPACE]
            if(glfwGetKey(win, GLFW_KEY_SPACE) == GL_TRUE) {
            gViewportMode++;
            if( gViewportMode > VIEWPORT_MODE_RADAR )
                {
                    gViewportMode = VIEWPORT_MODE_FULL;
                }            
        
            }
            
 
            
                
           //Abaixo a esquerda red quad
           
          
           glViewport( 0, 0, SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2 );
           glBegin( GL_QUADS );
           	   glTexCoord2f(0.5f,0);
               glVertex2f( -SCREEN_WIDTH / 4.f, -SCREEN_HEIGHT / 4.f );
               glTexCoord2f(0.5f,0.5f);
               glVertex2f(  SCREEN_WIDTH / 4.f, -SCREEN_HEIGHT / 4.f );
               glTexCoord2f(0,0.5f);
               glVertex2f(  SCREEN_WIDTH / 4.f,  SCREEN_HEIGHT / 4.f );
               glTexCoord2f(0,0);
               glVertex2f( -SCREEN_WIDTH / 4.f,  SCREEN_HEIGHT / 4.f );
           glEnd();
   
       //Abaixo a direita green quad
       glViewport( SCREEN_WIDTH / 2, 0, SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2 );
       glBegin( GL_QUADS );
           glTexCoord2f(0.5f,0.5f);
           glVertex2f( -SCREEN_WIDTH / 4.f, -SCREEN_HEIGHT / 4.f );
           glTexCoord2f(0,0.5f);
           glVertex2f(  SCREEN_WIDTH / 4.f, -SCREEN_HEIGHT / 4.f );
           glTexCoord2f(0,0);
           glVertex2f(  SCREEN_WIDTH / 4.f,  SCREEN_HEIGHT / 4.f );
           glTexCoord2f(0.5f,0);
           glVertex2f( -SCREEN_WIDTH / 4.f,  SCREEN_HEIGHT / 4.f );
       glEnd();
       //Acima a esquerda blue quad
       glViewport( 0, SCREEN_HEIGHT / 2, SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2 );
       glBegin( GL_QUADS );
        	
           glTexCoord2f(0,0);
           glVertex2f( -SCREEN_WIDTH / 4.f, -SCREEN_HEIGHT / 4.f );
           glTexCoord2f(.5f,0);
           glVertex2f(  SCREEN_WIDTH / 4.f, -SCREEN_HEIGHT / 4.f );
           glTexCoord2f(.5f,.5f);
           glVertex2f(  SCREEN_WIDTH / 4.f,  SCREEN_HEIGHT / 4.f );
           glTexCoord2f(0,.5f);
           glVertex2f( -SCREEN_WIDTH / 4.f,  SCREEN_HEIGHT / 4.f );
       glEnd();
       //Acima a direita yellow quad
       glViewport( SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2, SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2 );
       glBegin( GL_QUADS );
           glTexCoord2f(0,0.5f);
           glVertex2f( -SCREEN_WIDTH / 4.f, -SCREEN_HEIGHT / 4.f );
           glTexCoord2f(0,0);
           glVertex2f(  SCREEN_WIDTH / 4.f, -SCREEN_HEIGHT / 4.f );
           glTexCoord2f(0.5f,0);
           glVertex2f(  SCREEN_WIDTH / 4.f,  SCREEN_HEIGHT / 4.f );
           glTexCoord2f(0.5f,.5f);
           glVertex2f( -SCREEN_WIDTH / 4.f,  SCREEN_HEIGHT / 4.f );
       glEnd();         
         
           
           
              
           
            
            //Precisa de 2 contextos(buffers) para trocar de um para outro, um é mostrado na tela, enquanto o OpenGL desenha no outro
            glfwSwapBuffers(win);
            
        }        
        }      
       
   

    boolean initGL()
    {
        //Set the viewport
        glViewport( 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT );

        //Initialize Projection Matrix
        glMatrixMode( GL_PROJECTION );
        glLoadIdentity();
        glOrtho( -SCREEN_WIDTH, SCREEN_WIDTH, SCREEN_HEIGHT, - SCREEN_HEIGHT, 1.0, -1.0 );

        //Initialize Modelview Matrix
        glMatrixMode( GL_MODELVIEW );
        glLoadIdentity();

        //Initialize clear color
        glClearColor( 0.f, 0.f, 0.f, 1.f );

        //Check for error
        int error = glGetError();
        if( error != GL_NO_ERROR )
        {
//            System.out.printf( "Error initializing OpenGL! %s\n", GLUtil.Er( error ) );
            return false;
        }

        return true;
        
    }
    
 
    public static void main(String[] args) {
        new Textura07();
       
    }

    
   
}