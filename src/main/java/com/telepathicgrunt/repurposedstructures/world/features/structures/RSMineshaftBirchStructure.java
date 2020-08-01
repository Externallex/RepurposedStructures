package com.telepathicgrunt.repurposedstructures.world.features.structures;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.structure.Structure;


public class RSMineshaftBirchStructure extends AbstractMineshaftStructure {
    public RSMineshaftBirchStructure(Codec<NoFeatureConfig> config) {
        super(config);
    }

    public double getProbability() {
        return RepurposedStructures.RSMineshaftsConfig.birchMineshaftSpawnrate.get();
    }

    @Override
    public Structure.IStartFactory<NoFeatureConfig> getStartFactory() {
        return RSMineshaftBirchStructure.Start::new;
    }

    public static class Start extends AbstractStart{
        public Start(Structure<NoFeatureConfig> structureIn, int chunkX, int chunkZ, MutableBoundingBox mutableBoundingBox, int referenceIn, long seedIn) {
            super(structureIn, chunkX, chunkZ, mutableBoundingBox, referenceIn, seedIn);
        }

        @Override
        public RSMineshaftPieces.Type getMineshaftType() {
            return RSMineshaftPieces.Type.BIRCH;
        }
        @Override
        public int getMaxHeight() {
            return RepurposedStructures.RSMineshaftsConfig.birchMineshaftMaxHeight.get();
        }
        @Override
        public int getMinHeight() {
            return RepurposedStructures.RSMineshaftsConfig.birchMineshaftMinHeight.get();
        }

    }

}
