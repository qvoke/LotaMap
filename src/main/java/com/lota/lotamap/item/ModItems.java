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

    // 1. Руины Каматха
    public static final RegistryObject<Item> KAMATH_RUINS_MAP = ITEMS.register("kamath_ruins_map_finder",
            () -> new LotaMapItem(new Item.Properties().stacksTo(1), "dungeons_and_combat:the_ruins_of_kamath", "kamath_ruins_map"));

    // 2. Пантеон Хранительницы Солнца
    public static final RegistryObject<Item> SUNLEIA_PANTHEON_MAP = ITEMS.register("sunleia_pantheon_map_finder",
            () -> new LotaMapItem(new Item.Properties().stacksTo(1), "dungeons_and_combat:the_pantheon_of_sunleia", "sunleia_pantheon_map"));

    // 3. Рыцарь Преисподней (Незер)
    public static final RegistryObject<Item> UNDERWORLD_ARENA_MAP = ITEMS.register("underworld_arena_map_finder",
            () -> new LotaMapItem(new Item.Properties().stacksTo(1), "block_factorys_bosses:underworld_arena", "underworld_arena_map"));

    // 4. Ледяной Лабиринт (Корабль)
    public static final RegistryObject<Item> ICE_MAZE_MAP = ITEMS.register("ice_maze_map_finder",
            () -> new LotaMapItem(new Item.Properties().stacksTo(1), "aquamirae:ship", "ice_maze_map"));

    // 5. Солнечный Храм
    public static final RegistryObject<Item> SUN_TEMPLE_MAP = ITEMS.register("sun_temple_map_finder",
            () -> new LotaMapItem(new Item.Properties().stacksTo(1), "call_of_yucutan:overgrown_temple", "sun_temple_map"));

    // 6. Смертельный склеп
    public static final RegistryObject<Item> DEADLY_CRYPT_MAP = ITEMS.register("deadly_crypt_map_finder",
            () -> new LotaMapItem(new Item.Properties().stacksTo(1), "call_of_yucutan:deadly_crypt", "deadly_crypt_map"));

    // 7. Храм Технологий прошлого
    public static final RegistryObject<Item> TEMPLE_OF_DUALITY_MAP = ITEMS.register("temple_of_duality_map_finder",
            () -> new LotaMapItem(new Item.Properties().stacksTo(1), "dungeonnowloading:temple_of_duality", "temple_of_duality_map"));

    // 8. Пустынное гнездо
    public static final RegistryObject<Item> SANDWORM_NEST_MAP = ITEMS.register("sandworm_nest_map_finder",
            () -> new LotaMapItem(new Item.Properties().stacksTo(1), "block_factorys_bosses:sandworm_nest", "sandworm_nest_map"));

    // 9. Руины Лаборатории (Незер)
    public static final RegistryObject<Item> LAB_RUINS_MAP = ITEMS.register("lab_ruins_map_finder",
            () -> new LotaMapItem(new Item.Properties().stacksTo(1), "incendium:pipeline", "lab_ruins_map"));

    // 10. Тюрма Лича
    public static final RegistryObject<Item> LICH_PRISON_MAP = ITEMS.register("lich_prison_map_finder",
            () -> new LotaMapItem(new Item.Properties().stacksTo(1), "graveyard:lich_prison", "lich_prison_map"));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}