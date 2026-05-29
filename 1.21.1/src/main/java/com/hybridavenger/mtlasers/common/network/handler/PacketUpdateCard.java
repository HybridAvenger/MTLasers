package com.hybridavenger.mtlasers.common.network.handler;

import com.hybridavenger.mtlasers.common.containers.CardEnergyContainer;
import com.hybridavenger.mtlasers.common.containers.CardItemContainer;
import com.hybridavenger.mtlasers.common.items.cards.BaseCard;
import com.hybridavenger.mtlasers.common.items.cards.CardEnergy;
import com.hybridavenger.mtlasers.common.items.cards.CardFluid;
import com.hybridavenger.mtlasers.common.items.cards.CardItem;
import com.hybridavenger.mtlasers.common.network.data.UpdateCardPayload;
import com.hybridavenger.mtlasers.integration.mekanism.CardChemical;
import com.hybridavenger.mtlasers.setup.Config;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class PacketUpdateCard {
    public static final PacketUpdateCard INSTANCE = new PacketUpdateCard();

    public static PacketUpdateCard get() {
        return INSTANCE;
    }

    public void handle(final UpdateCardPayload payload, final IPayloadContext context) {
        context.enqueueWork(() -> {
            Player sender = context.player();

            AbstractContainerMenu container = sender.containerMenu;
            if (container == null)
                return;

            if (container instanceof CardItemContainer || container instanceof CardEnergyContainer) {
                ItemStack stack;
                if (container instanceof CardEnergyContainer)
                    stack = ((CardEnergyContainer) container).cardItem;
                else
                    stack = ((CardItemContainer) container).cardItem;
                BaseCard.setTransferMode(stack, payload.mode());
                BaseCard.setChannel(stack, payload.channel());
                int extractAmt = payload.extractAmt();
                int overClockerCount = 0;
                if (stack.getItem() instanceof CardItem) {
                    overClockerCount = container.getSlot(1).getItem().getCount();
                    if (extractAmt > Math.max(overClockerCount * 16, 8)) {
                        extractAmt = (byte) Math.max(overClockerCount * 16, 8);
                    }
                    CardItem.setItemExtractAmt(stack, (byte) extractAmt);
                    short ticks = payload.ticks();
                    if (ticks < Math.max(20 - overClockerCount * 5, 1))
                        ticks = (short) Math.max(20 - overClockerCount * 5, 1);
                    BaseCard.setExtractSpeed(stack, ticks);
                } else if (stack.getItem() instanceof CardFluid) {
                    overClockerCount = container.getSlot(1).getItem().getCount();
                    if (extractAmt > Math.max(overClockerCount * Config.MULTIPLIER_MILLI_BUCKETS_FLUID.get(), Config.BASE_MILLI_BUCKETS_FLUID.get())) {
                        extractAmt = Math.max(overClockerCount * Config.MULTIPLIER_MILLI_BUCKETS_FLUID.get(), Config.BASE_MILLI_BUCKETS_FLUID.get());
                    }
                    CardFluid.setFluidExtractAmt(stack, extractAmt);
                    short ticks = payload.ticks();
                    if (ticks < Math.max(20 - overClockerCount * 5, 1))
                        ticks = (short) Math.max(20 - overClockerCount * 5, 1);
                    BaseCard.setExtractSpeed(stack, ticks);
                } else if (stack.getItem() instanceof CardEnergy) {
                    int max = Config.MAX_FE_TICK.get();
                    if (extractAmt > max) {
                        extractAmt = max;
                    }
                    CardEnergy.setEnergyExtractAmt(stack, extractAmt);
                    short ticks = payload.ticks();
                    if (ticks < 1)
                        ticks = (short) 1;
                    CardEnergy.setExtractSpeed(stack, ticks);
                    CardEnergy.setExtractLimitPercent(stack, payload.extractLimit());
                    CardEnergy.setInsertLimitPercent(stack, payload.insertLimit());
                } else if (stack.getItem() instanceof CardChemical) {
                    overClockerCount = container.getSlot(1).getItem().getCount();
                    if (extractAmt > Math.max(overClockerCount * Config.MULTIPLIER_MILLI_BUCKETS_CHEMICAL.get(), Config.BASE_MILLI_BUCKETS_CHEMICAL.get())) {
                        extractAmt = Math.max(overClockerCount * Config.MULTIPLIER_MILLI_BUCKETS_CHEMICAL.get(), Config.BASE_MILLI_BUCKETS_CHEMICAL.get());
                    }
                    CardChemical.setChemicalExtractAmt(stack, extractAmt);
                    short ticks = payload.ticks();
                    if (ticks < Math.max(20 - overClockerCount * 5, 1))
                        ticks = (short) Math.max(20 - overClockerCount * 5, 1);
                    BaseCard.setExtractSpeed(stack, ticks);
                }
                BaseCard.setPriority(stack, payload.priority());
                BaseCard.setSneaky(stack, payload.sneaky());
                BaseCard.setExact(stack, payload.exact());
                BaseCard.setRoundRobin(stack, payload.roundRobin());
                BaseCard.setRegulate(stack, payload.regulate());
                BaseCard.setRedstoneMode(stack, payload.redstoneMode());
                BaseCard.setRedstoneChannel(stack, payload.redstoneChannel());
                BaseCard.setAnd(stack, payload.andMode());
            }
        });
    }
}
