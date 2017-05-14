package Pokemon;

//Lista com golpes utilizados pelos pokemons
//pode ser sempre incrementada ou alterada
public class ListaGolpes 
{
	public static Move ListaM[] = new Move[100];
	
	public
	ListaGolpes ()
	{
		ListaM[1] = new Move ("Solar Beam", 120, 100, 1);
		ListaM[2] = new Move ("Razor Leaf", 55, 95, 1);
		ListaM[3] = new Move ("Leech Seed", 120, 100, 1);
		ListaM[4] = new Move ("Flamethrower", 95, 100, 1);
		ListaM[5] = new Move ("Slash", 70, 100, 1);
		ListaM[6] = new Move ("Leer", 0, 100, 1);
		ListaM[7] = new Move ("Hydro Pump", 120, 80, 1);
		ListaM[8] = new Move ("Bite", 60, 100, 1);
		ListaM[9] = new Move ("Skull Bash", 100, 100, 2);
		ListaM[10] = new Move ("Wing Attack", 60, 100, 1);
		ListaM[11] = new Move ("Quick Attack", 40, 100, 0);
		ListaM[12] = new Move ("Thunder", 110, 70, 1);
		ListaM[13] = new Move ("Thunderbolt", 90, 100, 1);
		ListaM[14] = new Move ("Slam", 80, 75, 1);
		ListaM[15] = new Move ("Horn Attack", 65, 100, 1);
		ListaM[16] = new Move ("Thrash", 90, 100, 1);
		ListaM[17] = new Move ("Poison Sting", 0, 100, 1);
		ListaM[18] = new Move ("Double Kick", 60, 100, 1);
		ListaM[19] = new Move ("Body Slam", 85, 100, 1);
		ListaM[20] = new Move ("Scratch", 40, 100, 1);
		ListaM[21] = new Move ("Headbutt", 70, 100, 1);
		ListaM[22] = new Move ("Psychic", 90, 100, 1);
		ListaM[23] = new Move ("Seismic Toss", 80, 100, 1);
		ListaM[24] = new Move ("Submission", 90, 100, 1);
		ListaM[25] = new Move ("Low Kick", 50, 95, 1);
		ListaM[26] = new Move ("Earthquake", 100, 100, 1);
		ListaM[27] = new Move ("Rock Throw", 50, 90, 1);
		ListaM[28] = new Move ("Explosion", 170, 100, 1);
		ListaM[29] = new Move ("Ice Beam", 95, 100, 1);
		ListaM[30] = new Move ("Dragon Rage", 80, 100, 1);
		ListaM[31] = new Move ("Hyper Beam", 150, 90, 2);
	}
}