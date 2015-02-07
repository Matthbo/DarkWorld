package matthbo.mods.darkworld.block;

import java.util.List;

import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.util.IStringSerializable;

public class BlockDarkSandStone extends BlockDarkWorld {

	public static final PropertyEnum TYPE = PropertyEnum.create("type", BlockDarkSandStone.EnumType.class);

	public BlockDarkSandStone(){
		super();
		this.setUnlocalizedName("darksandstone");
		this.setHardness(0.8F);
		this.setStepSound(soundTypePiston);
		this.setDefaultState(this.blockState.getBaseState().withProperty(TYPE, BlockDarkSandStone.EnumType.DEFAULT));
	}

	public int damageDropped(IBlockState state)
	{
		return ((BlockDarkSandStone.EnumType)state.getValue(TYPE)).getMetadata();
	}

	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item itemIn, CreativeTabs tab, List list)
	{
		BlockDarkSandStone.EnumType[] aenumtype = BlockDarkSandStone.EnumType.values();
		int i = aenumtype.length;

		for (int j = 0; j < i; ++j)
		{
			BlockDarkSandStone.EnumType enumtype = aenumtype[j];
			list.add(new ItemStack(itemIn, 1, enumtype.getMetadata()));
		}
	}

	public IBlockState getStateFromMeta(int meta)
	{
		return this.getDefaultState().withProperty(TYPE, BlockDarkSandStone.EnumType.byMetadata(meta));
	}

	public int getMetaFromState(IBlockState state)
	{
		return ((BlockDarkSandStone.EnumType)state.getValue(TYPE)).getMetadata();
	}

	protected BlockState createBlockState()
	{
		return new BlockState(this, new IProperty[] {TYPE});
	}

	public static enum EnumType implements IStringSerializable
	{
		DEFAULT(0, "darksandstone", "default"),
		CHISELED(1, "darkchiseled_sandstone", "chiseled"),
		SMOOTH(2, "darksmooth_sandstone", "smooth");
		private static final BlockDarkSandStone.EnumType[] META_LOOKUP = new BlockDarkSandStone.EnumType[values().length];
		private final int metadata;
		private final String name;
		private final String unlocalizedName;

		private static final String __OBFID = "CL_00002068";

		private EnumType(int meta, String name, String unlocalizedName)
		{
			this.metadata = meta;
			this.name = name;
			this.unlocalizedName = unlocalizedName;
		}

		public int getMetadata()
		{
			return this.metadata;
		}

		public String toString()
		{
			return this.name;
		}

		public static BlockDarkSandStone.EnumType byMetadata(int meta)
		{
			if (meta < 0 || meta >= META_LOOKUP.length)
			{
				meta = 0;
			}

			return META_LOOKUP[meta];
		}

		public String getName()
		{
			return this.name;
		}

		public String getUnlocalizedName()
		{
			return this.unlocalizedName;
		}

		static
		{
			BlockDarkSandStone.EnumType[] var0 = values();
			int var1 = var0.length;

			for (int var2 = 0; var2 < var1; ++var2)
			{
				BlockDarkSandStone.EnumType var3 = var0[var2];
				META_LOOKUP[var3.getMetadata()] = var3;
			}
		}
	}

}
