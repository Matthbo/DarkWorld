package matthbo.mods.darkworld.block;

public class BlockDarkDiamondOre extends BlockDarkWorld {
	
	public BlockDarkDiamondOre(){
		super();
		this.setBlockName("darkdiamondore");
		this.setHardness(3.0F).setResistance(5.0F);
		this.setStepSound(soundTypePiston);
	}

}
