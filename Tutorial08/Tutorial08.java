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

public class Tutorial08{ 

int SCREEN_WIDTH = 640;
int SCREEN_HEIGHT = 480;
    private static int textureID;
    private static PNGDecoder textureDecoder;
    private static ByteBuffer textureData;
    private int width;
	private int height;
	private int width1;
	private int height1;
    
    public int potenciaW(int num ){
		if( num != 0 )
	    {
	        num--;
	        num |= (num >> 1); //Or first 2 bits
	        num |= (num >> 2); //Or next 2 bits
	        num |= (num >> 4); //Or next 4 bits
	        num |= (num >> 8); //Or next 8 bits
	        num |= (num >> 16); //Or next 16 bits
	        num++;
	    }

	    return num;
	}
	
	public int potenciaH(int num){
		
		if( num != 0 )
	    {
	        num--;
	        num |= (num >> 1); //Or first 2 bits
	        num |= (num >> 2); //Or next 2 bits
	        num |= (num >> 4); //Or next 4 bits
	        num |= (num >> 8); //Or next 8 bits
	        num |= (num >> 16); //Or next 16 bits
	        num++;
	    }

	    return num;
		
	}
	
    public pngD(){
   
        if (!glfwInit()) {
            System.err.println("Falha ao inicializar GLFW!");
            System.exit(1);
        }        
        
        long win = glfwCreateWindow(640, 480, "Window", 0, 0);
        
        glfwShowWindow(win);
        glfwMakeContextCurrent(win);     
        GL.createCapabilities();
        glEnable(GL_TEXTURE_2D);      
                        
        try (InputStream inputStream = new FileInputStream("res/test.png")) {
            textureDecoder = new PNGDecoder(inputStream);
            
                width1 = textureDecoder.getWidth(); // largura da imagem
		        height1 = textureDecoder.getHeight(); // altura da imagem
			
			    width = potenciaW(width1); // largura da textura
			    height = potenciaW(height1); // altura da textura
			
			
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
            
            glBindTexture(GL_TEXTURE_2D, textureID);
            glTexImage2D( GL_TEXTURE_2D, 0, GL_RGBA, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, textureData );
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
            
            glTexImage2D(GL_TEXTURE_2D, // Texture type (1D, 2D, 3D)
                    0, // Level, always set this to zero
                    GL_RGBA, // Internal format, RGBA works best
                    textureDecoder.getWidth(), // Width of the texture in pixels
                    textureDecoder.getHeight(), // Width of the texture in pixels
                    0, // Border, always set this to zero
                    GL_RGBA, // Texture format, in our case this is RGBA (you can dynamically find the texture type with PNGDecoder)
                    GL_UNSIGNED_BYTE, // Type of the texture data, this is always unsigned byte (this should ring a bell with C/C++ programmers)
                    textureData);
                                    
            int mImageWidth = width1;
            int mImageHeight = height1;
            int mTextureWidth = width;
            int mTextureHeight = height;
            
            
          //Remove any previous transformations
            glLoadIdentity();

            //Texture coordinates
            float texTop = 0.f;
            float texBottom = (float)mImageHeight / (float)mTextureHeight;
            float texLeft = 0.f;
            float texRight = (float)mImageWidth / (float)mTextureWidth;

            //Vertex coordinates
            float quadWidth = mImageWidth;
            float quadHeight = mImageHeight;
            
             float x =  ( SCREEN_WIDTH - width1 ) / 2.f;
             float y = ( SCREEN_HEIGHT - height1) / 2.f; 
            
          //Texture coordinates
            texLeft = x / mTextureWidth;
            texRight = ( x + w ) / mTextureWidth;
            texTop =  y / mTextureHeight;
            texBottom = ( y + h ) / mTextureHeight;

            //Vertex coordinates
            quadWidth = w;
            quadHeight = h;
            
            
            
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
        new pngD();
    }

}