package Pokemon;

public class Pokemon {
	private String name; 
	private int id; //numero do pokemon na pokedex :D
	private Move attack[] = new Move[4]; //vetor que contem ataques do pokemon
	private int maxHealth; //vida maxima do pokemon
	private int currentHealth; //vida atual do pokemon
	private int atkPower; //poder de ataque
	private int defPower; //poder de defesa
	private int indAtqAtual = 0; //indica qual ataque sera usado por este pokemon na proxima vez de acordo com vetor attack[]

	public Pokemon(String nome, int ident, Move golpe[], int vidaMax, int atkpower, int defpower){
		this.name = nome;
		this.id = ident;
		int i = 0;
		//atribui os golpes a este pokemon 
		while(i < 4) 
			this.attack[i] = golpe[i++];
		this.maxHealth = vidaMax;
		this.currentHealth = vidaMax;
		this.atkPower = atkpower;
		this.defPower = defpower;
	}	
	
	//metodo necessario para criar novo pokemon e atribui-lo a treinador/wild
	public Pokemon CriaNovoPoke(){
		Pokemon bicho = new Pokemon(this.name, this.id, this.attack, this.maxHealth, this.atkPower, this.defPower);
		return bicho;
	}
	
	//faz com que o pokemon reveze entre seus ataques
	public void GiraAtaque ()
	{
		this.indAtqAtual++;
		if (this.indAtqAtual == 4)
			this.indAtqAtual = 0;
	}
	
	//troca pokemon que atingir vida atual == 0
	
	public void TrocaPokeFainted(Player recebe) {
		boolean possivelTrocar = true;	
		int i = recebe.getPokeAtivo() + 1; //Vai para pokemon seguinte na party
				if (i >= recebe.RetornaTamanhoParty()) 
					i = 0;
			while (recebe.RetornarPokemonGuardado(i).RetornaVida() <= 0) //verifica se pokemon escolhido tem vida para entrar em batalha
			{
				i++;
				if (i == recebe.RetornaTamanhoParty())
					i = 0;
				if (i == recebe.getPokeAtivo()) //todos os pokemons foram avaliados e nao foi possivel trocar
				{
					possivelTrocar = false;
					break;
				}
			}
			if (possivelTrocar)
			{
				int pokeAntigo = recebe.getPokeAtivo();
				recebe.setPokeAtivo(i);
				System.out.println(recebe.GetPlayerName() +" took fainted " +recebe.RetornarPokemonGuardado(pokeAntigo).GetPokeName() + " out of battle and sent out " +recebe.RetornarPokemonAtivo().GetPokeName() +".");
			}
		} 
	
	//altera vida de acordo com poçao usada ou dano recebido
	public void MudaVida (int x)
	{
		currentHealth += x;
		if (currentHealth < 0)
			currentHealth = 0;
		if (currentHealth > maxHealth)
			currentHealth = maxHealth;
	}
		
	//recupera vida de pokemons apos batalha contra pokemon selvagem
	public void RecuperaVidaPokemon() {
		this.currentHealth = this.maxHealth;
	}
	
	public Move RetornarMovementPokemon (int x)
	{
		return this.attack[x];
	}
	
	public int RetornaAtkPower ()
	{
		return this.atkPower;
	}
	
	public int RetornaDefPower ()
	{
		return this.defPower;
	}
	
	public int RetornaVida(){
		return this.currentHealth;
	}
	
	public int RetornaVidaMaxima(){
		return this.maxHealth;
	}
	
	public String GetPokeName () {
		return this.name;
	}
	
	public int RetornaIndiceAtqAtual ()
	{
		return indAtqAtual;
	}
	
	public void ZeraIndiceAtqAtual(){
		this.indAtqAtual = 0;
	}
	
	public int getId() {
		return this.id;
	}
}
