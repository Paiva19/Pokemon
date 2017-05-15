package Pokemon;
import java.util.Random;

public class BattleControls  extends Controller { 

	private boolean acabouBatalha = false;
	private boolean trocou = false; //ultimo movimento do jogador foi trocar pokemon
	
	public BattleControls(){
		addAction1(new Restart1());
		addAction2(new Restart2());
		
	}

	//Calcula e causa dano no pokemon adversario
	//Verifica se pokemon que recebeu dano está sem vida (fainted)
	private class Atacar extends Action { 
		public Atacar() { 
			super(3); 
		}
		private int CalculaDano (int power, int atkpower, int defpower)
		{
			float dano = 0;
			dano = (22 * power * atkpower + 100)/(defpower*50); //formula obtida de Bulbapedia para lvl = 50
			return (int) dano;
		}
		public void action(Player faz, Player recebe) { 
			int dano = CalculaDano (faz.RetornarPokemonAtivo().RetornarMovementPokemon(faz.RetornarPokemonAtivo().RetornaIndiceAtqAtual()).RetornaMovePower(), faz.RetornarPokemonAtivo().RetornaAtkPower(), recebe.RetornarPokemonAtivo().RetornaDefPower());
			recebe.RetornarPokemonAtivo().MudaVida(-dano);
			System.out.print(faz.GetPlayerName());
			if (!faz.isWild())
				System.out.print("'s");
			System.out.println(" " +faz.RetornarPokemonAtivo().GetPokeName() + " used " + faz.RetornarPokemonAtivo().RetornarMovementPokemon(faz.RetornarPokemonAtivo().RetornaIndiceAtqAtual()).GetMoveName() + "!");
			System.out.println("It deals " +dano + " damage!" );
			if(recebe.RetornarPokemonAtivo().RetornaVida() == 0) {
				System.out.print(recebe.GetPlayerName());
				if (!recebe.isWild())
					System.out.print("'s");
				System.out.println( " " + recebe.RetornarPokemonAtivo().GetPokeName() +" has fainted!");
				recebe.RetornarPokemonAtivo().TrocaPokeFainted(recebe);
			}
			faz.RetornarPokemonAtivo().GiraAtaque();
		}
	} 
	
	//Usa item que restaura 20HP no pokemon do usuario
	private class UsarItem extends Action { 
		public UsarItem() { 
			super(2); 
		}
		public void action(Player faz, Player recebe) { 
			faz.RetornarPokemonAtivo().MudaVida(20);
			System.out.println(faz.GetPlayerName() + " used a healing potion on " + faz.RetornarPokemonAtivo().GetPokeName() + " !");
			System.out.println("It heals its HP to " +faz.RetornarPokemonAtivo().RetornaVida() + " HP!");
		} 
	}

	//Troca Pokemon do usuario (por comando do usuario ou por faint) para o proximo saudavel na party.
	private class TrocarPokemon extends Action { 
		public boolean possivelTrocar = true;
		public TrocarPokemon() { 
			super(1);
		}
		public void action(Player faz, Player recebe) {
			int i = faz.getPokeAtivo() + 1; //Vai para pokemon seguinte na party
				if (i == faz.RetornaTamanhoParty()) 
					i = 0;
			while (faz.RetornarPokemonGuardado(i).RetornaVida() <= 0) //verifica se pokemon escolhido tem vida para entrar em batalha
			{
				i++;
				if (i == faz.RetornaTamanhoParty())
					i = 0;
				if (i == faz.getPokeAtivo()) //todos os pokemons foram avaliados e nao foi possivel trocar
					possivelTrocar = false;
			}
			if (possivelTrocar)
			{
				int pokeAntigo = faz.getPokeAtivo();
				faz.setPokeAtivo(i);
				System.out.println(faz.GetPlayerName() +" took " +faz.RetornarPokemonGuardado(pokeAntigo).GetPokeName() + " out of battle and sent out " +faz.RetornarPokemonAtivo().GetPokeName() +".");
			}
			else //jogador perde o turno por ter tentado trocar quando nao podia
				System.out.println(faz.GetPlayerName() + " doesn't have any other healthy Pokémon! " +faz.RetornarPokemonAtivo().GetPokeName() +" can't be switched out!");
		} 
	} 

	//Encerra a batalha por comando do treinador, que perde a batalha
	private class Fugir extends Action {
		public Fugir() { 
			super(0); 
		} 
		public void action(Player faz, Player recebe) {
			System.out.print(faz.GetPlayerName());
			if (faz.isWild())
				System.out.print(" " + faz.RetornarPokemonAtivo().GetPokeName());
			System.out.println(" is a coward and has fled the battle! Shame!" );
			System.out.println(recebe.GetPlayerName() +" has won the battle!");
			acabouBatalha = true;
		} 
	} 
	
