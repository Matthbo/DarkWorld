package matthbo.mods.darkworld.init;

import net.minecraftforge.common.DimensionManager;
import matthbo.mods.darkworld.handler.ConfigHandler;
import matthbo.mods.darkworld.reference.Refs;
import matthbo.mods.darkworld.world.OverworldGenerator;
import matthbo.mods.darkworld.world.WorldProviderDarkWorld;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.GameRegistry.ObjectHolder;

@ObjectHolder(Refs.MOD_ID)
public class ModDimensions {
	
	private static DimensionManager DM;
	private static GameRegistry GM;
	
	public static int dimensionIDDarkWorld = ConfigHandler.darkWordID;
	
	public static void init(){
		
		DM.registerProviderType(dimensionIDDarkWorld, WorldProviderDarkWorld.class, false);
		DM.registerDimension(dimensionIDDarkWorld, dimensionIDDarkWorld);
		
		GM.registerWorldGenerator(new OverworldGenerator(), 0);
		
	}

}
