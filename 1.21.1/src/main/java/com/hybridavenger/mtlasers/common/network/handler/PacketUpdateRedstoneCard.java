package com.hybridavenger.mtlasers.common.network.handler;

import com.hybridavenger.mtlasers.common.containers.CardRedstoneContainer;
import com.hybridavenger.mtlasers.common.items.cards.CardRedstone;
import com.hybridavenger.mtlasers.common.network.data.UpdateRedstoneCardPayload;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class PacketUpdateRedstoneCard {
    public static final PacketUpdateRedstoneCard INSTANCE = new PacketUpdateRedstoneCard();

    public static PacketUpdateRedstoneCard get() {
        return INSTANCE;
    }

    public void handle(final UpdateRedstoneCardPayload payload, final IPayloadContext context) {
        context.enqueueWork(() -> {
            Player sender = context.player();

            AbstractContainerMenu container = sender.containerMenu;
            if (container == null)
                return;

            if (!(container instanceof CardRedstoneContainer))
                return;

            ItemStack stack;
            stack = ((CardRedstoneContainer) container).cardItem;
            CardRedstone.setTransferMode(stack, payload.mode());
            CardRedstone.setRedstoneChannel(stack, payload.channel());
            CardRedstone.setStrong(stack, payload.strong());
        });
    }
}
