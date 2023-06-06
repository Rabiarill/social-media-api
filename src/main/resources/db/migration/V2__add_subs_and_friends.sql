create table Subscriptions(
    subscriber_id int not null references Users(id) on delete cascade,
    subscribed_to_id int not null references Users(id) on delete cascade,
    primary key (subscriber_id, subscribed_to_id)
);

create table Friends(
    friend_id int not null references Users(id) on delete cascade,
    friend_second_id int not null references Users(id) on delete cascade,
    primary key (friend_id, friend_second_id)
);