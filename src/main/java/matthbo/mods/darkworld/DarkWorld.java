package matthbo.mods.darkworld;

import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
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
	
	//TODO make stuff added to the oreDictionary
	//TODO change dimension to be 100% 1.7! (or 1.8 if its posible)
	
	@Mod.EventHandler
	public static void preInit(FMLPreInitializationEvent event){
		
		ConfigHandler.init(event.getSuggestedConfigurationFile());
		FMLCommonHandler.instance().bus().register(new ConfigHandler());
		
		ModBlocks.init();
		ModItems.init();
		ModFluids.init();
		
		MinecraftForge.EVENT_BUS.register(BucketHandler.INSTANCE);
		
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
