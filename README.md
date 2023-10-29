<h1 align="center">Welcome to simple todo list 👋</h1>
<p>

</p>

> ***간단한 TODO List 서비스*** <br>
> ---
> **기능**<br>
> 
> `회원` <br>
> 1. 회원가입 및 탈퇴<br>
> 2. 로그인 및 로그아웃<br>
> 3. 회원정보 수정 <br>
> 4. 전체 회원 조회<br>
>
> `아이템` <br>
> 1. 아이템 추가, 수정 및 삭제<br>
> 2. 아이템 상태 수정
> 3. 아이템 전체 목록 조회
> 4. 가장 최근 TODO 1개 조회

## ERD
![image](https://github.com/grey920/todo-list-moais/assets/58028215/1bc2ea52-e875-44ea-a459-814dfcd0f8d0)

## 디렉토리 구조
```
todo
├── config
│   └── SecurityConfig.java
├── service
│   ├── Item
│   │   ├── domain
│   │   │   ├── Item.java
│   │   │   └── ItemCnd.java
│   │   ├── repository
│   │   │   └── ItemMapper.java
│   │   └── service
│   │      ├── ItemService.java
│   │      └── ItemServiceImpl.java
│   └── user
│      ├── domain
│      │   ├── UserStatusHist.java
│      │   ├── User.java
│      │   └── UserCnd.java
│      ├── repository
│      │   ├── UserStatusHistMapper.java
│      │   └── UserMapper.java
│      └── service
│         ├── UserService.java
│         └── UserServiceImpl.java
└── web
   ├── api
   │   ├── ItemApiController.java
   │   └── UserApiController.java
   └── util
       └── SessionUtil.java
```
- 참고
  - Cnd : 검색시 검색 조건을 담는 객체

## 추후 개선 사항
- [ ] 아이템 카테고리 추가
- [ ] 아이템 검색 기능 추가

## Author

👤 **gyuwoon jung**

* Website: https://grey920.github.io/
* Github: [@grey](https://github.com/grey)
