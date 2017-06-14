import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.opengl.GL;
import input.Input;
public class Tutorial02 {

final int SCREEN_WIDTH = 640;
final int SCREEN_HEIGHT = 480;

int COLOR_MODE_CYAN = 0;
int COLOR_MODE_MULTI = 1;

int gColorMode = COLOR_MODE_CYAN;
float gProjectionScale = 1.f;

// This prevents our window from crashing later on.
private GLFWKeyCallback keyCallback;

public Tutorial02() {
   
        if (!glfwInit()) {
            System.err.println("Falha ao inicializar GLFW!");
            System.exit(1);
        }
        
        long win = glfwCreateWindow(SCREEN_WIDTH, SCREEN_HEIGHT, "Window", 0, 0);
// Primeiro 0 serve para permitir que mudemos a tela para Tela cheia. (Caso queira, substituir 0 por glfwGetPrimaryMonitor()

        
        
        glfwShowWindow(win);
        glfwMakeContextCurrent(win);
//cria o contexto e permite que o open gl desenhe nele
        GL.createCapabilities();
        
        initGL();
        
        glfwSetKeyCallback(win, keyCallback = new Input());
        
      
        
        while (!glfwWindowShouldClose(win)) {
            glfwPollEvents();
            glClear(GL_COLOR_BUFFER_BIT);
            
           // Input.keys[GLFW_KEY_SPACE]
            
            glMatrixMode( GL_MODELVIEW );
            glLoadIdentity();
            
            //ByteBuffer vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
            glTranslatef( SCREEN_WIDTH / 2.f, SCREEN_HEIGHT / 2.f, 0.f );
            
            
            if( gColorMode == COLOR_MODE_CYAN ) 
            {
            //Solid Cyan            
            glBegin( GL_QUADS );
            glColor3f( 0.f, 1.f, 1.f );
            glVertex2f( -50.f, -50.f );
            glVertex2f(  50.f, -50.f );
            glVertex2f(  50.f,  50.f );
            glVertex2f( -50.f,  50.f );
            glEnd();
           
            
            }
            else
            {
                //RYGB Mix  MULTICOLOR
                glBegin( GL_QUADS );
                    glColor3f( 1.f, 0.f, 0.f ); glVertex2f( -50.f, -50.f );
                    glColor3f( 1.f, 1.f, 0.f ); glVertex2f(  50.f, -50.f );
                    glColor3f( 0.f, 1.f, 0.f ); glVertex2f(  50.f,  50.f );
                    glColor3f( 0.f, 0.f, 1.f ); glVertex2f( -50.f,  50.f );
                glEnd();
            }
            
            
            /*if(Input.isKeyDown(GLFW_KEY_SPACE)){
            gViewportMode++;
            if( gViewportMode > VIEWPORT_MODE_RADAR )
                {
                    gViewportMode = VIEWPORT_MODE_FULL;
                }            
            }*/
                       
              
        if(glfwGetKey(win, GLFW_KEY_Q) == GL_TRUE) 
        {
        //Toggle color mode
        if( gColorMode == COLOR_MODE_CYAN ) 
        {
                  gColorMode = COLOR_MODE_MULTI;
        }
               else 
               {
                   gColorMode = COLOR_MODE_CYAN;
               }
        }  
        else if( glfwGetKey(win, GLFW_KEY_E) == GL_TRUE )
        {
            //Cycle through projection scales
            if( gProjectionScale == 1.f )
            {
                //Zoom out
                gProjectionScale = 2.f;
            }
            else if( gProjectionScale == 2.f )
            {
                //Zoom in
                gProjectionScale = 0.5f;
            }
            else if( gProjectionScale == 0.5f )
            {
                //Regular zoom
                gProjectionScale = 1.f;
            }

            //Update projection matrix
            glMatrixMode( GL_PROJECTION );
            glLoadIdentity();
            glOrtho( 0.0f, SCREEN_WIDTH * gProjectionScale, SCREEN_HEIGHT * gProjectionScale, 0.0f, 1.0f, -1.0f );
            //glOrtho( -SCREEN_WIDTH , SCREEN_WIDTH * gProjectionScale, SCREEN_HEIGHT * gProjectionScale, -SCREEN_HEIGHT, 1.0, -1.0 );
        }
        //Precisa de 2 contextos(buffers) para trocar de um para outro, um é mostrado na tela, enquanto o OpenGL desenha no outro
        glfwSwapBuffers(win);
    }
                        
            }


boolean initGL() {
// Set the viewport
//glViewport(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);

// Initialize Projection Matrix
glMatrixMode(GL_PROJECTION);
glLoadIdentity();
glOrtho( 0.0, SCREEN_WIDTH, SCREEN_HEIGHT, 0.0, 1.0, -1.0 );  

// Initialize Modelview Matrix
glMatrixMode(GL_MODELVIEW);
glLoadIdentity();

// Initialize clear color
glClearColor(0.f, 0.f, 0.f, 1.f);

// Check for error
int error = glGetError();
if (error != GL_NO_ERROR) {
// System.out.printf( "Error initializing OpenGL! %s\n", GLUtil.Er(
// error ) );
return false;
}

return true;

}

public static void main(String[] args) {
new Tutorial02();

}

}