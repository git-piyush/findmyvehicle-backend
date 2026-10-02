package com.findmyvehicle.serviceImpl.vehicleImpl;

import com.findmyvehicle.dto.vehicle.VehicleDto;
import com.findmyvehicle.entity.vehicle.MissingDetails;
import com.findmyvehicle.entity.vehicle.Vehicle;
import com.findmyvehicle.entity.vehicle.VehicleImage;
import com.findmyvehicle.enums.VehicleStatus;
import com.findmyvehicle.repository.vehicle.VehicleRepository;
import com.findmyvehicle.service.vehicle.VehicleService;
import com.findmyvehicle.util.ImageService;
import com.findmyvehicle.util.MultiFunctionUtility;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private ModelMapper mapper;

    @Autowired 
    private ImageService imageService;

    @Autowired
    private MultiFunctionUtility multiFunctionUtility;

    @Override
    public Boolean existsByRegNumber(String regNumber) {
        return vehicleRepository.existsByRegNumber(regNumber);
    }

    @Override
    public List<Vehicle> getRecentMissingVehicles(Integer count) {
        if (count == null || count <= 0) {
            return List.of();
        }
        return vehicleRepository.findDistinctByMissingDetails_VehicleStatusOrderByCreatedDateDesc(
                VehicleStatus.MISSING, PageRequest.of(0, count));
    }

    @Override
    @Transactional
    public Vehicle reportMissingVehicle(VehicleDto vehicleDto, List<MultipartFile> imageFile) {

        Vehicle vehicle = mapper.map(vehicleDto, Vehicle.class);



        MissingDetails missingDetails =
                mapper.map(vehicleDto.getMissingDetails(), MissingDetails.class);

        missingDetails.setVehicleStatus(VehicleStatus.MISSING);
        missingDetails.setVehicle(vehicle);

        vehicle.setMissingDetails(List.of(missingDetails));
        vehicle.setReportedBy(multiFunctionUtility.getCurrentUser());


        List<String> urls =  imageService.uploadVehicleImages(vehicle.getRegNumber(), imageFile);
        List<VehicleImage> vehicleImages = new ArrayList<VehicleImage>();

        if(urls != null && !urls.isEmpty()){
            for(String url: urls){
                VehicleImage vehicleImage = new VehicleImage();
                vehicleImage.setVehicle(vehicle);
                vehicleImage.setImageUrl(url);
                vehicleImages.add(vehicleImage);
            }
            vehicle.setImages(vehicleImages);
        }

        return vehicleRepository.save(vehicle);
    }
}
