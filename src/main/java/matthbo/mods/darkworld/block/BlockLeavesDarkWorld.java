package matthbo.mods.darkworld.block;

import matthbo.mods.darkworld.creativetab.CreativeTabDarkWorld;
import net.minecraft.block.BlockLeaves;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.IIcon;

public abstract class BlockLeavesDarkWorld extends BlockLeaves{

	public BlockLeavesDarkWorld(){
		super();
		this.setCreativeTab(CreativeTabDarkWorld.DARKWORLD_TAB);
	}

}
