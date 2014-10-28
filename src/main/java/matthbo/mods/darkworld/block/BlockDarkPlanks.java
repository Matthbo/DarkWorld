package matthbo.mods.darkworld.block;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import matthbo.mods.darkworld.reference.MetaNames;
import matthbo.mods.darkworld.reference.Refs;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class BlockDarkPlanks extends BlockDarkWorld {
	
	@SideOnly(Side.CLIENT)
	private IIcon[] icons;
	
	public BlockDarkPlanks(){
		super(Material.wood);
		this.setBlockName("darkplanks");
		this.setHardness(2.0F).setResistance(5.0F);
		this.setStepSound(soundTypeWood);
	}
	
	@Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister)
    {
    	this.icons = new IIcon[MetaNames.blockDarkPlanks.length];
    	
    	for(int i = 0; i < MetaNames.blockDarkPlanks.length; ++i){
    		icons[i] = iconRegister.registerIcon(this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1) + "_" + MetaNames.blockDarkPlanks[i]);
    	}
    }
	
	/*@Override
    public Item getItemDropped(int par1, Random random, int par2)
    {
        return Item.getItemFromBlock(this);
    }*/
	
	public int damageDropped(int meta)
	{
		 return meta;
	}
	
	@SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs creativeTab, List list)
    {
		 for (int meta = 0; meta < MetaNames.blockDarkPlanks.length; meta++)
	        {
	            list.add(new ItemStack(item, 1, meta));
	        }
    }
	
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta){
		return icons[meta];
	}

    

}
