package matthbo.mods.darkworld.world.gen.feature;

import matthbo.mods.darkworld.block.BlockDarkPlanks;
import matthbo.mods.darkworld.block.BlockDarkSapling;
import matthbo.mods.darkworld.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

import java.util.Random;

public class DarkWorldGenSavannaTree extends DarkWorldGenAbstractTree {

    public DarkWorldGenSavannaTree(boolean par1)
    {
        super(par1);
    }

    public boolean generate(World worldIn, Random rand, BlockPos pos)
    {
        int i = rand.nextInt(3) + rand.nextInt(3) + 5;
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
                Block block = worldIn.getBlockState(down).getBlock();
                boolean isSoil = block.canSustainPlant(worldIn, down, net.minecraft.util.EnumFacing.UP, ((BlockDarkSapling) ModBlocks.darkSapling));

                if (isSoil && pos.getY() < 256 - i - 1)
                {
                    block.onPlantGrow(worldIn, down, pos);
                    EnumFacing enumfacing = EnumFacing.Plane.HORIZONTAL.random(rand);
                    k = i - rand.nextInt(4) - 1;
                    l = 3 - rand.nextInt(3);
                    int i1 = pos.getX();
                    int j1 = pos.getZ();
                    int k1 = 0;
                    int i2;

                    for (int l1 = 0; l1 < i; ++l1)
                    {
                        i2 = pos.getY() + l1;

                        if (l1 >= k && l > 0)
                        {
                            i1 += enumfacing.getFrontOffsetX();
                            j1 += enumfacing.getFrontOffsetZ();
                            --l;
                        }

                        BlockPos blockpos1 = new BlockPos(i1, i2, j1);
                        block = worldIn.getBlockState(blockpos1).getBlock();

                        if (block.isAir(worldIn, blockpos1) || block.isLeaves(worldIn, blockpos1))
                        {
                            this.func_175905_a(worldIn, blockpos1, ModBlocks.darkLog2, BlockDarkPlanks.EnumType.DARKACACIA.getMetadata() - 4);
                            k1 = i2;
                        }
                    }

                    BlockPos blockpos3 = new BlockPos(i1, k1, j1);
                    int k2;

                    for (i2 = -3; i2 <= 3; ++i2)
                    {
                        for (k2 = -3; k2 <= 3; ++k2)
                        {
                            if (Math.abs(i2) != 3 || Math.abs(k2) != 3)
                            {
                                this.func_175924_b(worldIn, blockpos3.add(i2, 0, k2));
                            }
                        }
                    }

                    blockpos3 = blockpos3.up();

                    for (i2 = -1; i2 <= 1; ++i2)
                    {
                        for (k2 = -1; k2 <= 1; ++k2)
                        {
                            this.func_175924_b(worldIn, blockpos3.add(i2, 0, k2));
                        }
                    }

                    this.func_175924_b(worldIn, blockpos3.east(2));
                    this.func_175924_b(worldIn, blockpos3.west(2));
                    this.func_175924_b(worldIn, blockpos3.south(2));
                    this.func_175924_b(worldIn, blockpos3.north(2));
                    i1 = pos.getX();
                    j1 = pos.getZ();
                    EnumFacing enumfacing1 = EnumFacing.Plane.HORIZONTAL.random(rand);

                    if (enumfacing1 != enumfacing)
                    {
                        i2 = k - rand.nextInt(2) - 1;
                        k2 = 1 + rand.nextInt(3);
                        k1 = 0;
                        int j2;

                        for (int l2 = i2; l2 < i && k2 > 0; --k2)
                        {
                            if (l2 >= 1)
                            {
                                j2 = pos.getY() + l2;
                                i1 += enumfacing1.getFrontOffsetX();
                                j1 += enumfacing1.getFrontOffsetZ();
                                BlockPos blockpos2 = new BlockPos(i1, j2, j1);
                                block = worldIn.getBlockState(blockpos2).getBlock();

                                if (block.isAir(worldIn, blockpos2) || block.isLeaves(worldIn, blockpos2))
                                {
                                    this.func_175905_a(worldIn, blockpos2, ModBlocks.darkLog2, BlockDarkPlanks.EnumType.DARKACACIA.getMetadata() - 4);
                                    k1 = j2;
                                }
                            }

                            ++l2;
                        }

                        if (k1 > 0)
                        {
                            BlockPos blockpos4 = new BlockPos(i1, k1, j1);
                            int i3;

                            for (j2 = -2; j2 <= 2; ++j2)
                            {
                                for (i3 = -2; i3 <= 2; ++i3)
                                {
                                    if (Math.abs(j2) != 2 || Math.abs(i3) != 2)
                                    {
                                        this.func_175924_b(worldIn, blockpos4.add(j2, 0, i3));
                                    }
                                }
                            }

                            blockpos4 = blockpos4.up();

                            for (j2 = -1; j2 <= 1; ++j2)
                            {
                                for (i3 = -1; i3 <= 1; ++i3)
                                {
                                    this.func_175924_b(worldIn, blockpos4.add(j2, 0, i3));
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

    private void func_175924_b(World worldIn, BlockPos pos)
    {
        Block block = worldIn.getBlockState(pos).getBlock();

        if (block.isAir(worldIn, pos) || block.isLeaves(worldIn, pos))
        {
            this.func_175905_a(worldIn, pos, ModBlocks.darkLeaves, 0);
        }
    }

}
