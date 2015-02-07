package matthbo.mods.darkworld.world.gen.feature;

import java.util.Random;

import matthbo.mods.darkworld.init.ModBlocks;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenCactus;

public class DarkWorldGenCactus extends WorldGenCactus{

	public boolean generate(World world, Random rand, BlockPos pos)
    {
        for (int i = 0; i < 10; ++i)
        {
            BlockPos blockpos1 = pos.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));

            if (world.isAirBlock(blockpos1))
            {
                int j = 1 + rand.nextInt(rand.nextInt(3) + 1);

                for (int k = 0; k < j; ++k)
                {
                    if (ModBlocks.darkCactus.canBlockStay(world, blockpos1))
                    {
                        world.setBlockState(blockpos1.up(k), ModBlocks.darkCactus.getDefaultState(), 2);
                    }
                }
            }
        }

        return true;
    }
	
}
