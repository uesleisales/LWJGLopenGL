import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GLUtil;



public class Tutorial3 {
	
	int SCREEN_WIDTH = 640;
	int SCREEN_HEIGHT = 480;
	
	
    public Tutorial3() {
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
        
        while (!glfwWindowShouldClose(win)) {
            glfwPollEvents();
            glClear(GL_COLOR_BUFFER_BIT);
//limpa todos os pixels para a cor preta
            //Bottom left red quad
            glViewport( 0, 0, SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2 );
            glBegin( GL_QUADS );
                glColor3f( 1.f, 0.f, 0.f );
                glVertex2f( -SCREEN_WIDTH / 4.f, -SCREEN_HEIGHT / 4.f );
                glVertex2f(  SCREEN_WIDTH / 4.f, -SCREEN_HEIGHT / 4.f );
                glVertex2f(  SCREEN_WIDTH / 4.f,  SCREEN_HEIGHT / 4.f );
                glVertex2f( -SCREEN_WIDTH / 4.f,  SCREEN_HEIGHT / 4.f );
            glEnd();

            //Bottom right green quad
            glViewport( SCREEN_WIDTH / 2, 0, SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2 );
            glBegin( GL_QUADS );
                glColor3f( 0.f, 1.f, 0.f );
                glVertex2f( -SCREEN_WIDTH / 4.f, -SCREEN_HEIGHT / 4.f );
                glVertex2f(  SCREEN_WIDTH / 4.f, -SCREEN_HEIGHT / 4.f );
                glVertex2f(  SCREEN_WIDTH / 4.f,  SCREEN_HEIGHT / 4.f );
                glVertex2f( -SCREEN_WIDTH / 4.f,  SCREEN_HEIGHT / 4.f );
            glEnd();

            //Top left blue quad
            glViewport( 0, SCREEN_HEIGHT / 2, SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2 );
            glBegin( GL_QUADS );
                glColor3f( 0.f, 0.f, 1.f );
                glVertex2f( -SCREEN_WIDTH / 4.f, -SCREEN_HEIGHT / 4.f );
                glVertex2f(  SCREEN_WIDTH / 4.f, -SCREEN_HEIGHT / 4.f );
                glVertex2f(  SCREEN_WIDTH / 4.f,  SCREEN_HEIGHT / 4.f );
                glVertex2f( -SCREEN_WIDTH / 4.f,  SCREEN_HEIGHT / 4.f );
            glEnd();

            //Top right yellow quad
            glViewport( SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2, SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2 );
            glBegin( GL_QUADS );
                glColor3f( 1.f, 1.f, 0.f );
                glVertex2f( -SCREEN_WIDTH / 4.f, -SCREEN_HEIGHT / 4.f );
                glVertex2f(  SCREEN_WIDTH / 4.f, -SCREEN_HEIGHT / 4.f );
                glVertex2f(  SCREEN_WIDTH / 4.f,  SCREEN_HEIGHT / 4.f );
                glVertex2f( -SCREEN_WIDTH / 4.f,  SCREEN_HEIGHT / 4.f );
            glEnd();
            glfwSwapBuffers(win);
//Precisa de 2 contextos(buffers) para trocar de um para outro, um é mostrado na tela, enquanto o OpenGL desenha no outro
        }
        glfwTerminate();
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
        new Tutorial3();
    }

}
