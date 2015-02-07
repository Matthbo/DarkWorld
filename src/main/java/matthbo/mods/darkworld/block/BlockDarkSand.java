package matthbo.mods.darkworld.block;

import java.util.List;

import matthbo.mods.darkworld.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockDarkSand extends BlockFallingDarkWorld {

	public static final PropertyEnum VARIANT = PropertyEnum.create("variant", BlockDarkSand.EnumType.class);

	public BlockDarkSand(){
		super(Material.sand);
		this.setUnlocalizedName("darksand");
		this.setHardness(0.5F);
		this.setStepSound(soundTypeSand);
		this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, BlockDarkSand.EnumType.DARKSAND));
	}

	public int damageDropped(IBlockState state)
	{
		return ((BlockDarkSand.EnumType)state.getValue(VARIANT)).getMetadata();
	}

	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item itemIn, CreativeTabs tab, List list)
	{
		BlockDarkSand.EnumType[] aenumtype = BlockDarkSand.EnumType.values();
		int i = aenumtype.length;

		for (int j = 0; j < i; ++j)
		{
			BlockDarkSand.EnumType enumtype = aenumtype[j];
			list.add(new ItemStack(itemIn, 1, enumtype.getMetadata()));
		}
	}

	public MapColor getMapColor(IBlockState state)
	{
		return ((BlockDarkSand.EnumType)state.getValue(VARIANT)).getMapColor();
	}

	public IBlockState getStateFromMeta(int meta)
	{
		return this.getDefaultState().withProperty(VARIANT, BlockDarkSand.EnumType.byMetadata(meta));
	}

	public int getMetaFromState(IBlockState state)
	{
		return ((BlockDarkSand.EnumType)state.getValue(VARIANT)).getMetadata();
	}

	protected BlockState createBlockState()
	{
		return new BlockState(this, new IProperty[] {VARIANT});
	}
	
	@Override
    public boolean canSustainPlant(IBlockAccess world, BlockPos pos, EnumFacing direction, IPlantable plantable) {
    	Block plant = plantable.getPlant(world, pos).getBlock();
    	if (plant == ModBlocks.darkCactus)
        {
            return true;
        }
    	return false;
    }

	public static enum EnumType implements IStringSerializable
	{
		DARKSAND(0, "darksand", "default", MapColor.sandColor),
		DARKRED_SAND(1, "darkred_sand", "red", MapColor.dirtColor);
		private static final BlockDarkSand.EnumType[] META_LOOKUP = new BlockDarkSand.EnumType[values().length];
		private final int meta;
		private final String name;
		private final MapColor mapColor;
		private final String unlocalizedName;

		private EnumType(int meta, String name, String unlocalizedName, MapColor mapColor)
		{
			this.meta = meta;
			this.name = name;
			this.mapColor = mapColor;
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

		public MapColor getMapColor()
		{
			return this.mapColor;
		}

		public static BlockDarkSand.EnumType byMetadata(int meta)
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
			BlockDarkSand.EnumType[] var0 = values();
			int var1 = var0.length;

			for (int var2 = 0; var2 < var1; ++var2)
			{
				BlockDarkSand.EnumType var3 = var0[var2];
				META_LOOKUP[var3.getMetadata()] = var3;
			}
		}
	}

}
