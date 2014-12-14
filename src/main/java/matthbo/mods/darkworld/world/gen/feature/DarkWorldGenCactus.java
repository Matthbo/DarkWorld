package matthbo.mods.darkworld.world.gen.feature;

import java.util.Random;

import matthbo.mods.darkworld.init.ModBlocks;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenCactus;

public class DarkWorldGenCactus extends WorldGenCactus{

	public boolean generate(World world, Random rand, int x, int y, int z)
    {
        for (int l = 0; l < 10; ++l)
        {
            int i1 = x + rand.nextInt(8) - rand.nextInt(8);
            int j1 = y + rand.nextInt(4) - rand.nextInt(4);
            int k1 = z + rand.nextInt(8) - rand.nextInt(8);

            if (world.isAirBlock(i1, j1, k1))
            {
                int l1 = 1 + rand.nextInt(rand.nextInt(3) + 1);

                for (int i2 = 0; i2 < l1; ++i2)
                {
                    if (ModBlocks.darkCactus.canBlockStay(world, i1, j1 + i2, k1))
                    {
                        world.setBlock(i1, j1 + i2, k1, ModBlocks.darkCactus, 0, 2);
                    }
                }
            }
        }

        return true;
    }
	
}
