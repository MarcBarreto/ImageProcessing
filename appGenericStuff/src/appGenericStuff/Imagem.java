package appGenericStuff;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Imagem {
	private static final String CAMINHO_RELATIVO = "src" + System.getProperty("file.separator");
	private int largura;
	private int altura;
	private int[][] imagem;
	
	public Imagem(String caminho) throws IOException {
		caminho = CAMINHO_RELATIVO + caminho;
		try(BufferedReader leia = new BufferedReader(new FileReader(caminho))) {
			String linha = leia.readLine();
			int aux = 0, auxLinha = 0;
			while(linha != null) {
				if(!linha.substring(0, 1).equals("#")) {
					switch(aux) {
					case 0:
						if(linha.substring(0, 2).equals("P1")) {
							aux++;
							break;
						}
						else {
							System.out.println("Imagem não é do tipo P1(binária)");
							return;
						}
					case 1:
						String tamanho[] = linha.split(" ");
						largura = Integer.parseInt(tamanho[0]);
						altura = Integer.parseInt(tamanho[1]);
						imagem = new int[altura][largura];
						aux++;
						break;
					default: 
						tamanho = linha.split("");
						for(int i = 0; i < largura; i++)
							imagem[auxLinha][i] = Integer.parseInt(tamanho[i]);
						auxLinha++;
						break;
					}
				}
			}
		} catch(FileNotFoundException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
	
	public int[][] dilatarImagemP1(int[][] mascara) {
		int alturaMascara = mascara.length;
		int larguraMascara = mascara[0].length;
		int[][] mascaraRefletida = new int[alturaMascara][larguraMascara];
		for(int i = 0, m = alturaMascara - 1; i < alturaMascara; i++, m--) {
			for(int j = 0, n = larguraMascara - 1 ; j < larguraMascara; j++, n--) {
				mascaraRefletida[i][j] = mascara[m][n]; 
			}
		}
		boolean linhaPar = false;
		boolean colunaPar = false;
		if(alturaMascara % 2 == 0)  linhaPar = true;
		if(larguraMascara % 2 == 0) colunaPar = true;
		int[][] imagemDilatada;
		if(linhaPar && colunaPar) {
			imagemDilatada = new int[altura + 4][largura + 4];
			for (int i = 0; i < altura; i++) {
	            for (int j = 0; j < largura; j++) {
	                for (int k = 0, centroAltura = (int) alturaMascara/2; k < alturaMascara; k++, centroAltura--) {
	                    for (int l = 0, centroLargura = (int) larguraMascara/2; l < larguraMascara; l++, centroLargura--) {
	                        if(imagem[i][j] == 1 && mascaraRefletida[k][l] == 1) 
	                        	imagemDilatada[i + centroAltura + 1][j + centroLargura + 1] = 1;
	                        	imagemDilatada[i + centroAltura + 1][j + centroLargura + 2] = 1;
	                        	imagemDilatada[i + centroAltura + 2][j + centroLargura + 1] = 1;
	                        	imagemDilatada[i + centroAltura + 2][j + centroLargura + 2] = 1;
	                    }
	                }
	            }
	        }
			
		}
		else if(linhaPar) {
			imagemDilatada = new int[altura + 4][largura + 2];
			for (int i = 0; i < altura; i++) {
	            for (int j = 0; j < largura; j++) {
	                for (int k = 0, centroAltura = (int) alturaMascara/2; k < alturaMascara; k++, centroAltura--) {
	                    for (int l = 0, centroLargura = (int) larguraMascara/2; l < larguraMascara; l++, centroLargura--) {
	                        if(imagem[i][j] == 1 && mascaraRefletida[k][l] == 1) 
	                        	imagemDilatada[i + centroAltura + 1][j + centroLargura + 1] = 1;
	                        	imagemDilatada[i + centroAltura + 2][j + centroLargura + 1] = 1;
	                    }
	                }
	            }
	        }
		}
		else if(colunaPar) {
			imagemDilatada = new int[altura + 2][largura + 4];
			for (int i = 0; i < altura; i++) {
	            for (int j = 0; j < largura; j++) {
	                for (int k = 0, centroAltura = (int) alturaMascara/2; k < alturaMascara; k++, centroAltura--) {
	                    for (int l = 0, centroLargura = (int) larguraMascara/2; l < larguraMascara; l++, centroLargura--) {
	                        if(imagem[i][j] == 1 && mascaraRefletida[k][l] == 1) 
	                        	imagemDilatada[i + centroAltura + 1][j + centroLargura + 1] = 1;
	                        	imagemDilatada[i + centroAltura + 1][j + centroLargura + 2] = 1;
	                    }
	                }
	            }
	        }
		}
		else {
			imagemDilatada = new int[altura + 2][largura + 2];
			for (int i = 0; i < altura; i++) {
	            for (int j = 0; j < largura; j++) {
	                for (int k = 0, centroAltura = (int) alturaMascara/2; k < alturaMascara; k++, centroAltura--) {
	                    for (int l = 0, centroLargura = (int) larguraMascara/2; l < larguraMascara; l++, centroLargura--) {
	                        if(imagem[i][j] == 1 && mascaraRefletida[k][l] == 1) 
	                        	 imagemDilatada[i + centroAltura + 1][j + centroLargura + 1] = 1;
	                    }
	                }
	            }
	        }
		}
		return imagemDilatada;
	}
	
	public int[][] erodirImagemP1(int[][] mascara) {
		int[][] novaImagem = new int[altura][largura];
		int alturaMascara = mascara.length, larguraMascara = mascara[0].length;
		boolean linhaPar = false, colunaPar = false;
		if(alturaMascara % 2 == 0)  linhaPar = true;
		if(larguraMascara % 2 == 0) colunaPar = true;
	    int alturaCentro = (int) alturaMascara/2;
	    int larguraCentro = (int) larguraMascara/2;
        boolean flag = false;
        int kx = 0, ky = 0, m = 0, n =0;
        if(linhaPar && colunaPar) {
        	for (int y = 0; y < altura; y++) {
                for (int x = 0; x < largura; x++) {
                    m = y;
                    n = x;
                	while(true) {
                		if(mascara[ky][kx] == 1 && imagem[m][n] == 0) {
                    		kx = 0;
                    		ky = 0;
                    		flag = false;
                    		break;
                    	}
                    	else if(mascara[ky][kx] == 1) flag = true;
                    	if(kx + 1 == larguraMascara && ky + 1 == alturaMascara) {
                    		kx = 0;
                    		ky = 0;
                    		break;
                    	}
                    	else if(kx + 1 == larguraMascara) {
                    		ky++;
                    		kx = 0;
                    		m++;
                    		n = x;
                    		continue;
                    	}
                    	kx++;
                    	n++;
                    }
                	if(flag) {
                		novaImagem[y + alturaCentro][x + larguraCentro] = 1;
                		novaImagem[y + alturaCentro][x + larguraCentro + 1] = 1;
                		novaImagem[y + alturaCentro + 1][x + larguraCentro] = 1;
                		novaImagem[y + alturaCentro + 1][x + larguraCentro + 1] = 1;
                	}
                	flag = false;
                }
            }
        }
        else if(linhaPar) {
        	for (int y = 0; y < altura; y++) {
                for (int x = 0; x < largura; x++) {
                    m = y;
                    n = x;
                	while(true) {
                		if(mascara[ky][kx] == 1 && imagem[m][n] == 0) {
                    		kx = 0;
                    		ky = 0;
                    		flag = false;
                    		break;
                    	}
                    	else if(mascara[ky][kx] == 1) flag = true;
                    	if(kx + 1 == larguraMascara && ky + 1 == alturaMascara) {
                    		kx = 0;
                    		ky = 0;
                    		break;
                    	}
                    	else if(kx + 1 == larguraMascara) {
                    		ky++;
                    		kx = 0;
                    		m++;
                    		n = x;
                    		continue;
                    	}
                    	kx++;
                    	n++;
                    }
                	if(flag) {
                		novaImagem[y + alturaCentro][x + larguraCentro] = 1;
                		novaImagem[y + alturaCentro + 1][x + larguraCentro] = 1;
                	}
                	flag = false;
                }
            }
        }
        else if(colunaPar) {
        	for (int y = 0; y < altura; y++) {
                for (int x = 0; x < largura; x++) {
                    m = y;
                    n = x;
                	while(true) {
                		if(mascara[ky][kx] == 1 && imagem[m][n] == 0) {
                    		kx = 0;
                    		ky = 0;
                    		flag = false;
                    		break;
                    	}
                    	else if(mascara[ky][kx] == 1) flag = true;
                    	if(kx + 1 == larguraMascara && ky + 1 == alturaMascara) {
                    		kx = 0;
                    		ky = 0;
                    		break;
                    	}
                    	else if(kx + 1 == larguraMascara) {
                    		ky++;
                    		kx = 0;
                    		m++;
                    		n = x;
                    		continue;
                    	}
                    	kx++;
                    	n++;
                    }
                	if(flag) {
                		novaImagem[y + alturaCentro][x + larguraCentro] = 1;
                		novaImagem[y + alturaCentro][x + larguraCentro + 1] = 1;
                	}
                	flag = false;
                }
            }
        }
        else {
        	for (int y = 0; y < altura; y++) {
                for (int x = 0; x < largura; x++) {
                    m = y;
                    n = x;
                	while(true) {
                		if(mascara[ky][kx] == 1 && imagem[m][n] == 0) {
                    		kx = 0;
                    		ky = 0;
                    		flag = false;
                    		break;
                    	}
                    	else if(mascara[ky][kx] == 1) flag = true;
                    	if(kx + 1 == larguraMascara && ky + 1 == alturaMascara) {
                    		kx = 0;
                    		ky = 0;
                    		break;
                    	}
                    	else if(kx + 1 == larguraMascara) {
                    		ky++;
                    		kx = 0;
                    		m++;
                    		n = x;
                    		continue;
                    	}
                    	kx++;
                    	n++;
                    }
                	if(flag) {
                		novaImagem[y + alturaCentro][x + larguraCentro] = 1;
                	}
                	flag = false;
                }
            }
        }
		return novaImagem;
	}
}
