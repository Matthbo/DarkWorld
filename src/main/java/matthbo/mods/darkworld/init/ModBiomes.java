package matthbo.mods.darkworld.init;

import matthbo.mods.darkworld.biome.BiomeDarkDesert;
import matthbo.mods.darkworld.biome.BiomeDarkPlains;
import matthbo.mods.darkworld.biome.DarkBiomeGenBase;

public class ModBiomes {
	
	public static DarkBiomeGenBase darkPlains;
	public static DarkBiomeGenBase darkDesert;

	public static void init(){

		darkPlains = new BiomeDarkPlains(101);
		darkDesert = new BiomeDarkDesert(102);
		
	}
	
}
