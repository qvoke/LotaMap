package com.lota.lotamap.item;

import com.mojang.datafixers.util.Pair;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
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

import java.util.Optional;

public class LotaMapItem extends Item {

    private final String structureId;
    private final String translationKeyBase;

    public LotaMapItem(Properties pProperties, String structureId, String translationKeyBase) {
        super(pProperties);
        this.structureId = structureId;
        this.translationKeyBase = translationKeyBase;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);

        if (!pLevel.isClientSide && pLevel instanceof ServerLevel serverLevel) {

            BlockPos playerPos = pPlayer.blockPosition();

            var structureRegistry = serverLevel.registryAccess().registryOrThrow(Registries.STRUCTURE);
            ResourceLocation structRL = ResourceLocation.parse(this.structureId);
            ResourceKey<Structure> structKey = ResourceKey.create(Registries.STRUCTURE, structRL);

            Optional<Holder.Reference<Structure>> structureHolder = structureRegistry.getHolder(structKey);

            if (structureHolder.isEmpty()) {
                pPlayer.sendSystemMessage(Component.translatable("item.lotamap.structure_missing"));
                return InteractionResultHolder.fail(itemstack);
            }

            Pair<BlockPos, Holder<Structure>> pair = serverLevel.getChunkSource().getGenerator()
                    .findNearestMapStructure(serverLevel, HolderSet.direct(structureHolder.get()), playerPos, 100, false);

            if (pair != null) {
                BlockPos structurePos = pair.getFirst();

                ItemStack mapStack = MapItem.create(pLevel, structurePos.getX(), structurePos.getZ(), (byte) 2, true, true);

                MapItem.renderBiomePreviewMap(serverLevel, mapStack);

                MapItemSavedData.addTargetDecoration(mapStack, structurePos, "+", MapDecoration.Type.TARGET_X);

                mapStack.setHoverName(Component.translatable("item.lotamap." + this.translationKeyBase + "_filled"));

                if (!pPlayer.getAbilities().instabuild) {
                    itemstack.shrink(1);
                }

                if (itemstack.isEmpty()) {
                    return InteractionResultHolder.success(mapStack);
                }
                if (!pPlayer.getInventory().add(mapStack)) {
                    pPlayer.drop(mapStack, false);
                }

                return InteractionResultHolder.success(itemstack);

            } else {
                pPlayer.sendSystemMessage(Component.translatable("item.lotamap." + this.translationKeyBase + "_not_founded"));
                return InteractionResultHolder.fail(itemstack);
            }
        }

        return InteractionResultHolder.pass(itemstack);
    }
}