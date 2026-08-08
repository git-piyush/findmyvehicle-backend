package com.findmyvehicle.util;

import com.findmyvehicle.dto.AddressDto;
import com.findmyvehicle.dto.UserProfile;
import com.findmyvehicle.dto.home.DashboardData;
import com.findmyvehicle.entity.Address;
import com.findmyvehicle.entity.User;
import com.findmyvehicle.entity.home.HomeDashData;
import com.findmyvehicle.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MapperService {

    @Autowired
    private UserRepository userRepository;

    public DashboardData homeDashDataToDashboardDate(HomeDashData homeDashData){
        DashboardData dashboardData = new DashboardData();

        //header
        dashboardData.getHeader().setTitle(homeDashData.getTitle());
        dashboardData.getHeader().setHighlightedWord(homeDashData.getHighlightedWord());
        dashboardData.getHeader().setDescription(homeDashData.getDescription());
        dashboardData.getHeader().setSearchPlaceholder(homeDashData.getSearchPlaceholder());


        //Statistics
        //box1
        dashboardData.getStatistics().getBox1().setValue(homeDashData.getBox1Value());
        dashboardData.getStatistics().getBox1().setLabel(homeDashData.getBox1Label());
        dashboardData.getStatistics().getBox1().setDescription(homeDashData.getBox1Description());
        dashboardData.getStatistics().getBox1().setIcon(homeDashData.getBox1Icon());

        //box2
        dashboardData.getStatistics().getBox2().setValue(homeDashData.getBox2Value());
        dashboardData.getStatistics().getBox2().setLabel(homeDashData.getBox2Label());
        dashboardData.getStatistics().getBox2().setDescription(homeDashData.getBox2Description());
        dashboardData.getStatistics().getBox2().setIcon(homeDashData.getBox2Icon());

        //box3
        dashboardData.getStatistics().getBox3().setValue(homeDashData.getBox3Value());
        dashboardData.getStatistics().getBox3().setLabel(homeDashData.getBox3Label());
        dashboardData.getStatistics().getBox3().setDescription(homeDashData.getBox3Description());
        dashboardData.getStatistics().getBox3().setIcon(homeDashData.getBox3Icon());

        //box4
        dashboardData.getStatistics().getBox4().setValue(homeDashData.getBox4Value());
        dashboardData.getStatistics().getBox4().setLabel(homeDashData.getBox4Label());
        dashboardData.getStatistics().getBox4().setDescription(homeDashData.getBox4Description());
        dashboardData.getStatistics().getBox4().setIcon(homeDashData.getBox4Icon());

        return dashboardData;
    }

    public UserProfile userToUserProfile(User user) {
        Address address = user.getAddress();
        UserProfile userProfile = new UserProfile();
        userProfile.setId(user.getId());
        userProfile.setName(user.getName());
        userProfile.setEmail(user.getEmail());
        userProfile.setPhone(user.getPhoneNumber());

        if(address!=null){
            userProfile.getAddress().setId(address.getId());
            userProfile.getAddress().setAddressLine1(address.getAddressLine1());
            userProfile.getAddress().setAddressLine2(address.getAddressLine2());
            userProfile.getAddress().setCity(address.getCity());
            userProfile.getAddress().setCountry(address.getCountry());
            userProfile.getAddress().setState(address.getState());
            userProfile.getAddress().setPinCode(address.getPinCode());
        }
        return userProfile;
    }

    public User userProfileToUser(UserProfile userProfile, User user) {
        Address address = user.getAddress();
        if(address==null){
            address = new Address();
        }
        if(userProfile.getEmail()!=null){
            user.setEmail(userProfile.getEmail());
        }
        if(userProfile.getName()!=null){
            user.setName(userProfile.getName());
        }
        if(userProfile.getPhone()!=null){
            user.setPhoneNumber(userProfile.getPhone());
        }
        AddressDto addressDto = userProfile.getAddress();

        if(addressDto.getAddressLine1()!=null && !addressDto.getAddressLine1().trim().isEmpty()){
            address.setAddressLine1(addressDto.getAddressLine1());
        }
        if(addressDto.getAddressLine2()!=null && !addressDto.getAddressLine2().trim().isEmpty()){
            address.setAddressLine2(addressDto.getAddressLine2());
        }
        if(addressDto.getCity()!=null && !addressDto.getCity().trim().isEmpty()){
            address.setCity(addressDto.getCity());
        }
        if(addressDto.getState()!=null){
            address.setState(addressDto.getState());
        }
        if(addressDto.getState()!=null){
            address.setState(addressDto.getState());
        }
        if(addressDto.getPinCode()!=null && !addressDto.getPinCode().trim().isEmpty()){
            address.setPinCode(addressDto.getPinCode());
        }
        if(addressDto.getCountry()!=null && !addressDto.getCountry().trim().isEmpty()){
            address.setCountry(addressDto.getCountry());
        }
        address.setUser(user);
        user.setAddress(address);
        return user;
    }
}
