<center># Fortune(오늘의 운세) </center>

- 비트캠프 2차 해커톤.
- 이름과 나이, 성별을 입력하면 오늘의 운세를 알려주는 프로그램입니다.
- 해커톤 멤버 : **오세준**, **박민섭**

## 일지

### 10/21

- 주제선정 및 리파짓토리 생성, 역할 분담.
- MemberJoinCommand, MemberLoginCommand, FortuneAddCommand, FortuneResponseCommand 생성

- 클라이언트에게 사용할 수 있는 명령을 보여주는 CommandListCommand생성

### 10/22

- 로그인 된 회원 정보를 저장할 수 있는 방법을 찾는 중.
- 일반회원 / 관리자 전용 커맨드창 만들기
- 로그아웃 기능

- 회원이 좋아요 한 운세 목록, 여태 봤던 운세 리스트, 기본 운세 문구 추가
- 매니저 권한 -  불량회원 딱지 기능 구현
- UI수정 - 아스키 아트를 이용하여 화면 구성
- AdminMemberGradeCommand생성, Board관련 CRUD생성, 로그인 후 관리자/일반회원에게 커맨드 창을 보여주는 ShowXXXCommandListCommand생성
- 회원이 글을 쓸 수 있는 게시판 CRUD를 App목적과 취지에 맞게 삭제함.
- 출력되는 문구를 다듬었음

### 10/23

- 오늘의 운세 보기 기능에서 엔터를 치면 북마크를 하지 않고 빠져나오도록 구현
- 운세/점심을 볼때 Thread.sleep으로 1초 뒤에 문구가 뜨도록 수정함.

## 구현된 기능

- 회원 로그인, 회원가입 기능
- 운세 글귀 추가, 운세글귀 출력

- 로그인에서 관리자/일반회원계정으로 접속할 수 있도록 구현
- 관리자가 불량회원 / 우수회원 태그 추가기능
- 점심추천기능
- 유저가 good이라고 표시한 글귀를 리스트에 저장하여 볼 수 있게 하였음. 
- 비로그인 상태일대 관리자 메뉴로 접속되는 버그 수정.

## 버그

* admin계정이 중복저장 되고 있음. -> jason에 admin계정을 미리 넣어두는 것으로 했다.

- 로그인한 회원 정보를 불러오는 것이 되지 않음. -> 멤버를 스태틱으로 바꿔서 직접 멤버객체를 집어넣는 것으로 해결 했으나, 인스턴스로 변환할 방법이 필요함.

- 로그아웃 기능이 필요함

## 시연순서

1. 회원가입 하기 전 비회원인 상태에서 관리자 메뉴로 접속하려고 할 경우 경고가 뜬다는 것을 시연한다.(**b 버튼은 절대 누르지 말자. 경고창과 동시에 리스트 보는것이 동시에 실행된다.**)
2. 회원가입을 한다.
3. 회원가입 한 회원으로 로그인 한다.
4. 일반회원인 상태에서도 관리자 메뉴에 접근이 불가능 하다는 것을 시연한다.(역시 b버튼은 누르지 않도록 한다.)
5. 내 정보 보기, 내 정보 바꾸기, 날 지우기와 같은 기본적인 CRUD시연한다.
6. 시연할 때 가입했던 계정이 삭제되었으니 기존에 넣어두었던 park계정으로 아래 계획에 맞춰서 순차적으로 시연한다.
7. 오늘의 운세, 북마크기능
8. 날 위로해줬던 북마크 기능
9. 점심 뭐먹지?
10. 관리자 계정으로 전환한다.(admin으로 다시로그인)
11. 회원 상세보기, 전체회원 리스트, 회원관리에서 일반 회원들 등급을 변경할 수 있다.
12. 운세문구 추구랑, 점심메뉴 추가 시연
13. 위의 시연 확인은 혹시 확인이 필요하다면 jason파일을 열어 확인시켜준다.



## 느낀점

**오세준** : 

생각했던것보다 어려웠던것 같다. 더 공부가 필요하다는 생각이 들었다.



**박민섭** : 

처음에 로그인 기능 구현할때 인스턴스 객체가 서로의 정보를 저장하지 못해서 로그인/로그아웃 연동이 되지 않았던 것이 어려웠다. 그리고 아스키 아트로 화면 구성은 처음 해봤는데 휴대폰이 막 보급하기 시작한 2000년대 초반으로 돌아간 것 같아서 재밌었다. 2차 해커톤을 통해서 C/S프로그래밍에 대한 이해를 더 높일 수 있는 계기가 되었던 것 같다.  
