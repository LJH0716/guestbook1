--테이블 삭제
drop table guestbook;

--시퀀스 삭제
drop sequence seq_guestbook_no;

--테이블 생성
create table guestbook(
                         no number ,
                         name varchar2(80) ,
                         password varchar2(20),
                         content varchar2(2000),
                         reg_date date,
                         primary key(no)
                   );
                   
--시퀀스 생성
create sequence seq_guestbook_no
increment by 1 
start with 1
nocache;

commit;
rollback;

select  no 식별번호
        ,name 이름
        ,password 비밀번호
        ,content 본문
        ,reg_date 등록일
from guestbook;

