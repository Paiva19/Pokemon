package Pokemon;

import java.util.Random;


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
	
		private boolean CalculaBatalhaSelvagem (int tipodechao)
		{
			Random que = new Random();
			int n = que.nextInt(100);
			if((n*tipodechao) > 0) return true;
			return false;
		}
	
		public void mapaction(Player player, Mapa map) { 
			player.setPosicaoX(player.getPosicaoX()+1);
			if(CalculaBatalhaSelvagem (map.getRelevo(player.getPosicaoX())))
			{
				Random rdn = new Random();
				int x = rdn.nextInt(12);
				Player s = new Player("Wild", 1, new int [] {x}, true);
				BattleControls b = new BattleControls();
				b.batalha(s, player);
			}
			
		}
	}
	
	/*private class PokeSelvagemBattle extends MapAction { 
		public PokeSelvagemBattle (){ 
			super(); 
		}	
	
		public void mapaction(Player p, Mapa m) { 
			Random rdn = new Random();
			int i = rdn.nextInt(12);
			Player wild = new Player("Wild", 1, new int [i], true);
			BattleControls b = new BattleControls();
			b.batalha(p, wild);
			} 
	}*/




	private class Restart1 extends MapAction { 
		public Restart1() { 
			super(); 
		} 
		public void mapaction(Player faz, Mapa m) {
			addMapAction1 (new Andar());
			addMapAction1 (new Restart1()); 
		} 
	}

	private class Restart2 extends MapAction { 
		public Restart2() { 
			super(); 
		} 
		public void mapaction(Player faz, Mapa m) {
			addMapAction2 (new Andar());
			addMapAction2 (new Restart2()); 
		} 	
 
	}
	
	public void simula(Player P1, Player P2, Mapa map) { 
		int i;
		
		while(!gameOver)
		{
			for(i = 0; i < P1.RetornaTamanhoParty(); i++)	P2.RetornarPokemonGuardado(i).RecuperaVidaPokemon();
			MapAction acao1 = es1.getNext();
			MapAction acao2 = es2.getNext();
			run(P1, acao1, map);
			if(!gameOver)
				for(i = 0; i < P1.RetornaTamanhoParty(); i++)	P1.RetornarPokemonGuardado(i).RecuperaVidaPokemon();
					run(P2, acao2, map);
			
			es1.removeCurrent();
			es2.removeCurrent();
		}
		
	}
	

	public static void main(String[] args) {
		Player P1 = new Player("Emoji Ludescher", 6, new int[]{1, 2, 3, 4, 5, 6}, false);
		Player P2 = new Player("Lucas Paiva", 6,  new int[]{1, 2, 3, 4, 5, 6}, false);
		Mapa map = new Mapa(P1, P2, 10);
		
		map.criaMapa(P1, P2, 5);
		MapControls m = new MapControls();
		m.simula(P1, P2, map);
		

	}
}


/*     TO DO
 * 	1- Batalha entre treinadores no tatame
 * 	2- Restaurar pokemon
 *  3- Game over condition
 *  4- ajustar printf
 *  */