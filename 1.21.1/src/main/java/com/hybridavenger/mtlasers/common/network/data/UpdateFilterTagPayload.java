package com.hybridavenger.mtlasers.common.network.data;

import com.hybridavenger.mtlasers.common.MtLasers;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

import java.util.List;

public record UpdateFilterTagPayload(
        boolean allowList,
        List<String> tags
) implements CustomPacketPayload {
    public static final Type<UpdateFilterTagPayload> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(MtLasers.MODID, "update_filter_tag"));

    @Override
    public Type<UpdateFilterTagPayload> type() {
        return TYPE;
    }

    public static final StreamCodec<FriendlyByteBuf, UpdateFilterTagPayload> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.BOOL, UpdateFilterTagPayload::allowList,
            ByteBufCodecs.STRING_UTF8.apply(ByteBufCodecs.list()), UpdateFilterTagPayload::tags,
            UpdateFilterTagPayload::new
    );
}
