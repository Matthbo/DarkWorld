package matthbo.mods.darkworld.biome;

import java.awt.Color;
import java.util.Random;

import matthbo.mods.darkworld.DarkWorld;
import matthbo.mods.darkworld.block.BlockDarkSand;
import matthbo.mods.darkworld.handler.BiomeDecoratorHandler;
import matthbo.mods.darkworld.init.ModBlocks;
import matthbo.mods.darkworld.init.ModFluids;
import matthbo.mods.darkworld.world.DarkWorldGenerator;
import matthbo.mods.darkworld.world.gen.feature.DarkWorldGenAbstractTree;
import matthbo.mods.darkworld.world.gen.feature.DarkWorldGenBigTree;
import matthbo.mods.darkworld.world.gen.feature.DarkWorldGenSwamp;
import matthbo.mods.darkworld.world.gen.feature.DarkWorldGenTrees;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public class DarkBiomeGenBase extends BiomeGenBase{


    public static final DarkBiomeGenBase.Height height_MidHills = BiomeGenBase.height_MidHills;
    public static final DarkBiomeGenBase.Height height_MidPlains = BiomeGenBase.height_MidPlains;

    protected DarkWorldGenTrees darkWorldGenTrees;
    protected DarkWorldGenBigTree darkWorldGenBigTree;
    protected DarkWorldGenSwamp darkWorldGenSwamp;

    public BiomeDecoratorHandler theDecorationHandler;

	public DarkBiomeGenBase(int id) {
		super(id);
		
		this.topBlock = ModBlocks.darkGrass.getDefaultState();
		this.fillerBlock = ModBlocks.darkDirt.getDefaultState();

        this.theDecorationHandler = new BiomeDecoratorHandler();

        this.darkWorldGenTrees = new DarkWorldGenTrees(false);
        this.darkWorldGenBigTree = new DarkWorldGenBigTree(false);
        this.darkWorldGenSwamp = new DarkWorldGenSwamp();
	}
	
	@Override
	public int getSkyColorByTemp(float p_76731_1_) {
		return Color.BLACK.getRGB();
	}

    public void genTerrainBlocks(World world, Random rand, ChunkPrimer chunkPrimer, int x, int z, double par6)
    {
        this.genBiomeTerrainDarkWorld(world, rand, chunkPrimer, x, z, par6);
    }

    public DarkWorldGenAbstractTree genBigTreeChance(Random p_150567_1_)
    {
        return (DarkWorldGenAbstractTree)(p_150567_1_.nextInt(10) == 0 ? this.darkWorldGenBigTree : this.darkWorldGenTrees);
    }

    public void genBiomeTerrainDarkWorld(World world, Random rand, ChunkPrimer chunkPrimer, int x, int z, double par6)
    {
        boolean flag = true;
        IBlockState iblockstate = this.topBlock;
        IBlockState iblockstate1 = this.fillerBlock;
        int k = -1;
        int l = (int)(par6 / 3.0D + 3.0D + rand.nextDouble() * 0.25D);
        int i1 = x & 15;
        int j1 = z & 15;

        for (int k1 = 255; k1 >= 0; --k1)
        {
            if (k1 <= rand.nextInt(5))
            {
                chunkPrimer.setBlockState(j1, k1, i1, Blocks.bedrock.getDefaultState());
            }
            else
            {
                IBlockState iblockstate2 = chunkPrimer.getBlockState(j1, k1, i1);

                if (iblockstate2.getBlock().getMaterial() == Material.air)
                {
                    k = -1;
                }
                else if (iblockstate2.getBlock() == ModBlocks.darkStone)
                {
                    if (k == -1)
                    {
                        if (l <= 0)
                        {
                            iblockstate = null;
                            iblockstate1 = ModBlocks.darkStone.getDefaultState();
                        }
                        else if (k1 >= 59 && k1 <= 64)
                        {
                            iblockstate = this.topBlock;
                            iblockstate1 = this.fillerBlock;
                        }

                        if (k1 < 63 && (iblockstate == null || iblockstate.getBlock().getMaterial() == Material.air))
                        {
                            if (this.getFloatTemperature(new BlockPos(x, k1, z)) < 0.15F)
                            {
                                iblockstate = Blocks.ice.getDefaultState();
                            }
                            else
                            {
                                iblockstate = ModFluids.darkWaterBlock.getDefaultState();
                            }
                        }

                        k = l;

                        if (k1 >= 62)
                        {
                            chunkPrimer.setBlockState(j1, k1, i1, iblockstate);
                        }
                        else if (k1 < 56 - l)
                        {
                            iblockstate = null;
                            iblockstate1 = ModBlocks.darkStone.getDefaultState();
                            chunkPrimer.setBlockState(j1, k1, i1, ModBlocks.darkGravel.getDefaultState());
                        }
                        else
                        {
                            chunkPrimer.setBlockState(j1, k1, i1, iblockstate1);
                        }
                    }
                    else if (k > 0)
                    {
                        --k;
                        chunkPrimer.setBlockState(j1, k1, i1, iblockstate1);

                        if (k == 0 && iblockstate1.getBlock() == ModBlocks.darkSand)
                        {
                            k = rand.nextInt(4) + Math.max(0, k1 - 63);
                            iblockstate1 = iblockstate1.getValue(BlockDarkSand.VARIANT) == BlockDarkSand.EnumType.DARKRED_SAND ? ModBlocks.darkSandStone.getDefaultState() : ModBlocks.darkSandStone.getDefaultState();//TODO: make dark red sandstone
                        }
                    }
                }
            }
        }
    }

}
