package Pokemon;

//cria objetos do tipo Move, que são os ataques dos pokemons
public class Move{
	private int atkpriority;
	private String name;
	private int power;
	
	public Move (String nome, int poder, int mira, int atkpr){
		this.atkpriority = atkpr; //ataques mais rapidos tem atkpriority menor e ocorrem antes
		this.name = nome;
		this.power = poder;
	}
	
	public int RetornaMovePower ()
	{
		return this.power;
	}
	
	public String GetMoveName ()
	{
		return this.name;
	}
	public int GetAtkPriority (){
		return atkpriority;
	}
}