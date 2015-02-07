package matthbo.mods.darkworld.block;

import matthbo.mods.darkworld.reference.Refs;
import net.minecraft.block.BlockPortal;

public class BlockPortalBaseDarkWorld extends BlockPortal{
	
	public BlockPortalBaseDarkWorld(){
		super();
	}
	
	@Override
    public String getUnlocalizedName()
    {
        return String.format("tile.%s%s", Refs.MOD_ID.toLowerCase() + ":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }

    protected String getUnwrappedUnlocalizedName(String unlocalizedName)
    {
        return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
    }

}
