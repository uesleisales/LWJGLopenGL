import static org.lwjgl.glfw.GLFW.*;

import static org.lwjgl.opengl.GL11.*;


import org.lwjgl.glfw.GLFWKeyCallback;

import org.lwjgl.opengl.GL;

import input.Input;


public class Tutorial04 {


final int SCREEN_WIDTH = 640;

final int SCREEN_HEIGHT = 480;


float gCameraX = 0.f, gCameraY = 0.f;

private GLFWKeyCallback keyCallback;


public Tutorial04() {

   

        if (!glfwInit()) {

            System.err.println("Falha ao inicializar GLFW!");

            System.exit(1);

        }

        

        long win = glfwCreateWindow(SCREEN_WIDTH, SCREEN_HEIGHT, "Window", 0, 0);

        //Primeiro 0 serve para permitir que mudemos a tela para Tela cheia. (Caso queira, substituir 0 por glfwGetPrimaryMonitor()


        

        

        glfwShowWindow(win);

        glfwMakeContextCurrent(win);

        //Cria o contexto e permite que o open gl desenhe nele

        

        GL.createCapabilities();

        

        initGL();

        

        glfwSetKeyCallback(win, keyCallback = new Input());

        

      

        

        while (!glfwWindowShouldClose(win)) {

            glfwPollEvents();

            glClear(GL_COLOR_BUFFER_BIT);           

           

            

            glMatrixMode( GL_MODELVIEW );

            glPopMatrix();

            

            //Salva a matrix padrão novamente

            glPushMatrix();

            

            glTranslatef( SCREEN_WIDTH / 2.f, SCREEN_HEIGHT / 2.f, 0.f );

            

            

            //Quadrado vermelho

            glBegin( GL_QUADS );

                glColor3f( 1.f, 0.f, 0.f );

                glVertex2f( -SCREEN_WIDTH / 4.f, -SCREEN_HEIGHT / 4.f );

                glVertex2f(  SCREEN_WIDTH / 4.f, -SCREEN_HEIGHT / 4.f );

                glVertex2f(  SCREEN_WIDTH / 4.f,  SCREEN_HEIGHT / 4.f );

                glVertex2f( -SCREEN_WIDTH / 4.f,  SCREEN_HEIGHT / 4.f );

            glEnd();


            //Move para a direita da tela

            glTranslatef( SCREEN_WIDTH, 0.f, 0.f );


            //Quadrado verde

            glBegin( GL_QUADS );

                glColor3f( 0.f, 1.f, 0.f );

                glVertex2f( -SCREEN_WIDTH / 4.f, -SCREEN_HEIGHT / 4.f );

                glVertex2f(  SCREEN_WIDTH / 4.f, -SCREEN_HEIGHT / 4.f );

                glVertex2f(  SCREEN_WIDTH / 4.f,  SCREEN_HEIGHT / 4.f );

                glVertex2f( -SCREEN_WIDTH / 4.f,  SCREEN_HEIGHT / 4.f );

            glEnd();


            //Move para a parte inferior direita da tela

            glTranslatef( 0.f, SCREEN_HEIGHT, 0.f );


            //Quadrado azul

            glBegin( GL_QUADS );

                glColor3f( 0.f, 0.f, 1.f );

                glVertex2f( -SCREEN_WIDTH / 4.f, -SCREEN_HEIGHT / 4.f );

                glVertex2f(  SCREEN_WIDTH / 4.f, -SCREEN_HEIGHT / 4.f );

                glVertex2f(  SCREEN_WIDTH / 4.f,  SCREEN_HEIGHT / 4.f );

                glVertex2f( -SCREEN_WIDTH / 4.f,  SCREEN_HEIGHT / 4.f );

            glEnd();


            //Move a tela para baixo

            glTranslatef( -SCREEN_WIDTH, 0.f, 0.f );


            //Quadrado amarelo

            glBegin( GL_QUADS );

                glColor3f( 1.f, 1.f, 0.f );

                glVertex2f( -SCREEN_WIDTH / 4.f, -SCREEN_HEIGHT / 4.f );

                glVertex2f(  SCREEN_WIDTH / 4.f, -SCREEN_HEIGHT / 4.f );

                glVertex2f(  SCREEN_WIDTH / 4.f,  SCREEN_HEIGHT / 4.f );

                glVertex2f( -SCREEN_WIDTH / 4.f,  SCREEN_HEIGHT / 4.f );

            glEnd();

            

           //Precisa de 2 contextos(buffers) para trocar de um para outro, um é mostrado na tela, enquanto o OpenGL desenha no outro

           glfwSwapBuffers(win);

            

           

           //Move de posição de renderização da câmera quando o usuário pressiona w/a/s/d

            if( glfwGetKey(win, GLFW_KEY_W) == GL_TRUE ){  

            gCameraY -= 0.5f;         

             

            }else if( glfwGetKey(win, GLFW_KEY_S) == GL_TRUE ){

            gCameraY += 0.5f;

           

            }else if( glfwGetKey(win, GLFW_KEY_A) == GL_TRUE ){

            gCameraX -= 0.5f;

           

            }else if( glfwGetKey(win, GLFW_KEY_D) == GL_TRUE ){

            gCameraX += 0.5f;

            }

           

            //Tire a matriz guardada da pilha e reseta ela

            glMatrixMode( GL_MODELVIEW );

            glPopMatrix();

            glLoadIdentity();


            //Move a camera para a posição indicada

            glTranslatef( -gCameraX, -gCameraY, 0.f );


            //Salva novamente a matriz padrão com a mudança de translação na câmera

            glPushMatrix();

        

    }

                        

            }



boolean initGL() {

//Define o viewport

   glViewport( 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);


//Inicializa a Matriz de projeção

glMatrixMode(GL_PROJECTION);

glLoadIdentity();

glOrtho( 0.0, SCREEN_WIDTH, SCREEN_HEIGHT, 0.0, 1.0, -1.0 );  


//Inicializa a Matriz Modelview

glMatrixMode(GL_MODELVIEW);

glLoadIdentity();

//Salva o modelo padrão da matriz

   glPushMatrix();


//Especifica valores para os buffers de cores

glClearColor(0.f, 0.f, 0.f, 1.f);


//Checa possíveis erros

int error = glGetError();

if (error != GL_NO_ERROR) {

System.err.println("Falha ao inicializar OpenGL  initGL");

            return false;

}


return true;


}


public static void main(String[] args) {

new Tutorial04();  }}
