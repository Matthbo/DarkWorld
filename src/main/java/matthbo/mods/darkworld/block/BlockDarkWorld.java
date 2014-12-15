package matthbo.mods.darkworld.block;

import static net.minecraftforge.common.util.ForgeDirection.UP;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import matthbo.mods.darkworld.creativetab.CreativeTabDarkWorld;
import matthbo.mods.darkworld.init.ModBlocks;
import matthbo.mods.darkworld.reference.EnumDarkWorld;
import matthbo.mods.darkworld.reference.Refs;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockDarkWorld extends Block{
	
	public BlockDarkWorld(Material material){
		super(material);
		this.setCreativeTab(CreativeTabDarkWorld.DARKWORLD_TAB);
	}
	
	public BlockDarkWorld(){
		this(Material.rock);
		this.setCreativeTab(CreativeTabDarkWorld.DARKWORLD_TAB);
	}
	
	@Override
    public String getUnlocalizedName()
    {
        return String.format("tile.%s%s", Refs.MOD_ID.toLowerCase() + ":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        blockIcon = iconRegister.registerIcon(this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1));
    }

    protected String getUnwrappedUnlocalizedName(String unlocalizedName)
    {
        return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
    }
    
    
    
    protected boolean canPlaceBlockOn(Block p_149854_1_)
    {
        return p_149854_1_ == ModBlocks.darkGrass || p_149854_1_ == ModBlocks.darkDirt;// || p_149854_1_ == Blocks.farmland;
    }

}
