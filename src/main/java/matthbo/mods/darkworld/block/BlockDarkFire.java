package matthbo.mods.darkworld.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import matthbo.mods.darkworld.DarkWorld;
import matthbo.mods.darkworld.creativetab.CreativeTabDarkWorld;
import matthbo.mods.darkworld.init.ModBlocks;
import matthbo.mods.darkworld.init.ModDimensions;
import matthbo.mods.darkworld.reference.Refs;
import net.minecraft.block.BlockFire;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockDarkFire extends BlockFire {
	
	@SideOnly(Side.CLIENT)
    private IIcon[] blockIcon;
	
	public BlockDarkFire() {
		super();
		this.setBlockName("darkfire");
	}
	
	@Override
    public String getUnlocalizedName()
    {
        return String.format("tile.%s%s", Refs.MOD_ID.toLowerCase() + ":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }
    
	@SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister){
    	blockIcon = new IIcon[] {iconRegister.registerIcon("darkworld:darkfire_0"), iconRegister.registerIcon("darkworld:darkfire_1")};
    }
	
	@SideOnly(Side.CLIENT)
    public IIcon getFireIcon(int p_149840_1_)
    {
        return this.blockIcon[p_149840_1_];
    }
	
	@SideOnly(Side.CLIENT)
    public IIcon getIcon(int p_149691_1_, int p_149691_2_)
    {
        return this.blockIcon[0];
    }

    protected String getUnwrappedUnlocalizedName(String unlocalizedName)
    {
        return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
    }
    
    public void onBlockAdded(World world, int x, int y, int z){
    	if(!(world.provider.dimensionId == 0 || world.provider.dimensionId == ModDimensions.dimensionIDDarkWorld) || !ModBlocks.darkworldPortal.func_150000_e(world, x, y, z)){
    		if(!world.doesBlockHaveSolidTopSurface(world, x, y-1, z)){
    			world.setBlockToAir(x, y, z);
    		}else{
    			world.scheduleBlockUpdate(x, y, z, this, this.tickRate(world) + world.rand.nextInt(10));
    		}
    	}
    }

}
