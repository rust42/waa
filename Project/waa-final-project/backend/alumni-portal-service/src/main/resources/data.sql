set schema 'public';

insert into public.role (id, role)
values (2, 'FACULTY'),
       (1, 'STUDENT');

insert into public.user (id, email, first_name, last_name, active, is_deleted)
values (1, 'student1@gmail.com', 'student1', '', true, false),
       (2, 'student2@gmail.com', 'student2', '', true, false),
       (3, 'faculty1@gmail.com', 'faculty1', '', true, false),
       (4, 'faculty2@gmail.com', 'faculty2', '', true, false);

INSERT INTO public.department(id, type, name, is_deleted)
VALUES(1, 'Computer Science', 'Compro', false),
      (2, 'Computer Science', 'IT', false),
      (3, 'Computer Science', 'Electrical Engineering', false),
      (4, 'Art', 'Political Science', false),
      (5, 'Art', 'Musics', false),
      (6, 'Art', 'Drama', false),
      (7, 'Management', 'Economics', false),
      (8, 'Management', 'Accounting', false);

INSERT INTO public.user(id, active, email, first_name, last_name, is_deleted)
VALUES (5, true, 'tanvanmai@gmail.com', 'Van Tan', 'Mai', false),
       (6, true, 'jharnakarki@gmail.com', 'Jharna', 'Karki', false),
       (7, true, 'than.trung@gmail.com', 'Thanh Trung', 'Le', false),
       (8, true, 'binod.pant@hotmail.com', 'Binod', 'Pant', false),
       (9, true, 'saroj@gmail.com', 'Saroj', 'Dev', false),
       (10, true, 'rajiv.gyawali@gmail.com', 'Rajiv', 'Gyawali', false),
       (11, true, 'jenishghimire97@gmail.com', 'Jenish', 'Ghimire', false),
       (12, true, 'saurav.shrestha@gmail.com', 'Saurav', 'Shrestha', false),
       (13, true, 'krishna.hari@gmail.com', 'Krishna Hari', 'Shrestha', false),
       (14, true, 'prabeen.soti@gmail.com', 'Prabeen', 'Soti', false),
       (15, true, 'sushil@gmail.com', 'Sushil', 'Subedi', false),
       (16, true, 'pradip.dhung@gmail.com', 'Pradip', 'Dhungel', false),
       (17, true, 'bishwo@gmail.com', 'Bishwo Deep', 'Ghimire', false),
       (18, true, 'sanju.baba@gmail.com', 'Sanjay', 'Koju', false),
       (19, true, 'shravan@gmail.com', 'Shravan', 'Adhikari', false),
       (20, true, 'niraula.me@gmail.com', 'Rahul', 'Niraula', false),
       (21, true, 'supriya.ghishing@gmail.com', 'Supriya', 'Ghising', false),
       (22, true, 'omkar.me@gmail.com', 'Omkar', 'Chaudhary', false),
       (23, true, 'sudip@gmail.com', 'Sudip', 'Adhikari', false),
       (24, true, 'bibek@gmail.com', 'Bibek', 'Thakkar', false),
       (25, true, 'bibek.karki.me@gmail.com', 'Bibek', 'Karki', false),
       (26, true, 'raj@gmail.com', 'Rajendra', 'Shrestha', false),
       (27, true, 'raksh@gmail.com', 'Rakesh', 'Shrestha', false),
       (28, true, 'rohish.me@gmail.com', 'Rohit', 'Shrestha', false),
       (29, true, 'praupreti.me@gmail.com', 'Pravash', 'Upreti', false),
       (30, true, 'niwal.hello@gmail.com', 'Nirmal', 'Silwal', false),
       (31, true, 'ajashama.me@gmail.com', 'Anjana', 'Sharma', false),
       (32, true, 'sndpkrl007@gmail.com', 'Sandeep', 'Koirala', false);



