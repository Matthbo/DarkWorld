package matthbo.mods.darkworld;

import net.minecraftforge.common.MinecraftForge;
import matthbo.mods.darkworld.handler.BiomeDecoratorHandler;
import matthbo.mods.darkworld.handler.BucketHandler;
import matthbo.mods.darkworld.handler.ConfigHandler;
import matthbo.mods.darkworld.handler.EventHandler;
import matthbo.mods.darkworld.init.ModAchievements;
import matthbo.mods.darkworld.init.ModBiomes;
import matthbo.mods.darkworld.init.ModBlocks;
import matthbo.mods.darkworld.init.ModDimensions;
import matthbo.mods.darkworld.init.ModFluids;
import matthbo.mods.darkworld.init.ModItems;
import matthbo.mods.darkworld.init.ModRecipes;
import matthbo.mods.darkworld.proxy.IProxy;
import matthbo.mods.darkworld.reference.Refs;
import matthbo.mods.darkworld.utility.LogHelper;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Refs.MOD_ID, name = Refs.MOD_NAME, version = Refs.VERSION, guiFactory = Refs.GUI_FACTORY_CLASS, dependencies = Refs.DEPENDS)
public class DarkWorld {
	
	@Instance(Refs.MOD_ID)
	public static DarkWorld instance;
	
	@SidedProxy(clientSide = Refs.CLIENT_PROXY_CLASS, serverSide = Refs.SERVER_PROXY_CLASS)
	public static IProxy proxy;
	
	public static ConfigHandler Config = new ConfigHandler();
	
	//TODO make it so that vanilla tools don't do shit in the darkworld (will be very nice if it is easy to do)
	//TODO make stuff added to the oreDictionary
	//TODO check for more broken shit
	//I like trains and how the DW portal spits out particles in overworld but sucks them in in the DW
	
	@Mod.EventHandler
	public static void preInit(FMLPreInitializationEvent event){
		ConfigHandler.init(event.getSuggestedConfigurationFile());
		FMLCommonHandler.instance().bus().register(Config);
		ModBlocks.init();
		ModFluids.init();
		ModItems.init();
		
		MinecraftForge.EVENT_BUS.register(BucketHandler.INSTANCE);
		
		ModRecipes.initCrafting();
		ModRecipes.initSmelting();
		
		LogHelper.info("Pre Initialization Complete");
		
	}
	
	@Mod.EventHandler
	public static void init(FMLInitializationEvent event){
		ModBiomes.init();
		MinecraftForge.TERRAIN_GEN_BUS.register(new BiomeDecoratorHandler());
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
