package matthbo.mods.darkworld.init;

import matthbo.mods.darkworld.block.*;
import net.minecraft.block.Block;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import matthbo.mods.darkworld.creativetab.CreativeTabDarkWorld;
import matthbo.mods.darkworld.reference.Refs;

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
	public static final BlockBushDarkWorld darkSapling = new BlockDarkSapling();
	public static final Block darkSand = new BlockDarkSand();
	public static final Block darkGravel = new BlockDarkGravel();
	public static final BlockBushDarkWorld darkTallGrass = new BlockDarkTallGrass();
	public static final Block darkGoldOre = new BlockDarkGoldOre();
	public static final Block darkIronOre = new BlockDarkIronOre();
	public static final Block darkCoalOre = new BlockDarkCoalOre();
	public static final Block darkLog = new BlockDarkLog();
	public static final Block darkLog2 = new BlockDarkLog2();
	public static final BlockLeavesDarkWorld darkLeaves = new BlockDarkLeaves();//TODO: old/new leaves? or just 1 kind of leave
	public static final Block darkSponge = new BlockDarkSponge();
	public static final Block darkSandStone = new BlockDarkSandStone();
	public static final Block darkLapisOre = new BlockDarkLapisOre();
	public static final Block darkBrick = new BlockDarkBrick();
	public static final Block darkMossyCobble = new BlockDarkMossyCobbleStone();
	public static final Block darkObsidian = new BlockDarkObsidian();
	public static final Block darkDiamondOre = new BlockDarkDiamondOre();
	public static final Block darkRedstoneOre = new BlockDarkRedstoneOre(false);
	public static final Block darkLitRedstoneOre = new BlockDarkRedstoneOre(true);
	public static final BlockDarkCactus darkCactus = new BlockDarkCactus();
	public static final Block darkEmeraldOre = new BlockDarkEmeraldOre();
	public static final Block darkFire = new BlockDarkFire();
	
	
	public static void init(){
		darkRedstoneOre.setCreativeTab(CreativeTabDarkWorld.DARKWORLD_TAB);
		darkLitRedstoneOre.setLightLevel(0.625F);

        GameRegistry.registerBlock(peculiarStone, "peculiarStone");
        GameRegistry.registerBlock(peculiarCobbleStone, "peculiarCobbleStone");
        GameRegistry.registerBlock(peculiarDustBlock, "peculiarDustBlock");
        GameRegistry.registerBlock(compressedPeculiarDust, "compressedPeculiarDust");
        GameRegistry.registerBlock(darkworldPortal, "darkworldPortal");

        GameRegistry.registerBlock(darkGrass, "darkGrass");
        GameRegistry.registerBlock(darkDirt, "darkDirt");
        GameRegistry.registerBlock(darkStone, "darkStone");
        GameRegistry.registerBlock(darkCobbleStone, "darkCobbleStone");
        GameRegistry.registerBlock(darkPlanks, "darkPlanks");
        GameRegistry.registerBlock(darkSapling, "darkSapling");
        GameRegistry.registerBlock(darkSand, "darkSand");
        GameRegistry.registerBlock(darkGravel, "darkGravel");
        GameRegistry.registerBlock(darkTallGrass, "darkTallGrass");
        GameRegistry.registerBlock(darkGoldOre, "darkGoldOre");
        GameRegistry.registerBlock(darkIronOre, "darkIronOre");
        GameRegistry.registerBlock(darkCoalOre, "darkCoalOre");
        GameRegistry.registerBlock(darkLog, "darkLog");
        GameRegistry.registerBlock(darkLog2, "darkLog2");
        GameRegistry.registerBlock(darkLeaves, "darkLeaves");
        GameRegistry.registerBlock(darkSponge, "darkSponge");
        GameRegistry.registerBlock(darkSandStone, "darkSandStone");
        GameRegistry.registerBlock(darkLapisOre, "darkLapisOre");
        GameRegistry.registerBlock(darkBrick, "darkBrick");
        GameRegistry.registerBlock(darkMossyCobble, "darkMossyCobble");
        GameRegistry.registerBlock(darkObsidian, "darkObsidian");
        GameRegistry.registerBlock(darkDiamondOre, "darkDiamondOre");
        GameRegistry.registerBlock(darkRedstoneOre, "darkRedstoneOre");
        GameRegistry.registerBlock(darkLitRedstoneOre, "darkLitRedstoneOre");
        GameRegistry.registerBlock(darkCactus, "darkCactus");
        GameRegistry.registerBlock(darkEmeraldOre, "darkEmeraldOre");
        GameRegistry.registerBlock(darkFire, "darkFire");
	}

}
