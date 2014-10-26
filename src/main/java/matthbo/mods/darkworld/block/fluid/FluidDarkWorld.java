package matthbo.mods.darkworld.block.fluid;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import matthbo.mods.darkworld.reference.Refs;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;

public class FluidDarkWorld extends BlockFluidClassic {
	
	@SideOnly(Side.CLIENT)
	protected IIcon stillIcon;
	@SideOnly(Side.CLIENT)
	protected IIcon flowIcon;

	public FluidDarkWorld(Fluid fluidName, Material material) {
		super(fluidName, material);
	}
	
	public boolean canDisplace(IBlockAccess world, int x, int y, int z) {
		if(world.getBlock(x, y, z).getMaterial().isLiquid()) return false;
		return super.canDisplace(world, x, y, z);
	};
	
	public boolean displaceIfPossible(World world, int x, int y, int z) {
		if(world.getBlock(x, y, z).getMaterial().isLiquid()) return false;
		return super.displaceIfPossible(world, x, y, z);
	};
	
	@Override
    public String getUnlocalizedName()
    {
        return String.format("fluid.%s%s", Refs.MOD_ID.toLowerCase() + ":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        stillIcon = iconRegister.registerIcon(this.getIconName().substring(this.getIconName().indexOf(".") + 1) + "_still");
        flowIcon = iconRegister.registerIcon(this.getIconName().substring(this.getIconName().indexOf(".") + 1) + "_flow");
    }
    
    public String getIconName(){
		return String.format("item.%s%s", Refs.MOD_ID.toLowerCase() + ":" + "fluid/", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
	}

    protected String getUnwrappedUnlocalizedName(String unlocalizedName)
    {
        return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
    }
    
    @Override
    public IIcon getIcon(int side, int meta) {
    	return (side == 0 || side == 1) ? stillIcon : flowIcon;
    }

}
