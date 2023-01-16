package rest.service;

import org.jetbrains.annotations.NotNull;
import rest.dao.VehicleDAO;
import rest.dto.VehicleDto;
import rest.mapper.VehicleMapper;
import rest.model.Vehicle;
import rest.model.enums.FuelType;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class VehicleService {
    private final VehicleDAO vehicleDAO = new VehicleDAO();

    public List<VehicleDto> getAllVehicles() {
        List<VehicleDto> dtos = new ArrayList<>();
        List<Vehicle> vehicles = vehicleDAO.getAllVehicles();
        for (Vehicle vehicle: vehicles) {
            dtos.add(VehicleMapper.vehicleToDto(vehicle));
        }
        return dtos;
    }

    public VehicleDto getVehicleForId (long id) {
        Vehicle vehicle = vehicleDAO.getVehicleForId(id);
        return VehicleMapper.vehicleToDto(vehicle);
    }

    public void createVehicle (@NotNull VehicleDto dto) {
            Vehicle vehicle = VehicleMapper.dtoToVehicle(dto);
            vehicleDAO.createVehicle(vehicle);
        }

        public void updateVehicle(VehicleDto dto) {
            Vehicle vehicle = VehicleMapper.dtoToVehicle(dto);
            vehicleDAO.updateVehicle(vehicle);
        }

        public void deleteVehicle(long id) {
            if (!vehicleDAO.deleteVehicle(id))
                throw new EntityNotFoundException("Cannot find vehicle with id " + id);
        }

        public List<VehicleDto> filter (Map<String, String[]> queryMap) {
            List<VehicleDto> dtos = new ArrayList<>();
            List<Vehicle> filteredVehicles;
            if (queryMap.size() == 2 && queryMap.containsKey("selectedPage") && queryMap.containsKey("numberOfRecordsPerPage")) {
                filteredVehicles = vehicleDAO.getAllVehicles();
            } else {
                filteredVehicles = vehicleDAO.getFilteredVehicles(queryMap);
            }

            if (queryMap.containsKey("selectedPage") && queryMap.containsKey("numberOfRecordsPerPage")) {
                int page = Integer.parseInt(queryMap.get("selectedPage")[0]);
                int perPage = Integer.parseInt(queryMap.get("numberOfRecordsPerPage")[0]);

                int from = (page - 1) * perPage;
                int to = Math.min(from + perPage, filteredVehicles.size());

                for (Vehicle vehicle: filteredVehicles.subList(from, to)) {
                    dtos.add(VehicleMapper.vehicleToDto(vehicle));
                }
            } else {
            for (Vehicle vehicle: filteredVehicles) {
                dtos.add(VehicleMapper.vehicleToDto(vehicle));
            }
        }
        return dtos;
    }

    public Double getPowerSum () {
        return vehicleDAO.getEnginePowerSum();
    }

    public String getFuelUnique () {
        List<FuelType> fuels = vehicleDAO.getUniqueFuelTypes();
        StringBuilder response = new StringBuilder();
        for (FuelType fuel: fuels) {
            response.append(fuel.toString());
            response.append(", \n");
        }
        response.delete(response.length() - 2, response.length() - 1);
        return response.toString();
    }

    public VehicleDto getNameMin () {
        Vehicle vehicle = vehicleDAO.getMinName();
        return VehicleMapper.vehicleToDto(vehicle);
    }
}