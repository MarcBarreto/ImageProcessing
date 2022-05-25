package appGenericStuff;

public class appGenericStuff {
	public static void main(String[] argv) {
		/*int[][] matrizMaior  = new int[5][6];
		int[][] matrizMenor = new int[3][3];
		for(int i = 0; i < matrizMaior.length; i++) {
			for(int j = 0; j < matrizMaior[0].length; j++) {
				matrizMaior[i][j] = 0;
			}
		}
		for(int i = 0; i < matrizMenor.length; i++) {
			for(int j = 0; j < matrizMenor[0].length; j++) {
				matrizMenor[i][j] = 0;
			}
		}
		//matrizMaior[matrizMaior.length - 1][matrizMaior[0].length - 1] = 1;
		//matrizMaior[matrizMaior.length - 1][0] = 1;
		//matrizMaior[0][matrizMaior[0].length - 1] = 1;
		matrizMaior[0][0] = 1;
		matrizMaior[1][1] = 1;
		matrizMaior[2][2] = 1;
		matrizMaior[2][0] = 1;
		matrizMaior[1][0] = 1;
		matrizMenor[1][1] = 1;
		matrizMenor[2][0] = 1;
		matrizMenor[1][0] = 1;
		matrizMenor[0][0] = 1;
		matrizMenor[2][2] = 1;
		//----------------------------
		matrizMaior[0][3] = 1;
		matrizMaior[1][3] = 1;
		matrizMaior[1][4] = 1;
		matrizMaior[2][3] = 1;
		matrizMaior[2][5] = 1;
		//----------------------------
		//matrizMenor[0][matrizMenor[0].length - 1] = 1;
		int alturaMenor = matrizMenor.length;
        int larguraMenor = alturaMenor == 0 ? 0 : matrizMenor[0].length;
        int alturaMaior = matrizMaior.length;
        int larguraMaior = alturaMaior == 0 ? 0 : matrizMaior[0].length;
        int[][] novaImagem = new int[alturaMaior][larguraMaior];
        int alturaCentro = (int) matrizMenor.length/2;
        int larguraCentro = (int) matrizMenor[0].length/2;
        boolean flag = false;
        int kx = 0, ky = 0, m = 0, n =0;
        
        for (int y = 0; y < alturaMaior; y++) {
            for (int x = 0; x < larguraMaior; x++) {
                m = y;
                n = x;
            	while(true) {
            		if(matrizMenor[ky][kx] == 1 && matrizMaior[m][n] == 0) {
                		kx = 0;
                		ky = 0;
                		flag = false;
                		break;
                	}
                	else if(matrizMenor[ky][kx] == 1) flag = true;
                	if(kx + 1 == larguraMenor && ky + 1 == alturaMenor) {
                		kx = 0;
                		ky = 0;
                		break;
                	}
                	else if(kx + 1 == larguraMenor) {
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
        System.out.println("Imagem");
        for(int i = 0; i < matrizMaior.length; i++) {
        	for(int j = 0; j < matrizMaior[0].length; j++)
        		System.out.printf("%d\t", matrizMaior[i][j]);
        	System.out.println();
        }
        System.out.println("Imagem após a operações morfológica");
        for(int i = 0; i < novaImagem.length; i++) {
        	for(int j = 0; j < novaImagem[0].length; j++)
        		System.out.printf("%d\t", novaImagem[i][j]);
        	System.out.println();
        }
        System.out.println("Máscara");
        System.out.println("-------------------------------------");
        for(int i = 0; i < matrizMenor.length; i++) {
        	for(int j = 0; j < matrizMenor[0].length; j++)
        		System.out.printf("%d\t", matrizMenor[i][j]);
        	System.out.println();
        }*/
    }
}
