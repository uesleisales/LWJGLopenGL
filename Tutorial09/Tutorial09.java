import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL;
//import org.lwjgl.opengl.GLContext;
//import org.lwjgl.system.MemoryUtil;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.stb.STBImage.stbi_image_free;
import static org.lwjgl.stb.STBImage.stbi_load;

import org.lwjgl.system.Configuration;

public class Tutorial09 {	

	int SCREEN_WIDTH = 640;
	int SCREEN_HEIGHT = 480;
	
    private static int id;


    public Tutorial09(){
    	
        if (!glfwInit()) {
            System.err.println("Falha ao inicializar GLFW!");
            System.exit(1);
        }        
        
        long win = glfwCreateWindow(SCREEN_WIDTH, SCREEN_HEIGHT, "Window", 0, 0);
        
        glfwShowWindow(win);
        glfwMakeContextCurrent(win);     
        GL.createCapabilities();
        glEnable(GL_TEXTURE_2D);      
                       
        
                
        while (!glfwWindowShouldClose(win)) {       	
        	
        	glfwPollEvents();
            glClear(GL_COLOR_BUFFER_BIT);            
                       
        	
    		IntBuffer width = BufferUtils.createIntBuffer(1);
    		IntBuffer height = BufferUtils.createIntBuffer(1);
    		IntBuffer comp = BufferUtils.createIntBuffer(1);		
    		ByteBuffer data = stbi_load("res/circle.png" , width, height, comp, 4);

    		
            // A cor que estamos procurando
    		// BRANCO
            byte REF_R = (byte)255;
            byte REF_G = (byte)255;
            byte REF_B = (byte)255;
            byte REF_A = (byte)255;

            for (int i = 0, len = (width.get(0) * height.get(0) * 4); i < len; i += 4) {
                byte r = data.get(i + 0);                
                byte g = data.get(i + 1);
                byte b = data.get(i + 2);
                byte a = data.get(i + 3);

                if (r == REF_R && g == REF_G && b == REF_B && a == REF_A) {
                    // A cor que iremos substituir
                	// PRETO
                    data.put(i + 0, (byte)0);
                    data.put(i + 1, (byte)0);
                    data.put(i + 2, (byte)0);
                    data.put(i + 3, (byte)255);
                }
            } 		
            
            
            // A cor que estamos procurando
            // PRETO
            byte REF_R2 = (byte)0;
            byte REF_G2 = (byte)0;
            byte REF_B2 = (byte)0;
            byte REF_A2 = (byte)255;

            for (int i = 0, len = (width.get(0) * height.get(0) * 4); i < len; i += 48) {
                byte r = data.get(i + 0);                
                byte g = data.get(i + 1);
                byte b = data.get(i + 2);
                byte a = data.get(i + 3);

                if (r == REF_R2 && g == REF_G2 && b == REF_B2 && a == REF_A2) {
                    // A cor que iremos substituir
                	// BRANCO
                    data.put(i + 0, (byte)255);
                    data.put(i + 1, (byte)255);
                    data.put(i + 2, (byte)255);
                    data.put(i + 3, (byte)255);
                }
            } 		
            
            
            
            
            
            // A cor que estamos procurando
            // CYAN
            byte REF_R3 = (byte)0;
            byte REF_G3 = (byte)255;
            byte REF_B3 = (byte)255;
            byte REF_A3 = (byte)255;

            for (int i = 0, len = (width.get(0) * height.get(0) * 4); i < len; i += 4) {
                byte r = data.get(i + 0);                
                byte g = data.get(i + 1);
                byte b = data.get(i + 2);
                byte a = data.get(i + 3);

                if (r == REF_R3 && g == REF_G3 && b == REF_B3 && a == REF_A3) {
                    // A cor que iremos substituir
                	// PRETO
                    data.put(i + 0, (byte)0);
                    data.put(i + 1, (byte)0);
                    data.put(i + 2, (byte)0);
                    data.put(i + 3, (byte)255);
                }
            } 
            
            
            
            
            id = glGenTextures();	
    		glBindTexture(GL_TEXTURE_2D, id);		
    		glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
    		glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);		
    		glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width.get(), height.get(), 0, GL_RGBA, GL_UNSIGNED_BYTE, data);
    		stbi_image_free(data);
            
            glBegin(GL_QUADS);
            glTexCoord2f(0,0);
            glVertex2f(-0.75f, 1.f);
            
            glTexCoord2f(1,0);
            glVertex2f(0.75f, 1.f);
            
            glTexCoord2f(1,1);
            glVertex2f(0.75f, -1.f);
            
            glTexCoord2f(0,1);
            glVertex2f(-0.75f, -1.f);
            glEnd();
        
            
            
            glfwSwapBuffers(win);            
                    
        }
        glfwTerminate();
    }

    public static void main(String[] args) {
    	//Configuration.DEBUG.set(true);
        new Tutorial09();
    }

}
