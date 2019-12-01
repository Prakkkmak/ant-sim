package model.entities;

import model.abstracts.EntityTile;
import model.abstracts.Role;
import model.interfaces.IAction;
import model.interfaces.IEntityTile;
import model.interfaces.IVisitor;
import model.roles.Wild;
import model.world.Tile;

public class Prey extends EntityTile {

    private Role role;

    public Prey(Tile tile){
        super(tile);
        role = new Wild();
    }

    public Role getRole(){
        return this.role;
    }

    public void setRole(Role role){
        this.role = role;
    }

    @Override
    public void update() {
        role.action(this);
    }

    @Override
    public void accept(IVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public int getId() {
        return 10;
    }
}
