package com.hybridavenger.mtlasers.common.containers.customhandler;

import com.hybridavenger.mtlasers.setup.LaserIODataComponents;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.ComponentItemHandler;

import javax.annotation.Nonnull;

public class FilterBasicHandler extends ComponentItemHandler {
    public ItemStack stack;

    public FilterBasicHandler(int size, ItemStack itemStack) {
        super(itemStack, LaserIODataComponents.ITEMSTACK_HANDLER.get(), size);
        this.stack = itemStack;
    }

    @Override
    public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
        return true;
    }

    @Override
    public int getSlotLimit(int slot) {
        return 1;
    }
}
