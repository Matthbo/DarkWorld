package matthbo.mods.darkworld.init;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.GameRegistry.ObjectHolder;
import matthbo.mods.darkworld.item.HardPeculiarDust;
import matthbo.mods.darkworld.item.ItemDarkWorld;
import matthbo.mods.darkworld.item.ItemPeculiarDust;
import matthbo.mods.darkworld.item.PeculiarDustBar;
import matthbo.mods.darkworld.item.PeculiarDustNSteel;
import matthbo.mods.darkworld.reference.Refs;

@ObjectHolder(Refs.MOD_ID)
public class ModItems {
	
	public static final ItemDarkWorld peculiarDust = new ItemPeculiarDust();
	public static final ItemDarkWorld peculiarDustBar = new PeculiarDustBar();
	public static final ItemDarkWorld hardPeculiarDust = new HardPeculiarDust();
	public static final ItemDarkWorld peculiarDustNSteel = new PeculiarDustNSteel();
	
	public static void init(){
		GameRegistry.registerItem(peculiarDust, "peculiarDust");
		GameRegistry.registerItem(peculiarDustBar, "peculiarDustBar");
		GameRegistry.registerItem(hardPeculiarDust, "hardPeculiarDust");
		GameRegistry.registerItem(peculiarDustNSteel, "peculiarDustNSteel");
	}

}
