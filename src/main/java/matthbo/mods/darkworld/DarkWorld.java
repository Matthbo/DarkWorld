package matthbo.mods.darkworld;

import matthbo.mods.darkworld.handler.ConfigHandler;
import matthbo.mods.darkworld.handler.EventHandler;
import matthbo.mods.darkworld.reference.Refs;
import matthbo.mods.darkworld.utility.LogHelper;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Refs.MOD_ID, name = Refs.MOD_NAME, version = Refs.VERSION, guiFactory = Refs.GUI_FACTORY_CLASS)
public class DarkWorld {
	
	@Instance(Refs.MOD_ID)
	public static DarkWorld instance;
	
	@Mod.EventHandler
	public static void preInit(FMLPreInitializationEvent event){
		
		ConfigHandler.init(event.getSuggestedConfigurationFile());
		FMLCommonHandler.instance().bus().register(new ConfigHandler());
		
		FMLCommonHandler.instance().bus().register(new EventHandler());
		
		LogHelper.info("Pre Initialization Complete");
		
	}
	
	@Mod.EventHandler
	public static void init(FMLInitializationEvent event){
		
		LogHelper.info("Initialization Complete");
		
	}
	
	@Mod.EventHandler
	public static void postInit(FMLPostInitializationEvent event){
	
		LogHelper.info("Post Initialization Complete");
		
	}

}
