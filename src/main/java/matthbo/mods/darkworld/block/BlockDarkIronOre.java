package matthbo.mods.darkworld.block;

public class BlockDarkIronOre extends BlockDarkWorld {
	
	public BlockDarkIronOre(){
		super();
		this.setUnlocalizedName("darkironore");
		this.setHardness(3.0F).setResistance(5.0F);
		this.setStepSound(soundTypePiston);
	}

}
