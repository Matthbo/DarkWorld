package matthbo.mods.darkworld.block;

import net.minecraft.entity.Entity;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;

public class BlockTeleporterNone extends Teleporter {

	public BlockTeleporterNone(WorldServer p_i1963_1_) {
		super(p_i1963_1_);
	}
	
	@Override
	public void placeInPortal(Entity entity, float rotationYaw) {}
	
	@Override
	public boolean placeInExistingPortal(Entity entity, float par2) {
		return false;
	}
	
	@Override
	public boolean makePortal(Entity entity) {
		return false;
	}
	
	@Override
	public void removeStalePortalLocations(long p_85189_1_) {}

}
