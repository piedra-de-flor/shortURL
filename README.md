# Short Url

단축 URL 프로젝트
<br>
<br>

# 🕰️ 개발기간
* 2023.08.06 ~ 
<br>


# ⚙️ 개발환경
- 'java 17'
- **IDE** : intelliJ 2022.1.2
- Spring boot : 3.x.x
  
<br>
<br>

# 📌 기능 요구 사항
## 1. 시작
  #### - 단축을 원하는 url 입력
## 2. 진행
  #### - 1. 새로운 문자열 생성
  #### - 2. 새로운 문자열의 유효성 체크
    - 기존에 만든 url들과 겹치지는 않는지
  #### - 3. 겹치치 않을 시 생성한 문자열을 url로 저장
    -저장과 동시에 소멸 기간 타이머 작동
  #### - 5. 저장된 url을 입력된 url로 연결
  #### - 6. 사용자에게 url 데이터 보여주기
  #### - 7. 일정 기간마다 소멸기간이 다 된 url 데이터들은 삭제
<br>
<br>
<br>
<br>

# 📌 개발 기능 요구 사항

### ⌨️ UI
  #### 입력
    - 단축을 원하는 url 입력

  #### 출력
    - redirect까지 완료한 url 객체 정보 출력
    
### 🧾 비지니스 로직
  #### 랜덤값으로 8자리 문자열 생성
    - db에서 문자열의 중복 체크
    - http://www. 의 형식이 아닐 시 이 형식으로 변환
  #### 랜덤 문자열을 받아서 입력값과 함께 Url객체로 변환
  #### 입력값을 랜덤 문자열로 redirect
  #### 캐시 메모리 구현

### 🧾 API
  - Url CRUD
  - redirect

<br>
<br>
<br>


# Class diagram
![image](https://github.com/piedra-de-flor/shortURL/assets/101418352/85ce8615-aa3b-4dc0-9665-2dbc0c2e6eb9)

