package twitter.mapper;

import twitter.entity.user.User;

public interface UserMapper {

    User mapUploadFileStringToUser(String userAsString);
    User mapFileStringToUser(String userAsString);
}
