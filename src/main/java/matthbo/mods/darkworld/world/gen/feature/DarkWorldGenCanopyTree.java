package matthbo.mods.darkworld.world.gen.feature;

import matthbo.mods.darkworld.block.BlockDarkPlanks;
import matthbo.mods.darkworld.block.BlockDarkSapling;
import matthbo.mods.darkworld.init.ModBlocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

import java.util.Random;

public class DarkWorldGenCanopyTree extends DarkWorldGenAbstractTree {

    public DarkWorldGenCanopyTree(boolean par1)
    {
        super(par1);
    }

    public boolean generate(World worldIn, Random rand, BlockPos pos)
    {
        int i = rand.nextInt(3) + rand.nextInt(2) + 6;
        boolean flag = true;

        if (pos.getY() >= 1 && pos.getY() + i + 1 <= 256)
        {
            int k;
            int l;

            for (int j = pos.getY(); j <= pos.getY() + 1 + i; ++j)
            {
                byte b0 = 1;

                if (j == pos.getY())
                {
                    b0 = 0;
                }

                if (j >= pos.getY() + 1 + i - 2)
                {
                    b0 = 2;
                }

                for (k = pos.getX() - b0; k <= pos.getX() + b0 && flag; ++k)
                {
                    for (l = pos.getZ() - b0; l <= pos.getZ() + b0 && flag; ++l)
                    {
                        if (j >= 0 && j < 256)
                        {
                            if (!this.isReplaceable(worldIn, new BlockPos(k, j, l)))
                            {
                                flag = false;
                            }
                        }
                        else
                        {
                            flag = false;
                        }
                    }
                }
            }

            if (!flag)
            {
                return false;
            }
            else
            {
                BlockPos down = pos.down();
                net.minecraft.block.state.IBlockState state = worldIn.getBlockState(down);
                boolean isSoil = state.getBlock().canSustainPlant(worldIn, down, net.minecraft.util.EnumFacing.UP, ((BlockDarkSapling)ModBlocks.darkSapling));

                if (isSoil && pos.getY() < 256 - i - 1)
                {
                    this.onPlantGrow(worldIn, pos.down(), pos);
                    this.onPlantGrow(worldIn, pos.add(1, -1, 0), pos);
                    this.onPlantGrow(worldIn, pos.add(1, -1, 1), pos);
                    this.onPlantGrow(worldIn, pos.add(0, -1, 1), pos);
                    EnumFacing enumfacing = EnumFacing.Plane.HORIZONTAL.random(rand);
                    k = i - rand.nextInt(4);
                    l = 2 - rand.nextInt(3);
                    int i1 = pos.getX();
                    int j1 = pos.getZ();
                    int k1 = 0;
                    int l1;
                    int i2;

                    for (l1 = 0; l1 < i; ++l1)
                    {
                        i2 = pos.getY() + l1;

                        if (l1 >= k && l > 0)
                        {
                            i1 += enumfacing.getFrontOffsetX();
                            j1 += enumfacing.getFrontOffsetZ();
                            --l;
                        }

                        BlockPos blockpos1 = new BlockPos(i1, i2, j1);
                        state = worldIn.getBlockState(blockpos1);

                        if (state.getBlock().isAir(worldIn, blockpos1) || state.getBlock().isLeaves(worldIn, blockpos1))
                        {
                            this.func_175905_a(worldIn, blockpos1, ModBlocks.darkLog2, BlockDarkPlanks.EnumType.DARKBIG_OAK.getMetadata() - 4);
                            this.func_175905_a(worldIn, blockpos1.east(), ModBlocks.darkLog2, BlockDarkPlanks.EnumType.DARKBIG_OAK.getMetadata() - 4);
                            this.func_175905_a(worldIn, blockpos1.south(), ModBlocks.darkLog2, BlockDarkPlanks.EnumType.DARKBIG_OAK.getMetadata() - 4);
                            this.func_175905_a(worldIn, blockpos1.east().south(), ModBlocks.darkLog2, BlockDarkPlanks.EnumType.DARKBIG_OAK.getMetadata() - 4);
                            k1 = i2;
                        }
                    }

                    for (l1 = -2; l1 <= 0; ++l1)
                    {
                        for (i2 = -2; i2 <= 0; ++i2)
                        {
                            byte b1 = -1;
                            this.func_150526_a(worldIn, i1 + l1, k1 + b1, j1 + i2);
                            this.func_150526_a(worldIn, 1 + i1 - l1, k1 + b1, j1 + i2);
                            this.func_150526_a(worldIn, i1 + l1, k1 + b1, 1 + j1 - i2);
                            this.func_150526_a(worldIn, 1 + i1 - l1, k1 + b1, 1 + j1 - i2);

                            if ((l1 > -2 || i2 > -1) && (l1 != -1 || i2 != -2))
                            {
                                byte b2 = 1;
                                this.func_150526_a(worldIn, i1 + l1, k1 + b2, j1 + i2);
                                this.func_150526_a(worldIn, 1 + i1 - l1, k1 + b2, j1 + i2);
                                this.func_150526_a(worldIn, i1 + l1, k1 + b2, 1 + j1 - i2);
                                this.func_150526_a(worldIn, 1 + i1 - l1, k1 + b2, 1 + j1 - i2);
                            }
                        }
                    }

                    if (rand.nextBoolean())
                    {
                        this.func_150526_a(worldIn, i1, k1 + 2, j1);
                        this.func_150526_a(worldIn, i1 + 1, k1 + 2, j1);
                        this.func_150526_a(worldIn, i1 + 1, k1 + 2, j1 + 1);
                        this.func_150526_a(worldIn, i1, k1 + 2, j1 + 1);
                    }

                    for (l1 = -3; l1 <= 4; ++l1)
                    {
                        for (i2 = -3; i2 <= 4; ++i2)
                        {
                            if ((l1 != -3 || i2 != -3) && (l1 != -3 || i2 != 4) && (l1 != 4 || i2 != -3) && (l1 != 4 || i2 != 4) && (Math.abs(l1) < 3 || Math.abs(i2) < 3))
                            {
                                this.func_150526_a(worldIn, i1 + l1, k1, j1 + i2);
                            }
                        }
                    }

                    for (l1 = -1; l1 <= 2; ++l1)
                    {
                        for (i2 = -1; i2 <= 2; ++i2)
                        {
                            if ((l1 < 0 || l1 > 1 || i2 < 0 || i2 > 1) && rand.nextInt(3) <= 0)
                            {
                                int k2 = rand.nextInt(3) + 2;
                                int l2;

                                for (l2 = 0; l2 < k2; ++l2)
                                {
                                    this.func_175905_a(worldIn, new BlockPos(pos.getX() + l1, k1 - l2 - 1, pos.getZ() + i2), ModBlocks.darkLog2, BlockDarkPlanks.EnumType.DARKBIG_OAK.getMetadata() - 4);
                                }

                                int j2;

                                for (l2 = -1; l2 <= 1; ++l2)
                                {
                                    for (j2 = -1; j2 <= 1; ++j2)
                                    {
                                        this.func_150526_a(worldIn, i1 + l1 + l2, k1 - 0, j1 + i2 + j2);
                                    }
                                }

                                for (l2 = -2; l2 <= 2; ++l2)
                                {
                                    for (j2 = -2; j2 <= 2; ++j2)
                                    {
                                        if (Math.abs(l2) != 2 || Math.abs(j2) != 2)
                                        {
                                            this.func_150526_a(worldIn, i1 + l1 + l2, k1 - 1, j1 + i2 + j2);
                                        }
                                    }
                                }
                            }
                        }
                    }

                    return true;
                }
                else
                {
                    return false;
                }
            }
        }
        else
        {
            return false;
        }
    }

    private void func_150526_a(World worldIn, int x, int y, int z)
    {
        BlockPos pos = new BlockPos(x, y, z);
        net.minecraft.block.state.IBlockState state = worldIn.getBlockState(pos);

        if (state.getBlock().isAir(worldIn, pos))
        {
            this.func_175905_a(worldIn, new BlockPos(x, y, z), ModBlocks.darkLeaves, 1);
        }
    }

    //Just a helper macro
    private void onPlantGrow(World world, BlockPos pos, BlockPos source)
    {
        world.getBlockState(pos).getBlock().onPlantGrow(world, pos, source);
    }

}
