package matthbo.mods.darkworld.block;

import matthbo.mods.darkworld.init.ModBlocks;
import matthbo.mods.darkworld.init.ModItems;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockState;
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
        this.setDefaultState(this.blockState.getBaseState().withProperty(CHECK_DECAY, Boolean.valueOf(true)).withProperty(DECAYABLE, Boolean.valueOf(true)));
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

    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(DECAYABLE, Boolean.valueOf((meta & 4) == 0)).withProperty(CHECK_DECAY, Boolean.valueOf((meta & 8) > 0));
    }

    public int getMetaFromState(IBlockState state)
    {
        byte b0 = 0;
        int i = b0;

        if (!((Boolean)state.getValue(DECAYABLE)).booleanValue())
        {
            i |= 4;
        }

        if (((Boolean)state.getValue(CHECK_DECAY)).booleanValue())
        {
            i |= 8;
        }

        return i;
    }

    protected BlockState createBlockState()
    {
        return new BlockState(this, new IProperty[] {CHECK_DECAY, DECAYABLE});
    }

}
