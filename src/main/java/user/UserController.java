package user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    // TODO: 깃헙 푸시 테스트 -> 삭제 예정

    // /users API : 모든 유저 조회
    @GetMapping("/users")
    public ResponseEntity<?> getUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    // /users/{id} API : 특정 유저 조회
    @GetMapping("/users/{id}")
    public ResponseEntity<?> getUser(@PathVariable Integer id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }
}
