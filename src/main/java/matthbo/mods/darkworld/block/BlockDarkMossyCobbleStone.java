package matthbo.mods.darkworld.block;

public class BlockDarkMossyCobbleStone extends BlockDarkWorld {
	
	public BlockDarkMossyCobbleStone(){
		super();
		this.setUnlocalizedName("darkmossycobblestone");
		this.setHardness(2.0F).setResistance(10.0F);
		this.setStepSound(soundTypePiston);
	}

}
