package Pokemon;

public class Move{
	private int atkpriority;
	private String name;
	private int power;
	private int accuracy;
	//private String tipo;    // Credito extra
	
	public Move (String nome, int poder, int mira, int atkpr){
		this.atkpriority = atkpr;
		this.name = nome;
		this.power = poder;
		this.accuracy = mira;
		
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