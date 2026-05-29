package com.hybridavenger.mtlasers.client.blockentityrenders;

import com.hybridavenger.mtlasers.client.blockentityrenders.baseberender.BaseLaserBERender;
import com.hybridavenger.mtlasers.common.blockentities.LaserConnectorBE;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;

public class LaserConnectorBERender extends BaseLaserBERender<LaserConnectorBE> {
    public LaserConnectorBERender(BlockEntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public void render(LaserConnectorBE blockentity, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int combinedLightsIn, int combinedOverlayIn) {
        super.render(blockentity, partialTicks, matrixStackIn, bufferIn, combinedLightsIn, combinedOverlayIn);
    }

}
