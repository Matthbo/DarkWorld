package matthbo.mods.darkworld.init;

import matthbo.mods.darkworld.block.*;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFire;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.GameRegistry.ObjectHolder;
import matthbo.mods.darkworld.DarkWorld;
import matthbo.mods.darkworld.block.fluid.FluidDarkLava;
import matthbo.mods.darkworld.block.fluid.FluidDarkWater;
import matthbo.mods.darkworld.block.fluid.FluidDarkWorld;
import matthbo.mods.darkworld.creativetab.CreativeTabDarkWorld;
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
	public static final Block peculiarStone = new BlockPeculiarStone();
	public static final Block peculiarCobbleStone = new BlockPeculiarCobbleStone();
	public static final Block peculiarDustBlock = new BlockPecluliarDust();
	public static final Block compressedPeculiarDust = new BlockCompressedPeculiarDust();
	public static final BlockPortalBaseDarkWorld darkworldPortal = new BlockPortalDarkWord();

	//in the darkworld
	public static final Block darkGrass = new BlockDarkGrass();
	public static final Block darkDirt = new BlockDarkDirt();
	public static final Block darkStone = new BlockDarkStone();
	public static final Block darkCobbleStone = new BlockDarkCobbleStone();
	public static final Block darkPlanks = new BlockDarkPlanks();
	//public static final BlockDarkWorld darkSapling = new BlockDarkSapling();
	public static final Block darkSand = new BlockDarkSand();
	public static final Block darkGravel = new BlockDarkGravel();
	public static final BlockBushDarkWorld darkTallGrass = new BlockDarkTallGrass();
	public static final Block darkGoldOre = new BlockDarkGoldOre();
	public static final Block darkIronOre = new BlockDarkIronOre();
	public static final Block darkCoalOre = new BlockDarkCoalOre();
	public static final Block darkLog = new BlockDarkLog();
	public static final Block darkLog2 = new BlockDarkLog2();
	//public static final BlockLeavesDarkWorld darkLeaves = new BlockDarkLeaves();
	//public static final BlockDarkLeaves darkLeaves2 = new BlockDarkLeaves2();
	public static final Block darkSponge = new BlockDarkSponge();
	public static final Block darkSandStone = new BlockDarkSandStone();
	public static final Block darkLapisOre = new BlockDarkLapisOre();
	public static final Block darkBrick = new BlockDarkBrick();
	public static final Block darkMossyCobble = new BlockDarkMossyCobbleStone();
	public static final Block darkObsidian = new BlockDarkObsidian();
	public static final Block darkDiamondOre = new BlockDarkDiamondOre();
	public static final Block darkRedstoneOre = new BlockDarkRedstoneOre(false);
	public static final Block darkLitRedstoneOre = new BlockDarkRedstoneOre(true);
	public static final BlockDarkWorld darkCactus = new BlockDarkCactus();
	public static final Block darkEmeraldOre = new BlockDarkEmeraldOre();
	public static final Block darkFire = new BlockDarkFire();
	
	
	public static void init(){
		darkRedstoneOre.setCreativeTab(CreativeTabDarkWorld.DARKWORLD_TAB);
		darkLitRedstoneOre.setLightLevel(0.625F);
		
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
		GR.registerBlock(darkTallGrass, "darkTallGrass");
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
		GR.registerBlock(darkLitRedstoneOre, "darkLitRedstoneOre");
		GR.registerBlock(darkCactus, "darkCactus");
		GR.registerBlock(darkEmeraldOre, "darkEmeraldOre");
		GR.registerBlock(darkFire, "darkFire");
	}

}
