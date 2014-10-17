package matthbo.mods.darkworld.world;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.common.eventhandler.Event.Result;
import matthbo.mods.darkworld.init.ModBlocks;
import matthbo.mods.darkworld.init.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSand;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.SpawnerAnimals;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.MapGenBase;
import net.minecraft.world.gen.MapGenRavine;
import net.minecraft.world.gen.NoiseGenerator;
import net.minecraft.world.gen.NoiseGeneratorOctaves;
import net.minecraft.world.gen.feature.WorldGenDungeons;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraft.world.gen.structure.MapGenMineshaft;
import net.minecraft.world.gen.structure.MapGenScatteredFeature;
import net.minecraft.world.gen.structure.MapGenStronghold;
import net.minecraft.world.gen.structure.MapGenVillage;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.ChunkProviderEvent;
import net.minecraftforge.event.terraingen.InitMapGenEvent;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import net.minecraftforge.event.terraingen.TerrainGen;

public class ChunkProviderDarkWord implements IChunkProvider {
	
	private Random rand;
	
	private World worldObj;
	
	private final boolean mapFeaturesEnabled;
	
	private NoiseGeneratorOctaves NoiseGen1;
	private NoiseGeneratorOctaves NoiseGen2;
	private NoiseGeneratorOctaves NoiseGen3;
	private NoiseGeneratorOctaves NoiseGen4;
	public NoiseGeneratorOctaves NoiseGen5;
	public NoiseGeneratorOctaves NoiseGen6;
	public NoiseGeneratorOctaves mobSpawnerNoise;

	private double[] noiseArray;
	private double[] stoneNoise = new double[256];
	private MapGenBase caveGen = new MapGenBase();
	private MapGenStronghold strongholdGen = new MapGenStronghold();
	private MapGenVillage villageGen = new MapGenVillage();
	private MapGenMineshaft mineshaftGen = new MapGenMineshaft();
	private MapGenScatteredFeature scatterFeatureGen = new MapGenScatteredFeature();
	private MapGenBase ravineGen = new MapGenRavine();
	
	private BiomeGenBase[] biomesForGen;
	
	private InitMapGenEvent.EventType mapGenEvent;
	private PopulateChunkEvent.Populate.EventType popEvent;
	
	public double[] noise1;
	public double[] noise2;
	public double[] noise3;
	public double[] noise5;
	public double[] noise6;
	
	public float[] parabolicField;
	public int[][] field = new int[32][32];
	
	{
		caveGen = TerrainGen.getModdedMapGen(caveGen, mapGenEvent.CAVE);
		strongholdGen = (MapGenStronghold) TerrainGen.getModdedMapGen(strongholdGen, mapGenEvent.STRONGHOLD);
		villageGen = (MapGenVillage) TerrainGen.getModdedMapGen(villageGen, mapGenEvent.VILLAGE);
		mineshaftGen = (MapGenMineshaft) TerrainGen.getModdedMapGen(mineshaftGen, mapGenEvent.MINESHAFT);
		scatterFeatureGen = (MapGenScatteredFeature) TerrainGen.getModdedMapGen(scatterFeatureGen, mapGenEvent.SCATTERED_FEATURE);
		ravineGen = TerrainGen.getModdedMapGen(ravineGen, mapGenEvent.RAVINE);
	}

	public ChunkProviderDarkWord(World worldObj, long seed, boolean features) {
		this.worldObj = worldObj;
		this.mapFeaturesEnabled = features;
		this.rand = new Random(seed);
		this.NoiseGen1 = new NoiseGeneratorOctaves(rand, 16);
		this.NoiseGen2 = new NoiseGeneratorOctaves(rand, 16);
		this.NoiseGen3 = new NoiseGeneratorOctaves(rand, 8);
		this.NoiseGen4 = new NoiseGeneratorOctaves(rand, 4);
		this.NoiseGen5 = new NoiseGeneratorOctaves(rand, 10);
		this.NoiseGen6 = new NoiseGeneratorOctaves(rand, 16);
		this.mobSpawnerNoise =  new NoiseGeneratorOctaves(rand, 8);
		
		NoiseGeneratorOctaves[] noiseGens = {NoiseGen1, NoiseGen2, NoiseGen3, NoiseGen4, NoiseGen5, NoiseGen6, mobSpawnerNoise};
		noiseGens = (NoiseGeneratorOctaves[]) TerrainGen.getModdedNoiseGenerators(worldObj, rand, noiseGens);
		
		this.NoiseGen1 = noiseGens[0];
		this.NoiseGen2 = noiseGens[1];
		this.NoiseGen3 = noiseGens[2];
		this.NoiseGen4 = noiseGens[3];
		this.NoiseGen5 = noiseGens[4];
		this.NoiseGen6 = noiseGens[5];
		this.mobSpawnerNoise =  noiseGens[6];
	}

