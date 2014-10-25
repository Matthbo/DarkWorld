package matthbo.mods.darkworld.block;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import matthbo.mods.darkworld.reference.MetaNames;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class BlockDarkLog extends BlockLogDarkWorld {
	
	public BlockDarkLog(){
		super();
		this.setBlockName("darklog");
	}
	
	@SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs creativeTab, List list)
    {
		 for (int meta = 0; meta < MetaNames.blockDarkLog.length; meta++)
	        {
	            list.add(new ItemStack(item, 1, meta));
	        }
    }
	
	@Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister)
    {
    	this.SideIcons = new IIcon[MetaNames.blockDarkLog.length];
    	this.TopIcons = new IIcon[MetaNames.blockDarkLog.length];
    	
    	for(int i = 0; i < MetaNames.blockDarkLog.length; ++i){
    		SideIcons[i] = iconRegister.registerIcon(this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1) + "_" + MetaNames.blockDarkPlanks[i]);
    		TopIcons[i] = iconRegister.registerIcon(this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1) + "_" + MetaNames.blockDarkPlanks[i]+ "_top");
    	}
    }

}
