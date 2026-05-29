package com.hybridavenger.mtlasers.common.network.data;

import com.hybridavenger.mtlasers.common.MtLasers;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record ToggleParticlesPayload(
        boolean renderParticles
) implements CustomPacketPayload {
    public static final Type<ToggleParticlesPayload> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(MtLasers.MODID, "toggle_particles"));

    @Override
    public Type<ToggleParticlesPayload> type() {
        return TYPE;
    }

    public static final StreamCodec<FriendlyByteBuf, ToggleParticlesPayload> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.BOOL, ToggleParticlesPayload::renderParticles,
            ToggleParticlesPayload::new
    );
}
