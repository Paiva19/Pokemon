package Pokemon;

import java.util.Random;

public class Player {
	private String ID;
	private int npokemon;
	private Pokemon party[] = new Pokemon[6]; // ?
	private int pokeAtivo = 0;
	private boolean wild;
	private int posicaoX;




	public Player(String ident, int numOfPoke, int pokeIndex[], boolean selvagem){
		this.ID = ident;
		//this.npokemon = npoke; 
		System.out.println("");
		System.out.println("Player: " +ident +"'s party:");
		int i;
		ListaPokemon Lista = new ListaPokemon();
		this.npokemon = numOfPoke; 
		for (i = 0; i < numOfPoke; i++)
		{
			this.party[i] = ListaPokemon.Lista[pokeIndex[i]].CriaNovoPoke();
			/*Random pokedex = new Random();
			int indice = pokedex.nextInt(13);
		
			party[i] = ListaPokemon.Lista[indice].CriaNovoPoke();
			*/
			System.out.println("Pokemon " +(i+1) +": " +party[i].getId() + " " +party[i].GetPokeName() + " HP: " + party[i].RetornaVidaMaxima());
		
		}
	}
	
	public boolean isWild() {
		return wild;
	}

	public void setWild(boolean wild) {
		this.wild = wild;
	}

	public int getPosicaoX() {
		return posicaoX;
	}

	public void setPosicaoX(int posicaoX) {
		this.posicaoX = posicaoX;
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