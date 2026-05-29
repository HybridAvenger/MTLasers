package com.hybridavenger.mtlasers.common.network.handler;

import com.hybridavenger.mtlasers.common.containers.LaserNodeContainer;
import com.hybridavenger.mtlasers.common.network.data.ToggleParticlesPayload;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class PacketToggleParticles {
    public static final PacketToggleParticles INSTANCE = new PacketToggleParticles();

    public static PacketToggleParticles get() {
        return INSTANCE;
    }

    public void handle(final ToggleParticlesPayload payload, final IPayloadContext context) {
        context.enqueueWork(() -> {
            Player sender = context.player();

            AbstractContainerMenu container = sender.containerMenu;
            if (container == null)
                return;

            if (container instanceof LaserNodeContainer laserNodeContainer) {
                laserNodeContainer.tile.setShowParticles(payload.renderParticles());
            }
        });
    }
}
