package Model.POJO.Permissions;


import Model.POJO.Interfaces.BuilderInterface;
import Model.POJO.Permissions.AbstractPermissions;

/**
 *
 * @author Lupo
 */
public final class UserPermissions extends AbstractPermissions {

    private UserPermissions(
            boolean canCreate,
            boolean canEdit,
            boolean canReadFull,
            boolean canReadPartial,
            boolean canDelete) {

        super(canCreate, canEdit, canReadFull, canReadPartial, canDelete);
    }

    public Builder getBuilder() {
        return new Builder();
    }

    public static class Builder implements BuilderInterface<UserPermissions> {

        private boolean canCreate;
        private boolean canEdit;
        private boolean canReadFull;
        private boolean canReadPartial;
        private boolean canDelete;

        private Builder(){}
        
        @Override
        public UserPermissions build() {
            return new UserPermissions(
            this.canCreate,
            this.canEdit,
            this.canReadFull,
            this.canReadPartial,
            this.canDelete);
        }
        
        public Builder canCreate(boolean canCreate){
            this.canCreate = canCreate;
            return this;
        }
        
        public Builder canEdit(boolean canEdit){
            this.canEdit = canEdit;
            return this;
        }
        
        public Builder canReadFull(boolean canReadFull){
            this.canReadFull = canReadFull;
            return this;
        }
        
        public Builder canReadPartial(boolean canReadPartial){
            this.canReadPartial = canReadPartial;
            return this;
        }
        
        public Builder canDelete(boolean canDelete){
            this.canDelete = canDelete;
            return this;
        }
    }
}
