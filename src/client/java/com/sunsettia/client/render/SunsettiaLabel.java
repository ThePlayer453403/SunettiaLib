package com.sunsettia.client.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.network.chat.Component;
import org.joml.Vector2f;

public class SunsettiaLabel {
    private final GuiGraphicsExtractor context;
    private final Font textRenderer;
    private final Component text;
    private final Vector2f position;
    private Vector2f scale = new Vector2f(1, 1);
    private Component content;
    private int color = 0xffffffff;
    private float rotation = 0;
    private boolean hasOutline = true;
    private boolean centered = false;
    private float outline = 0.2f;
    private float outlineDarken = 0.4f;

    public SunsettiaLabel(GuiGraphicsExtractor context, Font textRenderer, Component text, float positionX, float positionY) {
        this.context = context;
        this.textRenderer = textRenderer;
        this.text = text;
        this.content = text;
        this.position = new Vector2f(positionX, positionY);
    }

    public SunsettiaLabel(GuiGraphicsExtractor context, Component text, float positionX, float positionY) {
        this.context = context;
        this.textRenderer = Minecraft.getInstance().font;
        this.text = text;
        this.content = text;
        this.position = new Vector2f(positionX, positionY);
    }

    public SunsettiaLabel(GuiGraphicsExtractor context, Font textRenderer, Component text, Vector2f position) {
        this.context = context;
        this.textRenderer = textRenderer;
        this.text = text;
        this.content = text;
        this.position = position;
    }

    public SunsettiaLabel(GuiGraphicsExtractor context, Component text, Vector2f position) {
        this.context = context;
        this.textRenderer = Minecraft.getInstance().font;
        this.text = text;
        this.content = text;
        this.position = position;
    }

    public SunsettiaLabel(GuiGraphicsExtractor context, Font textRenderer, String text, float positionX, float positionY) {
        this.context = context;
        this.textRenderer = textRenderer;
        this.text = Component.literal(text);
        this.content = Component.literal(text);
        this.position = new Vector2f(positionX, positionY);
    }

    public SunsettiaLabel(GuiGraphicsExtractor context, String text, float positionX, float positionY) {
        this.context = context;
        this.textRenderer = Minecraft.getInstance().font;
        this.text = Component.literal(text);
        this.content = Component.literal(text);
        this.position = new Vector2f(positionX, positionY);
    }

    public SunsettiaLabel(GuiGraphicsExtractor context, Font textRenderer, String text, Vector2f position) {
        this.context = context;
        this.textRenderer = textRenderer;
        this.text = Component.literal(text);
        this.content = Component.literal(text);
        this.position = position;
    }

    public SunsettiaLabel(GuiGraphicsExtractor context, String text, Vector2f position) {
        this.context = context;
        this.textRenderer = Minecraft.getInstance().font;
        this.text = Component.literal(text);
        this.content = Component.literal(text);
        this.position = position;
    }

    public SunsettiaLabel scale(Vector2f scale) {
        this.scale = scale;
        return this;
    }

    public SunsettiaLabel scale(float scale) {
        this.scale = new Vector2f(scale, scale);
        return this;
    }

    public SunsettiaLabel scale(float scaleX, float scaleY) {
        this.scale = new Vector2f(scaleX, scaleY);
        return this;
    }

    public SunsettiaLabel color(int color) {
        this.color = color;
        return this;
    }

    public SunsettiaLabel rotate(float rotation) {
        this.rotation = (float) Math.toRadians(rotation);
        return this;
    }

    public SunsettiaLabel rotateRad(float rotation) {
        this.rotation = rotation;
        return this;
    }

    public SunsettiaLabel outline(boolean outline) {
        this.hasOutline = outline;
        return this;
    }

    public SunsettiaLabel outlineWidth(float width) {
        this.outline = width;
        return this;
    }

    public SunsettiaLabel outlineDarken(float darken) {
        this.outlineDarken = darken;
        return this;
    }

    public SunsettiaLabel center() {
        this.centered = true;
        return this;
    }

    public SunsettiaLabel center(Component text) {
        this.centered = true;
        this.content = text;
        return this;
    }

    public void render() {
        if (scale.x() <= 0.0F || scale.y() <= 0.0F) return;
        if (centered) {
            position.x = position.x - (width(content) * scale.x) / 2f;
        }
        context.pose().pushMatrix();
        context.pose().translate(position);
        if (hasOutline) {
            int outline_color = ((color >> 24) & 0xFF << 24) | ((int) Math.max(0, ((color >> 16) & 0xFF) * outlineDarken) << 16) | ((int) Math.max(0, ((color >> 8) & 0xFF) * outlineDarken) << 8) | (int) Math.max(0, (color & 0xFF) * outlineDarken);
            render(new Vector2f(outline, outline), outline_color);
            render(new Vector2f(outline, -outline), outline_color);
            render(new Vector2f(outline, 0), outline_color);
            render(new Vector2f(-outline, outline), outline_color);
            render(new Vector2f(-outline, -outline), outline_color);
            render(new Vector2f(-outline, 0), outline_color);
            render(new Vector2f(0, outline), outline_color);
            render(new Vector2f(0, -outline), outline_color);
        }
        render(new Vector2f(0, 0), color);
        context.pose().popMatrix();
    }

    private void render(Vector2f offset, int color) {
        context.pose().translate(offset);
        context.pose().scale(scale);
        context.pose().rotate(rotation);
        context.text(textRenderer, text, 0, 0, color, false);
        context.pose().translate(-offset.x, -offset.y);
        context.pose().scale(1f / scale.x, 1f / scale.y);
        context.pose().rotate(-rotation);
    }

    public static int width(Component value) {
        return Minecraft.getInstance().font.width(value);
    }
}
