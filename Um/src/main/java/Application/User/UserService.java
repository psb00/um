package Application.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User registerNewUserAccount(User user) {
        // 여기에 회원가입 로직 구현 (비밀번호 암호화, 유효성 검사 등)
        return userRepository.save(user);
    }

    // 기타 필요한 메소드
}