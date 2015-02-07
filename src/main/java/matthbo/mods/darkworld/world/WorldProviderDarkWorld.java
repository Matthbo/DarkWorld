package matthbo.mods.darkworld.world;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import matthbo.mods.darkworld.init.ModDimensions;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.common.DimensionManager;

public class WorldProviderDarkWorld extends WorldProvider {

	public void registerWorldChunkManager() {
		//this.worldChunkMgr = new WorldChunkManagerHell(ModBiomes.darkPlains, 0.6F);
		//this.worldChunkMgr = new WorldChunkManagerHell(BiomeGenBase.desert, 0.6F);
		this.worldChunkMgr = new WorldChunkManagerDarkWorld(worldObj);
		this.dimensionId = ModDimensions.dimensionIDDarkWorld;
		
	}
	
	public IChunkProvider createChunkGenerator(){
		return new ChunkProviderDarkWorldCharlie(this.worldObj, this.worldObj.getSeed(), true, this.worldObj.getWorldInfo().getGeneratorOptions());
		//return new ChunkProviderHell(ModDimensions.dimensionIDDarkWorld, 0.6);
	}

    public static WorldProvider getProviderForDimension(int id)
	{
		return DimensionManager.createProviderFor(ModDimensions.dimensionIDDarkWorld);
	}
	
	public boolean isSurfaceWorld()
    {
        return false;
    }
	
	@Override
    public float calculateCelestialAngle(long var1, float var3) {
		return 0.5F;
    }
	
	protected void generateLightBrightnessTable()
    {
        float f = 0.1F;

        for (int i = 0; i <= 15; ++i)
        {
            float f1 = 1.0F - (float)i / 15.0F;
            this.lightBrightnessTable[i] = (1.0F - f1) / (f1 * 3.0F + 1.0F) * (1.0F - f) + f;
        }
    }
	
	
	/**
	 * Doesn't really work, mojang needs to fix this
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public String getWelcomeMessage()
	{
		return "Entering the Dark World";
	}

	/**
	 * Doesn't really work, mojang needs to fix this
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public String getDepartMessage()
	{
		return "Leaving the Dark World";
	}
	
	public boolean canRespawnHere()
    {
        return true;
    }

	
	@Override
	public String getDimensionName() {
		return "Dark World";
	}

    @Override
    public String getInternalNameSuffix() {
        return "_darkworld";
    }

}
