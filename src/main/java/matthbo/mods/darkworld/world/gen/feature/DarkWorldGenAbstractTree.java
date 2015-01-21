package matthbo.mods.darkworld.world.gen.feature;

import matthbo.mods.darkworld.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

import java.util.Random;

public abstract class DarkWorldGenAbstractTree extends WorldGenAbstractTree {

    public DarkWorldGenAbstractTree(boolean par1){ super(par1); }

    @Override
    protected boolean func_150523_a(Block block){
        return block.getMaterial() == Material.air || block.getMaterial() == Material.leaves || block == ModBlocks.darkGrass || block == ModBlocks.darkDirt || block == ModBlocks.darkLog || block == ModBlocks.darkLog2 || block == ModBlocks.darkSapling /*|| block == Blocks.vine*/;//TODO: make custom vine
    }

    @Override
    public void func_150524_b(World world, Random rand, int x, int y, int z) {}

    @Override
    protected boolean isReplaceable(World world, int x, int y, int z){
        Block block = world.getBlock(x, y, z);
        return block.isAir(world, x, y, z) || block.isLeaves(world, x, y, z) || block.isWood(world, x, y, z) || func_150523_a(block);
    }
}