	private class ThrowPokeball extends Action {
		public ThrowPokeball () {
			super(-1);//ocorre antes de tentativa de fuga
		}
		public void action (Player faz, Player recebe){
			System.out.println(faz.GetPlayerName() + " threw a Poké Ball!");
			Random rdm = new Random ();
			//formula adaptada de Bulbapedia
			//% de captura = nBolasAtiradas*VidaMax/VidaAtual*12
			recebe.RetornarPokemonAtivo().setnBallsThrown(recebe.RetornarPokemonAtivo().getnBallsThrown() + 1);
			if (100*recebe.RetornarPokemonAtivo().getnBallsThrown()*recebe.RetornarPokemonAtivo().RetornaVidaMaxima()/(recebe.RetornarPokemonAtivo().RetornaVida()*12) >= rdm.nextInt(100))
			{
				faz.setBillsPC(recebe.RetornarPokemonAtivo()); //transfere pokemon para Bills PC 
				faz.setnCapturados(faz.getnCapturados()+ 1);
				System.out.println(faz.GetPlayerName() + " captured " + recebe.RetornarPokemonAtivo().GetPokeName() +"!");
				System.out.println(recebe.RetornarPokemonAtivo().GetPokeName() + " was moved to Bill's PC.");
				acabouBatalha = true;
			}
			else
				System.out.println("But " + recebe.RetornarPokemonAtivo().GetPokeName() + " escapes the Poké Ball!");
		}
	}

	//Decide proxima ação do Player 1 na batalha
	private class Restart1 extends Action { 
		public Restart1() { 
			super(100); //variavel prioridade alta para nao se antecipar às acoes dos treinadores
		} 
		public void action(Player faz, Player recebe) {
			if (!faz.isWild()){
				//decide acao de acordo com vida do pokemon ativo
				double numAcao = 100 * (double) faz.RetornarPokemonAtivo().RetornaVida()/faz.RetornarPokemonAtivo().RetornaVidaMaxima();
				//Condicoes para tentativa de captura:
				//Pokemon selvagem
				//Pokemon selvagem com vida igual ou abaixo de 40% da vida max
				//Só 3 poke bolas serão atiradas, após isso, o treinador desiste e volta a atacar
				//Máximo de 5 pokemons capturados por treinador
 				if (recebe.isWild() && 100*(double)recebe.RetornarPokemonAtivo().RetornaVida()/recebe.RetornarPokemonAtivo().RetornaVidaMaxima() <= 40 && recebe.RetornarPokemonAtivo().getnBallsThrown() < 3 && faz.getnCapturados() < 5)
					addAction1 (new ThrowPokeball());
 				else if (numAcao < 2)
					addAction1 (new Fugir());
				else if (numAcao < 10){ 
					addAction1 (new UsarItem());
					trocou = false;
				}
				//nao permite trocas consecutivas de pokemon
				else if (numAcao < 20 && !trocou ){ 
					addAction1 (new TrocarPokemon());
				trocou = true;
				}
				else {
					addAction1 (new Atacar());
					trocou = false;
				}
				addAction1 (new Restart1()); 
			}
			else{ //decide aleatoriamente acoes do pokemon selvagem
				Random w = new Random();
				if(w.nextInt(100) >= 95)
					addAction1 (new Fugir());
				else
					addAction1 (new Atacar());
				addAction1 (new Restart1()); 
			}
		} 
	} 
	
	//Decide proxima ação do Player 1 na batalha
	//Ver comentarios para Restart1
	private class Restart2 extends Action { 
		public Restart2() { 
			super(100); 
		} 
		public void action(Player faz, Player recebe) {  
			//Random x = new Random();
			if (!faz.isWild()){
				double numAcao = 100 * (double) faz.RetornarPokemonAtivo().RetornaVida()/faz.RetornarPokemonAtivo().RetornaVidaMaxima();
				if (recebe.isWild() && 100*(double)recebe.RetornarPokemonAtivo().RetornaVida()/recebe.RetornarPokemonAtivo().RetornaVidaMaxima() <= 40 && recebe.RetornarPokemonAtivo().getnBallsThrown() < 3 && faz.getnCapturados() < 5)
					addAction2 (new ThrowPokeball());
				else if (numAcao < 2) 
					addAction2 (new Fugir());
				else if (numAcao < 10){
					addAction2 (new UsarItem());
					trocou = false;
				}
				
				else if (numAcao < 20 && !trocou ){
					addAction2 (new TrocarPokemon());
					trocou = true;
				}
				else {
					addAction2 (new Atacar());
					trocou = false;
				}
				addAction2 (new Restart2()); 
			}
			else
			{
				Random w = new Random();
				if(w.nextInt(100) >= 95)
					addAction2 (new Fugir());
				else
					addAction2 (new Atacar());
				addAction2 (new Restart2());
			} 
		} 
	}

