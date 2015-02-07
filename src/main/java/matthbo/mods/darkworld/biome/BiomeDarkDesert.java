package matthbo.mods.darkworld.biome;

import java.util.Random;

import matthbo.mods.darkworld.init.ModBlocks;
import matthbo.mods.darkworld.world.DarkWorldGenerator;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenDesertWells;

public class BiomeDarkDesert extends DarkBiomeGenBase{

	public BiomeDarkDesert(int id) {
		super(id); // add to configs
		this.setBiomeName("Dark Desert");
		this.setColor(16421912);
		this.setDisableRain();
		this.setTemperatureRainfall(2.0F, 0.0F);
		this.setHeight(height_LowPlains);
		
		this.spawnableCreatureList.clear();
        this.topBlock = ModBlocks.darkSand.getDefaultState();
        this.fillerBlock = ModBlocks.darkSand.getDefaultState();
        this.theDecorationHandler.treesPerChunk = -999;
        this.theBiomeDecorator.deadBushPerChunk = 2;
        this.theBiomeDecorator.reedsPerChunk = 50;
        //this.theBiomeDecorator.cactiPerChunk = 10; FIXED!
        this.spawnableCreatureList.clear();
	}

    public void decorate(World world, Random rand, BlockPos pos)
    {
        super.decorate(world, rand, pos);

        if (rand.nextInt(1000) == 0)
        {
            int i = rand.nextInt(16) + 8;
            int j = rand.nextInt(16) + 8;
            BlockPos blockpos1 = world.getHorizon(pos.add(i, 0, j)).up();
            (new WorldGenDesertWells()).generate(world, rand, blockpos1);//uh okay TODO: make DarkWorldGenDesertWells xD
        }
    }

}
