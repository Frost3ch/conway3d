package net.frosty.conway3d.item;

import net.frosty.conway3d.block.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.HashSet;
import java.util.Set;

public class ControllerItem extends Item {

    public static Set<BlockPos> conwayBlocks = new HashSet<>();
    public static Set<BlockPos> toKill = new HashSet<>();
    public static Set<BlockPos> toBeBorn = new HashSet<>();

    public static Integer deathRule1 = 1;
    public static Integer deathRule2 = 4;

    public static Integer bornRule1 = 3;
    public static Integer bornRule2 = 3;

    public static boolean is3D = true;

    public ControllerItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {

        if (!user.getWorld().isClient() && hand == Hand.MAIN_HAND) {
            //runs only on server-side

//            user.sendMessage(Text.of("conwayBlocks " + conwayBlocks.toString()));
//            user.sendMessage(Text.of("toKill " + toKill.toString()));
//            user.sendMessage(Text.of("toBeBorn " + toBeBorn.toString()));

            for (BlockPos conwayPos : conwayBlocks) {

                BlockState state = world.getBlockState(conwayPos);
                int noNeighbours =-1;

                if (is3D) {
                    //Find Total Number of Neighbours
                    for (int i = -1; i < 2; i++) {
                        for (int j = -1; j < 2; j++) {
                            for (int k = -1; k < 2; k++) {
                                BlockState neighbourState = world.getBlockState(new BlockPos(conwayPos.getX() + i, conwayPos.getY() + j, conwayPos.getZ() + k));
                                if (neighbourState.getBlock() == state.getBlock()) {
                                    noNeighbours++;
                                } else {
                                    int newNeighbours = 0;
                                    BlockPos newPos = new BlockPos(conwayPos.getX() + i, conwayPos.getY() + j, conwayPos.getZ() + k);
                                    for (int a = -1; a < 2; a++) {
                                        for (int b = -1; b < 2; b++) {
                                            for (int c = -1; c < 2; c++) {
                                                BlockState newNeighbourState = world.getBlockState(new BlockPos(newPos.getX() + a, newPos.getY() + b, newPos.getZ() + c));
                                                if (newNeighbourState.getBlock() == state.getBlock()) {
                                                    newNeighbours++;
                                                }
                                            }
                                        }
                                    }
                                    if (newNeighbours >= bornRule1 && newNeighbours <= bornRule2) {
                                        ControllerItem.toBeBorn.add(newPos);
                                    }
                                }
                            }
                        }
                    }
//                user.sendMessage(Text.of("Block at " + conwayPos.toString() + " has " + noNeighbours + " neighbours"));
                    if (noNeighbours <= deathRule1 || noNeighbours >= deathRule2) {
                        ControllerItem.toKill.add(conwayPos);
                    }
                }
                else {
                    //Find Total Number of Neighbours
                    for (int i = -1; i < 2; i++) {
                        for (int k = -1; k < 2; k++) {
                            BlockState neighbourState = world.getBlockState(new BlockPos(conwayPos.getX() + i, conwayPos.getY(), conwayPos.getZ() + k));
                            if (neighbourState.getBlock() == state.getBlock()) {
                                noNeighbours++;
                            } else {
                                int newNeighbours = 0;
                                BlockPos newPos = new BlockPos(conwayPos.getX() + i, conwayPos.getY(), conwayPos.getZ() + k);
                                for (int a = -1; a < 2; a++) {
                                    for (int c = -1; c < 2; c++) {
                                        BlockState newNeighbourState = world.getBlockState(new BlockPos(newPos.getX() + a, newPos.getY(), newPos.getZ() + c));
                                        if (newNeighbourState.getBlock() == state.getBlock()) {
                                            newNeighbours++;
                                        }
                                    }
                                }
                                if (newNeighbours >= bornRule1 && newNeighbours <= bornRule2) {
                                    ControllerItem.toBeBorn.add(newPos);
                                }
                            }
                        }
                    }
                    //                user.sendMessage(Text.of("Block at " + conwayPos.toString() + " has " + noNeighbours + " neighbours"));
                    if (noNeighbours <= deathRule1 || noNeighbours >= deathRule2) {
                        ControllerItem.toKill.add(conwayPos);
                    }
                }
            }


            for (BlockPos pos : toKill){
                world.setBlockState(pos, Blocks.AIR.getDefaultState());
                conwayBlocks.remove(pos);
            }
            for (BlockPos pos : toBeBorn){
                world.setBlockState(pos, ModBlocks.CONWAY_BLOCK.getDefaultState());
                conwayBlocks.add(pos);
            }

            user.sendMessage(Text.of("Stage Advanced..."));
            user.sendMessage(Text.of(toKill.size() + " Blocks died."));
            user.sendMessage(Text.of(toBeBorn.size() + " Blocks were born."));

            toKill = new HashSet<>();
            toBeBorn = new HashSet<>();
        }

        return super.use(world, user, hand);
    }
}
