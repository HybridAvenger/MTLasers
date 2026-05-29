package com.hybridavenger.mtlasers.datagen;

import com.hybridavenger.mtlasers.common.MtLasers;
import com.hybridavenger.mtlasers.setup.Registration;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.loot.packs.VanillaBlockLoot;
import net.minecraft.world.level.block.Block;

import java.util.Map;
import java.util.stream.Collectors;

public class LaserIOLootTable extends VanillaBlockLoot {

    public LaserIOLootTable(HolderLookup.Provider p_344962_) {
        super(p_344962_);
    }

    @Override
    protected void generate() {
        dropSelf(Registration.LaserNode.get());
        dropSelf(Registration.LaserConnector.get());
        dropSelf(Registration.LaserConnectorAdv.get());
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return BuiltInRegistries.BLOCK.entrySet().stream()
                .filter(e -> e.getKey().location().getNamespace().equals(MtLasers.MODID))
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }
}