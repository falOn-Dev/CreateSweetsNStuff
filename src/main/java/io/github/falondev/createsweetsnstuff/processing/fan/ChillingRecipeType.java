package io.github.falondev.createsweetsnstuff.processing.fan;

import com.simibubi.create.Create;
import com.simibubi.create.content.processing.recipe.ProcessingRecipeSerializer;
import io.github.falondev.createsweetsnstuff.CreateSweetsNStuff;
import io.github.falondev.createsweetsnstuff.util.RecipeTypeInfo;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Optional;

import static io.github.falondev.createsweetsnstuff.CreateSweetsNStuff.MOD_ID;

public class ChillingRecipeType {
    public static final String CHILLING = "chilling";
    public static final ResourceLocation CHILLING_ID = CreateSweetsNStuff.asResource(CHILLING);

    private static final DeferredRegister<RecipeSerializer<?>> SERIALIZER_REGISTER = DeferredRegister.create(Registries.RECIPE_SERIALIZER, MOD_ID);
    private static final DeferredRegister<RecipeType<?>> TYPE_REGISTER = DeferredRegister.create(Registries.RECIPE_TYPE, MOD_ID);

    private static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<ChillingRecipe>> CHILLING_SERIALIZER =
            SERIALIZER_REGISTER.register(CHILLING, () -> new ProcessingRecipeSerializer<>(ChillingRecipe::new));

    private static final DeferredHolder<RecipeType<?>, RecipeType<ChillingRecipe>> CHILLING_TYPE =
            TYPE_REGISTER.register(CHILLING, () -> RecipeType.simple(CHILLING_ID));

    public static final RecipeTypeInfo CHILLING_TYPE_INFO = new RecipeTypeInfo(CHILLING_ID, CHILLING_SERIALIZER::get, CHILLING_TYPE::get);


    public static void register(IEventBus modEventBus) {
        SERIALIZER_REGISTER.register(modEventBus);
        TYPE_REGISTER.register(modEventBus);
    }

    @SuppressWarnings("unchecked")
    public static <I extends RecipeInput, R extends Recipe<I>> RecipeType<R> getType() {
        return (RecipeType<R>) CHILLING_TYPE.get();
    }


    public static <I extends RecipeInput, R extends Recipe<I>> Optional<RecipeHolder<R>> find(I inv, Level world) {
        return world.getRecipeManager()
                .getRecipeFor(getType(), inv, world);
    }
}
