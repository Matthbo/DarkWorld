package matthbo.mods.darkworld;

import matthbo.mods.darkworld.reference.Refs;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Refs.MOD_ID, name = Refs.MOD_NAME, version = Refs.VERSION)
public class DarkWorld {
	
	@Instance(Refs.MOD_ID)
	public static DarkWorld instance;
	
	public static void preInit(FMLPreInitializationEvent event){
		
	}
	
	public static void init(FMLInitializationEvent event){
		
	}
	
	public static void postInit(FMLPostInitializationEvent event){
		
	}

}
