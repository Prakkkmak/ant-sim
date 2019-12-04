package main.model.interfaces;

import main.model.world.Tile;

public interface IVisitor {

    void visit(IVisitable visitable);

    void visit(Tile tile);

    void visit(IEntityTile entityTile);
}
