package matthbo.mods.darkworld.world;

import java.util.Random;

import matthbo.mods.darkworld.init.ModBlocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;

public class OverworldGenerator implements IWorldGenerator {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		
		switch(world.provider.getDimensionId()){
		case -1:
			generateNether(world, random, chunkX * 16, chunkZ * 16);
			break;
		case 0:
			generateSurface(world, random, chunkX * 16, chunkZ * 16);
			break;
		case 1:
			generateEnd(world, random, chunkX * 16, chunkZ * 16);
			break;
		}
		
	}

	private void generateEnd(World world, Random rand, int chunkX, int chunkZ) {}

	private void generateSurface(World world, Random rand, int chunkX, int chunkZ) {
		
		for(int k = 0; k < 2; k++){
			int peculiarStoneXCoords = chunkX + rand.nextInt(16);
			int peculiarStoneYCoords = rand.nextInt(20);
			int peculiarStoneZCoords = chunkZ + rand.nextInt(16);
			
			(new WorldGenMinable(ModBlocks.peculiarStone.getDefaultState(), 10)).generate(world, rand, new BlockPos(peculiarStoneXCoords, peculiarStoneYCoords, peculiarStoneZCoords));
		}
		
	}

	private void generateNether(World world, Random rand, int chunkX, int chunkZ) {}

}
