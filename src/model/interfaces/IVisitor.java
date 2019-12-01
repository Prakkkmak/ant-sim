package model.interfaces;

import model.world.Tile;
import model.world.World;

public interface IVisitor {
    void visit(IVisitable visitable);
    void visit(Tile tile);
    void visit(IEntityTile entityTile);
}
