/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MeditorJavaClasses;

import MeditorPersistence.Doctor;

/**
 *  Data Access Object Interface which is which is responsible for
 *  the CRUD operations on the Doctor table
 * @author glalas
 */
public interface DoctorDAO {
    
    /**
     * This method is responsible for adding a new Doctor to the Database.
     * @param doctor 
     */
    public void addDoctor(Doctor doctor);
    
    /**
     * This method is responsible for updating a Doctor entry in the Database.
     * @param doctor 
     */
    public void updateDoctor(Doctor doctor);
    
    /**
     * This method is responsible for deleting a Doctor entry in the Database.
     * This method is not implemented yet since this feature was not asked in feature 4.
     * @param doctor 
     */
    public void deleteDoctor(Doctor doctor);
    
    
    /**
     * This method is responsible for deleting a Doctor entry in the Database.
     * This method is not implemented yet since this feature was not asked in feature 4.
     * @param docId 
     */

    public void readDoctor(Integer docId);
    
}
