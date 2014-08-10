package matthbo.mods.darkworld.client.gui;

import java.util.ArrayList;
import java.util.List;

import matthbo.mods.darkworld.handler.ConfigHandler;
import matthbo.mods.darkworld.reference.Refs;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.common.config.ConfigElement;
import cpw.mods.fml.client.config.GuiConfig;
import cpw.mods.fml.client.config.GuiConfigEntries;
import cpw.mods.fml.client.config.GuiConfigEntries.CategoryEntry;
import cpw.mods.fml.client.config.IConfigElement;
import cpw.mods.fml.client.config.DummyConfigElement.DummyCategoryElement;

public class ModGuiConfig extends GuiConfig {
	
	

	public ModGuiConfig(GuiScreen guiScreen){
		super(guiScreen, 
				getConfigElement(), 
				Refs.MOD_ID, 
				false, 
				false, 
				GuiConfig.getAbridgedConfigPath(ConfigHandler.config.toString()));
	}
	
	private static List<IConfigElement> getConfigElement(){
		List<IConfigElement> list = new ArrayList<IConfigElement>();
		list.add(new DummyCategoryElement("Dev", Refs.CONFIG_LANGKEY_DEV, ModDevelopmentEntry.class));
		
		return list;
	}
	
	public static class ModDevelopmentEntry extends CategoryEntry{

		public ModDevelopmentEntry(GuiConfig owningScreen, GuiConfigEntries owningEntryList, IConfigElement configElement) {
			super(owningScreen, owningEntryList, configElement);
		}
		
		@Override
		protected GuiScreen buildChildScreen(){
			return new GuiConfig(this.owningScreen,
					(new ConfigElement(ConfigHandler.config.getCategory(ConfigHandler.CATEGORY_DEVELOPMENT)).getChildElements()),
					this.owningScreen.modID,
					false,
					false,
					I18n.format(Refs.CONFIG_LANGKEY_DEV));
			
		}
		
	}
	
}
