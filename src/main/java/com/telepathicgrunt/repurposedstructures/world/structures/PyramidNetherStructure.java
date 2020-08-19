package com.telepathicgrunt.repurposedstructures.world.structures;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.world.structures.pieces.GeneralJigsawGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.template.TemplateManager;


public class PyramidNetherStructure extends Structure<NoFeatureConfig> {
    // Special thanks to /r/l-ll-ll-l_IsDisLoss for allowing me to mimic his nether pyramid design!

    private final ResourceLocation START_POOL;
    public PyramidNetherStructure(Codec<NoFeatureConfig> config) {
        super(config);
        START_POOL = new ResourceLocation(RepurposedStructures.MODID + ":temples/pyramid_nether");
    }

    @Override
    public Structure.IStartFactory<NoFeatureConfig> getStartFactory() {
        return PyramidNetherStructure.Start::new;
    }

    public class Start extends AbstractNetherStructure.AbstractStart {
        public Start(Structure<NoFeatureConfig> structureIn, int chunkX, int chunkZ, MutableBoundingBox mutableBoundingBox, int referenceIn, long seedIn) {
            super(structureIn, chunkX, chunkZ, mutableBoundingBox, referenceIn, seedIn);
        }

        @Override
        public void init(DynamicRegistries dynamicRegistryManager, ChunkGenerator chunkGenerator, TemplateManager structureManager, int chunkX, int chunkZ, Biome biome, NoFeatureConfig NoFeatureConfig) {
            BlockPos blockpos = new BlockPos(chunkX * 16, 35, chunkZ * 16);
            GeneralJigsawGenerator.addPieces(dynamicRegistryManager, chunkGenerator, structureManager, blockpos, this.components, this.rand, dynamicRegistryManager.get(Registry.TEMPLATE_POOL_WORLDGEN).getOrDefault(START_POOL), 1);
            //PyramidFloorPiece.func_207617_a(structureManager, blockpos, this.components.get(0).getRotation(), this.components, random, Blocks.field_235406_np_, NoFeatureConfig);
            //this.components.get(1).getBoundingBox().encompass(this.components.get(0).getBoundingBox());
            this.recalculateStructureSize();

            BlockPos highestLandPos = getHighestLand(chunkGenerator);
            this.func_214626_a(this.rand, highestLandPos.getY()-16, highestLandPos.getY()-15);
        }
    }
}