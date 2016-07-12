package domain.services;

import com.google.api.services.oauth2.model.Userinfoplus;
import domain.GoogleOauthCredential;
import domain.Route;
import domain.User;
import domain.Vehicle;
import domain.exceptions.SingUpException;
import domain.exceptions.SubiQueTeLlevoException;
import domain.repositories.RouteRepository;
import domain.repositories.UserRepository;
import domain.repositories.VehicleRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class UserService {


    private UserRepository userRepository;
    private VehicleRepository vehicleRepository ;
    private RouteRepository routeRepository;

    private UserTokenService userTokenService;

    public UserService(){}

    public UserService(UserRepository userRepository,RouteRepository routeRepository,VehicleRepository vehicleRepository,UserTokenService userTokenService){
        this.userRepository = userRepository;
        this.routeRepository = routeRepository;
        this.userTokenService = userTokenService;
        this.vehicleRepository = vehicleRepository;
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
        user = userRepository.findById(user.getId());

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
    public void addVehicleForUser(User user,Vehicle vehicle) throws SubiQueTeLlevoException {
        if(user.getVehicle() == null) {
            user.setVehicle(vehicle);
            vehicle.setOwner(user);
            userRepository.saveOrUpdate(user);
        }
    }

    @Transactional
    public void addRoute(User user, Route route) throws SubiQueTeLlevoException {
        try {
            user.addRoute(route);
            userRepository.saveOrUpdate(user);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SubiQueTeLlevoException(e);
        }
    }

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

        newUser = userRepository.findById(newUser.getId());

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


    public VehicleRepository getVehicleRepository() {
        return vehicleRepository;
    }

    public RouteRepository getRouteRepository() {
        return routeRepository;
    }

    @Transactional
    public void updateUser(User user) throws SubiQueTeLlevoException {
        //tal vez podriamos validar mejor
        userRepository.saveOrUpdate(user);
    }
}