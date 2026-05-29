package com.hybridavenger.mtlasers.common.network.data;

import com.hybridavenger.mtlasers.common.MtLasers;
import com.hybridavenger.mtlasers.integration.mekanism.client.chemicalparticle.ParticleDataChemical;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

import java.util.List;

public record NodeParticlesChemicalPayload(
        List<ParticleDataChemical> particleList
) implements CustomPacketPayload {
    public static final Type<NodeParticlesChemicalPayload> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(MtLasers.MODID, "node_particles_chemical"));

    @Override
    public Type<NodeParticlesChemicalPayload> type() {
        return TYPE;
    }

    public static final StreamCodec<RegistryFriendlyByteBuf, NodeParticlesChemicalPayload> STREAM_CODEC = StreamCodec.composite(
            ParticleDataChemical.STREAM_CODEC.apply(ByteBufCodecs.list()), NodeParticlesChemicalPayload::particleList,
            NodeParticlesChemicalPayload::new
    );
}
