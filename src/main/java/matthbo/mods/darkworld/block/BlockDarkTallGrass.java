package matthbo.mods.darkworld.block;

import matthbo.mods.darkworld.init.ModItems;
import matthbo.mods.darkworld.utility.LogHelper;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;

import java.util.ArrayList;
import java.util.Random;

public class BlockDarkTallGrass extends BlockBushDarkWorld implements IGrowable, IShearable{

    public BlockDarkTallGrass(){
        super(Material.vine);
        this.setBlockName("darktallgrass");
        this.setStepSound(soundTypeGrass);

        float f = 0.4F;
        this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.8F, 0.5F + f);
    }

    public boolean canBlockStay(World world, int x, int y, int z)
    {
        return super.canBlockStay(world, x, y, z);
    }

    public Item getItemDropped(int par1, Random rand, int par3)
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
    public void harvestBlock(World world, EntityPlayer player, int i, int j, int k, int l)
    {
        super.harvestBlock(world, player, i, j, k, l);
    }

    public boolean func_149851_a(World world, int x, int y, int z, boolean par5)
    {
        int l = world.getBlockMetadata(x, y, z);
        return l != 0;
    }

    public boolean func_149852_a(World world, Random rand, int x, int y, int z)
    {
        return true;
    }

    public void func_149853_b(World world, Random rand, int x, int y, int z)
    {
        int l = world.getBlockMetadata(x, y, z);
        byte b0 = 2;

        if (l == 2)
        {
            b0 = 3;
        }

        if (Blocks.double_plant.canPlaceBlockAt(world, x, y, z))
        {
            Blocks.double_plant.func_149889_c(world, x, y, z, b0, 2);
        }
    }

    /*@Override
    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int meta, int fortune)
    {
        ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
        if (world.rand.nextInt(8) != 0) return ret;
        ItemStack seed = ForgeHooks.getGrassSeed(world);
        if (seed != null) ret.add(seed);
        return ret;
    }*/

    @Override
    public boolean isShearable(ItemStack item, IBlockAccess world, int x, int y, int z)
    {
        return true;
    }

    @Override
    public ArrayList<ItemStack> onSheared(ItemStack item, IBlockAccess world, int x, int y, int z, int fortune)
    {
        ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
        ret.add(new ItemStack(this, 1, world.getBlockMetadata(x, y, z)));
        return ret;
    }
}
