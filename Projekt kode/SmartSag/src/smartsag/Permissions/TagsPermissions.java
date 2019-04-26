
package smartsag.Permissions;

/**
 * Holds all the tags with the permissions a @Role can have.
 * 
 */
public interface TagsPermissions {

    String TAG_ROLE_CAN_CREATE = "RoleCanCreate";
    String TAG_ROLE_CAN_EDIT = "RoleCanEdit";
    String TAG_ROLE_CAN_READ = "RoleCanRead";
    String TAG_ROLE_CAN_DELETE = "RoleCanDelete";

    String TAG_USER_CAN_CREATE = "UserCanCreate";
    String TAG_USER_CAN_EDIT = "UserCanEdit";
    String TAG_USER_CAN_READ = "UserCanRead";
    String TAG_USER_CAN_DELETE = "UserCanDelete";

    String TAG_CASE_CAN_CREATE = "CaseCanCreate";
    String TAG_CASE_CAN_EDIT = "CaseCanEdit";
    String TAG_CASE_CAN_READ = "CaseCanRead";
    String TAG_CASE_CAN_DELETE = "CaseCanDelete";
}
