package Pokemon;

//classe abstrata para as acoes no mapa
abstract public class MapAction {  
	
	public MapAction() { 

	} 
	abstract public void mapaction(Player playerA, Player playerB, Mapa map); 

}