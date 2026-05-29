package com.hybridavenger.mtlasers.common.network.data;

import com.hybridavenger.mtlasers.common.MtLasers;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record OpenNodePayload(
        BlockPos sourcePos,
        byte side
) implements CustomPacketPayload {
    public static final Type<OpenNodePayload> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(MtLasers.MODID, "open_node"));

    @Override
    public Type<OpenNodePayload> type() {
        return TYPE;
    }

    public static final StreamCodec<FriendlyByteBuf, OpenNodePayload> STREAM_CODEC = StreamCodec.composite(
            BlockPos.STREAM_CODEC, OpenNodePayload::sourcePos,
            ByteBufCodecs.BYTE, OpenNodePayload::side,
            OpenNodePayload::new
    );
}
