package Pokemon;

import java.util.Random;

public class Player {
	private String ID;
	private int npokemon;
	private Pokemon party[] = new Pokemon[6]; // ?
	private int pokeAtivo = 0;
	
	public Player(String ident, int npoke[] ){
		this.ID = ident;
		//this.npokemon = npoke; 
		System.out.println("");
		System.out.println("Player: " +ident +"'s party:");
		int i;
		ListaPokemon Lista = new ListaPokemon(); 
		for (i = 0; i < party.length; i++)
		{
			this.party[i] = ListaPokemon.Lista[npoke[i]].CriaNovoPoke();
			/*Random pokedex = new Random();
			int indice = pokedex.nextInt(13);
		
			party[i] = ListaPokemon.Lista[indice].CriaNovoPoke();
			*/
			System.out.println("Pokemon " +(i+1) +": " +party[i].GetPokeName());
		
		}
	}
	
	
	
	public int RetornaTamanhoParty (){
		return this.npokemon;
	}
	public Pokemon RetornarPokemonAtivo ()
	{
		return this.party[pokeAtivo];
	}
	
	public String GetPlayerName ()
	{
		return this.ID;
	}

	public int getPokeAtivo() {
		return pokeAtivo;
	}

	public void setPokeAtivo(int pokeAtivo) {
		this.pokeAtivo = pokeAtivo;
	}
	
	public Pokemon RetornarPokemonGuardado (int n) {
		return this.party[n];
	}
	
	
}
