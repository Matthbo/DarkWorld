package matthbo.mods.darkworld.init;

import cpw.mods.fml.common.registry.GameRegistry;
import matthbo.mods.darkworld.block.BlockDarkCobblestone;
import matthbo.mods.darkworld.block.BlockDarkDirt;
import matthbo.mods.darkworld.block.BlockDarkStone;
import matthbo.mods.darkworld.block.BlockDarkWorld;

public class ModBlocks {
	
	public static final BlockDarkWorld darkDirt = new BlockDarkDirt();
	public static final BlockDarkWorld darkStone = new BlockDarkStone();
	public static final BlockDarkWorld darkCobblestone = new BlockDarkCobblestone();
	
	public static void init(){
		GameRegistry.registerBlock(darkDirt, "darkdirt");
		GameRegistry.registerBlock(darkStone, "darkstone");
		GameRegistry.registerBlock(darkCobblestone, "darkcobblestone");
	}

}
