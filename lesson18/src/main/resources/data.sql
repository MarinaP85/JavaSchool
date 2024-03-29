
set schema public;

drop table IF EXISTS RECIPES;
drop table IF EXISTS INGREDIENTS;

create table RECIPES (
    id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    title VARCHAR(100) NOT NULL
);

CREATE UNIQUE INDEX IF NOt EXISTS UNIQUE_RECIPE ON RECIPES(title);

create table INGREDIENTS (
    id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    product VARCHAR(100) NOT NULL,
    amount VARCHAR(20) NOT NULL,
    recipeID INT
);




