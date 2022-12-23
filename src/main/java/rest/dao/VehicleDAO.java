package rest.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import rest.model.Vehicle;
import rest.model.enums.FuelType;
import rest.util.HibernateUtil;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class VehicleDAO {
    public List<Vehicle> getAllVehicles() {
        Transaction transaction = null;
        List<Vehicle> vehicles = null;
        try (Session session = HibernateUtil.getFactory().openSession()) {
            transaction = session.beginTransaction();
            vehicles = session.createQuery("FROM rest.model.Vehicle").getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return vehicles;
    }

    public Vehicle getVehicleForId(long id) {
        Transaction transaction = null;
        Vehicle vehicle = null;
        try (Session session = HibernateUtil.getFactory().openSession()) {
            transaction = session.beginTransaction();
            vehicle = session.get(Vehicle.class, id);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return vehicle;
    }

    public void createVehicle(Vehicle vehicle) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(vehicle.getCoordinates());
            session.save(vehicle);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void updateVehicle(Vehicle vehicle) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(vehicle.getCoordinates());
            session.update(vehicle);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public boolean deleteVehicle(long id) {
        Transaction transaction = null;
        boolean successful = false;
        try (Session session = HibernateUtil.getFactory().openSession()) {
            transaction = session.beginTransaction();
            Vehicle vehicle = session.find(Vehicle.class, id);
            if (vehicle != null) {
                session.delete(vehicle);
                session.delete(vehicle.getCoordinates());
                session.flush();
                successful = true;
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return successful;
    }

    public List<Vehicle> getFilteredVehicles(Map<String, String[]> params) {
        Transaction transaction = null;
        List<Vehicle> vehicles = null;
        boolean isJoin = false;
        boolean isWhere = false;
        StringBuilder queryStr = new StringBuilder("from rest.model.Vehicle c");
        StringBuilder whereStr = new StringBuilder(" where");

        if (params.containsKey("name")) {
            isWhere = true;
            whereStr.append(" c.name LIKE '%").append(params.get("name")[0]).append("%' AND");
        }

        if (params.containsKey("x1") && params.containsKey("x2")) {
            isJoin = true;
            isWhere = true;
            whereStr.append(" cor.x BETWEEN '").append(params.get("x1")[0]).append("' AND '").append(params.get("x2")[0]).append("' AND");
        } else if (params.containsKey("x1")) {
            isJoin = true;
            isWhere = true;
            whereStr.append(" cor.x > ").append(params.get("x1")[0]);
        } else if (params.containsKey("x2")) {
            isJoin = true;
            isWhere = true;
            whereStr.append(" cor.x < ").append(params.get("x2")[0]);
        }

        if (params.containsKey("y1") && params.containsKey("y2")) {
            isJoin = true;
            isWhere = true;
            whereStr.append(" cor.y BETWEEN '").append(params.get("y1")[0]).append("' AND '").append(params.get("y2")[0]).append("' AND");
        } else if (params.containsKey("y1")) {
            isJoin = true;
            isWhere = true;
            whereStr.append(" cor.y > ").append(params.get("y1")[0]).append(" AND");
        } else if (params.containsKey("y2")) {
            isJoin = true;
            isWhere = true;
            whereStr.append(" cor.y < ").append(params.get("y2")[0]).append(" AND");
        }

        if (params.containsKey("start-creation-date") && params.containsKey("end-creation-date")
                && params.containsKey("start-creation-time") && params.containsKey("end-creation-time")) {
            isWhere = true;
            String start = params.get("start-creation-date")[0] + " " + params.get("start-creation-time")[0] + ":00";
            String end = params.get("end-creation-date")[0] + " " + params.get("end-creation-time")[0] + ":00";
            whereStr.append(" c.creationDate BETWEEN '").append(Timestamp.valueOf(start)).append(" +00:00' AND '").append(Timestamp.valueOf(end)).append(" +00:00' AND");
        } else

        if (params.containsKey("enginePower1") && params.containsKey("enginePower2")) {
            isWhere = true;
            whereStr.append(" c.enginePower BETWEEN '").append(params.get("enginePower1")[0]).append("' AND '").append(params.get("enginePower2")[0]).append("' AND");
        } else if (params.containsKey("enginePower1")) {
            isWhere = true;
            whereStr.append(" c.enginePower > ").append(params.get("enginePower1")[0]).append(" AND");
        } else if (params.containsKey("enginePower2")) {
            isWhere = true;
            whereStr.append(" c.enginePower < ").append(params.get("enginePower2")[0]).append(" AND");
        }

        if (params.containsKey("numberOfWheels1") && params.containsKey("numberOfWheels2")) {
            isWhere = true;
            whereStr.append(" c.numberOfWheels BETWEEN '").append(params.get("numberOfWheels1")[0]).append("' AND '").append(params.get("numberOfWheels2")[0]).append("' AND");
        } else if (params.containsKey("numberOfWheels")) {
            isWhere = true;
            whereStr.append(" c.numberOfWheels > ").append(params.get("numberOfWheels1")[0]).append(" AND");
        } else if (params.containsKey("numberOfWheels2")) {
            isWhere = true;
            whereStr.append(" c.numberOfWheels < ").append(params.get("numberOfWheels2")[0]).append(" AND");
        }

        ArrayList<String> checkboxParamsNames = new ArrayList<>(Arrays.asList("type", "fuelType"));
        for (String paramName : checkboxParamsNames) {
            if (params.containsKey(paramName)) {
                isWhere = true;
                whereStr.append(" c.").append(paramName).append(" IN (");
                String[] checkboxValues = params.get(paramName);
                for (String checkboxValue : checkboxValues) {
                    switch (checkboxValue) {
                        case "ALCOHOL":
                        case "PLANE":
                            whereStr.append(0).append(", ");
                            break;
                        case "MANPOWER":
                        case "BOAT":
                            whereStr.append(1).append(", ");
                            break;
                        case "PLASMA":
                        case "SHIP":
                            whereStr.append(2).append(", ");
                            break;
                        case "ANTIMATTER":
                        case "MOTORCYCLE":
                            whereStr.append(3).append(", ");
                            break;
                        case "CHOPPER":
                            whereStr.append(4).append(", ");
                            break;
                    }
                }
                whereStr = new StringBuilder(whereStr.substring(0, whereStr.length() - 2));
                whereStr.append(") AND");
            }
        }

        if (whereStr.toString().endsWith("AND"))
            whereStr = new StringBuilder(whereStr.substring(0, whereStr.length() - 4));

        if (isJoin) queryStr.append(" join c.coordinates cor ");

        if (isWhere) queryStr.append(whereStr);

        if(params.containsKey("sortBy")) {
            queryStr.append(" order by ");

            if (params.get("sortBy")[0].equals("x") || params.get("sortBy")[0].equals("y")) {
                if (isJoin) queryStr.append("cor.");
            } else queryStr.append("c.");

            queryStr.append(params.get("sortBy")[0]);

            if (params.containsKey("order")) queryStr.append(" ").append(params.get("order")[0]);
        }

        try (Session session = HibernateUtil.getFactory().openSession()) {
            transaction = session.beginTransaction();
            if (isJoin) {
                List<Object[]> vehicleListWithExtraColumns = session.createQuery(queryStr.toString()).getResultList();
                vehicles = new ArrayList<>();
                for (Object[] vehicleWithExtraColumns : vehicleListWithExtraColumns) {
                    vehicles.add((Vehicle) vehicleWithExtraColumns[0]);
                }
            } else {
                vehicles = session.createQuery(queryStr.toString()).getResultList();
            }
            session.flush();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return vehicles;
    }

    public Double getEnginePowerSum() {
        Transaction transaction = null;
        Double summ = null;
        try (Session session = HibernateUtil.getFactory().openSession()) {
            transaction = session.beginTransaction();
            summ = (Double) session.createQuery("select sum(v.enginePower) FROM rest.model.Vehicle v").getSingleResult();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return summ;
    }

    public Vehicle getMinName() {
        Transaction transaction = null;
        Vehicle vehicle = null;
        String name;
        try (Session session = HibernateUtil.getFactory().openSession()) {
            transaction = session.beginTransaction();
            name = (String) session.createQuery("select min(v.name) FROM rest.model.Vehicle v").getSingleResult();
            vehicle = (Vehicle) session.createQuery("FROM rest.model.Vehicle v where v.name in (select min(v.name) FROM rest.model.Vehicle v)").getSingleResult();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return vehicle;
    }

    public List<FuelType> getUniqueFuelTypes() {
        Transaction transaction = null;
        List<FuelType> types = null;
        try (Session session = HibernateUtil.getFactory().openSession()) {
            transaction = session.beginTransaction();
            types = session.createQuery("select distinct (v.fuelType) FROM rest.model.Vehicle v").getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return types;
    }
}