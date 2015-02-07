package matthbo.mods.darkworld.world.gen.feature;

import matthbo.mods.darkworld.block.BlockDarkPlanks;
import matthbo.mods.darkworld.block.BlockDarkSapling;
import matthbo.mods.darkworld.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class DarkWorldGenForest extends DarkWorldGenAbstractTree{

    private boolean field_150531_a;

    public DarkWorldGenForest(boolean par1, boolean par2)
    {
        super(par1);
        this.field_150531_a = par2;
    }

    public boolean generate(World worldIn, Random rand, BlockPos pos)
    {
        int i = rand.nextInt(3) + 5;

        if (this.field_150531_a)
        {
            i += rand.nextInt(7);
        }

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
                Block block1 = worldIn.getBlockState(down).getBlock();
                boolean isSoil = block1.canSustainPlant(worldIn, down, net.minecraft.util.EnumFacing.UP, ((BlockDarkSapling)ModBlocks.darkSapling));

                if (isSoil && pos.getY() < 256 - i - 1)
                {
                    block1.onPlantGrow(worldIn, down, pos);
                    int i2;

                    for (i2 = pos.getY() - 3 + i; i2 <= pos.getY() + i; ++i2)
                    {
                        k = i2 - (pos.getY() + i);
                        l = 1 - k / 2;

                        for (int i1 = pos.getX() - l; i1 <= pos.getX() + l; ++i1)
                        {
                            int j1 = i1 - pos.getX();

                            for (int k1 = pos.getZ() - l; k1 <= pos.getZ() + l; ++k1)
                            {
                                int l1 = k1 - pos.getZ();

                                if (Math.abs(j1) != l || Math.abs(l1) != l || rand.nextInt(2) != 0 && k != 0)
                                {
                                    BlockPos blockpos1 = new BlockPos(i1, i2, k1);
                                    Block block = worldIn.getBlockState(blockpos1).getBlock();

                                    if (block.isAir(worldIn, blockpos1) || block.isLeaves(worldIn, blockpos1))
                                    {
                                        this.func_175905_a(worldIn, blockpos1, ModBlocks.darkLeaves, BlockDarkPlanks.EnumType.DARKBIRCH.getMetadata());
                                    }
                                }
                            }
                        }
                    }

                    for (i2 = 0; i2 < i; ++i2)
                    {
                        BlockPos upN = pos.up(i2);
                        Block block2 = worldIn.getBlockState(upN).getBlock();

                        if (block2.isAir(worldIn, upN) || block2.isLeaves(worldIn, upN))
                        {
                            this.func_175905_a(worldIn, pos.up(i2), ModBlocks.darkLog, BlockDarkPlanks.EnumType.DARKBIRCH.getMetadata());
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
