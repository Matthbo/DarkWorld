package matthbo.mods.darkworld.world.gen.layer;

import matthbo.mods.darkworld.biome.DarkBiomeGenBase;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;
import static matthbo.mods.darkworld.init.ModBiomes.*;

public class GenLayerDarkWorldBiomes extends GenLayer {

	protected DarkBiomeGenBase[] allowedBiomes = {darkDesert, darkPlains, darkOcean};
	
	public GenLayerDarkWorldBiomes(long seed) {
		super(seed);
	}
	
	public GenLayerDarkWorldBiomes(long seed, GenLayer genlayer) {
		super(seed);
		this.parent = genlayer;
	}
	
    @Override
    public int[] getInts(int x, int z, int width, int depth) {
        int[] dest = IntCache.getIntCache(width * depth);
        for (int dz = 0; dz < depth; dz++) {
            for (int dx = 0; dx < width; dx++) {
                this.initChunkSeed(dx + x, dz + z);
                dest[(dx + dz * width)] = this.allowedBiomes[nextInt(this.allowedBiomes.length)].biomeID;
            }
        }
        return dest;
    }

}
