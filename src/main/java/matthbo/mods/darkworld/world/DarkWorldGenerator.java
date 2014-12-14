package matthbo.mods.darkworld.world;

import java.util.Random;

import matthbo.mods.darkworld.block.BlockDarkWorld;
import matthbo.mods.darkworld.handler.ConfigHandler;
import matthbo.mods.darkworld.init.ModBlocks;
import matthbo.mods.darkworld.init.ModDimensions;
import matthbo.mods.darkworld.utility.LogHelper;
import matthbo.mods.darkworld.world.gen.feature.DarkWorldGenCactus;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import cpw.mods.fml.common.IWorldGenerator;

public class DarkWorldGenerator implements IWorldGenerator {

	public final int DWID = ModDimensions.dimensionIDDarkWorld;
	
	public Random randomGenerator;
	
	public WorldGenerator cactusGen;
	
	public int cactiPerChunk;
	
	public DarkWorldGenerator() {
		LogHelper.info("DWGen LOADING!!!!! <--------------");
		cactusGen = new DarkWorldGenCactus();
	}
	
	@Override
	public void generate(Random rand, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		
		chunkX *= 16;
		chunkZ *= 16;
		
		if(world.provider.dimensionId == DWID){
			genStandardOre1(world, ModBlocks.darkDirt, 20, 32, chunkX, 0, 256, chunkZ, rand);
			genStandardOre1(world, ModBlocks.darkGravel, 10, 32, chunkX, 0, 256, chunkZ, rand);
			
			genStandardOre1(world, ModBlocks.darkCoalOre, 20, 16, chunkX, 0, 128, chunkZ, rand);
			genStandardOre1(world, ModBlocks.darkIronOre, 20, 8, chunkX, 0, 64, chunkZ, rand);
			genStandardOre1(world, ModBlocks.darkGoldOre, 2, 8, chunkX, 0, 32, chunkZ, rand);
			genStandardOre1(world, ModBlocks.darkRedstoneOre, 8, 7, chunkX, 0, 16, chunkZ, rand);
			genStandardOre1(world, ModBlocks.darkDiamondOre, 1, 7, chunkX, 0, 16, chunkZ, rand);
			genStandardOre2(world, ModBlocks.darkLapisOre, 1, 6, chunkX, 16, 16, chunkZ, rand);
			
			genCacti(world, chunkX, chunkZ, rand);
		}
			
		
	}
	
	protected void genStandardOre1(World world, Block block,int veins, int BPV, int chunkX, int chunkYSpecial, int chunkY, int chunkZ, Random rand)
    {
        for (int l = 0; l < veins; ++l)
        {
            int x = chunkX + rand.nextInt(16);
            int y = rand.nextInt(chunkY - chunkYSpecial) + chunkYSpecial;
            int z = chunkZ + rand.nextInt(16);
           
            (new WorldGenMinable(block, BPV, ModBlocks.darkStone)).generate(world, rand, x, y, z);
        }
    }
	
	protected void genStandardOre2(World world, Block block,int veins, int BPV, int chunkX, int chunkYSpecial, int chunkY, int chunkZ, Random rand)
    {
        for (int l = 0; l < veins; ++l)
        {
            int x = chunkX + rand.nextInt(16);
            int y = rand.nextInt(chunkY) + rand.nextInt(chunkY) + (chunkYSpecial - chunkY);
            int z = chunkZ + rand.nextInt(16);
           
            (new WorldGenMinable(block, BPV, ModBlocks.darkStone)).generate(world, rand, x, y, z);
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
	
	private int nextInt(int i) {
		Random rand = new Random();
        if (i <= 1)
            return 0;
        return rand.nextInt(i);
	}

}