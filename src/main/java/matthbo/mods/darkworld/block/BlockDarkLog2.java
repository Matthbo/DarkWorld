package matthbo.mods.darkworld.block;

import java.util.List;

import com.google.common.base.Predicate;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockDarkLog2 extends BlockLogDarkWorld {

	public static final PropertyEnum VARIANT = PropertyEnum.create("variant", BlockDarkPlanks.EnumType.class, new Predicate()
	{
		public boolean apply(BlockDarkPlanks.EnumType type)
		{
			return type.getMetadata() >= 4;
		}
		public boolean apply(Object p_apply_1_)
		{
			return this.apply((BlockDarkPlanks.EnumType)p_apply_1_);
		}
	});

	public BlockDarkLog2(){
		super();
		this.setUnlocalizedName("darklog");
		this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, BlockDarkPlanks.EnumType.DARKACACIA).withProperty(LOG_AXIS, BlockLogDarkWorld.EnumAxis.Y));
	}

	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item itemIn, CreativeTabs tab, List list)
	{
		list.add(new ItemStack(itemIn, 1, BlockDarkPlanks.EnumType.DARKACACIA.getMetadata() - 4));
		list.add(new ItemStack(itemIn, 1, BlockDarkPlanks.EnumType.DARKBIG_OAK.getMetadata() - 4));
	}

	public IBlockState getStateFromMeta(int meta)
	{
		IBlockState iblockstate = this.getDefaultState().withProperty(VARIANT, BlockDarkPlanks.EnumType.byMetadata((meta & 3) + 4));

		switch (meta & 12)
		{
			case 0:
				iblockstate = iblockstate.withProperty(LOG_AXIS, BlockLogDarkWorld.EnumAxis.Y);
				break;
			case 4:
				iblockstate = iblockstate.withProperty(LOG_AXIS, BlockLogDarkWorld.EnumAxis.X);
				break;
			case 8:
				iblockstate = iblockstate.withProperty(LOG_AXIS, BlockLogDarkWorld.EnumAxis.Z);
				break;
			default:
				iblockstate = iblockstate.withProperty(LOG_AXIS, BlockLogDarkWorld.EnumAxis.NONE);
		}

		return iblockstate;
	}

	public int getMetaFromState(IBlockState state)
	{
		byte b0 = 0;
		int i = b0 | ((BlockDarkPlanks.EnumType)state.getValue(VARIANT)).getMetadata() - 4;

		switch (BlockDarkLog2.SwitchEnumAxis.AXIS_LOOKUP[((BlockLogDarkWorld.EnumAxis)state.getValue(LOG_AXIS)).ordinal()])
		{
			case 1:
				i |= 4;
				break;
			case 2:
				i |= 8;
				break;
			case 3:
				i |= 12;
		}

		return i;
	}

	protected BlockState createBlockState()
	{
		return new BlockState(this, new IProperty[] {VARIANT, LOG_AXIS});
	}

	protected ItemStack createStackedBlock(IBlockState state)
	{
		return new ItemStack(Item.getItemFromBlock(this), 1, ((BlockDarkPlanks.EnumType)state.getValue(VARIANT)).getMetadata() - 4);
	}

	public int damageDropped(IBlockState state)
	{
		return ((BlockDarkPlanks.EnumType)state.getValue(VARIANT)).getMetadata() - 4;
	}

	static final class SwitchEnumAxis
	{
		static final int[] AXIS_LOOKUP = new int[BlockLogDarkWorld.EnumAxis.values().length];

		static
		{
			try
			{
				AXIS_LOOKUP[BlockLogDarkWorld.EnumAxis.X.ordinal()] = 1;
			}
			catch (NoSuchFieldError var3)
			{
				;
			}

			try
			{
				AXIS_LOOKUP[BlockLogDarkWorld.EnumAxis.Z.ordinal()] = 2;
			}
			catch (NoSuchFieldError var2)
			{
				;
			}

			try
			{
				AXIS_LOOKUP[BlockLogDarkWorld.EnumAxis.NONE.ordinal()] = 3;
			}
			catch (NoSuchFieldError var1)
			{
				;
			}
		}
	}

}
