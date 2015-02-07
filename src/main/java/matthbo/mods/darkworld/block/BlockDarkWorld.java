package matthbo.mods.darkworld.block;

import matthbo.mods.darkworld.creativetab.CreativeTabDarkWorld;
import matthbo.mods.darkworld.reference.Refs;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

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

    /*@Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        blockIcon = iconRegister.registerIcon(this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1));
    }*/

    protected String getUnwrappedUnlocalizedName(String unlocalizedName)
    {
        return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
    }

}
