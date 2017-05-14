package Pokemon;


//define objetos do tipo player
public class Player {
	private String ID; //nome do jogador
	private int npokemon; //tamanho da sua party
	private Pokemon party[] = new Pokemon[6]; //vetor que contem os pokemon party
	private int pokeAtivo = 0; //indica qual pokemon esta em batalha de acordo com vetor party[]
	private boolean wild; //indica se é um treinador ou um pokemon selvagem
	private int posicaoX; //indica posicao do jogador no mapa

	public Player(String ident, int numOfPoke, int pokeIndex[], boolean selvagem){
		this.ID = ident;
		this.wild = selvagem;
		if (!this.wild)
			System.out.println("\n" + this.ID +"'s party:");
		int i;
		ListaPokemon Lista = new ListaPokemon();
		this.npokemon = numOfPoke; 
		//atribui pokemons escolhidos ao player e imprime a party ao começar o jogo
		for (i = 0; i < numOfPoke; i++)
		{
			this.party[i] = ListaPokemon.Lista[pokeIndex[i]].CriaNovoPoke();
			if (!this.wild)
				System.out.println("Pokemon " +(i+1) +": " +party[i].getId() + " " +party[i].GetPokeName() + " HP: " + party[i].RetornaVidaMaxima());
		}
		System.out.println("");
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