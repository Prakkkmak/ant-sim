package model.visitors;

import model.abstracts.Role;
import model.abstracts.State;
import model.entities.Ant;
import model.interfaces.IEntityTile;
import model.interfaces.IVisitable;
import model.interfaces.IVisitor;
import model.world.Tile;
import model.world.World;

public class AntCounterVisitor implements IVisitor {

    private int antCount;
    public AntCounterVisitor(){
        antCount = 0;
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
        if(entityTile instanceof Ant){
            this.antCount++;
            Ant ant = (Ant) entityTile;
            State state = ant.getState();
            Role role = ant.getRole();
            //TODO each state + roles;
        }
    }

    public int getAntCount(){
        return this.antCount;
    }
}
