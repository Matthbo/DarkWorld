package matthbo.mods.darkworld.init;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.GameRegistry.ObjectHolder;
import matthbo.mods.darkworld.item.ItemDarkWorld;
import matthbo.mods.darkworld.item.ItemHardPeculiarDust;
import matthbo.mods.darkworld.item.ItemPeculiarDust;
import matthbo.mods.darkworld.item.ItemPeculiarDustBar;
import matthbo.mods.darkworld.item.ItemPeculiarDustNSteel;
import matthbo.mods.darkworld.item.ItemToolDarkWorld;
import matthbo.mods.darkworld.item.tool.ItemPeculiarPickaxe;
import matthbo.mods.darkworld.reference.Refs;

@ObjectHolder(Refs.MOD_ID)
public class ModItems {
	
	public static final ItemDarkWorld peculiarDust = new ItemPeculiarDust();
	public static final ItemDarkWorld peculiarDustBar = new ItemPeculiarDustBar();
	public static final ItemDarkWorld hardPeculiarDust = new ItemHardPeculiarDust();
	public static final ItemDarkWorld peculiarDustNSteel = new ItemPeculiarDustNSteel();
	
	//tools
	public static final ItemToolDarkWorld peculiarPickaxe = new ItemPeculiarPickaxe();
	
	//armor
	
	public static void init(){
		GameRegistry.registerItem(peculiarDust, "peculiarDust");
		GameRegistry.registerItem(peculiarDustBar, "peculiarDustBar");
		GameRegistry.registerItem(hardPeculiarDust, "hardPeculiarDust");
		GameRegistry.registerItem(peculiarDustNSteel, "peculiarDustNSteel");
		
		GameRegistry.registerItem(peculiarPickaxe, "peculiarPickaxe");
	}

}
