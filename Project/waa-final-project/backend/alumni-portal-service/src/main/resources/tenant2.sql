DROP SCHEMA IF EXISTS tenant2 CASCADE;
create schema tenant2;
set schema 'tenant2';

create sequence department_sequence;

alter sequence department_sequence owner to postgres;

create sequence file_sequence;

alter sequence file_sequence owner to postgres;

create sequence hibernate_sequence;

alter sequence hibernate_sequence owner to postgres;

create sequence job_advertisement_sequence;

alter sequence job_advertisement_sequence owner to postgres;

create sequence job_applied_sequence;

alter sequence job_applied_sequence owner to postgres;

create sequence job_history_sequence;

alter sequence job_history_sequence owner to postgres;

create sequence tag_sequence;

alter sequence tag_sequence owner to postgres;

create table category
(
    id   integer not null
        primary key,
    name varchar(255)
);

alter table category
    owner to postgres;

create table department
(
    id         integer not null
        primary key,
    is_deleted boolean not null,
    name       varchar(255),
    type       varchar(255)
);

alter table department
    owner to postgres;

create table role
(
    id   serial
        primary key,
    role varchar(255)
        constraint uk_bjxn5ii7v7ygwx39et0wawu0q
            unique
);

alter table role
    owner to postgres;

create table tag
(
    id       integer not null
        primary key,
    tag_name varchar(255)
);

alter table tag
    owner to postgres;

create table "user"
(
    id            integer not null
        primary key,
    active        boolean not null,
    email         varchar(255)
        constraint uk_ob8kqyqqgmefl0aco34akdtpe
            unique,
    external_id   varchar(255),
    external_type varchar(255),
    first_name    varchar(255),
    is_deleted    boolean not null,
    last_name     varchar(255)
);

alter table "user"
    owner to postgres;

create table activity_log
(
    id        integer not null
        primary key,
    duration  bigint,
    info      varchar(255),
    operation varchar(255),
    time      timestamp,
    user_id   integer
        constraint fkibtftvn7x3fb2v2ss4hdopqc0
            references "user"
);

alter table activity_log
    owner to postgres;

create table faculty
(
    last_logged_int_at timestamp,
    id                 integer not null
        primary key
        constraint fkan1thl1b1kfyxktvhv500ka9p
            references "user",
    department_id      integer
        constraint fkjjbxuh1s9fdxcqjenmsxpfj3r
            references department
);

alter table faculty
    owner to postgres;

create table notification_info
(
    id      integer not null
        primary key,
    time    timestamp,
    token   varchar(255),
    user_id integer
        constraint fkdm41hshqcuwlmrgny2eiue7u5
            references "user"
);

alter table notification_info
    owner to postgres;

create table offensive_word_log
(
    id      integer not null
        primary key,
    time    timestamp,
    word    varchar(255),
    user_id integer
        constraint fkoj7usp9jn814s5363tf3le7lq
            references "user"
);

alter table offensive_word_log
    owner to postgres;

create table product
(
    id          integer not null
        primary key,
    name        varchar(255),
    price       integer not null,
    rating      integer not null,
    category_id integer
        constraint fk1mtsbur82frn64de7balymq9s
            references category,
    user_id     integer
        constraint fkrdm347otg7es905rv4wyi2eee
            references "user"
);

alter table product
    owner to postgres;

create table review
(
    id         integer not null
        primary key,
    comment    varchar(255),
    product_id integer
        constraint fkiyof1sindb9qiqr9o8npj8klt
            references product
);

alter table review
    owner to postgres;

create table student
(
    city               varchar(255),
    gpa                real    not null,
    last_logged_int_at timestamp,
    state              varchar(255),
    id                 integer not null
        primary key
        constraint fkjwchh3n51d5avtd32c1lpowg8
            references "user",
    major_id           integer
        constraint fkbqoi5c3trs14j8dqn2iw64aej
            references department
);

alter table student
    owner to postgres;

create table comment
(
    id         integer not null
        primary key,
    comment    varchar(255),
    student_id integer
        constraint fkmtl0wtdu6f791qxljup5r0jhb
            references student
);

