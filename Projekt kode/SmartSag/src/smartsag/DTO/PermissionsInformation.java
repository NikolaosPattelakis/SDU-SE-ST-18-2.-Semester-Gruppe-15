package Smartsag.DTO;

import smartsag.DTO.Interfaces.BuilderInterface;
import smartsag.DTO.Interfaces.PermissionsInterface;

/**
 *
 * Class that is holding permissions information, to be used as a variable
 * inside the DTO.
 */
public final class PermissionsInformation implements PermissionsInterface {

    private boolean canCreate;
    private boolean canEdit;
    private boolean canRead;
    private boolean canDelete;

    private PermissionsInformation() {
    }

    /**
     *
     * @return can create
     */
    @Override
    public boolean canCreate() {
        return this.canCreate;
    }

    /**
     * Sets can create
     *
     * @param canCreate
     */
    @Override
    public void setCanCreate(boolean canCreate) {
        this.canCreate = canCreate;
    }

    /**
     *
     * @return can edit
     */
    @Override
    public boolean canEdit() {
        return this.canEdit;
    }

    /**
     * Sets can edit
     *
     * @param canEdit
     */
    @Override
    public void setCanEdit(boolean canEdit) {
        this.canEdit = canEdit;
    }

    /**
     *
     * @return can read
     */
    @Override
    public boolean canRead() {
        return this.canRead;
    }

    /**
     * Sets can read
     *
     * @param canRead
     */
    @Override
    public void setCanRead(boolean canRead) {
        this.canRead = canRead;
    }

    /**
     *
     * @return can delete
     */
    @Override
    public boolean canDelete() {
        return this.canDelete;
    }

    /**
     * Sets can delete
     *
     * @param canDelete
     */
    @Override
    public void setCanDelete(boolean canDelete) {
        this.canDelete = canDelete;
    }

    /**
     * Gets builder to be used in the construction of this class object.
     *
     * @return
     */
    public static Create getBuilder() {
        return new Builder();
    }

    /**
     * Nested interface to set the sequence of the objects contruction.
     */
    public interface Create {

        Read withCanCreate(boolean canCreate);
    }

    /**
     * Nested interface to set the sequence of the objects contruction.
     */
    public interface Read {

        Edit withCanRead(boolean canRead);
    }

    /**
     * Nested interface to set the sequence of the objects contruction.
     */
    public interface Edit {

        Delete withCanEdit(boolean canEdit);
    }

    /**
     * Nested interface to set the sequence of the objects contruction.
     */
    public interface Delete {

        Builder withCanDelete(boolean canDelete);
    }

    /**
     * Inner class used to construct the outer PermissionsInformation class.
     */
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

        /**
         * Finalizes the construction of the class by building it.
         * @return 
         */
        @Override
        public PermissionsInformation build() {
            return this.permissions;
        }

        /**
         * Sets can create.
         * @param canCreate
         * @return 
         */
        @Override
        public Read withCanCreate(boolean canCreate) {
            this.permissions.setCanCreate(canCreate);
            return this;
        }

        /**
         * Sets can read.
         * @param canRead
         * @return 
         */
        @Override
        public Edit withCanRead(boolean canRead) {
            this.permissions.setCanRead(canRead);
            return this;
        }

        /**
         * Sets can edit.
         * @param canEdit
         * @return 
         */
        @Override
        public Delete withCanEdit(boolean canEdit) {
            this.permissions.setCanEdit(canEdit);
            return this;
        }

        /**
         * Sets can delete.
         * @param canDelete
         * @return 
         */
        @Override
        public Builder withCanDelete(boolean canDelete) {
            this.permissions.setCanDelete(canDelete);
            return this;
        }

    }
}
