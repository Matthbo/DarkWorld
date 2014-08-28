package matthbo.mods.darkworld.creativetab;

import matthbo.mods.darkworld.init.ModBlocks;
import matthbo.mods.darkworld.reference.Refs;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class CreativeTabDarkWorld {
	
	public static final CreativeTabs DARKWORLD_TAB = new CreativeTabs(Refs.MOD_ID.toLowerCase()){

		@Override
		public Item getTabIconItem() {
			//make icon dark grass
			return Item.getItemFromBlock(ModBlocks.darkDirt);
		}
		
	};

}
