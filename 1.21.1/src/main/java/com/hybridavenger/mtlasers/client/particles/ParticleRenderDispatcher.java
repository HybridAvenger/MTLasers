package com.hybridavenger.mtlasers.client.particles;

import com.hybridavenger.mtlasers.client.particles.fluidparticle.FluidFlowParticle;
import com.hybridavenger.mtlasers.client.particles.itemparticle.ItemFlowParticle;
import com.hybridavenger.mtlasers.common.MtLasers;
import com.hybridavenger.mtlasers.integration.mekanism.MekanismIntegration;
import com.hybridavenger.mtlasers.integration.mekanism.client.chemicalparticle.ChemicalFlowParticle;
import com.hybridavenger.mtlasers.integration.mekanism.client.chemicalparticle.MekanismModParticles;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;


@EventBusSubscriber(modid = MtLasers.MODID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
public class ParticleRenderDispatcher {

    @SubscribeEvent
    public static void registerProviders(RegisterParticleProvidersEvent evt) {
        evt.registerSpecial(ModParticles.ITEMFLOWPARTICLE.get(), ItemFlowParticle.FACTORY);
        evt.registerSpecial(ModParticles.FLUIDFLOWPARTICLE.get(), FluidFlowParticle.FACTORY);
        if (MekanismIntegration.isLoaded()) {
            evt.registerSpecial(MekanismModParticles.CHEMICALFLOWPARTICLE.get(), ChemicalFlowParticle.FACTORY);
        }
    }
}
