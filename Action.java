package Pokemon;

abstract public class Action { 
	private int priority; 
	public Action(int prioridade) { 
		this.priority = prioridade; 
	} 
	
	public int getPriority(){
		return priority;
	}
	
	
	abstract public void action(Player A, Player B); 
	//abstract public String description(); 
	
}