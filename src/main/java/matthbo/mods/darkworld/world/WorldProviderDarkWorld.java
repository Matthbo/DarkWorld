package matthbo.mods.darkworld.world;

import java.awt.Color;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import matthbo.mods.darkworld.DarkWorld;
import matthbo.mods.darkworld.init.ModBiomes;
import matthbo.mods.darkworld.init.ModDimensions;
import net.minecraft.entity.Entity;
import net.minecraft.util.Vec3;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenEnd;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.biome.WorldChunkManagerHell;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.ChunkProviderHell;
import net.minecraftforge.common.DimensionManager;

public class WorldProviderDarkWorld extends WorldProvider {

	public void registerWorldChunkManager() {
		//this.worldChunkMgr = new WorldChunkManagerHell(ModBiomes.darkPlains, 0.6F);
		//this.worldChunkMgr = new WorldChunkManagerHell(BiomeGenBase.desert, 0.6F);
		this.worldChunkMgr = new WorldChunkManagerDarkWorld(worldObj.getSeed(), terrainType);
		this.dimensionId = ModDimensions.dimensionIDDarkWorld;
		
	};
	
	public IChunkProvider createChunkGenerator(){
		return new ChunkProviderDarkWorldBeta(this.worldObj, this.worldObj.getSeed(), true);
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

}
