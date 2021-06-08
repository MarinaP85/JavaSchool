
set schema public;

drop table IF EXISTS Fibonachi;

create table Fibonachi (
    n INT NOT NULL,
    nFibonachi INT NOT NULL
);

CREATE UNIQUE INDEX IF NOt EXISTS UNIQUE_FIBONACHI ON Fibonachi(n, nFibonachi);

