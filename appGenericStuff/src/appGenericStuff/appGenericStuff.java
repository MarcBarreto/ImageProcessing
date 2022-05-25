package appGenericStuff;

/**
 * 	@author Denisson Santos Alves de Freitas
 * 	@author Josiel Alemão Santos Silveira
 * 	@author Marcelo Henrique Lima Barreto
 */
import java.io.IOException;

public class appGenericStuff {
	public static void main(String[] argv) throws IOException {
		Imagem imagem = new Imagem("imagem.pbm");
		//imagem.imprimirMatriz();
		imagem.salvarImagemP1(imagem.getImagemOriginal(), "novaImagem.pbm");
		 int matriz[][] = imagem.getImagemOriginal();
	        int mascara1[][] = new int[3][3];
	        mascara1[0][0]= 1;
	        mascara1[0][1]= 0;
	        mascara1[0][2]= 1;
	        mascara1[1][0]= 0;
	        mascara1[1][1]= 1;
	        mascara1[1][2]= 0;
	        mascara1[2][0]= 1;
	        mascara1[2][1]= 0;
	        mascara1[2][2]= 1;

	        int mascara2[][] = new int[3][3];
	        mascara2[0][0]= 0;
	        mascara2[0][1]= 1;
	        mascara2[0][2]= 0;
	        mascara2[1][0]= 1;
	        mascara2[1][1]= 0;
	        mascara2[1][2]= 1;
	        mascara2[2][0]= 0;
	        mascara2[2][1]= 1;
	        mascara2[2][2]= 0;

	        imagem.salvarImagemP1(imagem.dilatarImagemP1(matriz, mascara1), "imagemDilatada1.pbm");
	        imagem.salvarImagemP1(imagem.dilatarImagemP1(matriz, mascara2), "imagemDilatada2.pbm");

	        imagem.salvarImagemP1(imagem.erodirImagemP1(matriz, mascara1), "imagemErodida1.pbm");
	        imagem.salvarImagemP1(imagem.realizarAberturaImagemP1(matriz, mascara1), "imagemAbertura1.pbm");
	        imagem.salvarImagemP1(imagem.realizarAberturaImagemP1(matriz, mascara2), "imagemAbertura2.pbm");

	        imagem.salvarImagemP1(imagem.realizarFechamentoImagemP1(matriz, mascara1), "imagemFechamento1.pbm");
	        imagem.salvarImagemP1(imagem.realizarFechamentoImagemP1(matriz, mascara2), "imagemFechamento2.pbm");
    }
}
