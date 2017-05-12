package Pokemon;


public class Mapa 
{
	//Mato = 'X', Treinador = '@', oponente = O, vazio = ' ';
	//Mato = 1, Vazio = 0, Oponente = -1; 
	private int[] relevo;
	
	public Mapa ()
	{
		
	}
	
	public void criaMapa ()
	{
		int i;
		for (i = 0; i < relevo.length - 1; i++)
				relevo[i] = 1;
		relevo[i] = 0;	
	}
	
	public void imprimeMapa ()
	{
		int i;
		for (i = 0; i < relevo.length; i++){
			if (relevo[i] == 1)
				System.out.print ("X");
			if (relevo[i] == 0)
				System.out.print ("0");
			if (i < relevo.length - 1)
				System.out.print (" ");
		}
	}
}
