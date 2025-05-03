create table person (
    id integer not null primary key,
    name varchar(255) not null,
    location varchar(255) not null,
    birthdate date not null
);

insert into person (id, name, location, birthdate) values
(1, 'John Doe', 'New York', '1980-01-01'),
(2, 'Jane Smith', 'Los Angeles', '1990-02-02'),
(3, 'Alice Johnson', 'Chicago', '1985-03-03'),
(4, 'Bob Brown', 'Houston', '1975-04-04'),
(5, 'Charlie Davis', 'Phoenix', '1995-05-05');
