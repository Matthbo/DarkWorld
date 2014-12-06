package matthbo.mods.darkworld.block;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import matthbo.mods.darkworld.init.ModBlocks;
import matthbo.mods.darkworld.reference.MetaNames;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

public class BlockDarkSand extends BlockFallingDarkWorld {
	
	@SideOnly(Side.CLIENT)
	private IIcon[] icons;
	
	public BlockDarkSand(){
		super(Material.sand);
		this.setBlockName("darksand");
		this.setHardness(0.5F);
		this.setStepSound(soundTypeSand);
	}
	
	@SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs creativeTab, List list)
    {
		 for (int meta = 0; meta < MetaNames.blockDarkSand.length; meta++)
	        {
	            list.add(new ItemStack(item, 1, meta));
	        }
    }
	
	@Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister)
    {
    	this.icons = new IIcon[MetaNames.blockDarkSand.length];
    	
    	for(int i = 0; i < MetaNames.blockDarkSand.length; ++i){
    		icons[i] = iconRegister.registerIcon(this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1) + "_" + MetaNames.blockDarkSand[i]);
    	}
    }
	
	public int damageDropped(int meta)
	{
		 return meta;
	}
	
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta){
		return icons[meta];
	}
	

}
