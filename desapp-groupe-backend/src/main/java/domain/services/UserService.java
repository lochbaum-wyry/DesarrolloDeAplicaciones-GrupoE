package domain.services;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.services.oauth2.model.Userinfoplus;
import domain.*;
import domain.exceptions.SingUpException;
import domain.exceptions.SubiQueTeLlevoException;
import domain.repositories.RouteRepository;
import domain.repositories.UserRepository;
import helpers.UserAuthorization;
import org.springframework.transaction.annotation.Transactional;


import java.lang.*;
import java.util.List;

public class UserService {


    private UserRepository userRepository;
    private RouteRepository routeRepository;

    private UserTokenService userTokenService;

    public UserService(){}

    public UserService(UserRepository userRepository,RouteRepository routeRepository,UserTokenService userTokenService){
        this.userRepository = userRepository;
        this.routeRepository = routeRepository;
        this.userTokenService = userTokenService;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    @Transactional
    public Boolean existUser(Userinfoplus userinfoplus){
        return userRepository.getUserByEmail(userinfoplus.getEmail()) != null;
    }


    @Transactional
    public User signUp(String name, String lastName, String userName, String email,String image) throws SingUpException
    {
        validateUser(userName,email);
        User user = new User(name,lastName,userName,email);
        user.setImage(image);

        userRepository.save(user);

        return user;
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
    public void addRoute(User user, Route route) throws SubiQueTeLlevoException {
        try {
            user.addRoute(route);
            userRepository.update(user);
        } catch (Exception e) {
            throw new SubiQueTeLlevoException(e);
        }
    }

//    @Transactional
//    public void addRoute(User user, List<LatLng> points, Float distanceInKms, Float fixedCosts, List<Schedule> schedules) throws SubiQueTeLlevoException {
//        try {
//            Route route = new Route(distanceInKms, fixedCosts, points, schedules);
//            routeRepository.save(route);
//            user.addRoute(route);
//            userRepository.update(user);
//        } catch (Exception e) {
//            throw new SubiQueTeLlevoException(e);
//        }
//    }
//
    @Transactional
    public User login(String email) {
        return userRepository.getUserByEmail(email);
    }

    @Transactional
    public User getUser(Integer id) {
        return userRepository.findById(id);
    }

    @Transactional
    public User signUpWithCredentials(Userinfoplus userinfoplus, GoogleOauthCredential googleOauthCredential)
    {
        User newUser = this.signUp2(userinfoplus.getName(),userinfoplus.getFamilyName(),userinfoplus.getEmail(),userinfoplus.getPicture());
        newUser.setToken(googleOauthCredential);
        userRepository.update(newUser);

        return newUser;
    }

    @Transactional
    public User signUp2(String fullName,String familyName, String email,String picture)
    {
        User user = new User(fullName,familyName,fullName,email);
        user.setImage(picture);
        userRepository.save(user);
        user = userRepository.findById(user.getId());

        userTokenService.create(user);

        return user;
    }

    @Transactional
    public List<User> getUsers()
    {
        return userRepository.findAll();
    }
}