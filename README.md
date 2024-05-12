# Marketplace

* 상품 결제 및 리뷰
----




ERD
---

 ![이미지 이름](https://github.com/somsomi3/Marketplace/blob/main/marketplaceERD2.png)



사용기술 스택
---
  <img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white"> <img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white"> <img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white"> <img src="https://img.shields.io/badge/Spring Security-6DB33F?style=for-the-badge&logo=Spring Security&logoColor=white">



프로젝트 기능 및 설계
---
* 회원가입
  * 아이디와 이메일은 unique 해야한다.
  * 일반인으로 가입 시  User 권한을 갖는다
  * 관리자 페이지로 가입 시 Manager 권한을 갖는다.

* 로그인/로그아웃+ 이메일 로그인
  * 로그인시 회원가입한적 없는 아이디/이메일을 이용하여 로그인을 시도하면 에러가 발생한다
  * 로그인시 비밀번호가 일치하지 않는다면 에러가 발생한다.
  * 관리자 로그인 시 상품을 등록 및 삭제, 회원 관리, 리뷰 삭제 등을 할 수 있다.

* 상품조회
  * 상품 검색은 로그인 여부와 관계없이 진행할 수 있다.
  * 상품 상세 조회 시 상품명, 가격, 제조일자, 제조사 등 상품에 대한 정보를 확인할 수 있다.
 
* 장바구니기능
  * 로그인 후 상품을 추가할 수 있다.
  * 로그인 후 상품목록을 확인 할 수 있다. 
  * 로그인 후 상품을 삭제할 수 있다. 

* 상품결제 
  * 장바구니 물건 중 선택하여 재고가 있는 물건에 한하여 결제를 가능하게끔 한다.
 
* 리뷰
  * 결제한 물건에 대한 사용자가 리뷰 작성, 수정이 가능하다.


Trouble Shooting 
---

