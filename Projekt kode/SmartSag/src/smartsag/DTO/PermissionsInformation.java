package Smartsag.DTO;

import smartsag.DTO.Interfaces.BuilderInterface;
import smartsag.DTO.Interfaces.PermissionsInterface;

/**
 *
 * @author Lupo
 */
public final class PermissionsInformation implements PermissionsInterface {

    private boolean canCreate;
    private boolean canEdit;
    private boolean canRead;
    private boolean canDelete;
    
    private PermissionsInformation() {
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
    public boolean canRead() {
        return this.canRead;
    }

    @Override
    public void setCanRead(boolean canRead) {
        this.canRead = canRead;
    }

    @Override
    public boolean canDelete() {
        return this.canDelete;
    }

    @Override
    public void setCanDelete(boolean canDelete) {
        this.canDelete = canDelete;
    }

    public static Create getBuilder() {
        return new Builder();
    }

    public interface Create {

        Read withCanCreate(boolean canCreate);
    }

    public interface Read {

        Edit withCanRead(boolean canRead);
    }

    public interface Edit {

        Delete withCanEdit(boolean canEdit);
    }

    public interface Delete {

        Builder withCanDelete(boolean canDelete);
    }

    public static class Builder implements
            BuilderInterface<PermissionsInformation>,
            Create,
            Read,
            Edit,
            Delete {

        PermissionsInformation permissions;

        private Builder() {
            this.permissions = new PermissionsInformation();
        }

        @Override
        public PermissionsInformation build() {
            return this.permissions;
        }

        @Override
        public Read withCanCreate(boolean canCreate) {
            this.permissions.setCanCreate(canCreate);
            return this;
        }

        @Override
        public Edit withCanRead(boolean canRead) {
            this.permissions.setCanRead(canRead);
            return this;
        }

        @Override
        public Delete withCanEdit(boolean canEdit) {
            this.permissions.setCanEdit(canEdit);
            return this;
        }

        @Override
        public Builder withCanDelete(boolean canDelete) {
            this.permissions.setCanDelete(canDelete);
            return this;
        }

    }
}
