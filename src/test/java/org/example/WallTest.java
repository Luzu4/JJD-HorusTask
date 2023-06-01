package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.horus.Block;
import pl.horus.CompositeBlock;
import pl.horus.Wall;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class WallTest {


    @Test
    public void findBlockByColor_should_return_block() {
        Wall wall = this.buildTestingWall();

        Optional<Block> foundBlock = wall.findBlockByColor("red");

        Assertions.assertTrue(foundBlock.isPresent());
        Assertions.assertEquals("red", foundBlock.get().getColor());
    }

    @Test
    public void findBlockByColor_should_return_optional_empty() {
        Wall wall = this.buildTestingWall();

        Optional<Block> foundBlock = wall.findBlockByColor("orange");

        Assertions.assertFalse(foundBlock.isPresent());
    }

    @Test
    public void findBlocksByMaterial_should_return_list_of_size_4() {
        Wall wall = this.buildTestingWall();

        List<Block> foundBlocks = wall.findBlocksByMaterial("concrete");

        Assertions.assertEquals(4, foundBlocks.size());
    }

    @Test
    public void findBlocksByMaterial_should_return_empty_list() {
        Wall wall = this.buildTestingWall();

        List<Block> foundBlocks = wall.findBlocksByMaterial("wood");

        Assertions.assertEquals(0, foundBlocks.size());
    }

    @Test
    public void count_should_return_list_of_size_4() {
        Wall wall = this.buildTestingWall();

        int sumOfBlocks = wall.count();

        Assertions.assertEquals(4, sumOfBlocks);
    }
    @Test
    public void count_should_return_empty_list() {
        Wall wall = new Wall(new ArrayList<>());

        int sumOfBlocks = wall.count();

        Assertions.assertEquals(0, sumOfBlocks);
    }

    private Wall buildTestingWall() {
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
                return "yellow";
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
                        return "blue";
                    }

                    @Override
                    public String getMaterial() {
                        return "concrete";
                    }
                });
                blocksInsideComposite.add(new Block() {
                    @Override
                    public String getColor() {
                        return "blue";
                    }

                    @Override
                    public String getMaterial() {
                        return "concrete";
                    }
                });
                return blocksInsideComposite;
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

        return new Wall(blocks);
    }
}
