package model.interfaces;

import model.world.Tile;

public interface IVisitor {

  void visit(IVisitable visitable);

  void visit(Tile tile);

  void visit(IEntityTile entityTile);
}
