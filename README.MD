# 📚 도서 관리 시스템 (Book Management System)

Java로 구현한 콘솔 기반 도서 CRUD 프로그램입니다.  
MVC 패턴을 적용하여 구조를 분리했으며, SQLite 데이터베이스로 데이터를 영속 저장합니다.

---

## 🗂️ 프로젝트 구조

```
DoSay/
└── src/
    ├── Main.java                          # 진입점
    ├── model/
    │   ├── Book.java                      # 도서 데이터 모델 (id, title, author)
    │   └── BookDAO.java                   # SQLite 연동 및 CRUD 처리
    ├── controller/
    │   └── LibraryController.java         # 흐름 제어 및 예외 처리
    ├── view/
    │   └── LibraryView.java               # 메뉴 출력 및 사용자 입출력
    └── database/
        └── library.db                     # SQLite DB 파일 (최초 실행 시 자동 생성)
```

---

## ⚙️ 개발 환경

- **Language**: Java 21 이상
- **DB**: SQLite (`sqlite-jdbc` 라이브러리 필요)
- **빌드 도구**: 없음 (javac 직접 컴파일)

---

## 📋 주요 기능

| 번호 | 기능 | 설명 |
|------|------|------|
| 1 | 도서 추가 | 제목과 저자를 입력하여 DB에 저장 |
| 2 | 도서 정보 수정 | 제목·저자로 검색 후 선택한 도서 수정 |
| 3 | 도서 목록 | 등록된 모든 도서 출력 |
| 4 | 도서 삭제 | 제목·저자로 검색 후 선택한 도서 삭제 |
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

>> 2
검색할 책 제목(공백 시 무시) >> 클린
검색할 책 저자(공백 시 무시) >>
======== List ========
1. 클린 코드 - 로버트 마틴
======================
>> 1
책 제목 >> 클린 코드 (Clean Code)
책 저자 >> 로버트 C. 마틴
처리가 완료되었습니다.
```

---

## 🏗️ 아키텍처

**MVC 패턴** 기반으로 설계되었습니다.

- **Model** (`Book.java`, `BookDAO.java`): `Book`은 id·title·author 필드를 가진 데이터 클래스, `BookDAO`는 SQLite 연동 및 CRUD(검색·추가·수정·삭제) 담당
- **View** (`LibraryView.java`): 메뉴 출력, 사용자 입력 수집, 도서 목록·메시지 출력 담당
- **Controller** (`LibraryController.java`): `BookDAO`를 통해 DB와 상호작용하며 흐름 제어 및 입력 예외 처리 담당