	@Override
	public boolean chunkExists(int i, int j) {
		return true;
	}

	@Override
	public Chunk provideChunk(int i, int j) {
		this.rand.setSeed((long)i * 34873128712L + (long)j * 132897987541L);
		
		Block[] byteArray = new Block[32768];
		
		this.generateTerrain(i, j, byteArray);
		this.biomesForGen = this.worldObj.getWorldChunkManager().loadBlockGeneratorData(biomesForGen, i * 16, j * 16, 16, 16);
		this.replaceBlocksforBiome(i, j, byteArray, this.biomesForGen);
		this.caveGen.func_151539_a(this, worldObj, i, j, byteArray);
		this.ravineGen.func_151539_a(this, worldObj, i, j, byteArray);
		
		if(this.mapFeaturesEnabled){
			this.mineshaftGen.func_151539_a(this, worldObj, i, j, byteArray);
			this.villageGen.func_151539_a(this, worldObj, i, j, byteArray);
			this.strongholdGen.func_151539_a(this, worldObj, i, j, byteArray);
			this.scatterFeatureGen.func_151539_a(this, worldObj, i, j, byteArray);
		}
		
		Chunk chunk = new Chunk(this.worldObj, byteArray, i, j);
		byte[] byteArray2 = chunk.getBiomeArray();
		
		for(int k = 0; k < byteArray2.length; k++){
			byteArray2[k] = (byte)this.biomesForGen[k].biomeID;
		}
		
		chunk.generateSkylightMap();
		
		return chunk;
	}

	public void replaceBlocksforBiome(int i, int j, Block[] byteArray, BiomeGenBase[] biomesForGen2) {
		ChunkProviderEvent.ReplaceBiomeBlocks event = new ChunkProviderEvent.ReplaceBiomeBlocks(this, i, j, byteArray, null, biomesForGen2, null);//TODO look at this if something's wrong!
		MinecraftForge.EVENT_BUS.post(event);
		
		if(event.getResult() == Result.DENY) return;
		
		byte b = 63;
		double d = 0.03125D;
		
		this.stoneNoise = this.NoiseGen4.generateNoiseOctaves(stoneNoise, i * 16, j * 16, 0, 16, 16, 1, d * 2D, d*2D, d * 2D);
		
		for(int x = 0; x < 16; x++) for(int z = 0; z <16; x++){
			BiomeGenBase biome = biomesForGen2[z + x * 16];
			
			int k = (int) (this.stoneNoise[z + x*16] / 3D + 3D + this.rand.nextDouble() * 0.25D);
			int l = -1;
			Block b1 = biome.topBlock;
			Block b2 = biome.fillerBlock;
			
			for(int m = 127; m >= 0; m--){
				int n = (z*16+x)*128+m;
				
				if(m <= 0 + this.rand.nextInt(5)){
					byteArray[n] = Blocks.bedrock;
				}else{
					Block b3 = byteArray[n];
					
					if(b3 == Blocks.air)l = -1;
					else if(b3 == Blocks.stone){
						if(l == -1){
							if(k <= 0){
								b1 = Blocks.air;
								b2 = Blocks.stone;
							}else if (m >= b-4 && m <= b+1){
								b1 = biome.topBlock;
								b2 = biome.fillerBlock;
							}
							
							if(m < b && b1 == Blocks.air){
								b1 = Blocks.water;
							}
							j = i;
							
							if(k >= b-1){
								byteArray[n] = b1;
							}else{
								byteArray[n] = b2;
							}
						}else if(j > 0){
							j--;
							byteArray[n] = b2;
							
							if(j == 0 && b2 == Blocks.sand){
								j = this.rand.nextInt(4);
								b2 = Blocks.sandstone;
							}
						}
					}
				}
			}
		}
	}

