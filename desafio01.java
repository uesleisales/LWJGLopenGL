import org.joml.Vector3f;
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

public static float i= 1.f;  

public static float testex= 0; 
public static float testey= 0; 
public static float auxX= 0; 
public static int contadorX= 1; 
public static float auxY= 0; 
public static int contadorY= 1; 
public static float replaceX= 0; 
public static float replaceY= 0; 
public static float auxX1= 0; 





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
    	float quadradoX = randx.nextFloat() * (maxX - minX) + minX;
    	
    	float minY = -1.f;
    	float maxY = 1.f;    	
    	Random randy = new Random();
    	float quadradoY = randy.nextFloat() * (maxY - minY) + minY;
    	
    	
    
    	
        while (!glfwWindowShouldClose(win)) {      
       
        	
        	  glfwPollEvents();
              glClear(GL_COLOR_BUFFER_BIT); 
              glMatrixMode(GL_MODELVIEW);
              glLoadIdentity();
              
            /* glfwPollEvents();
            glClear(GL_COLOR_BUFFER_BIT);        
            glRotatef(0.01f,0.0f,0.0f,1.0f); 
            
            glPushMatrix();
            */
            
            
            //glPopMatrix();
           
            
            
           //  glPushMatrix();
           // glfwPollEvents();
           // glClear(GL_COLOR_BUFFER_BIT); 
            
            
          
            /*glTranslatef(0.f , 0.f , -1.f);
            glRotatef(0.01f,0.0f,0.0f,1.0f); 
            glTranslatef(0.f , 0.f , 1.f);
            */
      
            /*if (i<300.f){
        		i+=0.01f;
        	}     
            */
            
              Vector3f square = new Vector3f(quadradoX + 0.025f, quadradoY - 0.025f, 0);
              Vector3f triangle = new Vector3f(0.0f, 0.1f, 0);  
              double angle = Math.toDegrees(square.angle(triangle));
              
              
            /*if(quadradoX > 0){
            	auxX = 	quadradoX / 10000; 
            	
            	if(contadorX < 10000){            	 
            	replaceX = auxX * contadorX;
            	contadorX = contadorX + 1;            	
              }
            }
            */
            
            if(quadradoX < 0 || quadradoX > 0){
            	auxX = 	quadradoX / 10000; 
            	
            	if(contadorX < 10000){            	 
            	replaceX = auxX * contadorX;
            	contadorX = contadorX + 1;            	
              }
            }
                       
//////////////////////////
            
           /* if(quadradoY > 0){
            	auxY = 	quadradoY / 10000; 
            	
            	if(contadorY < 10000){            	 
            	replaceY = auxY * contadorY;
            	contadorY = contadorY + 1;            	
              }
            }
        */
            if(quadradoY < 0 || quadradoY > 0){
            	auxY = 	quadradoY / 10000; 
            	
            	if(contadorY < 10000){            	 
            	replaceY = auxY * contadorY;
            	contadorY = contadorY + 1;            	
              }
            }
 
            
glPushMatrix ( ); // (salva a matrix) para mover o objeto independente do resto da cena 

	/*
	if(quadradoX > 0){
	glRotatef ((float)-angle, 0.f, 0.f, 1.f );	
	}else{
	glRotatef ((float)angle, 0.f, 0.f, 1.f );	
	}
	*/


	glTranslatef (replaceX+0.025f, replaceY-0.1f, 0.f);
	
	glBegin(GL_TRIANGLES);    
    glColor3f( 1.f, 1.f, 1.f );
	glVertex2f(-0.1f, 0.f);
	glVertex2f(0.1f, 0.f);
	glVertex2f(0.f, 0.2f); 
	glEnd();	

glPopMatrix ( ); // (carrega a ultima matrix salva) volta para o resto da cena
         	

            
           // glPopMatrix();
            
            
        	//System.out.println(quadradoX);
        	//System.out.println(quadradoY);
        	

glBegin(GL_QUADS);
glColor3f( 0.f, 0.f, 1.f );
glTexCoord2f(0,0);
glVertex2f(quadradoX, quadradoY);

glTexCoord2f(1,0);
glVertex2f(quadradoX + 0.05f, quadradoY);

glTexCoord2f(1,1);
glVertex2f(quadradoX + 0.05f, quadradoY - 0.05f);

glTexCoord2f(0,1);
glVertex2f(quadradoX, quadradoY - 0.05f);
glEnd();
            
            glfwSwapBuffers(win);            
                    
        }
        glfwTerminate();
    }

    public static void main(String[] args) {
        new Desafio01();
    }

}
