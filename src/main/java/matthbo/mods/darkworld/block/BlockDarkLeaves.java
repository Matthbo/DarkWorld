package matthbo.mods.darkworld.block;

import matthbo.mods.darkworld.init.ModBlocks;
import matthbo.mods.darkworld.init.ModItems;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

public class BlockDarkLeaves extends BlockLeavesDarkWorld {

    public BlockDarkLeaves(){
        super();
        this.setUnlocalizedName("darkleaves");
    }



    public Item getItemDropped(IBlockState state_, Random rand, int fortune)
    {
        return Item.getItemFromBlock(ModBlocks.darkSapling);
    }

    protected void dropApple(World worldIn, BlockPos pos, IBlockState state, int chance)
    {
        if (worldIn.rand.nextInt(chance) == 0)
        {
            spawnAsEntity(worldIn, pos, new ItemStack(ModItems.darkApple, 1, 0));
        }
    }

    protected int func_150123_b(int p_150123_1_)
    {
        int j = super.func_150123_b(p_150123_1_);

        if ((p_150123_1_ & 3) == 3)
        {
            j = 40;
        }

        return j;
    }

    public BlockDarkPlanks.EnumType getDarkWoodType(int meta)
    {
        return BlockDarkPlanks.EnumType.byMetadata((meta & 3) % 4);
    }

    @Override
    public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune)
    {
        IBlockState state = world.getBlockState(pos);
        return new java.util.ArrayList(java.util.Arrays.asList(new ItemStack(this, 1, 0)));
    }

}
