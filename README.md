# java-convenience-store-precourse

## 프로그램 흐름

1. 컨트롤러에서 편의점 프로그램 실행
2. 상품 목록 마크다운 로딩
3. 상품 목록 생성
4. 프로모션 목록 마크다운 로딩
5. 프로모션 목록 생성
6. 재고에 상품 등록

===== ⬆️최초 실행시에만 돌아가는 로직 ⬆️=====

7. 편의점 소개
8. 보유상품 출력
9. 손님이 구매할 상품과 수령 입력
10. 편의점 서비스 생성 (상품재고, 손님) 데이터 넘김

===== ⬆️상품 재고 확인, 상품 재고 갱신 등의 주요 로직이 돌아가는 부분 ⬆️=====

11. 멤버십 할인 여부
12. 영수증 출력
13. 재구매의사
14. 7~12 반복

## 구현할 기능 목록

Model

1. [x] 상품
    * [x] 상품 리스트 생성
2. [x] 프로모션
    * [x] 프로모션 리스트 생성
3. [ ] 고객
    * [x] 입력된 상품 가공
    * [x] 상품 담기
    * [x] 추가 상품 담기
    * [x] 장바구니 물건 빼기
4. [x] 상품 재고
    * [x] 재고에 상품 등록
    * [x] 일반 상품 추가 (프로모션 제품만 있고 일반 상품에 대한 정보가 없는 상품에 한정하여)
    * [x] 상품 재고 갱신
    * [x] 전체 상품 수량 반환
        * [x] 유효성 검사
    * [x] 프로모션 상품 수량 반환
    * [x] 일반 상품 수량 반환
    * [x] 프로모션 정보 반환
    * [x] 재고, 프로모션 초기화
5. [x] 상품 가격 enum

Service

1. [x] 구매 가능한 재고인지
2. [x] 프로모션이 가능한데 상품을 모자르게 가져왔을 때 프로모션을 받을건지
3. [x] 프로모션 가능 수량보다 많은 제품을 구매했을 때 정가로 구매할건지
4. [x] 멤버십 적용 유무
5. [x] 전체 구매 정보 반환
6. [x] 증정 상품명 개수 저장
7. [x] 할인 내역
8. [x] 최종 결제 금액

View

1. [x] 출력 문구 enum
2. [x] 편의점 이름 및 현재 보유 상품 출력 기능
3. [x] 구매할 상품명과 수량 입출력 기능
    * [x] 유효성 검사
4. [x] 프로모션 재고 부족시 정가 구매 여부 입출력 기능
    * [x] 유효성 검사
5. [x] 프로모션이 가능한데 안 가져온 경우 추가로 가져올건지 여부 입출력 기능
    * [x] 유효성 검사
6. [x] 멤버십 할인 유무 입출력 기능
    * [x] 유효성 검사
7. [x] 영수증 출력 기능
8. [x] 재구매 여부 입출력 기능
    * [x] 유효성 검사
9. [x] Y / N 입력 기능
    * [x] 유효성 검사

Controller

1. [x] 편의점 실행
    * [x] 편의점 초기 설정
    * [x] 편의점 환영 인사
    * [x] 편의점 물건 구매
    * [x] 물건 구매 영수증
    * [x] 재구매 의사 물어보기
    * [x] stockManager 초기화
2. [x] 오류 발생시 해당 지점부터 재시작

예외

1. [x] 예외 메세지 enum

유틸리티

1. [x] 텍스트 파일 로더

테스트

1. [x] 하나의 상품이 추가되는지 (Customer)
2. [x] 원하는 만큼 상품이 줄어드는지 (Customer)
3. [x] 이름으로 상품가격이 가져와지는지 (ProductPrice)
4. [x] 상품이 정상적으로 만들어지는지 (Product)
5. [x] 프로모션이 정상적으로 만들어지는지 (Promotion)
6. [x] 상품이 정상적으로 등록되는지
7. [x] 해당 상품 전체 수량 반환이 되는지
8. [x] 해당 상품 프로모션 상품 수량 반환이 되는지
9. [x] 해당 상품 일반 상품 수량 반환이 되는지
