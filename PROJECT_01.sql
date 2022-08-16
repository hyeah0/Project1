CREATE TABLE member_info (
    id varchar2(20) PRIMARY KEY,
    pwd varchar2(20) NOT NULL,
    memberName varchar2(20) NOT NULL,
    phone varchar2(20) NOT NULL,
    cardNum varchar2(20) NOT NULL,
    cardPwd number(4) NOT NULL
);

CREATE TABLE movie_info(
    movieNum varchar2(2) PRIMARY KEY,
    movieName varchar2(20) NOT NULL,
    movieTime varchar2(10) NOT NULL,
    movieImg varchar2(30) NOT NULL,
    movieGenre varchar2(10) NOT NULL,
    movieAge varchar2(10) NOT NULL
);

CREATE TABLE cinema_info(
    cinemaNum varchar2(10) PRIMARY KEY,
    cinemaName varchar2(10) NOT NULL,
    movieNum varchar2(2) REFERENCES movie_info(movieNum)
);

CREATE TABLE seat_info(
    seatNum varchar2(10) PRIMARY KEY,
    seatRoom varchar2(10) NOT NULL,
    seatRound varchar2(10),
    seatDate varchar2(10) NOT NULL,
    seatBoolean varchar2(10) NOT NULL,
    seatPrice varchar2(10) NOT NULL,
    cinemaNum varchar2(10) REFERENCES cinema_info(cinemaNum)
);

CREATE TABLE ticket_info(
    ticketNum varchar2(10) PRIMARY KEY,
    seatNum varchar2(10) REFERENCES seat_info(seatNum)
);

COMMIT;