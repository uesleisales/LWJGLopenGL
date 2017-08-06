import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.stb.STBImage.stbi_image_free;
import static org.lwjgl.stb.STBImage.stbi_load;

public class Desafio03 {

	int SCREEN_WIDTH = 640;
	int SCREEN_HEIGHT = 480;
	int SCREEN_FPS = 60;

	private static float mTextureWidth;
	private static float mTextureHeight;
	private static int id;

	public static float i = 1.f;

	public static float auxX = 0.f;

	public static float auxY = 0.f;

	public static float coordX = 0.f;
	public static float coordY = 0.f;

	public Desafio03() {

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
		ByteBuffer data = stbi_load("res/Charactervector.png", width, height, comp, 4);
		id = glGenTextures();
		glBindTexture(GL_TEXTURE_2D, id);
		glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
		glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
		glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width.get(), height.get(), 0, GL_RGBA, GL_UNSIGNED_BYTE, data);
		stbi_image_free(data);
		mTextureWidth = width.get(0);
		mTextureHeight = height.get(0);

		while (!glfwWindowShouldClose(win)) {

			glfwPollEvents();
			glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
			glClear(GL_COLOR_BUFFER_BIT);
			glMatrixMode(GL_MODELVIEW);
			glLoadIdentity();
			glColor3f(1.0f, 1.0f, 1.0f);

			// 1601 largura, 400,25 largura ; 2397 altura, 599,25

			if (glfwGetKey(win, GLFW_KEY_D) == GL_TRUE) {

				coordX += 0.0001;
				glTranslatef(coordX, coordY, 0.f);

				i += 0.001;

				if (i >= 1 && i < 2) {
					auxY = 0.75f;
					auxX = 0;

					glBegin(GL_QUADS);
					glTexCoord2f(auxX, auxY);
					glVertex2f(-0.25f, 0.25f); // tela onde desenhar

					glTexCoord2f(auxX + 0.25f, auxY);
					glVertex2f(0.25f, 0.25f);

					glTexCoord2f(auxX + 0.25f, auxY + 0.25f);
					glVertex2f(0.25f, -0.25f);

					glTexCoord2f(auxX, auxY + 0.25f);
					glVertex2f(-0.25f, -0.25f);
					glEnd();

				}

				if (i >= 2 && i < 3) {
					auxX = 0.25f;

					glBegin(GL_QUADS);
					glTexCoord2f(auxX, auxY);
					glVertex2f(-0.25f, 0.25f); // tela onde desenhar

					glTexCoord2f(auxX + 0.25f, auxY);
					glVertex2f(0.25f, 0.25f);

					glTexCoord2f(auxX + 0.25f, auxY + 0.25f);
					glVertex2f(0.25f, -0.25f);

					glTexCoord2f(auxX, auxY + 0.25f);
					glVertex2f(-0.25f, -0.25f);
					glEnd();

				}

				if (i >= 3 && i < 4) {
					auxX = 0.5f;

					glBegin(GL_QUADS);
					glTexCoord2f(auxX, auxY);
					glVertex2f(-0.25f, 0.25f); // tela onde desenhar

					glTexCoord2f(auxX + 0.25f, auxY);
					glVertex2f(0.25f, 0.25f);

					glTexCoord2f(auxX + 0.25f, auxY + 0.25f);
					glVertex2f(0.25f, -0.25f);

					glTexCoord2f(auxX, auxY + 0.25f);
					glVertex2f(-0.25f, -0.25f);
					glEnd();

				}

				if (i >= 4) {
					auxX = 0.75f;

					glBegin(GL_QUADS);
					glTexCoord2f(auxX, auxY);
					glVertex2f(-0.25f, 0.25f); // tela onde desenhar

					glTexCoord2f(auxX + 0.25f, auxY);
					glVertex2f(0.25f, 0.25f);

					glTexCoord2f(auxX + 0.25f, auxY + 0.25f);
					glVertex2f(0.25f, -0.25f);

					glTexCoord2f(auxX, auxY + 0.25f);
					glVertex2f(-0.25f, -0.25f);
					glEnd();

				}
				if (i >= 5) {
					i = 1f;
				}

			}

			if (glfwGetKey(win, GLFW_KEY_A) == GL_TRUE) {

				coordX -= 0.0001;
				glTranslatef(coordX, coordY, 0.f);

				i += 0.001;

				if (i >= 1 && i < 2) {
					auxY = 0.5f;
					auxX = 0;

					glBegin(GL_QUADS);
					glTexCoord2f(auxX, auxY);
					glVertex2f(-0.25f, 0.25f); // tela onde desenhar

					glTexCoord2f(auxX + 0.25f, auxY);
					glVertex2f(0.25f, 0.25f);

					glTexCoord2f(auxX + 0.25f, auxY + 0.25f);
					glVertex2f(0.25f, -0.25f);

					glTexCoord2f(auxX, auxY + 0.25f);
					glVertex2f(-0.25f, -0.25f);
					glEnd();

				}

				if (i >= 2 && i < 3) {
					auxX = 0.25f;

					glBegin(GL_QUADS);
					glTexCoord2f(auxX, auxY);
					glVertex2f(-0.25f, 0.25f); // tela onde desenhar

					glTexCoord2f(auxX + 0.25f, auxY);
					glVertex2f(0.25f, 0.25f);

					glTexCoord2f(auxX + 0.25f, auxY + 0.25f);
					glVertex2f(0.25f, -0.25f);

					glTexCoord2f(auxX, auxY + 0.25f);
					glVertex2f(-0.25f, -0.25f);
					glEnd();

				}

				if (i >= 3 && i < 4) {
					auxX = 0.5f;

					glBegin(GL_QUADS);
					glTexCoord2f(auxX, auxY);
					glVertex2f(-0.25f, 0.25f); // tela onde desenhar

					glTexCoord2f(auxX + 0.25f, auxY);
					glVertex2f(0.25f, 0.25f);

					glTexCoord2f(auxX + 0.25f, auxY + 0.25f);
					glVertex2f(0.25f, -0.25f);

					glTexCoord2f(auxX, auxY + 0.25f);
					glVertex2f(-0.25f, -0.25f);
					glEnd();

				}

				if (i >= 4) {
					auxX = 0.75f;

					glBegin(GL_QUADS);
					glTexCoord2f(auxX, auxY);
					glVertex2f(-0.25f, 0.25f); // tela onde desenhar

					glTexCoord2f(auxX + 0.25f, auxY);
					glVertex2f(0.25f, 0.25f);

					glTexCoord2f(auxX + 0.25f, auxY + 0.25f);
					glVertex2f(0.25f, -0.25f);

					glTexCoord2f(auxX, auxY + 0.25f);
					glVertex2f(-0.25f, -0.25f);
					glEnd();

				}
				if (i >= 5) {
					i = 1f;
				}

			}

			if (glfwGetKey(win, GLFW_KEY_W) == GL_TRUE) {

				if (glfwGetKey(win, GLFW_KEY_D) == GL_TRUE) {
					coordY += 0.0001;
				}

				if (glfwGetKey(win, GLFW_KEY_A) == GL_TRUE) {
					coordY += 0.0001;
				}

				if (glfwGetKey(win, GLFW_KEY_D) == GL_FALSE) {
					if (glfwGetKey(win, GLFW_KEY_A) == GL_FALSE) {

						coordY += 0.0001;
						glTranslatef(coordX, coordY, 0.f);

						i += 0.001;

						if (i >= 1 && i < 2) {
							auxY = 0.25f;
							auxX = 0;

							glBegin(GL_QUADS);
							glTexCoord2f(auxX, auxY);
							glVertex2f(-0.25f, 0.25f); // tela onde desenhar

							glTexCoord2f(auxX + 0.25f, auxY);
							glVertex2f(0.25f, 0.25f);

							glTexCoord2f(auxX + 0.25f, auxY + 0.25f);
							glVertex2f(0.25f, -0.25f);

							glTexCoord2f(auxX, auxY + 0.25f);
							glVertex2f(-0.25f, -0.25f);
							glEnd();

						}

						if (i >= 2 && i < 3) {
							auxX = 0.25f;

							glBegin(GL_QUADS);
							glTexCoord2f(auxX, auxY);
							glVertex2f(-0.25f, 0.25f); // tela onde desenhar

							glTexCoord2f(auxX + 0.25f, auxY);
							glVertex2f(0.25f, 0.25f);

							glTexCoord2f(auxX + 0.25f, auxY + 0.25f);
							glVertex2f(0.25f, -0.25f);

							glTexCoord2f(auxX, auxY + 0.25f);
							glVertex2f(-0.25f, -0.25f);
							glEnd();

						}

						if (i >= 3 && i < 4) {
							auxX = 0.5f;

							glBegin(GL_QUADS);
							glTexCoord2f(auxX, auxY);
							glVertex2f(-0.25f, 0.25f); // tela onde desenhar

							glTexCoord2f(auxX + 0.25f, auxY);
							glVertex2f(0.25f, 0.25f);

							glTexCoord2f(auxX + 0.25f, auxY + 0.25f);
							glVertex2f(0.25f, -0.25f);

							glTexCoord2f(auxX, auxY + 0.25f);
							glVertex2f(-0.25f, -0.25f);
							glEnd();

						}

						if (i >= 4) {
							auxX = 0.75f;

							glBegin(GL_QUADS);
							glTexCoord2f(auxX, auxY);
							glVertex2f(-0.25f, 0.25f); // tela onde desenhar

							glTexCoord2f(auxX + 0.25f, auxY);
							glVertex2f(0.25f, 0.25f);

							glTexCoord2f(auxX + 0.25f, auxY + 0.25f);
							glVertex2f(0.25f, -0.25f);

							glTexCoord2f(auxX, auxY + 0.25f);
							glVertex2f(-0.25f, -0.25f);
							glEnd();

						}
						if (i >= 5) {
							i = 1f;
						}

					}
				}
			}

			if (glfwGetKey(win, GLFW_KEY_S) == GL_TRUE) {

				if (glfwGetKey(win, GLFW_KEY_D) == GL_TRUE) {
					coordY -= 0.0001;
				}

				if (glfwGetKey(win, GLFW_KEY_A) == GL_TRUE) {
					coordY -= 0.0001;
				}

				if (glfwGetKey(win, GLFW_KEY_D) == GL_FALSE) {
					if (glfwGetKey(win, GLFW_KEY_A) == GL_FALSE) {

						coordY -= 0.0001;
						glTranslatef(coordX, coordY, 0.f);

						i += 0.001;

						if (i >= 1 && i < 2) {
							auxY = 0;
							auxX = 0;

							glBegin(GL_QUADS);
							glTexCoord2f(auxX, auxY);
							glVertex2f(-0.25f, 0.25f); // tela onde desenhar

							glTexCoord2f(auxX + 0.25f, auxY);
							glVertex2f(0.25f, 0.25f);

							glTexCoord2f(auxX + 0.25f, auxY + 0.25f);
							glVertex2f(0.25f, -0.25f);

							glTexCoord2f(auxX, auxY + 0.25f);
							glVertex2f(-0.25f, -0.25f);
							glEnd();

						}

						if (i >= 2 && i < 3) {
							auxX = 0.25f;

							glBegin(GL_QUADS);
							glTexCoord2f(auxX, auxY);
							glVertex2f(-0.25f, 0.25f); // tela onde desenhar

							glTexCoord2f(auxX + 0.25f, auxY);
							glVertex2f(0.25f, 0.25f);

							glTexCoord2f(auxX + 0.25f, auxY + 0.25f);
							glVertex2f(0.25f, -0.25f);

							glTexCoord2f(auxX, auxY + 0.25f);
							glVertex2f(-0.25f, -0.25f);
							glEnd();

						}

						if (i >= 3 && i < 4) {
							auxX = 0.5f;

							glBegin(GL_QUADS);
							glTexCoord2f(auxX, auxY);
							glVertex2f(-0.25f, 0.25f); // tela onde desenhar

							glTexCoord2f(auxX + 0.25f, auxY);
							glVertex2f(0.25f, 0.25f);

							glTexCoord2f(auxX + 0.25f, auxY + 0.25f);
							glVertex2f(0.25f, -0.25f);

							glTexCoord2f(auxX, auxY + 0.25f);
							glVertex2f(-0.25f, -0.25f);
							glEnd();

						}

						if (i >= 4) {
							auxX = 0.75f;

							glBegin(GL_QUADS);
							glTexCoord2f(auxX, auxY);
							glVertex2f(-0.25f, 0.25f); // tela onde desenhar

							glTexCoord2f(auxX + 0.25f, auxY);
							glVertex2f(0.25f, 0.25f);

							glTexCoord2f(auxX + 0.25f, auxY + 0.25f);
							glVertex2f(0.25f, -0.25f);

							glTexCoord2f(auxX, auxY + 0.25f);
							glVertex2f(-0.25f, -0.25f);
							glEnd();

						}
						if (i >= 5) {
							i = 1f;
						}

					}
				}
			}

			if (glfwGetKey(win, GLFW_KEY_A) == GL_FALSE) {
				if (glfwGetKey(win, GLFW_KEY_S) == GL_FALSE) {
					if (glfwGetKey(win, GLFW_KEY_D) == GL_FALSE) {
						if (glfwGetKey(win, GLFW_KEY_W) == GL_FALSE) {
							i = 1;
							glTranslatef(coordX, coordY, 0.f);
							glBegin(GL_QUADS);
							glTexCoord2f(auxX, auxY);
							glVertex2f(-0.25f, 0.25f); // tela onde desenhar

							glTexCoord2f(auxX + 0.25f, auxY);
							glVertex2f(0.25f, 0.25f);

							glTexCoord2f(auxX + 0.25f, auxY + 0.25f);
							glVertex2f(0.25f, -0.25f);

							glTexCoord2f(auxX, auxY + 0.25f);
							glVertex2f(-0.25f, -0.25f);
							glEnd();
						}
					}
				}
			}

			glfwSwapBuffers(win);

		}
		glfwTerminate();
	}

	public static void main(String[] args) {
		new Desafio03();
	}

}
