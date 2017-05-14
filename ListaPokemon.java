package Pokemon;
//Lista com pokemon que podem ser utilizados pelos players
//pode ser sempre incrementada ou alterada
public class ListaPokemon 
{
	public static Pokemon Lista[] = new Pokemon[151];
	
	public ListaPokemon ()
	{
		ListaGolpes lg = new ListaGolpes(); //apesar do warning, esta linha é necessaria para criar a lista de golpes
		Lista[0] = new Pokemon ("Venusaur", 3, new Move[]{ListaGolpes.ListaM[1], ListaGolpes.ListaM[2], ListaGolpes.ListaM[1], ListaGolpes.ListaM[2]}, 80, 82, 83);
		Lista[1] = new Pokemon ("Charizard", 6, new Move[]{ListaGolpes.ListaM[4], ListaGolpes.ListaM[5], ListaGolpes.ListaM[4], ListaGolpes.ListaM[5]}, 78, 84, 78);
		Lista[2] = new Pokemon ("Blastoise", 9, new Move[]{ListaGolpes.ListaM[7], ListaGolpes.ListaM[8], ListaGolpes.ListaM[9], ListaGolpes.ListaM[8]}, 79, 83, 100);
		Lista[3] = new Pokemon ("Pidgeot", 18, new Move[]{ListaGolpes.ListaM[10], ListaGolpes.ListaM[11], ListaGolpes.ListaM[10], ListaGolpes.ListaM[11]}, 83, 80, 75);
		Lista[4] = new Pokemon ("Raichu", 26, new Move[]{ListaGolpes.ListaM[12], ListaGolpes.ListaM[13], ListaGolpes.ListaM[14], ListaGolpes.ListaM[13]}, 60, 100, 55);
		Lista[5] = new Pokemon ("Nidoqueen", 31, new Move[]{ListaGolpes.ListaM[18], ListaGolpes.ListaM[19], ListaGolpes.ListaM[20], ListaGolpes.ListaM[19]}, 90, 82, 87);
		Lista[6] = new Pokemon ("Nidoking", 34, new Move[]{ListaGolpes.ListaM[15], ListaGolpes.ListaM[16], ListaGolpes.ListaM[18], ListaGolpes.ListaM[16]}, 81, 92, 77);
		Lista[7] = new Pokemon ("Hypno", 97, new Move[]{ListaGolpes.ListaM[21], ListaGolpes.ListaM[22], ListaGolpes.ListaM[21], ListaGolpes.ListaM[22]}, 85, 73, 70);
		Lista[8] = new Pokemon ("Machamp", 68, new Move[]{ListaGolpes.ListaM[23], ListaGolpes.ListaM[24], ListaGolpes.ListaM[25], ListaGolpes.ListaM[24]}, 90, 130, 80);
		Lista[9] = new Pokemon ("Golem", 76, new Move[]{ListaGolpes.ListaM[26], ListaGolpes.ListaM[27], ListaGolpes.ListaM[26], ListaGolpes.ListaM[27]}, 80, 110, 130);
		Lista[10] = new Pokemon ("Lapras", 131, new Move[]{ListaGolpes.ListaM[19], ListaGolpes.ListaM[24], ListaGolpes.ListaM[29], ListaGolpes.ListaM[24]}, 130, 85, 80);
		Lista[11] = new Pokemon ("Scyther", 123, new Move[]{ListaGolpes.ListaM[5], ListaGolpes.ListaM[10], ListaGolpes.ListaM[11],ListaGolpes.ListaM[10]}, 70, 110, 80);
		Lista[12] = new Pokemon ("Dragonite", 149, new Move[]{ListaGolpes.ListaM[14], ListaGolpes.ListaM[30], ListaGolpes.ListaM[31], ListaGolpes.ListaM[30]}, 91, 134, 95);

	}
			
}