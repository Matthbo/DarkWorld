package matthbo.mods.darkworld.init;

import net.minecraftforge.common.DimensionManager;
import matthbo.mods.darkworld.reference.Refs;
import matthbo.mods.darkworld.world.DarkWorldGenerator;
import matthbo.mods.darkworld.world.OverworldGenerator;
import matthbo.mods.darkworld.world.WorldProviderDarkWorld;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;

@ObjectHolder(Refs.MOD_ID)
public class ModDimensions {
	
	private static DimensionManager DM;
	private static GameRegistry GM;
	
	public static int dimensionIDDarkWorld = -2;
	
	public static void init(){
		
		DM.registerProviderType(dimensionIDDarkWorld, WorldProviderDarkWorld.class, false);
		DM.registerDimension(dimensionIDDarkWorld, dimensionIDDarkWorld);
		
		GM.registerWorldGenerator(new OverworldGenerator(), 0);
		GM.registerWorldGenerator(new DarkWorldGenerator(), 0);
	}

}
