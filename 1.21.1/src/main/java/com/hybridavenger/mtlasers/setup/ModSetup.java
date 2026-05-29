package com.hybridavenger.mtlasers.setup;

import com.hybridavenger.mtlasers.common.MtLasers;
import com.hybridavenger.mtlasers.common.events.ServerTickHandler;
import com.hybridavenger.mtlasers.common.items.cards.CardRedstone;
import com.hybridavenger.mtlasers.integration.mekanism.MekanismIntegration;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;


public class ModSetup {
    public static void init(final FMLCommonSetupEvent event) {
        NeoForge.EVENT_BUS.register(ServerTickHandler.class);
    }

    public static final String TAB_NAME = "mtlasers";
    public static DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MtLasers.MODID);
    public static DeferredHolder<CreativeModeTab, CreativeModeTab> TAB_MTLASERS = TABS.register(TAB_NAME, () -> CreativeModeTab.builder()
            .title(Component.literal("MassTech Lasers"))
            .icon(() -> new ItemStack(Registration.Laser_Wrench.get()))
            .withTabsBefore(CreativeModeTabs.SPAWN_EGGS)
            .displayItems((featureFlags, output) -> {
                Registration.ITEMS.getEntries().forEach(e -> {
                    Item item = e.get();
                    output.accept(item);
                    if (item instanceof CardRedstone) { //Doing it this way puts the Mekanism card after the redstone card.
                        if (MekanismIntegration.isLoaded()) {
                            Registration.ITEMS_MEKANISM.getEntries().forEach(f -> {
                                Item itemMek = f.get();
                                output.accept(itemMek);
                            });
                        }
                    }
                });
            })
            .build());
}
