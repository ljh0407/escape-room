drop database if exists room;
create database room;
use room;
drop table if exists room;
create table room(
	mno int auto_increment primary key,
    mname varchar(10),
	mid varchar(15),
    mpw varchar(20),
    mphone varchar(15)
);

select * from room;

drop table if exists rboard;
create table rboard(
	rno int auto_increment ,  				-- 게시물번호
    rtitle varchar(100), 					-- 게시물제목
    rcontent varchar(1000),					-- 게시물내용
    rscore int,								-- 점수
	rfile varchar(1000),					-- 첨부파일
    rcomment varchar(100),					-- 댓글
    rdate datetime default now(),			-- 작성일
    rview int,								-- 조회수
    mno int, 								-- 회원번호
    mid varchar(100), 						-- 회원아이디
	constraint rno_pk primary key (rno),
    constraint rmno_fk foreign key (mno) references room(mno) on update cascade on delete cascade
   
);

select * from rboard;
update rboard set rcomment = 'ㅎㅇㅎㅇㅎㅇㅎㅇ' ;

select rb.* , r.mid from room r , rboard rb where r.mno = rb.mno and rb.rno = 7;


drop table if exists Questions;

create table Questions(
   	bno int auto_increment primary key ,
    btitle varchar(100) ,
    bcontent varchar(100) ,
    bfile longtext, -- 첨부파일
    bdate datetime default now(),
    reply varchar(1000), -- 댓글
    mno int not null,
    constraint mno_fk foreign key (mno) references room (mno) on update cascade on delete cascade   -- 회원 탈퇴시 
);


drop table if exists notice ;
create table notice(
   nno int auto_increment primary key,
   ntitle      varchar(1000) NOT NULL,       -- 제목
   ncontent   longtext NULL,            -- 내용
   ndate       datetime default now() NOT NULL   , -- 작성일
   nview      int default 0 NOT NULL,   -- 조회수
   mno int not null,
   constraint notice_mno_fk foreign key (mno) references room(mno) on update cascade on delete cascade 
);


select * from notice;

select * from admin;

drop table if exists reservation;
create table reservation(
   gno int auto_increment primary key,
    gname  varchar(100),
    gdate    datetime,
    mno int,
   constraint reservation_mno_fk foreign key (mno) references room(mno) on update cascade on delete cascade 
);


select count(*) from room m , rboard rb where r.mno = rb.mno and "+key+" like '%"+keyword+"%'; -- 게시물 검색 할때 쓰는 문법


select rb.* , r.mid from room r , rboard rb where r.mno = rb.mno ;
