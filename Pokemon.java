package Pokemon;

public class Pokemon {
	private String name;
	private int id;
	private Move attack[] = new Move[4]; 
	private int maxHealth;
	private int currentHealth;
	private int atkPower;
	private int defPower;
	private int indAtqAtual = 0;
	//private  type Ponto Extra?

	public Pokemon(String nome, int ident, Move golpe[], int vidaMax, int atkpower, int defpower){
		int i = 0;
		this.name = nome;
		this.id = ident;
		
		while(i < 4) this.attack[i] = golpe[i++];
		this.maxHealth = vidaMax;
		this.currentHealth = vidaMax;
		this.atkPower = atkpower;
		this.defPower = defpower;
	}	
	
	//String nome, int ident, Move golpe[], int vidaMax, int atkpower, int defpower
	public Pokemon CriaNovoPoke(){
		Pokemon bicho = new Pokemon(this.name, this.id, this.attack, this.maxHealth, this.atkPower, this.defPower);
		return bicho;
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
	
	public void MudaVida (int x)
	{
		currentHealth += x;
		if (currentHealth < 0)
			currentHealth = 0;
		if (currentHealth > maxHealth)
			currentHealth = maxHealth;
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
	
	public void GiraAtaque ()
	{
		indAtqAtual++;
		if (indAtqAtual == 4)
			indAtqAtual = 0;
	}
	
	
	public int getId() {
		return id;
	}

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
	
}