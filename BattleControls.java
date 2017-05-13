package Pokemon;

import java.util.Random;



public class BattleControls  extends Controller { 

	private boolean acabouBatalha = false;
	
	public BattleControls(){
		addAction1(new Restart1());
		addAction2(new Restart2());
		
	}
	
private class Atacar extends Action { 
	public Atacar() { 
		super(3); 
	}
	//Random acerto = new Random();
	private int CalculaDano (int power, int atkpower, int defpower /*, int accuracy*/)
	{
		float dano = 0;
		/*if(acerto.nextInt(100) + 1 <= accuracy)*/
			dano = (22 * power * atkpower + 100)/(defpower*50);
		return (int) dano;
	}
	public void action(Player faz, Player recebe) { 
		int dano = CalculaDano (faz.RetornarPokemonAtivo().RetornarMovementPokemon(faz.RetornarPokemonAtivo().RetornaIndiceAtqAtual()).RetornaMovePower(), faz.RetornarPokemonAtivo().RetornaAtkPower(), recebe.RetornarPokemonAtivo().RetornaDefPower() /*,pokeUser.accuracy*/);
		recebe.RetornarPokemonAtivo().MudaVida(-dano);
		System.out.println(faz.GetPlayerName() + "'s " +faz.RetornarPokemonAtivo().GetPokeName() + " used " + faz.RetornarPokemonAtivo().RetornarMovementPokemon(faz.RetornarPokemonAtivo().RetornaIndiceAtqAtual()).GetMoveName() + "!");
		System.out.println("It deals " +dano + " damage!" );
		if(recebe.RetornarPokemonAtivo().RetornaVida() == 0) {
			System.out.println(recebe.GetPlayerName() + "'s " + recebe.RetornarPokemonAtivo().GetPokeName() +" has fainted!");
			recebe.RetornarPokemonAtivo().TrocaPokeFainted(recebe);
		}
		faz.RetornarPokemonAtivo().GiraAtaque();
		
		
	}
	
	public String description() { 
		return "";
	} 
} 

private class UsarItem extends Action { 
	public UsarItem() { 
		super(2); 
	}
	public void action(Player faz, Player recebe) { 
		faz.RetornarPokemonAtivo().MudaVida(20);
		System.out.println(faz.GetPlayerName() + " used a healing potion on " + faz.RetornarPokemonAtivo().GetPokeName() + " !");
		System.out.println("It heals its HP to " +faz.RetornarPokemonAtivo().RetornaVida() + " HP!");
		
	} 
	public String description() { 
		return "";
		//return user.name +" used Super Potion on " + pokeUser.name +"! It heals 40 HP!";
	}
}

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
		else
			System.out.println(faz.GetPlayerName() + " doesn't have any other healthy Pokémon! " +faz.RetornarPokemonAtivo().GetPokeName() +" can't be switched out!");
	} 
	public String description() { 
		return "";
		//return user.name +" took out " + pokeUser.name +"and sent out "+ pokeUser.name +".;
	} 
} 

private class Fugir extends Action { 
	public Fugir() { 
		super(0); 
	} 
	public void action(Player faz, Player recebe) { 
		System.out.println(faz.GetPlayerName() +" is a coward and has fled the battle! Shame!" );
		System.out.println(recebe.GetPlayerName() +" has won the battle!");
		acabouBatalha = true;
	} 
	public String description() { 
		return "";
		//return user.name " fled! Such a coward! SHAME!";
	} 	
} 

	private class PokeSelvagemBattle extends Action { 
	public PokeSelvagemBattle (){ 
		super(0); 
	}	

	public void action(Player p, Player s)  { 

		BattleControls b = new BattleControls();
		b.batalha(p, s);
		} 
	
		public String description() { 
			return "";
			//return user.name " fled! Such a coward! SHAME!";
		} 	
	}


private class Restart1 extends Action { 
	public Restart1() { 
		super(100); 
	} 
	public void action(Player faz, Player recebe) {
		//Random DecideAcao = new Random();
		if (!faz.isWild()){ //é treinador
			double numAcao = 100 * (double) faz.RetornarPokemonAtivo().RetornaVida()/faz.RetornarPokemonAtivo().RetornaVidaMaxima();			//DecideAcao.nextInt(100) + 1;
			if (numAcao < 3 ) 
				addAction1 (new Fugir());
			else if (numAcao < 4) //16% de chance de usar poção
				addAction1 (new UsarItem());
			else if (numAcao < 5 ) //10% de chance de trocar Pokémon
				addAction1 (new TrocarPokemon());
			else {
				addAction1 (new Atacar()); //73% de chance de atacar
			}
			addAction1 (new Restart1()); 
		}
		else
		{
			Random w = new Random();
			if(w.nextInt(100) >= 95)
				addAction1 (new Fugir());
			else
				addAction1 (new Atacar());
			addAction1 (new Restart1()); 
		}
	} 
	
