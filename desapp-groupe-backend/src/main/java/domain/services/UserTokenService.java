package domain.services;

import domain.User;
import domain.UserToken;
import domain.repositories.UserTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



public class UserTokenService {

    private UserTokenRepository userTokenRepository;

    public UserTokenService(){}

    @Transactional
    public UserToken create(User userModel)
    {
        UserToken token = new UserToken();
        token.setUserModel(userModel);
        token.generateToken();
        this.getUserTokenRepository().save(token);

        token = this.getUserTokenRepository().findById(token.getId());

        return token;
    }

    public UserTokenService(UserTokenRepository userTokenRepository){
        this.userTokenRepository =userTokenRepository;
    }

    public UserTokenRepository getUserTokenRepository() {
        return userTokenRepository;
    }

    @Autowired
    public void setUserTokenRepository(UserTokenRepository userTokenRepository) {
        this.userTokenRepository = userTokenRepository;
    }

    @Transactional
    public UserToken findByUserId(int id) {
        return userTokenRepository.findByUserId(id);
    }
}
