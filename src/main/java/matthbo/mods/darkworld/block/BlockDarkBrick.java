package matthbo.mods.darkworld.block;

public class BlockDarkBrick extends BlockDarkWorld {
	
	public BlockDarkBrick(){
		super();
		this.setBlockName("darkbrick");
		this.setHardness(2.0F).setResistance(10.0F);
		this.setStepSound(soundTypePiston);
	}

}
