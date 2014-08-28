package matthbo.mods.darkworld.init;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.GameRegistry.ObjectHolder;
import matthbo.mods.darkworld.block.BlockDarkCobbleStone;
import matthbo.mods.darkworld.block.BlockDarkDirt;
import matthbo.mods.darkworld.block.BlockDarkStone;
import matthbo.mods.darkworld.block.BlockDarkWorld;
import matthbo.mods.darkworld.block.BlockPeculiarCobbleStone;
import matthbo.mods.darkworld.block.BlockPeculiarStone;
import matthbo.mods.darkworld.block.BlockTest;
import matthbo.mods.darkworld.item.BlockPecluliarDust;
import matthbo.mods.darkworld.reference.Refs;

@ObjectHolder(Refs.MOD_ID)
public class ModBlocks {
	
	//public static final BlockDarkWorld test = new BlockTest();
	
	//in the overworld
	public static final BlockDarkWorld peculiarStone = new BlockPeculiarStone();
	public static final BlockDarkWorld peculiarCobbleStone = new BlockPeculiarCobbleStone();
	public static final BlockDarkWorld peculiarDustBlock = new BlockPecluliarDust();

	//in the darkworld
	public static final BlockDarkWorld darkDirt = new BlockDarkDirt();
	public static final BlockDarkWorld darkStone = new BlockDarkStone();
	public static final BlockDarkWorld darkCobbleStone = new BlockDarkCobbleStone();
	
	public static void init(){
		//GameRegistry.registerBlock(test, "test");
		
		GameRegistry.registerBlock(peculiarStone, "peculiarStone");
		GameRegistry.registerBlock(peculiarCobbleStone, "peculiarCobbleStone");
		GameRegistry.registerBlock(peculiarDustBlock, "peculiarDustBlock");
		
		GameRegistry.registerBlock(darkDirt, "darkDirt");
		GameRegistry.registerBlock(darkStone, "darkStone");
		GameRegistry.registerBlock(darkCobbleStone, "darkCobbleStone");
	}

}
