package com.hybridavenger.mtlasers.common.network.data;

import com.hybridavenger.mtlasers.common.MtLasers;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record UpdateFilterPayload(
        boolean allowList,
        boolean compareNBT
) implements CustomPacketPayload {
    public static final Type<UpdateFilterPayload> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(MtLasers.MODID, "update_filter"));

    @Override
    public Type<UpdateFilterPayload> type() {
        return TYPE;
    }

    public static final StreamCodec<ByteBuf, UpdateFilterPayload> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.BOOL, UpdateFilterPayload::allowList,
            ByteBufCodecs.BOOL, UpdateFilterPayload::compareNBT,
            UpdateFilterPayload::new
    );
}
