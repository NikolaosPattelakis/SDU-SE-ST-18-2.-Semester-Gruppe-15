package Model.POJO.Permissions;


import Model.POJO.Interfaces.BuilderInterface;

/**
 *
 * @author Lupo
 */
public final class CasePermissions extends AbstractPermissions {

    private CasePermissions(
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

    public static class Builder implements BuilderInterface<CasePermissions> {

        private boolean canCreate;
        private boolean canEdit;
        private boolean canReadFull;
        private boolean canReadPartial;
        private boolean canDelete;

        private Builder(){}
        
        @Override
        public CasePermissions build() {
            return new CasePermissions(
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
