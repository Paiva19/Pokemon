package Pokemon;

import java.util.Random;


public class MapControls  extends MapController { 
	public MapControls(){
		addMapAction1(new Restart1());
		addMapAction2(new Restart2());
		
	}
	private boolean FimdeJogo = false;
	
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
	
		public void mapaction(Player playerA, Player playerB, Mapa map) { 
			playerA.setPosicaoX(playerA.getPosicaoX()+1);
			if(CalculaBatalhaSelvagem (map.getRelevo(playerA.getPosicaoX())))
			{
				Random rdn = new Random();
				int x = rdn.nextInt(12);
				Player s = new Player("Wild", 1, new int [] {x}, true);
				BattleControls b = new BattleControls();
				b.batalha(s, playerA);
				playerA.setPokeAtivo(0);
				
			}
			if(map.getRelevo(playerA.getPosicaoX()) == 0 && playerA.getPosicaoX() == playerB.getPosicaoX()){
				BattleControls BatalhaFinal = new BattleControls();
				BatalhaFinal.batalha(playerA, playerB);
				FimdeJogo = true;
			}
			
		}
	}
	public boolean gameOver(Player P) {
		int i = 0;
		while (P.RetornarPokemonGuardado(i).RetornaVida() <= 0)
		{
			i++;
			if (i == P.RetornaTamanhoParty())
			{
				FimdeJogo = true;
				break;
			}
		}
		return FimdeJogo;
	}


	private class Restart1 extends MapAction { 
		public Restart1() { 
			super(); 
		} 
		public void mapaction(Player faz, Player recebe, Mapa m) {
			addMapAction1 (new Andar());
			addMapAction1 (new Restart1()); 
		} 
	}

	private class Restart2 extends MapAction { 
		public Restart2() { 
			super(); 
		} 
		public void mapaction(Player faz, Player recebe, Mapa m) {
			addMapAction2 (new Andar());
			addMapAction2 (new Restart2()); 
		} 	
 
	}
	
	public void simula(Player P1, Player P2, Mapa map) { 
		int i;
		
		while(!gameOver(P1) && !gameOver(P2))
		{
			for(i = 0; i < P1.RetornaTamanhoParty(); i++){
				P1.RetornarPokemonGuardado(i).RecuperaVidaPokemon();
				P1.RetornarPokemonGuardado(i).ZeraIndiceAtqAtual();
			}
			MapAction acao1 = es1.getNext();
			run(P1, P2, acao1, map);
			if(!gameOver(P2)){	
				for(i = 0; i < P2.RetornaTamanhoParty(); i++){
					P2.RetornarPokemonGuardado(i).RecuperaVidaPokemon();
					P2.RetornarPokemonGuardado(i).ZeraIndiceAtqAtual();
				}
				MapAction acao2 = es2.getNext();
				run(P2, P1, acao2, map);
			}
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
		System.out.println("==========GAME OVER==========");
	}
}


/*     TO DO
 *  4- ajustar printf
 *  */