package matthbo.mods.darkworld.handler;

import java.io.File;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.LoadController;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import matthbo.mods.darkworld.reference.Refs;
import matthbo.mods.darkworld.utility.LogHelper;
import net.minecraftforge.common.config.Configuration;

public class ConfigHandler {
	
	public static Configuration config;
	
	public static final String CATEGORY_DEVELOPMENT = "dev";
	
	public static boolean dev = false;
	
	public static int darkWordID = -2;
	
	public static void init(File configFile){
		
		if(config == null){
			config = new Configuration(configFile);
			loadConfig();
		}
		
	}
	
	private static void loadConfig(){
		dev = config.getBoolean("DevMode", CATEGORY_DEVELOPMENT, false, "For devs ONLY!");
		
		darkWordID = config.getInt("DimensionID", config.CATEGORY_GENERAL, -2, -256, 256, "ID for the DarkWorld Dimension");
		
		if(dev == true) LogHelper.warn("Devmode is ON! if you are NOT a developer, turn devmode off in the config!");
		
		if(config.hasChanged()){
			config.save();
		}
	}
	
	@SubscribeEvent
	public void onConfigChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event){
		if(event.modID.equalsIgnoreCase(Refs.MOD_ID)){
			loadConfig();
		}
	}

}
