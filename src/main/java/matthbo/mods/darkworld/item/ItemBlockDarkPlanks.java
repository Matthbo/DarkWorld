package matthbo.mods.darkworld.item;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import matthbo.mods.darkworld.creativetab.CreativeTabDarkWorld;
import matthbo.mods.darkworld.reference.MetaNames;
import matthbo.mods.darkworld.reference.Refs;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;

public class ItemBlockDarkPlanks extends ItemBlock{

	public ItemBlockDarkPlanks(Block block) {
		super(block);
		this.setHasSubtypes(true);
	}
	
	@Override
    public String getUnlocalizedName(ItemStack itemStack)
    {
		int i = itemStack.getItemDamage();
		if(i < 0 || i >= MetaNames.blockDarkPlanks.length) i = 0;
		
        return String.format("tile.%s.%s", super.getUnlocalizedName().substring(super.getUnlocalizedName().indexOf(".") + 1), MetaNames.blockDarkPlanks[i]);
    }
	
	public int getMetadata(int meta){
		return meta;
	}
	
	
}
