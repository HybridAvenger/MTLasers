package com.hybridavenger.mtlasers.common.network.data;

import com.hybridavenger.mtlasers.common.MtLasers;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record OpenCardPayload(
        int slotNumber,
        BlockPos sourcePos,
        boolean hasShiftDown
) implements CustomPacketPayload {
    public static final Type<OpenCardPayload> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(MtLasers.MODID, "open_card"));

    @Override
    public Type<OpenCardPayload> type() {
        return TYPE;
    }

    public static final StreamCodec<FriendlyByteBuf, OpenCardPayload> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.INT, OpenCardPayload::slotNumber,
            BlockPos.STREAM_CODEC, OpenCardPayload::sourcePos,
            ByteBufCodecs.BOOL, OpenCardPayload::hasShiftDown,
            OpenCardPayload::new
    );
}
