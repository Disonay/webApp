package repository.house;

import repository.Repository;
import repository.entities.HouseEntity;
import repository.entities.PersonEntity;

public interface RepositoryHouse extends Repository<HouseEntity> {
    void addPersonToHouse(int houseId, PersonEntity person);

}
