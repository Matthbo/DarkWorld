package matthbo.mods.darkworld.world.gen.feature;

import matthbo.mods.darkworld.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class DarkWorldGenTallGrass extends WorldGenerator{

    private IBlockState field_a;
    //private int tallGrassMetadata;

    public DarkWorldGenTallGrass(){
        //super(block, par2);
        this.field_a = ModBlocks.darkTallGrass.getDefaultState();
        //this.tallGrassMetadata = par2;
    }

    public boolean generate(World world, Random rand, BlockPos pos)
    {
        Block block;

        do
        {
            block = world.getBlockState(pos).getBlock();
            if (!(block.isLeaves(world, pos) || !block.isAir(world, pos)))
            {
                break;
            }
            pos = pos.down();
        } while (pos.getY() > 0);

        //for (int l = 0; l < 128; ++l)
        for (int l = 0; l < 32; ++l)
        {
            BlockPos blockpos1 = pos.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));

            int r = rand.nextInt(2);

            if (world.isAirBlock(blockpos1) && ModBlocks.darkTallGrass.canBlockStay(world, blockpos1, this.field_a) && r == 1)
            {
                world.setBlockState(blockpos1, this.field_a, 2);
            }
        }

        return true;
    }

}
