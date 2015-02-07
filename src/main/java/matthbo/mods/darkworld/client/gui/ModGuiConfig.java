package matthbo.mods.darkworld.client.gui;

import java.util.ArrayList;
import java.util.List;

import matthbo.mods.darkworld.handler.ConfigHandler;
import matthbo.mods.darkworld.reference.Refs;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.IConfigElement;
import net.minecraftforge.fml.client.config.DummyConfigElement.DummyCategoryElement;

public class ModGuiConfig extends GuiConfig {
	
	

	public ModGuiConfig(GuiScreen guiScreen){
		super(guiScreen, 
				getConfigElement(), 
				Refs.MOD_ID, 
				false, 
				false, 
				/*GuiConfig.getAbridgedConfigPath(ConfigHandler.config.toString())*/
				I18n.format(Refs.CONFIG_LANGKEY));
	}
	
	private static List<IConfigElement> getConfigElement(){
		List<IConfigElement> list = new ArrayList<IConfigElement>();
		//list.add(new DummyCategoryElement("General", Refs.CONFIG_LANGKEY_GENERAL, ModGeneralEntry.class));
		list.add(new DummyCategoryElement("General", Refs.CONFIG_LANGKEY_GENERAL, new ConfigElement(ConfigHandler.config.getCategory(Configuration.CATEGORY_GENERAL)).getChildElements()));
		list.add(new DummyCategoryElement("Dev", Refs.CONFIG_LANGKEY_DEV, new ConfigElement(ConfigHandler.config.getCategory(ConfigHandler.CATEGORY_DEVELOPMENT)).getChildElements()));
		
		return list;
	}
	
}
