package Pokemon;

// This is just a way to hold Event objects.
class EventSet {
	private Action[] events = new Action[100];
	private int index = 1;
	private int next = 0;
	public void add(Action e) {
		if(index >= events.length)
			return; // (In real life, throw exception)
		events[index++] = e;
	}
	public Action getNext() {
		boolean looped = false;
		int start = next;
		 do {
			 next = (next + 1) % events.length;
			 //throw new NullPointerException();
			 
			 if(start == next) looped = true;
			 //throw new NullPointerException();}while(true);
			 if((next == (start + 1) % events.length) && looped)
				 return null;
			 } while(events[next] == null);
		 
			 return events[next];
		 }
	
	
	public void removeCurrent() {
		events[next] = null;
	}
}

public class Controller {
	protected EventSet es1 = new EventSet();
	public void addAction1(Action c) {
		es1.add(c); 
	}
	
	protected EventSet es2 = new EventSet();
	public void addAction2(Action a) {
		es2.add(a);
	}
	
	public void run(Player p1, Player p2, Action e) {
		if (e != null) {
			e.action(p1, p2);
			System.out.println(e.description());
		}
	}
	
}