----------------------/*
Tutorial 04 - Percorrendo e pilha de matrizes

Em muitos, se não a maioria dos jogos, os ambientes de jogo são maiores do que uma tela. Aqui vamos aprender a percorrer grandes ambientes.


Ao lidar com grandes ambientes, você precisa de algum tipo de câmera para definir a área que você está fazendo. Aqui vamos salvar as transformações para a matriz modelview para fazer isso.


Neste tutorial, vamos aplicar transformação de translação para a matriz modelview para percorrer o ambiente. Desta vez, no entanto, ao invés de chamar glLoadIdentity () e glOrtho () todas as vezes que fizemos no tutorial Matrizes e Colorir Polígonos, vamos empurrar uma cópia da matriz inicial do modelo percorrendo para a posição da câmera na pilha para salvá-la para quando precisarmos aplicar transformações nela.
*/--------------------



----------------------
float gCameraX = 0.f, gCameraY = 0.f;
----------------------/*
No começo do código  definimos coordenadas x / y para a nossa câmera. A função initGL() é praticamente a mesma, só agora há uma chamada para glPushMatrix().

Uma vez que as transformações geométricas no espaço são representadas por matrizes, o uso de uma pilha de matrizes de transformação ajuda a lembrar a seqüência de transformações realizadas. No OpenGL, esta facilidade é provida pelas funções glPushMatrix(), que insere a matriz de transformação corrente na pilha, e glPopMatrix(), que retira a matriz do topo da pilha e torna esta última a matriz de transformação corrente.
*/--------------------



----------------------
glMatrixMode( GL_MODELVIEW );

glPopMatrix();

glLoadIdentity();

glTranslatef( -gCameraX, -gCameraY, 0.f );


glPushMatrix();
----------------------/*
"glMatrixMode" Especifica qual pilha de matriz é o destino para operações de matriz subseqüentes.
"GL_MODELVIEW" Aplica as operações de matriz subsequentes à pilha de matriz modelview.

"glPopMatrix" Exibe a pilha de matriz atual, substituindo a matriz atual por aquela abaixo da pilha.

"GlLoadIdentity" substitui a matriz atual pela matriz de identidade.

"glTranslatef" move todas as coordenadas dos objetos ao longo dos eixos coordenados.

"GlPushMatrix" empurra a pilha de matriz atual para baixo por um, duplicando a matriz atual. Ou seja, depois de uma chamada GlPushMatrix, a matriz em cima da pilha é idêntica à que está abaixo dela.


Desde que mudamos a posição da câmera quando o usuário pressionou uma tecla, precisamos mudar nossa matriz de câmera padrão.

Primeiro, removemos a antiga matriz padrão fora da pilha para a matriz atual com glPopMatrix(). Em seguida, carregamos a matriz de identidade na matriz atual de modelview. Depois disso, traduzimos a matriz de modelview pelos deslocamentos da câmera, de modo que tudo se tornará relativo à câmera.


Como removemos a matriz padrão da pilha, precisamos colocar a nossa nova na parte superior da pilha para que possamos guardá-la para mais tarde
*/--------------------



----------------------
//Limpa as cores do buffer
glClear(GL_COLOR_BUFFER_BIT);  

//Inicializa a Matriz Modelview e carrega a matriz que salvamos com a translação da câmera
glMatrixMode( GL_MODELVIEW );

glPopMatrix();

//Salva a matriz padrão novamente
glPushMatrix();
----------------------/*
Em vez de usar glLoadIdentity() para redefinir a matriz de modelview, usaremos glPopMatrix() para carregar a matriz que guardamos com a translação da câmera. Como precisamos dessa matriz de modelview padrão no próximo quadro, imediatamente o empurramos para a pilha para salvá-lo para mais tarde.

Agora que a matriz modelview torna tudo relativo à câmera, podemos começar a renderizar nossa geometria
*/--------------------




----------------------
if( glfwGetKey(win, GLFW_KEY_W) == GL_TRUE ){  

            gCameraY -= 0.5f;         

             

            }else if( glfwGetKey(win, GLFW_KEY_S) == GL_TRUE ){

            gCameraY += 0.5f;

           

            }else if( glfwGetKey(win, GLFW_KEY_A) == GL_TRUE ){

            gCameraX -= 0.5f;

           

            }else if( glfwGetKey(win, GLFW_KEY_D) == GL_TRUE ){

            gCameraX += 0.5f;

            }
----------------------/*
Move de posição de renderização da câmera quando o usuário pressiona w/a/s/d
*/--------------------



----------------------
//Move para o centro da tela
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

----------------------/*
Renderizamos uma cena duas vezes a largura / altura da tela. Nossa geometria nunca muda de posição, apenas a câmera muda por toda parte do exemplo.
*/--------------------


----------------------
public static void main(String[] args) {

new Tutorial04();  
}
----------------------/*
Inicializa o programa.
*/--------------------