	//Verifica se player ainda tem pokemons saudaveis.
	//Caso nao tenha, decreta o outro player vencedor.
	public boolean fimDeBatalha(Player P, Player Vence) {
		int i = 0;
		while (P.RetornarPokemonGuardado(i).RetornaVida() <= 0)
		{
			i++;
			if (i == P.RetornaTamanhoParty())
			{
				System.out.println(Vence.GetPlayerName() +" has won the battle!");
				acabouBatalha = true;
				
				break;
			}
		}
		return acabouBatalha;
	}

	//calcula qual acao deve ocorrer primeiro
	public int prioridade(Action a, Action b) {
		return a.getPriority() - b.getPriority();
	}
	
	//caso ambas as acoes sejam ataques, calcula qual deve ocorrer primeiro.
	public int prioridade(Move a, Move b) {
		return a.GetAtkPriority() - b.GetAtkPriority();
	}
	
	//método principal para simulacao da batalha
	public void batalha(Player P1, Player P2) { 
		int turno = 0;
		System.out.println("\n=====NEW BATTLE=====");
		if (P1.isWild())
			System.out.println("A wild " + P1.RetornarPokemonAtivo().GetPokeName() + " appears!");
		else
			System.out.println(P1.GetPlayerName() +" sends out " +P1.RetornarPokemonAtivo().GetPokeName() + "!");
		System.out.println(P2.GetPlayerName() +" sends out " +P2.RetornarPokemonAtivo().GetPokeName() + "!");
		while(!fimDeBatalha(P1, P2) && !fimDeBatalha(P2, P1)) //verifica se ambos os players tem como batalhar
		{
			turno++;
			//imprime turno atual e HP dos pokemons ativos
			if(turno%2 == 0){
				System.out.print(P1.GetPlayerName());
				if (!P1.isWild())
					System.out.print("'s");
				System.out.println(" " +P1.RetornarPokemonAtivo().GetPokeName() + " HP: " + P1.RetornarPokemonAtivo().RetornaVida() + "/" + P1.RetornarPokemonAtivo().RetornaVidaMaxima());
				System.out.print(P2.GetPlayerName());
				if (!P2.isWild())
					System.out.print("'s");
				System.out.println(" " +P2.RetornarPokemonAtivo().GetPokeName() + " HP: " + P2.RetornarPokemonAtivo().RetornaVida() + "/" + P2.RetornarPokemonAtivo().RetornaVidaMaxima() +"\n");
				System.out.println("________TURN " +turno/2 + "_______");
			}
			//Pega próxima acao do player
			Action acao1 = es1.getNext();
			Action acao2 = es2.getNext();
			
			//Verifica qual acao ocorre primeiro usando os metodos para isso
			//Executa as acoes na ordem correta, checando se a batalha nao acabou apos a primeira delas
			int p = prioridade (acao1, acao2);
			if (p < 0){ // Prioridade acao1 - prioridade acao2
				run(P1, P2, acao1);
				if(!fimDeBatalha(P2, P1)) //acao2;
					run(P2, P1, acao2);
			}
			else if (p > 0){
				run(P2, P1, acao2);
				if(!fimDeBatalha(P1, P2))
					run(P1, P2, acao1);
			}
			else{
				if (acao1.getPriority() == 3) //acao == ataque
					if(prioridade(P1.RetornarPokemonAtivo().RetornarMovementPokemon(P1.RetornarPokemonAtivo().RetornaIndiceAtqAtual()), P2.RetornarPokemonAtivo().RetornarMovementPokemon(P2.RetornarPokemonAtivo().RetornaIndiceAtqAtual())) <= 0){
						run(P1, P2, acao1);
						if(!fimDeBatalha(P2, P1))
							run(P2, P1, acao2);
					}
					else{
						run(P2, P1, acao2);
						if(!fimDeBatalha(P1, P2))
							run(P1, P2, acao1);
					}
				else{
					run(P1, P2, acao1);
					if(!fimDeBatalha(P2, P1))
						run(P2, P1, acao2);
				}
			}
			//Remove acao realizada do vetor de acoes
			es1.removeCurrent();
			es2.removeCurrent();
		}
		
	}  
	
	//main utilizada para batalha exclusivamente entre treinadores
	//cria jogadores e batalha
	public static void main(String[] args) {
		Player P1 = new Player("Emozão", 6, new int[]{1, 7, 10, 6, 2, 11}, false);
		Player P2 = new Player("Paivoso", 6, new int[]{4, 1, 8, 10, 9, 7}, false);
		BattleControls b = new BattleControls();
		b.batalha(P1, P2);
		System.out.println("\n=====GAME OVER=====");
	}
} 