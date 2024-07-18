# Spring-Security-JWT
스프링 시큐리티 JWT

## JWT를 사용하는 이유

- 모바일 앱
JWT가 사용된 주 이유는 결국 모바일 앱의 등장입니다. 모바일 앱의 특성상 주로 JWT 방식으로 인증/인가를 진행합니다. STATLESS는 부수적인 효과

- 모바일 앱에서의 로그아웃
모바일 앱에서는 JWT 탈취 우려가 거의 없기 때문에 앱단에서 로그아웃을 진행하여 JWT 자체를 제거해버리면 서버측에선 추가 조치도 필요가 없습니다. (토큰 자체가 확실하게 없어졌다는 보장이 되기 때문에)

- 장시간 로그인과 세션
장기간 동안 로그인 상태를 유지하려고 세션 설정을 하면 서버 측 부하가 많이 가기 때문에 JWT 방식을 이용하는 것도 한 방법입니다.

## 모식도

|회원가입|로그인 (인증)|경로 접근 (인가)|
|---|---|---|
|![image](https://github.com/user-attachments/assets/7bf55a68-afab-4f9a-8589-a1aadec59cd2)|![image](https://github.com/user-attachments/assets/6684f28a-eca2-49bd-b80f-6c2293e360ae)|![image](https://github.com/user-attachments/assets/05ce123e-07cb-4368-a448-04e4a790c085)|
|내부 회원 가입 로직은 세션 방식과 JWT 방식의 차이가 없다.|로그인 요청을 받은 후 세션 방식은 서버 세션이 유저 정보를 저장하지만 JWT 방식은 토큰을 생성하여 응답한다.|JWT Filter를 통해 요청의 헤더에서 JWT를 찾아 검증을하고 일시적 요청에 대한 Session을 생성한다.<br>(생성된 세션은 요청이 끝나면 소멸됨)|

## 로그인 필터 구현

### 스프링 시큐리티 필터 동작 원리

스프링 시큐리티는 클라이언트의 요청이 여러개의 필터를 거쳐 DispatcherServlet(Controller)으로 향하는 중간 필터에서 요청을 가로챈 후 검증(인증/인가)을 진행한다.

|클라이언트 요청 → 서블릿 필터 → 서블릿 (컨트롤러)|Delegating Filter Proxy|서블릿 필터 체인의 DelegatingFilter → Security 필터 체인 (내부 처리 후) → 서블릿 필터 체인의 DelegatingFilter|SecurityFilterChain의 필터 목록과 순서|
|---|---|---|---|
|![image](https://github.com/user-attachments/assets/4ae92fc6-338a-4114-9cb4-f14d20cf8011)|![image](https://github.com/user-attachments/assets/62c64444-a184-4e8e-8dc5-5b779573f63c)|![image](https://github.com/user-attachments/assets/9cc151d1-5227-4409-84c5-2109414560a4)|![image](https://github.com/user-attachments/assets/f8c621e0-2f09-4169-8844-a187e6b58d11)|


