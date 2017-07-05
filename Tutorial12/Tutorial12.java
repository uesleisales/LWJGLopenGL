import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.stb.STBImage.stbi_image_free;
import static org.lwjgl.stb.STBImage.stbi_load;

public class Tutorial12{ 

int SCREEN_WIDTH = 640;
int SCREEN_HEIGHT = 480;
int SCREEN_FPS = 60;

private static int id;	   
private static float gAngle = 0.f;
	
    public Tutorial12(){
   
        if (!glfwInit()) {
            System.err.println("Falha ao inicializar GLFW!");
            System.exit(1);
        }        
        
        long win = glfwCreateWindow(640, 480, "Window", 0, 0);
        
        glfwShowWindow(win);
        glfwMakeContextCurrent(win);     
        GL.createCapabilities();
        glEnable(GL_TEXTURE_2D);      
                        
                        
        while (!glfwWindowShouldClose(win)) {      
       

            //Rotate
            gAngle += 360.f / SCREEN_FPS;

            //Cap angle
            if( gAngle > 360.f )
            {
                gAngle -= 360.f;
            }
        	
            
            glfwPollEvents();
            glClear(GL_COLOR_BUFFER_BIT);    			

        	
    		IntBuffer width = BufferUtils.createIntBuffer(1);
    		IntBuffer height = BufferUtils.createIntBuffer(1);
    		IntBuffer comp = BufferUtils.createIntBuffer(1);		
    		ByteBuffer data = stbi_load("res/arrow.png" , width, height, comp, 4);
        id = glGenTextures();	
    		glBindTexture(GL_TEXTURE_2D, id); 
    		glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
    		glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width.get(), height.get(), 0, GL_RGBA, GL_UNSIGNED_BYTE, data);
    		stbi_image_free(data);
            
    		
    		
            //Move to rendering point
            glTranslatef( 0.f, 0.f, 0.f );

            //Rotate around rendering point
            glRotatef( gAngle , 0.f, 0.f, 1.f );
    		
    		
    		
    		glBegin(GL_QUADS);
            glTexCoord2f(0,0);
            glVertex2f(-1.f, 1.f);
            
            glTexCoord2f(1,0);
            glVertex2f(1.f, 1.f);
            
            glTexCoord2f(1,1);
            glVertex2f(1.f, -1.f);
            
            glTexCoord2f(0,1);
            glVertex2f(-1.f, -1.f);
            glEnd();              
            
            
            
            glfwSwapBuffers(win);            
                    
        }
        glfwTerminate();
    }

    public static void main(String[] args) {
        new Tutorial12();
    }

}