alter table comment
    owner to postgres;

create table job_advertisement
(
    id                integer not null
        primary key,
    benefits          text,
    city              varchar(255),
    company_name      varchar(255),
    creation_time     timestamp,
    description       text,
    is_deleted        boolean not null,
    short_description text,
    state             varchar(255),
    title             varchar(255),
    student_id        integer
        constraint fk64wgkkcws9ijw6r1aeq7bn977
            references student
);

alter table job_advertisement
    owner to postgres;

create table job_advertisement_tag
(
    job_advertisements_id integer not null
        constraint fk4kjq6ayl9s32uvdk4q7f62r67
            references job_advertisement,
    tags_id               integer not null
        constraint fkbupb0fng9961l61991pjaqi57
            references tag
);

alter table job_advertisement_tag
    owner to postgres;

create table job_application
(
    id                   integer not null
        primary key,
    cover_letter         varchar(255),
    creation_time        timestamp,
    is_deleted           boolean not null,
    title                varchar(255),
    applicant_id         integer
        constraint fk6fr1qcl0l9xhdjlpeihg9b6xl
            references student,
    job_advertisement_id integer
        constraint fkpihll7wt0evy8gd6qtx3iops7
            references job_advertisement
);

alter table job_application
    owner to postgres;

create table file
(
    id                   integer not null
        primary key,
    creation_time        date,
    is_deleted           boolean not null,
    path                 varchar(255),
    job_advertisement_id integer
        constraint fk1pj88jgk1uc81r15og6n6us77
            references job_advertisement,
    job_application_id   integer
        constraint fkn50x1dlxtsf4lvsij8oh4h7vj
            references job_application
);

alter table file
    owner to postgres;

create table job_history
(
    id          integer not null
        primary key,
    description varchar(255),
    comments    varchar(255),
    company     varchar(255),
    end_date    timestamp,
    is_deleted  boolean not null,
    start_date  timestamp,
    title       varchar(255),
    student_id  integer
        constraint fkiv46dfchhklrxfkm5rt6uff77
            references student
);

alter table job_history
    owner to postgres;

create table job_history_tag
(
    job_histories_id integer not null
        constraint fkqy09ued3in9orrrrhdsqvkft1
            references job_history,
    tags_id          integer not null
        constraint fkburxmnqy169u5cv92kmdllga6
            references tag
);

alter table job_history_tag
    owner to postgres;

create table student_tag
(
    student_list_id    integer not null
        constraint fkhl49bikmifi4ti8qc1tqigtbv
            references student,
    interested_tags_id integer not null
        constraint fkpmjnktw5qto693he39g78mjjt
            references tag
);

alter table student_tag
    owner to postgres;

create table user_role
(
    user_id  integer not null
        constraint fkhjx9nk20h4mo745tdqj8t8n9d
            references "user",
    roles_id integer not null
        constraint fkhwenaj5yuw6lwnmx3dvtofbdb
            references role
);

alter table user_role
    owner to postgres;


insert into role (id, role)
values (2, 'FACULTY'),
       (1, 'STUDENT');

insert into tenant2.user (id, email, first_name, last_name, active, is_deleted)
values (1, 'student1@yahoo.com', 'student1', '', true, false),
       (2, 'student2@yahoo.com', 'student2', '', true, false),
       (3, 'faculty1@yahoo.com', 'faculty1', '', true, false),
       (4, 'faculty2@yahoo.com', 'faculty2', '', true, false);

INSERT INTO department(id, type, name, is_deleted)
VALUES(1, 'Computer Science', 'Compro', false),
      (2, 'Computer Science', 'IT', false),
      (3, 'Computer Science', 'Electrical Engineering', false),
      (4, 'Art', 'Political Science', false),
      (5, 'Art', 'Musics', false),
      (6, 'Art', 'Drama', false),
      (7, 'Management', 'Economics', false),
      (8, 'Management', 'Accounting', false);

insert into faculty (id, department_id)
values (3, 1),
       (4, 2);

