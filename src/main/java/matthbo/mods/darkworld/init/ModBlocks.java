package matthbo.mods.darkworld.init;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.GameRegistry.ObjectHolder;
import matthbo.mods.darkworld.block.BlockDarkCobbleStone;
import matthbo.mods.darkworld.block.BlockDarkDirt;
import matthbo.mods.darkworld.block.BlockDarkStone;
import matthbo.mods.darkworld.block.BlockDarkWorld;
import matthbo.mods.darkworld.block.BlockFallingDarkWorld;
import matthbo.mods.darkworld.block.BlockPecluliarDust;
import matthbo.mods.darkworld.block.BlockPeculiarCobbleStone;
import matthbo.mods.darkworld.block.BlockPeculiarStone;
import matthbo.mods.darkworld.block.BlockPortalDarkWord;
import matthbo.mods.darkworld.block.BlockTest;
import matthbo.mods.darkworld.reference.Refs;

@ObjectHolder(Refs.MOD_ID)
public class ModBlocks {
	
	private static GameRegistry GR;
	
	//public static final BlockDarkWorld test = new BlockTest();
	
	//in the overworld
	public static final BlockDarkWorld peculiarStone = new BlockPeculiarStone();
	public static final BlockDarkWorld peculiarCobbleStone = new BlockPeculiarCobbleStone();
	public static final BlockFallingDarkWorld peculiarDustBlock = new BlockPecluliarDust();
	public static final BlockPortalDarkWord darkworldPortal = new BlockPortalDarkWord();

	//in the darkworld
	public static final BlockDarkWorld darkDirt = new BlockDarkDirt();
	public static final BlockDarkWorld darkStone = new BlockDarkStone();
	public static final BlockDarkWorld darkCobbleStone = new BlockDarkCobbleStone();
	
	public static void init(){
		//GameRegistry.registerBlock(test, "test");
		
		GR.registerBlock(peculiarStone, "peculiarStone");
		GR.registerBlock(peculiarCobbleStone, "peculiarCobbleStone");
		GR.registerBlock(peculiarDustBlock, "peculiarDustBlock");
		GR.registerBlock(darkworldPortal, "darkworldPortal");
		
		GR.registerBlock(darkDirt, "darkDirt");
		GR.registerBlock(darkStone, "darkStone");
		GR.registerBlock(darkCobbleStone, "darkCobbleStone");
	}

}
