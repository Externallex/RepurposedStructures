package com.telepathicgrunt.repurposedstructures.biomeinjection;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructureTagMap;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import com.telepathicgrunt.repurposedstructures.utils.BiomeSelection;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraftforge.event.world.BiomeLoadingEvent;

import java.util.function.Supplier;

public class Mineshafts {

    public static void addMineshafts(BiomeLoadingEvent event) {

        if (RepurposedStructures.RSMineshaftsConfig.birchMineshaftSpawnrate.get() != 0 &&
            genericMineshaftCheck(event, RSStructures.MINESHAFT_BIRCH.get(),
                    () -> BiomeSelection.hasName(event, "birch")))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.MINESHAFT_BIRCH);
        }

        if (RepurposedStructures.RSMineshaftsConfig.jungleMineshaftSpawnrate.get() != 0 &&
            genericMineshaftCheck(event, RSStructures.MINESHAFT_JUNGLE.get(),
                    () -> BiomeSelection.haveCategories(event, Category.JUNGLE)))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.MINESHAFT_JUNGLE);
        }

        if (RepurposedStructures.RSMineshaftsConfig.desertMineshaftSpawnrate.get() != 0 &&
            genericMineshaftCheck(event, RSStructures.MINESHAFT_DESERT.get(),
                    () -> BiomeSelection.haveCategories(event, Category.DESERT)))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.MINESHAFT_DESERT);
        }

        if (RepurposedStructures.RSMineshaftsConfig.stoneMineshaftSpawnrate.get() != 0 &&
            genericMineshaftCheck(event, RSStructures.MINESHAFT_STONE.get(),
                    () -> BiomeSelection.haveCategories(event, Category.EXTREME_HILLS)))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.MINESHAFT_STONE);
        }

        if (RepurposedStructures.RSMineshaftsConfig.savannaMineshaftSpawnrate.get() != 0 &&
            genericMineshaftCheck(event, RSStructures.MINESHAFT_SAVANNA.get(),
                    () -> BiomeSelection.haveCategories(event, Category.SAVANNA)))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.MINESHAFT_SAVANNA);
        }

        if (RepurposedStructures.RSMineshaftsConfig.icyMineshaftSpawnrate.get() != 0 &&
            genericMineshaftCheck(event, RSStructures.MINESHAFT_ICY.get(),
                    () -> BiomeSelection.haveCategories(event, Category.ICY) ||
                    BiomeSelection.hasName(event, "snowy")))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.MINESHAFT_ICY);
        }

        if (RepurposedStructures.RSMineshaftsConfig.oceanMineshaftSpawnrate.get() != 0 &&
            genericMineshaftCheck(event, RSStructures.MINESHAFT_OCEAN.get(),
                    () -> BiomeSelection.haveCategories(event, Category.OCEAN)))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.MINESHAFT_OCEAN);
        }

        if (RepurposedStructures.RSMineshaftsConfig.taigaMineshaftSpawnrate.get() != 0 &&
            genericMineshaftCheck(event, RSStructures.MINESHAFT_TAIGA.get(),
                    () -> BiomeSelection.haveCategories(event, Category.TAIGA)))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.MINESHAFT_TAIGA);
        }

        if (RepurposedStructures.RSMineshaftsConfig.darkForestMineshaftSpawnrate.get() != 0 &&
            genericMineshaftCheck(event, RSStructures.MINESHAFT_DARK_FOREST.get(),
                    () -> BiomeSelection.haveCategories(event, Category.FOREST) &&
                    BiomeSelection.hasName(event, "dark", "spooky", "dead", "haunted")))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.MINESHAFT_DARK_FOREST);
        }

        if (RepurposedStructures.RSMineshaftsConfig.swampMineshaftSpawnrate.get() != 0 &&
            genericMineshaftCheck(event, RSStructures.MINESHAFT_SWAMP.get(),
                    () -> BiomeSelection.haveCategories(event, Category.SWAMP)))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.MINESHAFT_SWAMP);
        }
        if (RepurposedStructures.RSMineshaftsConfig.netherMineshaftSpawnrate.get() != 0 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.MINESHAFT_CRIMSON.get(),
                    () -> BiomeSelection.haveCategories(event, Category.NETHER) &&
                    BiomeSelection.hasName(event, "crimson", "_red")))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.MINESHAFT_CRIMSON);
        }

        if (RepurposedStructures.RSMineshaftsConfig.netherMineshaftSpawnrate.get() != 0 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.MINESHAFT_WARPED.get(),
                    () -> BiomeSelection.haveCategories(event, Category.NETHER) &&
                    BiomeSelection.hasName(event, "warped", "blue")))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.MINESHAFT_WARPED);
        }

        if (RepurposedStructures.RSMineshaftsConfig.netherMineshaftSpawnrate.get() != 0 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.MINESHAFT_NETHER.get(),
                    () -> BiomeSelection.haveCategories(event, Category.NETHER) &&
                    !BiomeSelection.hasName(event, "crimson", "_red", "warped", "blue")))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.MINESHAFT_NETHER);
        }

        if (RepurposedStructures.RSMineshaftsConfig.endMineshaftSpawnrate.get() != 0 &&
                BiomeSelection.isBiomeAllowed(event, RSStructures.MINESHAFT_END.get(),
                        () -> BiomeSelection.haveCategories(event, Category.THEEND) && !BiomeSelection.isBiome(event, Biomes.THE_END) &&
                        (RepurposedStructures.RSMineshaftsConfig.endMineshaftMinIslandThickness.get() == 0 || !BiomeSelection.isBiome(event, Biomes.END_BARRENS, Biomes.SMALL_END_ISLANDS))))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.MINESHAFT_END);
        }

        if(RepurposedStructures.yungsBetterMineshaftIsNotOn && BiomeSelection.doesHaveStructureType(event, RSStructureTagMap.STRUCTURE_TAGS.MINESHAFT)){
            event.getGeneration().getStructures().removeIf((supplier) -> supplier.get().feature == Structure.MINESHAFT);
        }
    }

    private static boolean genericMineshaftCheck(BiomeLoadingEvent context, Structure<?> structureFeature, Supplier<Boolean> condition) {
        return BiomeSelection.isBiomeAllowed(context, structureFeature,
                () -> BiomeSelection.doesNotHaveStructureType(context, RSStructureTagMap.STRUCTURE_TAGS.MINESHAFT) && condition.get());
    }
}
