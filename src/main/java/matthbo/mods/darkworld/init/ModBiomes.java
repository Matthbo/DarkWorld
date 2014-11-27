package matthbo.mods.darkworld.init;

import matthbo.mods.darkworld.biome.BiomeDarkDesert;
import matthbo.mods.darkworld.biome.DarkBiomeGenBase;

public class ModBiomes {
	
	public static DarkBiomeGenBase darkDesert;

	public static void init(){
		
		darkDesert = new BiomeDarkDesert();
		
	}
	
}