	public void generateTerrain(int i, int j, Block[] byteArray) {
		byte b0 = 4;
		byte b1 = 16;
		byte b2 = 63;
		byte b3= 17;
		
		int k = b0 + 1;
		int l = b0 + 1;
		
		this.biomesForGen =  this.worldObj.getWorldChunkManager().getBiomesForGeneration(biomesForGen, i * 4 - 2, j * 4 - 2, k + 5, l + 5);
		this.noiseArray = this.initalizeNoiseField(this.noiseArray, i*b0, 0, j*b0, k, b3, l);
		
		for(int i1 = 0; i1 < b0; i1++){
			for(int j1 = 0; j1 < b0; j1++){
				for(int k1 = 0; k1 < b1; k1++){
					double d0 = 0.125D;
					double d1 = this.noiseArray[((i1 + 0) * l + j1 + 0) * b3 + k1 + 0];
					double d2 = this.noiseArray[((i1 + 0) * l + j1 + 1) * b3 + k1 + 0];
					double d3 = this.noiseArray[((i1 + 1) * l + j1 + 0) * b3 + k1 + 0];
					double d4 = this.noiseArray[((i1 + 1) * l + j1 + 1) * b3 + k1 + 0];
					double d5 = (this.noiseArray[((i1 + 0) * l + j1 + 0) * b3 + k1 + 1] - d1) * d0;
					double d6 = (this.noiseArray[((i1 + 0) * l + j1 + 1) * b3 + k1 + 1] - d2) * d0;
					double d7 = (this.noiseArray[((i1 + 1) * l + j1 + 0) * b3 + k1 + 1] - d3) * d0;
					double d8 = (this.noiseArray[((i1 + 1) * l + j1 + 1) * b3 + k1 + 1] - d4) * d0;

					for(int i2 = 0; i2 < b0; i2++){
						double d9 = 0.25D;
						double d10 = d1;
						double d11 = d2;
						double d12 = (d3 - d1) * d9;
						double d13 = (d4 - d2) * d0;
						
						for(int j2 = 0; j2 < b0; j2++){
							int j3 = j2 + i1 * 4 << 11 | 0 + j1 * 4 << 7 | k1 * 8 + i2;
							short short1 = 128;
							j2 -= short1;
							double d14 = 0.25D;
							double d15 = (d11 - d10)* d14;
							double d16 = d10 - d15;
							
							for(int k2 = 0; k2 < b1; k2++){
								if((d16 += d15) > 0.0D){
									byteArray[j3 += short1] = Blocks.stone;
								}else if(k1 * 8 + i2 < b2){
									byteArray[j3 += short1] = Blocks.water;
								}else{
									byteArray[j3 += short1] = Blocks.air;
								}
							}
							
							d10 += d12;
							d11 += d13;
						}
						
						d1 += d5;
						d2 += d6;
						d3 += d7;
						d4 += d8;
						
					}
				}
			}
		}
	}

