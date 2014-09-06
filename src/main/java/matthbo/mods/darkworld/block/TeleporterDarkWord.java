package matthbo.mods.darkworld.block;

import java.util.Random;

import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;

public class TeleporterDarkWord extends Teleporter {
	
	private final WorldServer WSInstance;
	private final Random rand;

	public TeleporterDarkWord(WorldServer p_i1963_1_) {
		super(p_i1963_1_);
		
		
		this.WSInstance = p_i1963_1_;
		this.rand = new Random(p_i1963_1_.getSeed()); // may use the seed instead of a random....
	}

}