INSERT INTO public.student(id, city, gpa, state, major_id)
VALUES (1, 'Fairfield', 3.5, 'Iowa', 1),
       (2, 'Fairfield', 3.5, 'Iowa', 2),
       (4, 'Fairfield', 3.5, 'Iowa', 1),
       (5, 'Oakland', 3.6, 'California', 2),
       (32, 'Oakland', 3.6, 'California', 2),
       (6, 'Boston', 3.8, 'Massachussets', 3),
       (7, 'Irving', 4.0, 'Texas', 4),
       (9, 'Boston', 3.8, 'Massachussets', 1),
       (10, 'Irving', 4.0, 'Texas', 4),
       (11, 'Outumwa', 3.6, 'Iowa', 2),
       (12, 'Dallas', 3.8, 'Texas', 4),
       (13, 'Burlington', 4.0, 'Iowa', 4),
       (14, 'San Fransisco', 3.6, 'California', 2),
       (15, 'Arlington', 3.8, 'Texas', 3),
       (16, 'Mt. Pleasant', 4.0, 'Iowa', 4),
       (17, 'Jefferson', 3.6, 'Missouri', 2),
       (18, 'Boston', 3.8, 'Massachussets', 3),
       (19, 'Irving', 4.0, 'Texas', 4),
       (20, 'Oakland', 3.6, 'California', 2),
       (21, 'Boston', 3.8, 'Massachussets', 3),
       (22, 'Irving', 4.0, 'Texas', 1),
       (23, 'St. Luoious', 3.6, 'Missouri', 2),
       (24, 'Ozark', 3.8, 'Missouri', 2),
       (25, 'Chicago', 4.0, 'Illionois', 4),
       (26, 'Outumwa', 3.6, 'Iowa', 2),
       (27, 'Dallas', 3.8, 'Texas', 3),
       (28, 'Burlington', 4.0, 'Iowa', 1),
       (29, 'San Fransisco', 3.6, 'California', 2),
       (30, 'Arlington', 3.8, 'Texas', 3),
       (31, 'Boston', 3.8, 'Massachussets', 3);


insert into public.faculty (id, department_id)
values (3, 1),
       (8, 2);

insert into public.user_role (user_id, roles_id)
values (1, 1),
       (2, 1),
       (3, 2),
       (8, 2);

insert into public.user_role (user_id, roles_id)
values (4, 1),
       (5, 1),
       (6, 1),
       (7, 1),
       (9, 1),
       (10, 1),
       (11, 1),
       (12, 1),
       (13, 1),
       (14, 1),
       (15, 1),
       (16, 1),
       (17, 1),
       (18, 1),
       (19, 1),
       (20, 1),
       (21, 1),
       (22, 1),
       (23, 1),
       (24, 1),
       (25, 1),
       (26, 1),
       (27, 1),
       (28, 1),
       (29, 1),
       (30, 1),
       (31, 1),
       (32, 1);

SELECT setval('hibernate_sequence', (SELECT last_value FROM hibernate_sequence) + 1000, true);
SELECT setval('department_sequence', (SELECT last_value FROM department_sequence) + 1000, true);
SELECT setval('file_sequence', (SELECT last_value FROM file_sequence) + 1000, true);
SELECT setval('job_advertisement_sequence', (SELECT last_value FROM job_advertisement_sequence) + 1000, true);
SELECT setval('job_applied_sequence', (SELECT last_value FROM job_applied_sequence) + 1000, true);
SELECT setval('job_history_sequence', (SELECT last_value FROM job_history_sequence) + 1000, true);
SELECT setval('role_id_seq', (SELECT last_value FROM role_id_seq) + 1000, true);
SELECT setval('tag_sequence', (SELECT last_value FROM tag_sequence) + 1000, true);


INSERT INTO public.tag (id, tag_name)
VALUES (1, 'REACT'),
       (2, 'Spring boot'),
       (3, 'Angular'),
       (4, 'NoSql'),
       (5, 'MySql');

insert into public.student_tag (student_list_id, interested_tags_id)
values  (1, 2),
        (1, 3),
        (2, 3),
        (2, 1),
        (2, 5);

