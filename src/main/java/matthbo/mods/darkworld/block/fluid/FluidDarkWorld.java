package matthbo.mods.darkworld.block.fluid;

import net.minecraft.util.BlockPos;
import matthbo.mods.darkworld.reference.Refs;
import net.minecraft.block.material.Material;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;

public class FluidDarkWorld extends BlockFluidClassic {

	public FluidDarkWorld(Fluid fluidName, Material material) {
		super(fluidName, material);
	}

	public boolean canDisplace(IBlockAccess world, BlockPos pos) {
		if(world.getBlockState(pos).getBlock().getMaterial().isLiquid()) return false;
		return super.canDisplace(world, pos);
	}
	
	public boolean displaceIfPossible(World world, BlockPos pos) {
		if(world.getBlockState(pos).getBlock().getMaterial().isLiquid()) return false;
		return super.displaceIfPossible(world, pos);
	}
	
	@Override
    public String getUnlocalizedName()
    {
        return String.format("fluid.%s%s", Refs.MOD_ID.toLowerCase() + ":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }

    protected String getUnwrappedUnlocalizedName(String unlocalizedName)
    {
        return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
    }

}
