package midnight.data.recipes;

import midnight.common.Midnight;
import midnight.common.block.MnBlocks;
import net.minecraft.data.*;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.Tag;
import net.minecraft.util.IItemProvider;

import java.util.function.Consumer;

public class MnRecipeProvider extends RecipeProvider {
    private Consumer<IFinishedRecipe> consumer;

    public MnRecipeProvider(DataGenerator gen) {
        super(gen);
    }

    @Override
    protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {
        this.consumer = consumer;

        generic4x4("dead_wood_4x4", MnBlocks.DEAD_WOOD_LOG, MnBlocks.DEAD_WOOD, 3);
        generic4x4("stripped_dead_wood_4x4", MnBlocks.STRIPPED_DEAD_WOOD_LOG, MnBlocks.STRIPPED_DEAD_WOOD, 3);
        generic4x4("dead_wood_planks_from_wood_4x4", MnBlocks.DEAD_WOOD, MnBlocks.DEAD_WOOD_PLANKS, 4);
        generic4x4("dead_wood_planks_from_log_4x4", MnBlocks.DEAD_WOOD_LOG, MnBlocks.DEAD_WOOD_PLANKS, 4);
        generic4x4("dead_wood_planks_from_stripped_wood_4x4", MnBlocks.STRIPPED_DEAD_WOOD, MnBlocks.DEAD_WOOD_PLANKS, 4);
        generic4x4("dead_wood_planks_from_stripped_log_4x4", MnBlocks.STRIPPED_DEAD_WOOD_LOG, MnBlocks.DEAD_WOOD_PLANKS, 4);

        generic4x4("shadowroot_wood_4x4", MnBlocks.SHADOWROOT_LOG, MnBlocks.SHADOWROOT, 3);
        generic4x4("stripped_shadowroot_wood_4x4", MnBlocks.STRIPPED_SHADOWROOT_LOG, MnBlocks.STRIPPED_SHADOWROOT, 3);
        generic4x4("shadowroot_wood_planks_from_wood_4x4", MnBlocks.SHADOWROOT, MnBlocks.SHADOWROOT_PLANKS, 4);
        generic4x4("shadowroot_wood_planks_from_log_4x4", MnBlocks.SHADOWROOT_LOG, MnBlocks.SHADOWROOT_PLANKS, 4);
        generic4x4("shadowroot_wood_planks_from_stripped_wood_4x4", MnBlocks.STRIPPED_SHADOWROOT, MnBlocks.SHADOWROOT_PLANKS, 4);
        generic4x4("shadowroot_wood_planks_from_stripped_log_4x4", MnBlocks.STRIPPED_SHADOWROOT_LOG, MnBlocks.SHADOWROOT_PLANKS, 4);
    }

    private void generic4x4(String id, IItemProvider from, IItemProvider to, int count) {
        ShapedRecipeBuilder.shapedRecipe(to, count)
                           .key('#', from)
                           .patternLine("##")
                           .patternLine("##")
                           .addCriterion("has_ingredient", hasItem(from))
                           .build(consumer, Midnight.resLoc(id));
    }

    private void shapeless(String id, IItemProvider from, IItemProvider to, int count) {
        ShapelessRecipeBuilder.shapelessRecipe(to, count)
                              .addIngredient(from)
                              .addCriterion("has_ingredient", hasItem(from))
                              .build(consumer, Midnight.resLoc(id));
    }

    private void shapeless(String id, Tag<Item> tag, IItemProvider to, int count) {
        ShapelessRecipeBuilder.shapelessRecipe(to, count)
                              .addIngredient(tag)
                              .addCriterion("has_ingredient", hasItem(tag))
                              .build(consumer, Midnight.resLoc(id));
    }

    private void fence(String id, IItemProvider from, IItemProvider to, int count) {
        ShapedRecipeBuilder.shapedRecipe(to, count)
                           .key('#', from)
                           .key('/', Items.STICK)
                           .patternLine("#/#")
                           .patternLine("#/#")
                           .addCriterion("has_ingredient", hasItem(from))
                           .build(consumer, Midnight.resLoc(id));
    }

    private void generic1x2(String id, IItemProvider from, IItemProvider to, int count) {
        ShapedRecipeBuilder.shapedRecipe(to, count)
                           .key('#', from)
                           .patternLine("#")
                           .patternLine("#")
                           .addCriterion("has_ingredient", hasItem(from))
                           .build(consumer, Midnight.resLoc(id));
    }

    private void generic1x3(String id, IItemProvider from, IItemProvider to, int count) {
        ShapedRecipeBuilder.shapedRecipe(to, count)
                           .key('#', from)
                           .patternLine("#")
                           .patternLine("#")
                           .patternLine("#")
                           .addCriterion("has_ingredient", hasItem(from))
                           .build(consumer, Midnight.resLoc(id));
    }

    private void generic3x1(String id, IItemProvider from, IItemProvider to, int count) {
        ShapedRecipeBuilder.shapedRecipe(to, count)
                           .key('#', from)
                           .patternLine("###")
                           .addCriterion("has_ingredient", hasItem(from))
                           .build(consumer, Midnight.resLoc(id));
    }

    private void generic3x2(String id, IItemProvider from, IItemProvider to, int count) {
        ShapedRecipeBuilder.shapedRecipe(to, count)
                           .key('#', from)
                           .patternLine("###")
                           .patternLine("###")
                           .addCriterion("has_ingredient", hasItem(from))
                           .build(consumer, Midnight.resLoc(id));
    }

    private void stairs(String id, IItemProvider from, IItemProvider to, int count) {
        ShapedRecipeBuilder.shapedRecipe(to, count)
                           .key('#', from)
                           .patternLine("#  ")
                           .patternLine("## ")
                           .patternLine("###")
                           .addCriterion("has_ingredient", hasItem(from))
                           .build(consumer, Midnight.resLoc(id));
    }

    private void step(String id, IItemProvider from, IItemProvider to, int count) {
        ShapedRecipeBuilder.shapedRecipe(to, count)
                           .key('#', from)
                           .patternLine("#  ")
                           .patternLine("## ")
                           .addCriterion("has_ingredient", hasItem(from))
                           .build(consumer, Midnight.resLoc(id));
    }

    private void smelting(String id, IItemProvider from, IItemProvider to, double xp) {
        CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(from), to, (float) xp, 200)
                            .addCriterion("has_ingredient", hasItem(from))
                            .build(consumer, Midnight.resLoc(id));
    }

    @Override
    public String getName() {
        return "Midnight/Recipes";
    }
}
