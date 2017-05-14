package Pokemon;

import java.util.Random;


public class MapControls  extends MapController { 
	public MapControls(){
		addMapAction1(new Restart1());
		addMapAction2(new Restart2());
		
	}
	private boolean fimDeJogo = false;
	
	private class Andar extends MapAction { 
	
		//movimenta automaticamente os treinadores
		public Andar() { 
			super(); 
		}
	
		//verifica se Player está no mato
		//indica que deve haver batalha entre player e pokemon selvagem 60% das vezes em que trainer está no mato
		private boolean CalculaBatalhaSelvagem (int relevo) //relevo = 1 (mato), relevo = 0 (tatame)
		{
			Random x = new Random();
			int n = x.nextInt(100);
			if((n*relevo) > 40)
				return true;
			return false;
		}
	
		//Movimenta player e inicia batalha, caso haja uma.
		public void mapaction(Player playerA, Player playerB, Mapa map) { 
			playerA.setPosicaoX(playerA.getPosicaoX()+1);
			map.imprimeMapa(playerA); //chama impressao do mapa cada vez que o player se movimenta
			if(CalculaBatalhaSelvagem (map.getRelevo(playerA.getPosicaoX())))
			{
				Random rdn = new Random();
				int x = rdn.nextInt(12);
				Player s = new Player("Wild", 1, new int [] {x}, true);
				BattleControls b = new BattleControls();
				b.batalha(s, playerA);
				playerA.setPokeAtivo(0); //reseta o pokemon inicial do player
				
			}
			//engaja ambos os players em batalha quando se encontram em relevo tipo tatame
			if(map.getRelevo(playerA.getPosicaoX()) == 0 && playerA.getPosicaoX() == playerB.getPosicaoX()){
				BattleControls BatalhaFinal = new BattleControls();
				BatalhaFinal.batalha(playerA, playerB);
				fimDeJogo = true; //apos a batalha entre players, o jogo se encerra
			}
		}
	}
	
	//Verifica se player ainda tem pokemons saudaveis.
	public boolean gameOver(Player P) {
		int i = 0;
		while (P.RetornarPokemonGuardado(i).RetornaVida() <= 0)
		{
			i++;
			if (i == P.RetornaTamanhoParty())
			{
				fimDeJogo = true;
				break;
			}
		}
		return fimDeJogo;
	}

	//Seleciona proxima acao do player1 no mapa (fora de batalha)
	private class Restart1 extends MapAction { 
		public Restart1() { 
			super(); 
		} 
		public void mapaction(Player faz, Player recebe, Mapa m) {
			addMapAction1 (new Andar());
			addMapAction1 (new Restart1()); 
		} 
	}

	//Seleciona proxima acao do player2 no mapa (fora de batalha)
	private class Restart2 extends MapAction { 
		public Restart2() { 
			super(); 
		} 
		public void mapaction(Player faz, Player recebe, Mapa m) {
			addMapAction2 (new Andar());
			addMapAction2 (new Restart2()); 
		} 	
 
	}
	
	//Principal método que simula as acoes dos jogadores no mapa
	public void simula(Player P1, Player P2, Mapa map) { 
		int i;
		
		while(!gameOver(P1) && !gameOver(P2)) //checa se ambos os players tem pokemons saudaveis
		{
			//reseta stats iniciais dos pokemons do player1
			for(i = 0; i < P1.RetornaTamanhoParty(); i++){
				P1.RetornarPokemonGuardado(i).RecuperaVidaPokemon();
				P1.RetornarPokemonGuardado(i).ZeraIndiceAtqAtual();
			}
			MapAction acao1 = es1.getNext(); //pega proxima acao
			run(P1, P2, acao1, map);
			if(!gameOver(P2)){	
				//reseta stats iniciais dos pokemons do player1
				for(i = 0; i < P2.RetornaTamanhoParty(); i++){
					P2.RetornarPokemonGuardado(i).RecuperaVidaPokemon();
					P2.RetornarPokemonGuardado(i).ZeraIndiceAtqAtual();
				}
				MapAction acao2 = es2.getNext();
				run(P2, P1, acao2, map);
			}
			//remove acoes ja realizadas do vetor de acoes
			es1.removeCurrent();
			es2.removeCurrent();
		}
		
	}
	
	//main para ser chamada para jogo completo, com batalhas wild x player e player x player
	//cria jogadores e mapa a ser utilizado
	public static void main(String[] args) {
		Player P1 = new Player("Emoji", 6, new int[]{0, 8, 9, 10, 11, 4}, false);
		Player P2 = new Player("Lucas Paiva", 6,  new int[]{1, 2, 3, 4, 5, 6}, false);
		Mapa map = new Mapa(P1, P2, 10);
		//chama impressao do mapa na sua condicao inicial
		map.imprimeMapa(P1);
		map.imprimeMapa(P2);
		MapControls m = new MapControls();
		m.simula(P1, P2, map);
		System.out.println("\n==========GAME OVER==========");
	}
}
