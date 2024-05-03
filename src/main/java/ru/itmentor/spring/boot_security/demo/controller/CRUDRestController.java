package ru.itmentor.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.service.impl.UserService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/crud")
public class CRUDRestController {

    private final UserService userService;

    @Autowired
    public CRUDRestController(UserService userService) {
        this.userService = userService;
    }

    // ResponseEntity представляет http-ответ, используется для формирования ответа HTTP с пользовательскими параметрами
    @PostMapping(value = "/users")
    public ResponseEntity<?> create(@RequestBody User user) { // RequestBody говорит, что значение user подставляется из тела запроса
        userService.save(user); // Внутри тела метода мы вызываем метод save у ранее созданного сервиса
        System.out.println("POST");
        return new ResponseEntity<>(HttpStatus.CREATED); //201, а по умолчанию HttpStatus.OK - 200
    }


    //страничка со всеми юзерами из БД
    @GetMapping(value = "/users")
    public ResponseEntity<List<User>> read() { // возвращаем тело ответа (список юзеров), помимо HTTP статуса
        final List<User> users = userService.getAll(); // получаем список клиентов для возврата
        //return "userCRUD";
        return users != null &&  !users.isEmpty()
                ? new ResponseEntity<>(users, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 если список пуст
    }

    @GetMapping(path = "/users1", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<User> getAll() {
        return this.userService.getAll();
    }


    // возврат юзера по id
    @GetMapping(value = "/users/{id}")
    public ResponseEntity<User> read(@PathVariable long id) {
        final Optional<User> user = userService.getById(id);

        return !user.isEmpty()
                ? new ResponseEntity<>(user.get(), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404
    }

    @PutMapping(value = "/users/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody User user) {
        try {
            userService.update(id, user);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/users/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        try {
            userService.deleteById(id);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED); // 304
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }





}