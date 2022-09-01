package appprojetopdi;

import java.io.IOException;

/**
 * @author Denisson Santos Alves de Freitas
 * @author Josiel Alemão Santos Silveira
 * @author Marcelo Henrique Lima Barreto
 */

public class AppProjetoPDI {

    public static void main(String[] args) throws IOException {
        
        //Imagens teste para outras formas
        //OBS: Para executar essa parte do código, é necessário comentar o algoritmo
        //o método de abertura e deve-se passar como argumento para o algoritmo 
        //de flood-fill o get da imagem original.
        
        //FloodFill imagemObj = new FloodFill("NovaImagem 1.pbm");
        //FloodFill imagemObj = new FloodFill("NovaImagem 2.pbm");
        //FloodFill imagemObj = new FloodFill("NovaImagem 3.pbm");
        //FloodFill imagemObj = new FloodFill("NovaImagem 4.pbm");
        
        //Imagens das colônias de bactérias
        FloodFill imagemObj = new FloodFill("Colônias de Bactérias.pbm");
        //FloodFill imagemObj = new FloodFill("Colônias de Bactérias 2.pbm");
        //FloodFill imagemObj = new FloodFill("Colônias de Bactérias 3.pbm");
        
        int nBuracos = 0, nObjetos = 0;
        int M = imagemObj.getAlturaImagemOriginal();
        int N = imagemObj.getLarguraImagemOriginal();
        int imagem1[][] = imagemObj.adicionarRestricoesLaterais();
        
        int mascara[][] = new int[3][3];
        mascara[0][0] = 0;
        mascara[0][1] = 1;
        mascara[0][2] = 0;
        mascara[1][0] = 0;
        mascara[1][1] = 1;
        mascara[1][2] = 0;
        mascara[2][0] = 0;
        mascara[2][1] = 0;
        mascara[2][2] = 0;
        
        //int imagem2[][] = imagemObj.realizarFechamentoImagemP1(imagem1, mascara);
        int imagem[][] =imagemObj.realizarAberturaImagemP1(imagem1, mascara);
        imagemObj.salvarImagemP1(imagem, "Resultado.pbm");
        
        System.out.println("Imagem Limpa:");
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(imagem[i][j] + " ");
            }
            System.out.println();
        }
               
        //Conta os objetos
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (imagem[i][j] == 1) {
                    nObjetos += 1;
                    imagemObj.floodFill(imagem, i, j, nObjetos, 1);
                }
            }
        }
        System.out.println("Contando objetos");
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(imagem[i][j] + " ");
            }
            System.out.println();
        }
        
        //Fundo branco
        imagemObj.floodFill(imagem, 0, 0, 1, 0);
        System.out.println("Fundo branco");
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(imagem[i][j] + " ");
            }
            System.out.println();
        }
        //conta os objetos com buracos
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (imagem[i][j] == 0) {
                    if (imagem[i][j - 1] > 1) {
                        nBuracos += 1;
                        imagemObj.floodFill(imagem, i, j - 1, 1, 1);
                    }
                    imagemObj.floodFill(imagem, i, j, 1, 0);
                }
            }
        }
        
        System.out.println("Conta buracos");
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(imagem[i][j] + " ");
            }
            System.out.println();
        }
          
        System.out.println("Numero de objetos:" + (nObjetos - 1));
        System.out.println("Numero de objetos com buracos:" + nBuracos);
        System.out.println("Numero de objetos sem buracos:" + (nObjetos - nBuracos - 1));

    }

}
