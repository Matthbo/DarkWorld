package matthbo.mods.darkworld.init;

import matthbo.mods.darkworld.biome.*;

public class ModBiomes {

	public static DarkBiomeGenBase darkOcean;
	public static DarkBiomeGenBase darkPlains;
	public static DarkBiomeGenBase darkDesert;
	public static DarkBiomeGenBase darkExtremeHills;
	public static DarkBiomeGenBase darkForest;
	public static DarkBiomeGenBase darkTaiga;
	public static DarkBiomeGenBase darkSwampland;

	public static void init(){

		darkOcean = new BiomeDarkOcean(100);
		darkPlains = new BiomeDarkPlains(101);
		darkDesert = new BiomeDarkDesert(102);
		darkExtremeHills = (DarkBiomeGenBase) new BiomeDarkHills(103, false).setColor(6316128).setBiomeName("Dark Extreme Hills").setHeight(DarkBiomeGenBase.height_MidHills).setTemperatureRainfall(0.2F, 0.3F);
		darkForest = (DarkBiomeGenBase) new BiomeDarkForest(104, 0).setBiomeName("Dark Forest");
		darkTaiga = (DarkBiomeGenBase) new BiomeDarkTaiga(105, 0).setColor(747097).setBiomeName("Dark Taiga").func_76733_a(5159473).setTemperatureRainfall(0.25F, 0.8F).setHeight(DarkBiomeGenBase.height_MidPlains);
		darkSwampland = new BiomeDarkSwamp(106);

	}
	
}
