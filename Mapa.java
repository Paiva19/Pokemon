package Pokemon;


public class Mapa 
{
	//Mato = 'X', Treinador = '@', oponente = O, vazio = ' ';
	//Mato = 1, Vazio = 0, Oponente = -1; 
	private static int[] relevo;
	
	public Mapa (Player P1, Player P2, int size)
	{
		relevo = new int[size];
		int i;
		System.out.println("Criando Mapa...");
		for (i = 0; i < size; i++){
				relevo[i] = 1;
		}
		relevo[i-1] = 0;
		for (i = 0; i < size; i++)
			System.out.print(relevo[i] + " ");
		P1.setPosicaoX(0);
		P2.setPosicaoX(0);
	}
	
	public int getRelevo(int x) {
		return relevo[x];
	}

	/*public void setRelevo(int[] relevo) {
		relevo = relevo;
	}*/

	public void criaMapa (Player P1, Player P2, int size)
	{
		
	}
	
	public void imprimeMapa ()
	{
		int i;
		for (i = 0; i < this.relevo.length; i++){
			if (relevo[i] == 1)
				System.out.print ("X");
			if (relevo[i] == 0)
				System.out.print ("0");
			if (i < relevo.length - 1)
				System.out.print (" ");
		}
	}
}