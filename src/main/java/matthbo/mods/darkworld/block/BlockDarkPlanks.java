package matthbo.mods.darkworld.block;

import java.util.List;

import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.IStringSerializable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class BlockDarkPlanks extends BlockDarkWorld {

	public static final PropertyEnum VARIANT = PropertyEnum.create("variant", BlockDarkPlanks.EnumType.class);
	
	public BlockDarkPlanks(){
		super(Material.wood);
		this.setUnlocalizedName("darkplanks");
		this.setHardness(2.0F).setResistance(5.0F);
		this.setStepSound(soundTypeWood);
		this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, EnumType.DARKOAK));
	}
	
	public int damageDropped(IBlockState state)
	{
		 return ((BlockDarkPlanks.EnumType)state.getValue(VARIANT)).getMetadata();
	}

	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item itemIn, CreativeTabs tab, List list)
	{
		BlockDarkPlanks.EnumType[] aenumtype = BlockDarkPlanks.EnumType.values();
		int i = aenumtype.length;

		for (int j = 0; j < i; ++j)
		{
			BlockDarkPlanks.EnumType enumtype = aenumtype[j];
			list.add(new ItemStack(itemIn, 1, enumtype.getMetadata()));
		}
	}

	public IBlockState getStateFromMeta(int meta)
	{
		return this.getDefaultState().withProperty(VARIANT, BlockDarkPlanks.EnumType.byMetadata(meta));
	}

	public int getMetaFromState(IBlockState state)
	{
		return ((BlockDarkPlanks.EnumType)state.getValue(VARIANT)).getMetadata();
	}

	protected BlockState createBlockState()
	{
		return new BlockState(this, new IProperty[] {VARIANT});
	}

	public static enum EnumType implements IStringSerializable
	{
		DARKOAK(0, "darkoak"),
		DARKSPRUCE(1, "darkspruce"),
		DARKBIRCH(2, "darkbirch"),
		DARKJUNGLE(3, "darkjungle"),
		DARKACACIA(4, "darkacacia"),
		DARKBIG_OAK(5, "darkbig_oak");
		private static final BlockDarkPlanks.EnumType[] META_LOOKUP = new BlockDarkPlanks.EnumType[values().length];
		private final int meta;
		private final String name;
		private final String unlocalizedName;

		private static final String __OBFID = "CL_00002081";

		private EnumType(int meta, String name)
		{
			this(meta, name, name);
		}

		private EnumType(int meta, String name, String unlocalizedName)
		{
			this.meta = meta;
			this.name = name;
			this.unlocalizedName = unlocalizedName;
		}

		public int getMetadata()
		{
			return this.meta;
		}

		public String toString()
		{
			return this.name;
		}

		public static BlockDarkPlanks.EnumType byMetadata(int meta)
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
			BlockDarkPlanks.EnumType[] var0 = values();
			int var1 = var0.length;

			for (int var2 = 0; var2 < var1; ++var2)
			{
				BlockDarkPlanks.EnumType var3 = var0[var2];
				META_LOOKUP[var3.getMetadata()] = var3;
			}
		}
	}

}
