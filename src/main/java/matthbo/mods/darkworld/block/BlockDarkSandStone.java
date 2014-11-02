package matthbo.mods.darkworld.block;

import java.util.List;

import matthbo.mods.darkworld.reference.MetaNames;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockDarkSandStone extends BlockDarkWorld {
	
	@SideOnly(Side.CLIENT)
    private IIcon[] icons_M;
    @SideOnly(Side.CLIENT)
    private IIcon icons_N;
    @SideOnly(Side.CLIENT)
    private IIcon icons_O;
	
	public BlockDarkSandStone(){
		super();
		this.setBlockName("darksandstone");
		this.setHardness(0.8F);
		this.setStepSound(soundTypePiston);
	}
	
	 @SideOnly(Side.CLIENT)
	    public IIcon getIcon(int side, int meta)
	    {
	        if (side != 1 && (side != 0 || meta != 1 && meta != 2))
	        {
	            if (side == 0)
	            {
	                return this.icons_O;
	            }
	            else
	            {
	                if (meta < 0 || meta >= this.icons_M.length)
	                {
	                    meta = 0;
	                }

	                return this.icons_M[meta];
	            }
	        }
	        else
	        {
	            return this.icons_N;
	        }
	    }

	    /**
	     * Determines the damage on the item the block drops. Used in cloth and wood.
	     */
	    public int damageDropped(int par1)
	    {
	        return par1;
	    }

	    /**
	     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
	     */
	    @SideOnly(Side.CLIENT)
	    public void getSubBlocks(Item item, CreativeTabs p_149666_2_, List list)
	    {
	    	for (int meta = 0; meta < MetaNames.blockDarkSandStone_b.length; meta++)
	        {
	            list.add(new ItemStack(item, 1, meta));
	        }
	    }

	    @SideOnly(Side.CLIENT)
	    public void registerBlockIcons(IIconRegister iconRegister)
	    {
	        this.icons_M = new IIcon[MetaNames.blockDarkSandStone_b.length];

	        for (int i = 0; i < this.icons_M.length; ++i)
	        {
	            this.icons_M[i] = iconRegister.registerIcon(this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1) + "_" + MetaNames.blockDarkSandStone_b[i]);
	        }

	        this.icons_N = iconRegister.registerIcon(this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1) + "_top");
	        this.icons_O = iconRegister.registerIcon(this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1) + "_bottom");
	    }

}