	public String description() { 
		return ""; 
	} 
} 

private class Restart2 extends Action { 
	public Restart2() { 
		super(100); 
	} 
	public void action(Player faz, Player recebe) {  
		//Random DecideAcao = new Random();
		if (!faz.isWild()){ //é treinador
			double numAcao = 100 * (double) faz.RetornarPokemonAtivo().RetornaVida()/faz.RetornarPokemonAtivo().RetornaVidaMaxima();			//DecideAcao.nextInt(100) + 1;
			if (numAcao < 3 ) 
				addAction2 (new Fugir());
			else if (numAcao < 4) //16% de chance de usar poção
				addAction2 (new UsarItem());
			else if (numAcao < 5 ) //10% de chance de trocar Pokémon
				addAction2 (new TrocarPokemon());
			else {
				addAction2 (new Atacar()); //73% de chance de atacar
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
	public String description() { 
		return ""; 
	}
}

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
		if(!P.isWild()) /*GAMEOVER ---- nao da pra acessar a variavel m.gameover daqui*/
		return acabouBatalha;
	}

	public int prioridade(Action a, Action b) {
		return a.getPriority() - b.getPriority();
	}

	public int prioridade(Move a, Move b) {
		return a.GetAtkPriority() - b.GetAtkPriority();
	}
	
	public void batalha(Player P1, Player P2) { 
		int turno = 0;
		System.out.println("\nA new battle starts!");
		System.out.println(P1.GetPlayerName() +" sends out " +P1.RetornarPokemonAtivo().GetPokeName() + "!");
		System.out.println(P2.GetPlayerName() +" sends out " +P2.RetornarPokemonAtivo().GetPokeName() + "!");
		while(!fimDeBatalha(P1, P2) && !fimDeBatalha(P2, P1))
		{
			turno++;
			if(turno%2 == 0){
				System.out.println(P1.GetPlayerName() + "'s " +P1.RetornarPokemonAtivo().GetPokeName() + " HP: " + P1.RetornarPokemonAtivo().RetornaVida() + "/" + P1.RetornarPokemonAtivo().RetornaVidaMaxima());
				System.out.println(P2.GetPlayerName() + "'s " +P2.RetornarPokemonAtivo().GetPokeName() + " HP: " + P2.RetornarPokemonAtivo().RetornaVida() + "/" + P2.RetornarPokemonAtivo().RetornaVidaMaxima() +"\n");
				System.out.println("________TURN " +turno/2 + "_______");
			}
			Action acao1 = es1.getNext();
			Action acao2 = es2.getNext();
			
			int p = prioridade (acao1, acao2);
			if (p < 0){ // Prioridade acao1 - prioridade acao2
				run(P1, P2, acao1);
				if(!fimDeBatalha(P2, P1)) //acao2;
					run(P2, P1, acao2);
			}
			
			else if (p > 0)
			{
				run(P2, P1, acao2);
				if(!fimDeBatalha(P1, P2))
					run(P1, P2, acao1);
			}
			else
			{
				if (acao1.getPriority() == 3) //acao == ataque
					if(prioridade(P1.RetornarPokemonAtivo().RetornarMovementPokemon(P1.RetornarPokemonAtivo().RetornaIndiceAtqAtual()), P2.RetornarPokemonAtivo().RetornarMovementPokemon(P2.RetornarPokemonAtivo().RetornaIndiceAtqAtual())) <= 0){
						run(P1, P2, acao1);
						if(!fimDeBatalha(P2, P1))
							run(P2, P1, acao2);
					}
					else
					{
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
			es1.removeCurrent();
			es2.removeCurrent();
		}
		
	}  

	public static void main(String[] args) {
		Player P1 = new Player("Emoji Ludescher", 6, new int[]{12, 12, 12, 12, 12, 12}, false);
		Player P2 = new Player("Lucas Paiva", 6, new int[]{1, 2, 3, 4, 5, 6}, false);
		BattleControls b = new BattleControls();
		//b.addAction(b.new Restart1());
		//b.run(P1, P2, );
		b.batalha(P1, P2);
	}
} 