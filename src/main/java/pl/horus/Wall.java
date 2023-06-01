package pl.horus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Wall implements Structure {
    private List<Block> blocks;

    public Wall(List<Block> blocks) {
        this.blocks = blocks;
    }

    @Override
    public Optional<Block> findBlockByColor(String color) {
        return getBlock(color, blocks);
    }

    private Optional<Block> getBlock(String color, List<Block> blocks) {
        for (Block block : blocks) {
            if (block.getColor().equals(color)) {
                return Optional.of(block);
            }
            if (block instanceof CompositeBlock) {
                Optional<Block> nestedBlock = findNestedBlockByColor((CompositeBlock) block, color);
                if (nestedBlock.isPresent()) {
                    return nestedBlock;
                }
            }
        }
        return Optional.empty();
    }

    private Optional<Block> findNestedBlockByColor(CompositeBlock compositeBlock, String color) {
        List<Block> blocks = compositeBlock.getBlocks();
        return getBlock(color, blocks);
    }

    @Override
    public List<Block> findBlocksByMaterial(String material) {
        return getBlocks(material, blocks);
    }

    private List<Block> getBlocks(String material, List<Block> blocks) {
        List<Block> foundBlocks = new ArrayList<>();
        for (Block block : blocks) {
            if (block.getMaterial().equals(material)) {
                foundBlocks.add(block);
            }
            if (block instanceof CompositeBlock) {
                List<Block> nestedBlocks = findNestedBlocksByMaterial((CompositeBlock) block, material);
                if (nestedBlocks.size() > 0) {
                    foundBlocks.addAll(nestedBlocks);
                }
            }
        }
        return foundBlocks;
    }

    private List<Block> findNestedBlocksByMaterial(CompositeBlock compositeBlock, String material) {
        List<Block> blocks = compositeBlock.getBlocks();
        return getBlocks(material, blocks);
    }

    @Override
    public int count() {
        int sumOfBlocks = 0;
        for (Block block : blocks) {
            sumOfBlocks += countNestedBlocks(block);
        }
        return sumOfBlocks;
    }

    private int countNestedBlocks(Block block) {
        int sumOfBlocks = 0;
        if (block instanceof CompositeBlock) {
            List<Block> blocks = ((CompositeBlock) block).getBlocks();
            for (Block nestedBlock : blocks) {
                sumOfBlocks += countNestedBlocks(nestedBlock);
            }
        }else{
            sumOfBlocks +=1;
        }
        return sumOfBlocks;
    }
}