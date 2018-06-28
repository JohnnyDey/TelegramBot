package bot;

import jpa.entity.User;
import jpa.service.UserService;

import javax.inject.Inject;

public abstract class AbstractBot {

    @Inject
    private UserService userServiceImp;

    public void registerIfNeed(User user){
        User storedUser = userServiceImp.getUserByTelegramOrVkId(user.getTelegramId(), user.getVkId());
        if(storedUser == null){
            userServiceImp.saveUser(user);
        }
    }
}
