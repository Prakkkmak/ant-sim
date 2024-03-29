package main.model.visitors;

import main.model.enums.EType;
import main.model.interfaces.IEntityTile;
import main.model.interfaces.IVisitable;
import main.model.interfaces.IVisitor;
import main.model.world.Tile;

import java.util.HashMap;
import java.util.Map;

public class AntCounterVisitor implements IVisitor {

    private Map<EType, Integer> inside;
    private Map<EType, Integer> outside;

    public AntCounterVisitor() {
        this.inside = new HashMap<>();
        this.outside = new HashMap<>();
    }

    @Override
    public void visit(IVisitable visitable) {

    }

    @Override
    public void visit(Tile tile) {
        tile.getEntities().forEach((v) -> {
            v.accept(this);
        });
    }

    @Override
    public void visit(IEntityTile entityTile) {
        EType type = entityTile.getType();
        int number = 1;
        if (entityTile.getTile().contains(EType.ANTHILL)) {
            if (inside.get(type) != null) number += inside.get(type);
            inside.put(type, number);
        } else {
            if (outside.get(type) != null) number += outside.get(type);
            outside.put(type, number);
        }

    }

    public Map<EType, Integer> getInside() {
        return inside;
    }

    public void setInside(Map<EType, Integer> inside) {
        this.inside = inside;
    }

    public Map<EType, Integer> getOutside() {
        return outside;
    }

    public void setOutside(Map<EType, Integer> outside) {
        this.outside = outside;
    }
}
