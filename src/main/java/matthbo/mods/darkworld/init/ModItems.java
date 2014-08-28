package matthbo.mods.darkworld.init;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.GameRegistry.ObjectHolder;
import matthbo.mods.darkworld.item.ItemDarkWorld;
import matthbo.mods.darkworld.item.ItemPeculiarDust;
import matthbo.mods.darkworld.reference.Refs;

@ObjectHolder(Refs.MOD_ID)
public class ModItems {
	
	public static final ItemDarkWorld peculiarDust = new ItemPeculiarDust();
	
	public static void init(){
		GameRegistry.registerItem(peculiarDust, "peculiarDust");
	}

}
