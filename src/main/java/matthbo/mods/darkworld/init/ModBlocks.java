package matthbo.mods.darkworld.init;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFire;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.GameRegistry.ObjectHolder;
import matthbo.mods.darkworld.DarkWorld;
import matthbo.mods.darkworld.block.BlockCompressedPeculiarDust;
import matthbo.mods.darkworld.block.BlockDarkBrick;
import matthbo.mods.darkworld.block.BlockDarkCoalOre;
import matthbo.mods.darkworld.block.BlockDarkCobbleStone;
import matthbo.mods.darkworld.block.BlockDarkDiamondOre;
import matthbo.mods.darkworld.block.BlockDarkDirt;
import matthbo.mods.darkworld.block.BlockDarkEmeraldOre;
import matthbo.mods.darkworld.block.BlockDarkFire;
import matthbo.mods.darkworld.block.BlockDarkGoldOre;
import matthbo.mods.darkworld.block.BlockDarkGrass;
import matthbo.mods.darkworld.block.BlockDarkGravel;
import matthbo.mods.darkworld.block.BlockDarkIronOre;
import matthbo.mods.darkworld.block.BlockDarkLapisOre;
import matthbo.mods.darkworld.block.BlockDarkLog;
import matthbo.mods.darkworld.block.BlockDarkLog2;
import matthbo.mods.darkworld.block.BlockDarkMossyCobbleStone;
import matthbo.mods.darkworld.block.BlockDarkObsidian;
import matthbo.mods.darkworld.block.BlockDarkPlanks;
import matthbo.mods.darkworld.block.BlockDarkRedstoneOre;
import matthbo.mods.darkworld.block.BlockDarkSand;
import matthbo.mods.darkworld.block.BlockDarkSandStone;
import matthbo.mods.darkworld.block.BlockDarkSponge;
import matthbo.mods.darkworld.block.BlockDarkStone;
import matthbo.mods.darkworld.block.BlockDarkWorld;
import matthbo.mods.darkworld.block.BlockFallingDarkWorld;
import matthbo.mods.darkworld.block.BlockLeavesDarkWorld;
import matthbo.mods.darkworld.block.BlockLogDarkWorld;
import matthbo.mods.darkworld.block.BlockPecluliarDust;
import matthbo.mods.darkworld.block.BlockPeculiarCobbleStone;
import matthbo.mods.darkworld.block.BlockPeculiarStone;
import matthbo.mods.darkworld.block.BlockPortalBaseDarkWorld;
import matthbo.mods.darkworld.block.BlockPortalDarkWord;
import matthbo.mods.darkworld.block.BlockTest;
import matthbo.mods.darkworld.block.fluid.FluidDarkLava;
import matthbo.mods.darkworld.block.fluid.FluidDarkWater;
import matthbo.mods.darkworld.block.fluid.FluidDarkWorld;
import matthbo.mods.darkworld.item.ItemBlockDarkLog;
import matthbo.mods.darkworld.item.ItemBlockDarkLog2;
import matthbo.mods.darkworld.item.ItemBlockDarkPlanks;
import matthbo.mods.darkworld.item.ItemBlockDarkSand;
import matthbo.mods.darkworld.item.ItemBlockDarkSandStone;
import matthbo.mods.darkworld.reference.Refs;
import matthbo.mods.darkworld.utility.LogHelper;

@ObjectHolder(Refs.MOD_ID)
public class ModBlocks {
	
	private static GameRegistry GR;
	
	
	//in the overworld
	public static final BlockDarkWorld peculiarStone = new BlockPeculiarStone();
	public static final BlockDarkWorld peculiarCobbleStone = new BlockPeculiarCobbleStone();
	public static final BlockFallingDarkWorld peculiarDustBlock = new BlockPecluliarDust();
	public static final BlockDarkWorld compressedPeculiarDust = new BlockCompressedPeculiarDust();
	public static final BlockPortalBaseDarkWorld darkworldPortal = new BlockPortalDarkWord();

