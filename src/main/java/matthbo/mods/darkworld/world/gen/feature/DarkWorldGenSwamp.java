package matthbo.mods.darkworld.world.gen.feature;

import matthbo.mods.darkworld.block.BlockDarkSapling;
import matthbo.mods.darkworld.init.ModBlocks;
import matthbo.mods.darkworld.init.ModFluids;
import net.minecraft.block.Block;
import net.minecraft.block.BlockVine;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class DarkWorldGenSwamp extends DarkWorldGenAbstractTree {

    public DarkWorldGenSwamp()
    {
        super(false);
    }

    public boolean generate(World worldIn, Random rand, BlockPos pos)
    {
        int i;

        for (i = rand.nextInt(4) + 5; worldIn.getBlockState(pos.down()).getBlock().getMaterial() == Material.water; pos = pos.down())
        {
            ;
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
                    b0 = 3;
                }

                for (k = pos.getX() - b0; k <= pos.getX() + b0 && flag; ++k)
                {
                    for (l = pos.getZ() - b0; l <= pos.getZ() + b0 && flag; ++l)
                    {
                        if (j >= 0 && j < 256)
                        {
                            BlockPos pos1 = new BlockPos(k, j, l);
                            Block block = worldIn.getBlockState(pos1).getBlock();

                            if (!block.isAir(worldIn, pos1) && !block.isLeaves(worldIn, pos1))
                            {
                                if (block != ModFluids.darkWaterBlock)
                                {
                                    flag = false;
                                }
                                else if (j > pos1.getY())
                                {
                                    flag = false;
                                }
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
                boolean isSoil = block1.canSustainPlant(worldIn, down, net.minecraft.util.EnumFacing.UP, ((BlockDarkSapling) ModBlocks.darkSapling));

                if (isSoil && pos.getY() < 256 - i - 1)
                {
                    block1.onPlantGrow(worldIn, down, pos);
                    int i1;
                    BlockPos blockpos1;
                    int l1;
                    int i2;

                    for (l1 = pos.getY() - 3 + i; l1 <= pos.getY() + i; ++l1)
                    {
                        k = l1 - (pos.getY() + i);
                        l = 2 - k / 2;

                        for (i2 = pos.getX() - l; i2 <= pos.getX() + l; ++i2)
                        {
                            i1 = i2 - pos.getX();

                            for (int j1 = pos.getZ() - l; j1 <= pos.getZ() + l; ++j1)
                            {
                                int k1 = j1 - pos.getZ();

                                if (Math.abs(i1) != l || Math.abs(k1) != l || rand.nextInt(2) != 0 && k != 0)
                                {
                                    blockpos1 = new BlockPos(i2, l1, j1);

                                    if (worldIn.getBlockState(blockpos1).getBlock().canBeReplacedByLeaves(worldIn, blockpos1))
                                    {
                                        this.func_175906_a(worldIn, blockpos1, ModBlocks.darkLeaves);
                                    }
                                }
                            }
                        }
                    }

                    for (l1 = 0; l1 < i; ++l1)
                    {
                        BlockPos upN = pos.up(l1);
                        Block block2 = worldIn.getBlockState(upN).getBlock();

                        if (block2.isAir(worldIn, upN) || block2.isLeaves(worldIn, upN) || block2 == ModFluids.darkWaterBlock)
                        {
                            this.func_175906_a(worldIn, pos.up(l1), ModBlocks.darkLog);
                        }
                    }

                    for (l1 = pos.getY() - 3 + i; l1 <= pos.getY() + i; ++l1)
                    {
                        k = l1 - (pos.getY() + i);
                        l = 2 - k / 2;

                        for (i2 = pos.getX() - l; i2 <= pos.getX() + l; ++i2)
                        {
                            for (i1 = pos.getZ() - l; i1 <= pos.getZ() + l; ++i1)
                            {
                                BlockPos blockpos4 = new BlockPos(i2, l1, i1);

                                if (worldIn.getBlockState(blockpos4).getBlock().isLeaves(worldIn, blockpos4))
                                {
                                    BlockPos blockpos5 = blockpos4.west();
                                    blockpos1 = blockpos4.east();
                                    BlockPos blockpos2 = blockpos4.north();
                                    BlockPos blockpos3 = blockpos4.south();

                                    if (rand.nextInt(4) == 0 && worldIn.getBlockState(blockpos5).getBlock().isAir(worldIn, blockpos5))
                                    {
                                        this.func_175922_a(worldIn, blockpos5, BlockVine.EAST_FLAG);
                                    }

                                    if (rand.nextInt(4) == 0 && worldIn.getBlockState(blockpos1).getBlock().isAir(worldIn, blockpos1))
                                    {
                                        this.func_175922_a(worldIn, blockpos1, BlockVine.WEST_FLAG);
                                    }

                                    if (rand.nextInt(4) == 0 && worldIn.getBlockState(blockpos2).getBlock().isAir(worldIn, blockpos2))
                                    {
                                        this.func_175922_a(worldIn, blockpos2, BlockVine.SOUTH_FLAG);
                                    }

                                    if (rand.nextInt(4) == 0 && worldIn.getBlockState(blockpos3).getBlock().isAir(worldIn, blockpos3))
                                    {
                                        this.func_175922_a(worldIn, blockpos3, BlockVine.NORTH_FLAG);
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

    private void func_175922_a(World worldIn, BlockPos pos, int par3)
    {
        this.func_175905_a(worldIn, pos, Blocks.vine, par3);
        int j = 4;

        for (pos = pos.down(); worldIn.getBlockState(pos).getBlock().isAir(worldIn, pos) && j > 0; --j)
        {
            this.func_175905_a(worldIn, pos, Blocks.vine, par3);
            pos = pos.down();
        }
    }

}
