package matthbo.mods.darkworld.item;

import matthbo.mods.darkworld.creativetab.CreativeTabDarkWorld;
import matthbo.mods.darkworld.reference.Refs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemDarkWorld extends Item {
	
	public ItemDarkWorld(){
		super();
		this.setCreativeTab(CreativeTabDarkWorld.DARKWORLD_TAB);
	}
	
	@Override
	public String getUnlocalizedName(){
		return String.format("item.%s%s", Refs.MOD_ID.toLowerCase() + ":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
	}
	
	@Override
	public String getUnlocalizedName(ItemStack itemStack){
		return String.format("item.%s%s", Refs.MOD_ID.toLowerCase() + ":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
	}
	
	protected String getUnwrappedUnlocalizedName(String unlocalizedName)
    {
        return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
    }
}