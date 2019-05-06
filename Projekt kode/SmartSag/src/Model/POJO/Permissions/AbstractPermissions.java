/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.POJO.Permissions;

/**
 *
 * @author Lupo
 */
public abstract class AbstractPermissions implements PermissionInterface {

    private boolean canCreate;
    private boolean canEdit;
    private boolean canReadFull;
    private boolean canReadPartial;
    private boolean canDelete;

    public AbstractPermissions(
            boolean canCreate,
            boolean canEdit,
            boolean canReadFull,
            boolean canReadPartial,
            boolean canDelete) {

        this.canCreate = canCreate;
        this.canEdit = canEdit;
        this.canReadFull = canReadFull;
        this.canReadPartial = canReadPartial;
        this.canDelete = canDelete;
    }

    @Override
    public boolean canCreate() {
        return this.canCreate;
    }

    @Override
    public void setCanCreate(boolean canCreate) {
        this.canCreate = canCreate;
    }

    @Override
    public boolean canEdit() {
        return this.canEdit;
    }

    @Override
    public void setCanEdit(boolean canEdit) {
        this.canEdit = canEdit;
    }

    @Override
    public boolean canReadFull() {
        return this.canReadFull;
    }

    @Override
    public void setCanReadFull(boolean canReadFull) {
        this.canReadFull = canReadFull;
    }

    @Override
    public boolean canReadPartial() {
        return this.canReadPartial;
    }

    @Override
    public void setCanReadPartial(boolean canReadPartial) {
        this.canReadPartial = canReadPartial;
    }

    @Override
    public boolean canDelete() {
        return this.canDelete;
    }

    @Override
    public void setCanDelete(boolean canDelete) {
        this.canDelete = canDelete;
    }
}
