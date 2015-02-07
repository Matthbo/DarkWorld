package matthbo.mods.darkworld.block;

import java.util.Iterator;

import matthbo.mods.darkworld.creativetab.CreativeTabDarkWorld;
import matthbo.mods.darkworld.reference.Refs;
import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockLogDarkWorld extends BlockRotatedPillar {

    public static final PropertyEnum LOG_AXIS = PropertyEnum.create("axis", BlockLogDarkWorld.EnumAxis.class);

	public BlockLogDarkWorld(){
		super(Material.wood);
		this.setHardness(2F);
		this.setStepSound(soundTypeWood);
		this.setCreativeTab(CreativeTabDarkWorld.DARKWORLD_TAB);
	}
	
	@Override
    public String getUnlocalizedName()
    {
        return String.format("tile.%s%s", Refs.MOD_ID.toLowerCase() + ":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }

    protected String getUnwrappedUnlocalizedName(String unlocalizedName)
    {
        return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
    }

	public void breakBlock(World world, BlockPos pos, IBlockState state)
    {
        byte b0 = 4;
        int i = b0 + 1;

        if (world.isAreaLoaded(pos.add(-i, -i, -i), pos.add(i, i, i)))
        {
            Iterator iterator = BlockPos.getAllInBox(pos.add(-b0, -b0, -b0), pos.add(b0, b0, b0)).iterator();

            while (iterator.hasNext())
            {
                BlockPos blockpos1 = (BlockPos)iterator.next();
                IBlockState iblockstate1 = world.getBlockState(blockpos1);

                if (iblockstate1.getBlock().isLeaves(world, blockpos1))
                {
                    iblockstate1.getBlock().beginLeavesDecay(world, blockpos1);
                }
            }
        }
    }

    public IBlockState onBlockPlaced(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer){
        return super.onBlockPlaced(world, pos, facing, hitX, hitY, hitZ, meta, placer);
    }

    @Override
    public boolean canSustainLeaves(IBlockAccess world, BlockPos pos)
    {
        return true;
    }
    
    @Override
    public boolean isWood(IBlockAccess world, BlockPos pos)
    {
        return true;
    }

    public static enum EnumAxis implements IStringSerializable
    {
        X("x"),
        Y("y"),
        Z("z"),
        NONE("none");
        private final String name;

        private static final String __OBFID = "CL_00002100";

        private EnumAxis(String name)
        {
            this.name = name;
        }

        public String toString()
        {
            return this.name;
        }

        public static BlockLogDarkWorld.EnumAxis fromFacingAxis(EnumFacing.Axis axis)
        {
            switch (BlockLogDarkWorld.SwitchAxis.AXIS_LOOKUP[axis.ordinal()])
            {
                case 1:
                    return X;
                case 2:
                    return Y;
                case 3:
                    return Z;
                default:
                    return NONE;
            }
        }

        public String getName()
        {
            return this.name;
        }
    }

    static final class SwitchAxis
    {
        static final int[] AXIS_LOOKUP = new int[EnumFacing.Axis.values().length];
        private static final String __OBFID = "CL_00002101";

        static
        {
            try
            {
                AXIS_LOOKUP[EnumFacing.Axis.X.ordinal()] = 1;
            }
            catch (NoSuchFieldError var3)
            {
                ;
            }

            try
            {
                AXIS_LOOKUP[EnumFacing.Axis.Y.ordinal()] = 2;
            }
            catch (NoSuchFieldError var2)
            {
                ;
            }

            try
            {
                AXIS_LOOKUP[EnumFacing.Axis.Z.ordinal()] = 3;
            }
            catch (NoSuchFieldError var1)
            {
                ;
            }
        }
    }
	
}