INSERT INTO student(id, city, gpa, state, major_id)
VALUES (1, 'Fairfield', 3.5, 'Iowa', 1),
       (2, 'Oakland', 3.6, 'California', 2);

insert into user_role (user_id, roles_id)
values (1, 1),
       (2, 1),
       (3, 2),
       (4, 2);

INSERT INTO tag (id, tag_name)
VALUES (1, 'REACT'),
       (2, 'Spring boot'),
       (3, 'Angular'),
       (4, 'NoSql'),
       (5, 'MySql');

insert into student_tag (student_list_id, interested_tags_id)
values  (1, 2),
        (1, 3),
        (2, 3),
        (2, 1),
        (2, 5);

SELECT setval('hibernate_sequence', (SELECT last_value FROM hibernate_sequence) + 1000, true);
SELECT setval('department_sequence', (SELECT last_value FROM department_sequence) + 1000, true);
SELECT setval('file_sequence', (SELECT last_value FROM file_sequence) + 1000, true);
SELECT setval('job_advertisement_sequence', (SELECT last_value FROM job_advertisement_sequence) + 1000, true);
SELECT setval('job_applied_sequence', (SELECT last_value FROM job_applied_sequence) + 1000, true);
SELECT setval('job_history_sequence', (SELECT last_value FROM job_history_sequence) + 1000, true);
SELECT setval('role_id_seq', (SELECT last_value FROM role_id_seq) + 1000, true);
SELECT setval('tag_sequence', (SELECT last_value FROM tag_sequence) + 1000, true);