	private double[] initalizeNoiseField(double[] doubleArray, int posX, int posY, int posZ, int sizeX, int sizeY, int sizeZ) {
		ChunkProviderEvent.InitNoiseField event = new ChunkProviderEvent.InitNoiseField(this, doubleArray, posX, posY, posZ, sizeX, sizeY, sizeZ);
		MinecraftForge.EVENT_BUS.post(event);
		if(event.getResult() == Result.DENY) return event.noisefield;
		if(doubleArray == null)doubleArray = new double[sizeX * sizeY * sizeZ];
		
		if(this.parabolicField == null){
			this.parabolicField = new float[25];
			
			for(int k1 = -2; k1 <= 2; k1++){
				for(int l1 = -2; l1 <= 2; l1++){
					float f = 10F / MathHelper.sqrt_float((float)(k1*k1 + l1*l1) + 0.2F);
					this.parabolicField[k1 + 2 + (l1 + 2) * 5] = f;
				}
			}
		}
		
		double d0 = 684.412D;
		double d1 = 684.412D;
		
		this.noise5 = this.NoiseGen5.generateNoiseOctaves(this.noise5, posX, posZ, sizeX, sizeZ, 1.121D, 1.121D, 0.5D);
		this.noise6 = this.NoiseGen6.generateNoiseOctaves(this.noise6, posX, posZ, sizeX, sizeZ, 200D, 200D, 0.5D);
		this.noise3 = this.NoiseGen3.generateNoiseOctaves(this.noise3, posX, posZ, sizeX, sizeZ, sizeY, sizeZ, d0 / 80D, d1 / 160D, d0 / 80D);
		this.noise1 = this.NoiseGen1.generateNoiseOctaves(this.noise1, posX, posZ, sizeX, sizeZ, sizeY, sizeZ, d0, d1, d0);
		this.noise2 = this.NoiseGen2.generateNoiseOctaves(this.noise2, posX, posZ, sizeX, sizeZ, sizeY, sizeZ, d0, d1, d0);
		
		boolean flag = false;
		boolean flag1 = false;
		int i2 = 0;
		int j2 = 0;
		
		for(int k2 = 0; k2 < sizeX; k2++){
			for(int l2 = 0; l2 < sizeZ; l2++){
				float f1 = 0.0F;
				float f2 = 0.0F;
				float f3 = 0.0F;
				byte b0 = 2;
				BiomeGenBase biome = this.biomesForGen[k2 + 2 + (l2 + 2) * (sizeX + 5)];
				
				for(int i3 = -b0; i3 <= b0; i3++){
					for(int j3 = -b0; j3 <= b0; j3++){
						BiomeGenBase biome1 = this.biomesForGen[k2 + i3 +2 + (l2 + j3 + 2) * (sizeX + 5)];
						float f4 = this.parabolicField[i3 + 2 + (j3 + 2) * 5] / (biome1.rootHeight + 2F);
						
						if(biome1.rootHeight > biome.rootHeight){
							f4 /= 2F;
						}
						
						f1 += biome1.heightVariation * f4;
						f2 += biome1.heightVariation * f4;
						f3 += f4;
						
					}
				}
				
				f1 /= f3;
				f2 /= f3;
				f1 = f1 * 0.9F + 0.1F;
				f2 = (f2 * 4.0F - 0.1F) / 8F;
				double d2 = this.noise6[j2] / 8000D;
				
				if(d2 < 0D){
					d2 = -d2 * 0.3D;
				}
				
				d2 = d2 * 3D - 2D;
				
				if(d2 < 0D){
					d2 /= 2D;
					
					if(d2 < -1D){
						d2 = 1D;
					}
					
					d2/= 1.4D;
					d2 /= 2D;
					
				}else{
					if(d2 > 1D){
						d2 = 1D;
					}
					
					d2 /= 8D;
				}
				
				j2++;
				
				for(int k3 = 0; k3 < sizeY; k3++){
					double d3 = (double)f2;
					double d4 = (double)f1;
					d3 += d2*0.2D;
					double d5 = (double)sizeY / 2D + d3*4D;
					double d6 = 0D;
					double d7 = ((double)k3 - d5) * 12D * 128D / 128D / d4;
					
					if(d7 < 0D){
						d7 *= 4D;
					}
					
					double d8 = this.noise1[i2] / 521D;
					double d9 = this.noise2[i2] / 521D;
					double d10 = (this.noise3[i2] / 10D + 1D) / 2D;
					
					if(d10 < 0D){
						d6 = d8;
					}else if(d10 > 1D){
						d6 = d9;
					}else{
						d6 = d8 + (d9-d8) * d10;
					}
					
					d6 -= d7;
					
					if(k3 > sizeY-4){
						double d11 = (double)((float)(k3 - (sizeY - 4)) / 3F);
						d6 = d6 * (1D - d11) + (-10D * d11);
					}
					
					doubleArray[i2] = d6;
					i2++;
					
				}
				
			}
		}
		
		return doubleArray;
		
	}

	@Override
	public Chunk loadChunk(int i, int j) {
		return this.provideChunk(i, j);
	}

