import de.matthiasmann.twl.utils.PNGDecoder;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

//import org.lwjgl.opengl.GLContext;
//import org.lwjgl.system.MemoryUtil;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public class Tutorial11{ 

int SCREEN_WIDTH = 640;
int SCREEN_HEIGHT = 480;
    private static int textureID;
    private static PNGDecoder textureDecoder;
    private static ByteBuffer textureData;
    private int width;
	private int height;
	
    

	
    public Tutorial11(){
   
        if (!glfwInit()) {
            System.err.println("Falha ao inicializar GLFW!");
            System.exit(1);
        }        
        
        long win = glfwCreateWindow(640, 480, "Window", 0, 0);
        
        glfwShowWindow(win);
        glfwMakeContextCurrent(win);     
        GL.createCapabilities();
        glEnable(GL_TEXTURE_2D);      
                        
        try (InputStream inputStream = new FileInputStream("res/circle.png")) {
            textureDecoder = new PNGDecoder(inputStream);
            
                width = textureDecoder.getWidth(); // largura da imagem
		        height = textureDecoder.getHeight(); // altura da imagem
			
			
			
            textureData = BufferUtils.createByteBuffer(4 * width * height);
            textureDecoder.decode(textureData, width * 4, PNGDecoder.Format.RGBA);
            textureData.flip();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
                
        while (!glfwWindowShouldClose(win)) {      
       
        glfwPollEvents();
           // glClear(GL_COLOR_BUFFER_BIT);           
              glClearColor( 0.f, 1.f, 0.f, 1.f );
             // rotate at center?
            //GL11.glRotatef(0.1f, 0.0f, 0.0f, 1.0f); // Rotate On The Z Axis
         
           //GL11.glPushMatrix();
           //GL11.glTranslatef(400,300, 0.0f);
            
              
            int gFiltering = GL_LINEAR;
              
            	
            	
            	if(glfwGetKey(win, GLFW_KEY_Q) == GL_TRUE){
            		
            		
            		
            		glBindTexture(GL_TEXTURE_2D, textureID);
            		
            		if( gFiltering != GL_LINEAR )
            		{
            			glTexImage2D( GL_TEXTURE_2D, 0, GL_RGBA, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, textureData );
            			glTexParameteri( GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR );
            			glTexParameteri( GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR );
            			
            			gFiltering = GL_LINEAR;
            		}
            		else
            		{
            			glBindTexture(GL_TEXTURE_2D, textureID);
            			glTexImage2D( GL_TEXTURE_2D, 0, GL_RGBA, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, textureData );             
            			glTexParameteri( GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST );
            			glTexParameteri( GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST );
            			
            			gFiltering = GL_NEAREST;
            		}  
            		
            		glBindTexture(GL_TEXTURE_2D, 0);
            		
            	}
            
      
            glTexImage2D(GL_TEXTURE_2D, // Texture type (1D, 2D, 3D)
                    0, // Level, always set this to zero
                    GL_RGBA, // Internal format, RGBA works best
                    textureDecoder.getWidth(), // Width of the texture in pixels
                    textureDecoder.getHeight(), // Width of the texture in pixels
                    0, // Border, always set this to zero
                    GL_RGBA, // Texture format, in our case this is RGBA (you can dynamically find the texture type with PNGDecoder)
                    GL_UNSIGNED_BYTE, // Type of the texture data, this is always unsigned byte (this should ring a bell with C/C++ programmers)
                    textureData);
                                    
          
            
            
            
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
                    
        }
        glfwTerminate();
    }

    public static void main(String[] args) {
        new Tutorial11();
    }

}