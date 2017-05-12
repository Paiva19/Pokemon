package ex02;

import java.util.Random;

import ex01.Player;
import ex01.Action;
import ex01.BattleControls;


public class MapControls  extends MapController { 
	public MapControls(){
		addMapAction1(new Restart1());
		addMapAction2(new Restart2());
		
	}
	private boolean gameOver = false;
	
	private class Andar extends MapAction { 
	
		public Andar() { 
			super(); 
		}
	
		private boolean CalculaBatalha (int tipodechao)
		{
			Random que = new Random();
			int n = que.nextInt(100);
			if((n*tipodechao) > 60 || (tipodechao == 0/*&& treinador*/)) return true;
			return false;
		}
	
		public void mapaction(Player player) { 
			//anda pra direita
			//CalculaBatalha
			} 
	}
	
	private class PokeSelvagemBattle extends MapAction { 
		public PokeSelvagemBattle (){ 
			super(); 
		}	
	
		public void mapaction(Player p) { 
			Random rdn = new Random();
			int i = rdn.nextInt(12);
			Player wild = new Player("Wild", new int [i]);
			BattleControls b = new BattleControls();
			b.batalha(p, wild);
			} 
	}




	private class Restart1 extends MapAction { 
		public Restart1() { 
			super(); 
		} 
		public void mapaction(Player faz) {
			addMapAction1 (new Andar());
			addMapAction1 (new Restart1()); 
		} 
	}

	private class Restart2 extends MapAction { 
		public Restart2() { 
			super(); 
		} 
		public void mapaction(Player faz) {
			addMapAction2 (new Andar());
			addMapAction2 (new Restart2()); 
		} 	
 
	}
	
	public void batalha(Player P1, Player P2) { 
		
		while(!gameOver)
		{
			MapAction acao1 = es1.getNext();
			MapAction acao2 = es2.getNext();
			run(P1, acao1);
			if(!gameOver) 
					run(P2, acao2);
			
			es1.removeCurrent();
			es2.removeCurrent();
		}
		
	}
	

	public static void main(String[] args) {
		Player P1 = new Player("Emoji Ludescher", new int[]{1, 2, 3, 4, 5, 6});
		Player P2 = new Player("Lucas Paiva",  new int[]{1, 2, 3, 4, 5, 6});
		MapControls b = new MapControls();
		//b.simula(P1, P2);

	}
}