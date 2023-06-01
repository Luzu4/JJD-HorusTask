package pl.horus;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Block> blocks = new ArrayList<>();
        blocks.add(new Block() {
            @Override
            public String getColor() {
                return "red";
            }

            @Override
            public String getMaterial() {
                return "concrete";
            }
        });
        blocks.add(new Block() {
            @Override
            public String getColor() {
                return "red";
            }

            @Override
            public String getMaterial() {
                return "concrete";
            }
        });
        blocks.add(new CompositeBlock() {
            @Override
            public List<Block> getBlocks() {
                List<Block> blocksInsideComposite = new ArrayList<>();
                blocksInsideComposite.add(new Block() {
                    @Override
                    public String getColor() {
                        return "purple";
                    }

                    @Override
                    public String getMaterial() {
                        return "concrete";
                    }
                });
                blocksInsideComposite.add(new Block() {
                    @Override
                    public String getColor() {
                        return "red";
                    }

                    @Override
                    public String getMaterial() {
                        return "concrete";
                    }
                });
                return blocksInsideComposite ;
            }

            @Override
            public String getColor() {
                return "blue";
            }

            @Override
            public String getMaterial() {
                return "concrete";
            }
        });

        Wall wall = new Wall(blocks);
        wall.findBlockByColor("asd").ifPresent(block -> {
            System.out.println("block = " + block.getColor());
            System.out.println("block.getMaterial() = " + block.getMaterial());
        });

        System.out.println("wall.findBlocksByMaterial(\"composite\") = " + wall.findBlocksByMaterial("concrete"));

        System.out.println("wall.count() = " + wall.count());
    }
}


