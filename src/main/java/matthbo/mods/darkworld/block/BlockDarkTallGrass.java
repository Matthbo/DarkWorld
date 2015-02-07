package matthbo.mods.darkworld.block;

import matthbo.mods.darkworld.init.ModItems;
import matthbo.mods.darkworld.utility.LogHelper;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;

import java.util.ArrayList;
import java.util.Random;

public class BlockDarkTallGrass extends BlockBushDarkWorld implements IGrowable, IShearable{

    public BlockDarkTallGrass(){
        super(Material.vine);
        this.setUnlocalizedName("darktallgrass");
        this.setStepSound(soundTypeGrass);

        float f = 0.4F;
        this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.8F, 0.5F + f);
    }

    public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient)
    {
        return false;
    }

    public boolean canBlockStay(World world, BlockPos pos, IBlockState state)
    {
        return super.canBlockStay(world, pos, state);
    }

    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return ModItems.darkWheat;
    }

    //DOESN'T WORK!!
    public int quantityDropped(Random rand){
        int i = rand.nextInt(3) - 2;
        if(i < 0) i = 0;
        return i;
    }

    /**
     * Returns the usual quantity dropped by the block plus a bonus of 1 to 'i' (inclusive).
     */
    public int quantityDroppedWithBonus(int par1, Random rand)
    {
        int i = rand.nextInt(3) - 1;
        if(i < 0) i = 0;
        return i;
    }

    /**
     * Called when the player destroys a block with an item that can harvest it. (i, j, k) are the coordinates of the
     * block and l is the block's subtype/damage.
     */
    public void harvestBlock(World world, EntityPlayer player, BlockPos pos, IBlockState state, TileEntity te)
    {
        super.harvestBlock(world, player, pos, state, te);
    }

    public int getDamageValue(World worldIn, BlockPos pos)
    {
        IBlockState iblockstate = worldIn.getBlockState(pos);
        return iblockstate.getBlock().getMetaFromState(iblockstate);
    }

    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state)
    {
        return true;
    }

    public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state) {}

    /*@Override
    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int meta, int fortune)
    {
        ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
        if (world.rand.nextInt(8) != 0) return ret;
        ItemStack seed = ForgeHooks.getGrassSeed(world);
        if (seed != null) ret.add(seed);
        return ret;
    }*/

    @Override public boolean isShearable(ItemStack item, IBlockAccess world, BlockPos pos){ return true; }

    @Override
    public ArrayList<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune)
    {
        ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
        ret.add(new ItemStack(this, 1, 0));
        return ret;
    }
}
