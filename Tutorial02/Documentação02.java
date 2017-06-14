----------------------/*
Tutorial 02 - Matrizes e coloração de poligonos.


Neste tutorial, vamos configurar um sistema de coordenadas de 640x480 e, enquanto estivermos nisso, vamos dar alguma cor ao nosso polígono.
*/--------------------



----------------------
final int SCREEN_WIDTH = 640;
final int SCREEN_HEIGHT = 480;
	
int COLOR_MODE_CYAN = 0;
int COLOR_MODE_MULTI = 1;
int gColorMode = COLOR_MODE_CYAN;
	
float gProjectionScale = 1.f;
----------------------/*
Inicializamos algumas variaveis globais no topo do programa. 

"SCREEN_WIDTH"  Define a largura da janela
"SCREEN_HEIGHT" Define a altura da janela

"COLOR_MODE_CYAN" Variavel para quando o programa terá cor Ciano
"COLOR_MODE_MULTI" Variavel para quando o programa terá Multicores

"GColorMode" controla se renderizamos um quadrado com cor Ciano sólido ou um quadrado multicolorido.

"GProjectionScale" controla o tamanho de uma área de coordenadas que queremos renderizar.
*/--------------------



----------------------
	boolean initGL() {		
	    glViewport( 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
		
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho( 0.0, SCREEN_WIDTH, SCREEN_HEIGHT, 0.0, 1.0, -1.0 );  

		//Inicializa a Matriz Modelview
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();

		//Especifica valores para os buffers de cores
		glClearColor(0.f, 0.f, 0.f, 1.f);

		//Checa possíveis erros
		int error = glGetError();
		if (error != GL_NO_ERROR) {
			System.err.println("Falha ao inicializar OpenGL  initGL");
        	return false;
		}

		return true;

	}
----------------------/*
Função de inicialização da biblioteca gráfica

glfwInit() inicializa a biblioteca GLFW. 

Antes que a maioria das funções do GLFW possa ser usada, o GLFW deve ser inicializado

E antes de um aplicação terminar, o GLFW deve ser encerrado, a fim de liberar quaisquer recursos alocados durante ou após a inicialização.


Se esta função falhar, chama glfwTerminate antes de retornar. Se for bem-sucedida, você deve chamar o glfwTerminate antes que o aplicativo seja encerrado.

Retorna TRUE se for bem-sucedida, e FALSE se ocorrer algum erro

Caso glfwInit() retornar FALSE, mostra na tela a mensagem "Falha ao inicializar GLFW!".  System.exit(int) encerra o programa, o parametro int quando for diferente de 0 indica que o programa foi finalizado devido a algum erro.
*/--------------------




----------------------
long win = glfwCreateWindow(640, 480, "Window", 0, 0);


glfwCreateWindow ( int width,

int height,

const char * title,

GLFWmonitor * monitor,

GLFWwindow *share 

) 
----------------------/*

Cria uma janela e com seu contexto do OpenGL associado.


Antes que você possa começar a desenhar coisas com o OpenGL, precisa inicializa-lo. Isso é feito pela criação de um contexto, que é essencialmente um estado de máquina que armazena todos os dados relacionados à renderização de sua aplicação. Quando a sua aplicação é fechada, o contexto é destruído e tudo que ele armazena é apagado da memória.


Parâmetros

width :  A largura desejada, em coordenadas de tela, da janela. Isso deve ser maior que zero.

height:  A altura desejada, em coordenadas de tela, da janela. Isso deve ser maior que zero.

title:   O título da janela inicial.

monitor: O monitor a utilizar para o modo de tela cheia, ou NULL para o modo de janela.

share:   A janela cujo contexto para compartilhar recursos com, ou NULL para não compartilhar recursos.
*/--------------------





----------------------
glfwMakeContextCurrent(win);
----------------------/*
A criação bem-sucedida da janela não muda o contexto atual. Antes de poder usar o contexto recém-criado, você precisa torná-lo atual. Para isso utilizamos essa função.
*/--------------------



----------------------
void glfwShowWindow ( GLFWwindow * window ) 
----------------------/*
Essa função torna a janela especificada visível se ela estava anteriormente oculta. Se a janela já estiver visível ou estiver no modo de tela cheia, esta função não faz nada.


Parâmetros

window: A janela para tornar visível.
*/--------------------







----------------------
GL.createCapabilities();
----------------------/*
Para que LWJGL realmente saiba sobre o contexto OpenGL e se inicialize usando esse contexto, temos que chamar GL.createCapabilities ()
*/--------------------







----------------------
while(!glfwWindowShouldClose(win)){

glfwPollEvents();

glClear(GL_COLOR_BUFFER_BIT);
//limpa todos os pixels para a cor preta
 
/*Criação dos quadrados*/

glfwSwapBuffers(win);
//Precisa de 2 contextos(buffers) para trocar de um para outro, um é mostrado na tela, enquanto o OpenGL desenha no outro

}
----------------------/*

while(!glfwWindowShouldClose(win))

Loop até que o usuário feche a janela



glfwPollEvents();

O GLFW precisa se comunicar regularmente com o sistema de janelas tanto para receber eventos quanto para mostrar que o aplicativo não está bloqueado. O processamento de eventos deve ser feito regularmente enquanto você tem janelas visíveis e normalmente é feito em cada frame após o buffer swapping.

Há dois métodos para processar eventos pendentes; polling ou waiting. Este exemplo usará polling de eventos, que processa apenas os eventos que já foram recebidos e, em seguida, retorna imediatamente.



glClear(GL_COLOR_BUFFER_BIT);

Limpa todos os pixels para a cor preta





glfwSwapBuffers(win);

É necessário 2 contextos(buffers) para trocar de um para outro durante o desenho, enquanto um é mostrado na tela, o OpenGL desenha no outro. Essa função realiza essa troca de buffers, passando como parâmetro a janela cujos buffers são trocados, fazendo com que a imagem da tela atualize.

*/--------------------




----------------------
glfwTerminate();
----------------------/*
Esta função destrói todas as janelas e cursores restantes e libera quaisquer outros recursos alocados.
*/--------------------





----------------------
glBegin(GL_QUADS);   
  glColor4f(1,0,0,0);
  glVertex2f(-0.5f, 0.5f);
  glColor4f(0,1,0,0);
  glVertex2f(0.5f, 0.5f);
  glColor4f(0,0,1,0);
  glVertex2f(0.5f, -0.5f);
  glColor4f(0,0,0,1);
  glVertex2f(-0.5f, -0.5f); 
glEnd();
----------------------/*
Explicação da Função de criação de quadrados.

glBegin e glEnd: delimitam os vértices de um primitivo ou um grupo de primitivos semelhantes

GL_QUADS: Trata cada grupo de quatro vértices como um quadrilátero independente.

glVertex2f: Especifica um ponteiro para uma matriz de dois elementos. Os elementos de um array de dois elementos são x e y

glColor4f: Especifique novos valores de vermelho, verde, azul e alpha para a cor atual.
*/--------------------



----------------------
 if( gColorMode == COLOR_MODE_CYAN ) 
            {
            	//Cria um quadrado com a cor  CYAN            	
            	glBegin( GL_QUADS );
            	glColor3f( 0.f, 1.f, 1.f );
            	glVertex2f( -50.f, -50.f );
            	glVertex2f(  50.f, -50.f );
            	glVertex2f(  50.f,  50.f );
            	glVertex2f( -50.f,  50.f );
            	glEnd();
            	
            
            }
----------------------/*
Agora é hora de renderizar o nosso quadrado. Se o modo de cor for ciano (que é o valor inicial), queremos renderizar um quadrado de cor sólida.

Para definir a próxima cor do vértice, chamamos glColor () com os valores vermelho, verde e azul. Em seguida, configuramos a posição vertex com glVertex ().

Pela segunda vez que enviamos um vértice (assim como o terceiro e o quarto), não damos cor. Portanto, o pipeline OpenGL usa o último valor de cor que foi fornecido. Isso dá ao nosso quad uma cor cian sólida.
*/--------------------




----------------------
 if( gColorMode == COLOR_MODE_CYAN ) 
            {
            	//Cria um quadrado com a cor  CYAN            	
            	glBegin( GL_QUADS );
            	glColor3f( 0.f, 1.f, 1.f );
            	glVertex2f( -50.f, -50.f );
            	glVertex2f(  50.f, -50.f );
            	glVertex2f(  50.f,  50.f );
            	glVertex2f( -50.f,  50.f );
            	glEnd();                       
            }
----------------------/*
Se "gColorMode" não é ciano, nós assumimos que ele deve ser multicolorido.

Observe que desta vez, damos uma cor individual para cada vértice. Novamente, é importante dar a cor antes de dar o vértice porque o OpenGL olha o valor de cor mais recente ao renderizar esse vértice particular.
*/--------------------


----------------------
glfwSetKeyCallback(win, keyCallback = new Input());
glfwGetKey(win, GLFW_KEY_Q);
glfwGetKey(win, GLFW_KEY_E);
----------------------/*
Neste tutorial utilizamos uma classe Input para auxilar a verificação das teclas pressionadas pelo usuário.
*/--------------------	
	


----------------------
if(glfwGetKey(win, GLFW_KEY_Q) == GL_TRUE) 
        {
        	//Muda a cor entre CYAN e MULTICOLOR
        	if( gColorMode == COLOR_MODE_CYAN ) 
        	{
                  gColorMode = COLOR_MODE_MULTI;
        	}
        	        else 
        	        {
        	            gColorMode = COLOR_MODE_CYAN;
        	        }
        }  
----------------------/*
Verificamos se o usuário pressiona Q, caso ele pressionar alternamos o quadrado entre ciano e multicolorido.
*/--------------------







----------------------
else if( glfwGetKey(win, GLFW_KEY_E) == GL_TRUE )
        {
            //Muda entre as escalas de projeções
            if( gProjectionScale == 1.f )
            {
                //Zoom out
                gProjectionScale = 2.f;
            }
            else if( gProjectionScale == 2.f )
            {
                //Zoom in
                gProjectionScale = 0.5f;
            }
            else if( gProjectionScale == 0.5f )
            {
                //Regular zoom
                gProjectionScale = 1.f;
            }
----------------------/*
Verificamos se o usuário pressionou a tecla E, caso pressione, alternamos as várias escalas de projeção.
Escala de 100%: padrão
Escala de 200%: nós renderizamos uma área duas vezes maior, então tudo é menor
Escala de 50%: nós renderizamos uma área metade tão grande, então tudo está próximo e maior
*/--------------------



----------------------
//Atualiza a matriz de projeção
glMatrixMode( GL_PROJECTION );
glLoadIdentity();
glOrtho( 0.0f, SCREEN_WIDTH * gProjectionScale, SCREEN_HEIGHT * gProjectionScale, 0.0f, 1.0f, -1.0f );   
----------------------/*
Após a seleção da escala, restauramos a matriz de identidade da matriz de projeção e multiplicamos com a matriz de perspectiva ortográfica em escala.
*/--------------------



----------------------
 glfwSwapBuffers(win);
----------------------/*
É necessário 2 contextos(buffers) para desenhar na tela, enquanto um é mostrado na tela, o OpenGL desenha no outro.     
*/--------------------
