import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.opengl.GL;

public class Main {

    public Main(){
        if (!glfwInit()) {
            System.err.println("Falha ao inicializar GLFW!");
            System.exit(1);
        }
        
        long win = glfwCreateWindow(640, 480, "Window", 0, 0);
        // Primeiro 0 serve para permitir que mudemos a tela para Tela cheia. (Caso queira, substituir 0 por glfwGetPrimaryMonitor()

        glfwShowWindow(win);
        glfwMakeContextCurrent(win);
        
        
       //cria o contexto e permite que o open gl desenhe nele
        GL.createCapabilities();
        glEnable(GL_TEXTURE_2D);
        
        
        
        
        Textura tex = new Textura("./res/teste3.png");
        
 
        
        while (!glfwWindowShouldClose(win)) {
        	
        	
   
            glfwPollEvents();
            glClear(GL_COLOR_BUFFER_BIT);
            
            
            tex.bind();
            //limpa todos os pixels para a cor preta
            glBegin(GL_QUADS);
	            glTexCoord2f(0,0);
	            glVertex2f(-0.5f, 0.5f);
	            
	            glTexCoord2f(1,0);
	            glVertex2f(0.5f, 0.5f);
	            
	            glTexCoord2f(1,1);
	            glVertex2f(0.5f, -0.5f);
	            
	            glTexCoord2f(0,1);
	            glVertex2f(-0.5f, -0.5f);
            glEnd();
            glfwSwapBuffers(win);
        //Precisa de 2 contextos(buffers) para trocar de um para outro, um é mostrado na tela, enquanto o OpenGL desenha no outro
        }
        glfwTerminate();
    }

    public static void main(String[] args) {
        new Main();
    }

}
