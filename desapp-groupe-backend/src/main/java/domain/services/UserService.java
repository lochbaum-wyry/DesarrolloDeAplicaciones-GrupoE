package domain.services;

import domain.Vehicle;
import domain.exceptions.SubiQueTeLlevoException;
import domain.repositories.UserRepository;
import domain.User;

import java.lang.*;
import java.lang.System;


public class UserService {//extends GenericService<User> { //TODO :deberia quedr la herencia ?

    //private static final long serialVersionUID = 2131359482422367092L;

    private UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }


    public void singUp(String name, String lastName, String userName, String email) throws SubiQueTeLlevoException
    {

        if(validateUser(userName,email)){

            User user = new User(name,lastName,userName,email);

            userRepository.save(user);
            System.out.print(userRepository.findById(user.getId()));
        }
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    private boolean validateUser(String userName, String email) {
        return isValidUserName(userName) && isValidEmail(email);
    }

    private boolean isValidUserName(String userName) {
        //TODO : consulta bd, fijandose si ya existe o no
        return true;
    }

    private boolean isValidEmail(String email) {
        //TODO : consulta bd, fijandose si ya existe o no
        return true;
    }

    public void addVehicleForUser(User user,Vehicle vehicle){
        user.setVehicle(vehicle);
        userRepository.update(user);
    }

    public void addRoute(User user){
        //TODO :  addRoute ( user , datos de la ruta , schedules de la ruta )

    }

    //TODO : falta pasar todo lo de rideRequest



}