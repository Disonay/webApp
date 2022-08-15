package data.dao.house;

import data.dao.DAO;
import data.entities.HouseEntity;
import data.entities.PersonEntity;

public interface HouseDAO extends DAO<HouseEntity> {
    void addPersonToHouse(int houseId, PersonEntity person);

}
