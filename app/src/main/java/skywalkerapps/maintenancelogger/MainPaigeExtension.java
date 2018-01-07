package skywalkerapps.maintenancelogger;

/**
 * This class inherits all the data from the MainPaige
 * class which contains all the data in a list of arrays such
 * as the vehicle name, make, model, year, etc. This class was created
 * to easily access the data from the MainPaige class
 *
 * Skywalker Apps
 *
 * Created by Luke Moua
 * 1/7/2018.
 */

public class MainPaigeExtension extends MainPaige{

    public String getArrayListNickname(int positionNum) {
        return nicknameArrayList.get(positionNum);
    }

    public String getArrayListMake(int positionNum) {
        return makeArrayList.get(positionNum);
    }

    public String getArrayListModel(int positionNum) {
        return modelArrayList.get(positionNum);
    }

    public String getArrayListYear(int positioNum) {
        return yearArrayList.get(positioNum);
    }

    public int getVehicleListSize() {
        return nicknameArrayList.size();
    }
}
