package matthbo.mods.darkworld.block;

public class BlockDarkRedstoneOre extends BlockDarkWorld {

	public BlockDarkRedstoneOre(){
		super();
		this.setBlockName("darkredstoneore");
		this.setHardness(3.0F).setResistance(5.0F);
		this.setStepSound(soundTypePiston);
	}
	
}
