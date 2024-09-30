package user;

import core.error.ex.ExceptionApi404;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    // 모든 유저 조회
    public List<Map<String, Integer>> getAllUsers() {
        return userRepository.findAll().stream()
                .map(user -> Map.of("id", user.getId()))
                .collect(Collectors.toList());
    }

    // 특정 유저 조회
    public User getUserById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ExceptionApi404("유저를 찾을 수 없습니다."));
    }
}
