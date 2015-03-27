package matthbo.mods.darkworld.client.particles;

import net.minecraft.client.particle.EntityPortalFX;
import net.minecraft.world.World;

public class EntityDWPortalFXBeta extends EntityPortalFX {

    public EntityDWPortalFXBeta(World world, double par2, double par3, double par4, double par5, double par6, double par7) {
        super(world, par2, par3, par4, par5, par6, par7);
        this.particleRed = this.particleBlue = this.particleGreen = 0.20F;
    }

    //TODO: finish this class (onUpdate)

}
