package com.hybridavenger.mtlasers.util;

import com.hybridavenger.mtlasers.common.items.cards.BaseCard;
import com.hybridavenger.mtlasers.common.items.filters.BaseFilter;
import com.hybridavenger.mtlasers.common.items.upgrades.OverclockerCard;
import com.hybridavenger.mtlasers.common.items.upgrades.OverclockerNode;
import com.hybridavenger.mtlasers.setup.LaserIODataComponents;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.ComponentItemHandler;
import org.jetbrains.annotations.NotNull;

public class CardHolderItemStackHandler extends ComponentItemHandler {
    public CardHolderItemStackHandler(int size, ItemStack itemStack) {
        super(itemStack, LaserIODataComponents.ITEMSTACK_HANDLER.get(), size);
    }

    @Override
    public boolean isItemValid(int slot, @NotNull ItemStack stack) {
        if (stack.isEmpty()) return true;
        return (stack.getItem() instanceof BaseCard || stack.getItem() instanceof BaseFilter || stack.getItem() instanceof OverclockerCard || stack.getItem() instanceof OverclockerNode);
    }

    @Override
    public int getSlotLimit(int slot) {
        return 64;
    }
}
