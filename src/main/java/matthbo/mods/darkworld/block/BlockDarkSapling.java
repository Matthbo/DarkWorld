package matthbo.mods.darkworld.block;

import matthbo.mods.darkworld.world.gen.feature.*;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.IGrowable;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.*;

import java.util.Random;

public class BlockDarkSapling extends BlockBushDarkWorld implements IGrowable{

    public static final PropertyEnum TYPE = PropertyEnum.create("type", BlockDarkPlanks.EnumType.class);
    public static final PropertyInteger STAGE = PropertyInteger.create("stage", 0, 1);

    public BlockDarkSapling(){
        float f = 0.4F;
        this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f * 2.0F, 0.5F + f);
        this.setUnlocalizedName("darksapling");
        this.setHardness(0.0F);
        this.setStepSound(soundTypeGrass);
        this.setDefaultState(this.blockState.getBaseState().withProperty(TYPE, BlockDarkPlanks.EnumType.DARKOAK).withProperty(STAGE, Integer.valueOf(0)));
    }

    public void updateTick(World world, BlockPos pos, IBlockState state, Random rand)
    {
        if (!world.isRemote)
        {
            super.updateTick(world, pos, state, rand);

            if (world.getLightFromNeighbors(pos.up()) >= 9 && rand.nextInt(7) == 0)
            {
                this.grow(world, pos, state, rand);
            }
        }
    }

    public void grow(World world, BlockPos pos, IBlockState state, Random rand)
    {
        if (((Integer)state.getValue(STAGE)).intValue() == 0)
        {
            world.setBlockState(pos, state.cycleProperty(STAGE), 4);
        }
        else
        {
            this.generateTree(world, pos, state, rand);
        }
    }

    //TODO: make all tree gen stuff and edit this
    public void generateTree(World world, BlockPos pos, IBlockState state, Random rand)
    {
        if (!net.minecraftforge.event.terraingen.TerrainGen.saplingGrowTree(world, rand, pos)) return;
        Object object = rand.nextInt(10) == 0 ? new DarkWorldGenBigTree(true) : new DarkWorldGenTrees(true);
        int i = 0;
        int j = 0;
        boolean flag = false;

        switch (BlockDarkSapling.SwitchEnumType.WOOD_TYPE_LOOKUP[((BlockPlanks.EnumType)state.getValue(TYPE)).ordinal()])
        {
            case 1:
                label78:

                for (i = 0; i >= -1; --i)
                {
                    for (j = 0; j >= -1; --j)
                    {
                        if (this.isTypeAt(world, pos.add(i, 0, j), BlockDarkPlanks.EnumType.DARKSPRUCE) && this.isTypeAt(world, pos.add(i + 1, 0, j), BlockDarkPlanks.EnumType.DARKSPRUCE) && this.isTypeAt(world, pos.add(i, 0, j + 1), BlockDarkPlanks.EnumType.DARKSPRUCE) && this.isTypeAt(world, pos.add(i + 1, 0, j + 1), BlockDarkPlanks.EnumType.DARKSPRUCE))
                        {
                            object = new DarkWorldGenMegaPineTree(false, rand.nextBoolean());
                            flag = true;
                            break label78;
                        }
                    }
                }

                if (!flag)
                {
                    j = 0;
                    i = 0;
                    object = new DarkWorldGenTaiga2(true);
                }

                break;
            case 2:
                object = new DarkWorldGenForest(true, false);
                break;
            case 3:
                label93:

                for (i = 0; i >= -1; --i)
                {
                    for (j = 0; j >= -1; --j)
                    {
                        if (this.isTypeAt(world, pos.add(i, 0, j), BlockDarkPlanks.EnumType.DARKJUNGLE) && this.isTypeAt(world, pos.add(i + 1, 0, j), BlockDarkPlanks.EnumType.DARKJUNGLE) && this.isTypeAt(world, pos.add(i, 0, j + 1), BlockDarkPlanks.EnumType.DARKJUNGLE) && this.isTypeAt(world, pos.add(i + 1, 0, j + 1), BlockDarkPlanks.EnumType.DARKJUNGLE))
                        {
                            object = new DarkWorldGenMegaJungle(true, 10, 20, BlockDarkPlanks.EnumType.DARKJUNGLE.getMetadata(), BlockDarkPlanks.EnumType.DARKJUNGLE.getMetadata());
                            flag = true;
                            break label93;
                        }
                    }
                }

                if (!flag)
                {
                    j = 0;
                    i = 0;
                    object = new DarkWorldGenTrees(true, 4 + rand.nextInt(7), BlockDarkPlanks.EnumType.DARKJUNGLE.getMetadata(), BlockDarkPlanks.EnumType.DARKJUNGLE.getMetadata(), false);
                }

                break;
            case 4:
                object = new DarkWorldGenSavannaTree(true);
                break;
            case 5:
                label108:

                for (i = 0; i >= -1; --i)
                {
                    for (j = 0; j >= -1; --j)
                    {
                        if (this.isTypeAt(world, pos.add(i, 0, j), BlockDarkPlanks.EnumType.DARKBIG_OAK) && this.isTypeAt(world, pos.add(i + 1, 0, j), BlockDarkPlanks.EnumType.DARKBIG_OAK) && this.isTypeAt(world, pos.add(i, 0, j + 1), BlockDarkPlanks.EnumType.DARKBIG_OAK) && this.isTypeAt(world, pos.add(i + 1, 0, j + 1), BlockDarkPlanks.EnumType.DARKBIG_OAK))
                        {
                            object = new DarkWorldGenCanopyTree(true);
                            flag = true;
                            break label108;
                        }
                    }
                }

                if (!flag)
                {
                    return;
                }
            case 6:
        }

        IBlockState iblockstate1 = Blocks.air.getDefaultState();

        if (flag)
        {
            world.setBlockState(pos.add(i, 0, j), iblockstate1, 4);
            world.setBlockState(pos.add(i + 1, 0, j), iblockstate1, 4);
            world.setBlockState(pos.add(i, 0, j + 1), iblockstate1, 4);
            world.setBlockState(pos.add(i + 1, 0, j + 1), iblockstate1, 4);
        }
        else
        {
            world.setBlockState(pos, iblockstate1, 4);
        }

        if (!((WorldGenerator)object).generate(world, rand, pos.add(i, 0, j)))
        {
            if (flag)
            {
                world.setBlockState(pos.add(i, 0, j), state, 4);
                world.setBlockState(pos.add(i + 1, 0, j), state, 4);
                world.setBlockState(pos.add(i, 0, j + 1), state, 4);
                world.setBlockState(pos.add(i + 1, 0, j + 1), state, 4);
            }
            else
            {
                world.setBlockState(pos, state, 4);
            }
        }
    }

    public boolean isTypeAt(World worldIn, BlockPos pos, BlockDarkPlanks.EnumType type)
    {
        IBlockState iblockstate = worldIn.getBlockState(pos);
        return iblockstate.getBlock() == this && iblockstate.getValue(TYPE) == type;
    }

    /**
     * Determines the damage on the item the block drops. Used in cloth and wood.
     */
    public int damageDropped(IBlockState state)
    {
        return ((BlockPlanks.EnumType)state.getValue(TYPE)).getMetadata();
    }

    public boolean canGrow(World world, BlockPos pos, IBlockState state, boolean isClient)
    {
        return true;
    }

    public boolean canUseBonemeal(World world, Random rand, BlockPos pos, IBlockState state)
    {
        return (double)world.rand.nextFloat() < 0.45D;
    }

    public void grow(World world, Random rand, BlockPos pos, IBlockState state)
    {
        this.grow(world, pos, state, rand);
    }

    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(TYPE, BlockDarkPlanks.EnumType.byMetadata(meta & 7)).withProperty(STAGE, Integer.valueOf((meta & 8) >> 3));
    }

    public int getMetaFromState(IBlockState state)
    {
        byte b0 = 0;
        int i = b0 | ((BlockDarkPlanks.EnumType)state.getValue(TYPE)).getMetadata();
        i |= ((Integer)state.getValue(STAGE)).intValue() << 3;
        return i;
    }

    protected BlockState createBlockState()
    {
        return new BlockState(this, new IProperty[] {TYPE, STAGE});
    }

    static final class SwitchEnumType
    {
        static final int[] WOOD_TYPE_LOOKUP = new int[BlockDarkPlanks.EnumType.values().length];
        private static final String __OBFID = "CL_00002067";

        static
        {
            try
            {
                WOOD_TYPE_LOOKUP[BlockDarkPlanks.EnumType.DARKSPRUCE.ordinal()] = 1;
            }
            catch (NoSuchFieldError var6)
            {
                ;
            }

            try
            {
                WOOD_TYPE_LOOKUP[BlockDarkPlanks.EnumType.DARKBIRCH.ordinal()] = 2;
            }
            catch (NoSuchFieldError var5)
            {
                ;
            }

            try
            {
                WOOD_TYPE_LOOKUP[BlockDarkPlanks.EnumType.DARKJUNGLE.ordinal()] = 3;
            }
            catch (NoSuchFieldError var4)
            {
                ;
            }

            try
            {
                WOOD_TYPE_LOOKUP[BlockDarkPlanks.EnumType.DARKACACIA.ordinal()] = 4;
            }
            catch (NoSuchFieldError var3)
            {
                ;
            }

            try
            {
                WOOD_TYPE_LOOKUP[BlockDarkPlanks.EnumType.DARKBIG_OAK.ordinal()] = 5;
            }
            catch (NoSuchFieldError var2)
            {
                ;
            }

            try
            {
                WOOD_TYPE_LOOKUP[BlockDarkPlanks.EnumType.DARKOAK.ordinal()] = 6;
            }
            catch (NoSuchFieldError var1)
            {
                ;
            }
        }
    }

}