	@Override
	public void populate(IChunkProvider ichunkprovider, int i, int j) {
		BlockSand.fallInstantly = true;
		
		int k = i * 16;
		int l = j * 16;
		
		BiomeGenBase biome = this.worldObj.getBiomeGenForCoords(k + 16, l + 16);
		
		this.rand.setSeed(this.worldObj.getSeed());
		
		long i1 = this.rand.nextLong() / 2L * 2L + 1L;
		long j1 = this.rand.nextLong() / 2L * 2L + 1L;
		
		this.rand.setSeed((long)i * i1 + (long)j * j1 ^ this.worldObj.getSeed());
		
		boolean flag = false;
		
		MinecraftForge.EVENT_BUS.post(new PopulateChunkEvent.Pre(ichunkprovider, worldObj, rand, i, j, flag));
		
		if(mapFeaturesEnabled){
			this.mineshaftGen.generateStructuresInChunk(worldObj, rand, i, j);
			flag = this.villageGen.generateStructuresInChunk(worldObj, rand, i, j);
			this.strongholdGen.generateStructuresInChunk(worldObj, rand, i, j);
			this.scatterFeatureGen.generateStructuresInChunk(worldObj, rand, i, j);
		}
		
		int k1;
		int l1;
		int i2;
		
		if(biome != BiomeGenBase.desert && biome != BiomeGenBase.desertHills && !flag && this.rand.nextInt(4) == 0 && TerrainGen.populate(ichunkprovider, worldObj, rand, i, j, flag, popEvent.LAKE)){
			k1 = k + this.rand.nextInt(16) + 8;
			l1 = this.rand.nextInt(128);
			i2 = l + this.rand.nextInt(16) + 8;
			
			(new WorldGenLakes(Blocks.water)).generate(worldObj, rand, k1, l1, i2);
		}
		
		if(TerrainGen.populate(ichunkprovider, worldObj, rand, i, j, flag, popEvent.LAVA) && !flag && this.rand.nextInt(8) == 0){
			k1 = k + this.rand.nextInt(16) + 8;
			l1 = this.rand.nextInt(this.rand.nextInt(120) + 8);
			i2 = l + this.rand.nextInt(16) + 8;
			
			if(l1 < 63 || this.rand.nextInt(10) == 0){
				(new WorldGenLakes(Blocks.lava)).generate(worldObj, rand, k1, l1, i2);
			}
		}
		
		boolean doGen = TerrainGen.populate(ichunkprovider, worldObj, rand, i, j, flag, popEvent.DUNGEON);
		
		for(k1 = 0; doGen && k1 < 8; k1++){
			l1 = k + this.rand.nextInt(16) + 8;
			i2 = l + this.rand.nextInt(16) + 8;//yPos
			int j2 = l +this.rand.nextInt(16) + 8;
			
			(new WorldGenDungeons()).generate(worldObj, rand, l1, i2, j2);
		}
		
		biome.decorate(worldObj, rand, k, l);
		SpawnerAnimals.performWorldGenSpawning(worldObj, biome, k+ 8, l+8, 16, 16, rand);
		
		k+=8;
		l+=8;

		doGen = TerrainGen.populate(ichunkprovider, worldObj, rand, i, j, flag, popEvent.ICE);
		
		for(k1 = 0; doGen && k1 < 16; k1++){
			for(l1 = 0; l1 < 16; l1++){
				i2 = this.worldObj.getPrecipitationHeight(k + k1, l + l1);
				
				if(this.worldObj.isBlockFreezable(k + k1, i2 -1, l + l1)){
					this.worldObj.setBlock(k + k1, i2-1, l + l1, Blocks.ice, 0, 2);
				}
				
				if(this.worldObj.func_147478_e(k + k1, i2 -1, l + l1, true)){
					this.worldObj.setBlock(k + k1, i2, l + l1, Blocks.ice, 0, 2);
				}
			}
		}
		
		MinecraftForge.EVENT_BUS.post(new PopulateChunkEvent.Post(ichunkprovider, worldObj, rand, i, j, flag));
		
		BlockSand.fallInstantly = false;
	}

	@Override
	public boolean saveChunks(boolean flag, IProgressUpdate iprogressupdate) {
		return true;
	}

	@Override
	public boolean unloadQueuedChunks() {
		return false;
	}

	@Override
	public boolean canSave() {
		return true;
	}

	@Override
	public String makeString() {
		return "RandomLevelSource";
	}

	@Override
	public List getPossibleCreatures(EnumCreatureType enumcreaturetype, int i, int j, int k) {
		BiomeGenBase biome = this.worldObj.getBiomeGenForCoords(i, k);
		return biome == null ? null : (biome == BiomeGenBase.swampland && enumcreaturetype == EnumCreatureType.monster && this.scatterFeatureGen.hasStructureAt(i, j, k) ? this.scatterFeatureGen.getScatteredFeatureSpawnList() : biome.getSpawnableList(enumcreaturetype));
	}

	@Override
	public ChunkPosition func_147416_a(World world, String s, int i, int j, int k) {
		return "Stronghold".equals(s) && this.strongholdGen != null ? this.strongholdGen.func_151545_a(world, i, j, k) : null;
	}

	@Override
	public int getLoadedChunkCount() {
		return 0;
	}

	@Override
	public void recreateStructures(int i, int j) {
		if(this.mapFeaturesEnabled){
			this.mineshaftGen.func_151539_a(this, worldObj, i, j, null);
			this.villageGen.func_151539_a(this, worldObj, i, j, null);
			this.strongholdGen.func_151539_a(this, worldObj, i, j, null);
			this.scatterFeatureGen.func_151539_a(this, worldObj, i, j, null);
		}
	}

	@Override
	public void saveExtraData() {}

}
