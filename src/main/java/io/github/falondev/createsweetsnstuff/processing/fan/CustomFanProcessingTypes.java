package io.github.falondev.createsweetsnstuff.processing.fan;

import com.simibubi.create.AllRecipeTypes;
import com.simibubi.create.Create;
import com.simibubi.create.api.registry.CreateBuiltInRegistries;
import com.simibubi.create.api.registry.CreateRegistries;
import com.simibubi.create.content.kinetics.fan.processing.AllFanProcessingTypes;
import com.simibubi.create.content.kinetics.fan.processing.FanProcessingType;
import com.simibubi.create.foundation.recipe.RecipeApplier;
import io.github.falondev.createsweetsnstuff.CreateSweetsNStuff;
import io.github.falondev.createsweetsnstuff.tags.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.items.wrapper.RecipeWrapper;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.jetbrains.annotations.Nullable;

import java.util.*;

import static io.github.falondev.createsweetsnstuff.CreateSweetsNStuff.LOGGER;
import static io.github.falondev.createsweetsnstuff.CreateSweetsNStuff.MOD_ID;

public class CustomFanProcessingTypes {

    public static final DeferredRegister<FanProcessingType> FAN_PROCESSING_TYPE = DeferredRegister.create(CreateRegistries.FAN_PROCESSING_TYPE, MOD_ID);

    public static final DeferredHolder<FanProcessingType, FanProcessingType> CHILLING = FAN_PROCESSING_TYPE.register("chilling", ChillingType::new);

    public static void register(IEventBus eventBus) {
        FAN_PROCESSING_TYPE.register(eventBus);
    }

    public static class ChillingType implements FanProcessingType {


        @Override
        public boolean isValidAt(Level level, BlockPos pos) {
            return level.getBlockState(pos.below()).is(ModTags.Blocks.CHILLING_CATALYSTS);
        }

        @Override
        public int getPriority() {
            return 300;
        }

        @Override
        public boolean canProcess(ItemStack stack, Level level) {
            Optional<RecipeHolder<Recipe<SingleRecipeInput>>> recipe = ChillingRecipeTypes.CHILLING.find(new SingleRecipeInput(stack), level);
            if(recipe.isPresent()) {
                LOGGER.info("Found recipe for " + stack);
            } else {
                LOGGER.info("No recipe found for " + stack);
            }
            return recipe.isPresent();
        }

        @Override
        public @Nullable List<ItemStack> process(ItemStack stack, Level level) {
            Optional<RecipeHolder<Recipe<SingleRecipeInput>>> recipe = ChillingRecipeTypes.CHILLING.find(new SingleRecipeInput(stack), level);
            return recipe.map(recipeRecipeHolder -> RecipeApplier.applyRecipeOn(level, stack, recipeRecipeHolder)).orElse(null);
        }

        @Override
        public void spawnProcessingParticles(Level level, Vec3 pos) {
            level.addParticle(ParticleTypes.SNOWFLAKE, pos.x, pos.y + .25f, pos.z, 0, 1 / 16f, 0);
        }

        @Override
        public void morphAirFlow(AirFlowParticleAccess particleAccess, RandomSource random) {

        }

        @Override
        public void affectEntity(Entity entity, Level level) {

        }
    }
}
