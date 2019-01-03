package com.pkrok.controller;

import com.pkrok.domain.SigninRequest;
import com.pkrok.domain.SigninResponse;
import com.pkrok.domain.SignupRequest;
import com.pkrok.entity.UserEntity;
import com.pkrok.service.AuthService;
import com.pkrok.service.FileStorageServiceUserPhoto;
import com.pkrok.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("auth")
public class AuthController {
    private AuthService authService;
    private FileStorageServiceUserPhoto fileStorageServiceUserPhoto;

    @Autowired
    public AuthController(AuthService authService, FileStorageServiceUserPhoto fileStorageServiceUserPhoto) {
        this.authService = authService;
        this.fileStorageServiceUserPhoto=fileStorageServiceUserPhoto;
    }

    @PostMapping("signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest request) {
        authService.signup(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("signin")
    public ResponseEntity<?> signin(@RequestBody SigninRequest request) {
        String token = authService.signin(request);
        return new ResponseEntity<>(new SigninResponse(token), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UserEntity>> getAllUsersById() {
        return ResponseEntity.ok(authService.findAllUsersOrderById());
    }

    @DeleteMapping("{userId}")
    public ResponseEntity<?> deleteUserById(@PathVariable("userId") Long id) {
        authService.deleteUserById(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @PostMapping("{id}/{name}/{role}")
    public ResponseEntity<?> setUserById(@PathVariable ("id") Long id, @PathVariable("name") String name, @PathVariable ("role") String role ){
        authService.setUserById(id,name,role);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @GetMapping("{userId}")
    public ResponseEntity<UserEntity> getUserById(@PathVariable("userId") Long id) {
        return ResponseEntity.ok(authService.findUserById(id));
    }

    @GetMapping("username/{username}")
    public ResponseEntity<String> getUserById(@PathVariable("username") String username) {
        System.out.println(authService.findImageByUsername(username));
        return ResponseEntity.ok(authService.findImageByUsername(username));
    }

    @PostMapping("{userId}/image")
    public ResponseEntity<?> uploadImage(
            @PathVariable("userId") Long id,
            @RequestParam("file") MultipartFile file
    ) {
        if (FileUtil.isNotEmpty(file)) {
            System.out.println(file.getOriginalFilename());
        fileStorageServiceUserPhoto.storeFile(file);
        authService.addImageToUser(file.getOriginalFilename(), id);}
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping("image")
    public ResponseEntity<?> getFile(
            @RequestParam("fileName") String fileName,
            HttpServletRequest request) {

        Resource resource = fileStorageServiceUserPhoto.loadFile(fileName);

        String contentType = null;

        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline: filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
