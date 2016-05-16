package domain.services;

import domain.*;
import domain.exceptions.SingUpException;
import domain.exceptions.SubiQueTeLlevoException;
import domain.repositories.RouteRepository;
import domain.repositories.UserRepository;
import org.springframework.transaction.annotation.Transactional;


import java.lang.*;
import java.util.List;

public class UserService {


    private UserRepository userRepository;
    private RouteRepository routeRepository;

    public UserService(){}

    public UserService(UserRepository userRepository,RouteRepository routeRepository){
        this.userRepository = userRepository;
        this.routeRepository = routeRepository;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    @Transactional
    public void signUp(String name, String lastName, String userName, String email) throws SingUpException
    {
        validateUser(userName,email);
        User user = new User(name,lastName,userName,email);

        userRepository.save(user);
    }

    private void validateUser(String userName, String email) throws SingUpException {
        validateNotExistingUserName(userName);
        validateNotExistingEmail(email);

    }

    private void validateNotExistingEmail(String email) throws SingUpException {
        User user = userRepository.getUserByEmail(email);
        if (user != null){
            throw new SingUpException();
        }
    }

    private void validateNotExistingUserName(String userName) throws SingUpException {
        User user = userRepository.getUserByUserName(userName);
        if(user != null){
            throw new SingUpException();
        }
    }

    @Transactional
    public void addVehicleForUser(User user,Vehicle vehicle){
        user.setVehicle(vehicle);
        userRepository.update(user);
    }

    @Transactional
    public void addRoute(User user, List<LatLng> points, Float distanceInKms, Float fixedCosts, List<Schedule> schedules) throws SubiQueTeLlevoException {
        try {
            Route route = new Route(distanceInKms, fixedCosts, points, schedules);
            routeRepository.save(route);
            user.addRoute(route);
            userRepository.update(user);
        } catch (Exception e) {
            throw new SubiQueTeLlevoException(e);
        }
    }

    public User login(String email, String token) {
        return userRepository.getUserByEmail(email);
    }
}