import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.TimeZone;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.stb.STBImage.stbi_image_free;
import static org.lwjgl.stb.STBImage.stbi_load;


import java.util.Calendar;
//import java.util.Date;


public class Desafio02{ 

int SCREEN_WIDTH = 640;
int SCREEN_HEIGHT = 480;
int SCREEN_FPS = 60;

private static float mTextureWidth;
private static float mTextureHeight;
private static int id;	 

public static float i= 1.f;  

public static float testex= 0; 


    public Desafio02(){
   
        if (!glfwInit()) {
            System.err.println("Falha ao inicializar GLFW!");
            System.exit(1);
        }        
        
        long win = glfwCreateWindow(940, 940, "Window", 0, 0);
        
        glfwShowWindow(win);
        glfwMakeContextCurrent(win);     
        GL.createCapabilities();
        glEnable(GL_TEXTURE_2D);      
                   
  		IntBuffer width = BufferUtils.createIntBuffer(1);
  		IntBuffer height = BufferUtils.createIntBuffer(1);
  		IntBuffer comp = BufferUtils.createIntBuffer(1);		
  		ByteBuffer data = stbi_load("res/clockimg2.png" , width, height, comp, 4);
        id = glGenTextures();	
  		glBindTexture(GL_TEXTURE_2D, id); 
  		glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
  		glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width.get(), height.get(), 0, GL_RGBA, GL_UNSIGNED_BYTE, data);
  		stbi_image_free(data);              
  		mTextureWidth = width.get(0);
        mTextureHeight = height.get(0); 
        
        while (!glfwWindowShouldClose(win)) {      
       
        	Calendar cal = Calendar.getInstance();        	 
        	cal.setTimeZone(TimeZone.getTimeZone("GMT-03:00"));
        	int hours = cal.get(Calendar.HOUR_OF_DAY);
        	int minutes = cal.get(Calendar.MINUTE);
        	int seconds = cal.get(Calendar.SECOND);
        	int millisecond = cal.get(Calendar.MILLISECOND);
        	
        	//System.out.println(hours+":"+minutes+":"+seconds+":"+millisecond);
        	
        	  glfwPollEvents();
        	  glClearColor(1.0f, 1.0f, 1.0f, 1.0f );
              glClear(GL_COLOR_BUFFER_BIT); 
              glMatrixMode(GL_MODELVIEW);
              glLoadIdentity();
              glColor3f(1.0f, 1.0f, 1.0f);

          	

            
            

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
            
       
            
            glTranslatef( 0.f, 0.f, 0.f );
             
            glPushMatrix();
            glRotatef(-hours * 30, 0.f, 0.f, 1.f);            
            glBegin(GL_POLYGON);
            glColor3f( 0.f, 0.f, 0.f);
            glVertex2f(-0.015f, 0.35f);
            glVertex2f(0.015f, 0.35f);
            glVertex2f(0.015f, 0f);
            glVertex2f(-0.015f, 0f);
            glEnd();                     
            glEnd();
            glPopMatrix();
            
            glPushMatrix();
            glRotatef(-minutes * 6, 0.f, 0.f, 1.f);            
            glBegin(GL_POLYGON);
            
            glVertex2f(-0.010f, 0.6f);
            glVertex2f(0.010f, 0.6f);
            glVertex2f(0.010f, 0f);
            glVertex2f(-0.010f, 0f);
            glEnd();
            glPopMatrix();
            
            glPushMatrix();
            glRotatef(-seconds * 6, 0.f, 0.f, 1.f);            
            glBegin(GL_POLYGON);            

            glVertex2f(-0.005f, 0.7f);
            glVertex2f(0.005f, 0.7f);
            glVertex2f(0.005f, 0f);
            glVertex2f(-0.005f, 0f);
            glEnd();
            glPopMatrix();
            
            
       	
         
            glfwSwapBuffers(win);            
                    
        }
        glfwTerminate();
    }

    public static void main(String[] args) {
        new Desafio02();
    }

}
