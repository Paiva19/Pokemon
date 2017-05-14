package Pokemon;

//cria mapa do jogo
public class Mapa 
{
	//Mato = 1, tatame = 0.
	private static int[] relevo;
	
	public Mapa (Player P1, Player P2, int size)
	{
		relevo = new int[size];
		int i;
		for (i = 0; i < size; i++){
				relevo[i] = 1;
		}
		relevo[i-1] = 0;
		P1.setPosicaoX(0);
		P2.setPosicaoX(0);
	}
	 
	
	public int getRelevo(int x) {
		return relevo[x];
	}

	//imprime mapa com o seguinte codigo:
	//Mato = 'M' (nao é porque mato começa com a letra M, mas sim porque M parece um mato), Treinador = '@', tatame = [].
	public void imprimeMapa (Player p)
	{
		int i;
		System.out.print("\n" + p.GetPlayerName() + "'s new location: ");		
		for (i = 0; i < relevo.length; i++){
			if (i == p.getPosicaoX())
				if (i == relevo.length -1)
					System.out.print ("[@]");
				else
					System.out.print ("@");
			else if (relevo[i] == 1)
				System.out.print ("M");
			else if (relevo[i] == 0)
				System.out.print ("[]");
			if (i < relevo.length - 1)
				System.out.print (" ");
		}
		System.out.println("");
	}
}