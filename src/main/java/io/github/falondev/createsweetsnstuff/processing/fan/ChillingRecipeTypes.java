package io.github.falondev.createsweetsnstuff.processing.fan;

import com.simibubi.create.content.processing.recipe.ProcessingRecipe;
import com.simibubi.create.content.processing.recipe.ProcessingRecipeBuilder;
import com.simibubi.create.content.processing.recipe.ProcessingRecipeSerializer;
import com.simibubi.create.foundation.recipe.IRecipeTypeInfo;
import io.github.falondev.createsweetsnstuff.CreateSweetsNStuff;
import net.createmod.catnip.lang.Lang;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

import static io.github.falondev.createsweetsnstuff.CreateSweetsNStuff.MOD_ID;

public enum ChillingRecipeTypes implements IRecipeTypeInfo {
    CHILLING(ChillingRecipe::new)
    ;

    private static class Registers {
        private static final DeferredRegister<RecipeSerializer<?>> SERIALIZER_REGISTER = DeferredRegister.create(Registries.RECIPE_SERIALIZER, MOD_ID);
        private static final DeferredRegister<RecipeType<?>> TYPE_REGISTER = DeferredRegister.create(Registries.RECIPE_TYPE, MOD_ID);
    }

    private final ResourceLocation id;
    private final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<?>> serializer;
    private final Supplier<RecipeType<?>> type;

    ChillingRecipeTypes(ProcessingRecipeBuilder.ProcessingRecipeFactory<?> factory) {
        String name = Lang.asId(name());

        this.id = CreateSweetsNStuff.asResource(name);
        this.serializer = Registers.SERIALIZER_REGISTER.register(name, () -> new ProcessingRecipeSerializer<>(factory));

        DeferredHolder<RecipeType<?>, RecipeType<?>> typeObject = Registers.TYPE_REGISTER.register(name, () -> RecipeType.simple(id));
        type = typeObject;
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends RecipeSerializer<?>> T getSerializer() {
        return (T) serializer.get();
    }

    @SuppressWarnings("unchecked")
    @Override
    public <I extends RecipeInput, R extends Recipe<I>> RecipeType<R> getType() {
        return (RecipeType<R>) type.get();
    }

    public static void register(IEventBus eventBus) {
        Registers.SERIALIZER_REGISTER.register(eventBus);
        Registers.TYPE_REGISTER.register(eventBus);
    }
}
