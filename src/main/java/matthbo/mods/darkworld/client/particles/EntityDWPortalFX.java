package matthbo.mods.darkworld.client.particles;

import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.entity.Entity;

import matthbo.mods.darkworld.init.ModDimensions;
import matthbo.mods.darkworld.reference.Refs;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import static org.lwjgl.opengl.GL11.*;


public class EntityDWPortalFX extends EntityFX {
	
	private static final ResourceLocation texture = new ResourceLocation(Refs.MOD_ID.toLowerCase(), "textures/particle/dwportal.png");
	
	private float portalParticleScale;
    private double portalPosX;
    private double portalPosY;
    private double portalPosZ;
	
	public EntityDWPortalFX(World world, double x, double y, double z, double velX, double velY, double velZ){
		super(world, x, y, z, velX, velY, velZ);
		this.motionX = velX;
        this.motionY = velY;
        this.motionZ = velZ;
        this.portalPosX = this.posX = x;
        this.portalPosY = this.posY = y;
        this.portalPosZ = this.posZ = z;
        this.portalParticleScale = this.particleScale = this.rand.nextFloat() * 0.2F + 0.5F;
        this.particleMaxAge = (int)(Math.random() * 10.0D) + 40;
        this.noClip = true;
	}
	
	public void func_180434_a(WorldRenderer tesselator, Entity entity, float partialTicks, float par3, float par4, float par5, float par6, float par7){
		Minecraft.getMinecraft().renderEngine.bindTexture(texture);
		float f6 = ((float)this.particleAge + partialTicks) / (float)this.particleMaxAge;
        f6 = 1.0F - f6;
        f6 *= f6;
        f6 = 1.0F - f6;
        this.particleScale = this.portalParticleScale * f6;
		glDepthMask(false);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		glAlphaFunc(GL_GREATER, 0.003921569F);
		tesselator.startDrawingQuads();
		tesselator.setBrightness(getBrightnessForRender(partialTicks));
		float scale = particleScale*0.05F;
		float x = (float)(prevPosX + (posX - prevPosX) * partialTicks - interpPosX);
		float y = (float)(prevPosY + (posY - prevPosY) * partialTicks - interpPosY);
		float z = (float)(prevPosZ + (posZ - prevPosZ) * partialTicks - interpPosZ);
		tesselator.addVertexWithUV(x - par3 * scale - par6 * scale, y - par4 * scale, z - par5 * scale - par7 * scale, 0, 0);
		tesselator.addVertexWithUV(x - par3 * scale + par6 * scale, y + par4 * scale, z - par5 * scale + par7 * scale, 1, 0);
		tesselator.addVertexWithUV(x + par3 * scale + par6 * scale, y + par4 * scale, z + par5 * scale + par7 * scale, 1, 1);
		tesselator.addVertexWithUV(x + par3 * scale - par6 * scale, y - par4 * scale, z + par5 * scale - par7 * scale, 0, 1);
		//tesselator.draw(); TODO: check this
		glAlphaFunc(GL_GREATER, 0.1F);
		glDisable(GL_BLEND);
		glDepthMask(true);
	}
	
	public int getBrightnessForRender(float p_70070_1_)
    {
        int i = super.getBrightnessForRender(p_70070_1_);
        float f1 = (float)this.particleAge / (float)this.particleMaxAge;
        f1 *= f1;
        f1 *= f1;
        int j = i & 255;
        int k = i >> 16 & 255;
        k += (int)(f1 * 15.0F * 16.0F);

        if (k > 240)
        {
            k = 240;
        }

        return j | k << 16;
    }

    /**
     * Gets how bright this entity is.
     */
    public float getBrightness(float p_70013_1_)
    {
        float f1 = super.getBrightness(p_70013_1_);
        float f2 = (float)this.particleAge / (float)this.particleMaxAge;
        f2 = f2 * f2 * f2 * f2;
        return f1 * (1.0F - f2) + f2;
    }

    /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate()
    {
    	//int r = rand.nextInt(2);
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        float f = (float)this.particleAge / (float)this.particleMaxAge;
        float f1 = f;
        f = -f + f * f * 2.0F;
        
        //if(r==1)f = 1.0F - f;
        //if(r==0)f = -1.0F - f;
        
        if(this.dimension !=ModDimensions.dimensionIDDarkWorld)f = -1.0F - f;
        if(this.dimension == ModDimensions.dimensionIDDarkWorld)f = 1.0F - f;
        
        this.posX = this.portalPosX + this.motionX * (double)f * 2;
        this.posY = this.portalPosY + this.motionY * (double)f * 2 + (double)(1.0F - f1);
        this.posZ = this.portalPosZ + this.motionZ * (double)f * 2;

        if (this.particleAge++ >= this.particleMaxAge)
        {
            this.setDead();
        }
    }
	
	public int getFXLayer(){
		return 3;
	}
}