INSERT INTO job_advertisement (id, benefits, city, company_name, creation_time, description, is_deleted, short_description, state, title, student_id) VALUES (1, 'In addition to a competitive benefits program consisting of medical and life insurance, retirement plans, and time off, eligible employees may also have access to

IBM offers a wide range of resources for eligible IBMers to thrive both inside and outside of work.

12 weeks of paid parental bonding leave. Family care options are also available to support eligible employees during COVID-19.
World-class training and educational resources on our personalized, AI-driven learning platform. IBM''s learning culture supports your restless attitude to grow your skills and build the depth and scale of knowledge needed to achieve your career goals.
Well-being programs to support mental and physical health.
Financial programs that empower you to plan, save, and manage your money (including expert financial counseling, 401(k), IBM stock discount, etc.).
Select educational reimbursement opportunities.
Diverse and inclusive employee resource groups where you can network and connect with IBMers across the globe.
Giving and volunteer programs to benefit charitable organizations and local communities.
Discounts on retail products, services, and experiences.

We consider qualified applicants with criminal histories, consistent with applicable law.', 'Sandy Springs', 'IBM', '2022-05-26 00:00:00.000000', 'Your Role and Responsibilities

Partner with Data Scientists and Data Engineers to operationalize machine learning and optimization models that deliver new insights to the business
Build data APIs and data delivery services that support critical operational and analytical applications for IBM’s internal business operations, customers, and partners
Take responsibility for ensuring that model code, data pipelines, API’s, and user interfaces are developed and deployed successfully into production
Troubleshooting issues that arise and supporting production applications
Continuously integrate code into AWS cloud environments

Required Technical and Professional Expertise

3 + years of experience with Java/J2EE technologies with Hibernate, JMS or any other messaging frameworks
2 + years of experience with SOAP and REST service development skills.
2 + years of experience of parsing/creating XML/JSON files.
2 + years of experience with front end technologies such as CSS, HTML, Java Script, AngularJS/NodeJS , Struts, Spring, Springboot etc
2 + years of experience with Microservices, Design patterns, data structures (stacks, queues, trees etc.), Data Modeling and Object Model design for applications.

Preferred Technical And Professional Expertise

Functional knowledge of the airline business or airline IT processes
MS degree, preferably in a technical or scientific field
Strong understanding of Application Security including information security principles and realization, web app security and PCI DSS Compliance
Solid understanding of core SQL principles and RDBMS.

About Business Unit

IBM Consulting is IBM’s consulting and global professional services business, with market leading capabilities in business and technology transformation. With deep expertise in many industries, we offer strategy, experience, technology, and operations services to many of the most innovative and valuable companies in the world. Our people are focused on accelerating our clients’ businesses through the power of collaboration. We believe in the power of technology responsibly used to help people, partners and the planet.

This job requires you to provide your COVID-19 vaccination status with supporting documentation, where legally permissible.

Your Life @ IBM

Are you craving to learn more? Prepared to solve some of the world''s most unique challenges? And ready to shape the future for millions of people? If so, then it''s time to join us, express your individuality, unleash your curiosity and discover new possibilities.

Every IBMer, and potential ones like yourself, has a voice, carves their own path, and uses their expertise to help co-create and add to our story. Together, we have the power to make meaningful change – to alter the fabric of our clients, of society and IBM itself, to create a truly positive impact and make the world work better for everyone.

It''s time to define your career.

About IBM

IBM’s greatest invention is the IBMer. We believe that through the application of intelligence, reason and science, we can improve business, society and the human condition, bringing the power of an open hybrid cloud and AI strategy to life for our clients and partners around the world.Restlessly reinventing since 1911, we are not only one of the largest corporate organizations in the world, we’re also one of the biggest technology and consulting employers, with many of the Fortune 50 companies relying on the IBM Cloud to run their business. At IBM, we pride ourselves on being an early adopter of artificial intelligence, quantum computing and blockchain. Now it’s time for you to join us on our journey to being a responsible technology innovator and a force for good in the world.

Location Statement', false, 'As an JAVA Developer, you will lead IBM into the future by translating system requirements into the design and development of customized systems in an agile environment. The success of IBM is in your hands as you transform vital business needs into code and drive innovation. Your work will power IBM and its clients globally, collaborating and integrating code into enterprise systems. You will have access to the latest education, tools and technology, and a limitless career path with the world’s technology leader. Come to IBM and make a global impact!', 'GA ', 'Java Developer', 2);
INSERT INTO job_advertisement (id, benefits, city, company_name, creation_time, description, is_deleted, short_description, state, title, student_id) VALUES (2, 'You Will Receive The Following Benefits
Medical Insurance
Dental Benefits
Vision Benefits
Paid Time Off (PTO)
401(k)', 'Dallas', 'Motion Recruitment', '2022-05-26 00:00:00.000000', 'Apply

Save
Save Software Engineer (Java) at Motion Recruitment

Show more options
Job Description

A prominent Software Company that is the leading software and services provider to communications and media companies across the globe is looking to add a Senior Full Stack Engineer to their technical team in the North Dallas area. The company delivers business improvements to drive growth and profitability for their clients by providing dynamic and continuous transformation – with a fucus of turning innovation into real business value

The new engineer will be joining a project with one of the worlds largest telecommunications companies. The ideal person for this role will have a strong background in Java, Spring Boot, Microservices, JavaScript, and Angular. Additionally being goal-oriented, having a self-starter mindset, and being able to work in a team environment are key qualities the new engineer needs to have.

Required Skills & Experience
Proficient knowledge of Java, Spring Boot, Microservices, SQL
Strong background using JavaScript and modern JavaScript framework
Excellent communication – written and verbal
Ability to work in team environment and be a team player
What You Will Be Doing
75% Backend, 25% frontend
90% coding, 10% architecture and design responsibilities
50% new development, 50% maintenance', false, 'A prominent Software Company that is the leading software and services provider to communications and media companies across the globe is looking to add a Senior Full Stack Engineer to their technical team in the North Dallas area. The company delivers business improvements to drive growth and profitability for their clients by providing dynamic and continuous transformation – with a fucus of turning innovation into real business value', 'Texas', 'Software Engineer (Java)', 1);

insert into job_application (id, cover_letter, creation_time, is_deleted, title, applicant_id, job_advertisement_id)
values  (1, 'cover letter 1', '2022-08-04', false, 'title 1', 2, 1),
        (2, 'cover letter 2', '2022-08-05', false, 'title 2', 1, 2);