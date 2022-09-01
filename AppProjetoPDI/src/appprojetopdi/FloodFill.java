package appprojetopdi;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * @author Denisson Santos Alves de Freitas
 * @author Josiel Alemão Santos Silveira
 * @author Marcelo Henrique Lima Barreto
 */

public class FloodFill {

    private static final String CAMINHO_RELATIVO = "src" + System.getProperty("file.separator");
    private int larguraImagemOriginal;
    private int alturaImagemOriginal;
    private int[][] imagemOriginal;

    public FloodFill(String caminho) throws IOException {
        caminho = CAMINHO_RELATIVO + caminho;
        try {
            FileReader arquivo = new FileReader(caminho);
            Scanner leitor = new Scanner(arquivo);
            int contaLinhas = 0, auxColuna = 0, auxLinha = 0;
            while (leitor.hasNextLine()) {
                String verificaString = leitor.nextLine();
                if (!verificaString.substring(0, 1).equals("#")) {
                    switch (contaLinhas) {
                        case 0:
                            if (verificaString.substring(0, 2).equals("P1")) {
                                contaLinhas++;
                                break;
                            } else {
                                System.out.println("Imagem não é do tipo P1(binária)");
                                return;
                            }
                        case 1:
                            String[] textoSeparado = verificaString.split(" ");
                            larguraImagemOriginal = Integer.parseInt(textoSeparado[0]);
                            alturaImagemOriginal = Integer.parseInt(textoSeparado[1]);
                            imagemOriginal = new int[alturaImagemOriginal][larguraImagemOriginal];
                            contaLinhas++;
                            break;
                        default:
                            char[] myChars = verificaString.toCharArray();
                            int i = 0,
                             j = auxColuna;
                            while (i < verificaString.length() && j < larguraImagemOriginal) {
                                imagemOriginal[auxLinha][j] = myChars[i] - '0';
                                i++;
                                j++;
                                if (j == larguraImagemOriginal) {
                                    auxLinha++;
                                    j = 0;
                                    auxColuna = j;
                                }
                            }
                            auxColuna = j;
                            break;
                    }
                }
            }
            leitor.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        /*
        System.out.println("------------------------------------------------");
        for (int a = 0; a < alturaImagemOriginal; a++) {
            for (int b = 0; b < larguraImagemOriginal; b++) {
                System.out.print(imagemOriginal[a][b]);
            }
            System.out.println();
        }
         */
    }
    // Uma fun��o recursiva para substituir a cor anterior 'prevC' em '(x, y)'
    // e todos os pixels ao redor de (x, y) com a nova cor 'newC'

    public void floodFillRec(int imagem[][], int x, int y, int corAnterior, int novaCor, int valor) {
        // Casos base
        if (x < 0 || x >= alturaImagemOriginal || y < 0 || y >= larguraImagemOriginal) {
            return;
        }
        if (imagem[x][y] != corAnterior) {
            return;
        }

        // substitui a cor em (x, y)
        imagem[x][y] = novaCor;

        // Recursivamente para cima, baixo, direita, esquerda
        if (valor == 1) {
            floodFillRec(imagem, x + 1, y, corAnterior, novaCor, valor);
            floodFillRec(imagem, x + 1, y - 1, corAnterior, novaCor, valor);
            floodFillRec(imagem, x - 1, y, corAnterior, novaCor, valor);
            floodFillRec(imagem, x - 1, y + 1, corAnterior, novaCor, valor);
            floodFillRec(imagem, x, y + 1, corAnterior, novaCor, valor);
            floodFillRec(imagem, x, y - 1, corAnterior, novaCor, valor);
            floodFillRec(imagem, x + 1, y + 1, corAnterior, novaCor, valor);
        } else {
            floodFillRec(imagem, x + 1, y, corAnterior, novaCor, valor);
            floodFillRec(imagem, x - 1, y, corAnterior, novaCor, valor);
            floodFillRec(imagem, x, y + 1, corAnterior, novaCor, valor);
            floodFillRec(imagem, x, y - 1, corAnterior, novaCor, valor);
        }
    }

    // Encontra a cor anterior em (x,y) e chama floodFillUtil()
    public void floodFill(int imagem[][], int x, int y, int novaCor, int valor) {
        int corAnterior = imagem[x][y];
        if (corAnterior == novaCor) {
            return;
        }
        floodFillRec(imagem, x, y, corAnterior, novaCor, valor);
    }

    //Getters e Setters
    public int getLarguraImagemOriginal() {
        return larguraImagemOriginal;
    }

    public void setLarguraImagemOriginal(int larguraImagemOriginal) {
        this.larguraImagemOriginal = larguraImagemOriginal;
    }

    public int getAlturaImagemOriginal() {
        return alturaImagemOriginal;
    }

    public void setAlturaImagemOriginal(int alturaImagemOriginal) {
        this.alturaImagemOriginal = alturaImagemOriginal;
    }

    public int[][] getImagemOriginal() {
        return imagemOriginal;
    }

    public void setImagemOriginal(int[][] imagemOriginal) {
        this.imagemOriginal = imagemOriginal;
    }

    //================================================================================
    public void salvarImagemP1(int[][] imagem, String nomeDaImagem) {
        try (PrintWriter gravarArquivo = new PrintWriter(new FileWriter(CAMINHO_RELATIVO + nomeDaImagem, false))) {
            gravarArquivo.println("P1" + "\n #Desenvolvida pela Generic Stuff\n" + imagem[0].length + " " + imagem.length);
            for (int i = 0; i < imagem.length; i++) {
                for (int j = 0; j < imagem[0].length; j++) {
                    gravarArquivo.print(imagem[i][j]);
                }
                gravarArquivo.println();
            }
            gravarArquivo.close();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public int[][] adicionarRestricoesLaterais() {
        int matriz[][] = new int[alturaImagemOriginal + 2][larguraImagemOriginal + 2];
        if (larguraImagemOriginal != alturaImagemOriginal) {
            for (int i = 0; i < larguraImagemOriginal + 2; i++) {
                matriz[0][i] = 0;
                matriz[larguraImagemOriginal + 1][i] = 0;
            }

            for (int i = 0; i < alturaImagemOriginal + 2; i++) {
                matriz[i][0] = 0;
                matriz[i][alturaImagemOriginal + 1] = 0;
            }
        } else {
            for (int i = 0; i < larguraImagemOriginal + 2; i++) {
                matriz[0][i] = 0;
                matriz[larguraImagemOriginal + 1][i] = 0;
                matriz[i][0] = 0;
                matriz[i][alturaImagemOriginal + 1] = 0;
            }
        }
        for (int i = 1; i < alturaImagemOriginal + 1; i++) {
            for (int j = 1; j < larguraImagemOriginal + 1; j++) {
                matriz[i][j] = imagemOriginal[i - 1][j - 1];
            }
        }
        return matriz;
    }

    public int[][] dilatarImagemP1(int[][] imagem, int[][] mascara) {
        int altura = imagem.length;
        int largura = imagem[0].length;
        int alturaMascara = mascara.length;
        int larguraMascara = mascara[0].length;
        int[][] mascaraRefletida = new int[alturaMascara][larguraMascara];
        for (int i = 0, m = alturaMascara - 1; i < alturaMascara; i++, m--) {
            for (int j = 0, n = larguraMascara - 1; j < larguraMascara; j++, n--) {
                mascaraRefletida[i][j] = mascara[m][n];
            }
        }
        boolean linhaPar = false;
        boolean colunaPar = false;
        if (alturaMascara % 2 == 0) {
            linhaPar = true;
        }
        if (larguraMascara % 2 == 0) {
            colunaPar = true;
        }
        int[][] imagemDilatada;
        if (linhaPar && colunaPar) {
            imagemDilatada = new int[altura + 4][largura + 4];
            for (int i = 0; i < altura; i++) {
                for (int j = 0; j < largura; j++) {
                    for (int k = 0, centroAltura = (int) alturaMascara / 2; k < alturaMascara; k++, centroAltura--) {
                        for (int l = 0, centroLargura = (int) larguraMascara / 2; l < larguraMascara; l++, centroLargura--) {
                            if (imagem[i][j] == 1 && mascaraRefletida[k][l] == 1) {
                                imagemDilatada[i + centroAltura + 1][j + centroLargura + 1] = 1;
                            }
                            imagemDilatada[i + centroAltura + 1][j + centroLargura + 2] = 1;
                            imagemDilatada[i + centroAltura + 2][j + centroLargura + 1] = 1;
                            imagemDilatada[i + centroAltura + 2][j + centroLargura + 2] = 1;
                        }
                    }
                }
            }

        } else if (linhaPar) {
            imagemDilatada = new int[altura + 4][largura + 2];
            for (int i = 0; i < altura; i++) {
                for (int j = 0; j < largura; j++) {
                    for (int k = 0, centroAltura = (int) alturaMascara / 2; k < alturaMascara; k++, centroAltura--) {
                        for (int l = 0, centroLargura = (int) larguraMascara / 2; l < larguraMascara; l++, centroLargura--) {
                            if (imagem[i][j] == 1 && mascaraRefletida[k][l] == 1) {
                                imagemDilatada[i + centroAltura + 1][j + centroLargura + 1] = 1;
                            }
                            imagemDilatada[i + centroAltura + 2][j + centroLargura + 1] = 1;
                        }
                    }
                }
            }
        } else if (colunaPar) {
            imagemDilatada = new int[altura + 2][largura + 4];
            for (int i = 0; i < altura; i++) {
                for (int j = 0; j < largura; j++) {
                    for (int k = 0, centroAltura = (int) alturaMascara / 2; k < alturaMascara; k++, centroAltura--) {
                        for (int l = 0, centroLargura = (int) larguraMascara / 2; l < larguraMascara; l++, centroLargura--) {
                            if (imagem[i][j] == 1 && mascaraRefletida[k][l] == 1) {
                                imagemDilatada[i + centroAltura + 1][j + centroLargura + 1] = 1;
                            }
                            imagemDilatada[i + centroAltura + 1][j + centroLargura + 2] = 1;
                        }
                    }
                }
            }
        } else {
            imagemDilatada = new int[altura + 2][largura + 2];
            for (int i = 0; i < altura; i++) {
                for (int j = 0; j < largura; j++) {
                    for (int k = 0, centroAltura = (int) alturaMascara / 2; k < alturaMascara; k++, centroAltura--) {
                        for (int l = 0, centroLargura = (int) larguraMascara / 2; l < larguraMascara; l++, centroLargura--) {
                            if (imagem[i][j] == 1 && mascaraRefletida[k][l] == 1) {
                                imagemDilatada[i + centroAltura + 1][j + centroLargura + 1] = 1;
                            }
                        }
                    }
                }
            }
        }
        return imagemDilatada;
    }

    public int[][] erodirImagemP1(int[][] imagem, int[][] mascara) {
        int altura = imagem.length;
        int largura = imagem[0].length;
        int[][] novaImagem = new int[altura][largura];
        int alturaMascara = mascara.length, larguraMascara = mascara[0].length;
        boolean linhaPar = false, colunaPar = false;
        if (alturaMascara % 2 == 0) {
            linhaPar = true;
        }
        if (larguraMascara % 2 == 0) {
            colunaPar = true;
        }
        int alturaCentro = (int) alturaMascara / 2;
        int larguraCentro = (int) larguraMascara / 2;
        boolean flag = false;
        int kx = 0, ky = 0, m = 0, n = 0;
        if (linhaPar && colunaPar) {
            for (int y = 0; y < altura; y++) {
                for (int x = 0; x < largura; x++) {
                    m = y;
                    n = x;
                    while (true) {
                        if (mascara[ky][kx] == 1 && imagem[m][n] == 0) {
                            kx = 0;
                            ky = 0;
                            flag = false;
                            break;
                        } else if (mascara[ky][kx] == 1) {
                            flag = true;
                        }
                        if (kx + 1 == larguraMascara && ky + 1 == alturaMascara) {
                            kx = 0;
                            ky = 0;
                            break;
                        } else if (kx + 1 == larguraMascara) {
                            ky++;
                            kx = 0;
                            if (m + 1 < altura) {
                                m++;
                            } else {
                                break;
                            }
                            n = x;
                            continue;
                        }
                        kx++;
                        if (n < largura) {
                            n++;
                        } else {
                            break;
                        }
                    }
                    if (flag) {
                        if ((y + alturaCentro + 1) < altura && (x + larguraCentro + 1) < largura) {
                            novaImagem[y + alturaCentro][x + larguraCentro] = 1;
                            novaImagem[y + alturaCentro][x + larguraCentro + 1] = 1;
                            novaImagem[y + alturaCentro + 1][x + larguraCentro] = 1;
                            novaImagem[y + alturaCentro + 1][x + larguraCentro + 1] = 1;
                        }
                    }
                    flag = false;
                }
            }
        } else if (linhaPar) {
            for (int y = 0; y < altura; y++) {
                for (int x = 0; x < largura; x++) {
                    m = y;
                    n = x;
                    while (true) {
                        if (mascara[ky][kx] == 1 && imagem[m][n] == 0) {
                            kx = 0;
                            ky = 0;
                            flag = false;
                            break;
                        } else if (mascara[ky][kx] == 1) {
                            flag = true;
                        }
                        if (kx + 1 == larguraMascara && ky + 1 == alturaMascara) {
                            kx = 0;
                            ky = 0;
                            break;
                        } else if (kx + 1 == larguraMascara) {
                            ky++;
                            kx = 0;
                            if (m + 1 < altura) {
                                m++;
                            } else {
                                break;
                            }
                            n = x;
                            continue;
                        }
                        kx++;
                        if (n + 1 < largura) {
                            n++;
                        } else {
                            break;
                        }
                    }
                    if (flag) {
                        if ((y + alturaCentro + 1) < altura && x + larguraCentro < 1) {
                            novaImagem[y + alturaCentro][x + larguraCentro] = 1;
                            novaImagem[y + alturaCentro + 1][x + larguraCentro] = 1;
                        }
                    }
                    flag = false;
                }
            }
        } else if (colunaPar) {
            for (int y = 0; y < altura; y++) {
                for (int x = 0; x < largura; x++) {
                    m = y;
                    n = x;
                    while (true) {
                        if (mascara[ky][kx] == 1 && imagem[m][n] == 0) {
                            kx = 0;
                            ky = 0;
                            flag = false;
                            break;
                        } else if (mascara[ky][kx] == 1) {
                            flag = true;
                        }
                        if (kx + 1 == larguraMascara && ky + 1 == alturaMascara) {
                            kx = 0;
                            ky = 0;
                            break;
                        } else if (kx + 1 == larguraMascara) {
                            ky++;
                            kx = 0;
                            if (m + 1 < altura) {
                                m++;
                            } else {
                                break;
                            }
                            n = x;
                            continue;
                        }
                        kx++;
                        if (n + 1 < largura) {
                            n++;
                        } else {
                            break;
                        }
                    }
                    if (flag) {
                        if ((y + alturaCentro) < altura && (x + larguraCentro + 1) < largura) {
                            novaImagem[y + alturaCentro][x + larguraCentro] = 1;
                            novaImagem[y + alturaCentro][x + larguraCentro + 1] = 1;
                        }
                    }
                    flag = false;
                }
            }
        } else {
            for (int y = 0; y < altura; y++) {
                for (int x = 0; x < largura; x++) {
                    m = y;
                    n = x;
                    while (true) {
                        if (mascara[ky][kx] == 1 && imagem[m][n] == 0) {
                            kx = 0;
                            ky = 0;
                            flag = false;
                            break;
                        } else if (mascara[ky][kx] == 1) {
                            flag = true;
                        }
                        if (kx + 1 == larguraMascara && ky + 1 == alturaMascara) {
                            kx = 0;
                            ky = 0;
                            break;
                        } else if (kx + 1 == larguraMascara) {
                            ky++;
                            kx = 0;
                            if (m + 1 < altura) {
                                m++;
                            } else {
                                break;
                            }
                            n = x;
                            continue;
                        }
                        kx++;
                        if (n + 1 < largura) {
                            n++;
                        } else {
                            break;
                        }
                    }
                    if (flag) {
                        if (x + larguraCentro < largura && y + alturaCentro < altura) {
                            novaImagem[y + alturaCentro][x + larguraCentro] = 1;
                        }
                    }
                    flag = false;
                }
            }
        }
        return novaImagem;
    }

    public int[][] realizarFechamentoImagemP1(int[][] imagem, int[][] mascara) {
        int[][] imagemDilatada = dilatarImagemP1(imagem, mascara);
        int[][] novaImagem = erodirImagemP1(imagemDilatada, mascara);
        return novaImagem;
    }

    public int[][] realizarAberturaImagemP1(int[][] imagem, int[][] mascara) {
        int[][] imagemErodida = erodirImagemP1(imagem, mascara);
        int[][] novaImagem = dilatarImagemP1(imagemErodida, mascara);
        return novaImagem;
    }
}
