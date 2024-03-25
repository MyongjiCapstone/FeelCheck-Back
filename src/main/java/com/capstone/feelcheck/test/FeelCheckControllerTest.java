//package com.capstone.feelcheck.test;
//
//import org.springframework.web.bind.annotation.*;
//
//@RestController //Controller를 넣어야 Spring이 메모리에 넣음
//public class FeelCheckControllerTest {
//    private static final String TAG = "FeelCheckControllerTest : ";
//
//    @GetMapping("/http/lombok")
//    public String lombokTest(){
//        Member member = Member.builder().username("chaechae").password("1255").email("chae@mju.ac.kr").build();
//        System.out.println(TAG+"getter : "+member.getUsername());
//        member.setUsername("ohoho");
//        System.out.println(TAG+"setter : "+member.getUsername());
//        return "lombok test 완료";
//    }
//    @GetMapping("/http/get")
//    public String getTest(Member member) {
//        return "get 요청 : "+member.getId()+", "+member.getUsername()+", "+member.getPassword()+", "+member.getEmail();
//    }
///*    public String getTest(@RequestParam int id, @RequestParam String username) {
//        return "get 요청 : "+id+", "+username;
//    }*/
//    @PostMapping("/http/post")
//    public String postTest(@RequestBody Member member) {
//        return "post 요청 : "+member.getId()+", "+member.getUsername()+", "+member.getPassword()+", "+member.getEmail();
//    }
//    @PutMapping("/http/put")
//    public String putTest(@RequestBody Member member) {
//        return "put 요청 : "+member.getId()+", "+member.getUsername()+", "+member.getPassword()+", "+member.getEmail();
//    }
//    @DeleteMapping("/http/delete")
//    public String deleteTest(@RequestBody Member member) {
//        return "delete 요청 : "+member.getId()+", "+member.getUsername()+", "+member.getPassword()+", "+member.getEmail();
//    }
//
//
//
//    // http://localhost:8080/test/hello
//    @GetMapping("/test/hello")
//    public String hello() {
//        return "<h1>Hello Spring Boot</h1>";
//    }
//}
