package matthbo.mods.darkworld.world.gen.feature;

import matthbo.mods.darkworld.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
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
    public void func_180711_a(World world, Random rand, BlockPos pos) {}

    protected void func_175921_a(World worldIn, BlockPos pos)
    {
        if (worldIn.getBlockState(pos).getBlock() != ModBlocks.darkDirt)
        {
            this.func_175903_a(worldIn, pos, ModBlocks.darkDirt.getDefaultState());
        }
    }

    @Override
    public boolean isReplaceable(World world, BlockPos pos){
        IBlockState state = world.getBlockState(pos);
        return state.getBlock().isAir(world, pos) || state.getBlock().isLeaves(world, pos) || state.getBlock().isWood(world, pos) || func_150523_a(state.getBlock());
    }
}
