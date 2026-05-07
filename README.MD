# 📚 도서 관리 시스템 (Book Management System)

Java로 구현한 콘솔 기반 도서 CRUD 프로그램입니다.  
MVC 패턴을 적용하여 구조를 분리했습니다.

---

## 🗂️ 프로젝트 구조

```
BankSystem/
├── Main.java                      # 진입점
├── model/
│   └── BankModel.java             # 도서 데이터 모델 (Book 클래스)
├── controller/
│   └── BankController.java        # 비즈니스 로직 및 CRUD 처리
└── view/
    └── BankView.java              # 사용자 입출력 처리
```

---

## ⚙️ 개발 환경

- Language: Java 21 이상 (Unnamed Main Method 사용)
- 빌드 도구: 없음 (javac 직접 컴파일)

---

## 📋 주요 기능

| 번호 | 기능 | 설명 |
|------|------|------|
| 1 | 도서 추가 | 책 제목과 저자를 입력하여 목록에 추가 |
| 2 | 도서 정보 수정 | 제목으로 검색 후 선택한 책의 정보를 수정 |
| 3 | 도서 목록 | 현재 등록된 모든 도서를 출력 |
| 4 | 도서 삭제 | 제목으로 검색 후 선택한 책을 삭제 |
| 0 | 프로그램 종료 | 프로그램 종료 |

---

## 🖥️ 실행 예시

```
1. 도서 추가
2. 도서 정보 수정
3. 도서 목록
4. 도서 삭제
0. 프로그램 종료
>> 1
책 제목 >> 클린 코드
책 저자 >> 로버트 마틴
처리가 완료되었습니다.

>> 3
======== List ========
1. 클린 코드 - 로버트 마틴
======================
```

---

## 🏗️ 아키텍처

**MVC 패턴** 기반으로 설계되었습니다.

- **Model** (`BankModel.java`): 도서 데이터를 표현하는 `Book` 클래스 (제목, 저자 필드 및 getter/setter)
- **View** (`BankView.java`): 메뉴 출력, 사용자 입력 수집, 목록 출력 담당
- **Controller** (`BankController.java`): `List<Book>`으로 데이터를 관리하며 create/find/update/delete 로직 처리