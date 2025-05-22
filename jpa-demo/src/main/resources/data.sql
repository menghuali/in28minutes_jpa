insert into course (id, name, created_time, last_updated_time, is_deleted) values 
    (10001, 'Java', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, false), 
    (10002, 'Spring', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, false), 
    (10003, 'Hibernate', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, false),
    (10004, 'Azure', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, false);

insert into passport (id, number) values
    (40001, 'K34892107'),
    (40002, 'X92154388'),
    (40003, 'M08372659');

insert into student (id, name, passport_id) values
    (20001,'Peter Parker', 40001),
    (20002,'Bruce Wayne', 40002),
    (20003,'Clark Kent', 40003);

insert into review (id, rating, description, course_id) values
    (50001, 'FIVE', 'Clear, practical, and great for Java beginners.', 10001),
    (50002, 'FIVE', 'Good Spring overview with helpful hands-on projects.', 10002),
    (50003, 'FIVE', 'Easy to follow, perfect for Hibernate basics.', 10003),
    (50004, 'FIVE', 'Excellent Java course—clear, practical, and great for beginners.', 10001);

insert into student_course(student_id, course_id) values
    (20001,10001),
    (20001,10002),
    (20002,10002),
    (20002,10003),
    (20003,10003);