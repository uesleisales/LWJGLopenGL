/*
RECORTANDO TEXTURAS

No último tutorial, nós apenas mapeamos toda a textura. Aqui vamos mapear porções de uma textura para renderizar imagens de uma folha de sprite.

(folhas de sprite são várias imagens armazenadas em uma mesma tela, para entender melhor o que é e para que serve,veja o vídeo https://www.codeandweb.com/what-is-a-sprite-sheet)



*/-----------------------
private class LFRect {
        float x;
        float y;
        float w;
        float h;
    }
-----------------------/*
Na nossa estrutura LFRect, definimos um tipo de dados de retângulo. Usamos isso para definir a região na folha de sprite que queremos renderizar.
*/-----------------------




-----------------------
 private void render(float x, float y, LFRect clip) {
    	//Remove quaisquer transformações anteriores
        glLoadIdentity();

        //Coordenada da textura
        float texTop = 0.f;
        float texBottom = 1.f;
        float texLeft = 0.f;
        float texRight = 1.f;

        //Coordenadas do vértice
        float quadWidth = mTextureWidth/2;
        float quadHeight = mTextureHeight/2;

-----------------------/*
Perto do topo da nossa função render modificada, definimos nossas coordenadas de textura padrão e largura/altura do quadrado para nossas coordenadas de vértice.
*/-----------------------



-----------------------
     //Manipulando o recorte    
        if (clip != null) {
            //Coordenadas da textura
            texLeft = clip.x / mTextureWidth;
            texRight = (clip.x + clip.w) / mTextureWidth;
            texTop = clip.y / mTextureHeight;
            texBottom = (clip.y + clip.h) / mTextureHeight;

            //Coordenadas do vértice
            quadWidth = clip.w;
            quadHeight = clip.h;
        }
----------------------/*
Como não sabemos se esta função irá renderizar a totalidade ou parte da textura, precisamos de variáveis para calcular as coordenadas da textura/vértice.

Agora, se a função obtiver um recorte de um retângulo, devemos ajustar nossas coordenadas de textura e vértice de acordo. As coordenadas do vértice são bastante fáceis, basta obter a largura/altura do recorte do retângulo.

As coordenadas de textura, no entanto, são um pouco mais complicadas desde que passam de 0 para 1. Digamos que queremos definir a posição x do recorte do retângulo para a seta para a parte inferior direita: (A posição do recorte seria no canto superior esquerdo (http://lazyfoo.net/tutorials/OpenGL/07_clipping_textures/clip.png)

Em coordenadas de pixels, a posição x é 128 em uma textura de 256 pixels de largura. 128/256 é 0,5. Então, para transformar as coordenadas de pixels em coordenadas de mapeamento de textura, pegue a posição e divida pela largura de pixels da imagem (ou altura para a posição vertical).

*/----------------------



----------------------
       //Move para o ponto de renderização
        glTranslatef( x, y, 0.f );
        
        //Define o ID da textura
        glBindTexture(GL_TEXTURE_2D, textureID);

        //Renderiza o quadrado texturizado
        glBegin(GL_QUADS);        
        glTexCoord2f(  texLeft,    texTop ); glVertex2f(       0.f,        0.f );
        glTexCoord2f( texRight,    texTop ); glVertex2f( quadWidth,        0.f );
        glTexCoord2f( texRight, texBottom ); glVertex2f( quadWidth, quadHeight );
        glTexCoord2f(  texLeft, texBottom ); glVertex2f(       0.f, quadHeight );
        glEnd();
----------------------/*
Com nossas coordenadas de textura e nossas dimensões de sprite calculadas, nós renderizamos nosso quadrado texturizado.
*/----------------------



----------------------
gArrowClips[0] = new LFRect();
            gArrowClips[0].x = 0.f;
            gArrowClips[0].y = 0.f;
            gArrowClips[0].w = 128.f;
            gArrowClips[0].h = 128.f;

            gArrowClips[1] = new LFRect();
            gArrowClips[1].x = 128.f;
            gArrowClips[1].y = 0.f;
            gArrowClips[1].w = 128.f;
            gArrowClips[1].h = 128.f;

            gArrowClips[2] = new LFRect();
            gArrowClips[2].x = 0.f;
            gArrowClips[2].y = 128.f;
            gArrowClips[2].w = 128.f;
            gArrowClips[2].h = 128.f;

            gArrowClips[3] = new LFRect();
            gArrowClips[3].x = 128.f;
            gArrowClips[3].y = 128.f;
            gArrowClips[3].w = 128.f;
            gArrowClips[3].h = 128.f;
----------------------/*
No nosso loop 'while (!glfwWindowShouldClose(win))' definimos nossos recortes de retângulos  e carregamos nossa textura.  E utlizando a função render, renderizamos os sprites de seta individuais em cada canto da tela.
*/----------------------








----------------------
GL11.glTexImage2D(GL_TEXTURE_2D, //Tipo da textura (1D, 2D, 3D)
                    0, //Nível, Sempre ajuste isso para zero
                    GL_RGBA, //Formato interno, o RGBA funciona melhor
                    textureDecoder.getWidth(), // Largura da textura em pixels
                    textureDecoder.getHeight(), // Altura da textura em pixels
                    0, //Border, always set this to zero
                    GL_RGBA, //Formato de textura, no nosso caso, isso é RGBA (você pode encontrar dinamicamente o tipo de textura com PNGDecoder)
                    GL_UNSIGNED_BYTE, //Tipo de dados de textura, este é sempre byte não assinado (isso deve tocar um sino com programadores C / C ++)
                    textureData);
           glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
           glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
----------------------/*
Mapeia uma porção de uma imagem de textura especificada em cada primitiva gráfica para a qual a texturização está ativa.

glTexImage2D é basicamente a função que move os dados de textura para o gpu.

A texturização permite que elementos de uma matriz de imagens sejam lidos por sombreadores.

Para definir imagens de textura, chame glTexImage2D. Os argumentos descrevem os parâmetros da imagem de textura, como altura, largura, largura da borda, número de nível de detalhe (ver glTexParameter) e número de componentes de cores fornecidos. Os três últimos argumentos descrevem como a imagem é representada na memória.
*/----------------------
