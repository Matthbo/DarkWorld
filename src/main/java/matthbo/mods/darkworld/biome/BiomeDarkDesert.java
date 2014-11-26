package matthbo.mods.darkworld.biome;

import matthbo.mods.darkworld.init.ModBlocks;
import net.minecraft.init.Blocks;

public class BiomeDarkDesert extends DarkBiomeGenBase{

	public BiomeDarkDesert() {
		super(102); // add to configs
		this.setBiomeName("Dark Desert");
		this.setColor(16421912);
		this.setDisableRain();
		this.setTemperatureRainfall(2.0F, 0.0F);
		this.setHeight(height_LowPlains);
		
		this.spawnableCreatureList.clear();
        this.topBlock = ModBlocks.darkSand;
        this.fillerBlock = ModBlocks.darkSandStone;
        this.theBiomeDecorator.treesPerChunk = -999;
        this.theBiomeDecorator.deadBushPerChunk = 2;
        this.theBiomeDecorator.reedsPerChunk = 50;
        this.theBiomeDecorator.cactiPerChunk = 10;
        this.spawnableCreatureList.clear();
	}

}
