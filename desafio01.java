import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.Random;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;


public class Desafio01{ 

int SCREEN_WIDTH = 640;
int SCREEN_HEIGHT = 480;
int SCREEN_FPS = 60;

    public Desafio01(){
   
        if (!glfwInit()) {
            System.err.println("Falha ao inicializar GLFW!");
            System.exit(1);
        }        
        
        long win = glfwCreateWindow(940, 940, "Window", 0, 0);
        
        glfwShowWindow(win);
        glfwMakeContextCurrent(win);     
        GL.createCapabilities();
        glEnable(GL_TEXTURE_2D);      
                        
          
   
    	
     	float minX = -0.9f;
    	float maxX = 0.9f;
    	Random randx = new Random();
    	float finalX = randx.nextFloat() * (maxX - minX) + minX;
    	
    	float minY = -1.f;
    	float maxY = 1.f;    	
    	Random randy = new Random();
    	float finalY = randy.nextFloat() * (maxY - minY) + minY;
    	
    	
    
    	
        while (!glfwWindowShouldClose(win)) {      
       
        	
        	  glfwPollEvents();
              glClear(GL_COLOR_BUFFER_BIT); 
           /* glfwPollEvents();
            glClear(GL_COLOR_BUFFER_BIT);        
            glRotatef(0.01f,0.0f,0.0f,1.0f); 
            
            glPushMatrix();
            */
            glBegin(GL_QUADS);
            glTexCoord2f(0,0);
            glVertex2f(finalX, finalY);
            
            glTexCoord2f(1,0);
            glVertex2f(finalX + 0.05f, finalY);
            
            glTexCoord2f(1,1);
            glVertex2f(finalX + 0.05f, finalY - 0.05f);
            
            glTexCoord2f(0,1);
            glVertex2f(finalX, finalY - 0.05f);
            glEnd();
            
            //glPopMatrix();
           
            
            
          //  glPushMatrix();
           // glfwPollEvents();
           // glClear(GL_COLOR_BUFFER_BIT); 
            
            glMatrixMode(GL_MODELVIEW);
          
            /*glTranslatef(0.f , 0.f , -1.f);
            glRotatef(0.01f,0.0f,0.0f,1.0f); 
            glTranslatef(0.f , 0.f , 1.f);
            */
            glBegin(GL_TRIANGLES);      
            glVertex2f(-0.1f, 0.f);
            glVertex2f(0.1f, 0.f);
            glVertex2f(0.f, 0.2f); 
            glEnd();
        	
          
            
           // glPopMatrix();
            
            
        	//System.out.println(finalX);
        	//System.out.println(finalY);
        	

            
            
            glfwSwapBuffers(win);            
                    
        }
        glfwTerminate();
    }

    public static void main(String[] args) {
        new Desafio01();
    }

}
