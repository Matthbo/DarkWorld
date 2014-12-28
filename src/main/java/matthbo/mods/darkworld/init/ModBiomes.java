package matthbo.mods.darkworld.init;

import matthbo.mods.darkworld.biome.BiomeDarkOcean;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.util.EnumHelper;
import cpw.mods.fml.common.registry.GameRegistry;
import matthbo.mods.darkworld.biome.BiomeDarkDesert;
import matthbo.mods.darkworld.biome.BiomeDarkPlains;
import matthbo.mods.darkworld.biome.DarkBiomeGenBase;
import matthbo.mods.darkworld.reference.EnumDarkWorld;

public class ModBiomes {
	
	public static DarkBiomeGenBase darkPlains;
	public static DarkBiomeGenBase darkDesert;
	public static DarkBiomeGenBase darkOcean;

	public static void init(){

		darkPlains = new BiomeDarkPlains(101);
		darkDesert = new BiomeDarkDesert(102);
		darkOcean = new BiomeDarkOcean(100);
		
	}
	
}
