package pojos.and.daos;

import java.sql.SQLException;
import java.util.List;

public interface CustomerDao<T> {

    void save() throws SQLException; // saves the record to the database

    void update(); // updates the record in the database

    void delete(); // deletes the record from the database

    void deleteAll(); // deletes all records in the table

    int getRandomId();// returns a random record id

    List<T> getRandomIds(int count); // returns a list of X random records' id

    int getRecordsCount(); // get the count of all records in the table

    T getByID(int id); // to extract a single object from the database by ID

    List<T> getByIDs(List<Integer> ids); // to extract a list of objects from the database by a List of IDs


}
