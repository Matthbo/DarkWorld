package matthbo.mods.darkworld.biome;

import java.util.Random;

import matthbo.mods.darkworld.init.ModBlocks;
import matthbo.mods.darkworld.world.DarkWorldGenerator;
import net.minecraft.init.Blocks;
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
        this.topBlock = ModBlocks.darkSand;
        this.fillerBlock = ModBlocks.darkSand;
        this.theBiomeDecorator.treesPerChunk = -999;
        this.theBiomeDecorator.deadBushPerChunk = 2;
        this.theBiomeDecorator.reedsPerChunk = 50;
        this.theBiomeDecorator.cactiPerChunk = 10;
        this.theDWGen.cactiPerChunk = 10;
        this.spawnableCreatureList.clear();
	}
	
	public void decorate(World p_76728_1_, Random p_76728_2_, int p_76728_3_, int p_76728_4_)
    {
        super.decorate(p_76728_1_, p_76728_2_, p_76728_3_, p_76728_4_);

        if (p_76728_2_.nextInt(1000) == 0)
        {
            int k = p_76728_3_ + p_76728_2_.nextInt(16) + 8;
            int l = p_76728_4_ + p_76728_2_.nextInt(16) + 8;
            WorldGenDesertWells worldgendesertwells = new WorldGenDesertWells();
            worldgendesertwells.generate(p_76728_1_, p_76728_2_, k, p_76728_1_.getHeightValue(k, l) + 1, l);
        }
    }

}
