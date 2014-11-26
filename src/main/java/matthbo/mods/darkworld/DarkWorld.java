package matthbo.mods.darkworld;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import matthbo.mods.darkworld.biome.BiomeDarkDesert;
import matthbo.mods.darkworld.biome.DarkBiomeGenBase;
import matthbo.mods.darkworld.handler.BucketHandler;
import matthbo.mods.darkworld.handler.ConfigHandler;
import matthbo.mods.darkworld.handler.EventHandler;
import matthbo.mods.darkworld.init.ModAchievements;
import matthbo.mods.darkworld.init.ModBlocks;
import matthbo.mods.darkworld.init.ModDimensions;
import matthbo.mods.darkworld.init.ModFluids;
import matthbo.mods.darkworld.init.ModItems;
import matthbo.mods.darkworld.init.ModRecipes;
import matthbo.mods.darkworld.proxy.IProxy;
import matthbo.mods.darkworld.reference.Refs;
import matthbo.mods.darkworld.utility.LogHelper;
import matthbo.mods.darkworld.world.OverworldGenerator;
import matthbo.mods.darkworld.world.WorldProviderDarkWorld;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = Refs.MOD_ID, name = Refs.MOD_NAME, version = Refs.VERSION, guiFactory = Refs.GUI_FACTORY_CLASS, dependencies = "required-after:Forge@[10.13.0.1200,)")
public class DarkWorld {
	
	@Instance(Refs.MOD_ID)
	public static DarkWorld instance;
	
	@SidedProxy(clientSide = Refs.CLIENT_PROXY_CLASS, serverSide = Refs.SERVER_PROXY_CLASS)
	public static IProxy proxy;
	
	public static DarkBiomeGenBase darkDesert; //TODO move this soon!
	
	//TODO make it so that vanilla tools don't do shit in the darkworld (will be very nice if it is easy to do)
	//TODO make stuff added to the oreDictionary
	//TODO make a better chunkprovider
	//TODO make a better portal frame thingy (update to 1.7 man!) **how to fucking do this, I copied the needed methods and changed obsidian>darkpeculiarcompresseddust, portal>portalDW, fire>darkfire
	//TODO check for more broken shit
	//I like trains and how the DW portal spits out particles in overworld but sucks them in in the DW
	
	@Mod.EventHandler
	public static void preInit(FMLPreInitializationEvent event){
		
		ConfigHandler.init(event.getSuggestedConfigurationFile());
		FMLCommonHandler.instance().bus().register(new ConfigHandler());
		
		ModBlocks.init();
		ModFluids.init();
		ModItems.init();
		
		MinecraftForge.EVENT_BUS.register(BucketHandler.INSTANCE);
		
		darkDesert = new BiomeDarkDesert(); //TODO move this soon!
		
		
		LogHelper.info("Pre Initialization Complete");
		
	}
	
	@Mod.EventHandler
	public static void init(FMLInitializationEvent event){
		ModRecipes.initCrafting();
		ModRecipes.initSmelting();
		ModDimensions.init();
		ModAchievements.init();
		
		FMLCommonHandler.instance().bus().register(new EventHandler());
		
		
		
		LogHelper.info("Initialization Complete");
	}
	
	@Mod.EventHandler
	public static void postInit(FMLPostInitializationEvent event){
	
		LogHelper.info("Post Initialization Complete");
	}

}
