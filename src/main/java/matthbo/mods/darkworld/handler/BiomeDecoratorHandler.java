package matthbo.mods.darkworld.handler;

import java.util.Random;

import matthbo.mods.darkworld.biome.DarkBiomeGenBase;
import matthbo.mods.darkworld.init.ModBlocks;
import matthbo.mods.darkworld.world.gen.feature.DarkWorldGenAbstractTree;
import matthbo.mods.darkworld.world.gen.feature.DarkWorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
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

	public DarkBiomeGenBase darkBiomeGenBase;
	
	public int cactiPerChunk = 10;
	public int grassPerChunk = 1;
	public int treesPerChunk = 10;

	public WorldGenerator cactusGen;// = new DarkWorldGenCactus();
	
	public Random randomGenerator;
	
	public BiomeDecoratorHandler() {
		this.cactusGen = new DarkWorldGenCactus();
		this.randomGenerator = new Random();
	}
	
	@SubscribeEvent(priority = EventPriority.LOWEST)
	public void onWorldDecoration(DecorateBiomeEvent.Decorate event){
		BiomeGenBase biome = event.world.getWorldChunkManager().getBiomeGenAt(event.chunkX, event.chunkZ);
		DarkBiomeGenBase darkBiomeGen = DarkBiomeGenBase.darkBiomeGenBase;
		if(event.getResult() == Result.ALLOW || event.getResult() == Result.DEFAULT){
			if(event.type == EventType.GRASS){
				genGrass(event.world, event.chunkX, event.chunkZ, randomGenerator);
			}
			else if(event.type == EventType.CACTUS && biome.isEqualTo(ModBiomes.darkDesert)) {
				genCacti(event.world, event.chunkX, event.chunkZ, randomGenerator);
			}
			else if(event.type == EventType.TREE){
				genTrees(event.world, event.chunkX, event.chunkZ, randomGenerator, biome);
			}
		}
	}
	
	protected void genCacti(World world, int chunkX, int chunkZ, Random rand){
		for (int j = 0; j < this.cactiPerChunk; ++j)
        {
            int k = chunkX + rand.nextInt(16) + 8;
            int l = chunkZ + rand.nextInt(16) + 8;
            int i = nextInt(world.getHeightValue(k, l) * 2);
            this.cactusGen.generate(world, randomGenerator, k, i, l);
        }
	}

	protected void genGrass(World world, int chunkX, int chunkZ, Random rand){
		for (int j = 0; j < this.grassPerChunk; ++j)
		{
			int k = chunkX + rand.nextInt(16) + 8;
			int l = chunkZ + rand.nextInt(16) + 8;
			int i = nextInt(world.getHeightValue(k, l) * 2);
			(new DarkWorldGenTallGrass(ModBlocks.darkTallGrass, 0)).generate(world, randomGenerator, k, i, l);
		}
	}

	protected void genTrees(World world, int chunkX, int chunkZ, Random rand, BiomeGenBase biome){
		for (int j = 0; j < this.treesPerChunk; ++j){
			int k = chunkX + rand.nextInt(16) + 8;
			int l = chunkZ + rand.nextInt(16) + 8;
			int i = nextInt(world.getHeightValue(k, l) * 2);
			WorldGenAbstractTree darkworldgenabstracttree = biome.func_150567_a(this.randomGenerator);
			darkworldgenabstracttree.setScale(1.0D, 1.0D, 1.0D);

			if(darkworldgenabstracttree.generate(world, this.randomGenerator, k, i, l)){
				darkworldgenabstracttree.func_150524_b(world, this.randomGenerator, k, i, l);
			}
		}
	}
	
	private int nextInt(int i) {
		Random rand = new Random();
        if (i <= 1)
            return 0;
        return rand.nextInt(i);
	}

}
