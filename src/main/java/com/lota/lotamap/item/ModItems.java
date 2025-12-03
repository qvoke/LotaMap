package com.lota.lotamap.item;

import com.lota.lotamap.LotaMap;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, LotaMap.MOD_ID);

    public static final RegistryObject<Item> LICH_PRISON_MAP = ITEMS.register("lich_prison_map_finder",
            () -> new LotaMapItem(new Item.Properties().stacksTo(1)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}