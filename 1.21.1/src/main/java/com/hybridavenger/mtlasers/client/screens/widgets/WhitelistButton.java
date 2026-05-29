package com.hybridavenger.mtlasers.client.screens.widgets;

import com.hybridavenger.mtlasers.common.MtLasers;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class WhitelistButton extends Button {
    private boolean isWhitelist;
    private ResourceLocation allow = ResourceLocation.fromNamespaceAndPath(MtLasers.MODID, "textures/gui/buttons/allowlisttrue.png");
    private ResourceLocation block = ResourceLocation.fromNamespaceAndPath(MtLasers.MODID, "textures/gui/buttons/allowlistfalse.png");

    public WhitelistButton(int widthIn, int heightIn, int width, int height, boolean isWhitelist, OnPress onPress) {
        super(widthIn, heightIn, width, height, Component.empty(), onPress, Button.DEFAULT_NARRATION);
        this.isWhitelist = isWhitelist;
    }

    @Override
    public void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        guiGraphics.blit(isWhitelist ? allow : block, this.getX(), this.getY(), 0, 0, 16, 16, 16, 16);
    }

    public void setWhitelist(boolean whitelist) {
        isWhitelist = whitelist;
    }
}
