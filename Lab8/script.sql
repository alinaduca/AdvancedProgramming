create table artists (
  id number primary key,
  name varchar2(50) not null
);

create table genres (
  id number primary key,
  name varchar2(50) not null
);

create table albums (
  id number primary key,
  release_year number not null,
  title varchar2(50) not null,
  artist_id number,
  foreign key (artist_id) references artists (id)
);

create table album_genres (
  album_id number,
  genre_id number,
  foreign key (album_id) references albums (id),
  foreign key (genre_id) references genres (id)
);