	//in the darkworld
	public static final BlockDarkGrass darkGrass = new BlockDarkGrass();
	public static final BlockDarkWorld darkDirt = new BlockDarkDirt();
	public static final BlockDarkWorld darkStone = new BlockDarkStone();
	public static final BlockDarkWorld darkCobbleStone = new BlockDarkCobbleStone();
	public static final BlockDarkWorld darkPlanks = new BlockDarkPlanks();
	//public static final BlockDarkWorld darkSapling = new BlockDarkSapling();
	public static final BlockFallingDarkWorld darkSand = new BlockDarkSand();
	public static final BlockFallingDarkWorld darkGravel = new BlockDarkGravel();
	public static final BlockDarkWorld darkGoldOre = new BlockDarkGoldOre();
	public static final BlockDarkWorld darkIronOre = new BlockDarkIronOre();
	public static final BlockDarkWorld darkCoalOre = new BlockDarkCoalOre();
	public static final BlockLogDarkWorld darkLog = new BlockDarkLog();
	public static final BlockLogDarkWorld darkLog2 = new BlockDarkLog2();
	//public static final BlockLeavesDarkWorld darkLeaves = new BlockDarkLeaves();
	//public static final BlockDarkLeaves darkLeaves2 = new BlockDarkLeaves2();
	public static final BlockDarkWorld darkSponge = new BlockDarkSponge();
	public static final BlockDarkWorld darkSandStone = new BlockDarkSandStone();
	public static final BlockDarkWorld darkLapisOre = new BlockDarkLapisOre();
	public static final BlockDarkWorld darkBrick = new BlockDarkBrick();
	public static final BlockDarkWorld darkMossyCobble = new BlockDarkMossyCobbleStone();
	public static final BlockDarkWorld darkObsidian = new BlockDarkObsidian();
	public static final BlockDarkWorld darkDiamondOre = new BlockDarkDiamondOre();
	public static final BlockDarkWorld darkRedstoneOre = new BlockDarkRedstoneOre();
	public static final BlockDarkWorld darkEmeraldOre = new BlockDarkEmeraldOre();
	public static final BlockDarkFire darkFire = new BlockDarkFire();
	
	
	public static void init(){
		GR.registerBlock(peculiarStone, "peculiarStone");
		GR.registerBlock(peculiarCobbleStone, "peculiarCobbleStone");
		GR.registerBlock(peculiarDustBlock, "peculiarDustBlock");
		GR.registerBlock(compressedPeculiarDust, "compressedPeculiarDust");
		GR.registerBlock(darkworldPortal, "darkworldPortal");
		
		GR.registerBlock(darkGrass, "darkGrass");
		GR.registerBlock(darkDirt, "darkDirt");
		GR.registerBlock(darkStone, "darkStone");
		GR.registerBlock(darkCobbleStone, "darkCobbleStone");
		GR.registerBlock(darkPlanks, ItemBlockDarkPlanks.class, "darkPlanks");
		GR.registerBlock(darkSand, ItemBlockDarkSand.class,"darkSand");
		GR.registerBlock(darkGravel, "darkGravel");
		GR.registerBlock(darkGoldOre, "darkGoldOre");
		GR.registerBlock(darkIronOre, "darkIronOre");
		GR.registerBlock(darkCoalOre, "darkCoalOre");
		GR.registerBlock(darkLog, ItemBlockDarkLog.class,"darkLog");
		GR.registerBlock(darkLog2, ItemBlockDarkLog2.class,"darkLog2");
		GR.registerBlock(darkSponge, "darkSponge");
		GR.registerBlock(darkSandStone, ItemBlockDarkSandStone.class, "darkSandStone");
		GR.registerBlock(darkLapisOre, "darkLapisOre");
		GR.registerBlock(darkBrick, "darkBrick");
		GR.registerBlock(darkMossyCobble, "darkMossyCobble");
		GR.registerBlock(darkObsidian, "darkObsidian");
		GR.registerBlock(darkDiamondOre, "darkDiamondOre");
		GR.registerBlock(darkRedstoneOre, "darkRedstoneOre");
		GR.registerBlock(darkEmeraldOre, "darkEmeraldOre");
		GR.registerBlock(darkFire, "darkFire");
	}

}
