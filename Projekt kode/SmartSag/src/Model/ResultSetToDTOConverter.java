
package Model;

import smartsag.DTO.enums.DTOType;
import Smartsag.DTO.BasicInformation;
import Smartsag.DTO.LoginInformation;
import Smartsag.DTO.DTO;
import Smartsag.DTO.IDInformation;
import Smartsag.DTO.PermissionsInformation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class that handles the conversion from sql result sets to specified DTOs. 
 * 
 */
public class ResultSetToDTOConverter {

    /**
     * Given a resultset with multiple entries, returns a list with specific types of DTO based on input.
     * @param type
     * @param rs
     * @return 
     */
    public static List<DTO> getDTOList(DTOType type, ResultSet rs) {
        List<DTO> DTOList = new ArrayList<>();

        try {
            while (rs.next()) {
                switch (type) {
                    case CITIZEN:
                        DTOList.add(ResultSetToDTOConverter.resultSetToCitizen(rs));
                        break;
                    case CASE:
                        DTOList.add(ResultSetToDTOConverter.resultSetToCase(rs));
                        break;
                    case EMPLOYEE:
                        DTOList.add(ResultSetToDTOConverter.resultSetToEmployee(rs));
                        break;
                    case DEPARTMENT:
                        DTOList.add(ResultSetToDTOConverter.resultSetToDepartment(rs));
                        break;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ResultSetToDTOConverter.class.getName()).log(Level.SEVERE, null, ex);
        }

        return DTOList;
    }

    /**
     * Given a resultset with a single entry, returns a DTO with specific type based on input.
     * @param type
     * @param rs
     * @return 
     */
    public static DTO getDTO(DTOType type, ResultSet rs) {
        DTO dto = DTO.builder().build();
        try {
            if (rs.next()) {
                switch (type) {
                    case CITIZEN:
                        dto = ResultSetToDTOConverter.resultSetToCitizen(rs);
                        break;
                    case CASE:
                        dto = ResultSetToDTOConverter.resultSetToCase(rs);
                        break;
                    case EMPLOYEE:
                        dto = ResultSetToDTOConverter.resultSetToEmployee(rs);
                        break;
                    case DEPARTMENT:
                        dto = ResultSetToDTOConverter.resultSetToDepartment(rs);
                        break;
                    case ROLE:
                        dto = ResultSetToDTOConverter.resultSetToRole(rs);
                        break;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ResultSetToDTOConverter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dto;
    }

    /**
     * Converts a result set to a DTO of type "citizen"
     * @param rs
     * @return 
     */
    private static DTO resultSetToCitizen(ResultSet rs) {
        try {

            DTO citizen = DTO.builder().
                    withIDInformation(IDInformation.getBuilder().withCitizenID(rs.getInt("id")).build()).
                    withLoginInformation(LoginInformation.builder().
                            password(rs.getString("password")).
                            build()).
                    withBasicInformation(BasicInformation.builder().
                            withCPR(rs.getInt("cpr")).
                            withFirstName(rs.getString("first_name")).
                            withMiddleName(rs.getString("middle_name")).
                            withLastName(rs.getString("last_name")).
                            withGender(rs.getString("gender")).
                            build()).
                    build();
            return citizen;
        } catch (SQLException ex) {
            Logger.getLogger(ResultSetToDTOConverter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * * Converts a result set to a DTO of type "employee"
     * @param rs
     * @return 
     */
    private static DTO resultSetToEmployee(ResultSet rs) {
        try {
            DTO employee = DTO.builder().
                    withIDInformation(IDInformation.getBuilder().
                            withEmployeeID(rs.getInt("id")).
                            withRoleID(rs.getInt("role_id")).
                            build()).
                    withLoginInformation(LoginInformation.builder().
                            username(rs.getString("username")).
                            password(rs.getString("password")).
                            build()).
                    withBasicInformation(BasicInformation.builder().
                            withFirstName(rs.getString("first_name")).
                            withMiddleName(rs.getString("middle_name")).
                            withLastName(rs.getString("last_name")).
                            build()).
                    build();
            return employee;
        } catch (SQLException ex) {
            Logger.getLogger(ResultSetToDTOConverter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * * Converts a result set to a DTO of type "department"
     * @param rs
     * @return 
     */
    private static DTO resultSetToDepartment(ResultSet rs) {
        try {
            DTO department = DTO.builder().
                    withIDInformation(IDInformation.getBuilder().
                            withDepartmentID(rs.getInt("id")).
                            build()).
                    withBasicInformation(BasicInformation.builder().
                            withName(rs.getString("name")).
                            build()).
                    build();
            return department;
        } catch (SQLException ex) {
            Logger.getLogger(ResultSetToDTOConverter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * * Converts a result set to a DTO of type "role"
     * @param rs
     * @return 
     */
    private static DTO resultSetToRole(ResultSet rs) {
        try {
            DTO role = DTO.builder().
                    withIDInformation(IDInformation.getBuilder().
                            withRoleID(rs.getInt("id")).
                            withDepartmentID(rs.getInt("department_id")).
                            build()).
                    withBasicInformation(BasicInformation.builder().
                            withName(rs.getString("name")).
                            build()).
                    withCasePermissions(PermissionsInformation.getBuilder().
                            withCanCreate(rs.getBoolean("case_create")).
                            withCanRead(rs.getBoolean("case_read")).
                            withCanEdit(rs.getBoolean("case_edit")).
                            withCanDelete(rs.getBoolean("case_delete")).
                            build()).
                    withUserPermissions(PermissionsInformation.getBuilder().
                            withCanCreate(rs.getBoolean("user_create")).
                            withCanRead(rs.getBoolean("user_read")).
                            withCanEdit(rs.getBoolean("user_edit")).
                            withCanDelete(rs.getBoolean("user_delete")).
                            build()).
                    withRolePermissions(PermissionsInformation.getBuilder().
                            withCanCreate(rs.getBoolean("role_create")).
                            withCanRead(rs.getBoolean("role_read")).
                            withCanEdit(rs.getBoolean("role_edit")).
                            withCanDelete(rs.getBoolean("role_delete")).
                            build()).
                    build();
            return role;
        } catch (SQLException ex) {
            Logger.getLogger(ResultSetToDTOConverter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * * Converts a result set to a DTO of type "case"
     * @param rs
     * @return 
     */
    private static DTO resultSetToCase(ResultSet rs) {
        try {
            DTO caseDTO = DTO.builder().
                    withIDInformation(IDInformation.getBuilder().
                            withID(rs.getInt("id")).
                            withCitizenID(rs.getInt("applicant_id")).
                            withEmployeeID(rs.getInt("case_worker_id")).
                            withDepartmentID(rs.getInt("department_id")).
                            withOpeningFormID(rs.getInt("opening_form_id")).
                            build()).
                    withCaseStatus(rs.getString("case_status")).
                    build();
            return caseDTO;
        } catch (SQLException ex) {
            Logger.getLogger(ResultSetToDTOConverter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
