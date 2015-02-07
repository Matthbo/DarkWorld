package matthbo.mods.darkworld.world.gen.feature;

import matthbo.mods.darkworld.block.BlockDarkPlanks;
import matthbo.mods.darkworld.block.BlockDarkSapling;
import matthbo.mods.darkworld.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class DarkWorldGenTaiga1 extends DarkWorldGenAbstractTree {

    public DarkWorldGenTaiga1()
    {
        super(false);
    }

    public boolean generate(World worldIn, Random rand, BlockPos pos)
    {
        int i = rand.nextInt(5) + 7;
        int j = i - rand.nextInt(2) - 3;
        int k = i - j;
        int l = 1 + rand.nextInt(k + 1);
        boolean flag = true;

        if (pos.getY() >= 1 && pos.getY() + i + 1 <= 256)
        {
            int j1;
            int k1;
            int k2;

            for (int i1 = pos.getY(); i1 <= pos.getY() + 1 + i && flag; ++i1)
            {
                boolean flag1 = true;

                if (i1 - pos.getY() < j)
                {
                    k2 = 0;
                }
                else
                {
                    k2 = l;
                }

                for (j1 = pos.getX() - k2; j1 <= pos.getX() + k2 && flag; ++j1)
                {
                    for (k1 = pos.getZ() - k2; k1 <= pos.getZ() + k2 && flag; ++k1)
                    {
                        if (i1 >= 0 && i1 < 256)
                        {
                            if (!this.isReplaceable(worldIn, new BlockPos(j1, i1, k1)))
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
                boolean isSoil = block.canSustainPlant(worldIn, down, net.minecraft.util.EnumFacing.UP, (BlockDarkSapling)ModBlocks.darkSapling);

                if (isSoil && pos.getY() < 256 - i - 1)
                {
                    block.onPlantGrow(worldIn, down, pos);
                    k2 = 0;

                    for (j1 = pos.getY() + i; j1 >= pos.getY() + j; --j1)
                    {
                        for (k1 = pos.getX() - k2; k1 <= pos.getX() + k2; ++k1)
                        {
                            int l1 = k1 - pos.getX();

                            for (int i2 = pos.getZ() - k2; i2 <= pos.getZ() + k2; ++i2)
                            {
                                int j2 = i2 - pos.getZ();

                                if (Math.abs(l1) != k2 || Math.abs(j2) != k2 || k2 <= 0)
                                {
                                    BlockPos blockpos1 = new BlockPos(k1, j1, i2);

                                    if (worldIn.getBlockState(blockpos1).getBlock().canBeReplacedByLeaves(worldIn, blockpos1))
                                    {
                                        this.func_175905_a(worldIn, blockpos1, ModBlocks.darkLeaves, BlockDarkPlanks.EnumType.DARKSPRUCE.getMetadata());
                                    }
                                }
                            }
                        }

                        if (k2 >= 1 && j1 == pos.getY() + j + 1)
                        {
                            --k2;
                        }
                        else if (k2 < l)
                        {
                            ++k2;
                        }
                    }

                    for (j1 = 0; j1 < i - 1; ++j1)
                    {
                        BlockPos upN = pos.up(j1);
                        Block block1 = worldIn.getBlockState(upN).getBlock();

                        if (block1.isAir(worldIn, upN) || block1.isLeaves(worldIn, upN))
                        {
                            this.func_175905_a(worldIn, pos.up(j1), ModBlocks.darkLog, BlockDarkPlanks.EnumType.DARKSPRUCE.getMetadata());
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

}
