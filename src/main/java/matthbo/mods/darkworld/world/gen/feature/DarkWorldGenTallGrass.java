package matthbo.mods.darkworld.world.gen.feature;

import net.minecraft.block.Block;
import net.minecraft.world.World;

import java.util.Random;

public class DarkWorldGenTallGrass {

    private Block field_a;
    private int tallGrassMetadata;

    public DarkWorldGenTallGrass(Block block, int par2){
        //super(block, par2);
        this.field_a = block;
        this.tallGrassMetadata = par2;
    }

    public boolean generate(World world, Random rand, int x, int y, int z)
    {
        Block block;

        do
        {
            block = world.getBlock(x, y, z);
            if (!(block.isLeaves(world, x, y, z) || block.isAir(world, x, y, z)))
            {
                break;
            }
            --y;
        } while (y > 0);

        //for (int l = 0; l < 128; ++l)
        for (int l = 0; l < 32; ++l)
        {
            int i1 = x + rand.nextInt(8) - rand.nextInt(8);
            int j1 = y + rand.nextInt(4) - rand.nextInt(4);
            int k1 = z + rand.nextInt(8) - rand.nextInt(8);

            int r = rand.nextInt(2);

            if (world.isAirBlock(i1, j1, k1) && this.field_a.canBlockStay(world, i1, j1, k1) && r == 1)
            {
                world.setBlock(i1, j1, k1, this.field_a, this.tallGrassMetadata, 2);
            }
        }

        return true;
    }

}