INSERT INTO public.comment (id, comment, student_id) VALUES (1025, 'You should working harder', 1),
                                                            (1, 'You should working harder', 2),
                                                            (4, 'You should working harder', 4),
                                                            (5, 'You should working harder', 5),
                                                            (6, 'You should working harder', 6),
                                                            (7, 'You should working harder', 7),
                                                            (9, 'You should working harder', 9),
                                                            (10, 'You should working harder', 10),
                                                            (11, 'You should working harder', 11),
                                                            (12, 'You should working harder', 12),
                                                            (13, 'You should working harder', 13),
                                                            (14, 'You should working harder', 14),
                                                            (15, 'You should working harder', 15),
                                                            (16, 'You should working harder', 16),
                                                            (17, 'You should working harder', 17),
                                                            (18, 'You should working harder', 18),
                                                            (19, 'You should working harder', 19),
                                                            (20, 'You should working harder', 20),
                                                            (21, 'You should working harder', 21),
                                                            (22, 'You should working harder', 22),
                                                            (23, 'You should working harder', 23),
                                                            (24, 'You should working harder', 24),
                                                            (25, 'You should working harder', 25),
                                                            (26, 'You should working harder', 26),
                                                            (27, 'You should working harder', 27);

INSERT INTO public.job_advertisement (id, benefits, city, company_name, creation_time, description, is_deleted, short_description, state, title, student_id) VALUES (3, 'You Will Receive The Following Benefits
Medical Insurance
Dental Benefits
Vision Benefits
Paid Time Off (PTO)
401(k)', 'Washington', 'Motion Recruitment', '2022-05-26 00:00:00.000000', 'In this position, you will be working on Galaxy Framework product. The product is a suite of tools and web services that address the main aspects of a simulation workflow, and they offer great benefits too.

Does this sound like an interesting role for you? Apply today to be considered!!

Required Skills & Experience
Java
Swing
MySQL
JavaScript
Eclipse
5+ years of professional experience
Desired Skills & Experience
Computer Science degree or related degree
What You Will Be Doing

Daily Responsibilities
80% Hands On
20% Team Collaboration', false, 'A Computer Software Company based out of New Mexico is looking for Software Engineers to join their team. It is a full-time remote role. The tech stack they are using is Java, Swing, MySQL, JavaScript, and Eclipse
', 'DC', 'Software Engineer (100% remote, Java)', 2);
INSERT INTO public.job_advertisement (id, benefits, city, company_name, creation_time, description, is_deleted, short_description, state, title, student_id) VALUES (4, 'In addition to a competitive benefits program consisting of medical and life insurance, retirement plans, and time off, eligible employees may also have access to

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
INSERT INTO public.job_advertisement (id, benefits, city, company_name, creation_time, description, is_deleted, short_description, state, title, student_id) VALUES (6, 'You Will Receive The Following Benefits
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
50% new development, 50% maintenance', false, 'A prominent Software Company that is the leading software and services provider to communications and media companies across the globe is looking to add a Senior Full Stack Engineer to their technical team in the North Dallas area. The company delivers business improvements to drive growth and profitability for their clients by providing dynamic and continuous transformation – with a fucus of turning innovation into real business value', 'Texas', 'Software Engineer (Java)', 5);
INSERT INTO public.job_advertisement (id, benefits, city, company_name, creation_time, description, is_deleted, short_description, state, title, student_id) VALUES (5, 'Base salary
$71,000/yr - $99,000/yr

Additional compensation types
Sign-on bonus, Bonus', 'O''Fallon', 'Mastercard', '2022-05-26 00:00:00.000000', 'Software Engineer II

Who is Mastercard?

Mastercard is a global technology company in the payments industry. Our mission is to connect and power an inclusive, digital economy that benefits everyone, everywhere by making transactions safe, simple, smart, and accessible. Using secure data and networks, partnerships and passion, our innovations and solutions help individuals, financial institutions, governments, and businesses realize their greatest potential.

Our decency quotient, or DQ, drives our culture and everything we do inside and outside of our company. With connections across more than 210 countries and territories, we are building a sustainable world that unlocks priceless possibilities for all.

Overview

The Transaction Switching program enables frictionless commerce through a secure, highly performant and operationally resilient platform that drives credit and debit transaction growth.  The program supports Credit, Debit, Prepaid and Commercial portfolios, through channels such as Automated Teller Machines (ATM), Point-of-Sale (POS), Electronic Commerce (E-Comm) and Digital products.

As a software engineer at Mastercard, you will be responsible for the analysis, design, development and delivery of software solutions. You will also define requirements for new applications and customizations, adhering to standards, processes and best practices.

Role

As a Software Engineer I at Mastercard, you are expected to carry out the following general duties:

Follow given directions and procedures in software delivery tasks (code development, test, deployment)

Deliver assigned work, seeking guidance from experienced team members

Proactively seek code reviews of your work from experienced members

Seek help to understand the big picture and end-to-end logical architecture of systems in ownership areas

Ask lots of questions

Provide feedback and suggestions on areas to improve

Learn and understand the use of Mastercard technology policies in everyday work

Demonstrate active learning and sharing of software practices via Guild/Engineering community initiatives

All About You

Has ability to write secure code in three or more languages (e.g., Java, JavaScript, C, C++, SQL)

Has skills in building applications using open frameworks to achieve reuse and reduce development times (e.g., SpringBoot, Steeltoe, Angular, DCP, and others)

Understands the basic tenets of building and running mission critical software capabilities (security, customer experience, testing, operability, simplification, service-oriented architecture)

Understands requirement analysis being essential part of delivering value to our customers and partners and participate in elaboration, prioritization, and effort estimation; Understands different SDLC practices (SAFe/Scrum/Kanban/Waterfall) and the delivery situations they are used for

Understands and implements standard branching (e.g., Gitflow) and peer review practices

Understands and builds test code at unit level, service level, and integration level to ensure code and functional coverage

Apply tools (e.g., Sonar, Zally, Checkmarx) and techniques to scan and measure code quality and anti-patterns as part of development activity

Has skills to author test cases leveraging behavior-driven development and customer journey concepts

Understands Continuous Integration (CI) and Delivery (CD) concepts, and capabilities to support automation, pipelines, virtualization, and containerization; Aware of configuration management using tools (e.g., Puppet, Chef) and CI/CD tools (e.g., Artifactory, Jenkins, Git, Sonar)

A plus if candidate understands Unix-based systems and command line usage such as shell scripting

Bachelor''s degree in software engineering, computer science, information technology or related discipline preferred, or equivalent work experience

COVID-19 Considerations

We value the safety of each member of our community because we know we’re all in this together. In many locations, which may change over time, we’ve implemented a virtual hiring process and continue to interview candidates by video or phone. In addition, in some locations, only individuals who have been fully vaccinated will be permitted inside Mastercard offices until further notice.

In the US, Mastercard is a government contractor, which may legally require most Mastercard employees to be vaccinated unless a verified approved medical or religious exemption is granted. Further, we are currently making every effort towards having employees return to work in the office 2 days per week, if that makes sense for their team. Everyone must be vaccinated to enter Mastercard offices at this time. Therefore, we expect all candidates to be vaccinated or to be approved for a medical or religious accommodation prior to commencing work at Mastercard.

In the US, Mastercard is an inclusive Equal Employment Opportunity employer that considers applicants without regard to gender, gender identity, sexual orientation, race, ethnicity, disabled or veteran status, or any other characteristic protected by law. If you require accommodations or assistance to complete the online application process, please contact reasonable_accommodation@mastercard.com and identify the type of accommodation or assistance you are requesting. Do not include any medical or health information in this email. The Reasonable Accommodations team will respond to your email promptly.

Corporate Security Responsibility

Responsibilities

All activities involving access to Mastercard assets, information, and networks comes with an inherent risk to the organization and, therefore, it is expected that every person working for, or on behalf of, Mastercard is responsible for information security and must
Abide by Mastercard’s security policies and practices;
Ensure the confidentiality and integrity of the information being accessed;
Report any suspected information security violation or breach, and
Complete all periodic mandatory security trainings in accordance with Mastercard’s guidelines.', false, 'We work to connect and power an inclusive, digital economy that benefits everyone, everywhere by making transactions safe, simple, smart and accessible. Using secure data and networks, partnerships and passion, our innovations and solutions help individuals, financial institutions, governments and businesses realize their greatest potential. Our decency quotient, or DQ, drives our culture and everything we do inside and outside of our company. We cultivate a culture of inclusion for all employees that respects their individual strengths, views, and experiences. We believe that our differences enable us to be a better team – one that makes better decisions, drives innovation and delivers better business results.', 'MO ', 'Software Engineer II', 4);
INSERT INTO job_advertisement (id, benefits, city, company_name, creation_time, description, is_deleted, short_description, state, title, student_id) VALUES (2, 'Base salary
$73,000/yr - $124,000/yr', 'Charlotte', 'JPMorgan Chase & Co.', '2022-05-26 00:00:00.000000', 'This role is for a senior engineer to work with the Wholesale Loan Technology team at JPMorgan Chase. Wholesale Loans Technology supports Commercial Lending activities (Bi-Lateral or Multi-Lateral Loans) for multiple lines of businesses of JP Morgan Chase. As a member of the Wholesale Loan Technology team, you will be working on the Aurora Servicing Express application platform, a multi-year strategic investment providing a digital experience for our clients changing and simplifying the way we do business.

You should bring strong communication and comprehension skills and experience working in an Agile environment with continuous integration and delivery. You should be able to apply your experience to identify the right path forward, execute the next steps and get work done as an individual, as a team member and with management support. You should be able to collaborate and work effectively in a team and across teams. A successful individual will be a problem solver and bring in best practices to the team.

Specifically The Role Requires You To
Design, analyze, develop, test, debug, and model the system components necessary to produce potentially shippable product iterations with each sprint.
Deliver high quality code that complies with standards and satisfies architectural tests.
Perform pair programming and assist the team in supporting the application in all required environments.
Provide estimates for user stories utilizing planning poker.
Participate in product backlog prioritization, story mapping, and story splitting activities.
Prepare and lead sprint review meetings showcasing completed product backlog items to the product owner and subject matter experts.
Proactively look to develop and implement best practices across the entire project team, practicing continuous process improvement.
Collaborate face to face and virtually with the Product Owner, Operations and Technology partners around the world.
Required Skills
Bachelor''s or Master''s Degree - preferably in Computer Science
Minimum 4+ years of hands-on programming/application development experience utilizing Java, Spring Boot, REST, Micro Services, Cloud Native, Agile, DevOps and TDD
Core Java (Java 8+) skills having experience in OOAD, Threads & Concurrency and Collections
Experience working on Unix
Strong experience utilizing standalone Spring Framework integration
Proficiency building RESTful web services with Java and Spring Boot
Proficiency building modern user interfaces with Angular or React JS
Hands on experience utilizing Kafka messaging
Recent experience with a cloud platform such as Pivotal Cloud Foundry or AWS. Utilization of Container technologies such as Docker and Kubernetes is a plus
Good SQL/PL-SQL Skills and Hibernate/JPA
Passionate about testing strategy, problem solving, learning new skills, sharing expertise and knowledge
Strong written and oral communication
Good presentation and influencing skills
Proven ability to collaborate with global teams
Experience working in Agile teams, ability to focus and grasp business concepts explained during PBRs.
Desired Skills
Experience in Production support.
Experience working in a financial services environment with good working knowledge of associated accounting principles
Previous experience with wholesale loans business
JPMorgan Chase & Co., one of the oldest financial institutions, offers innovative financial solutions to millions of consumers, small businesses and many of the world''s most prominent corporate, institutional and government clients under the J.P. Morgan and Chase brands. Our history spans over 200 years and today we are a leader in investment banking, consumer and small business banking, commercial banking, financial transaction processing and asset management.

We recognize that our people are our strength and the diverse talents they bring to our global workforce are directly linked to our success. We are an equal opportunity employer and place a high value on diversity and inclusion at our company. We do not discriminate on the basis of any protected attribute, including race, religion, color, national origin, gender, sexual orientation, gender identity, gender expression, age, marital or veteran status, pregnancy or disability, or any other basis protected under applicable law. In accordance with applicable law, we make reasonable accommodations for applicants'' and employees'' religious practices and beliefs, as well as any mental health or physical disability needs.

The health and safety of our colleagues, candidates, clients and communities has been a top priority in light of the COVID-19 pandemic. JPMorgan Chase was awarded the "WELL Health-Safety Rating" for all of our 6,200 locations globally based on our operational policies, maintenance protocols, stakeholder engagement and emergency plans to address a post-COVID-19 environment.

As a part of our commitment to health and safety, we have implemented various COVID-related health and safety requirements for our workforce. Employees are expected to follow the Firm''s current COVID-19 or other infectious disease health and safety requirements, including local requirements. Requirements include sharing information including your vaccine card in the firm''s vaccine record tool, and may include mask wearing. Requirements may change in the future with the evolving public health landscape. JPMorgan Chase will consider accommodation requests as required by applicable law.', false, 'Commercial Banking serves global clients, including corporations, municipalities, financial institutions, and not-for-profit entities with annual revenues generally ranging in billions of dollars. Our Commercial Bankers serve these clients by operating in 14 of the 15 top U.S. major markets. Our professionals'' industry knowledge and experience combined with our dedicated service model, comprehensive solutions, and local expertise to make us the #1 commercial bank in our retail branch footprint.', 'NC', 'Software Engineer (100% remote, Java)', 1);
INSERT INTO job_advertisement (id, benefits, city, company_name, creation_time, description, is_deleted, short_description, state, title, student_id) VALUES (1, 'Base salary
$73,000/yr - $124,000/yr', 'Irving', 'City', '2022-05-26 00:00:00.000000', 'Responsibilities:
Utilize knowledge of applications development procedures and concepts, and basic knowledge of other technical areas to identify and define necessary system enhancements, including using script tools and analyzing/interpreting code
Consult with users, clients, and other technology groups on issues, and recommend programming solutions, install, and support customer exposure systems
Apply fundamental knowledge of programming languages for design specifications.
Analyze applications to identify vulnerabilities and security issues, as well as conduct testing and debugging
Serve as advisor or coach to new or lower level analysts
Identify problems, analyze information, and make evaluative judgements to recommend and implement solutions
Resolve issues by identifying and selecting solutions through the applications of acquired technical experience and guided by precedents
Has the ability to operate with a limited level of direct supervision.
Can exercise independence of judgement and autonomy.
Acts as SME to senior stakeholders and /or other team members.
Appropriately assess risk when business decisions are made, demonstrating particular consideration for the firm''s reputation and safeguarding Citigroup, its clients and assets, by driving compliance with applicable laws, rules and regulations, adhering to Policy, applying sound ethical judgment regarding personal behavior, conduct and business practices, and escalating, managing and reporting control issues with transparency.
Qualifications:
2-5 years of relevant experience in the Financial Service industry
Intermediate level experience in Applications Development role
Consistently demonstrates clear and concise written and verbal communication
Demonstrated problem-solving and decision-making skills
Ability to work under pressure and manage deadlines or unexpected changes in expectations or requirements
Education:
Bachelor’s degree/University degree or equivalent experience
This job description provides a high-level review of the types of work performed. Other job-related duties may be assigned as required.

Programming in JAVA is mandatory. SQL experience in working with ORACLE database. Any DevOps experience is a plus.', false, 'The Applications Development Intermediate Programmer Analyst is an intermediate level position responsible for participation in the establishment and implementation of new or revised application systems and programs in coordination with the Technology team. The overall objective of this role is to contribute to applications systems analysis and programming activities.', 'Texas', 'Java Developer 2 (1A) C11', 1);

insert into job_advertisement_tag (job_advertisements_id, tags_id)
values ( 1, 1),
       ( 1, 2),
       ( 2, 2),
       ( 2, 4),
       ( 3, 1),
       ( 3, 2),
       ( 4, 3),
       ( 4, 4),
       ( 5, 1),
       ( 5, 2),
       ( 6, 5),
       ( 6, 4);

insert into public.job_application (id, cover_letter, creation_time, is_deleted, title, applicant_id, job_advertisement_id)
values  (1, 'cover letter 1', '2022-08-04', false, 'title 1', 6, 1),
        (2, 'cover letter 2', '2022-08-05', false, 'title 2', 5, 1);
