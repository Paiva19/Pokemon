package Pokemon;


// This is just a way to hold MapEvent objects.
class MapEventSet {
	private MapAction[] MapEvents = new MapAction[100];
	private int index = 1;
	private int next = 0;
	public void add(MapAction e) {
		if(index >= MapEvents.length)
			return; // (In real life, throw exception)
		MapEvents[index++] = e;
	}
	public MapAction getNext() {
		boolean looped = false;
		int start = next;
		 do {
			 next = (next + 1) % MapEvents.length;
			 //throw new NullPointerException();
			 
			 if(start == next) looped = true;
			 //throw new NullPointerException();}while(true);
			 if((next == (start + 1) % MapEvents.length) && looped)
				 return null;
			 } while(MapEvents[next] == null);
		 
			 return MapEvents[next];
		 }
	
	
	public void removeCurrent() {
		MapEvents[next] = null;
	}
}

public class MapController {
	protected MapEventSet es1 = new MapEventSet();
	public void addMapAction1(MapAction c) {
		es1.add(c); 
	}
	
	protected MapEventSet es2 = new MapEventSet();
	public void addMapAction2(MapAction a) {
		es2.add(a);
	}
	
	public void run(Player p, MapAction e, Mapa m) {
		if (e != null) {
			e.mapaction(p, m);
		}
	}
	
}
