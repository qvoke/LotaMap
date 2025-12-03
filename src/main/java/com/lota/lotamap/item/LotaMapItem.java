package com.lota.lotamap.item;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.MapItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.saveddata.maps.MapDecoration;
import net.minecraft.world.level.saveddata.maps.MapItemSavedData;

public class LotaMapItem extends Item {

    private static final TagKey<Structure> LICH_PRISON_TAG = TagKey.create(Registries.STRUCTURE,
            ResourceLocation.fromNamespaceAndPath("lotamap", "lich_prison_location"));

    public LotaMapItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);

        if (!pLevel.isClientSide && pLevel instanceof ServerLevel serverLevel) {

            BlockPos playerPos = pPlayer.blockPosition();

            BlockPos structurePos = serverLevel.findNearestMapStructure(LICH_PRISON_TAG, playerPos, 100, false);

            if (structurePos != null) {
                ItemStack mapStack = MapItem.create(pLevel, structurePos.getX(), structurePos.getZ(), (byte) 2, true, true);

                MapItem.renderBiomePreviewMap(serverLevel, mapStack);

                MapItemSavedData.addTargetDecoration(mapStack, structurePos, "+", MapDecoration.Type.TARGET_X);

                mapStack.setHoverName(Component.translatable("item.lotamap.lich_prison_map_filled"));

                return InteractionResultHolder.success(mapStack);

            } else {
                pPlayer.sendSystemMessage(Component.translatable("item.lotamap.lich_prison_map_not_founded"));
                return InteractionResultHolder.fail(itemstack);
            }
        }

        return InteractionResultHolder.pass(itemstack);
    }
}