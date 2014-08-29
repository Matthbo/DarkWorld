package matthbo.mods.darkworld.block;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockPeculiarCobbleStone extends BlockDarkWorld {
	
	public BlockPeculiarCobbleStone(){
		super();
		this.setBlockName("peculiarcobblestone");
		this.setStepSound(soundTypeStone);
		this.setHardness(2.0F);
		this.setResistance(10.0F);
	}

}
