package matthbo.mods.darkworld.handler;

import java.util.Random;

import matthbo.mods.darkworld.init.ModBlocks;
import matthbo.mods.darkworld.world.gen.feature.DarkWorldGenTallGrass;
import org.apache.logging.log4j.core.net.Priority;

import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import matthbo.mods.darkworld.init.ModBiomes;
import matthbo.mods.darkworld.reference.EnumDarkWorld;
import matthbo.mods.darkworld.utility.LogHelper;
import matthbo.mods.darkworld.world.gen.feature.DarkWorldGenCactus;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType;

public class BiomeDecoratorHandler {
	public BiomeDecorator theBiomeDecorator;
	
	public int cactiPerChunk = 10;
	public int grassPerChunk = 1;

	public WorldGenerator cactusGen;// = new DarkWorldGenCactus();
	
	public Random randomGenerator;
	
	public BiomeDecoratorHandler() {
		this.cactusGen = new DarkWorldGenCactus();
		this.randomGenerator = new Random();
	}
	
	@SubscribeEvent(priority = EventPriority.LOWEST)
	public void onWorldDecoration(DecorateBiomeEvent.Decorate event){
		BiomeGenBase biome = event.world.getWorldChunkManager().getBiomeGenAt(event.chunkX, event.chunkZ);
		if(event.getResult() == Result.ALLOW || event.getResult() == Result.DEFAULT){
			if(event.type == EventType.GRASS){
				genGrass(event.world, event.chunkX, event.chunkZ, randomGenerator);
			}
			else if(event.type == EventType.CACTUS && biome.isEqualTo(ModBiomes.darkDesert)) {
				genCacti(event.world, event.chunkX, event.chunkZ, randomGenerator);
			}
		}
	}
	
	protected void genCacti(World world, int chunkX, int chunkZ, Random rand){
		for (int j = 0; j < this.cactiPerChunk; ++j)
        {
            int k = chunkX + rand.nextInt(16) + 8;
            int l = chunkZ + rand.nextInt(16) + 8;
            int i1 = nextInt(world.getHeightValue(k, l) * 2);
            this.cactusGen.generate(world, randomGenerator, k, i1, l);
        }
	}

	protected void genGrass(World world, int chunkX, int chunkZ, Random rand){
		for (int j = 0; j < this.grassPerChunk; ++j)
		{
			int k = chunkX + rand.nextInt(16) + 8;
			int l = chunkZ + rand.nextInt(16) + 8;
			int i1 = nextInt(world.getHeightValue(k, l) * 2);
			(new DarkWorldGenTallGrass(ModBlocks.darkTallGrass, 0)).generate(world, randomGenerator, k, i1, l);
		}
	}
	
	private int nextInt(int i) {
		Random rand = new Random();
        if (i <= 1)
            return 0;
        return rand.nextInt(i);
	}

}
